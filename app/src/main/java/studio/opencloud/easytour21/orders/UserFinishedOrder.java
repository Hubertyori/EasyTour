package studio.opencloud.easytour21.orders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opclound.easytour.R;
import studio.opencloud.easytour21.internet.datas.EvaluateGuideInfomationData;
import studio.opencloud.easytour21.internet.datas.UserInformationData;
import studio.opencloud.easytour21.internet.datas.UserOrderData;
import studio.opencloud.easytour21.internet.interfaces.user.GetGuideInforByID_Interface;
import studio.opencloud.easytour21.internet.interfaces.user.GuideEvaluateInformation_Update_Interface;
import studio.opencloud.easytour21.internet.translations.Login_Translation;

public class UserFinishedOrder extends AppCompatActivity {
    private UserOrderData selectOrder;
    private UserInformationData userData;
    private EditText etOrderID;
    private EditText etDestination;
    private EditText etPeopleNum;
    private EditText etGuideName;
    private EditText etDescription;
    private EditText etDate;
    private TextView tvWithDraw;
    private Button btnGuideDetail;
    private int orderID;
    private int star;


    private Intent intent;
    private List<EvaluateGuideInfomationData> guideDetais;
    private List<Map<String, Object>> profilesList = new ArrayList<>();
    private GuideEvaluateInformation_Update_Interface updateGuideInfor_Interface;

    private UserInformationData guideData;
    private GetGuideInforByID_Interface getGuideInforByID_interface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //用户查看已结束订单详情
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_finished_order);
        Intent intent = getIntent();
        selectOrder = intent.getParcelableExtra("selectOrder");
        userData = intent.getParcelableExtra("userData");
        init();

    }

    private void init() {
        etOrderID = findViewById(R.id.et_user_finished_order_order_ID);
        etGuideName = findViewById(R.id.et_user_finished_order_guide_name);
        etDestination = findViewById(R.id.et_user_finished_order_destination);
        etPeopleNum = findViewById(R.id.et_user_finished_order_order_people);
        etDescription = findViewById(R.id.et_user_finished_order_remark);
        etDate = findViewById(R.id.et_user_finished_order_start_time);
        btnGuideDetail = findViewById(R.id.btn_user_finished_order_guide_information);

        tvWithDraw = findViewById(R.id.tv_user_finished_order_withdraw);
        tvWithDraw.setOnClickListener(MyListener);
        getGuideInforByID_interface = new GetGuideInforByID_Interface(){

            @Override
            public Call<Login_Translation> getGuideInforByID(int orderID) {
                return null;
            }

            @Override
            public void processData(UserInformationData uid) {
                btnGuideDetail.setOnClickListener(MyListener);
                intent = new Intent(UserFinishedOrder.this,HistoryGuideInfor.class);
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
        etGuideName.setText(selectOrder.getUserNickname());
        getHistoryGuideInfo();
    }

    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_user_finished_order_guide_information:
                    guideDetails();//查看导游详情
                    break;
                case R.id.tv_user_finished_order_withdraw:
                    finish();
                    break;
            }

        }
    };

    private void guideDetails() {
        //todo 填写导游详情制作页面表格

        startActivity(intent);
    }

    private void getHistoryGuideInfo() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        // 步骤5:创建 网络请求接口 的实例
        GetGuideInforByID_Interface request = retrofit.create(GetGuideInforByID_Interface.class);

        Call<Login_Translation> call = request.getGuideInforByID(selectOrder.getOrderID());

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Login_Translation>() {
                         //请求成功时回调
                         @Override
                         public void onResponse(Call<Login_Translation> call, Response<Login_Translation> response) {
                             // 步骤7：处理返回的数据结果：输出翻译的内容
//                Toast.makeText(OrderList.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                             System.out.println("********************************************************");
                             guideData = response.body().getData();
                             if (response.body().getCode() != 0)
                                 getGuideInforByID_interface.processData(guideData);
                             else {

                                 System.out.println("********************************************************");
                                 System.out.println(response.body().getMessage());
                                 System.out.println(response.body().getCode());
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

}
