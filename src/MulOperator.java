
public class MulOperator extends Operator{
	private final static int PRECEDENCE_VALUE = 20;
	
	MulOperator() {
		super(PRECEDENCE_VALUE);
	}
	
	public double evaluate(double num1, double num2) {
		return num1 * num2;
	}
}
