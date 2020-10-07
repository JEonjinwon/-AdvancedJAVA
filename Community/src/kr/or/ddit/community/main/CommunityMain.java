package kr.or.ddit.community.main;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.community.service.CommunityServiceImpl;
import kr.or.ddit.community.service.ICommunityService;
import kr.or.ddit.community.vo.CommunityVO;

public class CommunityMain {

	Scanner sc = new Scanner(System.in);
	
	
	private ICommunityService cmService ;
	
	public CommunityMain() {
		cmService = new CommunityServiceImpl();
	}
	
		
		
	
	public static void main(String[] args) {
		CommunityMain cm = new CommunityMain();
		cm.start();

	}

	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 전체 목록 출력");
		System.out.println("  2. 새글 작성");
		System.out.println("  3. 글 수정");
		System.out.println("  4. 글 삭제");
		System.out.println("  5. 검색  ");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	/**
	 * 프로그램 시작메서드
	 */
	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = sc.nextInt(); // 메뉴번호 입력받기
			switch (choice) {
			case 1: // 전체 목록 출력
				displayAll();
				break;
			case 2: // 새글 작성
				write();
				break;
			case 3: // 수정
				update();
				break;
			case 4: // 삭제
				delete();
				break;
			case 5: // 검색
				search();
				break;
			case 6: // 작업 끝
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 6);
	}

	/**
	 * 글을 검색하는 메서드
	 */
	private void search() {
		sc.nextLine();//입력 버퍼 지우기
		System.out.println();
		System.out.println("검색할 정보를 입력하세요...");
		
		System.out.print("글 제목 >>");
		String board_title = sc.nextLine().trim();
		
		System.out.print("작성자 >>");
		String board_writer = sc.nextLine().trim();
		
		System.out.print("내용 >>");
		String board_content = sc.nextLine().trim();
		
		CommunityVO cv = new CommunityVO();
		cv.setBoard_title(board_title);
		cv.setBoard_writer(board_writer);
		cv.setBoard_content(board_content);
		
		System.out.println();
		System.out.println("===================================");
		System.out.println("글번호 \t제목\t작성자\t내용\t작성날짜");
		System.out.println("===================================");

		List<CommunityVO> cmList = cmService.search(cv);
		
		for (CommunityVO cmVO : cmList) {
			System.out.print(cmVO.getBoard_no()+"\t");
			System.out.print(cmVO.getBoard_title()+"\t");
			System.out.print(cmVO.getBoard_writer()+"\t");
			System.out.print(cmVO.getBoard_content()+"\t");
			System.out.print(cmVO.getBoard_date()+"\n");
		}
		System.out.println("=============================================");
		System.out.println("출력 작업 끝 ...");
		
		
	}

	/**
	 * 글을 삭제하는 메서드
	 */
	private void delete() {
		System.out.println();
		System.out.println("삭제할 글 제목을 입력하세요");
		System.out.print("글 제목 >> ");
		String board_title = sc.next();
		
		int cnt = cmService.delete(board_title);
		if(cnt>0) {
			System.out.println(board_title+" 글 정보 삭제 완료...");
		}else {
			System.out.println(board_title+" 글 정보 삭제 실패...");	
		}
	
		
	}

	/**
	 * 글을 수정하는 메서드
	 */
	private void update() {
		boolean chk = false;	//기존 회원 존재여부 체크
	
		String board_title="";
		do {
			System.out.println();
			System.out.println("글 제목을 입력하세요.");
			System.out.print("제목 >>");
			board_title = sc.next();
			
			chk = getCommunity(board_title);
			if(!chk) {
				System.out.println( board_title+"인 작성 글은 없습니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		} while (chk==false);
		
		System.out.println("작성자 >>");
		String board_writer=sc.next();
		sc.nextLine();	//입력버퍼 비우기
		System.out.println("글 내용 >>");
		String board_content = sc.nextLine();
		
		CommunityVO CV = new CommunityVO();
		
		CV.setBoard_title(board_title);
		CV.setBoard_writer(board_writer);
		CV.setBoard_content(board_content);
		
		int cnt = cmService.update(CV);
		if(cnt>0) {
			System.out.println(board_title+" 글수정 성공...");
		}else {
			System.out.println(board_title+" 글수정 실패...");
		}
		
		
	}

	/**
	 * 글을 작성하는 메서드
	 */
	private void write() {
		boolean chk = false;	//기존 회원 존재여부 체크
		String board_title="";
		do {
			System.out.println();
			System.out.println("작성할 글의 제목을 입력하세요.");
			System.out.print("제목 >>");
			board_title = sc.next();
			
			if(chk) {
				System.out.println("제목이"+ board_title+"인 글은 이미 존재합니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		} while (chk==true);
		
		System.out.println("작성자 >>");
		String board_writer=sc.next();
		System.out.println("글 내용 >>");
		String board_content = sc.next();
		
		CommunityVO CV = new CommunityVO();
		
		CV.setBoard_title(board_title);
		CV.setBoard_writer(board_writer);
		CV.setBoard_content(board_content);
		
		int cnt = cmService.write(CV);
		if(cnt>0) {
			System.out.println(board_title+" 글쓰기 성공...");
		}else {
			System.out.println(board_title+" 글쓰기 실패...");
		}
	}
	

	/**
	 * 글 전체를 출력하는 메서드
	 */
	private void displayAll() {
		System.out.println();
		System.out.println("===================================");
		System.out.println("글번호 \t제목\t작성자\t내용\t작성날짜");
		System.out.println("===================================");

		List<CommunityVO> cmList = cmService.displayAll();
		for (CommunityVO cmVO : cmList) {
			System.out.print(cmVO.getBoard_no()+"\t");
			System.out.print(cmVO.getBoard_title()+"\t");
			System.out.print(cmVO.getBoard_writer()+"\t");
			System.out.print(cmVO.getBoard_content()+"\t");
			System.out.print(cmVO.getBoard_date()+"\n");
		}
		System.out.println("=============================================");
		System.out.println("출력 작업 끝 ...");
		
		
	}

	/**
	 * 글 제목 중복 체크 
	 */
	private boolean getCommunity(String board_title) {
		return cmService.getCommunity(board_title);
	}
	
}
