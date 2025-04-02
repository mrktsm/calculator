
public class RightParenOperator extends Operator{
	private final static int PRECEDENCE_VALUE = 10;
	
	RightParenOperator() {
		super(PRECEDENCE_VALUE);
	}
	
	public double evaluate(double num1, double num2) {
		throw new UnsupportedOperationException();
	}
}
