package studio.opencloud.easytour21.internet.interfaces.user;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opencloud.easytour21.internet.translations.Guide_Information_Translation;
import studio.opencloud.easytour21.internet.translations.Guide_Order_Translation;
import studio.opencloud.easytour21.internet.translations.Login_Translation;
import studio.opencloud.easytour21.internet.translations.UserGetGuideInfo_Translation;

public interface UserGetGuideInfo_Interface {
    //用户获取导游信息
    @POST("UserGetAcceptGuides.php")
    @FormUrlEncoded
    Call<UserGetGuideInfo_Translation> signInInformation(@Field("orderID") int orderID);

}
