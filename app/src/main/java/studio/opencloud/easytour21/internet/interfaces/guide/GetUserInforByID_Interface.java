package studio.opencloud.easytour21.internet.interfaces.guide;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opencloud.easytour21.internet.datas.GuideGetUserInfoByIDData;
import studio.opencloud.easytour21.internet.datas.UserInformationData;
import studio.opencloud.easytour21.internet.translations.GuideGetUserInfoByID_Translation;
import studio.opencloud.easytour21.internet.translations.UserGetGuideInfo_Translation;
import studio.opencloud.easytour21.users.UserInformation;

public interface GetUserInforByID_Interface {
    //通过订单号获取用户个人信息
    //用户获取导游信息
    @POST("GetUserInfoByOrder.php")
    @FormUrlEncoded
    Call<GuideGetUserInfoByID_Translation> getUserInforByID_Interface(@Field("orderID") int orderID);

    void processData(GuideGetUserInfoByIDData uid);
}
