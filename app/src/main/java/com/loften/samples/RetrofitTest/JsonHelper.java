package com.loften.samples.RetrofitTest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonHelper {
	public static String toJsonString(Object o) {
		ObjectMapper mapper = new ObjectMapper();

//	    mapper.configure(Feature.INDENT_OUTPUT, Boolean.TRUE);

	    String rtn = null;
		try {
			rtn = mapper.writeValueAsString(o);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return rtn;
	}

//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public static Object toObject(String jsonStr, Class clazz)
//			throws JsonParseException, JsonMappingException {
//		if (jsonStr == null)
//			return null;
//		ObjectMapper mapper = JacksonMapper.getInstance();
//		Object bean = null;
//		try {
//			bean = mapper.readValue(jsonStr, clazz);
//		} catch (JsonParseException e) {
//			// 应该抛出，测试未抛出
//		} catch (JsonMappingException e) {
//		} catch (IOException e) {
//		}
//		return bean;
//	}
////
//	public static void main(String[] args) {
//		AccountLoginRequest request = new AccountLoginRequest();
//		request.setAccountId("12345");
//		request.setAccountPwd("19900120");
//		request.setmImei("11111");
//		request.setmIp("192.168.1.10");
//		request.setmSys(0);
//		request.setmSystime(new Date());
//
//		// jackson用法
//		String json = toJsonString(request);
//		AccountRequestBean requestBean = new AccountRequestBean();
//		requestBean.setRequest(request);
//		requestBean.setReqType("accountLogin");
//		String requestStr = toJsonString(requestBean);
//		System.out.println(requestStr);
//		StringBuilder sb = new StringBuilder();
//		sb.append("{");
//		sb.append("\"reqType\":\"accountLogin\"");
//		sb.append(",\"request\":");
//		sb.append(json);
//		sb.append("}");
//		System.out.println(sb);
//		System.out.println(json);
//		try {
//			JsonNode node = JacksonMapper.getInstance().readTree(requestStr);
//			String reqType = "accountLogin";
//			System.out.println(reqType.equals(node.get("reqType").asText()));
//			System.out.println(node.get("request"));
//		} catch (JsonProcessingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			Object obj = toObject(json, AccountLoginRequest.class);
//			System.out.println(obj);
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// flexjson用法
/*		String flexJson = new JSONSerializer().exclude("class").serialize(
				request);
		AccountLoginRequest flexJsonoObj = new JSONDeserializer<AccountLoginRequest>()
				.deserialize(flexJson, AccountLoginRequest.class);
		Map<String, Object> map = (Map<String, Object>) new JSONDeserializer()
				.use(null, HashMap.class).deserialize(requestStr);
		System.out.println(map.get("reqType"));
		System.out.println(flexJsonoObj);
		System.out.println(flexJson);
	}*/
}
