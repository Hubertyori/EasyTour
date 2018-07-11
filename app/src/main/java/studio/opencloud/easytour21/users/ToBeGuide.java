package studio.opencloud.easytour21.users;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opclound.easytour.R;
import studio.opencloud.easytour21.internet.interfaces.user.RegisterAsGuide_Interface;
import studio.opencloud.easytour21.internet.translations.Register_Translation;

public class ToBeGuide extends AppCompatActivity {
    private Button btnSignUp;
    private EditText etRealName;
    private EditText etID;
    private EditText etGuideID;
    private EditText etCity;
    private String realName;
    private String ID;
    private String guideID;
    private Intent intent;
    private String userPhone;
    private String city;
    public static final String REGEX_ID = "^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$";
    public static final String REGEX_GuideID = "^D[0-9]{8}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_be_guide);
        init();
        intent = getIntent();
        userPhone = intent.getStringExtra("userPhone");
    }

    private void init() {
        btnSignUp = findViewById(R.id.btn_to_be_guide_signup);
        etRealName = findViewById(R.id.et_to_be_guide_realname);
        etID = findViewById(R.id.et_to_be_guide_ID);
        etCity = findViewById(R.id.et_to_be_guide_servercity);
        etGuideID = findViewById(R.id.et_to_be_guide_guideID);
        btnSignUp.setOnClickListener(MyListener);
    }

    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_to_be_guide_signup:
                    confirm();
                    break;
            }
        }
    };
//注册信息上传
    private void confirm() {
        //收集信息
        realName = etRealName.getText().toString();
        ID = etID.getText().toString();
        guideID = etGuideID.getText().toString();
        city = etCity.getText().toString();
        if(realName.isEmpty()){
            Toast.makeText(this,"姓名不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        else if(ID.isEmpty()){
            Toast.makeText(this,"身份证号不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        else if(guideID.isEmpty()){
            Toast.makeText(this,"导游证号不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        Pattern pattern1 = Pattern.compile(REGEX_ID); //粗略的校验
        Matcher matcher = pattern1.matcher(ID);
        if(!matcher.matches()){
            Toast.makeText(this,"身份证号格式错误",Toast.LENGTH_LONG).show();
            return;
        }
        pattern1 =  Pattern.compile(REGEX_GuideID); //粗略的校验
        matcher = pattern1.matcher(guideID);
        if(!matcher.matches()){
            Toast.makeText(this,"导游证号格式错误",Toast.LENGTH_LONG).show();
            return;
        }
        signUp();
    }

    private void signUp() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        // 步骤5:创建 网络请求接口 的实例
        RegisterAsGuide_Interface request = retrofit.create(RegisterAsGuide_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        Call<Register_Translation> call = request.signUpInformation(userPhone,realName,ID,guideID,city);
        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Register_Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Register_Translation> call, Response<Register_Translation> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容

                if(response.body().getCode()==1){
                    Toast.makeText(ToBeGuide.this, "注册成功，请重新启动更新数据", Toast.LENGTH_SHORT).show();
                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Register_Translation> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
                Toast.makeText(ToBeGuide.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });

}
}
