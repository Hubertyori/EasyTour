package com.example.xh.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class Guest_Information extends AppCompatActivity {


    private TextView mguest_name;
    private TextView mdescription;
    private RatingBar mratingbar;
    private TextView mrate;

    private String name;
    private String description;
    private float rating;
    private String rate;

    protected void onCreate(Bundle savedInstanceState){


        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestinf);
        Intent intent = getIntent();

        mguest_name=findViewById(R.id.text_view_profile_name);
        mdescription=findViewById(R.id.text_view_profile_description);
        mratingbar=findViewById(R.id.ratingBar);
        mrate=findViewById(R.id.TV_rating);
        getData();
        InitOrderdata();



    }

    public void InitOrderdata()//读取数据
    {
        mguest_name.setText(name);
        mdescription.setText(description);
        mratingbar.setRating(rating);
         rate=String.valueOf(rating);
         mrate.setText(rate);

    }

    public void getData()
    {
        name="杨译绗";
        description="老子真的牛逼";
        rating=4.5f;


    }

    public void back(View view)
    {
        Intent intent =new Intent(com.example.xh.login.Guest_Information.this, com.example.xh.login.OrderActivity_guide.class);
        //启动
        startActivity(intent);
        this.finish();
    }

}
