package kr.or.ddid.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 문제 ) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 
 * 		Student 클래스를 만든다.
 * 		생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수를 받아서 처리한다.
 * 		
 * 		이 Student 객체들은 List에 저장하여 관리한다.
 * 		List에 저장한 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과 
 * 		총점의 역순으로 정렬하는 부분을 프로그램 하시오.
 * 		(총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
 * 		(학번 정렬 기준은 Student클래스 자체에서 제공하도록 하고, 
 * 		  총점 정렬기준은 외부클래스에서 제공하도록 한다.)
 *
 */
public class T04_StudentTest {
	public static void main(String[] args) {
		List<Student> List = new ArrayList<>();
		List.add(new Student("1560050", "전진원",100,100,100));
		List.add(new Student("1560049", "박찬", 100, 100, 100));
		List.add(new Student("1560005", "김성준", 40, 75, 85));
		List.add(new Student("1560023", "김선준", 30, 22, 56));
		List.add(new Student("1560001", "홍길동", 35, 75, 90));

	
		
		System.out.println("정렬전");
		for(Student student : List) {
			System.out.println(student);
		}
		System.out.println("=============================================================");
		Collections.sort(List);// 정렬
		System.out.println("학번 오름차순 정렬 후 : ");
		for(Student student : List) {
			System.out.println(student.toString());
		}
		System.out.println("=========================================================");
		
		Collections.sort(List, new StudentTotal() );//정렬
		System.out.println("총점의 역순 정렬  : ");
		int a = 1;
		for(Student student : List) {
			int b = List.get(a-1).total;
			
			for (int i = 0; i <List.size() ; i++) {
				if(b< List.get(i).total) {
					int c =student.getGrade();
					student.setGrade(c+1);	
					
				}
			}a++;
		
			System.out.println(student.toString());
		}
		System.out.println("==========================================================");

	}
}

class StudentTotal implements Comparator<Student>{

	@Override
	public int compare(Student st1, Student st2) {
		if(new Integer(st1.getTotal()).compareTo(st2.getTotal()) ==0) { //총점이 같으면 학번 순 
			return new String(st1.getHak()).compareTo(st2.getHak()) -1;
		}else {
			return new Integer(st1.getTotal()).compareTo(st2.getTotal())*-1 ; //총점 내림차순 
		}
	}

}





class Student implements Comparable<Student>{
	
	String hak;
	String name;
	int kor;
	int eng;
	int math;
	int total;
	int grade =1;
	
	
	


	
	@Override
	public String toString() {
		return "Student [hak=" + hak + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", total=" + total + ", grade=" + grade + "]";
	}

	public Student(String hak, String name, int kor, int eng, int math) {
		super();
		this.hak = hak;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		total = kor+eng+math;
	}
	
	
	
	@Override
	public int compareTo(Student st1) {
		
		return hak.compareTo(st1.getHak());
	}
	
	
	
	
	
	
	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getHak() {
		return hak;
	}
	
	public void setHak(String hak) {
		this.hak = hak;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getKor() {
		return kor;
	}
	
	public void setKor(int kor) {
		this.kor = kor;
	}
	
	public int getEng() {
		return eng;
	}
	
	public void setEng(int eng) {
		this.eng = eng;
	}
	
	public int getMath() {
		return math;
	}
	
	public void setMath(int math) {
		this.math = math;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}