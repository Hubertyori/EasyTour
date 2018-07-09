package studio.opencloud.easytour21.internet.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opencloud.easytour21.internet.translations.Register_Translation;

public interface CancelOrder_Interface {

    @POST("UserCancelOrder.php")
    @FormUrlEncoded
    Call<Register_Translation> cancelOrder(@Field("orderId") String orderId);
//用户信息就该的返回数据可以用注册的翻译类来接收。
}
