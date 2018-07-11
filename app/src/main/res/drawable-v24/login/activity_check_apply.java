package com.example.xh.login;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class activity_check_apply extends AppCompatActivity {

    private EditText mID;
    private EditText mUsername;
    private EditText mDes;
    private EditText mDate;
    private EditText mNum;
    private EditText mRemark;
    private EditText mTel;
    private String orderID;
    private String orderUsername;
    private String orderDes;
    private String orderDate;
    private String orderNum;
    private String orderRemark;
    private String orderTel;
    protected void onCreate(Bundle savedInstanceState){

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_apply);
        Intent intent = getIntent();


        mID=findViewById(R.id.et_order_ID);
        mUsername=findViewById(R.id.et_guest_name);
        mDes=findViewById(R.id.et_destination);
        mDate=findViewById(R.id.et_start_time);
        mNum=findViewById(R.id.et_order_people);
        mRemark=findViewById(R.id.et_remark);
        mTel=findViewById(R.id.et_guest_tel);
        InitOrderdata();
        getData();

    }


    public void back(View view)
    {
        Intent intent =new Intent(com.example.xh.login.activity_check_apply.this, com.example.xh.login.OrderActivity_guide.class);
        //启动
        startActivity(intent);
        this.finish();
    }


    public void InitOrderdata()//读取数据
    {
        orderID="2000153";
        orderDate="2018/7/20";
        orderDes="四川省 成都市 锦江区";
        orderNum="10";
        orderRemark="可爱想日";
        orderTel="15823090781";
        orderUsername="杨泽绗";


    }

    public void getData()
    {
        mID.setText(orderID);
        mDate.setText(orderDate);
        mDes.setText(orderDes);
        mNum.setText(orderNum);
        mRemark.setText(orderRemark);
        mTel.setText(orderTel);
        mUsername.setText(orderUsername);

    }


}
