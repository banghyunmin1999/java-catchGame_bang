package catchGame.manage;

public class TextBoxClass {
	static int boxWidth = 100;
	int boxlength;
	
	
	public TextBoxClass() {

	}

	
	public static void printTextBoxStart() {
		System.out.println("┌" + "─".repeat(boxWidth) + "┐");
	}
	
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
	
	public static boolean ifCharHangul(char ch) {
		if('가' <= ch && ch<= '힣') {
			return true;
		}
		return false;
	}
	
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

	public static void printTextBox(String strText) {
		int count = ifHangul(strText);
	    int totalSpace = boxWidth - strText.length() - count;
	    int leftPad = totalSpace / 2;
	    int rightPad = totalSpace - leftPad;  // 오른쪽이 1 더 많게
	
	    String whitespace = " ";
	    System.out.println("│" + whitespace.repeat(leftPad) + strText + whitespace.repeat(rightPad) + "│");
	}
	
//	public static void runPrintTextBox(String strText) {
//		printTextBoxStart();
//		printTextBox(strText);
//		printTextBoxEnd();
//	}
}
