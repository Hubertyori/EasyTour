package studio.opencloud.easytour21.users;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opclound.easytour.R;
import studio.opencloud.easytour21.internet.datas.UserInformationData;
import studio.opencloud.easytour21.internet.interfaces.ChangeGuideInformation_Interface;
import studio.opencloud.easytour21.internet.interfaces.ChangeInformation_Interface;
import studio.opencloud.easytour21.internet.interfaces.Login_Interface;
import studio.opencloud.easytour21.internet.translations.Login_Translation;
import studio.opencloud.easytour21.internet.translations.Register_Translation;
import studio.opencloud.easytour21.photo.ClipBaseActivity;

public class UserInformation extends ClipBaseActivity {
    private Button btnUpdate;
    private EditText etUserName;
    private EditText etSex;
    private EditText etDescription;
    private EditText etPhone;
    private Intent intent;
    private UserInformationData userInformation;
    private LinearLayout linearLayout;
    private EditText etServercity;
    private EditText etGuidestar;
    private EditText etID;
    private EditText etGuideID;
    private EditText etRealName;
    private TextView tvToBeGuide;
    private TextView tvChangePassWord;
    private TextView tvChangePhone;
    private ImageView ivHeadIc;

    private String tel;
    private String password;
    private String nickname;
    private String sex;
    private String introduce;
    private String imageInfo = "default";
    private String headIc;

    private String IDNumber;
    private String servercity;
//    private EditText et
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        init();
    }

    @Override
    public void errorLoadImg() {
    }

    @Override
    public void setImg(Bitmap img, String path) {
        ivHeadIc.setImageBitmap(img);
        headIc = path;
        imageInfo = "update";
    }

    //初始化数据
    private void init() {

        //添加控件
        ivHeadIc = findViewById(R.id.iv_change_information_head_icon);
        tvChangePassWord = findViewById(R.id.tv_change_information_change_pass_word);
        tvToBeGuide = findViewById(R.id.tv_change_information_to_be_guide);
        etPhone = findViewById(R.id.et_change_information_phone);

        etUserName = findViewById(R.id.et_change_information_user_name);
        etDescription = findViewById(R.id.et_change_information_description);
        etSex = findViewById(R.id.et_change_information_sex);
        //添加点击相应事件
//        btnUpdate.setOnClickListener(MyListener);
        tvChangePassWord.setOnClickListener(MyListener);
        tvToBeGuide.setOnClickListener(MyListener);
        ivHeadIc.setOnClickListener(MyListener);
        linearLayout = findViewById(R.id.ll_user_information_layout);
        //读取个人信息数据
        intent = getIntent();
        getInformation();
        //显示个人信息
        showInformation();
    }
//动态生成控件信息
    private void showInformation() {
        if(userInformation.getIsguide().equals("yes")){
            //添加显示导游信息
            System.out.println("*****************************************************");
            System.out.println(userInformation.getGuidestar());
            //导入新控件
            LinearLayout ll =  (LinearLayout) LayoutInflater.from(UserInformation.this).inflate(R.layout.guide_information,null);

            ll.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));//此处设置权重
            //控件赋值
            btnUpdate = (Button) ll.findViewById(R.id.btn_change_information_update);
            etRealName =(EditText) ll.findViewById(R.id.et_guide_information_real_name);
            etID =(EditText) ll.findViewById(R.id.et_guide_information_ID);
            etGuideID =(EditText) ll.findViewById(R.id.et_guide_information_Guide_ID);
            etGuidestar =(EditText) ll.findViewById(R.id.et_guide_information_star);
            etServercity = (EditText) ll.findViewById(R.id.et_guide_information_city);
            tvToBeGuide.setVisibility(View.GONE);//移除注册成为导游字段
            //移除控件原parent
            ll.removeAllViews();
            linearLayout.addView(etRealName);
            linearLayout.addView(etID);
            linearLayout.addView(etGuideID);
            linearLayout.addView(etServercity);
            linearLayout.addView(etGuidestar);
            linearLayout.addView(btnUpdate);
            //***
            etSex.setText(userInformation.getSex());
            etDescription.setText(userInformation.getIntroduce());
            etUserName.setText(userInformation.getNickname());
            etGuidestar.setText(""+userInformation.getGuidestar());
            etGuidestar.setEnabled(Boolean.FALSE);
            etGuideID.setText("" + userInformation.getGuideid());
            etGuideID.setEnabled(Boolean.FALSE);
            etID.setText(userInformation.getGuideIDnumbr());
            etID.setEnabled(Boolean.FALSE);
            etRealName.setText(userInformation.getNickname());
            etRealName.setEnabled(Boolean.FALSE);
            etServercity.setText(userInformation.getGuideservercity());
            etPhone.setText(userInformation.getTelephone());
            btnUpdate.setOnClickListener(MyListener);
            //加载网络图片
            Picasso.with(UserInformation.this)
                    .load(userInformation.getHeadphoto())
                    .placeholder(R.drawable.default_ic)
                    .error(R.drawable.error)
                    .into(ivHeadIc);
        }else{
            //显示游客信息
            etSex.setText(userInformation.getSex());
            etDescription.setText(userInformation.getIntroduce());
            etUserName.setText(userInformation.getNickname());
            etPhone.setText(userInformation.getTelephone());
            etPhone.setEnabled(Boolean.FALSE);
            //导入新控件
            LinearLayout ll =  (LinearLayout) LayoutInflater.from(UserInformation.this).inflate(R.layout.guide_information,null);

            ll.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));//此处设置权重
            //控件赋值
            btnUpdate = (Button) ll.findViewById(R.id.btn_change_information_update);
            ll.removeAllViews();
            linearLayout.addView(btnUpdate);
            btnUpdate.setOnClickListener(MyListener);
            //加载网络图片
            Picasso.with(UserInformation.this)
                    .load(userInformation.getHeadphoto())
                    .placeholder(R.drawable.default_ic)
                    .error(R.drawable.error)
                    .into(ivHeadIc);
        }
    }

    //读取传入的用户个人信息数据
    private void getInformation() {
        userInformation = intent.getParcelableExtra("userData");
    }

    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_change_information_update:
                    update();
                    break;
                case R.id.tv_change_information_to_be_guide:
                    Intent toBeGuideIntent = new Intent(UserInformation.this,ToBeGuide.class);
                    toBeGuideIntent.putExtra("userPhone",userInformation.getTelephone());
                    startActivity(toBeGuideIntent);
                    break;
                case R.id.iv_change_information_head_icon:
                    showPopupWindow(ivHeadIc);
                    break;
                case R.id.tv_change_information_change_pass_word:
                    Intent toChangePassword = new Intent(UserInformation.this,Verification.class);
                    startActivity(toChangePassword);
            }
        }
    };
