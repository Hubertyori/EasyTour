package studio.opencloud.easytour21.blogs;

import java.util.List;

/**
 * Created by moos on 2018/4/20.
 */

public class CommentBean {
    private int code;
    private String message;
    private List<CommentDetailBean> data;

    public class Comments
    {
        String content;
        String userNickname;
        String time;
        String userHeadIcon;



        public String getContent() {
            return content;
        }

        public String getUserNickname() {
            return userNickname;
        }

        public String getTime() {
            return time;
        }

        public String getUserHeadIcon() {
            return userHeadIcon;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setUserNickname(String userNickname) {
            this.userNickname = userNickname;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setUserHeadIcon(String userHeadIcon) {
            this.userHeadIcon = userHeadIcon;
        }
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<CommentDetailBean> getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<CommentDetailBean> data) {
        this.data = data;
    }
}
