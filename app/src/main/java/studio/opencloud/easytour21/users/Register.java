package studio.opencloud.easytour21.users;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opencloud.easytour21.internet.interfaces.pbinterface.Register_Interface;
import studio.opencloud.easytour21.internet.translations.Register_Translation;
import studio.opencloud.easytour21.photo.ClipBaseActivity;
import studio.opclound.easytour.R;

public class Register extends ClipBaseActivity {

    private Intent intent;
    private String userName;
    private String userPhone;
    private String keyWord;
    private String confirmKeyWord;
    private String headIc;
    private EditText etUserName;
    private EditText etKeyWord;
    private EditText etConfirmKeyWord;
    private ImageView ivHeadIc;
    private Button btnSignUp;
    private String imageInfo;

    Date day=new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    String timestamp = df.format(day);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    @Override
    public void errorLoadImg() {

    }

    @Override
    public void setImg(Bitmap img, String path) {
        ivHeadIc.setImageBitmap(img);
        headIc = path;

        imageInfo = "imgupload";
    }

    private void init() {
        etUserName = findViewById(R.id.et_sign_up_user_name);
        etConfirmKeyWord = findViewById(R.id.et_confirm_pass_word);
        etKeyWord = findViewById(R.id.et_sign_up_key_word);
        btnSignUp = findViewById(R.id.btn_sign_up);
        btnSignUp.setOnClickListener(MyListener);
        ivHeadIc = findViewById(R.id.iv_sign_up_head_ic);
        ivHeadIc.setOnClickListener(MyListener);
        imageInfo = "default";
    }

    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_sign_up:
                    confirm();
                    singUp();
                    break;
                case R.id.iv_sign_up_head_ic:
                    showPopupWindow(ivHeadIc);
                    break;
            }
        }
    };

    private Boolean confirm() {
        keyWord = etKeyWord.getText().toString();
        confirmKeyWord = etConfirmKeyWord.getText().toString();
        userName = etUserName.getText().toString();
        if(keyWord.isEmpty()) {
            Toast.makeText(Register.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return Boolean.FALSE;
        }
        if(confirmKeyWord.isEmpty()) {
            Toast.makeText(Register.this, "验证密码不能为空", Toast.LENGTH_SHORT).show();
            return Boolean.FALSE;
        }
        if(userName.isEmpty()) {
            Toast.makeText(Register.this, "用户昵称不能为空", Toast.LENGTH_SHORT).show();
            return Boolean.FALSE;
        }
        if(keyWord.equals(confirmKeyWord))
            return Boolean.TRUE;
        else {
            Toast.makeText(Register.this, "两次输入密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
            etKeyWord.setText("");
            etConfirmKeyWord.setText("");
            return Boolean.FALSE;
        }

    }

    private void singUp() {

//        Toast.makeText(Register.this, "Todo : fill in the function to get vertification code", Toast.LENGTH_SHORT).show();
        request();
        intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        this.finish();
    }

    public void request() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)7
                .build();

        // 步骤5:创建 网络请求接口 的实例
        Register_Interface request = retrofit.create(Register_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        if (imageInfo == "default") {
            collectInfo();
            Call<Register_Translation> call = request.signUpInformation(userName, keyWord, userPhone, imageInfo);

            //步骤6:发送网络请求(异步)
            call.enqueue(new Callback<Register_Translation>() {
                //请求成功时回调
                @Override
                public void onResponse(Call<Register_Translation> call, Response<Register_Translation> response) {
                    // 步骤7：处理返回的数据结果：输出翻译的内容
                    Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println(response.body().getMessage());

                }

                //请求失败时回调
                @Override
                public void onFailure(Call<Register_Translation> call, Throwable throwable) {
                    System.out.println("请求失败");
                    System.out.println(throwable.getMessage());

                }
            });
        } else {
            collectInfo();
            File file = new File(headIc);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            if(file == null)
                System.out.println("file is null");
            Call<Register_Translation> call = request.upLoadImg(userName, keyWord, userPhone, imageInfo, body);

            //步骤6:发送网络请求(异步)
            call.enqueue(new Callback<Register_Translation>() {
                //请求成功时回调
                @Override
                public void onResponse(Call<Register_Translation> call, Response<Register_Translation> response) {
                    // 步骤7：处理返回的数据结果：输出翻译的内容
                    Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println(response.body().getMessage());
                    System.out.println("path:" + headIc);
                }

                //请求失败时回调
                @Override
                public void onFailure(Call<Register_Translation> call, Throwable throwable) {
                    System.out.println("请求失败");
                    System.out.println(throwable.getMessage());
                }
            });
        }
    }
    //点击后退按钮
    @Override
    public void onBackPressed() {
        this.finish();
    }

    private void collectInfo() {
        userName = etUserName.getText().toString();
        Intent getintent = getIntent();
        userPhone = getintent.getStringExtra("userPhone");
        keyWord = etKeyWord.getText().toString();
    }

}
