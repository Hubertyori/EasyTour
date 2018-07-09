package studio.opencloud.easytour21.orders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import studio.opclound.easytour.R;

import studio.opencloud.easytour21.View.RefreshableView;
import studio.opencloud.easytour21.adapters.QueryArrayAdapter;
import studio.opencloud.easytour21.internet.datas.GuideOrderData;
import studio.opencloud.easytour21.internet.datas.UserInformationData;
import studio.opencloud.easytour21.internet.datas.UserOrderData;
import studio.opencloud.easytour21.internet.interfaces.UpdateUI_Interface;

public class OrderList extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private RefreshableView guideMainRefreshableView;
    private Button btnIdleOrder;
    private Button btnAcceptOrder;
    private Button btnBeginOrder;
    private Button btnFinishedOrder;
    private ListView listView;
    private String phone;

    private Intent intent;
    private UserInformationData userData;
    private Order order;

    private int charactor;//用户角色
    private int status;//用户订单状态
    //角色
    private static final int USER = 0;
    private static final int GUIDE = 1;
    //状态
    private static final int IDLE = 0;
    private static final int ACCEPTED = 1;
    private static final int BEGIN = 2;
    private static final int FINISHED = 3;

    private List<UserOrderData> lUserOrderData;
    private List<GuideOrderData> lGuideOrderData;
    private UpdateUI_Interface updateUI_interface;
    //列表数据
    private List<Integer> idleOrders;//存放列表信息数据
    private List<Integer> acceptOrders;
    private List<Integer> beginOrders;
    private List<Integer> finishedOrders;
    private List<UserOrderData> userOrderList;
    private List<GuideOrderData> guideOrderList;
    private List<SelfOrder> list;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        init();
    }


    //添加数据信息
    public void idle() {
        status = IDLE;
        btnIdleOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonBlue));
        btnBeginOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btnAcceptOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btnFinishedOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        getIdleOrders();
    }

    public void accept() {
        status = ACCEPTED;
        btnAcceptOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonBlue));
        btnBeginOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btnIdleOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btnFinishedOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        getAcceptOrders();
    }

    public void begin() {
        status = BEGIN;
        btnBeginOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonBlue));
        btnIdleOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btnAcceptOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btnFinishedOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        getBeginOrders();
    }

    public void finished() {
        status = FINISHED;
        btnFinishedOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonBlue));
        btnBeginOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btnAcceptOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btnIdleOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        getFinishedOrders();
    }

    //初始化
    private void init() {
        //初始化列表数据
        userOrderList = new ArrayList<>();
        guideOrderList = new ArrayList<>();
        finishedOrders = new ArrayList<>();
        acceptOrders = new ArrayList<>();
        idleOrders = new ArrayList<>();
        beginOrders = new ArrayList<>();
        list = new ArrayList<SelfOrder>();

        status = 0;//初始状态为IDLE

        intent = getIntent();
        userData = intent.getParcelableExtra("userData");//获取用户个人信息

        btnIdleOrder = findViewById(R.id.btn_order_list_idle_order);
        btnAcceptOrder = findViewById(R.id.btn_order_list_accepted_order);
        btnBeginOrder = findViewById(R.id.btn_order_list_begin_order);
        btnFinishedOrder = findViewById(R.id.btn_order_list_finished_order);

        btnIdleOrder.setOnClickListener(MyListener);
        btnAcceptOrder.setOnClickListener(MyListener);
        btnBeginOrder.setOnClickListener(MyListener);
        btnFinishedOrder.setOnClickListener(MyListener);

        phone = userData.getTelephone();
        //用户角色检查
        if (userData.getIsguide().equals("yes")) {
            charactor = GUIDE;
        } else {
            charactor = USER;
        }

        guideMainRefreshableView = findViewById(R.id.rv_order_list);
        listView = findViewById(R.id.lv_order_list);
        listView.setOnItemClickListener(this);
        guideMainRefreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    ListViewDataUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                guideMainRefreshableView.finishRefreshing();
            }
        }, 0);

