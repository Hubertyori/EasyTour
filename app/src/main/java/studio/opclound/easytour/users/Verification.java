package studio.opclound.easytour.users;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import studio.opclound.easytour.R;

public class Verification extends Activity{
    private String userPhone;
    private TextView tvUserPhone;
    private EditText etUserPhone;
    private EditText etVertificationCode;
    private Button btnVertification;
    private Button btnGetVertificationCode;
    private Intent intent;
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
    }
    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_vertification:
                    vertifacation();
                    break;
                case R.id.btn_vertification_code:
                    getVertificationCode();
                    break;
            }
        }
    };

    private void getVertificationCode() {
        Toast.makeText(Verification.this,"Todo : fill in the function to get vertification code",Toast.LENGTH_SHORT).show();
    }

    private void vertifacation() {
        Toast.makeText(Verification.this,"Todo : fill in the vertification function",Toast.LENGTH_SHORT).show();
        singUp();
    }

    private void singUp() {
        userPhone = etUserPhone.getText().toString();
        intent = new Intent(Verification.this, SignUp.class);
        intent.putExtra("userPhone",userPhone);
        startActivity(intent);
        this.finish();
    }
}
