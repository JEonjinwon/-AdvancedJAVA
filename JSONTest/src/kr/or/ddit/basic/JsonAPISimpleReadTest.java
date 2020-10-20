package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 공공데이터포털의 OPEN API 예제 
 * (레시피 재료 정보를 가져오는 예제)
 */
public class JsonAPISimpleReadTest {
	public static void main(String[] args) throws IOException, ParseException {
		
		String svcKey = "Grid_20150827000000000227_1";  // 레시피 재료 정보 조회 서비스 
		String apiKey = "1df7e8571e8df3f8cbc9b87691ca7d3e4d04f03c593d477e52bf67b03f0b6e1c"; // 개인별 발급.
		String startIdx = "1";  	// 레시피 재료 시작 순번
		String endIdx = "5";		// 레시피 재료 종료 순번
		String recipeId = "195428";	// 래시피가 궁금한 음식 ID 

		URL url = new URL("http://211.237.50.150:7080/openapi/"+ apiKey 
				+ "/json/"+ svcKey + "/"+startIdx +"/" + endIdx 
				+"?RECIPE_ID=" +  recipeId);
		URLConnection urlConnection = url.openConnection();
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new InputStreamReader(urlConnection.getInputStream()));
		
		JSONObject jsonFile = (JSONObject)obj;

		JSONObject rootObj = (JSONObject)jsonFile.get(svcKey);
		
		//전체 레시피 재료 수
		long totalCnt = (long)rootObj.get("totalCnt");
		
		endIdx = totalCnt+"";//레시피 재료 마지막 순번
		//================================================================
		//endIdx가 5여서 전부다 받아오기 위해 14로 바꿔주고 다시 호출한 것
		
		url = new URL("http://211.237.50.150:7080/openapi/"+ apiKey 
				+ "/json/"+ svcKey + "/"+startIdx +"/" + endIdx 
				+"?RECIPE_ID=" +  recipeId);
		
		urlConnection = url.openConnection();
		obj = parser.parse(new InputStreamReader(urlConnection.getInputStream()));
		
		jsonFile = (JSONObject)obj;
		rootObj = (JSONObject)jsonFile.get(svcKey);
		
		JSONObject result = (JSONObject)rootObj.get("result");
		String code = (String)result.get("code");
	
		if(code.equals("INFO-000")) {	//정상결과이면...
			JSONArray list = (JSONArray) rootObj.get("row");
			for (Object tempObj : list) {
				JSONObject tempJson = (JSONObject)tempObj;
				System.out.println("순번 : " +tempJson.get("ROW_NUM"));
				System.out.println("레시피ID : " +tempJson.get("RECIPE_ID"));
				System.out.println("재료명 : " +tempJson.get("IRDNT_NM"));
				System.out.println("용량 : " +tempJson.get("IRDNT_CPCTY"));
				System.out.println("재료구분 : " +tempJson.get("IRDNT_TY_NM"));
				System.out.println("=============================================================");
			}
		
		}
		
		
		
	}
}
