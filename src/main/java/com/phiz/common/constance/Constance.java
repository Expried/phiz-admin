/**
 * <pre>
 * <b>Project:hzfare-core</b>
 * <b>FiledName:com.hz.fare.common.utils.Constance.java</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月29日 下午7:29:22</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月29日 下午7:29:22   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
package com.phiz.common.constance;

import org.springframework.beans.factory.annotation.Value;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.utils.Constance</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月29日 下午7:29:22</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月29日 下午7:29:22   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
public abstract class Constance {

	@Value("${idgenerate.workerId}")
	public static Long WORKERID = 0l;

	@Value("${idgenerate.datacenterId}")
	public static Long DATACENTERID = 0l;
	
	/** 
	 * 渠道标识号
	*/ 
	public static final String CID = "cid";
	
	/** 
	 * 全匹配
	*/ 
	public static final String ALL_MATCH = "*-*";
	
	/** 
	 * 逗号拼接符
	*/ 
	public static final String SEPARATOR = ",";
	
	
	public static final String SEPARATOR1= "-";
	
	public static final String SEPARATOR3 = ";";
	
	public static final String SEPARATOR2 = "/";
	
	public static final String SEPARATOR4 = "_";
	
	public static final String CACHE_KEY_FORMAT = "{0}"+SEPARATOR1+"{1}";
	
	public static final String ALL_MATCH_1 = "*";
	
	public static final String HEADER_CONTENT_TYPE_KEY = "Content-Type";
	
	public static final String HEADER_ACCEPT_ENCODING_KEY = "Accept-Encoding";
	
	public static final String HEADER_CONTENT_TYPE_JSON_VAL = "application/json";
	
	public static final String HEADER_CONTENT_TYPE_XML_VAL = "text/xml;charset=utf-8";
	
	public static final String HEADER_CONTENT_TYPE_XWWWFORMURLENCODED_VAL = "application/x-www-form-urlencoded";
	
	public static final String HEADER_CONTENT_TYPE_PLAN_VAL = "text/plain";
	
	public static final String HEADER_ACCEPT_ENCODING_GZIP_VAL = "gzip, deflate";
	
	public static final String SOAP_ACTION = "SOAPAction";
	
	public static final String HOST = "Host";
	
	public static final String HOST_VALUE = "nodeD2.test.webservices.amadeus.com";
	
	public static final String HEADER_CONTENT_TYPE_APPLICATION_XML_VAL = "application/xml";
	
	/** 
	 * 最小出行人数 默认1
	*/ 
	public static final int DEFAULT_MINPASSENGERCOUNT = 1;
	
	/** 
	 * 最大出行人数 默认9
	*/ 
	public static final int DEFAULT_MAXPASSENGERCOUNT = 9;
	
	/** 
	 * 0 表示儿童不算/ 1 表示 1 个儿童就算 1 个人/ 2 -表示 2 个儿童算 1 个人【无返回,默认为 1】
	*/ 
	public static final int DEFAULT_GVCHILDRULE   = 1;
	
	
	/** 
	 * 公布运价 运价类型
	*/ 
	public static final String PUB_PRODUCTTYPE = "PUB";
	
	/** 
	 * 默认币种  人民币
	*/ 
	public static final String CURRENCY_CNY = "CNY";
	
	public static final String CURRENCY_HKD = "HKD";


	/** 
	 * 默认币种  人民币
	*/ 
	public static final String CURRENCY_RMB = "RMB";
	
	/** 
	 * 行李单位 PC
	*/ 
	public static final String PC = "pc";
	
	/** 
	 * 行李单位 KG
	*/ 
	public static final String KG = "kg";
	
	
	public static final String UP_PC  = "PC";
	
	public static final String UP_KG = "KG";
	
	/** 
	 * 不允许退票
	*/ 
	public static final int NO_HASREFUND = 0;
	
	
	/** 
	 * 部分未使用 不允许退票
	*/ 
	public static final int NO_PARTREFUND = 0;
	
	
	/** 
	 * 不允许改期
	*/ 
	public static final int NO_HASENDORSE = 0;
	
	
	/** 
	 * 部分未使用 不允许改期
	*/ 
	public static final int NO_PARTENDORSE = 0;
	
	/** 
	 * 不支持签转
	*/ 
	public static final int NO_ENDORSEMENT = 0;
	
	
	/** 
	 * 没有noshow 规则
	*/ 
	public static final int NO_HASNOSHOW = 0;
	
	
	/** 
	 * 有行李提供
	*/ 
	public static final int HASBAGGAGE = 1;
	
	/** 
	 * 没有行李提供
	*/ 
	public static final int NO_HASBAGGAGE = 0;
	
	/** 
	 * 行李节点
	*/ 
	public static final String BAGGAGE = "baggage";
	
	/** 
	 * 成人
	*/ 
	public static final String ADT = "ADT";
	
	public static final String ADT_STR = "成人";
	
	/** 
	 * 儿童
	*/ 
	public static final String CHD = "CHD";
	
	public static final String CHD_STR = "儿童";
	
	public static final String CONSIGN_BAG_STR = "可 免费托运行李:";
	
	public static final String CARRY_BAG_STR = "可携带 手提行李:"; 
	
	
	public static final String BAG_STR = "{0},第{1}段：{2}";
	
	//public static final String RET_BAG_STR = "回程第{0}段:{1}";
	
	/** 
	 * 订票来源  GDS
	*/ 
	public static final String RESERVATIONTYPE_GDS = "GDS";
	
	/** 
	 * 默认出票时间
	*/ 
	public static final int DEFAULT_TICKETTIMELIMIT = 1440; 
	
	/** 
	 * 急速出票时间
	*/ 
	public static final int QUICK_TICKETTIMELIMIT = 50;
	
	
	/** 
	 * RSA 加密  私鑰
	*/ 
	public static final String PRIVATE_KEY = "MIIEuQIBADANBgkqhkiG9w0BAQEFAASCBKMwggSfAgEAAoIBACBwQZRTJeDDs40T4cm1LYcPF76bx1hNDLZNaXVfrmE+LBF0XkJltI9J+Mpudl41zBWbtPOckDnh+mew/4rvIHV8uD+Bcv8jzfNQpkN6SFgSe+hXXcBeaIlrYYGcQhtbVmUDv8p9+mqvpLYMSporKUDfLXXTy2mvMMKXkHi5T2RAi6+dKqf3GQ/P46Zp9PyR/wJBIouw6gFreDc6DRZTYk3DQa414RSyYjQsWwScIbNv7dM/m/ngIqI8qNvg/pAf3GGOXgOHvfG1yBMESpBllC8mzvUpHjkiLhUZUA/uyhYyAu15qYdWKfd0RdgrYoyUD6fIRWptklFAmsWwgDMDPKkCAwEAAQKCAQAaoU2VIPWVodPDW7Ee0JhBqkhJ+MUcXpSzDLYo35XeyxWOdsPzKl+iyo1uUmTmTq/uhVd2Lu/QNQ5HFe2T91/kU7FGoVTw1En9GBaGitrdQr9qLF8Qz/Bt8DOqHce/gapZX0ySRf3Qm8VSlqCgFBLX6N//PRJe/q2+8oJuOWNN33/heR+IWuOwlSqiYp3OtL8HuOmzGKUFHZnHtcijLWdvj/Zd9YAdEb3AdMQjA5XpDz6I0cadnaw3SDD/044A+pewomcxb2M84fp1AomFXsSe0fWmXLv5VAfBXMcHWnGEYkYafkX7YzkNk5lnanc8Hgn836+gsWlfxrsL08t1NqSpAoGAamVQoP1aKAYVd42PoOx9Otoi4qzONBLMoQxFQlbfbXhNwgQvG08aF+FEHRIAAw6vBQdYlMYzZotAd3cthmlD0zJow1u99L57vjHO12cf6SpltR4MUerZUV/KVjHlm2VDfqQbjsMW0lutvriBOuQbe2RhOr44DDBQYnIKscttfFsCgYBODPYOw9RWDoyHTAkU1tjVV09lEfLlwMbyoDWk+QlNrwGUfvJK/5ae/Xd6yXPtepmb0XfSjPcsMCncVf7gvmU68u+0RjbNZUIN9SGf7TthgaxJUOHXr+TgwW4JHxeiNNuzV2cHVfTYj6OWqypzmwJAhjzQrZfxJOf8BNHNynDKSwKBgEyjvni5BwBdoYkEx7k7oNmL2CVAJSkNgalglWM8AKVyS1C+gtBBIqKg+deW57tMEHam3AAO5WewVze2+Wwg12KCpURUCXkK3UgbyMsQ1cO3bpwQkP9RFEmxnqaIOsS9JOlMfS1RCldtArKnZS9usP0pSYBvFSByjPTdASchSjSRAoGACGagvWoamsCqI43tlE6jvCLPquNJr60WPNs9quobND9X/jHDxorIHYjOqWqxjagajIj5SrJdkGYGu26OJoaXwsaPQmokT+tMNtBhtCMGtzIL50BLbrnB+ucW/uhX1N+3nhAUSNfZqM2dIASAZz0R+vv348YrioscZSXnpyzZAw0CgYBgfAqYRBKnaA7XT4xJXWJHBr36QX+Y56mL3Tcjc5wblZPnk0tuYOP9L+ZB07GVzi6s77o6w1hdY/LcPFuCjRtZcNoDGnwZ0QYgj81+QtPoB/DfTwazGdBtq0gSh8+W8kfLzFYpjytRvE6PQyypM+A+aJHa4mqspBtwRaNWHblk4Q==";
	
	/** 
	 * RSA 加密公鑰
	*/ 
	public static final String PUBLIC_KEY = "MIIBITANBgkqhkiG9w0BAQEFAAOCAQ4AMIIBCQKCAQAgcEGUUyXgw7ONE+HJtS2HDxe+m8dYTQy2TWl1X65hPiwRdF5CZbSPSfjKbnZeNcwVm7TznJA54fpnsP+K7yB1fLg/gXL/I83zUKZDekhYEnvoV13AXmiJa2GBnEIbW1ZlA7/Kffpqr6S2DEqaKylA3y1108tprzDCl5B4uU9kQIuvnSqn9xkPz+OmafT8kf8CQSKLsOoBa3g3Og0WU2JNw0GuNeEUsmI0LFsEnCGzb+3TP5v54CKiPKjb4P6QH9xhjl4Dh73xtcgTBEqQZZQvJs71KR45Ii4VGVAP7soWMgLteamHVin3dEXYK2KMlA+nyEVqbZJRQJrFsIAzAzypAgMBAAE=";
	
	/** 
	 * 默认报销凭证类型 3= 不提供 凭证
	*/ 
	public static final int INVOICE_DEFAULT = 3;
	
	/** 
	 * 行李字符串格式化 单位 PC ,KG
	*/ 
	public static final String BAG_FORMAT = "{0}"+PC+",{1}"+KG;
	
	
	public static final String BAG_FORMAT_UP = "{0}"+UP_PC+",{1}"+UP_KG;
	
	
	/** 
	 * 行李字符串 格式化  单位 P,KG
	*/ 
	public static final String BAG_FORMAT_P = "{0}P,{1}"+KG;

	
	
	public static final String BAG_FORMAT_MSG = "";
	
	/** 
	 * OTA 平台 配置锁 节点名称
	*/ 
	public static final String OTA_CONFIG_PLAT_LOCK_NAME = "OTA_CONFIG_PLAT_LOCK_{0}";
	
	
	/** 
	 * 供应商 平台配置锁节点
	*/ 
	public static final String PROVIDER_CONFIG_PLAT_LOCK_NAME = "PROVIDER_CONFIG_PLAT_LOCK_{0}";
	
	
	/** 
	 * 【公布运价强校验】 产品类型：0 旅行套餐 /1 商务优选/2 特惠推荐 新上线供应商请赋值为0
	*/ 
	public static final String DEFAULT_PLANCATEGORY = "0";
	/**
	 * 默认婴儿票价
	 */
	public static final int DEFAULT_INFANTPRICE=0;
	
	/**
	 * 默认婴儿税费
	 */
	public static final int DEFAULT_INFANTTAX=0;
	
	/** 
	 * 报销凭证：T 行程单 / F 发票 / E 电子发票
	*/ 
	public static final String DEFAULT_INVOICETYPE = "F";
	
	/** 
	 * 最短停留时间【单位是天】【无返回，默认为0，涉及到改期】
	*/ 
	public static final String DEFAULT_MINSTAY = "1";
	
	
	/** 
	 * 最长停留时间【单位是天】【无返回，默认为0】
	*/ 
	public static final String DEFAULT_MAXSTAY = "360";
	
	
	/** 
	 * 值机服务状态 0：不支持在线值机 / 1：支持在线值机 / 2：必须在线值机（此选项代表供应商打包值机价格到机票）
	*/ 
	public static final int DEFAULT_CHECKINSERVICESTATUS = 1;
	
	/** 
	 * 退票标识 T：不可退 H：有条件退 F：免费退 E：按航司客规【公布运价专用】
	*/ 
	public static final String DEFAULT_REFUND_CHANGE_STATUS = "T";
	
	/** 
	 * 退票标识 T：不可退 H：有条件退 F：免费退 E：按航司客规【公布运价专用】
	*/ 
	public static final String REFUND_CHANGE_STATUS_E = "E";
	
	
	/** 
	 * 退票标识 T：不可退 H：有条件退 F：免费退 E：按航司客规【公布运价专用】
	*/ 
	public static final String REFUND_CHANGE_STATUS_H = "H";
	
	
	/** 
	 * 儿童
	*/ 
	public static final String CHD_PASSENGERTYPE = "0";
	
	/** 
	 * 成人
	*/ 
	public static final String ADT_PASSENGERTYPE = "1";
	
	/** 
	 * 婴儿
	*/ 
	public static final String INFANT_PASSENGERTYPE = "2";

	
	/** 
	 * 是否允许NoShow退票 T：不可退； H：有条件退；F：免费退；E：按航司客规为准
	*/ 
	public static final String NO_REFNOSHOW = "T";
	
	/** 
	 * 改期标识 T：不可改期  H：有条件改期  F：免费改期   E：按航司客规【公布运价专用】
	*/ 
	public static final String NO_CHANGE = "T";
	
	
