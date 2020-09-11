package com.vnpt.iot.portal.service.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.onem2m.common.constants.ResponseStatusCode;
import com.onem2m.common.model.RequestPrimitive;
import com.onem2m.common.model.ResponsePrimitive;
import com.onem2m.common.model.shortname.Asar;
import com.onem2m.protocol.protocol.RestClient;
import com.onem2m.protocol.protocol.binding.http.HTTPRestClient;
import com.onem2m.protocol.protocol.binding.mqtt.RestClientService;
import com.vnpt.iot.portal.entity.Customer;
import com.vnpt.iot.portal.entity.CustomerApikey;
import com.vnpt.iot.portal.exceptions.PortalException;
import com.vnpt.iot.portal.model.CustomerDTO;
import com.vnpt.iot.portal.model.RequestModel;
import com.vnpt.iot.portal.model.ResponseModel;
import com.vnpt.iot.portal.repository.CustomerRepository;
import com.vnpt.iot.portal.service.CustomerService;
import com.vnpt.iot.portal.utils.ConstantDefine;
import com.vnpt.iot.portal.utils.EnumValues;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 7, 2020
 */

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepo;

	@Value("${spring.post.call.asar.url}")
	private String POSTURL;

	@Value("${spring.post.call.asar.aai.value}")
	private String AAI_VALUE;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	@Transactional
	public ResponseModel createCustomer(RequestModel customerRequest) throws JsonSyntaxException, DataAccessException {
		// get infor customer
		Gson gson = new Gson();
		String json = gson.toJson(customerRequest.getContent());
		Customer customer = gson.fromJson(json, Customer.class);
		// set value, not input NullPointerException
		String emailRn = customer.getEmail().replace("@", "_");
		String role = "/SuperTester";

		ResponseModel customerResponse = new ResponseModel();
		// call API
		log.info("call API core: POST_ServiceSubscribedAppRuleEntity CREATE");
		Asar asar = getAsar(POSTURL, emailRn, role);

		if (null != asar.getApci() && !asar.getApci().isEmpty()) {
			String apci = asar.getApci().toString();
			// get list apci
			List<CustomerApikey> apikeys = new ArrayList<CustomerApikey>();
			for (int i = 0; i < asar.getApci().size(); i++) {
				CustomerApikey apikey = new CustomerApikey();
				apikey.setApci(asar.getApci().get(i));
				apikeys.add(apikey);
				apikey.setCustomer(customer);
			}
			customer.setCustomerApikeys(apikeys);
			customerRepo.save(customer);
			// TODO phai check not null tren giao dien, ko se ko save dc ma van sendEmail
			// TODO send Email server chan
			log.warn("TODO::SERVER FIREWALL:: Cannot Send to Email!!!");
			customerResponse.setResponseStatusCode(EnumValues.StatusProtocolEnum.STATUS_201.code);
			customerResponse.setResponseStatusMessage(EnumValues.StatusProtocolEnum.STATUS_201.message);

//			String checkEmail = sendEmail(customer.getEmail(), apci);
//			if (ConstantDefine.SUCCESS.equals(checkEmail)) {
//				customerResponse.setResponseStatusCode(EnumValues.StatusProtocolEnum.STATUS_201.code);
//				customerResponse.setResponseStatusMessage(EnumValues.StatusProtocolEnum.STATUS_201.message);
//			} else {
//			throw new PortalException(EnumValues.StatusProtocolEnum.STATUS_600.code,
//					EnumValues.StatusProtocolEnum.STATUS_600.message);
//				customerResponse.setResponseStatusCode(EnumValues.StatusProtocolEnum.STATUS_600.code);
//				customerResponse.setResponseStatusMessage(EnumValues.StatusProtocolEnum.STATUS_600.message);
//			}

		} else {
			log.error("Cannot create APCI from IOT_CORE");
			throw new PortalException(EnumValues.StatusProtocolEnum.STATUS_400.code,
					EnumValues.StatusProtocolEnum.STATUS_400.message);
		}

		return customerResponse;

	}

	/**
	 * call API get infor Asar, method = post
	 * 
	 * @param url
	 * @param emailRn
	 * @param role
	 * @return
	 */
	private Asar getAsar(String url, String emailRn, String role) {
		// init rest mqtt,http client
		Map<String, RestClientService> sclClients = new ConcurrentHashMap<String, RestClientService>();
		sclClients.put("http", new HTTPRestClient());
		RestClient.setRestClients(sclClients);

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String times = String.valueOf(timestamp.getTime());
		log.info("POST_URL: " + url);
		// Create AE
		RequestPrimitive requestPrimitive = new RequestPrimitive();
		requestPrimitive.setFrom(role);
		requestPrimitive.setTo(url);
		requestPrimitive.setOperation(BigInteger.ONE);
		requestPrimitive.setRequestContentType("application/json");
		requestPrimitive.setResourceType(BigInteger.valueOf(19));

		Map<String, String> httpHeaders = new HashMap<String, String>();
		httpHeaders.put("Accept", "application/json");
		requestPrimitive.setHttpHeaders(httpHeaders);
		requestPrimitive.setRequestIdentifier(times);

		JSONObject jsonObjectActutor = new JSONObject();
		Gson gsonActutor = new Gson();
		Asar asar = new Asar();
		asar.setRn(emailRn);
		log.info("getRN: " + asar.getRn());
		asar.getAai().add(AAI_VALUE);

		String jsonActutor = gsonActutor.toJson(asar);
		Object obj = gsonActutor.fromJson(jsonActutor, Object.class);
		jsonObjectActutor.put("m2m:asar", obj);
		requestPrimitive.setContent(jsonObjectActutor.toString());

		ResponsePrimitive responsePrimitive = RestClient.sendRequest(requestPrimitive);
		String apci = null;
		if (responsePrimitive != null && responsePrimitive.getResponseStatusCode() != null
				&& responsePrimitive.getResponseStatusCode().intValue() == ResponseStatusCode.CREATED.intValue()) {
			JSONObject jsonObjectCsr = new JSONObject(responsePrimitive.getContent().toString())
					.getJSONObject("m2m:asar");
			asar = gsonActutor.fromJson(jsonObjectCsr.toString(), Asar.class);
			apci = asar.getApci().toString();
			log.info("getAPCI: " + apci);
			// TODO truong hop loi:
			// saved AE thi da tao RN roi ma loi save customer thi ko revert lai RN dc
		}
		return asar;
	}

	/**
	 * Send Email to Customer
	 * 
	 * @param emailTo
	 * @param apci
	 */
	private String sendEmail(String emailTo, String apci) {
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(emailTo);
			msg.setSubject("Thông tin APCI");
			msg.setText("APCI_KEY: " + apci);
			javaMailSender.send(msg);
			log.info("Mail sent to :" + emailTo + " succesfully!");
			return ConstantDefine.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			log.error("Failed to send email to :" + emailTo);
			return ConstantDefine.ERROR;
		}
	}

	@Override
	public List<Customer> getAll(Example<Customer> example, Pageable pageable) {
		return customerRepo.findAll(example, pageable).getContent();
	}

	@Override
	public int totalCustomer() {
		return (int) customerRepo.count();
	}

	@Override
	public List<Customer> getAll() {
		return customerRepo.findAll();
	}

	@Override
	public ResponseModel viewCustomer(Integer page, Integer limit, String sort, String params, Integer contractType) {
		ResponseModel customerResponse = null;
		List<Customer> customers = null;

		if (null != page && null != limit) {
			log.info("Customer paging!");
			Sort sortable = null;
			// sort by ID
			if (sort.equals("ASC")) {
				sortable = Sort.by("id").ascending();
			}
			if (sort.equals("DESC")) {
				sortable = Sort.by("id").descending();
			}

			Pageable pageable = PageRequest.of(page - 1, limit, sortable);
//			customers = getAll(example, pageable);
			// search Customer
			customers = customerRepo.findByFullnameOrEmailOrPhoneAndContractType(params, params, params, contractType,
					pageable);

			// set value
			List<CustomerDTO> customerDtos = getCustomer(customers);
			customerResponse = getJsonfromList(customerDtos);
			customerResponse.setPage(page);
//			customerResponse.setTotalPage(totalCustomer() / limit);
			int checkTotalPage = customerRepo.countByFullnameOrEmailOrPhoneAndContractType(params, params, params,
					contractType) / limit;
			if (checkTotalPage == 0) {
				customerResponse.setTotalPage(1);
			} else {
				customerResponse.setTotalPage(
						customerRepo.countByFullnameOrEmailOrPhoneAndContractType(params, params, params, contractType)
								/ limit);
			}

		} else {
			log.info("Customer not paging!!!");
			customers = getAll();
			// set value
			List<CustomerDTO> customerDtos = getCustomer(customers);
			customerResponse = getJsonfromList(customerDtos);
		}

		return customerResponse;
	}

	/**
	 * get json from list customer
	 * 
	 * @param customers
	 * @return
	 */
	private ResponseModel getJsonfromList(List<CustomerDTO> customerDtos) {
		ResponseModel customerResponse = new ResponseModel();
		Gson gson = new Gson();
		String jsonStr = gson.toJson(customerDtos);
		Object objCustomer = gson.fromJson(jsonStr, Object.class);
		customerResponse.setContent(objCustomer);
		return customerResponse;
	}

	/**
	 * mapper data to list CustomerDTO
	 * 
	 * @param customers
	 * @return
	 */
	private List<CustomerDTO> getCustomer(List<Customer> customers) {
		List<CustomerDTO> customerDtos = new ArrayList<CustomerDTO>();
		ModelMapper modelMapper = new ModelMapper();
		customerDtos = modelMapper.map(customers, new TypeToken<List<CustomerDTO>>() {
		}.getType());
		log.info("total CustomerDTO: " + customerDtos.size());
		return customerDtos;
	}

	@Override
	public Customer findById(String id) {
		return customerRepo.findById(id).get();
	}

	/**
	 * Show Customer
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ResponseModel showCustomer(String id) {
		// get infor customer
		Customer customer = findById(id);

		ResponseModel customerResponse = new ResponseModel();
		customerResponse.setContent(customer);
		return customerResponse;

	}

	/**
	 * Edit Customer
	 * 
	 * @param customerRequest
	 * @return
	 */
	@Override
	@Transactional
	public void editCustomer(RequestModel customerRequest) {

		// get infor customer
		Gson gson = new Gson();
		String json = gson.toJson(customerRequest.getContent());
		Customer customer = gson.fromJson(json, Customer.class);

		// update customer
		customerRepo.save(customer);
	}

	/**
	 * Delete Customer
	 * 
	 * @param id
	 * @return
	 */
	@Override
	@Transactional
	public void deleteCustomer(String id) {
		// delete customer
		customerRepo.deleteById(id);
	}
}