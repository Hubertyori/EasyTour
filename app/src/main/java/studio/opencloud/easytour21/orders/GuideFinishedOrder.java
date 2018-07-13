package studio.opencloud.easytour21.orders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import studio.opencloud.easytour21.internet.datas.GuideGetUserInfoByIDData;
import studio.opencloud.easytour21.internet.datas.GuideOrderData;
import studio.opencloud.easytour21.internet.datas.UserInformationData;
import studio.opencloud.easytour21.internet.interfaces.guide.GetUserInforByID_Interface;
import studio.opencloud.easytour21.internet.translations.GuideGetUserInfoByID_Translation;

public class GuideFinishedOrder extends AppCompatActivity {
    private GuideOrderData selectOrder;
    private UserInformationData userData;
    private EditText etOrderID;
    private EditText etUserName;
    private EditText etPhone;
    private EditText etDestination;
    private EditText etPeopleNum;
    private EditText etDescription;
    private EditText etDate;
    private TextView tvWithDraw;
    private Button btnUserDetail;
    private GuideGetUserInfoByIDData guideData;
private GetUserInforByID_Interface getUserInforByID_interface;
private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_finished_order);
        Intent intent =getIntent();
        selectOrder = intent.getParcelableExtra("selectOrder");
        userData = intent.getParcelableExtra("userData");
        init();

    }
    private void init() {
        etUserName = findViewById(R.id.et_guide_finished_order_user_name);
        etOrderID = findViewById(R.id.et_guide_finished_order_order_ID);
        etDestination = findViewById(R.id.et_guide_finished_order_destination);
        etPeopleNum = findViewById(R.id.et_guide_finished_order_order_people);
        etDescription = findViewById(R.id.et_guide_finished_order_remark);
        etDate = findViewById(R.id.et_guide_finished_order_start_time);
        btnUserDetail = findViewById(R.id.btn_guide_finished_order_user_information);

        tvWithDraw = findViewById(R.id.tv_guide_finished_order_withdraw);
        tvWithDraw.setOnClickListener(MyListener);
        getUserInforByID_interface = new GetUserInforByID_Interface() {
            @Override
            public Call<GuideGetUserInfoByID_Translation> getUserInforByID_Interface(int orderID) {
                return null;
            }

            @Override
            public void processData(GuideGetUserInfoByIDData uid) {
                btnUserDetail.setOnClickListener(MyListener);
                intent = new Intent(GuideFinishedOrder.this,HistoryUserInfor.class);
                intent.putExtra("guideInfo", uid);
            }
        };
        setData();
    }
    private void setData() {
        etOrderID.setText(String.valueOf(selectOrder.getOrderID()));
        etDestination.setText(selectOrder.getPlace());
        etPeopleNum.setText(String.valueOf(selectOrder.getNumberOfPeople()));
        etDescription.setText(selectOrder.getNote());
        etDate.setText(selectOrder.getDate());
        etUserName.setText(selectOrder.getUserNickname());
        getHistoryUserInfo();
    }

    private void getHistoryUserInfo() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        // 步骤5:创建 网络请求接口 的实例
        GetUserInforByID_Interface request = retrofit.create(GetUserInforByID_Interface.class);

        Call<GuideGetUserInfoByID_Translation> call = request.getUserInforByID_Interface(selectOrder.getOrderID());

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<GuideGetUserInfoByID_Translation>() {
                         //请求成功时回调
                         @Override
                         public void onResponse(Call<GuideGetUserInfoByID_Translation> call, Response<GuideGetUserInfoByID_Translation> response) {
                             // 步骤7：处理返回的数据结果：输出翻译的内容
                             //                Toast.makeText(OrderList.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                             System.out.println("********************************************************");
                             guideData = response.body().getData();
                             if (response.body().getCode() != 0)
                                 getUserInforByID_interface.processData(guideData);
                             else {

                                 System.out.println("********************************************************");
                                 System.out.println(response.body().getMessage());
                                 System.out.println(response.body().getCode());
                             }
                         }

                         //请求失败时回调
                         @Override
                         public void onFailure(Call<GuideGetUserInfoByID_Translation> call, Throwable throwable) {
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
            switch (view.getId()){
                case R.id.btn_guide_finished_order_user_information:
                    userDetail();
                    break;
                case R.id.tv_user_accepted_order_withdraw:
                    finish();
                    break;
            }

        }
    };

    private void userDetail() {
        startActivity(intent);
    }
}
