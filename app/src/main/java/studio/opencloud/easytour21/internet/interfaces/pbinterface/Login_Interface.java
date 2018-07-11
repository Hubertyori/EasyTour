package studio.opencloud.easytour21.internet.interfaces.pbinterface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opencloud.easytour21.internet.translations.Login_Translation;
import studio.opencloud.easytour21.internet.translations.Register_Translation;

//获取登陆信息
public interface Login_Interface {
    @POST("Login.php")
    @FormUrlEncoded
    Call<Login_Translation> signInInformation(@Field("tel") String tel,
                                                 @Field("password") String password);
}
