package kr.or.ddit.creational.builder;

/**
* 빌더 패턴 : 인자가 많은 생성자나 정적팩토리가 필요한 클래스를 설계할 때 사용 
* 			특히, 대부분의 인자값이 선택적인 상황에 유리함.
* 
*  - 점층적 생성자 패턴보다 가독성이 좋아진다.
*  - 생성된 객체는 자바빈을 사용할 때보다 안전해진다.(Immutable 객체)
*/
public class Member {
	//필수값
	private String name;
	private int age;
	
	//선택값
	private String tel;
	private String addr;
	private String hobby;
	private String birthDate;


	public static class Builder{
		private String name;
		private int age;
		
		private String tel;
		private String addr;
		private String hobby;
		private String birthDate;
		
		public Builder(String name, int age) {
			this.name = name;
			this.age = age;
		}
		public Builder tel(String tel) {
			this.tel = tel;
			return this;
		}
		public Builder addr(String addr) {
			this.addr = addr;
			return this;
		}
		public Builder hobby(String hobby) {
			this.hobby = hobby;
			return this;
		}
		public Builder birthDate(String birthDate) {
			this.birthDate = birthDate;
			return this;
		}
		
		//Builder객체를 이용하여 Member객체 생성 
		public Member build() {
			return new Member(this);//this는 빌더 객체
		}
		
	}
	
	//생성자를 private 설정함 (Builder객체에서는 호출가능.)
	private Member(Builder builder) {	//private라서 외부에선 호출불가 내부에서만 호출가능 
		this.name = builder.name;
		this.age = builder.age;
		this.tel = builder.tel;
		this.addr = builder.addr;
		this.hobby = builder.hobby;
		this.birthDate = builder.birthDate;
	}


	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + ", tel=" + tel + ", addr=" + addr + ", hobby=" + hobby
				+ ", birthDate=" + birthDate + "]";
	}
	
}
