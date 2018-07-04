package studio.opencloud.easytour21.miaodi.httpApiDemo.common.httpApiDemo;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.net.URLEncoder;

import studio.opencloud.easytour21.miaodi.httpApiDemo.common.httpApiDemo.common.Config;
import studio.opencloud.easytour21.miaodi.httpApiDemo.common.httpApiDemo.common.HttpUtil;

/**
 * 验证码通知短信接口
 * 
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 *
 */
public class IndustrySMS
{
	private static final String String = null;

	private static String operation = "/industrySMS/sendSMS";

	private static String accountSid = Config.ACCOUNT_SID;
	private static String auth_token = Config.AUTH_TOKEN;
	private static String to = "15523352924";
	private static String smsContent = "【易游】尊敬的用户，您的验证码为123456，请于10分钟内正确输入，如非本人操作，请忽略此短信。";
	private static String templateid = "339517518";
	private static String timestamp = "";
			
	
	/**
	 * 验证码通知短信
	 */
	
	public static String execute()
	{
		int num = VerifyingCodeGenerator();
		String code = Integer.toString(num);
		smsContent = "【易游】尊敬的用户，您的验证码为"+code+"，请于60分钟内正确输入，如非本人操作，请忽略此短信。";
		Date day=new Date();    

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); 
		timestamp = df.format(day);
		String tmpSmsContent = null;
	    try{
	      tmpSmsContent = URLEncoder.encode(smsContent, "UTF-8");
	    }catch(Exception e){
//	      
	    }
	    String url = Config.BASE_URL + operation;
	    String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + smsContent +
	    		"&templateid" +templateid
	        + HttpUtil.createCommonParam();
//
//	    // 提交请求
	    String mresult = HttpUtil.post(url, body);
	    System.out.println("result:" + System.lineSeparator() + mresult);
		
	    System.out.println("phone:" + timestamp);
	    return code;
	}
	/**
	 * VerifyingCodeGenerator 
	 * 
	 * @param 
	 * @return int VerifyingCode number
	 */
	public static int VerifyingCodeGenerator() {
		int num = (int) ((Math.random() * 9 + 1) * 100000);
		return num;
	}

}
