package studio.opencloud.easytour21.internet.interfaces.pbinterface;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import studio.opencloud.easytour21.blogs.writeBlogBean;

/**
 * Created by 英俊的mrsail on 2018/7/9.
 */

public interface submitBlog_Interface {
    @POST("ReleaseBowen.php")
    @FormUrlEncoded
    Call<writeBlogBean> submitBlog_CALL(@Field("tel") String Tel, @Field("title") String Title, @Field("content") String Content,
                                        @Field("time") String Time, @Field("imageInfo") String imageInfo);
    @Multipart
    @POST("ReleaseBowen.php")
    Call<writeBlogBean> submitBlogWithPic_CALL(@Part("tel") String Tel, @Part("title") String Title, @Part("content") String Content,
                                        @Part("time") String Time, @Part("imageInfo") String imageInfo,@Part MultipartBody.Part file);
}
