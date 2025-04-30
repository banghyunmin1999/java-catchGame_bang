package catchGame.monster;

import catchGame.manage.TextBoxClass;
public class TestMonsterClass {
	public static void main(String[] args) {
		TextBoxClass.printTextBoxStart();
		TextBoxClass.printTextBox("디아루가가 나타났다 안녕하세여du");
		TextBoxClass.printTextBoxEnd();
		TextBoxClass.printTextBoxStart();
		TextBoxClass.printTextBox("안녕하세여");
		TextBoxClass.printTextBoxEnd();
		MonsterTest monsterTest = new MonsterTest();
		PrintImgClass printImgClass = new PrintImgClass(monsterTest);
		printImgClass.runReverseImgClass();
		printImgClass.runReverseHPImgClass();
		printImgClass.runImgClass();
		printImgClass.runHPImgClass();
		
	}
}
