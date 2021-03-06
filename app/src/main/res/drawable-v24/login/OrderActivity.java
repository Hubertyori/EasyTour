package com.example.xh.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private Button btn_i,btn_a,btn_b,btn_f;
    private GuideMainRefreshableView guideMainRefreshableView;
    private ListView order_list;
    private List<Integer> idleorder;
    private static final int IDLE = 0x1111;
    private static final int ACCEPT = 0x2222;
    private static final int BEGIN= 0x3333;
    private static final int FINISHED = 0x4444;
    private Handler handler_refresh_listview;
    private GestureDetector detector;
    private int flag;




    protected void onCreate(Bundle savedInstanceState){


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        btn_i=findViewById(R.id.btn_i);
        btn_a=findViewById(R.id.btn_a);
        btn_b=findViewById(R.id.btn_b);
        btn_f=findViewById(R.id.btn_f);
        order_list=findViewById(R.id.tourist_query_orders_listview);

        guideMainRefreshableView = (GuideMainRefreshableView) findViewById(R.id.tourist_query_rv);
        guideMainRefreshableView.listView = (ListView) findViewById(R.id.tourist_query_orders_listview);
        guideMainRefreshableView.setOnRefreshListener(new GuideMainRefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    ListViewDataUpdate();/*更新列表数据*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
                guideMainRefreshableView.finishRefreshing();
            }
        }, 0);


    }
    /****************************************************************************/

    private Runnable run_refresh_listview = new Runnable() {
        @Override
        public void run() {
            if(flag == IDLE){
                QueryArrayAdapter adapter = new QueryArrayAdapter(com.example.xh.login.OrderActivity.this,R.layout.order_item_idle,getDataIdle());
                order_list.setAdapter(adapter);
            }
            if(flag == ACCEPT){
                QueryArrayAdapter adapter = new QueryArrayAdapter(com.example.xh.login.OrderActivity.this,R.layout.order_item_accept,getDataAccept());
                order_list.setAdapter(adapter);
            }

            if(flag == BEGIN){
                QueryArrayAdapter adapter = new QueryArrayAdapter(com.example.xh.login.OrderActivity.this,R.layout.order_item_begin,getDataIdle());
                order_list.setAdapter(adapter);
            }
            if(flag == FINISHED){
                QueryArrayAdapter adapter = new QueryArrayAdapter(com.example.xh.login.OrderActivity.this,R.layout.order_item_finished,getDataAccept());
                order_list.setAdapter(adapter);
            }
        }
    };



    private void ListViewDataUpdate(){

    }
    public void idle(View view) {
        flag = IDLE;
        btn_i.setBackgroundColor(getResources().getColor(R.color.colorButtonBlue));
        btn_b.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btn_a.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btn_f.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        QueryArrayAdapter adapter = new QueryArrayAdapter(com.example.xh.login.OrderActivity.this,R.layout.order_item_idle,getDataIdle());
        order_list.setAdapter(adapter);

    }
    public void accept(View view) {
        flag = ACCEPT;
        btn_a.setBackgroundColor(getResources().getColor(R.color.colorButtonBlue));
        btn_b.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btn_i.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btn_f.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        QueryArrayAdapter adapter = new QueryArrayAdapter(com.example.xh.login.OrderActivity.this,R.layout.order_item_accept,getDataAccept());
        order_list.setAdapter(adapter);
    }
    public void begin(View view) {
        flag = BEGIN;
        btn_b.setBackgroundColor(getResources().getColor(R.color.colorButtonBlue));
        btn_i.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btn_a.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btn_f.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        QueryArrayAdapter adapter = new QueryArrayAdapter(com.example.xh.login.OrderActivity.this,R.layout.order_item_begin,getDataBegin());
        order_list.setAdapter(adapter);
    }
    public void finished(View view) {
        flag = FINISHED;
        btn_f.setBackgroundColor(getResources().getColor(R.color.colorButtonBlue));
        btn_b.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btn_a.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        btn_i.setBackgroundColor(getResources().getColor(R.color.colorButtonGray));
        QueryArrayAdapter adapter = new QueryArrayAdapter(com.example.xh.login.OrderActivity.this,R.layout.order_item_finished,getDataFinished());
        order_list.setAdapter(adapter);
    }

    public void cheack_idle(View view){
        Intent intent =new Intent(com.example.xh.login.OrderActivity.this,activity_check_order.class);
                       //启动
        startActivity(intent);


    }
    public void choose_guide(View view)
    {
        Intent intent =new Intent(com.example.xh.login.OrderActivity.this,EuclidActivity_i.class);
        //启动
        startActivity(intent);

    }
    public void finish_order(View view)
    {
        Intent intent =new Intent(com.example.xh.login.OrderActivity.this,EvaluateActivity.class);
        //启动
        startActivity(intent);

    }
    public void end_order(View view)
    {
        Intent intent =new Intent(com.example.xh.login.OrderActivity.this, com.example.xh.login.activity_order_end.class);
        //启动
        startActivity(intent);
    }



    private List<Order> getDataIdle() {
        // TODO Auto-generated method stub
        List<Order> list= new ArrayList<Order>();

        list.add(new Order(R.drawable.logotemp,"成都市","haha","2018/8/20","3"));
        list.add(new Order(R.drawable.logotemp,"重庆市","haha","2018/8/20","3"));
        list.add(new Order(R.drawable.logotemp,"成都市","haha","2018/8/20","3"));
        list.add(new Order(R.drawable.logotemp,"重庆市","haha","2018/8/20","3"));
        list.add(new Order(R.drawable.logotemp,"成都市","haha","2018/8/20","3"));
        list.add(new Order(R.drawable.logotemp,"重庆市","haha","2018/8/20","3"));


        return list;
    }
    private List<Order> getDataAccept() {
        // TODO Auto-generated method stub
        List<Order> list= new ArrayList<Order>();

        list.add(new Order(R.drawable.logotemp,"长安市","haha","2018/8/20","3"));
        list.add(new Order(R.drawable.logotemp,"南昌市","haha","2018/8/20","3"));

        return list;
    }
    private List<Order> getDataBegin() {
        // TODO Auto-generated method stub
        List<Order> list= new ArrayList<Order>();

        list.add(new Order(R.drawable.logotemp,"北京市","haha","2018/8/20","3"));
        list.add(new Order(R.drawable.logotemp,"香港市","haha","2018/8/20","3"));

        return list;
    }
    private List<Order> getDataFinished() {
        // TODO Auto-generated method stub
        List<Order> list= new ArrayList<Order>();

        list.add(new Order(R.drawable.logotemp,"长安市","haha","2018/8/20","3"));
        list.add(new Order(R.drawable.logotemp,"南昌市","haha","2018/8/20","3"));

        return list;
    }



}
