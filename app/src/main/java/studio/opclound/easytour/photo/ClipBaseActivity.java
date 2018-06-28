package studio.opclound.easytour.photo;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import studio.opclound.easytour.R;
import studio.opclound.easytour.clip.AcClipImg;


public abstract class ClipBaseActivity extends CheckPermissionsActivity {
    private String cachPath;

    private File cacheFile;
    private File cameraFile;
    //动态获取权限监听
    private static PermissionListener mListener;

    //图片保存的文件夹


    public String PHOTOSAVEPATH;

    private final static int PHOTOBYGALLERY = 0;//从相册获取照片

    private final static int PHOTOTACK = 1;//拍照获取

    private final static int PHOTOCOMPLETEBYGALLERY = 3;//完成

    private final static int PHOTOCOMPLETEBYTAKE = 2;//完成

    //以当前时间的毫秒数当做文件名，设置好的图片的路径
    private String photoname = System.currentTimeMillis() + ".png";

    private String mPath;  //要找的图片路径

    private PopupWindow mPopWindow;

    private LayoutInflater mLayoutInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayoutInflater = LayoutInflater.from(this);
        PHOTOSAVEPATH = getFilesDir() + "/crop_photo/";
        cachPath = getDiskCacheDir(this) + "/crop_image.jpg";
        cacheFile = getCacheFile(new File(getDiskCacheDir(this)), "crop_image.jpg");
    }

    public String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    private File getCacheFile(File parent, String child) {
        // 创建File对象，用于存储拍照后的图片
        File file = new File(parent, child);

        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    protected void showPopupWindow(ImageView img) {
        if (mPopWindow == null) {
            View view = mLayoutInflater.inflate(R.layout.pop_select_photo, null);
            mPopWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT, true);
            initPopupWindow(view);
        }
        mPopWindow.showAtLocation(img, Gravity.CENTER, 0, 0);
    }


    protected void initPopupWindow(View view) {
        TextView gallery = view.findViewById(R.id.id_pop_select_photo_tv_from_gallery);
        TextView take = view.findViewById(R.id.id_pop_select_photo_tv_take_photo);
        TextView cancel = view.findViewById(R.id.id_pop_select_photo_tv_cancel);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
                startToGetPhotoByGallery();
            }
        });
        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
                String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestRuntimePermission(permissions, new PermissionListener() {
                    @Override
                    public void onGranted() {
                        startToGetPhotoByTack();
                    }

                    @Override
                    public void onDenied(List<String> deniedPermission) {
                        //有权限被拒绝，什么也不做好了，看你心情
                    }
                });

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopWindow.isShowing())
                    mPopWindow.dismiss();
            }
        });
        mPopWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    //andrpoid 6.0 需要写运行时权限
    public void requestRuntimePermission(String[] permissions, PermissionListener listener) {

        mListener = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(ClipBaseActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(ClipBaseActivity.this, permissionList.toArray(new String[permissionList.size()]), 1);
        } else {
            mListener.onGranted();
        }
    }

    private void startToGetPhotoByGallery() {
        Intent openGalleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        openGalleryIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(openGalleryIntent, PHOTOBYGALLERY);
    }


    private void startToGetPhotoByTack() {
        photoname = String.valueOf(System.currentTimeMillis()) + ".jpg";
        cameraFile = getCacheFile(new File(getDiskCacheDir(this)), photoname);
        Uri imageUri;
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            imageUri = Uri.fromFile(cameraFile);
//            imageUri = Uri.fromFile(new File(PHOTOSAVEPATH, photoname));

        } else {
            /**
             * 7.0 调用系统相机拍照不再允许使用Uri方式，应该替换为FileProvider
             * 并且这样可以解决MIUI系统上拍照返回size为0的情况
             */
            openCameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            imageUri = FileProvider.getUriForFile(ClipBaseActivity.this, "studio.opencloud.easytour21.fileprovider", cameraFile);
        }

        openCameraIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(openCameraIntent, PHOTOTACK);

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        Uri uri;
        switch (requestCode) {
            case PHOTOBYGALLERY:
                uri = data.getData();
                if (null == uri) {
                    // TODO: 2018/3/30  图片加载失败,提示
                    break;
                }
                if (DocumentsContract.isDocumentUri(this, uri)) {
                    String wholeID = DocumentsContract.getDocumentId(uri);
                    String id = wholeID.split(":")[1];
                    String[] column = {MediaStore.Images.Media.DATA};
                    String sel = MediaStore.Images.Media._ID + "=?";
                    Cursor cursor = this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column,
                            sel, new String[]{id}, null);
                    int columnIndex;
                    try {
                        columnIndex = cursor.getColumnIndex(column[0]);
                    } catch (NullPointerException e) {
                        errorLoadImg();
                        break;
                    }
                    if (cursor.moveToFirst()) {
                        mPath = cursor.getString(columnIndex);
                    }
                    cursor.close();
                } else {
                    String[] projection = {MediaStore.Images.Media.DATA};
                    Cursor cursor = this.getContentResolver().query(uri, projection, null, null, null);
                    int column_index;
                    try {
                        column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    } catch (NullPointerException e) {
                        errorLoadImg();
                        break;
                    }
                    cursor.moveToFirst();
                    mPath = cursor.getString(column_index);
                    cursor.close();
                }
                // 获取到照片之后调用裁剪acticity
                Intent intentGalley = new Intent(this, AcClipImg.class);
                intentGalley.putExtra("path", mPath);
                startActivityForResult(intentGalley, PHOTOCOMPLETEBYGALLERY);
//                setImg(BitmapUtil.getBitmapFormPath(this, mPath),mPath);
                break;
            case PHOTOTACK:
                mPath = cameraFile.getAbsolutePath();
                Intent intentTake = new Intent(this, AcClipImg.class);
                intentTake.putExtra("path", mPath);
                startActivityForResult(intentTake, PHOTOCOMPLETEBYTAKE);
//                setImg(BitmapUtil.getBitmapFormPath(this, mPath),mPath);//问题出在这里
                break;
            case PHOTOCOMPLETEBYTAKE:
                final String temppath = data.getStringExtra("path");
//                setImg(BitmapUtil.getBitmapFormPath(this, mPath),mPath);
                setImg(BitmapUtil.getBitmapFormPath(this, temppath), temppath);
                //删除旧文件
                File file = new File(mPath);
                file.delete();
                mPath = temppath;
                break;
            case PHOTOCOMPLETEBYGALLERY:
                final String temppathgallery = data.getStringExtra("path");
//                setImg(BitmapUtil.getBitmapFormPath(this, mPath),mPath);
                setImg(BitmapUtil.getBitmapFormPath(this, temppathgallery), temppathgallery);
                mPath = temppathgallery;
                break;
        }
    }

    public abstract void errorLoadImg();

    public interface PermissionListener {
        /**
         * 成功获取权限
         */
        void onGranted();

        /**
         * 为获取权限
         *
         * @param deniedPermission
         */
        void onDenied(List<String> deniedPermission);

    }

    public abstract void setImg(Bitmap img, String path);
}
