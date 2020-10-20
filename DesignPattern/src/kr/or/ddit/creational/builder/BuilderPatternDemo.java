package kr.or.ddit.creational.builder;

public class BuilderPatternDemo {
	public static void main(String[] args) {
		Member member = new Member.Builder("전진원", 25).birthDate("19960707").hobby("독서").build();
		
	
		System.out.println(member);
	}
}
