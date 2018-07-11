package studio.opencloud.easytour21.mainspace;

import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
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
import studio.opencloud.easytour21.users.ToBeGuide;
import studio.opencloud.easytour21.users.UserInformation;

public class MainActivity_X extends Activity implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private Intent intent;
    private NavigationView navigationView;
    private UserInformationData userData;

    ImageView menu;
    private ClipData.Item client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_x);

        intent = getIntent();

        userData = intent.getParcelableExtra("userData");
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_na);
        navigationView = (NavigationView) findViewById(R.id.nav);
        menu = (ImageView) findViewById(R.id.main_menu);
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
                    case R.id.order: {
                        intent = new Intent(MainActivity_X.this, OrderList.class);
                        userData.setIsguide("no");
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
                    case R.id.client: {
                        if (userData.getIsguide() == "yes") {
                            intent = new Intent(MainActivity_X.this, OrderList.class);
                            intent.putExtra("userData", userData);
                            startActivity(intent);
                            break;
                        } else {

                            new AlertDialog.Builder(MainActivity_X.this)
                                    .setTitle("权限不足")
                                    .setMessage("您还不是导游，无法查看客户订单，是否注册成为导游？")
                                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(MainActivity_X.this, ToBeGuide.class);
                                                    intent.putExtra("userPhone", userData.getTelephone());
                                                    startActivity(intent);
                                                }
                                            }
                                    )
                                    .setNegativeButton("否", null)
                                    .show();
                        }

                    }

                }

                return true;
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_menu://点击菜单，跳出侧滑菜单
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawer(navigationView);
                } else {
                    drawerLayout.openDrawer(navigationView);
                }
                break;
        }
    }

}