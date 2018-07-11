package studio.opencloud.easytour21.internet.interfaces.guide;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import studio.opencloud.easytour21.internet.translations.Register_Translation;
import studio.opencloud.easytour21.internet.translations.ResetPassWord_Translation;

public interface ChangeGuideInformation_Interface {
    //修改导游个人信息
    @POST("UpdateGuideInfo.php")
    @FormUrlEncoded
    Call<Register_Translation> upDateGuideInfo(@Field("IDNumber") String IDNumber,
                                                 @Field("servercity") String servercity);
//用户信息就该的返回数据可以用注册的翻译类来接收。
}
