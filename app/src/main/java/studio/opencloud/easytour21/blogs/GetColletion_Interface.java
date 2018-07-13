package studio.opencloud.easytour21.blogs;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetColletion_Interface {
    @POST("UserGetCollectBowen.php")
    @FormUrlEncoded
    Call<BowenListBean> collect(@Field("tel") String tel);

}
