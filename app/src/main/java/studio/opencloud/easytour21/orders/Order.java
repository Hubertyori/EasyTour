package studio.opencloud.easytour21.orders;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opencloud.easytour21.internet.datas.GuideOrderData;
import studio.opencloud.easytour21.internet.datas.UserOrderData;
import studio.opencloud.easytour21.internet.interfaces.QuerryOrder_Interface;
import studio.opencloud.easytour21.internet.interfaces.UpdateUI_Interface;
import studio.opencloud.easytour21.internet.translations.Guide_Order_Translation;
import studio.opencloud.easytour21.internet.translations.User_Order_Translation;

public class MyOrder {
    private static final int IDLE = 0;
    private static final int ACCEPTED = 1;
    private static final int BEGIN = 2;
    private static final int FINISHED = 3;

    private static final int USER = 0;
    private static final int GUIDE = 1;

    private int charactor;
    private String phone;
    private String IDnumber;
    private List<UserOrderData> user_order_data;
    private List<GuideOrderData> guide_order_data;
    private  UpdateUI_Interface updateUI_interface;

    MyOrder(int charactor, String phone, UpdateUI_Interface updateUI_interface ) {
        this.updateUI_interface = updateUI_interface;
        this.charactor = charactor;
        if (charactor == USER) {
            this.phone = phone;
        } else {
            this.IDnumber = phone;
        }
    }


    //根据charactor判断角色获取订单
    public void getFinishedOrders() {
        if (charactor == USER) {
            //步骤4:创建Retrofit对象
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                    .build();
            // 步骤5:创建 网络请求接口 的实例
            QuerryOrder_Interface request = retrofit.create(QuerryOrder_Interface.class);

            Call<User_Order_Translation> call = request.getUserFinishedOrders(phone);

            //步骤6:发送网络请求(异步)
            call.enqueue(new Callback<User_Order_Translation>() {
                             //请求成功时回调
                             @Override
                             public void onResponse(Call<User_Order_Translation> call, Response<User_Order_Translation> response) {
                                 // 步骤7：处理返回的数据结果：输出翻译的内容
//                Toast.makeText(OrderList.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                 System.out.println("********************************************************");
                                 user_order_data = response.body().getData();
                                 System.out.println();
                                 for (int i = 0; i < user_order_data.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
                                     System.out.println(user_order_data.get(i));
//                    Toast.makeText(OrderList.this, "请求失败", Toast.LENGTH_SHORT).show();
                                 }
                             }
                             //请求失败时回调
                             @Override
                             public void onFailure(Call<User_Order_Translation> call, Throwable throwable) {
                                 System.out.println("请求失败");
                                 System.out.println(throwable.getMessage());
//                Toast.makeText(OrderList.this, "请求失败", Toast.LENGTH_SHORT).show();
                             }
                         }
            );
        } else {
            //步骤4:创建Retrofit对象
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                    .build();
            // 步骤5:创建 网络请求接口 的实例
            QuerryOrder_Interface request = retrofit.create(QuerryOrder_Interface.class);

            Call<Guide_Order_Translation> call = request.getGuideFinishedOrders(IDnumber);

            //步骤6:发送网络请求(异步)
            call.enqueue(new Callback<Guide_Order_Translation>() {
                             //请求成功时回调
                             @Override
                             public void onResponse(Call<Guide_Order_Translation> call, Response<Guide_Order_Translation> response) {
                                 // 步骤7：处理返回的数据结果：输出翻译的内容
//                Toast.makeText(OrderList.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                 System.out.println("********************************************************");
                                 guide_order_data = response.body().getData();
                                 System.out.println();
                                 for (int i = 0; i < guide_order_data.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
                                     System.out.println(guide_order_data.get(i));
//                    Toast.makeText(OrderList.this, "请求失败", Toast.LENGTH_SHORT).show();
                                 }
                             }

                             //请求失败时回调
                             @Override
                             public void onFailure(Call<Guide_Order_Translation> call, Throwable throwable) {
                                 System.out.println("请求失败");
                                 System.out.println(throwable.getMessage());
//                Toast.makeText(OrderList.this, "请求失败", Toast.LENGTH_SHORT).show();
                             }
                         }
            );
        }
    }

