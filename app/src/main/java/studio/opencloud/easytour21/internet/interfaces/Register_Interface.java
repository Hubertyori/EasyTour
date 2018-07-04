package studio.opencloud.easytour21.internet.interfaces;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import studio.opencloud.easytour21.internet.translations.Register_Translation;

public interface Register_Interface {
    @POST("Register.php")
    @FormUrlEncoded
    Call<Register_Translation> signUpInformation(@Field("username") String username,
                                                 @Field("password") String password,
                                                 @Field("tel") String tel,
                                                 @Field("imageInfo") String imageInfo);
    @Multipart
    @POST("Register.php")
    Call<Register_Translation> upLoadImg(@Part("username") String username,
                                         @Part("password") String password,
                                         @Part("tel") String tel,
                                         @Part("imageInfo") String imageInfo,
                                         @Part MultipartBody.Part file);
}