//	0-退票，1-NoShow退票，2-改期 ，3-NoShow改期
	
	/** 
	 * 退票类型
	*/ 
	public static final int REFUND_TYPE_VAL = 0;
	
	/** 
	 * noshow 退票类型 
	*/ 
	public static final int NOSHOW_REFUND_TYPE_VAL = 1;
	
	/** 
	 * 改期  类型
	*/ 
	public static final int CHANGE_TYPE_VAL = 2;
	
	/** 
	 * nowshow 改期 类型
	*/ 
	public static final int  NOSHOW_TYPE_VAL = 3;
	
	
//	0-全程未使用 ，1-去程已使用 ，2-两种情况均适用	2
	
	/** 
	 * 全程未使用
	*/ 
	public static final int  ALL_NO_USE = 0;
	
	/** 
	 * 去程已使用
	*/ 
	public static final int TRIP_USE = 1;
	
	/** 
	 * 退改签匹配模式  模糊匹配
	*/ 
	public static final int DEFAULT_FARERULEMATCHMODE = 1;
	
	
	/** 
	 * 去程
	*/ 
	public static final int DIRECTION_GO = 0;
	
	/** 
	 * 回程
	*/ 
	public static final int DIRECTION_RETURN = 1;
	
	//乘客国籍或地区类型:0 全部/1 适用/2 不适用 ，默认 0 全部
	public static final int DEFAULT_NATIONALITYTYPE = 0;
	
	public static final String MAXSEATS_ERROR_MSG = "舱位不足";
	
	/** 
	 * 默认错误返回码
	*/ 
	public static final int DEFAULT_ERROR_CODE = -1;
	
	
	public static final int DEFAULT_SUCESS_CODE = 0;
	
	public static final String CABIN_NUM_ERROR = "舱位不足";
	
	
	
	public static final String OWN_CID= "OWN";
	
	
	
	/** 
	 * 公布运价强校验】
1）旅客资质，标准三字码：
	NOR：普通成人
	LAB：劳务人员
	SEA：海员
	SNR：老年人
	STU：学生
	YOU：青年
	*/ 
	public static final String ELIGIBILITY_NOR = "NOR";
	
	
	public static final String DEFAULT_TAXTYPE = "0";
	
	public static final String DEFAULT_PRICETYPE  = "0";
	
	public static final String DEFAULT_FARETYPE  = "PUBLIC";
	
	public static final String DEFAULT_TICKETTYPE  = "BSP";
	
	public static final String DEFAULT_SUITAGE  = "12-59";
	
	public static final int  DEFAULT_MAXSEATS  = 9;
	
	public static final String DEFAULT_BOOKINGGDS  = "OT";
	
	
	/** 
	 * 方案航程 分布式锁名称
	*/ 
	public static final String PUSCHEMEFLI_LOCK_NAME = "putscheme_flight_{0}";
	
	public static final String PUSCHEME_LOCK_NAME = "putscheme_{0}";
	
	/** 
	 * 浮点类型格式化
	*/ 
	public static final String NUMBER_FORMAT_STR = "###";
	
	
	/** 
	 * 默认不可退  不可改
	*/ 
	public static final int DEFAULT_NO_CHANGE_REFUND_ISPERMITED = 0;
	
	
	/** 
	 * 携程默认运价类型
	*/ 
	public static final String PRODUCT_TYPE_DEFAULT = "PRV";
	
	public static final String NONIL = "nonil";
	
	public static final String NO_TEMPLATE = "\"no\":{0}";

	/**
	 * worldspan接口基础配置
	 */
	
	public static final String BRANCH = "P3117546";
			
	public  static final long WORLDSPAN_REDIS_TIME=600;
	
	
	



	
}
