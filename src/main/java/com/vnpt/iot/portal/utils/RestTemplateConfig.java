//package com.vnpt.iot.portal.utils;
//
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
///**
// * @author Trinh Dinh Tuan : Developer
// * @Email tuantdxyz@gmail.com
// * @Version 1.0.0 Sep 8, 2020
// */
//
////@Configuration
////@Component
//public class RestTemplateConfig {
//
//	@Autowired
//	CloseableHttpClient httpClient;
//
//	@Bean
//	public RestTemplate restTemplate() {
//
//		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
//		return restTemplate;
//	}
//
//	@Bean
//	public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
//
//		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//		clientHttpRequestFactory.setHttpClient(httpClient);
//		return clientHttpRequestFactory;
//	}

//---restTemplate
		// method = get
//		final String uri = "http://10.159.12.107:9999/~/vnpt.it/icc/rule_of_company1b";
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.set("X-M2M-RI", "1234asdfasd5");
//		headers.set("X-M2M-Origin", "admin:admin");
//		
//		RestTemplate  restTemplate = new RestTemplate();
//	    HttpEntity<String> entity = new HttpEntity<String>(headers);
//	 
//	    ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
//	    System.out.println(response);

		// method = post--> bug
//		final String uri = "http://10.159.12.107:9999/~/vnpt.it/icc";
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		String times = String.valueOf(timestamp.getTime());
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		headers.set("Content-Type", "application/json;ty=19");
//		headers.set("X-M2M-Origin", "/SuperTester");
//		headers.set("X-M2M-RI", times);
//
//		JSONObject jsonObject = new JSONObject();
//		Gson gsonSmart = new Gson();
//		Asar asar = new Asar();
//		asar.setRn("Tuan_" + times);
//		System.out.println("RN: " + asar.getRn());
////		asar.setAai(aai);
//		asar.getAai().add("(N|R)([0-9a-zA-Z\\.\\-\\_])*(\\.company1)");
//
//		String jsonSub = gsonSmart.toJson(asar);
//		Object objSub = gsonSmart.fromJson(jsonSub, Object.class);
//		jsonObject.put("m2m:asar", objSub);
//		System.out.println(jsonObject.toString());
//
//		RequestModel req = new RequestModel();
//		req.setContent(jsonObject.toString());
//
//		RestTemplate restTemplate = new RestTemplate();
//		HttpEntity<RequestModel> request = new HttpEntity<RequestModel>(req, headers);
//
////		String result = restTemplate.postForObject(uri, request, String.class);
//		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
//		System.out.println(result);
//}
