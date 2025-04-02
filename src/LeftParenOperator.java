
public class LeftParenOperator extends Operator{
	private final static int PRECEDENCE_VALUE = 5;
	
	LeftParenOperator() {
		super(PRECEDENCE_VALUE);
	}
	
	public double evaluate(double num1, double num2) {
		throw new UnsupportedOperationException();
	}
}
