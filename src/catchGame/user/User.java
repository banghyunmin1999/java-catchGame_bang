package catchGame.user;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

import catchGame.manage.TextBoxClass;
import catchGame.map.MapExploring;
import catchGame.monster.MonsterArrays;
import catchGame.monster.MonsterBase;
import catchGame.monster.MonsterUser;
import catchGame.monster.PrintImgClass;


/**
 * [기존] 유저 행동관리 클래스
 * [new] 전체 출력 텍스트 박스화, 자신이 잡은 포켓몬을 직접 도감에 등록하게끔 변경(기존 이름만 주고 도감에서 변경), 여러 메소드들 편집과 몬스터 싸움 메소드 신규 생성
 */
public class User {
	public String userName; // 사용자 이름
	public String location; // 사용자 위치
	public PokeDexClass pokeDex; // 사용자 도감
	public MapExploring mapExploring; // 맵 정보
	private MonsterBase[] myPoket; // 잡은 몬스터 배열
	private int myPoketCnt; // 잡은 몬스터 수
	private LocalDateTime startTime; // 플레이 시작 시간
	private MonsterBase usermonster; // [new] 유저가 선택한 몬스터
	PrintImgClass printImgClass; // [new] 유저 몬스터 이미지 출력 준비
	Scanner scanner = new Scanner(System.in);

	// User 객체 생성 시 이름 입력받고, 새로운 도감 불러옴
	public User() {
		try {
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox("당신의 이름은?");
			TextBoxClass.printTextBoxEnd();
		    String input = scanner.nextLine();
		    this.userName = customTrim(input);

		    if (customIsEmpty(this.userName)) {
				TextBoxClass.printTextBoxStart();
				TextBoxClass.printTextBox("이름은 공백일 수 없습니다. 기본 이름으로 설정합니다.");
				TextBoxClass.printTextBoxEnd();
		        this.userName = "트레이너";
		    }
		} catch (Exception e) {
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox("입력 도중 오류가 발생했습니다. 기본 이름으로 설정합니다.");
			TextBoxClass.printTextBoxEnd();
		    this.userName = "트레이너";
		}
		
		this.pokeDex = new PokeDexClass();
		this.pokeDex.insertPokeDex();
		this.mapExploring = new MapExploring();
		this.location = "집";
		this.startTime = LocalDateTime.now();
		this.myPoket = new MonsterBase[100];
		this.usermonster = new MonsterUser(); // 기본 유저
		this.printImgClass = new PrintImgClass();
	}

	// 사용자의 도감 출력
	public void printMyPokeDex() {
		this.pokeDex.printPokeDex();
	}

	// 몬스터 포획 메소드
	public void catchMonster() throws InterruptedException {
		// 몬스터 배열 객체 선언
		MonsterArrays monsterArrays = new MonsterArrays();

		boolean isCatch = false; // 포획 여부

		// 현재 지역에 맞는 몬스터 정보 가져와서 몬스터 등장 및 포획
		this.loadFightMonster(monsterArrays, this.location, isCatch);
	}

	/**
	 * [기존] 현재 지역에 맞는 몬스터 정보 가져와서 몬스터 등장 및 포획
	 * [new] → 현재 지역에 맞는 몬스터 정보 가져와서 몬스터 등장 및 선택
	 * @param monsterArrays
	 * @param mapInfo
	 * @param isCatch
	 * @throws InterruptedException
	 */
	public void loadFightMonster(MonsterArrays monsterArrays, String mapInfo, boolean isCatch)
			throws InterruptedException {
		String nowLocation = mapInfo;
		if (this.location.equals(nowLocation)) {
			MonsterBase monster = null;
			switch (nowLocation) {
			case "하늘":
				monster = monsterArrays.skyMonsters();
				break;
			case "바다":
				monster = monsterArrays.seaMonsters();
				break;
			case "땅":
				monster = monsterArrays.earthMonsters();
				break;
			case "우주":
				monster = monsterArrays.universeMonsters();
				break;
			}
			if(this.checkMonster(monster)) {// 몬스터 조우 및 등장 문구 출력
				Thread.sleep(500);
				userAction(monster, isCatch);
			}else {
				return;
			}
			
		}
	}
	
