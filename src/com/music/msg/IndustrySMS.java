package com.music.msg;

import java.net.URLEncoder;
import java.util.Random;

/**
 * 验证码通知短信接口
 * 
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 *
 */
public class IndustrySMS {

	private String msg = "";

	private static Random r = new Random();

	private static String operation = "/industrySMS/sendSMS";

	private static String accountSid = Config.ACCOUNT_SID;
	
	private String smsContent;

	/**
	 * 验证码通知短信
	 */
	public String execute(String to) {
		
		for (int i = 0; i < 6; i++) {
			msg = msg + r.nextInt(9);
		}

		smsContent = "【欧米】您的验证码为" + msg + "，请于2分钟内正确输入，如非本人操作，请忽略此短信。";
		String tmpSmsContent = null;
		try {
			tmpSmsContent = URLEncoder.encode(smsContent, "UTF-8");
		} catch (Exception e) {

		}
		String url = Config.BASE_URL + operation;
		String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + tmpSmsContent
				+ HttpUtil.createCommonParam();
	
		// 提交请求
		String result = HttpUtil.post(url, body);
		System.out.println("result:" + System.lineSeparator() + result);
		
		return msg;
	}
}
