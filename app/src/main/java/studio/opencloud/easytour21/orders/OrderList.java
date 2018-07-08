package studio.opencloud.easytour21.orders;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

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
    private ListView listView;
    private Button btnIdleOrder;
    private Button btnAcceptOrder;
    private Button btnBeginOrder;
    private Button btnFinishedOrder;
    private ListView order_list;
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
    private List<Map<String, String>> orderResult;

    private Handler handler_refresh_listview;

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

    }

    public void finished() {
        status = FINISHED;
        btnFinishedOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonBlue));
        btnBeginOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btnAcceptOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btnIdleOrder.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));

    }

    //初始化
    private void init() {
        //初始化列表数据
        orderResult = new ArrayList<>();
        finishedOrders = new ArrayList<>();
        acceptOrders = new ArrayList<>();
        idleOrders = new ArrayList<>();
        beginOrders = new ArrayList<>();

        status = 0;//初始状态为IDLE

        intent = getIntent();
        userData = intent.getParcelableExtra("userData");//获取用户个人信息

        order_list = findViewById(R.id.lv_order_list);
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
        listView = (ListView) findViewById(R.id.lv_order_list);
        guideMainRefreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                ListViewDataUpdate();
                guideMainRefreshableView.finishRefreshing();
            }
        }, 0);

