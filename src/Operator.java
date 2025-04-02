
public abstract class Operator {
	private int precedence;
	
	Operator(int precedence) {
		this.precedence = precedence;
	}
	
	public double getPrecedence() {
		return precedence;
	}
	
	abstract double evaluate(double num1, double num2);
}
