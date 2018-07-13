package studio.opencloud.easytour21.blogs;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 英俊的mrsail on 2018/7/10.
 */

public interface Collect_Interface {
    @POST("UserCollectBowen.php")
    @FormUrlEncoded
    Call<Collect_Translation> collect(@Field("bowenId") int bid, @Field("tel") String tel);
}
