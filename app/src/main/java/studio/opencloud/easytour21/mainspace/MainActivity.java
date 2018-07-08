package studio.opencloud.easytour21.mainspace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import studio.opclound.easytour.R;
import studio.opencloud.easytour21.internet.datas.UserInformationData;
import studio.opencloud.easytour21.orders.OrderList;
import studio.opencloud.easytour21.orders.SendOrder;
import studio.opencloud.easytour21.users.UserInformation;

public class MainActivity extends Activity {

    private Button btnOrderList;
    private Button btnInformation;
    private Button btnSendOrder;
    private Intent intent;
    private UserInformationData userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        intent = getIntent();
        userData = intent.getParcelableExtra("userData");
    }

    private void init() {
        btnOrderList = findViewById(R.id.btn_main_order_list);
        btnInformation = findViewById(R.id.btn_main_information);
        btnSendOrder = findViewById(R.id.btn_main_send_order);
        btnOrderList.setOnClickListener(MyListener);
        btnInformation.setOnClickListener(MyListener);
        btnSendOrder.setOnClickListener(MyListener);

    }
    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_main_order_list:
                    goToOrderList();
                    break;
                case R.id.btn_main_information:
                    goToInformation();
                    break;
                case R.id.btn_main_send_order:
                    sendOrder();
                    break;
            }
        }
    };

    private void sendOrder() {
        intent = new Intent(MainActivity.this, SendOrder.class);
        intent.putExtra("userData",userData);
        startActivity(intent);
    }

    private void goToInformation() {
        intent = new Intent(MainActivity.this, UserInformation.class);
        intent.putExtra("userData",userData);
        startActivity(intent);
    }

    private void goToOrderList() {
        intent = new Intent(MainActivity.this, OrderList.class);
        intent.putExtra("userData",userData);
        startActivity(intent);
    }
}
