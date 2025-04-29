package catchGame.monster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrintImgClass {
	File file;
	String HPfilePath; // HP바 이미지 
	MonsterBase monsterBase;
	Scanner scanFile;
	Scanner scanner = new Scanner(System.in);
	String Img;
	int HPPercentage;
	public PrintImgClass(MonsterBase monsterBase) {
		this.monsterBase = monsterBase;
		this.HPfilePath = "T:\\github\\java-catchGame_bang\\src\\catchGame\\monster\\HP바.txt";
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
			for(int i = 0; i < 60 ; i++) {
				System.out.print(" ");
			}
			System.out.println(this.Img);
		}
	}
	
	private void printHPImg() {
		this.Img = new String(this.scanFile.nextLine());
		System.out.println(this.Img);
		this.Img = new String(this.scanFile.nextLine());
		System.out.print(this.Img);
		System.out.println(this.HPPercentage + "%");
		this.Img = new String(this.scanFile.nextLine());
		System.out.println(this.Img);
	}
	
	private void printReverseHPImg() {
		for(int i = 0; i < 60 ; i++) {
			System.out.print(" ");
		}
		this.Img = new String(this.scanFile.nextLine());
		System.out.println(this.Img);
		for(int i = 0; i < 60 ; i++) {
			System.out.print(" ");
		}
		this.Img = new String(this.scanFile.nextLine());
		System.out.print(this.Img);
		System.out.println(this.HPPercentage + "%");
		for(int i = 0; i < 60 ; i++) {
			System.out.print(" ");
		}
		this.Img = new String(this.scanFile.nextLine());
		System.out.println(this.Img);
	}
	
	public void runImgClass() {
		this.readImgFile();
		this.printImg();
	}
	
	public void runReverseImgClass() {
		this.readReverseImgFile();
		this.printReverseImg();
	}
	
	public void runHPImgClass() {
		this.HPPercentage = (int)((this.monsterBase.currentHP / (double)this.monsterBase.maxHP) * 100);
		this.readHPImgFile();
		this.printHPImg();
	}
	
	public void runReverseHPImgClass() {
		this.HPPercentage = (int)((this.monsterBase.currentHP / (double)this.monsterBase.maxHP) * 100);
		this.readHPImgFile();
		this.printReverseHPImg();
	}
}
