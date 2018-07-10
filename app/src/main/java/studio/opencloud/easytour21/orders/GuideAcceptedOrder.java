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
import studio.opencloud.easytour21.internet.datas.GuideOrderData;
import studio.opencloud.easytour21.internet.datas.UserInformationData;
import studio.opencloud.easytour21.internet.datas.UserOrderData;
import studio.opencloud.easytour21.internet.interfaces.CancelOrder_Interface;
import studio.opencloud.easytour21.internet.translations.Register_Translation;

public class GuideAcceptedOrder extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_accepted_order);
        Intent intent =getIntent();
        selectOrder = intent.getParcelableExtra("selectOrder");
        userData = intent.getParcelableExtra("userData");
        init();

    }
    private void init() {
        etUserName = findViewById(R.id.et_guide_accepted_order_user_name);
        etPhone = findViewById(R.id.et_guide_accepted_order_user_tel);
        etOrderID = findViewById(R.id.et_guide_accepted_order_ID);
        etDestination = findViewById(R.id.et_guide_accepted_order_destination);
        etPeopleNum = findViewById(R.id.et_guide_accepted_order_people_number);
        etDescription = findViewById(R.id.et_guide_accepted_order_remark);
        etDate = findViewById(R.id.et_guide_accepted_order_start_time);
        tvWithDraw = findViewById(R.id.tv_guide_accepted_order_withdraw);
        tvWithDraw.setOnClickListener(MyListener);
        setData();
    }
    private void setData() {
        etUserName.setText(selectOrder.getUserNickname());
        etPhone.setText("15523352924");
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
                case R.id.tv_user_accepted_order_withdraw:
                    finish();
                    break;
            }

        }
    };

}
