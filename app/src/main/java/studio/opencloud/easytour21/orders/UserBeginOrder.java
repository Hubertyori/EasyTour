package studio.opencloud.easytour21.orders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opclound.easytour.R;
import studio.opencloud.easytour21.evalution.UserEvaluate;
import studio.opencloud.easytour21.internet.datas.UserBeginOrderData;
import studio.opencloud.easytour21.internet.datas.UserInformationData;
import studio.opencloud.easytour21.internet.datas.UserOrderData;
import studio.opencloud.easytour21.internet.interfaces.pbinterface.QuerryOrder_Interface;
import studio.opencloud.easytour21.internet.interfaces.user.GetGuideInforByID_Interface;
import studio.opencloud.easytour21.internet.translations.Login_Translation;
import studio.opencloud.easytour21.internet.translations.User_Begin_Order_Translation;

public class UserBeginOrder extends AppCompatActivity {
    private UserBeginOrderData selectOrder;
    private UserInformationData userData;
    private EditText etOrderID;
    private EditText etDestination;
    private EditText etPeopleNum;
    private EditText etDescription;
    private EditText etDate;
    private EditText etPhone;
    private TextView tvWithDraw;
    private Button btnGuideDetail;

    private int orderID;
    private UserInformationData guideInfo;
    private GetGuideInforByID_Interface updateUI_interface;//处理导游信息

    private String photoUrl;
    private String phone;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_begin_order);
        Intent intent = getIntent();
        selectOrder = intent.getParcelableExtra("selectOrder");
        userData = intent.getParcelableExtra("userData");
        init();

    }

    private void init() {
        updateUI_interface = new GetGuideInforByID_Interface(){
            @Override
            public Call<Login_Translation> getGuideInforByID(int orderID) {
                return null;
            }

            @Override
            public void processData(UserInformationData uid) {
                etPhone.setText(uid.getTelephone());
                btnGuideDetail.setOnClickListener(MyListener);
                intent = new Intent(UserBeginOrder.this, UserEvaluate.class);
                intent.putExtra("selectOrder", selectOrder);
                intent.putExtra("userInformationData",uid);

            }
        };
        etPhone = findViewById(R.id.et_user_begin_order_phone);
        etOrderID = findViewById(R.id.et_user_begin_order_order_ID);
        etDestination = findViewById(R.id.et_user_begin_order_destination);
        etPeopleNum = findViewById(R.id.et_user_begin_order_order_people);
        etDescription = findViewById(R.id.et_user_begin_order_description);
        etDate = findViewById(R.id.et_user_begin_order_start_time);
        btnGuideDetail = findViewById(R.id.btn_user_begin_order_to_finish_order);

        tvWithDraw = findViewById(R.id.tv_user_begin_order_withdraw);
        tvWithDraw.setOnClickListener(MyListener);
        setData();
    }

    private void setData() {
        etOrderID.setText(String.valueOf(selectOrder.getOrderID()));
        etDestination.setText(selectOrder.getPlace());
        etPeopleNum.setText(String.valueOf(selectOrder.getNumberOfPeople()));
        etDescription.setText(selectOrder.getNote());
        etDate.setText(selectOrder.getDate());
        orderID = selectOrder.getOrderID();
        getGuideInfor();
    }

    private void getGuideInfor() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        // 步骤5:创建 网络请求接口 的实例
        GetGuideInforByID_Interface request = retrofit.create(GetGuideInforByID_Interface.class);
//使用LOGIN 的转译接口接收数据
        Call<Login_Translation> call = request.getGuideInforByID(orderID);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Login_Translation>() {
                         //请求成功时回调
                         @Override
                         public void onResponse(Call<Login_Translation> call, Response<Login_Translation> response) {
                             // 步骤7：处理返回的数据结果：输出翻译的内容
//                Toast.makeText(OrderList.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                             System.out.println("********************************************************");

                             if (response.body().getCode() != 0) {
                                 guideInfo = response.body().getData();
                                 updateUI_interface.processData(guideInfo);
                             } else {
                                 System.out.println("********************************************************");
                                 System.out.println(response.body().getMessage());
                             }
                         }

                         //请求失败时回调
                         @Override
                         public void onFailure(Call<Login_Translation> call, Throwable throwable) {
                             System.out.println("请求失败");
                             System.out.println(throwable.getMessage());
//                Toast.makeText(OrderList.this, "请求失败", Toast.LENGTH_SHORT).show();
                         }
                     }
        );
    }

    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_user_begin_order_to_finish_order:
                    finishOrder();
                    break;
                case R.id.tv_user_begin_order_withdraw:
                    finish();
                    break;
            }

        }
    };

    private void finishOrder() {
        //todo 填写导游详情制作页面表格
        startActivity(intent);
    }
}
