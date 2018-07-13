package studio.opencloud.easytour21.orders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import studio.opclound.easytour.R;

public class OrderInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_information);
    }
}