//界面更新函数接口
        updateUI_interface = new UpdateUI_Interface() {
            //更新游客订单界面
            @Override
            public void updateIdleUserOrderUI(List<UserOrderData> uod) {
                for (int i = 0; i < uod.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。{
                    list.add(new SelfOrder(R.drawable.logotemp, uod.get(i).getPlace().split(" ")[1], uod.get(i).getNote(), uod.get(i).getDate(), uod.get(i).getNumberOfPeople()));
                    idleOrders.add(i);
                    userOrderList.add(uod.get(i));
                }
                QueryArrayAdapter adapter = new QueryArrayAdapter(OrderList.this, R.layout.order_item_idle, list);
                listView.setAdapter(adapter);
//                guideMainRefreshableView.listView.setOnItemClickListener(OrderList.this);
            }


            //更新导游订单界面
            @Override
            public void updateIdleGuideOrderUI(List<GuideOrderData> uod) {
                for (int i = 0; i < uod.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。{
                    list.add(new SelfOrder(R.drawable.logotemp, uod.get(i).getPlace().split(" ")[1], uod.get(i).getNote(), uod.get(i).getDate(), uod.get(i).getNumberOfPeople()));
                    idleOrders.add(i);
                    guideOrderList.add(uod.get(i));
                }
                QueryArrayAdapter adapter = new QueryArrayAdapter(OrderList.this, R.layout.order_item_idle, list);
                listView.setAdapter(adapter);
//                guideMainRefreshableView.listView.setOnItemClickListener(OrderList.this);
            }

            //用户查看接收订单
            @Override
            public void updateAcceptedUserOrderUI(List<UserOrderData> uod) {
                for (int i = 0; i < uod.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。{
                    list.add(new SelfOrder(R.drawable.logotemp, uod.get(i).getPlace().split(" ")[1], uod.get(i).getNote(), uod.get(i).getDate(), uod.get(i).getNumberOfPeople()));
                    acceptOrders.add(i);
                    userOrderList.add(uod.get(i));
                }
                QueryArrayAdapter adapter = new QueryArrayAdapter(OrderList.this, R.layout.order_item_accept, list);
                listView.setAdapter(adapter);
//                listView.setOnItemClickListener(OrderList.this);
            }

            //导游查看接收订单
            @Override
            public void updateAcceptedGuideOrderUI(List<GuideOrderData> uod) {
                for (int i = 0; i < uod.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。{
                    list.add(new SelfOrder(R.drawable.logotemp, uod.get(i).getPlace().split(" ")[1], uod.get(i).getNote(), uod.get(i).getDate(), uod.get(i).getNumberOfPeople()));
                    acceptOrders.add(i);
                    guideOrderList.add(uod.get(i));
                }
                QueryArrayAdapter adapter = new QueryArrayAdapter(OrderList.this, R.layout.order_item_accept, list);
                listView.setAdapter(adapter);
//                listView.setOnItemClickListener(OrderList.this);
            }

            //用户查看开始订单
            @Override
            public void updateBeginUserOrderUI(List<UserOrderData> uod) {
                for (int i = 0; i < uod.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。{
                    list.add(new SelfOrder(R.drawable.logotemp, uod.get(i).getPlace().split(" ")[1], uod.get(i).getNote(), uod.get(i).getDate(), uod.get(i).getNumberOfPeople()));
                    beginOrders.add(i);
                    userOrderList.add(uod.get(i));
                }
                QueryArrayAdapter adapter = new QueryArrayAdapter(OrderList.this, R.layout.order_item_begin, list);
                listView.setAdapter(adapter);
//                listView.setOnItemClickListener(OrderList.this);
            }

            //导游查看开始订单
            @Override
            public void updateBeginGuideOrderUI(List<GuideOrderData> uod) {
                for (int i = 0; i < uod.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。{
                    list.add(new SelfOrder(R.drawable.logotemp, uod.get(i).getPlace().split(" ")[1], uod.get(i).getNote(), uod.get(i).getDate(), uod.get(i).getNumberOfPeople()));
                    beginOrders.add(i);
                    guideOrderList.add(uod.get(i));
                }
                QueryArrayAdapter adapter = new QueryArrayAdapter(OrderList.this, R.layout.order_item_begin, list);
                listView.setAdapter(adapter);
//                listView.setOnItemClickListener(OrderList.this);
            }

            //用户查看结束订单
            @Override
            public void updateFinishedUserOrderUI(List<UserOrderData> uod) {
                for (int i = 0; i < uod.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。{
                    list.add(new SelfOrder(R.drawable.logotemp, uod.get(i).getPlace().split(" ")[1], uod.get(i).getNote(), uod.get(i).getDate(), uod.get(i).getNumberOfPeople()));
                    finishedOrders.add(i);
                    userOrderList.add(uod.get(i));
                }
                QueryArrayAdapter adapter = new QueryArrayAdapter(OrderList.this, R.layout.order_item_begin, list);
                listView.setAdapter(adapter);
//                listView.setOnItemClickListener(OrderList.this);
            }

            //导游查看结束订单
            @Override
            public void updateFinishedGuideOrderUI(List<GuideOrderData> uod) {
                for (int i = 0; i < uod.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。{
                    list.add(new SelfOrder(R.drawable.logotemp, uod.get(i).getPlace().split(" ")[1], uod.get(i).getNote(), uod.get(i).getDate(), uod.get(i).getNumberOfPeople()));
                    finishedOrders.add(i);
                    guideOrderList.add(uod.get(i));
                }
                QueryArrayAdapter adapter = new QueryArrayAdapter(OrderList.this, R.layout.order_item_begin, list);
                listView.setAdapter(adapter);
//                listView.setOnItemClickListener(OrderList.this);
            }

        };
        idle();//获取闲置订单数据
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

        //更新列表数据
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this, String.valueOf(position),Toast.LENGTH_LONG).show();
        if (charactor == USER) {
            if (status == IDLE) {
                Intent intent = new Intent(OrderList.this, UserIdleOrder.class);
                //存放选中的订单数据，供后面查询使用
                UserOrderData selectOrder = userOrderList.get(position);
                intent.putExtra("selectOrder",selectOrder);
                intent.putExtra("userData",userData);
                startActivity(intent);
            } else if (status == ACCEPTED) {
                Intent intent = new Intent(OrderList.this, UserAcceptedOrder.class);
                //存放选中的订单数据，供后面查询使用
                UserOrderData selectOrder = userOrderList.get(position);
                intent.putExtra("selectOrder",selectOrder);
                intent.putExtra("userData",userData);
                startActivity(intent);
            } else if (status == BEGIN) {
                Intent intent = new Intent(OrderList.this, UserBeginOrder.class);
                //存放选中的订单数据，供后面查询使用
                UserOrderData selectOrder = userOrderList.get(position);
                intent.putExtra("selectOrder",selectOrder);
                intent.putExtra("userData",userData);
                startActivity(intent);
            } else if (status == FINISHED) {
                Intent intent = new Intent(OrderList.this, UserFinishedOrder.class);
                //存放选中的订单数据，供后面查询使用
                UserOrderData selectOrder = userOrderList.get(position);
                intent.putExtra("selectOrder",selectOrder);
                intent.putExtra("userData",userData);
                startActivity(intent);
            }
        } else {
            if (status == IDLE) {
                Intent intent = new Intent(OrderList.this, GuideIdleOrder.class);
                GuideOrderData selectOrder = guideOrderList.get(idleOrders.get(position));
                intent.putExtra("selectOrder",selectOrder);
                intent.putExtra("userData",userData);
                startActivity(intent);
            } else if (status == ACCEPTED) {
                Intent intent = new Intent(OrderList.this, GuideAcceptedOrder.class);
                //存放选中的订单数据，供后面查询使用
                UserOrderData selectOrder = userOrderList.get(position);
                intent.putExtra("selectOrder",selectOrder);
                intent.putExtra("userData",userData);
                startActivity(intent);
            } else if (status == BEGIN) {
                Intent intent = new Intent(OrderList.this, GuideBeginOrder.class);
                //存放选中的订单数据，供后面查询使用
                UserOrderData selectOrder = userOrderList.get(position);
                intent.putExtra("selectOrder",selectOrder);
                intent.putExtra("userData",userData);
                startActivity(intent);
            } else if (status == FINISHED) {
                Intent intent = new Intent(OrderList.this, GuideFinishedOrder.class);
                //存放选中的订单数据，供后面查询使用
                UserOrderData selectOrder = userOrderList.get(position);
                intent.putExtra("selectOrder",selectOrder);
                intent.putExtra("userData",userData);
                startActivity(intent);
            }
        }
    }
    private void ListViewDataUpdate() {
        userOrderList.clear();
        guideOrderList.clear();
        finishedOrders.clear();
        acceptOrders.clear();
        idleOrders.clear();
        beginOrders.clear();
        list.clear();
        refreshList();
    }

    private void refreshList() {
        if (status == IDLE) {
            idle();
        }
        if (status == ACCEPTED) {
            accept();
        }
        if (status == BEGIN) {
            begin();
        }
        if (status == FINISHED) {
            finished();
        }
    }


    private Runnable run_refresh_listview = new Runnable() {
        @Override
        public void run() {
            if (status == IDLE) {
                idle();
            }
            if (status == ACCEPTED) {
                accept();
            }
            if (status == BEGIN) {
                begin();
            }
            if (status == FINISHED) {
                finished();
            }
        }
    };
    private View.OnClickListener MyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_order_list_idle_order:
                    idle();
                    break;
                case R.id.btn_order_list_accepted_order:
                    accept();
                    break;
                case R.id.btn_order_list_begin_order:
                    begin();
                    break;
                case R.id.btn_order_list_finished_order:
                    finished();
                    break;
            }
        }
    };

    private void getFinishedOrders() {
        if (charactor == GUIDE) {
            order = new Order(charactor, userData.getGuideIDnumbr(), updateUI_interface);
            order.getFinishedOrders();
        } else {
            order = new Order(charactor, userData.getTelephone(), updateUI_interface);
            order.getFinishedOrders();
        }
    }

    private void getBeginOrders() {
        if (charactor == GUIDE) {
            order = new Order(charactor, userData.getGuideIDnumbr(), updateUI_interface);
            order.getBeginOrders();
        } else {
            order = new Order(charactor, userData.getTelephone(), updateUI_interface);
            order.getBeginOrders();
        }
    }

    private void getAcceptOrders() {
        if (charactor == GUIDE) {
            order = new Order(charactor, userData.getGuideIDnumbr(), updateUI_interface);
            order.getAcceptOrders();
        } else {
            order = new Order(charactor, userData.getTelephone(), updateUI_interface);
            order.getAcceptOrders();
        }
    }

    private void getIdleOrders() {
        if (charactor == GUIDE) {
            order = new Order(charactor, userData.getGuideIDnumbr(), updateUI_interface);
            order.getIdleOrders();
        } else {
            order = new Order(charactor, userData.getTelephone(), updateUI_interface);
            order.getIdleOrders();
        }
    }
}
