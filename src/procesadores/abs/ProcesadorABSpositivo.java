package procesadores.abs;

import helpers.Helper;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.reference.CtTypeReference;
import spoon.support.reflect.code.CtExpressionImpl;

public class ProcesadorABSpositivo extends AbstractProcessor<CtElement>{
	
	@Override
	public boolean isToBeProcessed(CtElement candidate){
		CtBinaryOperator<?> op = (CtBinaryOperator<?>)candidate;
		Class<?> tipo = op.getRightHandOperand().getType().getActualClass();
		return tipo == int.class || tipo == float.class || tipo == double.class || tipo == long.class;
	}

	@Override
	public void process(CtElement element) {
		// TODO Auto-generated method stub
		
	}

}
