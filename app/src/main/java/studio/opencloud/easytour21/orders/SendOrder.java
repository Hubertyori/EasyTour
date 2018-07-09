package studio.opencloud.easytour21.orders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import studio.opencloud.easytour21.internet.datas.UserInformationData;
import studio.opencloud.easytour21.internet.interfaces.Login_Interface;
import studio.opencloud.easytour21.internet.interfaces.SendOrder_Interface;
import studio.opencloud.easytour21.internet.translations.Login_Translation;
import studio.opencloud.easytour21.internet.translations.Register_Translation;
import studio.opencloud.easytour21.users.Login;
import studio.opencloud.easytour21.users.UserInformation;

public class SendOrder extends AppCompatActivity {
    private Button btnSend;
    private EditText etLocation;
    private EditText etDate;
    private EditText etPeopleNum;
    private EditText etDescription;
    private TextView tvWithDraw;


    private Intent intent;
    private UserInformationData userData;

    private String location;
    private String date;
    private int peopleNum;
    private String description;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_order);
        init();
//        Intent intent = getIntent();
//        phone = intent.getStringExtra("userPhone");
        intent = getIntent();
        userData = intent.getParcelableExtra("userData");
        //默认使用测试用号码：15523352924
        phone = userData.getTelephone();
    }

    private void init() {
        btnSend = findViewById(R.id.btn_send_order_send);
        etDate = findViewById(R.id.et_send_order_date);
        etDescription = findViewById(R.id.et_send_order_description);
        etLocation = findViewById(R.id.et_send_order_location);
        etPeopleNum = findViewById(R.id.et_send_order_people_num);
        tvWithDraw = findViewById(R.id.tv_send_order_withdraw);
        btnSend.setOnClickListener(MyListener);
        tvWithDraw.setOnClickListener(MyListener);
    }


    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_send_order_send:
                    send();
                    break;
                case R.id.tv_send_order_withdraw:
                    finish();
                    break;
            }
        }
    };

    private void send() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        // 步骤5:创建 网络请求接口 的实例
        SendOrder_Interface request = retrofit.create(SendOrder_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        collectInfo();
        Call<Register_Translation> call = request.signUpInformation(phone,location,date,peopleNum,description);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Register_Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Register_Translation> call, Response<Register_Translation> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                Toast.makeText(SendOrder.this, "请求成功", Toast.LENGTH_SHORT).show();
                System.out.println("********************************************************");
                System.out.println(response.body().getMessage());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Register_Translation> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
                Toast.makeText(SendOrder.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void collectInfo() {
        peopleNum = Integer.parseInt(etPeopleNum.getText().toString());
        location = etLocation.getText().toString();
        description = etDescription.getText().toString();
        date = etDate.getText().toString();
    }

}
