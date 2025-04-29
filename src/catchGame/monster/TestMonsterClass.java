package catchGame.monster;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestMonsterClass {
	public static void main(String[] args) {
		MonsterTest monsterTest = new MonsterTest();
		PrintImgClass printImgClass = new PrintImgClass(monsterTest);
		printImgClass.runReverseImgClass();
		printImgClass.runReverseHPImgClass();
		printImgClass.runImgClass();
		printImgClass.runHPImgClass();
		
	}
}
