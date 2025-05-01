package catchGame.manage;



/**
 * 새롭게 추가한 출력을 텍스트 박스 안에서 출력하게 하는 클래스
 */
public class TextBoxClass {
	static int boxWidth = 100;
	int boxlength;
	
	
	public TextBoxClass() {

	}

	/**
	 * 윗 박스 부분
	 */
	public static void printTextBoxStart() {
		System.out.println("┌" + "─".repeat(boxWidth) + "┐");
	}
	
	/**
	 * 아래 박스 부분
	 */
	public static void printTextBoxEnd() {
		System.out.println("└" + "─".repeat(boxWidth) + "┘");
	}
	
	/**
	 * 주어진 문자열에서 한글 문자의 개수를 세는 메서드.
	 * 만든 이유:한글이 자리를 2칸 차지해서 상자가 깨지는 현상으로 인해 한글을 체크
	 * @param strText
	 * @return 한글 문자의 개수
	 */
	public static int ifHangul(String strText) {
		int count = 0;
		for (char num : strText.toCharArray()) {
			if('가' <= num && num<= '힣') {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * 주어진 글자 하나가 한글인지 아닌지를 리턴하는 메서드
	 * @param ch
	 * @return
	 */
	public static boolean ifCharHangul(char ch) {
		if('가' <= ch && ch<= '힣') {
			return true;
		}
		return false;
	}
	
	/**
	 * 긴 텍스트가 들어올경우 박스가 깨지는 현상으로 인해 너무 길지 않게 짤라서 나눠서 중앙 출력시키는 메소드
	 * @param strText
	 */
	public static void runPrintTextBox(String strText) {
		int strLenCount = 0;
		StringBuilder strLine = new StringBuilder();
		printTextBoxStart();
		for (char num : strText.toCharArray()) {
			if(ifCharHangul(num)) {
				strLenCount  = strLenCount + 2; // 한글일경우
			}else {
				strLenCount++; // 한글이 아닐경우
			}
			strLine.append(num);
			if (strLenCount > boxWidth/(double)2) {
				printTextBox(strLine.toString());
				strLenCount = 0;
				strLine  = new StringBuilder();
			}
			
		}
	    if (strLine.length() > 0) { // 남은 문자열 출력
	        printTextBox(strLine.toString());
	    }
		printTextBoxEnd();
	}
	
	/**
	 * 텍스트 박스 중앙부분 출력 메소드 
	 * @param strText
	 */
	public static void printTextBox(String strText) {
		int count = ifHangul(strText);
	    int totalSpace = boxWidth - strText.length() - count;
	    int leftPad = totalSpace / 2;
	    int rightPad = totalSpace - leftPad;
	
	    String whitespace = " ";
	    System.out.println("│" + whitespace.repeat(leftPad) + strText + whitespace.repeat(rightPad) + "│");
	}
}