    public void getBeginOrders() {
        if (charactor == USER) {
            //步骤4:创建Retrofit对象
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                    .build();
            // 步骤5:创建 网络请求接口 的实例
            QuerryOrder_Interface request = retrofit.create(QuerryOrder_Interface.class);

            Call<User_Order_Translation> call = request.getUserBeginOrders(phone);

            //步骤6:发送网络请求(异步)
            call.enqueue(new Callback<User_Order_Translation>() {
                             //请求成功时回调
                             @Override
                             public void onResponse(Call<User_Order_Translation> call, Response<User_Order_Translation> response) {
                                 // 步骤7：处理返回的数据结果：输出翻译的内容
//                Toast.makeText(OrderList.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                 System.out.println("********************************************************");
                                 user_order_data = response.body().getData();
                                 System.out.println();
                                 for (int i = 0; i < user_order_data.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
                                     System.out.println(user_order_data.get(i));
//                    Toast.makeText(OrderList.this, "请求失败", Toast.LENGTH_SHORT).show();
                                 }
                             }

                             //请求失败时回调
                             @Override
                             public void onFailure(Call<User_Order_Translation> call, Throwable throwable) {
                                 System.out.println("请求失败");
                                 System.out.println(throwable.getMessage());
//                Toast.makeText(OrderList.this, "请求失败", Toast.LENGTH_SHORT).show();
                             }
                         }
            );
        } else {
            //步骤4:创建Retrofit对象
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                    .build();
            // 步骤5:创建 网络请求接口 的实例
            QuerryOrder_Interface request = retrofit.create(QuerryOrder_Interface.class);

            Call<Guide_Order_Translation> call = request.getGuideBeginOrders(IDnumber);

            //步骤6:发送网络请求(异步)
            call.enqueue(new Callback<Guide_Order_Translation>() {
                             //请求成功时回调
                             @Override
                             public void onResponse(Call<Guide_Order_Translation> call, Response<Guide_Order_Translation> response) {
                                 // 步骤7：处理返回的数据结果：输出翻译的内容
//                Toast.makeText(OrderList.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                 System.out.println("********************************************************");
                                 guide_order_data = response.body().getData();
                                 System.out.println();
                                 for (int i = 0; i < guide_order_data.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
                                     System.out.println(guide_order_data.get(i));
//                    Toast.makeText(OrderList.this, "请求失败", Toast.LENGTH_SHORT).show();
                                 }
                             }

                             //请求失败时回调
                             @Override
                             public void onFailure(Call<Guide_Order_Translation> call, Throwable throwable) {
                                 System.out.println("请求失败");
                                 System.out.println(throwable.getMessage());
//                Toast.makeText(OrderList.this, "请求失败", Toast.LENGTH_SHORT).show();
                             }
                         }
            );
        }
    }

