package studio.opencloud.easytour21.internet.interfaces.pbinterface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opencloud.easytour21.internet.translations.ResetPassWord_Translation;
//重置密码接口
public interface ResetPassWord_Interface {
    @POST("UserForgetPassword.php")
    @FormUrlEncoded
    Call<ResetPassWord_Translation> resetPassWord(@Field("tel") String tel,
                                                  @Field("newPassword") String password);
}
