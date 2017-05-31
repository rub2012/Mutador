package procesadores.abs;

import helpers.Helper;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtElement;
import spoon.support.reflect.code.CtExpressionImpl;

public class ProcesadorABSpositivo extends AbstractProcessor<CtElement>{

	@Override
	public void process(CtElement candidate) {
		CtBinaryOperator<?> op = (CtBinaryOperator<?>)candidate;
		
		//op.getRightHandOperand().;
		//CtExpression<?> s = new CtExpressionImpl<?>();
		//op.setRightHandOperand(new CtExpression() {pl
		//})
		//BinaryOperatorKind.
//		if (Helper.MatchAORbinary(candidate) || Helper.MatchAORunary(candidate)){
//			candidate.getParent();
//			CtBinaryOperator op = (CtBinaryOperator)candidate;
//			op.setKind(BinaryOperatorKind.DIV);
//		}
//		else{
//			return;
//		}
//		
	}
//	
//	private CtElement valorAbsoluto(CtElement aritmetico){
//		return Math.abs(CtElement.class);
//	}

}
