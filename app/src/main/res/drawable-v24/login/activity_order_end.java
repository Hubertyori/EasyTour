package com.example.xh.login;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class activity_order_end extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState){

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_order);
        Intent intent = getIntent();

    }


    public void back(View view)
    {
        Intent intent =new Intent(com.example.xh.login.activity_order_end.this,OrderActivity.class);
        //启动
        startActivity(intent);
        this.finish();
    }
}


