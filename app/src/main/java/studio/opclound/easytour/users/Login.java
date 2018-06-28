package studio.opclound.easytour.users;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import studio.opclound.easytour.R;
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
        Toast.makeText(Login.this, "Todo : fill in the vertification function", Toast.LENGTH_SHORT).show();
        intent = new Intent(Login.this, Verification.class);
        startActivity(intent);
        this.finish();
    }

    private void login() {
        Toast.makeText(Login.this, "Todo : fill in the login function", Toast.LENGTH_SHORT).show();
        intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }


}
