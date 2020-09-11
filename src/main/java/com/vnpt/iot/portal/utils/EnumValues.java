package com.vnpt.iot.portal.utils;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 1, 2020
 */

public interface EnumValues {
	
	/**
	 * The User Status
	 */
	public enum UserStatusEnum {

		WAITING(0, "enum.user_status.waiting"), ACTIVE(1, "enum.user_status.active"),
		DEACTIVE(2, "enum.user_status.deactive");

		public int value;
		public String description;

		private UserStatusEnum(int value, String description) {
			this.value = value;
			this.description = description;
		}
	}

	/**
	 * The User Type
	 */
	public enum UserTypeEnum {

		ADMIN(0, "enum.user_type.admin"), USER(1, "enum.user_type.user");

		public int value;
		public String description;

		private UserTypeEnum(int value, String description) {
			this.value = value;
			this.description = description;
		}
	}

	/**
	 * The Status Protocol
	 */
	public enum StatusProtocolEnum {

		/** Success. */
		STATUS_200(200, "Success"),

		/** Created. */
		STATUS_201(201, "Created"),

		/** Unauthorized. */
		STATUS_401(401, "Unauthorized"),

		/** Bad Request, error common. */
		STATUS_400(400, "Bad Request, Incorrect syntax or data when inputting"),

		/** Forbidden. */
		STATUS_403(403, "Forbidden"),

		/** Not Found. */
		STATUS_404(404, "Not Found"),

		/** Internal Server Error. */
		STATUS_500(500, "Internal Server Error"),

		/** Gateway Timeout. */
		STATUS_504(504, "Gateway Timeout"),
		
		/** Failed to send email. */
		STATUS_600(600, "Failed to send email"),
		
		/** Sql error. */
		STATUS_601(601, "Cannot execute sql statement, syntax error"),
		
		/** NullPointerException. */
		STATUS_602(602, "NullPointerException"),
		
		/** Json error */
		STATUS_603(603, "Json parse or incorrect format error"),
		
		/** customer view error */
		STATUS_604(604, "Cannot view customer list"),
		
		/** customer show */
		STATUS_605(605, "Cannot show customer detail"),
		
		/** customer edit */
		STATUS_606(606, "Cannot edit customer"),
		
		/** customer delete */
		STATUS_607(607, "Cannot delete customer"),
		
		/** Invalid JWT token */
		STATUS_608(608, "Invalid JWT token"),
		
		/** Unsupported JWT token */
		STATUS_609(609, "Unsupported JWT token"),
		
		/** JWT claims string is empty */
		STATUS_610(610, "JWT claims string is empty");
		
		
		/** The code. */
		public Integer code;

		/** The message. */
		public String message;

		private StatusProtocolEnum(Integer code, String message) {
			this.code = code;
			this.message = message;
		}
	}

	/**
	 * The Provice
	 */
	public enum ProviceEnum {

