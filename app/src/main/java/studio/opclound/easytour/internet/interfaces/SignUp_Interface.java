package studio.opclound.easytour.internet.interfaces;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import studio.opclound.easytour.internet.translations.SignUp_Trainslation;

public interface SignUp_Interface {
    @POST("Register.php")
    @FormUrlEncoded
    Call<SignUp_Trainslation> signUpInformation(@Field("username") String username,
                                                @Field("password") String password,
                                                @Field("tel") String tel,
                                                @Field("imageInfo") String imageInfo);
    @Multipart
    @POST("Register.php")
    Call<SignUp_Trainslation> upLoadImg(@Part("username") String username,
                                        @Part("password") String password,
                                        @Part("tel") String tel,
                                        @Part("imageInfo") String imageInfo,
                                        @Part MultipartBody.Part file);
}
