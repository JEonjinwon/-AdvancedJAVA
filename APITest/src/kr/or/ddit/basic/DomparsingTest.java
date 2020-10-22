package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * DOM 파싱 예제 
 */
public class DomparsingTest {
	
	 


	public void parsing() {
		
		
		try {
			
			//DOM Document 객체 생성
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); //DocumentBuilder 만들기위해 팩토리만듬
			DocumentBuilder builder = dbf.newDocumentBuilder();
			
			//XML 파일 저장
			String url =getClass().getResource("/kr/or/ddit/basic/dust.xml").toString();
			Document xmlDoc = builder.parse(url);
			
			//DOM Document 객체로부터 루트 엘리먼트 잋 자식 객체 가져오기
			Element root = xmlDoc.getDocumentElement();
			System.out.println("루트 엘리먼트 태그명:"+root.getTagName());	//itemㄴ나옴
			
			NodeList itemNodeList =  root.getElementsByTagName("item");
			
			Node firstItemNode = itemNodeList.item(0);
			Element firstItemElement =(Element) firstItemNode;
			
			NodeList firstItemChildNodeList = firstItemNode.getChildNodes();
			
			Node titleNode = firstItemChildNodeList.item(1);
			Element titleElement = (Element)titleNode;
			
			System.out.println("titleElement.getTagName() => "+titleElement.getTagName());
			System.out.println("titleElement.getTextContent() => "+titleElement.getTextContent());
			
			
			for (int i = 0; i < itemNodeList.getLength(); i++) {
				Node itemNode = itemNodeList.item(i);
				Element element = (Element)itemNode;
				System.out.println("===============================================");
				System.out.println("미세먼지 수치 ");
				System.out.println("===============================================");
				System.out.println("측정 시간 : " + element.getElementsByTagName("dataTime").item(0).getTextContent());
				System.out.println("측정 항목 : " + element.getElementsByTagName("itemCode").item(0).getTextContent());
				System.out.println("서울 수치 : " + element.getElementsByTagName("seoul").item(0).getTextContent());
				System.out.println("부산 수치 : " + element.getElementsByTagName("busan").item(0).getTextContent());
				System.out.println("대구 수치 : " + element.getElementsByTagName("daegu").item(0).getTextContent());
				System.out.println("인천 수치 : " + element.getElementsByTagName("incheon").item(0).getTextContent());
				System.out.println("광주 수치 : " + element.getElementsByTagName("gwangju").item(0).getTextContent());
				System.out.println("대전 수치 : " + element.getElementsByTagName("daejeon").item(0).getTextContent());
				System.out.println("");
				System.out.println("");
			
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//xml 받아오는 부분 
	public void getXml() throws IOException{
		
		   StringBuilder urlBuilder = new StringBuilder("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=tiHP%2FnGDK%2FtSogy1Y0aGITAMnSbPUuGeOs6uRA8f%2F3gMvqwD1dNR7cYTHOIW%2F3OAyKSIdB5r3FCFuZRRUA6Heg%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
	        urlBuilder.append("&" + URLEncoder.encode("itemCode","UTF-8") + "=" + URLEncoder.encode("PM10", "UTF-8")); /*측정항목 구분 (SO2, CO, O3, NO2, PM10, PM25)*/
	        urlBuilder.append("&" + URLEncoder.encode("dataGubun","UTF-8") + "=" + URLEncoder.encode("HOUR", "UTF-8")); /*요청 자료 구분 (시간평균 : HOUR, 일평균 : DAILY)*/
	        urlBuilder.append("&" + URLEncoder.encode("searchCondition","UTF-8") + "=" + URLEncoder.encode("MONTH", "UTF-8")); /*요청 데이터기간 (일주일 : WEEK, 한달 : MONTH)*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        try {
	        	PrintWriter writer = new PrintWriter("/kr/or/ddit/basic/dust.xml");
	        	writer.println(sb);
	        	writer.close();
	        } catch (IOException e) {
	        	// TODO: handle exception
	        }


			rd.close();
	        conn.disconnect();
	        System.out.println(sb.toString());
		}
	
	
	
	public static void main(String[] args) throws IOException {
		new DomparsingTest().getXml();
		
		
		new DomparsingTest().parsing();
	}
}
