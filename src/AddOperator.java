
public class AddOperator extends Operator{
	private final static int PRECEDENCE_VALUE = 10;
	
	AddOperator() {
		super(PRECEDENCE_VALUE);
	}
	
	public double evaluate(double num1, double num2) {
		return num1 + num2;
	}
}
