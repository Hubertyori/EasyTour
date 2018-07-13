package studio.opencloud.easytour21.blogs;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 英俊的mrsail on 2018/7/9.
 */

public interface GetAllBowenList_Interface {
    @GET("GetAllBowen.php")
    Call<BowenListBean> getAllBowenList_CALL();
}
