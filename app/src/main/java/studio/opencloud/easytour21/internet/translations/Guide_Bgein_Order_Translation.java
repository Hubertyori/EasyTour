package studio.opencloud.easytour21.internet.translations;

import java.util.List;

import studio.opencloud.easytour21.internet.datas.GuideBeginOrderData;
import studio.opencloud.easytour21.internet.datas.UserBeginOrderData;
import studio.opencloud.easytour21.internet.datas.UserOrderData;

public class Guide_Bgein_Order_Translation {
    //导游BeginOrder转译
//用户BeginOrder转译
    private int code;
    private String message;
    private List<GuideBeginOrderData> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<GuideBeginOrderData> getData() {
        return data;
    }

    public void setData(List<GuideBeginOrderData> data) {
        this.data = data;
    }
}
