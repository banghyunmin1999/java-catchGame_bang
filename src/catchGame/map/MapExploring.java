package catchGame.map;

import java.util.Random;
import java.util.Scanner;

import catchGame.manage.TextBoxClass;


/**
 * [기존] 맵관련 클래스
 * [new] 전체적인 출력을 텍스트 박스 안에 출력시키게 조정
 */
public class MapExploring {
	public String[] map = { "하늘", "바다", "땅", "랜덤", "취소" };
	Scanner scanner = new Scanner(System.in);
	public String answerMap; // 고른 맵에 대한 대답 변수
	public int mapIterationCount; // 맵 선택하기 반복 횟수
	public String mapProbabilityAnswer; // 랜덤일 때 정해진 맵 이름에 대한 변수
	private int mapIndex = 2; // 맵 선택의 예외처리에서 기준이 되는 값
	public int mapReturn; // mapSelect 함수의 return 값으로 어떤 맵으로 이동했는지 확인만 가능

	// 하늘, 바다, 땅, 랜덤,취소 중에서 원하는 맵 선택하기 위한 함수
	// 맵 선택후 mapSelect 함수 호출, return 값으로 0,1,2를 반환
	// 0일 때는 랜덤과 취소를 제외한 값
	// 1일 때는 취소 눌렀을 때
	// 2일 때 잘못 눌렀을 때
	public void mapInput(int mapCount) throws InterruptedException {
		this.mapIterationCount = mapCount;
		while (this.mapIndex == 2) {
//			if (this.mapIterationCount < 1) {
//				TextBoxClass.runPrintTextBox("\n+++ 몬스터 잡기 게임 +++");
//			}
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox("맵을 선택하세요 ( 하늘 | 바다 | 땅 | 랜덤 | 취소)");
			TextBoxClass.printTextBoxEnd();
//			TextBoxClass.runPrintTextBox("🗺️ 맵을 선택하세요 (⛅ 하늘|🌊 바다|🌴 땅|🎲 랜덤|⚠️ 취소)");
			this.answerMap = scanner.nextLine();
			this.mapIndex = mapSelect(this.answerMap);
			if (this.mapIndex == 0) {
				Thread.sleep(500);
				TextBoxClass.printTextBoxStart();
				TextBoxClass.printTextBox(">> 맵 이동중입니다...");
				TextBoxClass.printTextBoxEnd();

				Thread.sleep(500);
				this.mapIterationCount++;
				break;
			}
			if (this.mapIndex == 1) {
				mapProbabilityAnswer = "취소";
				break;
			}

		}
	}

	// mapInput에서 대답한 것과 비교하여 조건에 맞는 이동 위치 설정
	// 랜덤일 때는 mapProbability를 호출하여 다시 맵을 고르고 mapProbabilityAnswer에 랜덤으로 정한 맵 이름 반환
	public int mapSelect(String answerMap) throws InterruptedException {
		if (answerMap.equals("하늘")) {
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox("하늘맵 페이지로 이동합니다");
			TextBoxClass.printTextBoxEnd();
			this.mapProbabilityAnswer = this.answerMap;
			this.mapReturn = 0;
		} else if (answerMap.equals("바다")) {
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox("바다맵 페이지로 이동합니다");
			TextBoxClass.printTextBoxEnd();
//			TextBoxClass.runPrintTextBox("🌊 바다맵 페이지로 이동합니다");
			this.mapProbabilityAnswer = this.answerMap;
			this.mapReturn = 0;
		} else if (answerMap.equals("땅")) {
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox("땅맵 페이지로 이동합니다");
			TextBoxClass.printTextBoxEnd();
//			TextBoxClass.runPrintTextBox("🌴 땅맵 페이지로 이동합니다");
			this.mapProbabilityAnswer = this.answerMap;
			this.mapReturn = 0;
		} else if (answerMap.equals("랜덤")) {
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox("=========랜덤맵 생성 중...=========");
			TextBoxClass.printTextBoxEnd();
//			TextBoxClass.runPrintTextBox("=========랜덤맵 생성 중...=========");
			Thread.sleep(500);
			String[] probabilityAnswer = mapProbability().split(" ");
			mapProbabilityAnswer = probabilityAnswer[1];
			TextBoxClass.runPrintTextBox(probabilityAnswer[0] + " " + probabilityAnswer[1] + " 맵 페이지로 이동합니다");
			this.mapReturn = 0;
		} else if (answerMap.equals("취소")) {
			this.mapReturn = 1;
		} else {
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox("잘못 입력하셨습니다");
			TextBoxClass.printTextBoxEnd();
//			TextBoxClass.runPrintTextBox("잘못 입력하셨습니다");
			this.mapReturn = 2;
		}
		return this.mapReturn;
	}

	// 랸덤 맵 선택했을 때 우주 맵이 나올 확률 20%로 확률 조작
	public String mapProbability() {
		Random random = new Random();
		int index = random.nextInt(15);
		if (index < 4) {
			return "☁️ 하늘";
		} else if (index < 8) {
			return "🌊 바다";
		} else if (index < 12) {
			return "🌴 땅";
		} else {
			return "🚀 우주";
		}
	}
}