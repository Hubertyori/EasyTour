package studio.opencloud.easytour21.blogs;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 英俊的mrsail on 2018/7/11.
 */

public interface MakeComment_Interface {
    @POST("UserComments.php")
    @FormUrlEncoded
    Call<MakeCommentResultBean> MakeComment_CALL(@Field("tel") String tel, @Field("bowenId") int bowenid, @Field("content") String content, @Field("time") String time);
}
