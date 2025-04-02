
public class PowerOperator extends Operator{
	private final static int PRECEDENCE_VALUE = 30;
	
	PowerOperator() {
		super(PRECEDENCE_VALUE);
	}
	
	public double evaluate(double num1, double num2) {
		return Math.pow(num1, num2);
	}
}
