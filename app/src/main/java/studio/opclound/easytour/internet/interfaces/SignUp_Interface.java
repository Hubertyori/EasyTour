package studio.opclound.easytour.internet.interfaces;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opclound.easytour.internet.translations.SignUp_Trainslation;

public interface SignUp_Interface {
    @POST("Register.php")
    @FormUrlEncoded
    Call<SignUp_Trainslation> signUpInformation(
            @Field("pic") MultipartBody.Part file);
}
