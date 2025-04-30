package catchGame.monster;

public class Monster10 extends MonsterBase {

	public Monster10() {
		this.filePath = "T:\\github\\java-catchGame_bang\\src\\catchGame\\monster\\아르세우스.txt";
		this.reverseFilePath = "T:\\github\\java-catchGame_bang\\src\\catchGame\\monster\\아르세우스 야생.txt";
		this.maxHP = 100000;
		this.ATT = 1000;
		this.currentHP = this.maxHP;
		this.currentHPPercentage();
		this.name = "아르세우스";
		this.spawnMap = "우주";
		this.present = "??? 타입 포켓몬 | '나는 창조자다', 근데 아직도 만들다 만 게임 코드에서 벗어나지 못한 불사신 포켓몬, ";
	}

	// 마주쳤을 때
	// 등장멘트
	@Override
	public void appearanceComment() {
		System.out.println("=========포켓몬 조우=========");
		this.probabilityValue = r.nextInt(3);
		if (this.probabilityValue == 0) {
			System.out.println("\"⚡시공간 서버 터졌습니다⚠️ 갑자기 하늘이 로그아웃하더니…\"\n" + "➤ [SYSTEM] 전설의 관리자 '아르세우스'님이 접속하셨습니다.\n"
					+ "(✨그는 버그조차 아름답게 만드는 존재… 로그인 효과 실화냐?)");
		} else if (this.probabilityValue == 1) {
			System.out.println("\"📡 우주 데이터 수신 중... 띠링! [신]이 도착했습니다.\"\n"
					+ "➤ \"내가 이 세상 만든 사람인데… 여기 왜 이래? 누가 설정 엉망으로 해놨어?\"\n" + "(아르세우스, 진짜 말투는 나긋하지만 뼈 때리는 스타일)");
		} else {
			System.out.println("\"💥하늘이 블루스크린 뜨더니 갑자기 세상이 리셋된 느낌…\"\n" + "➤ \"이 세계는 내가 켰고, 내가 껄 수도 있어. 뭐, 니 선택이야.\"\n"
					+ "(말 끝나자마자 배경이 흑백으로 변함;; 뭔가 각 잡혔다.)");
		}
		System.out.println();
	}

	// 도망칠 확률 "개별사건"
	// 0%
	// Monster10.runMonster()은 무조건 true 반환
	@Override
	public boolean runMonster() {
		System.out.println("=========포켓몬이 도망치지 않았다!=========");
		this.probabilityValue = r.nextInt(3);
		if (this.probabilityValue == 0) {
			System.out.println("\"그는 조용히 널 바라보다가, 천천히 몸을 일으킨다.\"\n" + "➤ \"좋아. 오랜만에 심심하지 않은 상대네. 어디, 좀 움직여볼까.\"\n"
					+ "(공기 밀도가 바뀌는 느낌. 로그에 '전투모드: ON'이 떴다.)");
		} else if (this.probabilityValue == 1) {
			System.out.println("\"눈빛이 바뀌었다. 장난 끊고, 진짜 각 잡은 듯.\"\n" + "➤ \"도망 안 가는 타입이네. 그럼… 나도 좀 진심 낼게.\"\n"
					+ "(주변 이펙트가 쏴아아~ 하고 켜진다. 아르세우스: [시동 거는 중…])");
		} else {
			System.out.println("\"그는 입꼬리를 살짝 올리며 말했다. 마치 게임 시작 버튼 누른 듯.\"\n"
					+ "➤ \"와, 재밌겠다. 근데 너 진짜 괜찮겠어? 이거 세이브 안 돼.\"\n" + "(전투 필드가 자동으로 펼쳐진다. 너도 모르게 준비자세 들어감.)");
		}
		System.out.println();
		return true; // 안 도망침, 무조건 true
	}

	// 잡힐 확률 "개별사건"
	// 0.0023% => 23/1000000
	// 반환 값 => 잡혔을 때:true | 아닐 때:false
	@Override
	public boolean catchMonster() throws InterruptedException {
		delayTime();
		int totalCatchPercent = (int)(100 - 99*(double)this.HPPercentage/100);
		// 잡혔을 때 true
		if (this.r.nextInt(100) < totalCatchPercent) {
			System.out.println("=========포켓몬 포획 성공!=========");
			if (this.probabilityValue == 0) {
				System.out.println("\""+ totalCatchPercent + "% 확률로 헉?! 볼 안에 빨려 들어간다… 시스템 오류인가? 아니 이건—\"\n"
						+ "➤ \"...진짜로 날 잡았다고? 와, 이건 좀… 예상 밖인데.\"\n" + "(볼 안에서 잠깐의 침묵. 그리고 천천히 울리는 진입음. [포획 성공])");
			} else if (this.probabilityValue == 1) {
				System.out.println("\""+ totalCatchPercent + "% 확률로 빛이 꺼지고, 볼이 딱! 하고 멈춘다. 이건 진짜…\"\n"
						+ "➤ \"됐어. 진 사람 말 없어. 대신—다음엔 너 진짜 감당해야 해.\"\n" + "(목소리는 사라졌지만 어딘가 의미심장한 기운이 남는다.)");
			} else {
				System.out.println("\""+ totalCatchPercent + "% 확률로 딱, 딱, 딱… 그리고 '퐁'. 포획 완료.\"\n"
						+ "➤ \"진짜 이래도 되는 거야…? 나 정도 되는 애가 이렇게…\"\n" + "(그 순간, 네 포켓볼에서 미묘한 빛이 한 번 더 퍼졌다. 뭔가 심상치 않다.)");
			}
			System.out.println();
			this.ifCatch = true;
			return true;
		}
		// 안잡혔을 때 false
		System.out.println("=========포켓몬 포획 실패!=========");
		if (this.probabilityValue == 0) {
			System.out.println("\""+ (100 -totalCatchPercent) + "% 확률로 볼에서 튕겨나온 순간, 차원이 잠깐 뒤틀린다.\"\n"
					+ "➤ \"...어이, 진짜 나를 잡으려고 한 거야? 그건 좀 무리수지.\"\n" + "(아르세우스는 고개를 저으며, 여유롭게 하늘로 올라간다. [회피됨])");
		} else if (this.probabilityValue == 1) {
			System.out.println("\""+ (100 -totalCatchPercent) + "% 확률로 볼을 찢고 튀어나오며, 말없이 하늘을 응시한다.\"\n"
					+ "➤ \"...넌 아직 준비가 안 된 것 같아. 다음에 다시 와. 그땐 진심으로 보지.\"\n" + "(말투는 담담하지만, 뭔가 아쉽다는 듯한 기색도 있다.)");
		} else {
			System.out.println("\""+ (100 -totalCatchPercent) + "% 확률로 포획 시도 실패. 그 순간, 빛이 한 번 크게 터진다.\"\n"
					+ "➤ \"넌 이제 내 존재를 봤어. 그걸로도 꽤 대단한 거야.\"\n" + "(아르세우스는 그렇게 사라진다. 마치 시험이 끝난 것처럼.)");
		}
		System.out.println();
		return false;
	}

}
