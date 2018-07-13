package studio.opencloud.easytour21.internet.interfaces.user;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opencloud.easytour21.internet.translations.Register_Translation;

public interface UserSelectGuide_Interface {
    //用户选择导游
    @POST("UserBeginOrder.php")
    @FormUrlEncoded
    Call<Register_Translation> selectGuide(@Field("orderID") int orderID,
                                           @Field("guideNumber") String guideNumber);
}
