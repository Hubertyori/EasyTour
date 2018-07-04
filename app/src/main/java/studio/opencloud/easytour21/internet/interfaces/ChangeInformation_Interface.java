package studio.opencloud.easytour21.internet.interfaces;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import studio.opencloud.easytour21.internet.translations.Register_Translation;
import studio.opencloud.easytour21.internet.translations.ResetPassWord_Translation;

public interface ChangeInformation_Interface {
    @POST("UpdateUserInfo.php")
    @Multipart
    Call<Register_Translation> upDateUserInfo(@Part("tel") String tel,
                                                  @Part("password") String password,
                                                  @Part("nickname") String nickname,
                                                  @Part("sex") String sex,
                                                  @Part("introduce") String introduce,
                                                  @Part("imageInfo") String imageInfo);
    @Multipart
    @POST("UpdateUserInfo.php")
    Call<Register_Translation> upLoadImg(@Part("tel") String tel,
                                         @Part("password") String password,
                                         @Part("nickname") String nickname,
                                         @Part("sex") String sex,
                                         @Part("introduce") String introduce,
                                         @Part("imageInfo") String imageInfo,
                                         @Part MultipartBody.Part file);
}
//用户信息就该的返回数据可以用注册的翻译类来接收。