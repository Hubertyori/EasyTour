package studio.opencloud.easytour21.orders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opclound.easytour.R;
import studio.opencloud.easytour21.internet.datas.GuideOrderData;
import studio.opencloud.easytour21.internet.datas.UserInformationData;
import studio.opencloud.easytour21.internet.interfaces.guide.TakeOrder_Interface;
import studio.opencloud.easytour21.internet.translations.Register_Translation;

public class GuideIdleOrder extends AppCompatActivity {
    private EditText mID;
    private EditText mUsername;
    private EditText mDes;
    private EditText mDate;
    private EditText mNum;
    private EditText mRemark;
    private Button btnTakeOrder;
    private TextView tvWithDraw;
//    private EditText mTel;
    private String orderID;
    private String orderUsername;
    private String orderDes;
    private String orderDate;
    private String orderNum;
    private String orderRemark;
    private Intent intent;
    private UserInformationData userData;
//    private String orderTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_idel_order);
        intent = getIntent();
        userData = intent.getParcelableExtra("userData");//获取用户个人信息

        mID=findViewById(R.id.et_guide_idle_order_ID);
        mUsername=findViewById(R.id.et_guide_idle_user_name);
        mDes=findViewById(R.id.et_guide_idle_destination);
        mDate=findViewById(R.id.et_guide_idle_start_time);
        mNum=findViewById(R.id.et_guide_idle_people_number);
        mRemark=findViewById(R.id.et_guide_idle_remark);
        btnTakeOrder = findViewById(R.id.btn_guide_idle_take_order);
        btnTakeOrder.setOnClickListener(MyListener);
        tvWithDraw = findViewById(R.id.tv_guide_idle_order_withdraw);
        tvWithDraw.setOnClickListener(MyListener);
//        mTel=findViewById(R.id.et_guide_idle_user_tel);
        InitOrderdata();
        getData();

    }

    public void InitOrderdata()//读取数据
    {
        Intent intent =getIntent();
        GuideOrderData selectOrder = intent.getParcelableExtra("selectOrder");
        orderID=String.valueOf(selectOrder.getOrderID());
        orderDate=selectOrder.getDate();
        orderNum=String.valueOf(selectOrder.getNumberOfPeople());
        orderRemark=selectOrder.getNote();
        orderDes = selectOrder.getPlace();
//        orderTel="15823090781";
        orderUsername=selectOrder.getUserNickname();



    }

    public void getData()
    {
        mID.setText(orderID);
        mDate.setText(orderDate);
        mDes.setText(orderDes);
        mNum.setText(orderNum);
        mRemark.setText(orderRemark);
//        mTel.setText(orderTel);
        mUsername.setText(orderUsername);

    }

    public void back(View view)
    {
        Intent intent =new Intent(GuideIdleOrder.this,OrderList.class);
        //启动
        startActivity(intent);
        this.finish();
    }
    public void apply_guide()//接单
    {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        // 步骤5:创建 网络请求接口 的实例
        TakeOrder_Interface request = retrofit.create(TakeOrder_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        System.out.println("*******************************************************");
        Call<Register_Translation> call = request.takeOrder(orderID,userData.getGuideIDnumbr());

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Register_Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Register_Translation> call, Response<Register_Translation> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                Toast.makeText(GuideIdleOrder.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<Register_Translation> call, Throwable throwable) {
                System.out.println("请求失败*****************************************");
                System.out.println(throwable.getMessage());
                Toast.makeText(GuideIdleOrder.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_guide_idle_take_order:
                    apply_guide();
                    break;
                case R.id.tv_guide_idle_order_withdraw:
                    finish();
                    break;
            }
        }
    };
}


