package studio.opencloud.easytour21.internet.interfaces.pbinterface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opencloud.easytour21.internet.translations.Register_Translation;

public interface FinisheOrder_Interface {
    //用户完成订单接口
    @POST("UserFinishOrder.php")
    @FormUrlEncoded
    Call<Register_Translation> finishOrder(@Field("orderId") int orderID,
                                           @Field("star") int star);
    //导游完成订单接口
    @POST("GuideFinishOrder.php")
    @FormUrlEncoded
    Call<Register_Translation> guideFinishOrder(@Field("orderId") int orderID,
                                                @Field("star") int star);
}
