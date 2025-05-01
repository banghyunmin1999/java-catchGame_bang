package catchGame.monster;

import catchGame.manage.TextBoxClass;
/**
 * [기존] 몬스터4 클래스
 * [new] 이미지와 전투부분, 출력 텍스트박스화 , 확률 조정과 출력부분조정
 */
public class Monster4 extends MonsterBase {

	public Monster4() {
		this.filePath = "T:\\github\\java-catchGame_bang\\src\\catchGame\\monster\\마자용.txt";
		this.reverseFilePath = "T:\\github\\java-catchGame_bang\\src\\catchGame\\monster\\마자용 야생.txt";
		this.maxHP = 500;
		this.ATT = 30;
		this.currentHP = this.maxHP;
		this.currentHPPercentage();
		this.name = "마자용";
		this.spawnMap = "땅";
		this.present = "에스퍼 타입 포켓몬 | 뭐가 맞다는지 모르겠다, 쳐맞고싶다는건가?";
	}

	// 마주쳤을 때
	// 등장멘트
	@Override
	public void appearanceComment() {
		TextBoxClass.runPrintTextBox("=========포켓몬 조우=========");
		this.probabilityValue = r.nextInt(3);
		if (this.probabilityValue == 0) {
			TextBoxClass.runPrintTextBox("\"마자용이 느긋하게 바닥에 누워 있다… 싸우려는 건지, 낮잠 자러 나온 건지 알 수 없다.\""
					+ " \"마~자~용... (좀 이따 시작하자...)\"" + "(기지개를 켜는 마자용, 전투보다 스트레칭이 우선!)");
		} else if (this.probabilityValue == 1) {
			TextBoxClass.runPrintTextBox("\"불길한 기운이 감돈다… 마자용이다! 잔잔한 음악을 틀어줘야 할 것 같은 분위기다.\"" + " \"마...자...용~\""
					+ "(눈빛은 멍하지만, 뭔가 한 건 할 것 같은 포스가 흐른다!)");
		} else {
			TextBoxClass.runPrintTextBox("\"마자용이 나타났다! 갑자기 바닥에 누워 불꽃을 피우며 등장했다!\"" + " \"마자~용! (누워도 강하다구~)\""
					+ "(자고 있는 건지, 준비 중인 건지 알 수 없지만… 존재감은 만렙!)");
		}
		
	}

	// 도망칠 확률 "개별사건"
	// 9% => 9/100
	// 랜덤 값 1~9 나오면 도망, 그외 숫자 안 도망침
	// 반환 값 => 도망칠 때:false | 안도망칠 때:true
	@Override
	public boolean runMonster() {
		this.probabilityValue = r.nextInt(3);

		// 도망칠 때 false
		if (this.r.nextInt(100) + 1 <= 9) {
			TextBoxClass.runPrintTextBox("=========포켓몬이 도망쳤다!=========");
			if (this.probabilityValue == 0) {
				TextBoxClass.runPrintTextBox("\"9% 확률로 마자용이 도망쳤다! 엎드려 있다가 갑자기 연기를 뿜으며 사라졌다!\""
						+ " \"마자~용~ (여긴 나랑 안 맞는 분위기야~)\"" + "(도망도 예술처럼 하는 마자용, 멍한 표정은 그대로.)");
			} else if (this.probabilityValue == 1) {
				TextBoxClass.runPrintTextBox("\"9% 확률로 마자용이 조용히 뒤로 기어가더니 사라졌다! 보고도 못 믿을 마성의 퇴장!\""
						+ " \"마...자...용~ (언제 싸우냐 했는데, 아 나 간다~)\"" + "(전투를 시작하지 않고 이탈하는 클래식 마자용 스타일.)");
			} else {
				TextBoxClass.runPrintTextBox("\"9% 확률로 마자용이 불꽃을 남기고 도망쳤다! ‘있었는지도 몰랐는데… 없어진다’라는 전설을 남김.\"" + " \"마자~ (bye~)\""
						+ "(자세는 늘어져도 도망은 스피디하다!)");
			}
			
			return false;
		}
		// 안 도망칠 때 true
		TextBoxClass.runPrintTextBox("=========포켓몬이 도망치지 않았다!=========");
		if (this.probabilityValue == 0) {
			TextBoxClass.runPrintTextBox("\"91% 확률로 마자용은 도망가지 않았다! 그냥 바닥에 누워 ‘가라고 해도 안 간다’는 포즈.\""
					+ " \"마자~용~ (어차피 어디 가긴 귀찮아~)\"" + "(전투보다 눕는 게 우선인 마자용.)");
		} else if (this.probabilityValue == 1) {
			TextBoxClass.runPrintTextBox("\"91% 확률로 마자용이 눈을 감았다... 하지만 도망치진 않았다! 자는 건지 버티는 건지 알 수 없다.\""
					+ " \"마...자... (지금 나 건들면 큰일 날걸?)\"" + "(이름값대로 맞는 걸 좋아하는거 같다, 살짝 소름이 돋는다)");
		} else {
			TextBoxClass.runPrintTextBox("\"91% 확률로 마자용이 그 자리에 가만히 있다! ‘도망? 나랑 그런 단어 안 어울려~’\""
					+ " \"마자~용~ (여기 누워있으면 아무도 못 건들겠지?)\"" + "(누워있는 게 전투 포지션인 마자용의 철학.)");
		}
		
		return true;
	}

	// 잡힐 확률 "개별사건"
	// 50% => 1/2
	// 반환 값 => 잡혔을 때:true | 아닐 때:false
	@Override
	public boolean catchMonster() throws InterruptedException {
		delayTime();
		
		int totalCatchPercent = (int)(100 - 50*(double)this.HPPercentage/100);
		// 잡혔을 때 true
		if (this.r.nextInt(100) < totalCatchPercent) {
			TextBoxClass.runPrintTextBox("=========포켓몬 포획 성공!=========");
			TextBoxClass.runPrintTextBox(
					""+ totalCatchPercent + "% 확률로 잡아냈다!" + "" + this.name + "의 눈이 제정신이 아닌거 같다.." + "(이런 녀석은 좀 아닌거 같은데.. 도감에 넣어야 되나?)");
			this.ifCatch = true;
			
			return true;
		}
		// 안 잡혔을 때 false
		TextBoxClass.runPrintTextBox("=========포켓몬 포획 실패!=========");
		TextBoxClass.runPrintTextBox(""+ (100-totalCatchPercent) + "% 확률로 놓치고 말았다" + "" + this.name + "의 모습이 보이지 않는다." + "(오히려 다행인거 같다..)");
		
		return false;
	}

}
