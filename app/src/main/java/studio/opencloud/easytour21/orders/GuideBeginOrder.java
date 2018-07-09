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
import studio.opencloud.easytour21.internet.datas.UserOrderData;
import studio.opencloud.easytour21.internet.interfaces.CancelOrder_Interface;
import studio.opencloud.easytour21.internet.translations.Register_Translation;

public class GuideBeginOrder extends AppCompatActivity {
    private UserOrderData selectOrder;
    private UserInformationData userData;
    private EditText etOrderID;
    private EditText etUserName;
    private EditText etPhone;
    private EditText etDestination;
    private EditText etPeopleNum;
    private EditText etDescription;
    private EditText etDate;
    private TextView tvWithDraw;
    private Button btnTakeOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_begin_order);
        Intent intent =getIntent();
        selectOrder = intent.getParcelableExtra("selectOrder");
        userData = intent.getParcelableExtra("userData");
        init();

    }
    private void init() {
        etOrderID = findViewById(R.id.et_guide_begin_order_order_ID);
        etDestination = findViewById(R.id.et_guide_begin_order_destination);
        etPeopleNum = findViewById(R.id.et_guide_begin_order_people_number);
        etDescription = findViewById(R.id.et_guide_begin_order_remark);
        etDate = findViewById(R.id.et_guide_begin_order_start_time);
        btnTakeOrder = findViewById(R.id.btn_guide_begin_order_take_order);
        btnTakeOrder.setOnClickListener(MyListener);
        tvWithDraw = findViewById(R.id.tv_guide_begin_order_withdraw);
        tvWithDraw.setOnClickListener(MyListener);
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
                case R.id.btn_guide_begin_order_take_order:
                    finishOrder();
                    break;
                case R.id.tv_guide_begin_order_withdraw:
                    finish();
                    break;
            }

        }
    };

    private void finishOrder() {
        //todo 结束订单信息
    }
}
