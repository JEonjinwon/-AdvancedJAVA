package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *  JSON : Javascript Object Notation
 *         자바스트립트 오브젝트 형식으로 표현된 텍스트
 *       
 *  - JSON에서 value 값으로 가능한 데이터 타입
 *  1. String 
 *  2. number
 *  3. object(JSON Object)
 *  4. array 
 *  5. boolean
 *  6. null
 */
public class JsonSimpleWriterTest {
   public static void main(String[] args) throws IOException {
      // JSON 데이터 생성
      JSONObject jsonObj = new JSONObject();
      
      jsonObj.put("name", "홍길동");
      jsonObj.put("job", "학생");
      jsonObj.put("age", 30);
      jsonObj.put("addr", "대전시 중구 대흥동");
      
      // JSONArray 데이터 생성
      JSONArray singerList = new JSONArray();
      
      JSONObject singer = new JSONObject();
      singer.put("name", "Etham");
      singer.put("gender", "남자");
      singer.put("age", 24);
      singerList.add(singer);

      singer = new JSONObject();
      singer.put("name", "Jess Glynne");
      singer.put("gender", "여자");
      singer.put("age", 32);
      singerList.add(singer);

      singer = new JSONObject();
      singer.put("name", "Alexander Oscar");
      singer.put("gender", "남자");
      singer.put("age", 23);
      singerList.add(singer);
      
      jsonObj.put("singerList", singerList);
      
      FileWriter fw = new FileWriter("D:/D_Other/myJsonFile.txt");
      fw.write(jsonObj.toString());
      fw.flush();
      fw.close();
   }
}