package studio.opencloud.easytour21.orders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opclound.easytour.R;
import studio.opencloud.easytour21.internet.datas.UserInformationData;
import studio.opencloud.easytour21.internet.datas.UserOrderData;
import studio.opencloud.easytour21.internet.interfaces.CancelOrder_Interface;
import studio.opencloud.easytour21.internet.translations.Register_Translation;

public class UserIdleOrder extends AppCompatActivity {
    private UserOrderData selectOrder;
    private UserInformationData userData;
    private EditText etOrderID;
    private EditText etDestination;
    private EditText etPeopleNum;
    private EditText etDescription;
    private EditText etDate;
    private Button btnCancelOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_idle_order);
        Intent intent =getIntent();
        selectOrder = intent.getParcelableExtra("selectOrder");
        userData = intent.getParcelableExtra("userData");
        init();
    }

    private void init() {
        etOrderID = findViewById(R.id.et_user_idel_order_ID);
        etDestination = findViewById(R.id.et_user_idel_destination);
        etPeopleNum = findViewById(R.id.et_user_idel_order_people);
        etDescription = findViewById(R.id.ed_user_idel_description);
        etDate = findViewById(R.id.et_user_idel_start_time);
        btnCancelOrder = findViewById(R.id.btn_user_idel_cancel_order);
        btnCancelOrder.setOnClickListener(MyListener);
        setData();
    }

    private void setData() {
        etOrderID.setText(selectOrder.getOrderID());
        etDestination.setText(selectOrder.getPlace());
        etPeopleNum.setText(selectOrder.getNumberOfPeople());
        etDescription.setText(selectOrder.getNote());
        etDate.setText(selectOrder.getDate());
    }

    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_user_idel_cancel_order:
                    cancelOrder();
                    break;
            }

        }
    };

    private void cancelOrder() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        // 步骤5:创建 网络请求接口 的实例
        CancelOrder_Interface request = retrofit.create(CancelOrder_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        System.out.println("*******************************************************");
        Call<Register_Translation> call = request.cancelOrder(etOrderID.getText().toString());

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Register_Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Register_Translation> call, Response<Register_Translation> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                Toast.makeText(UserIdleOrder.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<Register_Translation> call, Throwable throwable) {
                System.out.println("请求失败*****************************************");
                System.out.println(throwable.getMessage());
                Toast.makeText(UserIdleOrder.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
