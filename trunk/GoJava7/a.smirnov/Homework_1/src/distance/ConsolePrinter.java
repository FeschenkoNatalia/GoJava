package distance;

import java.util.List;

public class ConsolePrinter {
	
	public void print(List<Integer> list) {
		StringBuilder result = new StringBuilder();
		result.
			append("User's inputed numbers : ").
			append(list);
		System.out.println(result.toString());
	}
	
	public void print(String str) {
		System.out.println(str);
	}
}
