package kr.or.ddit.structural.decorator;

public class DecroatorPatternDemo {

	public static void main(String[] args) {
		Shape circle = new Circle();
		
		Shape redCircle = new RedShapeDecorator(new Circle());
		
		Shape redRectangle = new RedShapeDecorator(new Rectangle());
		System.out.println("일반적인 원그리기");
		circle.draw();
		System.out.println("================================");
		System.out.println("빨간 경계선 가진 원 원그리기");
		redCircle.draw();
		System.out.println("================================");
		System.out.println("빨간 경계선 가진  직사각형그리기");
		redRectangle.draw();
		System.out.println("================================");
		
		
	}

}