		PROVICE_ANGIANG("0296", "An Giang"),
		PROVICE_BRVT("0254", "Bà Rịa - Vũng Tàu"),
		PROVICE_BACLIEU("0291", "Bạc Liêu"),
		PROVICE_BACGIANG("0204", "BẮC GIANG"),
		PROVICE_BACKAN("0209", "BẮC KẠN"),
		PROVICE_BACNING("0222", "BẮC NINH"),
		PROVICE_BENTRE("0275", "BẾN TRE"),
		PROVICE_BINHDUONG("0274", "BÌNH DƯƠNG"),
		PROVICE_BINHDINH("0256", "BÌNH ĐỊNH"),
		PROVICE_BINHPHUOC("0271", "BÌNH PHƯỚC"),
		PROVICE_BINHTHUAN("0252", "BÌNH THUẬN"),
		PROVICE_CAMAU("0290", "CÀ MAU"),
		PROVICE_CAOBANG("0206", "CAO BẰNG"),
		PROVICE_CANTHO("0292", "CẦN THƠ"),
		PROVICE_DANANG("0236", "ĐÀ NẴNG"),
		PROVICE_DAKLAK("0262", "ĐẮK LẮK"),
		PROVICE_DAKNONG("0261", "ĐẮK NÔNG"),
		PROVICE_DONGNAI("0251", "ĐỒNG NAI"),
		PROVICE_DONGTHAP("0277", "ĐỒNG THÁP"),
		PROVICE_DIENBIEN("0215", "ĐIỆN BIÊN"),
		PROVICE_GIALAI("0269", "GIA LAI"),
		PROVICE_HAGIANG("0219", "HÀ GIANG"),
		PROVICE_HANAM("0226", "HÀ NAM"),
		PROVICE_HANOI("024", "HÀ NỘI"),
		PROVICE_HATINH("0239", "HÀ TĨNH"),
		PROVICE_HAIDUONG("0220", "HẢI DƯƠNG"),
		PROVICE_HAIPHONG("0225", "HẢI PHÒNG"),
		PROVICE_HOABINH("0218", "HÒA BÌNH"),
		PROVICE_HAUGIANG("0293", "HẬU GIANG"),
		PROVICE_HUNGYEN("0221", "HƯNG YÊN"),
		PROVICE_TPHCM("028", "Thành phố Hồ Chí Minh"),
		PROVICE_KHANHHOA("0258", "KHÁCH HÒA"),
		PROVICE_KIENGIANG("0297", "KIÊN  GIANG"),
		PROVICE_KONTUM("0260", "KON TUM"),
		PROVICE_LAICHAU("0213", "LAI CHÂU"),
		PROVICE_LAOCAI("0214", "LÀO CAI"),
		PROVICE_LANGSON("0205", "LẠNG SƠN"),
		PROVICE_LAMDONG("0263", "LÂM ĐỒNG"),
		PROVICE_LONGAN("0272", "LONG AN"),
		PROVICE_NAMDINH("0228", "NAM ĐỊNH"),
		PROVICE_NGHEAN("0238", "NGHỆ AN"),
		PROVICE_NINHBINH("0229", "NIMH BÌNH"),
		PROVICE_NINHTHUAN("0259", "NINH THUẬN"),
		PROVICE_PHUTHO("0210", "PHÚ THỌ"),
		PROVICE_PHUYEN("257", "PHÚ YÊN"),
		PROVICE_QUANGBINH("0232", "QUẢNG BÌNH"),
		PROVICE_QUANGNAM("0235", "QUẢNG NAM"),
		PROVICE_QUANGNGAI("0255", "QUẢNG NGÃI"),
		PROVICE_QUANGNINH("0203", "QUẢNG NINH"),
		PROVICE_QUANGTRI("0233", "QUẢNG TRỊ"),
		PROVICE_SOCTRANG("0299", "SÓC TRĂNG"),
		PROVICE_SONLA("0212", "SƠN LA"),
		PROVICE_TAYNINH("0276", "TÂY NINH"),
		PROVICE_THAIBINH("0227", "THÁI BÌNH"),
		PROVICE_THAINGUYEN("0208", "THÁI NGUYÊN"),
		PROVICE_THANHHOA("0237", "THANH HÓA"),
		PROVICE_TTH("0234", "THỪA THIÊN - HUẾ"),
		PROVICE_TIENGIANG("0273", "TIỀN GIANG"),
		PROVICE_TRAVINH("0294", "TRÀ VINH"),
		PROVICE_TUYENQUANG("0207", "TUYÊN QUANG"),
		PROVICE_VINHLONG("0270", "VĨNH LONG"),
		PROVICE_VINHPHUC("0211", "VĨNH PHÚC"),
		PROVICE_YENBAI("0216", "YÊN BÁI");

		/** The area code. */
		public String code;

		/** Provice name. */
		public String name;

		private ProviceEnum(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		

	}
}
