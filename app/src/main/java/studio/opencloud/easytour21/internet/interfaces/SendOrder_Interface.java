package studio.opencloud.easytour21.internet.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opencloud.easytour21.internet.translations.Register_Translation;

public interface SendOrder_Interface {
    @POST("ReleaseOrder.php")
    @FormUrlEncoded
    Call<Register_Translation> signUpInformation(@Field("tel") String tel,
                                                 @Field("place") String place,
                                                 @Field("date") String date,
                                                 @Field("numberOfPeople") int numberOfPeople,
                                                 @Field("description") String description);
}