//界面更新函数接口
        updateUI_interface = new UpdateUI_Interface() {
            //更新游客订单界面
            @Override
            public void updateIdleUserOrderUI(List<UserOrderData> uod) {
                List<SelfOrder> list = new ArrayList<SelfOrder>();
                for (int i = 0; i < uod.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。{
                    list.add(new SelfOrder(R.drawable.logotemp, uod.get(i).getPlace().split(" ")[1], uod.get(i).getNote(), uod.get(i).getDate(), uod.get(i).getNumberOfPeople()));
                    idleOrders.add(i);
                }
                QueryArrayAdapter adapter = new QueryArrayAdapter(OrderList.this, R.layout.order_item_idle, list);
                order_list.setAdapter(adapter);
                order_list.setOnItemClickListener(OrderList.this);
            }


            //更新导游订单界面
            @Override
            public void updateIdleGuideOrderUI(List<GuideOrderData> uod) {
                List<SelfOrder> list = new ArrayList<SelfOrder>();
                for (int i = 0; i < uod.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。{
                    list.add(new SelfOrder(R.drawable.logotemp, uod.get(i).getPlace().split(" ")[1], uod.get(i).getNote(), uod.get(i).getDate(), uod.get(i).getNumberOfPeople()));
                    idleOrders.add(i);
                }
                QueryArrayAdapter adapter = new QueryArrayAdapter(OrderList.this, R.layout.order_item_idle, list);
                order_list.setAdapter(adapter);
                order_list.setOnItemClickListener(OrderList.this);
            }

            //用户查看接收订单
            @Override
            public void updateAcceptedUserOrderUI(List<UserOrderData> uod) {
                List<SelfOrder> list = new ArrayList<SelfOrder>();
                for (int i = 0; i < uod.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。{
                    list.add(new SelfOrder(R.drawable.logotemp, uod.get(i).getPlace().split(" ")[1], uod.get(i).getNote(), uod.get(i).getDate(), uod.get(i).getNumberOfPeople()));
                    acceptOrders.add(i);
                }
                QueryArrayAdapter adapter = new QueryArrayAdapter(OrderList.this, R.layout.order_item_accept, list);
                order_list.setAdapter(adapter);
                order_list.setOnItemClickListener(OrderList.this);
            }

            //导游查看接收订单
            @Override
            public void updateAcceptedGuideOrderUI(List<GuideOrderData> uod) {
                List<SelfOrder> list = new ArrayList<SelfOrder>();
                for (int i = 0; i < uod.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。{
                    list.add(new SelfOrder(R.drawable.logotemp, uod.get(i).getPlace().split(" ")[1], uod.get(i).getNote(), uod.get(i).getDate(), uod.get(i).getNumberOfPeople()));
                    acceptOrders.add(i);
                }
                QueryArrayAdapter adapter = new QueryArrayAdapter(OrderList.this, R.layout.order_item_accept, list);
                order_list.setAdapter(adapter);
                order_list.setOnItemClickListener(OrderList.this);
            }

            //用户查看开始订单
            @Override
            public void updateBeginUserOrderUI(List<UserOrderData> uod) {
                List<SelfOrder> list = new ArrayList<SelfOrder>();
                for (int i = 0; i < uod.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。{
                    list.add(new SelfOrder(R.drawable.logotemp, uod.get(i).getPlace().split(" ")[1], uod.get(i).getNote(), uod.get(i).getDate(), uod.get(i).getNumberOfPeople()));
                    beginOrders.add(i);
                }
                QueryArrayAdapter adapter = new QueryArrayAdapter(OrderList.this, R.layout.order_item_begin, list);
                order_list.setAdapter(adapter);
                order_list.setOnItemClickListener(OrderList.this);
            }

            //导游查看开始订单
            @Override
            public void updateBeginGuideOrderUI(List<GuideOrderData> uod) {
                List<SelfOrder> list = new ArrayList<SelfOrder>();
                for (int i = 0; i < uod.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。{
                    list.add(new SelfOrder(R.drawable.logotemp, uod.get(i).getPlace().split(" ")[1], uod.get(i).getNote(), uod.get(i).getDate(), uod.get(i).getNumberOfPeople()));
                    beginOrders.add(i);
                }
                QueryArrayAdapter adapter = new QueryArrayAdapter(OrderList.this, R.layout.order_item_begin, list);
                order_list.setAdapter(adapter);
                order_list.setOnItemClickListener(OrderList.this);
            }

            //用户查看结束订单
            @Override
            public void updateFinishedUserOrderUI(List<UserOrderData> uod) {
                List<SelfOrder> list = new ArrayList<SelfOrder>();
                for (int i = 0; i < uod.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。{
                    list.add(new SelfOrder(R.drawable.logotemp, uod.get(i).getPlace().split(" ")[1], uod.get(i).getNote(), uod.get(i).getDate(), uod.get(i).getNumberOfPeople()));
                    finishedOrders.add(i);
                }
                QueryArrayAdapter adapter = new QueryArrayAdapter(OrderList.this, R.layout.order_item_begin, list);
                order_list.setAdapter(adapter);
                order_list.setOnItemClickListener(OrderList.this);
            }

            //导游查看结束订单
            @Override
            public void updateFinishedGuideOrderUI(List<GuideOrderData> uod) {
                List<SelfOrder> list = new ArrayList<SelfOrder>();
                for (int i = 0; i < uod.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。{
                    list.add(new SelfOrder(R.drawable.logotemp, uod.get(i).getPlace().split(" ")[1], uod.get(i).getNote(), uod.get(i).getDate(), uod.get(i).getNumberOfPeople()));
                    finishedOrders.add(i);
                }
                QueryArrayAdapter adapter = new QueryArrayAdapter(OrderList.this, R.layout.order_item_begin, list);
                order_list.setAdapter(adapter);
                order_list.setOnItemClickListener(OrderList.this);
            }

        };
    }

    //更新列表数据
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (status == USER) {
            if (status == IDLE) {
                Intent intent = new Intent(OrderList.this, UserIdleOrder.class);
                //存放选中的订单数据，供后面查询使用
                startActivity(intent);
            } else if (status == ACCEPTED) {
                Intent intent = new Intent(OrderList.this, UserAcceptedOrder.class);
                startActivity(intent);
            } else if (status == BEGIN) {
                Intent intent = new Intent(OrderList.this, UserBeginOrder.class);
                startActivity(intent);
            } else if (status == FINISHED) {
                Intent intent = new Intent(OrderList.this, UserFinishedOrder.class);
                startActivity(intent);
            }
        } else {
            if (status == IDLE) {
                Intent intent = new Intent(OrderList.this, GuideIdleOrder.class);
                startActivity(intent);
            } else if (status == ACCEPTED) {
                Intent intent = new Intent(OrderList.this, GuideAcceptedOrder.class);
                startActivity(intent);
            } else if (status == BEGIN) {
                Intent intent = new Intent(OrderList.this, GuideBeginOrder.class);
                startActivity(intent);
            } else if (status == FINISHED) {
                Intent intent = new Intent(OrderList.this, GuideFinishedOrder.class);
                startActivity(intent);
            }
        }
    }

    private void ListViewDataUpdate() {
        orderResult.clear();
        finishedOrders.clear();
        acceptOrders.clear();
        idleOrders.clear();
        beginOrders.clear();
        handler_refresh_listview.post(run_refresh_listview);

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
            lGuideOrderData = order.getGuideOrderData();
        } else {
            order = new Order(charactor, userData.getTelephone(), updateUI_interface);
            order.getFinishedOrders();
            lUserOrderData = order.getUserOrderData();
        }
    }

    private void getBeginOrders() {
        if (charactor == GUIDE) {
            order = new Order(charactor, userData.getGuideIDnumbr(), updateUI_interface);
            order.getBeginOrders();
            lGuideOrderData = order.getGuideOrderData();
        } else {
            order = new Order(charactor, userData.getTelephone(), updateUI_interface);
            order.getBeginOrders();
            lUserOrderData = order.getUserOrderData();
        }
    }

    private void getAcceptOrders() {
        if (charactor == GUIDE) {
            order = new Order(charactor, userData.getGuideIDnumbr(), updateUI_interface);
            order.getAcceptOrders();
//            lGuideOrderData = order.getGuideOrderData();
        } else {
            order = new Order(charactor, userData.getTelephone(), updateUI_interface);
            order.getAcceptOrders();
//            lUserOrderData = order.getUserOrderData();
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
