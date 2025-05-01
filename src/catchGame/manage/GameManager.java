package catchGame.manage;

import java.util.Scanner;

import catchGame.user.User;

public class GameManager {
	private boolean isRunning; // 게임 상태
	User user;

	Scanner scanner = new Scanner(System.in);
	String prevLocation;

	// 객체 생성 시 user 객체 생성, 게임 상태 true로 설정
	public GameManager() {
		user = new User();
		this.isRunning = true;
	}

	// 게임 메뉴 출력 메소드
	public void printGameMenu() throws InterruptedException {
//		ClearConsole.clearConsole();
		TextBoxClass.printTextBoxStart();
		TextBoxClass.printTextBox("=== 몬스터 잡기 게임 ===");
		TextBoxClass.printTextBox("1. 맵 선택하기");
		TextBoxClass.printTextBox("2. 몬스터 도감 보기");
		TextBoxClass.printTextBox("3. 몬스터 검색하기");
		TextBoxClass.printTextBox("4. 내 정보 보기");
		TextBoxClass.printTextBox("5. 게임 종료");
		TextBoxClass.printTextBox("6. 내 몬스터 교체");
		TextBoxClass.printTextBox("메뉴를 선택하세요 (1-6): ");
		TextBoxClass.printTextBoxEnd();
		String inputChoice = scanner.nextLine();
		inputChoice = inputChoice.replace(" ", "");
		switch (inputChoice) {
		case "1":
			this.user.selectMap();
			prevLocation = this.user.location;
			// 맵 선택이 취소되었거나 집으로 이동한 경우 홈으로
			if (this.user.location.equals("취소")) {
				TextBoxClass.printTextBoxStart();
				TextBoxClass.printTextBox(">> 맵 선택이 취소되었습니다. 홈으로 돌아갑니다.");
				TextBoxClass.printTextBoxEnd();
//				System.out.println("\n>> 맵 선택이 취소되었습니다. 홈으로 돌아갑니다.");
				return;
			}

			System.out.println();
			Thread.sleep(500);
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox("--" + user.location + "맵에 소환되었습니다" + "--");
			TextBoxClass.printTextBoxEnd();
//			System.out.println("--" + user.location + "맵에 소환되었습니다" + "--");
			Thread.sleep(500);
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox("(..두리번...두리번..)");
			TextBoxClass.printTextBoxEnd();
//			System.out.println("(..두리번...두리번..)");
			Thread.sleep(500);
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox(">> 몬스터를 탐색 중입니다.");
			TextBoxClass.printTextBoxEnd();
//			System.out.println("\n>> 몬스터를 탐색 중입니다.");
			Thread.sleep(500);
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox(">> ...");
			TextBoxClass.printTextBoxEnd();
//			System.out.println("\n>> ...");
			Thread.sleep(500);
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox(">> ...");
			TextBoxClass.printTextBoxEnd();
//			System.out.println("\n>> ...");

			user.catchMonster();
			printUserAction();
			break;

		case "2":
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox(">> 나의 몬스터 도감을 확인합니다.");
			TextBoxClass.printTextBoxEnd();
//			System.out.println("\n>> 나의 몬스터 도감을 확인합니다.");
			// 도감 출력 메서드 호출
			user.printMyPokeDex();
			printUserAction();
			break;
		case "3":
			user.searchTotalPokeDex();
			break;
		case "4":
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox(">> 내 정보를 확인합니다.");
			TextBoxClass.printTextBoxEnd();
//			System.out.println("\n>> 내 정보를 확인합니다.");
			// 사용자 정보 출력
			user.printUserInfo();
			break;
		case "5":
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox(">> 게임을 종료합니다. 감사합니다!");
			TextBoxClass.printTextBoxEnd();
//			System.out.println("\n>> 게임을 종료합니다. 감사합니다!");
			this.isRunning = false;
			break;
		case "6":
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox(">> 내 몬스터를 교체합니다");
			TextBoxClass.printTextBoxEnd();
			user.changeMyMonster();
			break;
		default:
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox("잘못된 입력입니다. 1 ~ 5 사이의 숫자를 입력하세요.");
			TextBoxClass.printTextBoxEnd();
//			System.out.println("잘못된 입력입니다. 1 ~ 5 사이의 숫자를 입력하세요.");
		}
	}

	public void printUserAction() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox("1. 계속 탐색하기 2. 다른 맵 이동 3. 도감 보기 4. 몬스터 검색 5. 홈으로 가기 6. 몬스터 교체");
			TextBoxClass.printTextBoxEnd();
//			System.out.println("\n1. 계속 탐색하기 2. 다른 맵 이동 3. 도감 보기 4. 몬스터 검색 5. 홈으로 가기");
			String ans = scanner.nextLine();
			ans = ans.replace(" ", "");

			switch (ans) {
			case "1":
				TextBoxClass.printTextBoxStart();
				TextBoxClass.printTextBox(">> 몬스터를 탐색 중입니다.");
				TextBoxClass.printTextBoxEnd();
//				System.out.println("\n>> 몬스터를 탐색 중입니다.");
				Thread.sleep(500);
				TextBoxClass.printTextBoxStart();
				TextBoxClass.printTextBox(">> ...");
				TextBoxClass.printTextBoxEnd();
//				System.out.println("\n>> ...");
				Thread.sleep(500);
				TextBoxClass.printTextBoxStart();
				TextBoxClass.printTextBox(">> ...");
				TextBoxClass.printTextBoxEnd();
//				System.out.println("\n>> ...");
				user.catchMonster();
				break;
			case "2":
				user.selectMap();

				// 사용자가 "취소"를 선택했을 경우
				if (this.user.location.equals("취소")) {
					TextBoxClass.printTextBoxStart();
					TextBoxClass.printTextBox(">> 맵 이동이 취소되었습니다. 현재 맵에서 계속 진행합니다.");
					TextBoxClass.printTextBoxEnd();
//					System.out.println("\n>> 맵 이동이 취소되었습니다. 현재 맵에서 계속 진행합니다.");
					user.location = prevLocation;
					user.catchMonster();
					break;
				}
				TextBoxClass.printTextBoxStart();
				TextBoxClass.printTextBox("--" + user.location + "맵에 소환되었습니다--");
				TextBoxClass.printTextBoxEnd();
//				System.out.println("\n--" + user.location + "맵에 소환되었습니다--");
				user.catchMonster();
				break;

			case "3":
				user.printMyPokeDex();
				break;
			case "4":
				user.searchTotalPokeDex();
				break;
			case "5":
				user.location = "집";
				return;
			case "6":
				user.changeMyMonster();
				break;
			default:
				TextBoxClass.printTextBoxStart();
				TextBoxClass.printTextBox("잘못된 입력입니다. 1 ~ 5 사이의 숫자를 입력하세요.");
				TextBoxClass.printTextBoxEnd();
//				System.out.println("잘못된 입력입니다. 1 ~ 5 사이의 숫자를 입력하세요.");
				printUserAction();
				break;
			}
		}
	}
	// [new] 콘솔 클리어 메소드
	public void claerConsole() {
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
	}
	// 게임 실행 메소드
	public void runGame() throws InterruptedException {
		while (this.isRunning) {
			this.printGameMenu();
		}
	}
}