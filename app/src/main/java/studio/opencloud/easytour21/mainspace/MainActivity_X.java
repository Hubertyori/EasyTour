package studio.opencloud.easytour21.mainspace;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opclound.easytour.R;
import studio.opencloud.easytour21.blogs.BowenListBean;
import studio.opencloud.easytour21.blogs.GetAllBowenList_Interface;
import studio.opencloud.easytour21.blogs.GetColletion_Interface;
import studio.opencloud.easytour21.blogs.MulRecyclerViewAdapter;
import studio.opencloud.easytour21.blogs.NullOnEmptyConverterFactory;
import studio.opencloud.easytour21.blogs.writeBlogActivity;
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
    private List<BowenListBean.BowenListInfo> listContent = new ArrayList<>();  //获取所有的博文列表
    private RecyclerView recyclerView;
    private PtrClassicFrameLayout ptrClassicFrameLayout;
    private Handler handler = new Handler();
    private int page = 0;
    private List<BowenListBean.BowenListInfo> list = new ArrayList<BowenListBean.BowenListInfo>();  //存储一部分的博文列表
    private MulRecyclerViewAdapter adapter;
    private RecyclerAdapterWithHF mAdapter;
    static int i = 0;
    private ImageView mhead;
    private UserInformationData userInformation;
    private TextView mName;
    private TextView mDes;
    private ImageView mhead2;
    private int flag = 0;
    private TextView mfind;
    private TextView mcollect;
    Drawable mdrawable;


    ImageView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_x);

        intent = getIntent();
        getInformation();

        userData = new UserInformationData(userInformation);

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_na);
        navigationView = (NavigationView) findViewById(R.id.nav);
        mfind = findViewById(R.id.find);
        mcollect = findViewById(R.id.collect);


        mhead2 = findViewById(R.id.toolbar_profile_head);


        View headerView = navigationView.getHeaderView(0);//获取头布局
        mhead = headerView.findViewById(R.id.person);
        mName = headerView.findViewById(R.id.m_name);
        mDes = headerView.findViewById(R.id.m_des);
        Picasso.with(MainActivity_X.this)
                .load(userInformation.getHeadphoto())
                .placeholder(R.drawable.default_ic)
                .error(R.drawable.error)
                .into(mhead);
        Picasso.with(MainActivity_X.this)
                .load(userInformation.getHeadphoto())
                .placeholder(R.drawable.default_ic)
                .error(R.drawable.error)
                .into(mhead2);
        mDes.setText(userInformation.getIntroduce());
        mName.setText(userInformation.getNickname());
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
                        userInformation.setIsguide("no");
                        intent = new Intent(MainActivity_X.this, OrderList.class);
                        intent.putExtra("userData", userInformation);
                        startActivity(intent);

                        break;

                    }
                    case R.id.guide_order: {

                        if (userData.getIsguide().equals("yes")) {
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
        initView();
        adapter = new MulRecyclerViewAdapter(this, list, userData.getTelephone());
        mAdapter = new RecyclerAdapterWithHF(adapter);
        recyclerView.setAdapter(mAdapter);

        //为写博客的FAB控件添加点击事件
        FloatingActionButton addBlogFAB = (FloatingActionButton) findViewById(R.id.fab);
        addBlogFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
//                Bundle bundle = new Bundle();
//                bundle.putString("userTel",userData.getTelephone());
                it.putExtra("userTel", userData.getTelephone());
                it.setClass(MainActivity_X.this, writeBlogActivity.class);
                startActivity(it);


                //传用户信息
                //bundle.putString("");

            }
        });


        ptrClassicFrameLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (flag == 0)
                    getAllBowenList();
                if (flag == 1)
                    getCollect();
                page = 0;
                list.clear();
                i = 0;
                int k = i + 10;
                for (; i < k; i++) {
                    if (listContent.size() == i || listContent.size() == 0)                                  //如果该item为空跳出循环
                        break;
                    list.add(listContent.get(i));                                   //每次获取十个item
                }
                listContent.clear();
                mAdapter.notifyDataSetChanged();

                ptrClassicFrameLayout.autoRefresh(true);
            }
        }, 150);

        //下拉刷新全部列表
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (flag == 0)
                            getAllBowenList();
                        if (flag == 1)
                            getCollect();
                        page = 0;
                        list.clear();
                        i = 0;
//                        try {

