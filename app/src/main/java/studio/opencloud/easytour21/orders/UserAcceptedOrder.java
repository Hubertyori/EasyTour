package studio.opencloud.easytour21.orders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opclound.easytour.R;
import studio.opencloud.easytour21.guide_choose.EuclidListAdapter;
import studio.opencloud.easytour21.internet.datas.EvaluateGuideInfomationData;
import studio.opencloud.easytour21.internet.datas.GuideInfomation;
import studio.opencloud.easytour21.internet.datas.UserInformationData;
import studio.opencloud.easytour21.internet.datas.UserOrderData;
import studio.opencloud.easytour21.internet.interfaces.user.UserGetGuideInfo_Interface;
import studio.opencloud.easytour21.internet.interfaces.user.GuideEvaluateInformation_Update_Interface;
import studio.opencloud.easytour21.internet.translations.Guide_Information_Translation;
import studio.opencloud.easytour21.internet.translations.UserGetGuideInfo_Translation;

public class UserAcceptedOrder extends AppCompatActivity {
    private UserOrderData selectOrder;
    private UserInformationData userData;
    private EditText etOrderID;
    private EditText etDestination;
    private EditText etPeopleNum;
    private EditText etDescription;
    private EditText etDate;
    private TextView tvWithDraw;
    private Button btnGuideDetail;

    private int orderID;
    private Intent intent;
    private List<EvaluateGuideInfomationData> guideDetais;
    private List<Map<String, Object>> profilesList = new ArrayList<>();
    private GuideEvaluateInformation_Update_Interface updateGuideInfor_Interface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_accepted_order);
        Intent intent =getIntent();
        selectOrder = intent.getParcelableExtra("selectOrder");
        userData = intent.getParcelableExtra("userData");
        init();

    }
    private void init() {
        etOrderID = findViewById(R.id.et_user_accepted_order_ID);
        etDestination = findViewById(R.id.et_user_accepted_order_description);
        etPeopleNum = findViewById(R.id.et_user_accepted_order_people);
        etDescription = findViewById(R.id.et_user_accepted_order_destination);
        etDate = findViewById(R.id.et_user_accepted_order_start_time);
        btnGuideDetail = findViewById(R.id.btn_user_accepted_order_guide_detail);
        btnGuideDetail.setOnClickListener(MyListener);
        tvWithDraw = findViewById(R.id.tv_user_accepted_order_withdraw);
        tvWithDraw.setOnClickListener(MyListener);
        setData();
    }
    private void setData() {
        etOrderID.setText(String.valueOf(selectOrder.getOrderID()));
        etDestination.setText(selectOrder.getPlace());
        etPeopleNum.setText(String.valueOf(selectOrder.getNumberOfPeople()));
        etDescription.setText(selectOrder.getNote());
        etDate.setText(selectOrder.getDate());
    }

    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_user_accepted_order_guide_detail:
                    guideDetail();
                    break;
                case R.id.tv_user_accepted_order_withdraw:
                    finish();
                    break;
            }

        }
    };
    //收集导游列表信息
    private void getGuideInfo() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        // 步骤5:创建 网络请求接口 的实例
        UserGetGuideInfo_Interface request = retrofit.create(UserGetGuideInfo_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)

        Call<UserGetGuideInfo_Translation> call = request.signInInformation(orderID);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<UserGetGuideInfo_Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<UserGetGuideInfo_Translation> call, Response<UserGetGuideInfo_Translation> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容

                if(response.body().getCode() != 0){
                    guideDetais = response.body().getData();
                    updateGuideInfor_Interface.updateIdleUserOrderUI(guideDetais);
                }
                else
                {
                    System.out.println("********************************************************");
                    System.out.println(response.body().getMessage());
                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<UserGetGuideInfo_Translation> call, Throwable throwable) {
                System.out.println("请求失败*****************************************");
                System.out.println(throwable.getMessage());
                Toast.makeText(UserAcceptedOrder.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void guideDetail() {
  //todo 填写导游详情制作页面表格
        orderID = Integer.parseInt(etOrderID.getText().toString());
        updateGuideInfor_Interface= new GuideEvaluateInformation_Update_Interface() {
            @Override
            public void updateIdleUserOrderUI(List<EvaluateGuideInfomationData> guideInfo) {
                Map<String, Object> profileMap;
                for (int i = 0; i < guideDetais.size(); i++) {
                    profileMap = new HashMap<>();
                    profileMap.put(EuclidListAdapter.KEY_AVATAR,guideDetais.get(i).getHeadphoto());
                    profileMap.put(EuclidListAdapter.KEY_NAME, guideDetais.get(i).getRealname());
                    profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, guideDetais.get(i).getGuideIntro());
                    profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, guideDetais.get(i).getGuideIntro());
                    profileMap.put(EuclidListAdapter.KEY_GUIDE_STAR_POINT, guideDetais.get(i).getStar());
                    profileMap.put(EuclidListAdapter.KEY_GUIDE_NUMBER, guideDetais.get(i).getGuideNumber());
//                    profileMap.put(EuclidListAdapter.KEY_GUIDE_NUMBER, guideDetais.get(i).get());//获取导游电话
                    profileMap.put(EuclidListAdapter.KEY_ORDER_ID, orderID);
                    profilesList.add(profileMap);
                }
                Intent intent = new Intent(UserAcceptedOrder.this,GuideDetails.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("profilesList", (Serializable) profilesList);
                intent.putExtras(bundle);
//                intent.putExtra("profilesList",profilesList);
                startActivity(intent);
            }
        };
        getGuideInfo();
    }
}
