package calcApp;

public class OpFactory {
	public static Operator getOp(String symbol) {
		switch(symbol) {
			case "+":
				return new Addition();
			case "-":
				return new Subtraction();
			case "x":
				return new Multiplication();
			case "/":
				return new Division();
			default:
				throw new IllegalArgumentException();
		}

	}
}
