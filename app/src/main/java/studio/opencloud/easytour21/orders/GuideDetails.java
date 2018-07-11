package studio.opencloud.easytour21.orders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import studio.opclound.easytour.R;
import studio.opencloud.easytour21.guide_choose.EuclidActivity;
import studio.opencloud.easytour21.guide_choose.EuclidListAdapter;
import studio.opencloud.easytour21.internet.datas.GuideInfomation;

public class GuideDetails extends EuclidActivity {
//private List<Gui>
    private int orderID;
    private Intent intent;
    private List<GuideInfomation> guideDetais;
    private List<Map<String, Object>> profilesList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GuideDetails.this, "Oh hi!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected BaseAdapter getAdapter() {
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        profilesList = (List<Map<String, Object>>)bundle.getSerializable("profilesList");
        return new EuclidListAdapter(this, R.layout.list_item, profilesList);
    }
}
