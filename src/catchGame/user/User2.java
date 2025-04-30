package catchGame.user;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

import catchGame.map.MapExploring;
import catchGame.monster.MonsterArrays;
import catchGame.monster.MonsterBase;

public class User2 {
	public String userName; // 사용자 이름
	public String location; // 사용자 위치
	public PokeDexClass pokeDex; // 사용자 도감
	public MapExploring mapExploring; // 맵 정보
	private MonsterBase[] myPoket; // 잡은 몬스터 배열
	private int myPoketCnt; // 잡은 몬스터 수
	private LocalDateTime startTime; // 플레이 시작 시간

	Scanner scanner = new Scanner(System.in);

	// User 객체 생성 시 이름 입력받고, 새로운 도감 불러옴
	public User2() {
		try {
		    System.out.print("당신의 이름은? ");
		    String input = scanner.nextLine();
		    this.userName = customTrim(input);

		    if (customIsEmpty(this.userName)) {
		        System.out.println("⚠️ 이름은 공백일 수 없습니다. 기본 이름으로 설정합니다.");
		        this.userName = "트레이너";
		    }
		} catch (Exception e) {
		    System.out.println("⚠️ 입력 도중 오류가 발생했습니다. 기본 이름으로 설정합니다.");
		    this.userName = "트레이너";
		}
		
		this.pokeDex = new PokeDexClass();
		this.pokeDex.insertPokeDex();
		this.mapExploring = new MapExploring();
		this.location = "집";
		this.startTime = LocalDateTime.now();
		this.myPoket = new MonsterBase[100];
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

	// 현재 지역에 맞는 몬스터 정보 가져와서 몬스터 등장 및 포획
	private void loadFightMonster(MonsterArrays monsterArrays, String mapInfo, boolean isCatch)
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
			this.checkMonster(monster); // 몬스터 조우 및 등장 문구 출력
			Thread.sleep(500);
			this.catchFightMonster(monster, isCatch); // 몬스터 포획
		}
	}

	// 몬스터와의 조우 이벤트를 처리하고, 해당 몬스터의 등장 메시지를 출력
	private void checkMonster(MonsterBase monster) {
		if (monster.isMet == 1) {
			System.out.println("\n💥 몬스터를 만났다! 💥\n");
			monster.appearanceComment();
		} else {
			System.out.println("\n😢 몬스터가 만나지 못했다... 😢\n");
		}
	}

	// 몬스터 포획
	private void catchFightMonster(MonsterBase monster, boolean isCatch) throws InterruptedException {
		if (!monster.name.equals("기본")) {
			String userChoice = this.checkUserChoice(monster); // 유저 선택
			String catchMonsterName = monster.name;
			if (userChoice.equalsIgnoreCase("Y")) {
				// 몬스터가 기본이 아닐 경우 포획 로직
				System.out.println("\n>> 싸우는 중");
				Thread.sleep(500);
				System.out.println(">> ...");
				Thread.sleep(500);
				System.out.println(">> ...\n");
				Thread.sleep(500);
				this.fightMonster(monster, isCatch, catchMonsterName);
			}
			return;
		}
	}

	// 몬스터 조우 시 유저에게 싸울지 여부를 입력받아 반환
	private String checkUserChoice(MonsterBase monster) {
		System.out.print("\n>> 싸우시겠습니까?(Y/N) ");
		String userChoice = "";
		while (true) {
			userChoice = scanner.nextLine();
			if (userChoice.equalsIgnoreCase("Y") || userChoice.equalsIgnoreCase("N")) {
		        break;
		    } else {
		        System.out.println("⚠️ 잘못된 입력입니다. 'Y' 또는 'N'을 입력해주세요.");
		    }
		}
		return userChoice;
	}

	// 유저가 싸우기를 선택했을 경우 포획 로직을 수행
	private void fightMonster(MonsterBase monster, boolean isCatch, String catchMonsterName)
			throws InterruptedException {
		this.catchMonster(monster, isCatch, catchMonsterName);
		return;
	}

	// 몬스터 포획 시 실행
	private void catchMonster(MonsterBase monster, boolean isCatch, String catchMonsterName)
			throws InterruptedException {
		if (monster.runMonster()) {
			isCatch = monster.catchMonster();
			catchMonsterName = monster.name;
			if (isCatch) {
				this.myPoket[myPoketCnt] = monster;
				myPoketCnt++;
				System.out.println("✨ 띠링! " + catchMonsterName + "이(가) 포켓몬 도감에 등록되었습니다!");
				this.updateMyPokeDex(catchMonsterName);
			}
		}
	}

	// 몬스터를 잡았을 때 잡은 몬스터의 이름을 기준으로 도감 정보 최신화
	private void updateMyPokeDex(String monsterName) {
		this.pokeDex.updatePokeDex(monsterName);
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
		
		System.out.println("사용자명: " + this.userName);
		System.out.println("사용자 위치: " + (this.location.equals("취소") ? "집" : this.location));
		System.out.println("플레이 시간: " + getPlayTime());
		System.out.println("잡은 몬스터 수: " + myPoketCnt + "마리");
		System.out.println("현재 잡은 몬스터\n");
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
}