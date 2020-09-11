package com.vnpt.iot.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vnpt.iot.portal.entity.Province;
import com.vnpt.iot.portal.repository.ProvinceRepository;
import com.vnpt.iot.portal.service.ProvinceService;
import com.vnpt.iot.portal.utils.EnumValues;
import com.vnpt.iot.portal.utils.EnumValues.ProviceEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 8, 2020
 */

@Service
@Slf4j
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	ProvinceRepository provinceRepo;

	@Override
	@Transactional
	public void createProvince() {
		List<Province> provinces = new ArrayList<Province>();

		for (ProviceEnum ex : EnumValues.ProviceEnum.values()) {
			Province province = new Province();
			province.setProviceName(ex.getName());
			province.setProviceCode(ex.getCode());
			provinces.add(province);
		}
		log.info("List province with size :" + provinces.size());
		provinceRepo.saveAll(provinces);
		log.info("Created Province");
	}

}
