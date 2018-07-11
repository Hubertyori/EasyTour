package com.example.xh.login;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class EvaluateActivity extends AppCompatActivity {

    private RatingBar ratingBar;//星级评分条
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_order);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * getRating():用于获取等级，表示选中的几颗星
                 * getStepSize():用语获取每次至少要改变多少个星级
                 * getProgress():用语获取进度，获取到的进度值为getRating()方法返回值与getStepSize()方法返回值之积
                 */
                int result = ratingBar.getProgress();
                float rating = ratingBar.getRating();
                float step = ratingBar.getStepSize();
                Log.e("星级评分条","step="+step+"result="+result+"rating="+rating);
                Toast.makeText(com.example.xh.login.EvaluateActivity.this,"你得到了"+rating+"颗星",Toast.LENGTH_SHORT).show();
            }
        });
    }
}


