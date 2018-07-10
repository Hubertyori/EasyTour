package studio.opencloud.easytour21.mainspace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import studio.opclound.easytour.R;
import studio.opencloud.easytour21.internet.datas.UserInformationData;
import studio.opencloud.easytour21.orders.OrderActivity;
import studio.opencloud.easytour21.orders.OrderList;
import studio.opencloud.easytour21.orders.SendOrder;
import studio.opencloud.easytour21.users.Login;
import studio.opencloud.easytour21.users.UserInformation;

public class MainActivity_X extends Activity implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private Intent intent;
    private NavigationView navigationView;
    private UserInformationData userData;

    ImageView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_x);

        intent = getIntent();

        userData = intent.getParcelableExtra("userData");

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_na);
        navigationView = (NavigationView) findViewById(R.id.nav);
        menu= (ImageView) findViewById(R.id.main_menu);
        View headerView = navigationView.getHeaderView(0);//获取头布局
        menu.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //item.setChecked(true);
              /** Toast.makeText(MainActivity_X.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();*/
                drawerLayout.closeDrawer(navigationView);
                switch (item.getItemId()) {
                    case R.id.send: {
                        intent = new Intent(MainActivity_X.this, SendOrder.class);
                        intent.putExtra("userData", userData);
                        startActivity(intent);
                        break;

                    }
                    case R.id.info: {
                        intent = new Intent(MainActivity_X.this, UserInformation.class);
                        intent.putExtra("userData", userData);
                        startActivity(intent);
                        break;
                    }
                    case R.id.order:{
                        intent = new Intent(MainActivity_X.this, OrderList.class);
                        intent.putExtra("userData", userData);
                        startActivity(intent);

                        break;

                    }


                    case R.id.out: {
                        intent = new Intent(MainActivity_X.this, Login.class);
                        startActivity(intent);
                        MainActivity_X.this.finish();
                        break;

                    }

                }

                return true;
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_menu://点击菜单，跳出侧滑菜单
                if (drawerLayout.isDrawerOpen(navigationView)){
                    drawerLayout.closeDrawer(navigationView);
                }else{
                    drawerLayout.openDrawer(navigationView);
                }
                break;
        }
    }

}