    public void getAcceptOrders() {
        if (charactor == USER) {
            //步骤4:创建Retrofit对象
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                    .build();
            // 步骤5:创建 网络请求接口 的实例
            QuerryOrder_Interface request = retrofit.create(QuerryOrder_Interface.class);

            Call<User_Order_Translation> call = request.getUserAcceptedOrders(phone);

            //步骤6:发送网络请求(异步)
            call.enqueue(new Callback<User_Order_Translation>() {
                             //请求成功时回调
                             @Override
                             public void onResponse(Call<User_Order_Translation> call, Response<User_Order_Translation> response) {
                                 // 步骤7：处理返回的数据结果：输出翻译的内容
//                Toast.makeText(OrderList.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                 System.out.println("********************************************************");
                                 user_order_data = response.body().getData();
                                 System.out.println();
                                 for (int i = 0; i < user_order_data.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
                                     System.out.println(user_order_data.get(i));
//                    Toast.makeText(OrderList.this, "请求失败", Toast.LENGTH_SHORT).show();
                                 }
                             }

                             //请求失败时回调
                             @Override
                             public void onFailure(Call<User_Order_Translation> call, Throwable throwable) {
                                 System.out.println("请求失败");
                                 System.out.println(throwable.getMessage());
//                Toast.makeText(OrderList.this, "请求失败", Toast.LENGTH_SHORT).show();
                             }
                         }
            );
        } else {
            //步骤4:创建Retrofit对象
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                    .build();
            // 步骤5:创建 网络请求接口 的实例
            QuerryOrder_Interface request = retrofit.create(QuerryOrder_Interface.class);

            Call<Guide_Order_Translation> call = request.getGuideAcceptedOrders(IDnumber);

            //步骤6:发送网络请求(异步)
            call.enqueue(new Callback<Guide_Order_Translation>() {
                             //请求成功时回调
                             @Override
                             public void onResponse(Call<Guide_Order_Translation> call, Response<Guide_Order_Translation> response) {
                                 // 步骤7：处理返回的数据结果：输出翻译的内容
//                Toast.makeText(OrderList.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                 System.out.println("********************************************************");
                                 guide_order_data = response.body().getData();
                                 System.out.println();
                                 for (int i = 0; i < guide_order_data.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
                                     System.out.println(guide_order_data.get(i));
//                    Toast.makeText(OrderList.this, "请求失败", Toast.LENGTH_SHORT).show();
                                 }
                             }

                             //请求失败时回调
                             @Override
                             public void onFailure(Call<Guide_Order_Translation> call, Throwable throwable) {
                                 System.out.println("请求失败");
                                 System.out.println(throwable.getMessage());
//                Toast.makeText(OrderList.this, "请求失败", Toast.LENGTH_SHORT).show();
                             }
                         }
            );
        }
    }

    public void getIdleOrders() {
        if (charactor == USER) {
            //步骤4:创建Retrofit对象
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                    .build();
            // 步骤5:创建 网络请求接口 的实例
            QuerryOrder_Interface request = retrofit.create(QuerryOrder_Interface.class);

            Call<User_Order_Translation> call = request.getUserIdleOrders(phone);

            //步骤6:发送网络请求(异步)
            call.enqueue(new Callback<User_Order_Translation>() {
                             //请求成功时回调
                             @Override
                             public void onResponse(Call<User_Order_Translation> call, Response<User_Order_Translation> response) {
                                 // 步骤7：处理返回的数据结果：输出翻译的内容
//                Toast.makeText(OrderList.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                 System.out.println("********************************************************");
                                 user_order_data = response.body().getData();
                                 for (int i = 0; i < user_order_data.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
                                        updateUI_interface.updateUserOrderUI(user_order_data.get(i));
                                 }
                             }
                             //lost river

                             //请求失败时回调
                             @Override
                             public void onFailure(Call<User_Order_Translation> call, Throwable throwable) {
                                 System.out.println("请求失败");
                                 System.out.println(throwable.getMessage());
//                Toast.makeText(OrderList.this, "请求失败", Toast.LENGTH_SHORT).show();
                             }
                         }
            );
        } else {
            //步骤4:创建Retrofit对象
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                    .build();
            // 步骤5:创建 网络请求接口 的实例
            QuerryOrder_Interface request = retrofit.create(QuerryOrder_Interface.class);

            Call<Guide_Order_Translation> call = request.getGuideNearByOrders(IDnumber);

            //步骤6:发送网络请求(异步)
            call.enqueue(new Callback<Guide_Order_Translation>() {
                             //请求成功时回调
                             @Override
                             public void onResponse(Call<Guide_Order_Translation> call, Response<Guide_Order_Translation> response) {
                                 // 步骤7：处理返回的数据结果：输出翻译的内容
//                Toast.makeText(OrderList.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                 System.out.println("********************************************************");
                                 guide_order_data = response.body().getData();
                                 for (int i = 0; i < guide_order_data.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
                                     updateUI_interface.updateGuideOrderUI(guide_order_data.get(i));
                                 }
                             }

                             //请求失败时回调
                             @Override
                             public void onFailure(Call<Guide_Order_Translation> call, Throwable throwable) {
                                 System.out.println("请求失败");
                                 System.out.println(throwable.getMessage());
//                Toast.makeText(OrderList.this, "请求失败", Toast.LENGTH_SHORT).show();
                             }
                         }
            );
        }
    }

    public List<UserOrderData> getUserOrderData(){
        return user_order_data;
    }
    public List<GuideOrderData> getGuideOrderData(){

        return guide_order_data;
    }
}
