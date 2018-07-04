package studio.opencloud.easytour21.internet.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opencloud.easytour21.internet.translations.Login_Translation;


public interface Login_Interface {
    @POST("Login.php")
    @FormUrlEncoded
    Call<Login_Translation> signInInformation(@Field("tel") String tel,
                                              @Field("password") String password);

}
