package studio.opencloud.easytour21.internet.interfaces.pbinterface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opencloud.easytour21.internet.translations.Guide_Bgein_Order_Translation;
import studio.opencloud.easytour21.internet.translations.Guide_Order_Translation;
import studio.opencloud.easytour21.internet.translations.User_Begin_Order_Translation;
import studio.opencloud.easytour21.internet.translations.User_Order_Translation;
//查询订单界面
public interface QuerryOrder_Interface {
    //用户获取订单
    @POST("UserGetIdleOrders.php")
    @FormUrlEncoded
    Call<User_Order_Translation> getUserIdleOrders(@Field("tel") String tel);
    @POST("UserGetAcceptedOrders.php")
    @FormUrlEncoded
    Call<User_Order_Translation> getUserAcceptedOrders(@Field("tel") String tel);
    @POST("UserGetBeginOrders.php")
    @FormUrlEncoded
    Call<User_Begin_Order_Translation> getUserBeginOrders(@Field("tel") String tel);
    @POST("UserGetFinishedOrders.php")
    @FormUrlEncoded
    Call<User_Order_Translation> getUserFinishedOrders(@Field("tel") String tel);


    //导游获取订单
    @POST("GuideGetNearbyOrders.php")
    @FormUrlEncoded
    Call<Guide_Order_Translation> getGuideNearByOrders(@Field("guideIDNumber") String IDnumber);
    @POST("GuideGetAcceptedOrders.php")
    @FormUrlEncoded
    Call<Guide_Order_Translation> getGuideAcceptedOrders(@Field("guideIDNumber") String IDnumber);
    @POST("GuideGetBeginOrders.php")
    @FormUrlEncoded
    Call<Guide_Order_Translation> getGuideBeginOrders(@Field("guideIDNumber") String IDnumber);
    @POST("GuideGetFinishedOrders.php")
    @FormUrlEncoded
    Call<Guide_Order_Translation> getGuideFinishedOrders(@Field("guideIDNumber") String IDnumber);
}
