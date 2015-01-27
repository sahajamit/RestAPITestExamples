package com.restassured.testing;

import java.util.List;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.matcher.RestAssuredMatchers.*;

import org.hamcrest.Matchers.*;

import com.jayway.restassured.module.jsv.JsonSchemaValidator.*;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class RestAssuredTesting {

	public static void main(String[] args) {
		try{
			System.setProperty("http.proxyHost", "192.168.230.1");
			System.setProperty("http.proxyPort", "8888");
			System.setProperty("https.proxyHost", "192.168.230.1");
			System.setProperty("https.proxyPort", "8888");
			Response response;
			String body;
			String BaseURL = "http://api.equinox.com/v1/";
			
			String LoginRawData = "username=myeqtestautomation24%40gmail.com&password=equinox1";
//			response = given()
//					.contentType("application/x-www-form-urlencoded; charset=UTF-8")
//			    	.body(LoginRawData)
//			        .when()
//			        .post(BaseURL+"authentication/login");
			
			response = given()
					.redirects().follow(false)
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
					.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36")
					.header("Accept-Encoding", "gzip, deflate, sdch")
					.header("Accept-Language", "en-US,en;q=0.8")
					.header("Authorization", "Basic RGV2UHJldmlldzpFcXVpbm94MSE=")
			        .when()
			        .get("http://stag.equinox.com/activate/email/b06bd976fd31451293e9e93bee797cd9");
		
			body = response.getBody().asString();
			response.cookies();
			response.cookies().values();
			response.cookies().get("isMemberRegistered");
			int code = response.getStatusCode();
			
			
			System.out.println(body);
			if(body.contains("firstName")){
				System.out.println("Login API Test Passed");
			}else{
				System.out.println("Login API Test Failed");
			}
//	    	System.out.println(body);
			
//			RestAssured.baseURI = "http://api.equinox.com/v1";
//			Response response = get("http://api.equinox.com/v1/classes/categories?_=1411818512245");
			
			String myJson = "{\"startDate\": \"2014-09-28T00:00:00\","
					+ "\"endDate\": \"2014-10-05T00:00:00\","
					+ "\"facilityIds\": [102],"
					+ "\"dateIsUtc\": \"false\"}";				
			response = given()
					.contentType("application/json; charset=UTF-8")
					.body(myJson)
			        .when()
			        .post(BaseURL+"search/classes");
			
//			List facilityIds = response.jsonPath().getList("classes.facility.facilityId");
			List classInstanceIds = response.jsonPath().getList("classes.classInstanceId");
			if(classInstanceIds.size()>0){
				System.out.println("Class Search API Test Passed");
			}else{
				System.out.println("Class Search API Test Failed");
			}
			body = response.getBody().asString();
//	    	System.out.println(body);
			
//			response.body().
//			get("http://api.equinox.com/v1/classes/categories?_=1411818512245").then().body("[0].categoryId", equalTo("5"));
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}

}
