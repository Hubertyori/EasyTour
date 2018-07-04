package studio.opencloud.easytour21.internet.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opencloud.easytour21.internet.translations.Register_Translation;

public interface RegisterAsGuide_Interface {
    @POST("BecomeGuide.php")
    @FormUrlEncoded
    Call<Register_Translation> signUpInformation(@Field("tel") String tel,
                                                 @Field("realname") String realname,
                                                 @Field("IDnumber") String IDnumber,
                                                 @Field("guidenumber") String guidenumber,
                                                 @Field("servercity") String servercity);
}
//用户信息就该的返回数据可以用注册的翻译类来接收。