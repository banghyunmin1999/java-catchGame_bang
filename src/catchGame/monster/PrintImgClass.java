package catchGame.monster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import catchGame.manage.TextBoxClass;

/**
 * 새롭게 추가된 .txt 파일을 읽어와 내 몬스터와 야생 몬스터를 출력하는 클래스
 */
public class PrintImgClass {
	
	File file;
	String HPfilePath; // HP바 이미지 
	MonsterBase monsterBase;
	Scanner scanFile;
	Scanner scanner = new Scanner(System.in);
	String Img;
	int spaceNumber = 50;
	
	public PrintImgClass() {
		this.HPfilePath = "T:\\github\\java-catchGame_bang\\src\\catchGame\\monster\\HP바.txt";
	}
	
	public PrintImgClass(MonsterBase monsterBase) {
		this.monsterBase = monsterBase;
		this.HPfilePath = "T:\\github\\java-catchGame_bang\\src\\catchGame\\monster\\HP바.txt";
	}
	
	public void inputMonster(MonsterBase monsterBase) {
		this.monsterBase = monsterBase;
	}
	
	private void readImgFile() {
		try {
			this.file = new File(this.monsterBase.filePath); //파일 객체를 통째로 스캔
			this.scanFile = new Scanner(this.file);
		} catch (FileNotFoundException e) {//파일을 없을경우
			System.out.println("파일이 없습니다");
		}
	}
	
	private void readReverseImgFile() {
		try {
			this.file = new File(this.monsterBase.reverseFilePath); //파일 객체를 통째로 스캔
			this.scanFile = new Scanner(this.file);
		} catch (FileNotFoundException e) {//파일을 없을경우
			System.out.println("파일이 없습니다");
		}
	}
	
	private void readHPImgFile() {
		try {
			this.file = new File(this.HPfilePath); //파일 객체를 통째로 스캔
			this.scanFile = new Scanner(this.file);
		} catch (FileNotFoundException e) {//파일을 없을경우
			System.out.println("파일이 없습니다");
		}
	}

	private void printImg() {
		while(this.scanFile.hasNextLine()){
			this.Img = new String(this.scanFile.nextLine());
			System.out.println(this.Img);
		}
	}
	
	private void printReverseImg() {
		while(this.scanFile.hasNextLine()){
			this.Img = new String(this.scanFile.nextLine());
			for(int i = 0; i < spaceNumber ; i++) {
				System.out.print(" ");
			}
			System.out.println(this.Img);
		}
	}
	
	private void printHPImg() {
		for(int i = 0; i < 14 ; i++) {
			System.out.print(" ");
		}
		System.out.println(this.monsterBase.name);
		this.Img = new String(this.scanFile.nextLine());
		System.out.println(this.Img);
		this.Img = new String(this.scanFile.nextLine());
		System.out.print(this.Img);
		System.out.println(this.monsterBase.HPPercentage + "%");
		this.Img = new String(this.scanFile.nextLine());
		System.out.println(this.Img);
	}
	
	private void printReverseHPImg() {
		for(int i = 0; i < spaceNumber + 12 ; i++) {
			System.out.print(" ");
		}
		System.out.println("야생 " + this.monsterBase.name);
		for(int i = 0; i < spaceNumber ; i++) {
			System.out.print(" ");
		}
		this.Img = new String(this.scanFile.nextLine());
		System.out.println(this.Img);
		for(int i = 0; i < spaceNumber ; i++) {
			System.out.print(" ");
		}
		this.Img = new String(this.scanFile.nextLine());
		System.out.print(this.Img);
		System.out.println(this.monsterBase.HPPercentage + "%");
		for(int i = 0; i < spaceNumber ; i++) {
			System.out.print(" ");
		}
		this.Img = new String(this.scanFile.nextLine());
		System.out.println(this.Img);
	}
	
	public void runImgClass() {
		this.readImgFile();
		this.printImg();
		this.readHPImgFile();
		this.printHPImg();
	}
	
	public void runReverseImgClass() {
		this.readReverseImgFile();
		this.printReverseImg();
		this.readHPImgFile();
		this.printReverseHPImg();
	}
	
	public void runHPImgClass() {
		this.readHPImgFile();
		this.printHPImg();
	}
	
	public void runReverseHPImgClass() {
		this.readHPImgFile();
		this.printReverseHPImg();
	}
}
