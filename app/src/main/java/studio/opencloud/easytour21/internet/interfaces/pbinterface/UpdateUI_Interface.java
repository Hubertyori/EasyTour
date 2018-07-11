package studio.opencloud.easytour21.internet.interfaces.pbinterface;

import java.util.List;

import studio.opencloud.easytour21.internet.datas.GuideOrderData;
import studio.opencloud.easytour21.internet.datas.UserBeginOrderData;
import studio.opencloud.easytour21.internet.datas.UserOrderData;
//更新list订单界面接口
public interface UpdateUI_Interface {
    //更新Idel游客订单
    void updateIdleUserOrderUI(List<UserOrderData> uod);
    //更新Idel导游订单
    void updateIdleGuideOrderUI(List<GuideOrderData> uod);
    //更新Accepted游客订单
    void updateAcceptedUserOrderUI(List<UserOrderData> uod);
    //更新Accepted导游订单
    void updateAcceptedGuideOrderUI(List<GuideOrderData> uod);
    //更新Begin游客订单
    void updateBeginUserOrderUI(List<UserBeginOrderData> uod);
    //更新Begin导游订单
    void updateBeginGuideOrderUI(List<GuideOrderData> uod);
    //更新Finished游客订单
    void updateFinishedUserOrderUI(List<UserOrderData> uod);
    //更新Finished导游订单
    void updateFinishedGuideOrderUI(List<GuideOrderData> uod);
}
