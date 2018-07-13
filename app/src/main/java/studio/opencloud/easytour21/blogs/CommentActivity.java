package studio.opencloud.easytour21.blogs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opclound.easytour.R;
import studio.opencloud.easytour21.internet.datas.UserInformationData;
import studio.opencloud.easytour21.internet.interfaces.pbinterface.Login_Interface;
import studio.opencloud.easytour21.internet.translations.Login_Translation;
import studio.opencloud.easytour21.mainspace.MainActivity_X;
import studio.opencloud.easytour21.users.Login;

/**
 * by moos on 2018/04/20
 */
public class CommentActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "CommentActivity";
    private android.support.v7.widget.Toolbar toolbar;
    private TextView bt_comment;
    private CommentExpandableListView expandableListView;
    private CommentExpandAdapter adapter;
    //private CommentBean commentBean;
    private List<CommentDetailBean> commentsList;
    private BottomSheetDialog dialog;
    private DetailContentBean.DetailContent content ;
    String UserID;
    String UserHeadIcon;
    String UserTel;
    private String tel;
    private int bowenID;
    private ImageView mcollect;
    private UserInformationData userData;
    private Intent intent;
//     "{\n" +
//            "\t\"code\": 1000,\n" +
//            "\t\"message\": \"查看评论成功\",\n" +
//            "\t\"data\": {\n" +
//            "\t\t\"total\": 3,\n" +
//            "\t\t\"list\": [{\n" +
//            "\t\t\t\t\"id\": 42,\n" +
//            "\t\t\t\t\"nickName\": \"程序猿\",\n" +
//            "\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
//            "\t\t\t\t\"content\": \"时间是一切财富中最宝贵的财富。\",\n" +
//            "\t\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
//            "\t\t\t\t\"replyTotal\": 1,\n" +
//            "\t\t\t\t\"createDate\": \"三分钟前\",\n" +
//            "\t\t\t\t\"replyList\": [{\n" +
//            "\t\t\t\t\t\"nickName\": \"沐風\",\n" +
//            "\t\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
//            "\t\t\t\t\t\"id\": 40,\n" +
//            "\t\t\t\t\t\"commentId\": \"42\",\n" +
//            "\t\t\t\t\t\"content\": \"时间总是在不经意中擦肩而过,不留一点痕迹.\",\n" +
//            "\t\t\t\t\t\"status\": \"01\",\n" +
//            "\t\t\t\t\t\"createDate\": \"一个小时前\"\n" +
//            "\t\t\t\t}]\n" +
//            "\t\t\t},\n" +
//            "\t\t\t{\n" +
//            "\t\t\t\t\"id\": 41,\n" +
//            "\t\t\t\t\"nickName\": \"设计狗\",\n" +
//            "\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
//            "\t\t\t\t\"content\": \"这世界要是没有爱情，它在我们心中还会有什么意义！这就如一盏没有亮光的走马灯。\",\n" +
//            "\t\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
//            "\t\t\t\t\"replyTotal\": 1,\n" +
//            "\t\t\t\t\"createDate\": \"一天前\",\n" +
//            "\t\t\t\t\"replyList\": [{\n" +
//            "\t\t\t\t\t\"nickName\": \"沐風\",\n" +
//            "\t\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
//            "\t\t\t\t\t\"commentId\": \"41\",\n" +
//            "\t\t\t\t\t\"content\": \"时间总是在不经意中擦肩而过,不留一点痕迹.\",\n" +
//            "\t\t\t\t\t\"status\": \"01\",\n" +
//            "\t\t\t\t\t\"createDate\": \"三小时前\"\n" +
//            "\t\t\t\t}]\n" +
//            "\t\t\t},\n" +
//            "\t\t\t{\n" +
//            "\t\t\t\t\"id\": 40,\n" +
//            "\t\t\t\t\"nickName\": \"产品喵\",\n" +
//            "\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
//            "\t\t\t\t\"content\": \"笨蛋自以为聪明，聪明人才知道自己是笨蛋。\",\n" +
//            "\t\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
//            "\t\t\t\t\"replyTotal\": 0,\n" +
//            "\t\t\t\t\"createDate\": \"三天前\",\n" +
//            "\t\t\t\t\"replyList\": []\n" +
//            "\t\t\t}\n" +
//            "\t\t]\n" +
//            "\t}\n" +
//            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Bundle bundle = this.getIntent().getExtras();
        bowenID = bundle.getInt("bowenID");
        UserID = bundle.getString("userid");
        UserHeadIcon = bundle.getString("userheadicon");
        UserTel=bundle.getString("userTel");
        getDetailedContent(bowenID);
        getComments(bowenID);
        mcollect=findViewById(R.id.collect1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                /**
                 * 延时执行的代码
                 */
                System.out.println(content);
                initView();

            }
        },3000); // 延时3秒

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        expandableListView = (CommentExpandableListView) findViewById(R.id.detail_page_lv_comment);
        bt_comment = (TextView) findViewById(R.id.detail_page_do_comment);
        bt_comment.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("详情");
       // setDetailedContent();   //更新博文详细内容
        initExpandableListView(commentsList);  //更新评论内容
    }

    /**更新博文详细内容**/
    private void setDetailedContent()
    {
        ((TextView)findViewById(R.id.detail_page_userName)).setText(content.getUserNickname());
        ((TextView)findViewById(R.id.detail_page_title)).setText(content.getTitle());
        ((TextView)findViewById(R.id.detail_page_time)).setText(content.getTime());
        ((TextView)findViewById(R.id.detail_page_story)).setText(content.getContent());
        System.out.println(content.getUserHeadIcon());
        System.out.println(content.getImage());
        Picasso.with(CommentActivity.this).load(content.getUserHeadIcon()).noFade().into((ImageView)findViewById(R.id.detail_page_userLogo));
        if(!content.getImage().toString().equals("no") )
            Picasso.with(CommentActivity.this).load(content.getImage()).noFade().into((ImageView)findViewById(R.id.detail_page_image));


    }


    /**网络请求详细内容**/
    private void getDetailedContent(int id)
    {
        //创建retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        //创建 网络请求接口 的实例
        GetDetailedContent_Interface request = retrofit.create(GetDetailedContent_Interface.class);

        //对 发送请求 进行封装
        Call<DetailContentBean> call = request.getDetailContent_CALL(id);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<DetailContentBean>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<DetailContentBean> call, Response<DetailContentBean> response) {
                if(response.body() == null)
                    System.out.println("返回体为空");
                else
                if(response.body().getCode() == 1) {
                    content = response.body().getData();
                    Log.i("1111111111111111",content.getUserHeadIcon()+content.getUserId()+content.getZanNumber()+" ");
                    setDetailedContent();
                    System.out.println(response.body());

                }
                else
                    System.out.println("错误"+response.body().getMessage());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<DetailContentBean> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
            }});
    }

    /**
     * 网络请求评论内容
     */
    private void getComments(int id)
    {
        //创建retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        //创建 网络请求接口 的实例
        GetComments_Interface request = retrofit.create(GetComments_Interface.class);

        //对 发送请求 进行封装
        Call<GetAllReplyBean> call = request.getComments_CALL(id);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<GetAllReplyBean>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<GetAllReplyBean> call, Response<GetAllReplyBean> response) {
                if(response.body() == null)
                    System.out.println("返回体为空");
                else
                if(response.body().getCode() == 1) {
                    commentsList = response.body().getData();
                    System.out.println(response.body());

                }
                else{
                    System.out.println(response.body().getMessage());
                    commentsList = response.body().getData();
                    commentsList.clear();
                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<GetAllReplyBean> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
            }});
    }

    /**
     * 提交评论
     */
    private void MakeComments(String tel, int bowenid, final String CommentContent, final String time)
    {
        //创建retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        //创建 网络请求接口 的实例
        MakeComment_Interface request = retrofit.create(MakeComment_Interface.class);

        //对 发送请求 进行封装
        Call<MakeCommentResultBean> call = request.MakeComment_CALL(tel,bowenid,CommentContent,time);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<MakeCommentResultBean>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<MakeCommentResultBean> call, Response<MakeCommentResultBean> response) {
                if(response.body() == null)
                    System.out.println("返回体为空");
                else
                if(response.body().getCode() == 1) {
                        dialog.dismiss();
                        CommentDetailBean detailBean = new CommentDetailBean(UserID, CommentContent,time, UserHeadIcon);
                        adapter.addTheCommentData(detailBean);
                        Toast.makeText(CommentActivity.this,"评论成功", Toast.LENGTH_SHORT).show();
                    System.out.println(response.body());

                }
                else {
                    Toast.makeText(CommentActivity.this, "评论失败", Toast.LENGTH_SHORT).show();
                    System.out.println(response.body().getMessage());
                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<MakeCommentResultBean> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
            }});
    }

    /**
     * 初始化评论和回复列表
     */
    private void initExpandableListView(final List<CommentDetailBean> commentList){
            expandableListView.setGroupIndicator(null);
            //默认展开所有回复

            adapter = new CommentExpandAdapter(this, commentList);
            expandableListView.setAdapter(adapter);

//        for(int i = 0; i<commentList.size(); i++){
//            expandableListView.expandGroup(i);
//        }
//        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
//                boolean isExpanded = expandableListView.isGroupExpanded(groupPosition);
//                Log.e(TAG, "onGroupClick: 当前的评论id>>>"+commentList.get(groupPosition).getUserNickname());
//                if(isExpanded){
//                    expandableListView.collapseGroup(groupPosition);
//                }else {
//                    expandableListView.expandGroup(groupPosition, true);
//                }
//                showReplyDialog(groupPosition);
//                return true;
//            }
//        });

//        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
//                Toast.makeText(CommentActivity.this,"点击了回复",Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                //toast("展开第"+groupPosition+"个分组");
//
//            }
//        });

    }

    /**
     * by moos on 2018/04/20
     * func:生成测试数据
     * @return 评论数据
     */
