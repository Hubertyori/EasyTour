package studio.opencloud.easytour21.internet.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opencloud.easytour21.internet.translations.Register_Translation;

public interface OrderDone_Interface {
    @POST("GuideFinishOrder.php ")
    @FormUrlEncoded
    Call<Register_Translation> signUpInformation(@Field("orderID") int orderID,
                                                 @Field("guideNumber") int guideNumber);
}
