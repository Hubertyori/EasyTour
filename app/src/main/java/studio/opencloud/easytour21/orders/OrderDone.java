package studio.opencloud.easytour21.orders;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.opencloud.easytour21.internet.interfaces.DealDone_Interface;
import studio.opencloud.easytour21.internet.interfaces.OrderDone_Interface;
import studio.opencloud.easytour21.internet.translations.Register_Translation;

public class OrderDone {
    private static final int USER = 0;
    private static final int GUIDE = 1;

    private int charactor;

    private int orderId;
    private int star;

    public OrderDone(int charactor, int orderId, int star) {
        this.charactor = charactor;
        this.orderId = orderId;
        this.star = star;
    }

    public void orderDone(){
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.89.18.136/YiYou/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        // 步骤5:创建 网络请求接口 的实例
        OrderDone_Interface request = retrofit.create(OrderDone_Interface.class);

        Call<Register_Translation> call = request.signUpInformation(orderId,star);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Register_Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Register_Translation> call, Response<Register_Translation> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                System.out.println("********************************************************");

            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Register_Translation> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());

            }
        });
    }

}
