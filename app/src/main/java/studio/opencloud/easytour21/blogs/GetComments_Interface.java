package studio.opencloud.easytour21.blogs;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 英俊的mrsail on 2018/7/10.
 */

public interface GetComments_Interface {
    @POST("GetBowenComments.php")
    @FormUrlEncoded
    Call<GetAllReplyBean> getComments_CALL(@Field("bowenId") int bid);
}
