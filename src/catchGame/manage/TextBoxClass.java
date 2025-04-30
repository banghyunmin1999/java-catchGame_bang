package catchGame.manage;

public class TextBoxClass {
	static int boxWidth = 100;
	int boxlength;
	
	
	public TextBoxClass() {

	}
//	public TextBoxClass(int boxWidth) {
//		boxWidth = boxWidth;
//	}
//	
//	public void inputWidthLength(int boxWidth) {
//		boxWidth = boxWidth;
//	}
	
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
	

	public static void printTextBox(String strText) {
		int count = ifHangul(strText);
	    int totalSpace = boxWidth - strText.length() - count;
	    int leftPad = totalSpace / 2;
	    int rightPad = totalSpace - leftPad;  // 오른쪽이 1 더 많게
	
	    String whitespace = " ";
	    System.out.println("│" + whitespace.repeat(leftPad) + strText + whitespace.repeat(rightPad) + "│");
	}
}
