package studio.opencloud.easytour21.orders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import studio.opclound.easytour.R;
import studio.opencloud.easytour21.internet.datas.GuideGetUserInfoByIDData;

public class HistoryUserInfor extends AppCompatActivity {
    private GuideGetUserInfoByIDData guideInfo;
    private EditText etUserName;
    private EditText etPhone;
    private EditText etDescription;
    private EditText etstar;
    private ImageView ivHeadIC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_user_infor);
        guideInfo = getIntent().getParcelableExtra("guideInfo");
        init();

    }

    private void init() {
        etUserName = findViewById(R.id.et_history_user_user_name);
        etPhone = findViewById(R.id.et_history_user_phone);
        etDescription = findViewById(R.id.et_history_user_description);
        etstar = findViewById(R.id.et_history_user_star);
        ivHeadIC = findViewById(R.id.iv_history_user_headic);

        etUserName.setText(guideInfo.getNickname());
        etPhone.setText(guideInfo.getTelephone());
        etDescription.setText(guideInfo.getIntroduce());
        etstar.setText(String.valueOf(guideInfo.getStar()));

        Picasso.with(HistoryUserInfor.this)
                .load(guideInfo.getHeadphoto())
                .placeholder(R.drawable.default_ic)
                .error(R.drawable.error)
                .into(ivHeadIC);

    }
}

