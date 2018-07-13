package studio.opencloud.easytour21.blogs;

/**
 * Created by moos on 2018/4/20.
 */

public class CommentDetailBean {
    String content;
    String usernickname;
    String time;
    String userHeadIcon;

    public CommentDetailBean(String userNickname, String content, String time, String userHeadIcon)
    {
        this.usernickname = userNickname;
        this.content=content;
        this.time = time;
        this.userHeadIcon = userHeadIcon;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserHeadIcon() {
        return userHeadIcon;
    }

    public void setUserHeadIcon(String userHeadIcon) {
        this.userHeadIcon = userHeadIcon;
    }
}
