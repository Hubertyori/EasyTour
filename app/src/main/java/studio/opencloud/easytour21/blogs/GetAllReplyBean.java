package studio.opencloud.easytour21.blogs;

import java.util.List;

/**
 * Created by 英俊的mrsail on 2018/7/11.
 */

public class GetAllReplyBean {
    int code;
    String message;
    List<CommentDetailBean> data;

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

    public List<CommentDetailBean> getData() {
        return data;
    }

    public void setData(List<CommentDetailBean> data) {
        this.data = data;
    }
}
