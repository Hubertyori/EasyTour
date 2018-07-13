package studio.opencloud.easytour21.internet.interfaces.user;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opencloud.easytour21.internet.datas.UserInformationData;
import studio.opencloud.easytour21.internet.translations.Login_Translation;

public interface GetGuideInforByID_Interface {
    //通过订单号获取导游全部信息
    @POST("GetGuideInfoByOrder.php")
    @FormUrlEncoded
    Call<Login_Translation> getGuideInforByID(@Field("orderID") int orderID);

    void processData(UserInformationData uid);
}
