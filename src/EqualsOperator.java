
public class EqualsOperator extends Operator{
	private final static int PRECEDENCE_VALUE = 0;
	
	EqualsOperator() {
		super(PRECEDENCE_VALUE);
	}
	
	public double evaluate(double num1, double num2) {
		throw new UnsupportedOperationException();
	}
}
