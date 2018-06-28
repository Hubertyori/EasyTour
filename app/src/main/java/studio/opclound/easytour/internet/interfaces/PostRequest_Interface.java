package studio.opclound.easytour.internet.interfaces;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import studio.opclound.easytour.internet.translations.Translation;

public interface PostRequest_Interface {
    @POST("Register.php")
    @FormUrlEncoded
    Call<Translation> getCall(@Field("doctype") String json,
                              @Field("i") String targetSentence);

}
