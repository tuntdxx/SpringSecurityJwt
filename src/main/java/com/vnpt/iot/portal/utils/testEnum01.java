package com.vnpt.iot.portal.utils;

import com.vnpt.iot.portal.utils.EnumValues.ProviceEnum;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 8, 2020
 */

public class testEnum01 {

	public static void main(String[] args) {
for( ProviceEnum ex : EnumValues.ProviceEnum.values()) {
	System.out.println(ex.getCode() + " __ " + ex.getName());
}

	}

}
