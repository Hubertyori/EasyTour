package studio.opencloud.easytour21.blogs;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opclound.easytour.R;
import studio.opencloud.easytour21.internet.interfaces.pbinterface.Register_Interface;
import studio.opencloud.easytour21.internet.interfaces.pbinterface.submitBlog_Interface;
import studio.opencloud.easytour21.internet.translations.Register_Translation;
import studio.opencloud.easytour21.photo.ClipBaseActivityNotClip;
import studio.opencloud.easytour21.users.Register;

public class writeBlogActivity extends ClipBaseActivityNotClip {
    private ImageView iv;
    private String impath;
    private String isPhotoSelect;
    private String tel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_blog);
        tel = getIntent().getStringExtra("userTel");
        isPhotoSelect = "no";
        init();
    }

    @Override
    public void errorLoadImg() {

    }

    @Override
    public void setImg(Bitmap img, String path) {
        iv.setImageBitmap(img);
        impath = path;
        isPhotoSelect = "yes";
    }

    public void init() {
        //添加图片的点击事件
        iv = (ImageView) findViewById(R.id.write_blog_picture);
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showPopupWindow(iv);
            }
        });

        //提交blog的响应事件
        Button submit = (Button) findViewById(R.id.write_blog_submit);
        submit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPhotoSelect.equals("no"))
                submitBlog();
                else
                    upLoad();
                finish();
            }
        });
    }

    //获取当前系统时间
    public String getNowSysTime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        return date;

    }

    //获取图片信息，看用户是否有上传图片
    public String getImageInfo() {
        return "";
    }

    public void submitBlog() {
        //创建retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        //创建 网络请求接口 的实例
        submitBlog_Interface request = retrofit.create(submitBlog_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        if((isPhotoSelect.equals("yes")))
            upLoad();
        Call<writeBlogBean> call = request.submitBlog_CALL(tel, ((EditText) findViewById(R.id.write_blog_title)).getText().toString(),
                ((EditText) findViewById(R.id.write_blog_content)).getText().toString(), getNowSysTime(), "no");

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<writeBlogBean>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<writeBlogBean> call, Response<writeBlogBean> response) {
                if (response.body().getCode() == 1)
                    Toast.makeText(writeBlogActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(writeBlogActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<writeBlogBean> call, Throwable throwable) {
                Toast.makeText(writeBlogActivity.this, "提交失败" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void upLoad() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)7
                .build();

        // 步骤5:创建 网络请求接口 的实例
        submitBlog_Interface request = retrofit.create(submitBlog_Interface.class);

        File file = new File(impath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        if(file == null)
            System.out.println("file is null");
        Call<writeBlogBean> call = request.submitBlogWithPic_CALL(tel, ((EditText) findViewById(R.id.write_blog_title)).getText().toString(),
                ((EditText) findViewById(R.id.write_blog_content)).getText().toString(), getNowSysTime(), "yes",body);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<writeBlogBean>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<writeBlogBean> call, Response<writeBlogBean> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                if (response.body().getCode() == 1)
                    Toast.makeText(writeBlogActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(writeBlogActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                    Log.i("",response.body().getMessage()+"");
                    Log.i("","提交失败");
                }

            }

            //请求失败时回调
            @Override
            public void onFailure(Call<writeBlogBean> call, Throwable throwable) {
                Toast.makeText(writeBlogActivity.this, "提交失败" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}


