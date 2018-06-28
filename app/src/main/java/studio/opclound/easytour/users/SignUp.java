package studio.opclound.easytour.users;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opclound.easytour.internet.interfaces.SignUp_Interface;
import studio.opclound.easytour.internet.translations.SignUp_Trainslation;
import studio.opclound.easytour.mainspace.MainActivity;
import studio.opclound.easytour.photo.ClipBaseActivity;
import studio.opclound.easytour.R;

public class SignUp extends ClipBaseActivity {

    private Intent intent;
    private String userName;
    private String userPhone;
    private String sex;
    private String keyWord;
    private String headIc;
    private String description;
    private TextView tvUserName;
    private TextView tvSex;
    private TextView tvKeyWord;
    private TextView tvDescription;
    private EditText etUserName;
    private EditText etSex;
    private EditText etKeyWord;
    private EditText etDescription;
    private ImageView ivHeadIc;
    private Button btnSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }

    @Override
    public void errorLoadImg() {

    }

    @Override
    public void setImg(Bitmap img, String path) {
        ivHeadIc.setImageBitmap(img);
        headIc = path;
    }

    private void init() {
        Intent getintent = getIntent();
        userPhone = getintent.getStringExtra("userPhone");
        etUserName = findViewById(R.id.et_sign_up_user_name);
        etKeyWord = findViewById(R.id.et_sign_up_key_word);
        etSex = findViewById(R.id.et_sign_up_sex);
        etDescription = findViewById(R.id.et_sign_up_description);
        btnSignUp = findViewById(R.id.btn_sign_up);
        btnSignUp.setOnClickListener(MyListener);
        ivHeadIc = findViewById(R.id.iv_sign_up_head_ic);
        ivHeadIc.setOnClickListener(MyListener);
    }

    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_sign_up:
                    singUp();
                    break;
                case R.id.iv_sign_up_head_ic:
                    showPopupWindow(ivHeadIc);
                    break;
            }
        }
    };

    private void singUp() {
        Toast.makeText(SignUp.this, "Todo : fill in the function to get vertification code", Toast.LENGTH_SHORT).show();
        intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void request() {
        File file = new File(headIc);
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/YiYouImg/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part  和后端约定好Key，这里的partName是用image
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        // 步骤5:创建 网络请求接口 的实例
        SignUp_Interface request = retrofit.create(SignUp_Interface.class);
        //对 发送请求 进行封装(设置需要翻译的内容)
        Call<SignUp_Trainslation> signUpInformation = request.signUpInformation(
                body);

        //步骤6:发送网络请求(异步)
        signUpInformation.enqueue(new Callback<SignUp_Trainslation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<SignUp_Trainslation> call, Response<SignUp_Trainslation> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                System.out.println(response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<SignUp_Trainslation> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
            }
        });
    }

}
