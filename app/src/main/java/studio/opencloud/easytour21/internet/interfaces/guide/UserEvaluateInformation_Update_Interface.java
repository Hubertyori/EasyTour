package studio.opencloud.easytour21.internet.interfaces.guide;

import java.util.List;

import studio.opencloud.easytour21.internet.datas.EvaluateUserInfoData;

public interface UserEvaluateInformation_Update_Interface {
    //导游获取游客数据用来评价游客
    void updateUserInformation(List<EvaluateUserInfoData> guideInfo);
}
