package studio.opencloud.easytour21.internet.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opencloud.easytour21.internet.translations.ResetPassWord_Translation;
public interface ResetPassWord_Interface {
    @POST("Register.php")
    @FormUrlEncoded
    Call<ResetPassWord_Translation> resetPassWord(@Field("tel") String tel,
                                                  @Field("password") String password);
}
