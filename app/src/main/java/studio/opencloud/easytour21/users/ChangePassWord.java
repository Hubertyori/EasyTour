package studio.opencloud.easytour21.users;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opclound.easytour.R;
import studio.opencloud.easytour21.internet.interfaces.ResetPassWord_Interface;
import studio.opencloud.easytour21.internet.translations.ResetPassWord_Translation;
import studio.opencloud.easytour21.mainspace.MainActivity;

public class ChangePassWord extends Activity {
    private EditText etPassWord;
    private EditText etConfirmPassWord;
    private Button btnSignUp;
    private String passWord;
    private String confirmPassWord;
    private String userPhone;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass_word);
        init();
    }

    private void init() {
        etPassWord = findViewById(R.id.et_new_pass_word);
        etConfirmPassWord = findViewById(R.id.et_confirm_pass_word);
        btnSignUp = findViewById(R.id.btn_change_pass_word);
        btnSignUp.setOnClickListener(MyListener);
    }
    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_change_pass_word:
                    if (confirm() == true)
                    {
                        resetPassWord();
                    }else{
                        Toast.makeText(ChangePassWord.this, "请确保两次输入相同", Toast.LENGTH_LONG).show();
                        etPassWord.setText("");
                        etConfirmPassWord.setText("");
                    }
                    break;
            }
        }
    };

    private void resetPassWord() {
        collentInfo();
        request();
        intent = new Intent(ChangePassWord.this, Login.class);
        startActivity(intent);
        ChangePassWord.this.finish();
    }

    private void request() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        ResetPassWord_Interface request = retrofit.create(ResetPassWord_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
            Call<ResetPassWord_Translation> call = request.resetPassWord(userPhone, passWord);

            //步骤6:发送网络请求(异步)
            call.enqueue(new Callback<ResetPassWord_Translation>() {
                //请求成功时回调
                @Override
                public void onResponse(Call<ResetPassWord_Translation> call, Response<ResetPassWord_Translation> response) {
                    // 步骤7：处理返回的数据结果：输出翻译的内容
                    Toast.makeText(ChangePassWord.this, "重置成功", Toast.LENGTH_SHORT).show();
                    System.out.println(response.body().getMessage());

                }

                //请求失败时回调
                @Override
                public void onFailure(Call<ResetPassWord_Translation> call, Throwable throwable) {
                    System.out.println("请求失败");
                    System.out.println(throwable.getMessage());

                }
            });

    }

    private void collentInfo() {
        intent = getIntent();
        userPhone = intent.getStringExtra("userPhone");
    }

    //后退键返回
    @Override
    public void onBackPressed() {
        this.finish();
    }
/*
* 验证两次输入密码是否一致
* */
    private boolean confirm() {
        passWord = etPassWord.getText().toString();
        confirmPassWord = etConfirmPassWord.getText().toString();
        if (passWord.equals(confirmPassWord)){
            return true;
        }
        else
            return false;
    }
}
