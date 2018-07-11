package studio.opencloud.easytour21.internet.translations;

import java.util.List;

import studio.opencloud.easytour21.internet.datas.EvaluateGuideInfomationData;
import studio.opencloud.easytour21.internet.datas.GuideInfomation;

public class UserGetGuideInfo_Translation {
    //用户获取导游信息接口转译
    private int code;
    private String message;
    private List<EvaluateGuideInfomationData> data;

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

    public List<EvaluateGuideInfomationData> getData() {
        return data;
    }

    public void setData(List<EvaluateGuideInfomationData> data) {
        this.data = data;
    }
}
