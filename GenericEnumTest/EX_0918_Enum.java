package kr.or.ddit.basic;

/**
 * 문제) 태양계 행성을 나타내는 enum Planet을 이용하여 구하시오. (단, enum 객체 생성시 반지름을 이용하도록 정의하시오.)
 * 
 * 예) 행성의 반지름(KM): 수성(2439), 금성(6052), 지구(6371), 성(3390), 목성(69911), 토성(58232),
 * 천왕성(25362), 해왕성(24622)
 */
public class EX_0918_Enum {
	public enum Planet {
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);

		private double num;

		Planet(double num2) {
			num = num2;
		}

		public double getNum() {
			return num;
		}

	}

	public static void main(String[] args) {
		Planet[] enumArr = Planet.values();
		for (int i = 0; i < enumArr.length; i++) {
			System.out.println(enumArr[i] + " 반지름 : " +(int)enumArr[i].getNum()+"KM" );
			System.out.println(enumArr[i] +" 면적 :" + (4*3.14*enumArr[i].getNum()*enumArr[i].getNum())+" KM^2");
			System.out.println("======================================================================================");
		}
		System.out.println();
	}
}