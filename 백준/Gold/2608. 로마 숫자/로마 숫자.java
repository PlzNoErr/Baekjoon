import java.util.*;
 
public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String input1 = sc.next();
		String input2 = sc.next();

		input1 = input1.replaceAll("IV", "A");
		input1 = input1.replaceAll("IX", "B");
		input1 = input1.replaceAll("XL", "E");
		input1 = input1.replaceAll("XC", "F");
		input1 = input1.replaceAll("CD", "G");
		input1 = input1.replaceAll("CM", "H");	
		input2 = input2.replaceAll("IV", "A");
		input2 = input2.replaceAll("IX", "B");
		input2 = input2.replaceAll("XL", "E");
		input2 = input2.replaceAll("XC", "F");
		input2 = input2.replaceAll("CD", "G");
		input2 = input2.replaceAll("CM", "H");

		char first[] = input1.toCharArray();
		int firstNum = 0;
		char second[] = input2.toCharArray();
		int secondNum = 0;

		for(char i : first)
			firstNum += charChanger(i);
		for(char i : second)
			secondNum += charChanger(i);
		
		int resultNum = firstNum + secondNum;
		String resultString = numChanger(resultNum);
		System.out.println(resultNum);
		System.out.println(resultString);
		
	}
  
	public static int charChanger(char input) {
		int comp = 0;
		if(input == 'I') comp = 1;
		else if(input == 'V') comp = 5;
		else if(input == 'X') comp = 10;
		else if(input == 'L') comp = 50;
		else if(input == 'C') comp = 100;
		else if(input == 'D') comp = 500;
		else if(input == 'M') comp = 1000;
		else if(input == 'A') comp = 4;
		else if(input == 'B') comp = 9;
		else if(input == 'E') comp = 40;
		else if(input == 'F') comp = 90;
		else if(input == 'G') comp = 400;
		else if(input == 'H') comp = 900;
		return comp;
	}
	
	public static String numChanger(int input) {
		String re = "";
		
		while(input >= 1000) {
			re += "M";
			input -= 1000;
		}
		if(input >= 900) {
			re += "CM";
			input -= 900;
		}
		if(input >= 500) {
			re += "D";
			input -= 500;
		}
		if(input >= 400) {
			re += "CD";
			input -= 400;
		}
		while(input >= 100) {
			re += "C";
			input -= 100;
		}
		if(input >= 90) {
			re += "XC";
			input -= 90;
		}
		if(input >= 50) {
			re += "L";
			input -= 50;
		}
		if(input >= 40) {
			re += "XL";
			input -= 40;
		}
		while(input >= 10) {
			re += "X";
			input -= 10;
		}
		if(input >= 9) {
			re += "IX";
			input -= 9;
		}
		if(input >= 5) {
			re += "V";
			input -= 5;
		}
		if(input >= 4) {
			re += "IV";
			input -= 4;
		}
		while(input >= 1) {
			re += "I";
			input -= 1;
		}
		return re;
	}
}