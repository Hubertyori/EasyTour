package studio.opencloud.easytour21.internet.interfaces.user;

import java.util.List;

import studio.opencloud.easytour21.internet.datas.EvaluateGuideInfomationData;
import studio.opencloud.easytour21.internet.datas.GuideInfomation;

public interface GuideEvaluateInformation_Update_Interface {
    //用户评价导游，获取导游个人信息
    void updateIdleUserOrderUI(List<EvaluateGuideInfomationData> guideInfo);
}
