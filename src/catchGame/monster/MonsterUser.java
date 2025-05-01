package catchGame.monster;



/**
 * 새롭게 추가된 유저 클래스
 * 처음 시작했을떄 기본으로 설정되는 유저
 */
public class MonsterUser extends MonsterBase {
	
	public MonsterUser() {
		this.filePath = "T:\\github\\java-catchGame_bang\\src\\catchGame\\monster\\유저.txt";
		this.reverseFilePath = "T:\\github\\java-catchGame_bang\\src\\catchGame\\monster\\유저.txt";
		this.maxHP = 10000;
		this.ATT = 5;
		this.currentHP = this.maxHP;
		this.currentHPPercentage();
		this.present = "유저이다 죽으면 게임이 끝난다";
		this.ifCatch = true;
		this.name = "User";
	}


}