//                            // 获取博文内容简略信息
//                            JSONObject jsonObject = new JSONObject(listContent);
//                            JSONArray jsonArray  = jsonObject.getJSONArray("data");

                        //读取每条博文内容
                        int k = i + 10;
                        for (; i < k; i++) {
                            if (listContent.size() == i || listContent.size() == 0)                                  //如果该item为空跳出循环
                                break;
                            list.add(listContent.get(i));                                   //每次获取十个item
//                                BowenListBean bowenListBean = new BowenListBean();
//                                bowenListBean.setType(jsonArray.getJSONObject(i).getInt("type"));
//                                bowenListBean.setTitle(jsonArray.getJSONObject(i).getString("title"));
//                                bowenListBean.setF_time(jsonArray.getJSONObject(i).getString("time"));
//                                bowenListBean.setAuthor(jsonArray.getJSONObject(i).getString("author"));
//                                bowenListBean.setContent(jsonArray.getJSONObject(i).getString("content"));

                            // int len = jsonArray.getJSONObject(i).getJSONArray("imgs").length();   //获取文中图片数量

//                                List<String> ls = new ArrayList<>();
//                                for(int j = 0;j<len;j++){
//                                    String s = jsonArray.getJSONObject(i).getJSONArray("imgs").getString(j);
//                                    ls.add(s);
//                                }
//                                bowenListBean.setList(ls);

                        }
                        listContent.clear();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                        mAdapter.notifyDataSetChanged();
                        ptrClassicFrameLayout.refreshComplete();
                        ptrClassicFrameLayout.setLoadMoreEnable(true);
                        Log.d("TAG", "正在刷新...");
                    }
                }, 1500);
            }
        });

        //上拉加载更多内容
        ptrClassicFrameLayout.setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void loadMore() {
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        if (flag == 0)
                            getAllBowenList();
                        if (flag == 1)
                            getCollect();
                        int k = i + 10;
                        for (; i < k; i++) {
                            if (listContent.size() == i || listContent.size() == 0)                                  //如果该item为空跳出循环
                                break;
                            list.add(listContent.get(i));                                   //每次获取十个item
//                                BowenListBean bowenListBean = new BowenListBean();
//                                bowenListBean.setType(jsonArray.getJSONObject(i).getInt("type"));
//                                bowenListBean.setTitle(jsonArray.getJSONObject(i).getString("title"));
//                                bowenListBean.setF_time(jsonArray.getJSONObject(i).getString("time"));
//                                bowenListBean.setAuthor(jsonArray.getJSONObject(i).getString("author"));
//                                bowenListBean.setContent(jsonArray.getJSONObject(i).getString("content"));
//                                int len = jsonArray.getJSONObject(i).getJSONArray("imgs").length();
//                                List<String> ls = new ArrayList<>();
//                                for(int j = 0;j<len;j++){
//                                    String s = jsonArray.getJSONObject(i).getJSONArray("imgs").getString(j);
//                                    ls.add(s);
//                                }
//                                bowenListBean.setList(ls);

                        }
                        listContent.clear();     //清除listContent

//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                        mAdapter.notifyDataSetChanged();
                        ptrClassicFrameLayout.loadMoreComplete(true);
                        page++;
                        Log.e("TAG", page + "页");
                        Toast.makeText(MainActivity_X.this, "加载完成", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);
            }
        });
    }

    private void getInformation() {
        userInformation = intent.getParcelableExtra("userData");
    }

    @Override
    public void onClick(View v) {

    }

    private void initView() {
        ptrClassicFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.test_list_view_frame);
        recyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity_X.this, LinearLayoutManager.VERTICAL));

    }

    private void getAllBowenList() {
        //创建retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        //创建 网络请求接口 的实例
        GetAllBowenList_Interface request = retrofit.create(GetAllBowenList_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        Call<BowenListBean> call = request.getAllBowenList_CALL();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<BowenListBean>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BowenListBean> call, Response<BowenListBean> response) {
                if (response.body().getCode() == 1) {

                    listContent = response.body().getData();   //将 获取到的博文列表存放在listcontent中
                    Toast.makeText(MainActivity_X.this, "获取成功", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity_X.this, "获取失败", Toast.LENGTH_SHORT).show();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<BowenListBean> call, Throwable throwable) {
                Toast.makeText(MainActivity_X.this, "提交失败" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void pullout(View view) {
        drawerLayout.openDrawer(navigationView);


    }


    private void getCollect() {
        //创建retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        //创建 网络请求接口 的实例
        GetColletion_Interface request = retrofit.create(GetColletion_Interface.class);
        String tel = userInformation.getTelephone();
        //对 发送请求 进行封装(设置需要翻译的内容)
        Call<BowenListBean> call = request.collect(tel);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<BowenListBean>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BowenListBean> call, Response<BowenListBean> response) {
                if (response.body().getCode() == 1) {

                    listContent = response.body().getData();   //将 获取到的博文列表存放在listcontent中
                    Toast.makeText(MainActivity_X.this, "获取成功", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity_X.this, "获取失败", Toast.LENGTH_SHORT).show();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<BowenListBean> call, Throwable throwable) {
                Toast.makeText(MainActivity_X.this, "提交失败" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void collect(View view) {
        Resources res = getResources();
        Drawable myImage = res.getDrawable(R.drawable.bg_orange_oval);

        mfind.setBackground(null);
        mfind.setTextColor(Color.rgb(255, 255, 255));

        mcollect.setTextColor(Color.rgb(0, 0, 0));
        mcollect.setBackground(myImage);
        initView();
        flag = 1;


    }

    public void find(View view) {
        Resources res = getResources();
        Drawable myImage = res.getDrawable(R.drawable.bg_orange_oval);

        mcollect.setBackground(null);
        mcollect.setTextColor(Color.rgb(255, 255, 255));

        mfind.setTextColor(Color.rgb(0, 0, 0));
        mfind.setBackground(myImage);

        flag = 0;

    }


}