//    private List<CommentDetailBean> generateTestData(){
//        Gson gson = new Gson();
//       commentBean = gson.fromJson(testJson, CommentBean.class);
//        List<CommentDetailBean> commentList = commentBean.getData().getList();
//        return commentList;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.detail_page_do_comment){

            showCommentDialog();
        }
    }

    /**
    获取当前系统时间
     */
    public String getNowSysTime()
    {
        SimpleDateFormat sDateFormat    =   new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        return date;

    }

    /**
     * by moos on 2018/04/20
     * func:弹出评论框
     */
    private void showCommentDialog(){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);
        /**
         * 解决bsd显示不全的情况
         */
        View parent = (View) commentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0,0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());

        bt_comment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String commentContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(commentContent)){
                        MakeComments(UserTel,bowenID,commentContent,getNowSysTime());

                }else {
                    Toast.makeText(CommentActivity.this,"评论内容不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();

    }
    /********************************************************/
    public void onclick(View view)
    {
        collected();
    }

    private void collected() {
//步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)7
                .build();

        // 步骤5:创建 网络请求接口 的实例
        Collect_Interface request = retrofit.create(Collect_Interface.class);
        //对 发送请求 进行封装(设置需要翻译的内容)

        collectInfo();
        Call<Collect_Translation> call = request.collect(bowenID,tel);
        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Collect_Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Collect_Translation> call, Response<Collect_Translation> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                Toast.makeText(CommentActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(response.body().getMessage());

            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Collect_Translation> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());

            }
        });



    }
    private void collectInfo () {


        Bundle bundle = this.getIntent().getExtras();
        tel = UserTel;
        bowenID=bundle.getInt("bowenID");



    }

}

//    /**
//     * by moos on 2018/04/20
//     * func:弹出回复框
//     */
//    private void showReplyDialog(final int position){
//        dialog = new BottomSheetDialog(this);
//        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
//        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
//        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
//        commentText.setHint("回复 " + commentsList.get(position).getNickName() + " 的评论:");
//        dialog.setContentView(commentView);
//        bt_comment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String replyContent = commentText.getText().toString().trim();
//                if(!TextUtils.isEmpty(replyContent)){
//
//                    dialog.dismiss();
//                    ReplyDetailBean detailBean = new ReplyDetailBean("小红",replyContent);
//                    adapter.addTheReplyData(detailBean, position);
//                    expandableListView.expandGroup(position);
//                    Toast.makeText(CommentActivity.this,"回复成功",Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(CommentActivity.this,"回复内容不能为空",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        commentText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
//                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
//                }else {
//                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//        dialog.show();
//    }


