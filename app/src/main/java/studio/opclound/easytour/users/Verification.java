package studio.opclound.easytour.users;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import studio.opclound.easytour.R;

public class Verification extends Activity{
    private String userPhone;
    private TextView tvUserPhone;
    private EditText etUserPhone;
    private EditText etVertificationCode;
    private Button btnVertification;
    private Button btnGetVertificationCode;
    private Intent intent;
    private TextView tvWithDraw;

    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertification);
        init();
    }

    private void init() {
        etUserPhone = findViewById(R.id.et_vertification_user_phone);
        etVertificationCode = findViewById(R.id.et_vertification_code);
        btnVertification = findViewById(R.id.btn_vertification);
        btnVertification.setOnClickListener(MyListener);
        btnGetVertificationCode = findViewById(R.id.btn_vertification_code);
        btnGetVertificationCode.setOnClickListener(MyListener);
        tvWithDraw = findViewById(R.id.tv_withdraw);
        tvWithDraw.setOnClickListener(MyListener);
    }
    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_vertification:
                    vertifacation();
                    break;
                case R.id.btn_vertification_code:
                    if(!isMobile(etUserPhone.getText().toString())){
                        Toast.makeText(Verification.this,"Telephone's format is wrong",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    getVertificationCode();
                    break;
                case R.id.tv_withdraw:
                    goBack();
                    break;
            }
        }
    };
    /**
     * 校验手机号
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
//返回
    private void goBack() {
        this.finish();
    }

    private void getVertificationCode() {
        Toast.makeText(Verification.this,"Todo : fill in the function to get vertification code",Toast.LENGTH_SHORT).show();
    }

    private void vertifacation() {
//        Toast.makeText(Verification.this,"Todo : fill in the vertification function",Toast.LENGTH_SHORT).show();
        if(etVertificationCode != null){
        singUp();
        }
        else
            Toast.makeText(Verification.this,"验证码不能为空",Toast.LENGTH_SHORT).show();

    }
    //后退键返回
    @Override
    public void onBackPressed() {
        goBack();
    }
    private void singUp() {
        userPhone = etUserPhone.getText().toString();
        intent = new Intent(Verification.this, SignUp.class);
        intent.putExtra("userPhone",userPhone);
        startActivity(intent);

    }
}
