package studio.opencloud.easytour21.internet.interfaces.pbinterface;

import java.util.List;

import studio.opencloud.easytour21.blogs.BowenListBean;

public interface PullRefreshList_Interface {
    void pullRefreshList_interface(List<BowenListBean.BowenListInfo> listContent);
}