	/**
	 * [기존] 유저 선택 클래스
	 * [new] 몬스터 교체 추가
	 * @param monster
	 * @param isCatch
	 * @throws InterruptedException
	 */
	public void userAction(MonsterBase monster , boolean isCatch) throws InterruptedException{
		this.printImgClass.inputMonster(monster);
		this.printImgClass.runReverseImgClass();
		this.printImgClass.inputMonster(this.usermonster);
		this.printImgClass.runImgClass();
		TextBoxClass.printTextBoxStart();
		TextBoxClass.printTextBox("1.싸운다");
		TextBoxClass.printTextBox("2.포획한다");
		TextBoxClass.printTextBox("3.도망간다");
		TextBoxClass.printTextBox("4.내 몬스터 교체");
		TextBoxClass.printTextBoxEnd();
		if(monster.name.equals("기본")) {
			return;
		}
		switch (this.checkUserChoice()) {// 유저 선택
			case "1" :  this.fightMonster(monster); 
				break;
			case "2" :  this.catchFightMonster(monster, isCatch); 
				break;
			case "3" :  return;
			case "4" : this.changeMyMonster(); 
				break;
		}
	}
	
	/**
	 * [기존]몬스터와의 조우 이벤트를 처리하고, 해당 몬스터의 등장 메시지를 출력
	 * [new] 못만났을경우 바로 돌아가는걸 판단하기 위해 return 추가
	 * @param monster
	 * @return
	 */
	private boolean checkMonster(MonsterBase monster) {
		if (monster.isMet == 1) {
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox("몬스터를 만났다!");
			TextBoxClass.printTextBoxEnd();
//			TextBoxClass.runPrintTextBox("💥 몬스터를 만났다! 💥");
			monster.appearanceComment();
			return true;
		} else {
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox("몬스터를 만나지 못했다...");
			TextBoxClass.printTextBoxEnd();
//			TextBoxClass.runPrintTextBox("😢 몬스터를 만나지 못했다... 😢");
			return false;
		}
	}
	
	/**
	 * 새롭게 만든 몬스터 싸움 메소드
	 * @param monster
	 * @throws InterruptedException
	 */
	// [new]몬스터 싸움
	private void fightMonster(MonsterBase monster) throws InterruptedException {
		TextBoxClass.printTextBoxStart();
		TextBoxClass.printTextBox(">> 싸우는 중");
		TextBoxClass.printTextBoxEnd();
		Thread.sleep(500);
		TextBoxClass.printTextBoxStart();
		TextBoxClass.printTextBox(">> ...");
		TextBoxClass.printTextBoxEnd();
		Thread.sleep(500);
		TextBoxClass.printTextBoxStart();
		TextBoxClass.printTextBox(">> ...");
		TextBoxClass.printTextBoxEnd();
		Thread.sleep(500);
		TextBoxClass.printTextBoxStart();
		TextBoxClass.printTextBox(">> " + this.usermonster.name + "이"  + monster.ATT + "만큼 피해를 입었다.");
		TextBoxClass.printTextBox(">> " + monster.name + "에게"  + this.usermonster.ATT + "만큼 피해를 주었다.");
		TextBoxClass.printTextBoxEnd();
		Thread.sleep(1000);
		
		if(monster.attackedAction(this.usermonster)) {
			monster.currentHPPercentage();
			this.usermonster.currentHPPercentage();
			this.userAction(monster, false);

		}else {
			TextBoxClass.printTextBoxStart();
			TextBoxClass.printTextBox("몬스터가 죽어버렸다....");
			TextBoxClass.printTextBoxEnd();
			return;
		}
	}
	
	/**
	 * [기존] 몬스터 포획 메소드
	 * [new] 싸운다 → 포획한다 수정  , 필요없는 부분들 수정
	 * @param monster
	 * @param isCatch
	 * @throws InterruptedException
	 */
	private void catchFightMonster(MonsterBase monster, boolean isCatch) throws InterruptedException {
		TextBoxClass.printTextBoxStart();
		TextBoxClass.printTextBox(">> 포획하는 중");
		TextBoxClass.printTextBoxEnd();
		Thread.sleep(500);
		TextBoxClass.printTextBoxStart();
		TextBoxClass.printTextBox(">> ...");
		TextBoxClass.printTextBoxEnd();
//		TextBoxClass.runPrintTextBox(">> ...");
		Thread.sleep(500);
		TextBoxClass.printTextBoxStart();
		TextBoxClass.printTextBox(">> ...");
		TextBoxClass.printTextBoxEnd();
//		TextBoxClass.runPrintTextBox(">> ...");
		Thread.sleep(500);
		this.catchTryMonster(monster, isCatch);
	}
	/**
	 *  [new] 싸운다 , 포획한다 , 도망간다 선택지로 변경후 그에 맞게 수정
	 * @return
	 */