//上传修改信息
    private void update() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        // 步骤5:创建 网络请求接口 的实例
        ChangeInformation_Interface request = retrofit.create(ChangeInformation_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        collectUserInfo();
        if(imageInfo.equals("default")){
        Call<Register_Translation> call = request.upDateUserInfo(tel,password,nickname,sex,introduce,imageInfo);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Register_Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Register_Translation> call, Response<Register_Translation> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                Toast.makeText(UserInformation.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                if(response.body().getCode()==1){
                    Toast.makeText(UserInformation.this, "请求成功", Toast.LENGTH_SHORT).show();
                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Register_Translation> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
                Toast.makeText(UserInformation.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });}
        else{
            File file = new File(headIc);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            Call<Register_Translation> call = request.upLoadImg(tel,password,nickname,sex,introduce,imageInfo,body);

            //步骤6:发送网络请求(异步)
            call.enqueue(new Callback<Register_Translation>() {
                //请求成功时回调
                @Override
                public void onResponse(Call<Register_Translation> call, Response<Register_Translation> response) {
                    // 步骤7：处理返回的数据结果：输出翻译的内容
                    Toast.makeText(UserInformation.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    if(response.body().getCode()==1){
                        Toast.makeText(UserInformation.this, "请求成功", Toast.LENGTH_SHORT).show();
                    }
                }

                //请求失败时回调
                @Override
                public void onFailure(Call<Register_Translation> call, Throwable throwable) {
                    System.out.println("请求失败");
                    System.out.println(throwable.getMessage());
                    Toast.makeText(UserInformation.this, "请求失败", Toast.LENGTH_SHORT).show();
                }
            });
        }
        //检测是否为导游发送导游修改信息
        if(userInformation.getIsguide().equals("yes")){
            //步骤4:创建Retrofit对象
             retrofit = new Retrofit.Builder()
                    .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                    .build();
            // 步骤5:创建 网络请求接口 的实例
            ChangeGuideInformation_Interface grequest = retrofit.create(ChangeGuideInformation_Interface.class);

            //对 发送请求 进行封装(设置需要翻译的内容)
            collectGuideInfo();
                Call<Register_Translation> call = grequest.upDateGuideInfo(IDNumber,servercity);

                //步骤6:发送网络请求(异步)
                call.enqueue(new Callback<Register_Translation>() {
                    //请求成功时回调
                    @Override
                    public void onResponse(Call<Register_Translation> call, Response<Register_Translation> response) {
                        // 步骤7：处理返回的数据结果：输出翻译的内容
                        Toast.makeText(UserInformation.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        if(response.body().getCode()==1){
                            Toast.makeText(UserInformation.this, "导游请求成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    //请求失败时回调
                    @Override
                    public void onFailure(Call<Register_Translation> call, Throwable throwable) {
                        System.out.println("导游请求失败");
                        System.out.println(throwable.getMessage());
                        Toast.makeText(UserInformation.this, "导游请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }

    private void collectGuideInfo() {
        IDNumber= etID.getText().toString();
        servercity=etServercity.getText().toString();
    }

    //收集信息。
    private void collectUserInfo() {
          tel = etPhone.getText().toString();
          password = userInformation.getPassword();
          nickname = etUserName.getText().toString();
          sex = etSex.getText().toString();
          introduce = etDescription.getText().toString();
    }

}
