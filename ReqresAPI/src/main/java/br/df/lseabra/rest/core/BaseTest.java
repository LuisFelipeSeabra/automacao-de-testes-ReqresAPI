package br.df.lseabra.rest.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

public class BaseTest implements Constantes  {

	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = APP_BASE_URL;
		RestAssured.port = APP_PORT;
		RestAssured.basePath = APP_BASE_PATH;
		
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.setContentType(APP_CONTENT_TYPE);
		RestAssured.requestSpecification = reqBuilder.build();
		
		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		resBuilder.expectResponseTime(Matchers.lessThan(MAX_TIMEOUT));
		RestAssured.responseSpecification = resBuilder.build();
		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

	}
	
	public String obterData() {
		Date data = new Date(System.currentTimeMillis()); 
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd");
		return formatarDate.format(data);
	}

	public Map<String,String > criarElemento(String key,String value,String key1,String value1 ) {
		Map<String, String> elemento = new HashMap<String, String>();
		elemento.put(key, value);
		elemento.put(key1, value1);
		return elemento;
	}
	public Map<String,String > criarElemento(String key,String value ) {
		Map<String, String> elemento = new HashMap<String, String>();
		elemento.put(key, value);
		return elemento;
	}	


}