	public String checkUserChoice() {
		String userChoice = "";
		while (true) {
			userChoice = scanner.nextLine();
			if (userChoice.equalsIgnoreCase("1") || userChoice.equalsIgnoreCase("2") || userChoice.equalsIgnoreCase("3") ||  userChoice.equalsIgnoreCase("4")) {
		        break;
		    } else {
				TextBoxClass.printTextBoxStart();
				TextBoxClass.printTextBox("잘못된 입력입니다. 1,2,3,4중에 하나를 입력해주세요.");
				TextBoxClass.printTextBoxEnd();
//		        TextBoxClass.runPrintTextBox("⚠️ 잘못된 입력입니다. 1,2 3중에 하나를 입력해주세요.");
		    }
		}
		return userChoice;
	}

	// 유저가 싸우기를 선택했을 경우 포획 로직을 수행
	private void catchTryMonster(MonsterBase monster, boolean isCatch)
			throws InterruptedException {
		this.catchMonster(monster, isCatch);
		return;
	}

	// 몬스터 포획 시 실행
	private void catchMonster(MonsterBase monster, boolean isCatch)
			throws InterruptedException {
		if (monster.runMonster()) {
			isCatch = monster.catchMonster();
			if (isCatch) {
				this.myPoket[myPoketCnt] = monster;
				myPoketCnt++;
				TextBoxClass.printTextBoxStart();
				TextBoxClass.printTextBox("띠링! " + monster.name + "이(가) 포켓몬 도감에 등록되었습니다!");
				TextBoxClass.printTextBoxEnd();
				this.updateMyPokeDex(monster);
			}
		}else {
			return;
		}
	}

	// 몬스터를 잡았을 때 잡은 몬스터의 이름을 기준으로 도감 정보 최신화
	private void updateMyPokeDex(MonsterBase monster) {
		this.pokeDex.updatePokeDex(monster);
	}

	// 도감 검색
	public void searchTotalPokeDex() {
		this.pokeDex.searchPokeDex();
	}

	// 맵 선택
	public void selectMap() throws InterruptedException {
		MapExploring newMap = new MapExploring();
		newMap.mapInput(this.mapExploring.mapIterationCount);
		this.mapExploring = newMap;
		this.location = newMap.mapProbabilityAnswer;
	}

	// 유저 정보 출력
	public void printUserInfo() {
		int lineLimit = 2;
		int countOnLine = 0;
		// 중복 여부 확인
	    boolean isDuplicate = false;
		TextBoxClass.printTextBoxStart();
		TextBoxClass.printTextBox("사용자명: " + this.userName);
		TextBoxClass.printTextBox("사용자 위치: " + (this.location.equals("취소") ? "집" : this.location));
		TextBoxClass.printTextBox("플레이 시간: " + getPlayTime());
		TextBoxClass.printTextBox("잡은 몬스터 수: " + myPoketCnt + "마리");
		TextBoxClass.printTextBox("현재 잡은 몬스터");
		TextBoxClass.printTextBoxEnd();
		for (int i = 0; i < this.myPoketCnt; i++) {
		    if (this.myPoket[i] == null) continue;
		    
		    for (int j = 0; j < i; j++) {
		        if (this.myPoket[j] != null && this.myPoket[i].name.equals(this.myPoket[j].name)) {
		            isDuplicate = true;
		            break;
		        }
		    }
		    if (isDuplicate) continue;

		    // 출력
		    if (countOnLine > 0) System.out.print(", ");
		    System.out.print("🎯" + this.myPoket[i].name);
		    countOnLine++;

		    if (countOnLine == lineLimit) {
		    	System.out.println();
		        countOnLine = 0;
		    }
		}
	}
	
	// 앞뒤 공백 제거
	public static String customTrim(String input) {
	    if (input == null) return "";
	    int start = 0;
	    int end = input.length() - 1;

	    while (start <= end && Character.isWhitespace(input.charAt(start))) {
	        start++;
	    }
	    while (end >= start && Character.isWhitespace(input.charAt(end))) {
	        end--;
	    }

	    return input.substring(start, end + 1);
	}

	// 문자열이 비었는지 확인
	public static boolean customIsEmpty(String input) {
	    return input == null || input.length() == 0;
	}

	// 플레이 시간을 사용자 친화적으로 변환
    private String getPlayTime() {
        Duration duration = Duration.between(startTime, LocalDateTime.now());
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        return String.format("%02d시간 %02d분 %02d초", hours, minutes, seconds);
    }
    
    /**
     * 새롭게 추가한 자신의 출전 몬스터 교체 클래스
     */
    public void changeMyMonster() {
    	this.usermonster = pokeDex.changeMonster(this.usermonster);
    }
}