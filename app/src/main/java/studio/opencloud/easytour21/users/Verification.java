package studio.opencloud.easytour21.users;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import studio.opclound.easytour.R;
import studio.opencloud.easytour21.Time.TimeCount;
import studio.opencloud.easytour21.miaodi.httpApiDemo.common.httpApiDemo.IndustrySMS;
import studio.opencloud.easytour21.miaodi.httpApiDemo.common.httpApiDemo.common.Config;

public class Verification extends Activity {
    private String userPhone;
    private TextView tvUserPhone;
    private EditText etUserPhone;
    private EditText etVertificationCode;
    private Button btnVertification;
    private Button btnGetVertificationCode;
    private Intent intent;
    private TextView tvWithDraw;
    private String vertificationCode;
    private String VertificationCode;
    private String accountSid = Config.ACCOUNT_SID;
    private String auth_token = Config.AUTH_TOKEN;
    private static final int SIGNUP = 0;
    private static final int RESETPWD = 1;
    private int status;
    private String to = "15523352924";
    private String smsContent = "【易游】尊敬的用户，您的验证码为123456，请于10分钟内正确输入，如非本人操作，请忽略此短信。";
    private String templateid = "339517518";
    private String timestamp = "";
    private String sig = "";

    private TimeCount time;
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertification);
        intent = getIntent();
        status = intent.getIntExtra("status", -1);
        init();
        time = new TimeCount(60000, 1000, btnGetVertificationCode);
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
            switch (v.getId()) {
                case R.id.btn_vertification:
//                    singUp();
                    if (!isMobile(etUserPhone.getText().toString())) {
                        Toast.makeText(Verification.this, "手机号码输入有误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    vertifacation();
                    break;
                case R.id.btn_vertification_code:
                    if (!isMobile(etUserPhone.getText().toString())) {
                        Toast.makeText(Verification.this, "手机号码输入有误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    time.start();
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
     *
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
//        Toast.makeText(Verification.this,"Todo : fill in the function to get vertification code",Toast.LENGTH_SHORT).show();
        // 验证码通知短信接口
//        VertificationCode = IndustrySMS.execute();
        //步骤4:创建Retrofit对象
        new Thread() {
            @Override
            public void run() {
                //这里写入子线程需要做的工作
                to = etUserPhone.getText().toString();
                VertificationCode = IndustrySMS.execute(to);
            }
        }.start();
    }

    public static String StrToMd5(String str) {

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            md5.update((str).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assert md5 != null;
        byte b[] = md5.digest();

        int i;
        StringBuilder buf = new StringBuilder("");

        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        String result = buf.toString();
        return result;
    }

    private void vertifacation() {
//        Toast.makeText(Verification.this,"Todo : fill in the vertification function",Toast.LENGTH_SHORT).show();
        vertificationCode = etVertificationCode.getText().toString();
        to = etUserPhone.getText().toString();
        if (!vertificationCode.isEmpty()) {
            if (!vertificationCode.equals(VertificationCode))
                Toast.makeText(Verification.this, "您的验证码输入错误", Toast.LENGTH_SHORT).show();
            else
                singUp();
        } else
            Toast.makeText(Verification.this, "验证码不能为空", Toast.LENGTH_SHORT).show();

    }

    //后退键返回
    @Override
    public void onBackPressed() {
        goBack();
    }

    private void singUp() {
        if (status == SIGNUP) {
            intent = new Intent(Verification.this, Register.class);
            intent.putExtra("userPhone", userPhone);
            startActivity(intent);
        }else{
            intent = new Intent(Verification.this, ChangePassWord.class);
            intent.putExtra("userPhone", userPhone);
            startActivity(intent);
        }
    }
}
