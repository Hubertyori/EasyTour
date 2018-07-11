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
import studio.opencloud.easytour21.internet.interfaces.guide.TakeOrder_Interface;
import studio.opencloud.easytour21.internet.translations.Register_Translation;

public class TakeOrder extends AppCompatActivity {
    private Button btnTakeOrder;
    private EditText etOrderID;

    private String orderID;
    private String guideIDNumber;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_order);
        init();
        intent = getIntent();
        orderID = intent.getStringExtra("orderID");
        guideIDNumber = intent.getParcelableExtra("userData");
        //默认使用测试用身份证号码：500225199802245411
//        guideIDNumber = "500225199802245411";
    }

    private void init() {
        btnTakeOrder = findViewById(R.id.btn_take_orders);
        etOrderID = findViewById(R.id.et_take_order_orderID);
        btnTakeOrder.setOnClickListener(MyListener);
    }

    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_take_orders:
                    take();
                    break;
            }
        }
    };

    private void take() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        // 步骤5:创建 网络请求接口 的实例
        TakeOrder_Interface request = retrofit.create(TakeOrder_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        collectInfo();
        Call<Register_Translation> call = request.takeOrder(orderID, guideIDNumber);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Register_Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Register_Translation> call, Response<Register_Translation> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                Toast.makeText(TakeOrder.this, "请求成功", Toast.LENGTH_SHORT).show();
                System.out.println("********************************************************");
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Register_Translation> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
                Toast.makeText(TakeOrder.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void collectInfo() {
        orderID = etOrderID.getText().toString();
    }
}
