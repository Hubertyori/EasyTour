package studio.opencloud.easytour21.internet.interfaces.guide;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opencloud.easytour21.internet.translations.Register_Translation;

public interface TakeOrder_Interface {
    //导游获取已接单信息
    @POST("GuideAcceptOrder.php")
    @FormUrlEncoded
    Call<Register_Translation> takeOrder(@Field("orderID") String orderID,
                                                 @Field("IDNumber") String IDNumber);
}
