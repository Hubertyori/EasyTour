package studio.opencloud.easytour21.CallBack;

import java.net.ConnectException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public abstract class BaseBack<T> implements Callback<BaseModel<T>> {

    public BaseBack() {
    }

    protected abstract void onSuccess(T t);

    protected void onFailed(int code, String msg) {
        System.out.println("请求失败**************************************");
        System.out.println(msg);
    }

    @Override
    public void onResponse(Call<BaseModel<T>> call, Response<BaseModel<T>> response) {
        BaseModel<T> baseModel = response.body();
        if (response.isSuccessful() && baseModel != null) {
            if (baseModel.code == 1) {
                onSuccess(baseModel.data);
            } else if (baseModel.code == 0) {
                //比如 做token无效统一处理
                onFailed(baseModel.code, baseModel.message);
            }
        } else {
            onFailed(response.code(), response.message());
        }
    }

    @Override
    public void onFailure(Call<BaseModel<T>> call, Throwable t) {
        if (t instanceof ConnectException) {
            //网络连接失败
            onFailed(403, t.getMessage());
        } else if (t instanceof HttpException) {
            HttpException ex = (HttpException) t;
            onFailed(ex.code(), ex.message());
        } else {
            onFailed(405, t.getMessage());
        }
    }
}
