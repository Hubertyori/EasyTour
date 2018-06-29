package studio.opclound.easytour.internet.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import studio.opclound.easytour.internet.translations.Translation;

public interface  GetRequest_Interface {
    @GET("translate")
    @FormUrlEncoded
    Call<Translation> getCall(@Field("doctype") String json,
                              @Field("i") String targetSentence);
//    Call<ResponseBody> getCall(@Field("doctype") String json, @Field("i") String targetSentence);
    // 注解里传入 网络请求 的部分URL地址

    // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
    // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
    // getCall()是接受网络请求数据的方法
}