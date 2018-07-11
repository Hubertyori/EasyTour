package studio.opencloud.easytour21.orders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import studio.opclound.easytour.R;
import studio.opencloud.easytour21.evalution.GuideEvaluate;
import studio.opencloud.easytour21.internet.datas.UserInformationData;

public class HistoryGuideInfor extends AppCompatActivity {

    private UserInformationData guideInfo;
    private EditText etUserName;
    private EditText etCity;
    private EditText etPhone;
    private EditText etDescription;
    private EditText etstar;
    private ImageView ivHeadIC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_guide_infor);
        guideInfo = getIntent().getParcelableExtra("guideInfo");
        init();

    }

    private void init() {
        etUserName = findViewById(R.id.et_history_guide_user_name);
        etCity = findViewById(R.id.et_history_guide_city);
        etPhone = findViewById(R.id.et_history_guide_phone);
        etDescription = findViewById(R.id.et_history_guide_description);
        etstar = findViewById(R.id.et_history_guide_star);
        ivHeadIC = findViewById(R.id.iv_history_guide_head_ic);

        etUserName.setText(guideInfo.getNickname());
        etCity.setText(guideInfo.getGuideservercity());
        etPhone.setText(guideInfo.getTelephone());
        etDescription.setText(guideInfo.getIntroduce());
        etstar.setText(String.valueOf(guideInfo.getStar()));

        Picasso.with(HistoryGuideInfor.this)
                .load(guideInfo.getHeadphoto())
                .placeholder(R.drawable.default_ic)
                .error(R.drawable.error)
                .into(ivHeadIC);

    }
}
