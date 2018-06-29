package studio.opclound.easytour.internet.interfaces;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import studio.opclound.easytour.internet.translations.SignIn_Trainslation;


public interface SignIn_Interface {
    @POST("Login.php")
    @FormUrlEncoded
    Call<SignIn_Trainslation> signInInformation(@Field("tel") String tel,
                                                @Field("password") String password);

}
