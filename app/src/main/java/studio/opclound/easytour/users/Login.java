package studio.opclound.easytour.users;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opclound.easytour.R;
import studio.opclound.easytour.internet.datas.SignInData;
import studio.opclound.easytour.internet.interfaces.SignIn_Interface;
import studio.opclound.easytour.internet.interfaces.SignUp_Interface;
import studio.opclound.easytour.internet.translations.SignIn_Trainslation;
import studio.opclound.easytour.internet.translations.SignUp_Trainslation;
import studio.opclound.easytour.mainspace.MainActivity;

/**
 * Created by lining on 2018/3/24.
 */

public class Login extends Activity {
    private String userName;
    private String keyWord;
    private Button btnLogin;
    private TextView tvSignUp;
    private TextView tvResetKeyWord;
    private EditText etUserName;
    private EditText etKeyWord;
    private ImageView imLogo;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        etUserName = findViewById(R.id.et_login_user_name);
        etKeyWord = findViewById(R.id.et_login_key_word);
        btnLogin = findViewById(R.id.btn_login);
        tvResetKeyWord = findViewById(R.id.tv_login_reset_key_word);
        tvSignUp = findViewById(R.id.tv_login_sign_up);
        btnLogin.setOnClickListener(MyListener);
        tvSignUp.setOnClickListener(MyListener);
        tvResetKeyWord.setOnClickListener(MyListener);
    }

    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_login:
                    login();
                    break;
                case R.id.tv_login_reset_key_word:
                    vertifacation();
                    break;
                case R.id.tv_login_sign_up:
                    vertifacation();
                    break;
            }
        }
    };

    private void vertifacation() {
        intent = new Intent(Login.this, Verification.class);
        startActivity(intent);
    }
    //点击后退按钮
    @Override
    public void onBackPressed() {
        showExitDialog();
    }
    /*
     * 点击后退提示是否退出窗口
     * */
    private void showExitDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Promoting")
                .setMessage("Are you sure to exit")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Login.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
    private void login() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/YiYouImg/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        // 步骤5:创建 网络请求接口 的实例
        SignIn_Interface request = retrofit.create(SignIn_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
            collectInfo();
            Call<SignIn_Trainslation> call = request.signInInformation(userName,keyWord);

            //步骤6:发送网络请求(异步)
            call.enqueue(new Callback<SignIn_Trainslation>() {
                //请求成功时回调
                @Override
                public void onResponse(Call<SignIn_Trainslation> call, Response<SignIn_Trainslation> response) {
                    // 步骤7：处理返回的数据结果：输出翻译的内容
                    Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println(response.body().getMessage());
                    if(response.body().getCode()==1){
                        intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        Login.this.finish();
                    }
                }

                //请求失败时回调
                @Override
                public void onFailure(Call<SignIn_Trainslation> call, Throwable throwable) {
                    System.out.println("请求失败");
                    System.out.println(throwable.getMessage());
                    Toast.makeText(Login.this, "请求失败", Toast.LENGTH_SHORT).show();
                }
            });


}

    private void collectInfo() {
        userName = etUserName.getText().toString();
        keyWord = etKeyWord.getText().toString();
    }
}
