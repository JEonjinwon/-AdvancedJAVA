package kr.or.ddit.structural.decorator;

public class RedShapeDecorator extends ShapeDecorator {

	public RedShapeDecorator(Shape decoratedShape) {
		super(decoratedShape);
		
	}

	@Override
	public void draw() {
		decoratedShape.draw();
		
		setRedBorder(decoratedShape);
	}

	private void setRedBorder(Shape decoratedShape) {
		System.out.println("빨간색 경계선 칠하기");
	}
}
