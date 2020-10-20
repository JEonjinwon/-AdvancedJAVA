package kr.or.ddit.basic;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
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
			String url =getClass().getResource("/kr/or/ddit/basic/book.xml").toString();//this.getClass()인데 this 생략 
			Document xmlDoc = builder.parse(url);
			
			//DOM Document 객체로부터 루트 엘리먼트 잋 자식 객체 가져오기
			Element root = xmlDoc.getDocumentElement();
			System.out.println("루트 엘리먼트 태그명:"+root.getTagName());
			
			//하위 엘리먼트 접근방법 1: getElementsByTagName()이용
			NodeList bookNodeList =  root.getElementsByTagName("book");
			
			Node firstBookNode = bookNodeList.item(0);	//첫번째
			Element firstBookElement = (Element) firstBookNode;
			
			//속성 접근 방법1 : 엘리먼트 객체의 getAttribute()이용
			System.out.println("엘리먼트 객체의 getAttrubute() 이용 => "+firstBookElement.getAttribute("isbn"));
			
			//속성 접근 방법2 : 노드객체의 getAttributes()이용 (속성 노드 이용)
			NamedNodeMap nodeMap = firstBookNode.getAttributes();
			System.out.println("노드 객체의 getAttrubutes() 이용 => "+nodeMap.getNamedItem("isbn").getNodeValue());
			
			//하이 엘리먼트 접근방법 2 : getchildnodes()메서드 이용 
			NodeList firstBookChildNodeList = firstBookNode.getChildNodes();
			
			//엔터키에 해당하는 부분이 읽일 수 있으므로, getChildNodes()보다는 getElementsByTagName()을 이용해 접근하는게 좋다.
			Node titleNode = firstBookChildNodeList.item(1);	//0넣으면 엔터키를 입력받아서 오류 한줄로 다쓰면 오류없음 
			Element titleElement =(Element)titleNode;
			System.out.println("titleElement.getTagName() => "+titleElement.getTagName());
			System.out.println("titleElement.getTextContent() => "+titleElement.getTextContent());
			
			
			//전체 출력하기
			//속성값 : isbn, kind
			//엘리먼트 텍스트 값 : title, author, price
			System.out.println("====================================================================");
			for (int i = 0; i < bookNodeList.getLength(); i++) {
				Node bookNode = bookNodeList.item(i);
				Element element = (Element)bookNode;
				String isbn = element.getAttribute("isbn");
				String kind = element.getAttribute("kind");
				String title = element.getElementsByTagName("title").item(0).getTextContent();
				String author = element.getElementsByTagName("author").item(0).getTextContent();
				String price = element.getElementsByTagName("price").item(0).getTextContent();
				String str = String.format("%8s %10s %10s %10s %8s", isbn, kind, title, author, price);
			
				System.out.println(str);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new DomparsingTest().parsing();
	}
}
