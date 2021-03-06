package studio.opencloud.easytour21.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import studio.opclound.easytour.R;
import studio.opencloud.easytour21.users.Login;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(WelcomeActivity.this, Login.class);

                startActivity(intent);
                WelcomeActivity.this.overridePendingTransition(R.anim.fade,
                        R.anim.my_alpha_action);
                WelcomeActivity.this.finish();
            }
        }, 2000);
    }
}
