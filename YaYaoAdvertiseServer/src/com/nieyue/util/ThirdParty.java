package com.nieyue.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 第三方应用的常量
 * @author yy
 *
 */
public class ThirdParty {
	
	/**
	 * 读取 properties
	 * @param key
	 * @return
	 */
	public static String GetValueByKey(String key){
		 Locale locale = Locale.getDefault();  
         ResourceBundle localResource = ResourceBundle.getBundle("config/thirdParty", locale);
         try {
        	 String value = localResource.getString(key); 
        	 return value;
			
		} catch (Exception e) {
			return null;
		}
	}
	public static void main(String[] args) {
		System.out.println(ThirdParty.GetValueByKey("SUCCESS"));
		//System.out.println(SUCCESS);
	}
	/**
	 * test appid
	 */
	public static String WEIXIN_TEST_APPID="WEIXIN_TEST_APPID";
	/**
	 *test SECRET 
	 */
	public static String WEIXIN_TEST_SECRET="WEIXIN_TEST_SECRET";
	/**
	 * YaYao appid
	 */
	public static String WEIXIN_YAYAO_APPID="WEIXIN_YAYAO_APPID";
	/**
	 *YaYao SECRET 
	 */
	public static String WEIXIN_YAYAO_SECRET="WEIXIN_YAYAO_SECRET";
	/**
	 *YaYao MCH_ID 
	 */
	public static String WEIXIN_YAYAO_MCH_ID="WEIXIN_YAYAO_MCH_ID";
	/**
	 *YaYao api 
	 */
	public static String WEIXIN_YAYAO_API="WEIXIN_YAYAO_API";
	/**
	 * 放肆约appid
	 */
	public static String WEIXIN_FANGSIYUE_APPID="WEIXIN_FANGSIYUE_APPID";
	/**
	 *放肆约SECRET 
	 */
	public static String WEIXIN_FANGSIYUE_SECRET="WEIXIN_FANGSIYUE_SECRET";
	/**
	 *放肆约  MCH_ID 
	 */
	public static String WEIXIN_FANGSIYUE_MCH_ID="WEIXIN_FANGSIYUE_MCH_ID";
	/**
	 *放肆约api 
	 */
	public static String WEIXIN_FANGSIYUE_API="WEIXIN_FANGSIYUE_API";
	/**
	 *阿里大鱼APPKEY
	 */
	public static String ALIBABA_SMS_APPKEY="ALIBABA_SMS_APPKEY";
	/**
	 *阿里大鱼APPSECRET
	 */
	public static String ALIBABA_SMS_APPSECRET="ALIBABA_SMS_APPSECRET";
	/**
	 *阿里大鱼 模板id
	 */
	public static String ALIBABA_SMS_TEMPLATE_CODE_ID="ALIBABA_SMS_TEMPLATE_CODE_ID";
	/**
	 *阿里大鱼 签名 
	 */
	public static String ALIBABA_SMS_SIGN_NAME="ALIBABA_SMS_SIGN_NAME";
	/**
	 *阿里百川APPKEY
	 */
	public static String ALIBABA_IM_APPKEY="ALIBABA_IM_APPKEY";
	/**
	 *阿里百川APPSECRET
	 */
	public static String ALIBABA_IM_APPSECRET="ALIBABA_IM_APPSECRET";
	
}
