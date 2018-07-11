package studio.opencloud.easytour21.internet.interfaces.guide;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opencloud.easytour21.internet.translations.EvaluateUser_Translation_Interface;
import studio.opencloud.easytour21.internet.translations.Guide_Information_Translation;

public interface GetUserInfo_Interface {
    //导游获取已接单用户的信息来评价
    @POST("UserGetAcceptGuides.php")
    @FormUrlEncoded
    Call<EvaluateUser_Translation_Interface> signInInformation(@Field("orderID") int orderID);
}
