package studio.opencloud.easytour21.evalution;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opclound.easytour.R;
import studio.opencloud.easytour21.internet.datas.EvaluateGuideInfomationData;
import studio.opencloud.easytour21.internet.datas.UserBeginOrderData;
import studio.opencloud.easytour21.internet.datas.UserInformationData;
import studio.opencloud.easytour21.internet.interfaces.pbinterface.FinisheOrder_Interface;
import studio.opencloud.easytour21.internet.interfaces.user.GuideEvaluateInformation_Update_Interface;
import studio.opencloud.easytour21.internet.translations.Register_Translation;

public class UserEvaluate extends AppCompatActivity {
    //用户评价导游
    private TextView tvWithDraw;
    private ImageView ivHeadIC;
    private RatingBar rbStar;
    private Button btnEvaluate;
    private GuideEvaluateInformation_Update_Interface updateGuideInfor_Interface;
    private int orderID;
    private int star;

    private Intent intent;
    private UserBeginOrderData selectOrder;
    private List<EvaluateGuideInfomationData> guideDetais;
    private UserInformationData guideData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_evaluate);
        intent = getIntent();
        selectOrder = intent.getParcelableExtra("selectOrder");
        guideData = intent.getParcelableExtra("userInformationData");
        init();
    }

    private void init() {
        orderID = selectOrder.getOrderID();
        tvWithDraw = findViewById(R.id.tv_user_evaluate_withdraw);
        ivHeadIC = findViewById(R.id.iv_user_evaluate_guide_headic);
        rbStar = findViewById(R.id.rb_user_evaluate_star);
        btnEvaluate = findViewById(R.id.btn_user_evaluate_finish);
        btnEvaluate.setOnClickListener(MyListener);
        rbStar.setOnRatingBarChangeListener(MyRatingBarChangeListener);
        tvWithDraw.setOnClickListener(MyListener);
        Picasso.with(UserEvaluate.this)
                .load(guideData.getHeadphoto())
                .placeholder(R.drawable.default_ic)
                .error(R.drawable.error)
                .into(ivHeadIC);
    }

    private RatingBar.OnRatingBarChangeListener MyRatingBarChangeListener = new RatingBar.OnRatingBarChangeListener() {

        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            rbStar.setRating(v);
        }
    };
    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_user_evaluate_finish:
                    evaluateGuide();
                    finish();

                    break;
                case R.id.tv_user_evaluate_withdraw:
                    finish();
                    break;
            }

        }
    };

    private void evaluateGuide() {
        star = (int) rbStar.getRating();
        orderID = selectOrder.getOrderID();
        retrofitBuilder();
    }

    private void retrofitBuilder() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        // 步骤5:创建 网络请求接口 的实例
        FinisheOrder_Interface request = retrofit.create(FinisheOrder_Interface.class);

        Call<Register_Translation> call = request.finishOrder(orderID, star);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Register_Translation>() {
                         //请求成功时回调
                         @Override
                         public void onResponse(Call<Register_Translation> call, Response<Register_Translation> response) {
                             // 步骤7：处理返回的数据结果：输出翻译的内容
//                Toast.makeText(OrderList.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                             System.out.println("********************************************************");
                             if (response.body().getCode() != 0)
                                 Toast.makeText(UserEvaluate.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                             else {
                                 System.out.println("********************************************************");
                                 System.out.println(response.body().getMessage());
                             }
                         }

                         //请求失败时回调
                         @Override
                         public void onFailure(Call<Register_Translation> call, Throwable throwable) {
                             Toast.makeText(UserEvaluate.this, "请求失败", Toast.LENGTH_SHORT).show();
                             System.out.println(throwable.getMessage());
//                Toast.makeText(OrderList.this, "请求失败", Toast.LENGTH_SHORT).show();
                         }
                     }
        );
    }
}
