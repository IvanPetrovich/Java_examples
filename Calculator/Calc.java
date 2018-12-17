package Calculator;

public class Calc {
	
	enum Operation {SUM, MUL, DIV, SUB, SQRT};

	private Operation currentOperation;
	
	private double operandA;
	private String operandB;
	
	public Calc() {
		operandA = 0f;
		operandB = "";
		currentOperation = null;
	
	}
	
	public void setOperandA(double operandA) {
		this.operandA = operandA;
	}
	public void setOperandB(String operandB) {
		this.operandB = operandB;
	}
	public Operation getCurrentOperation() {
		return currentOperation;
	}
	public double getOperandA() {
		return operandA;
	}
	public String getOperandB() {
		return operandB;
	}
	
	public Operation getOperationByText(String text) {
		if(text == "+")
			return Operation.SUM;
		else if(text == "*")
			return Operation.MUL;
		else if(text == "/")
			return Operation.DIV;
		else if(text == "-")
			return Operation.SUB;
		else if(text == "sqrt")
			return Operation.SQRT;
		else {
			return null;
		}
	}
	
	
	public void setCurrentOperation(String curOp) {
		this.currentOperation = getOperationByText(curOp);
	}
	
	double sum() {
		return this.operandA + Double.parseDouble(this.operandB);
	}
	
	double mul() {
		return this.operandA * Double.parseDouble(this.operandB);
	}
	
	double div() {
		if(Double.parseDouble(this.operandB)!=0f)
			return this.operandA / Double.parseDouble(this.operandB);
		return 0f;
	}
	double sub() {
		return this.operandA - Double.parseDouble(this.operandB);
	}
	
	double sqrtA() {
		return sqrt(this.operandA);
	}
	public double sqrt(double x) {
		return Math.sqrt(x);
	}

	public double calc() {
		switch (this.currentOperation) {
		case SUM:
			return sum();
		case MUL:
			return mul();
		case DIV:
			return div();
		case SUB:
			return sub();
		case SQRT:
			return sqrtA();
		default:
			return 0;
		}
	}
	
	String getTextByOperation(Operation oper) {
		
		switch (oper) {
		case SUM:
			return "+";
		case MUL:
			return "*";
		case DIV:
			return "/";
		case SUB:
			return "-";
		case SQRT:
			return "sqrt";
		}
		return "";
	}
	
	public String getExpression() {
		if (currentOperation!=null) {
			return "" + operandA + " " + getTextByOperation(currentOperation) + " " + operandB;
		}else
		{
			return "";
		}
	}
	
}
