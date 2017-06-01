package procesadores.uoi;

import helpers.Helper;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtTypedElement;
import spoon.reflect.reference.CtTypeReference;
import spoon.support.reflect.code.CtExpressionImpl;

public class ProcesadorUOInumerico extends AbstractProcessor<CtElement>{
	
	@Override
	public boolean isToBeProcessed(CtElement candidate){
		try {
			Class<?> tipo = ((CtTypedElement<?>) candidate).getType().getActualClass();
			return tipo == int.class || tipo == float.class || tipo == double.class || tipo == long.class || tipo == byte.class || tipo == short.class;
		
		} catch (Exception e){
			return false;
		}
	}

	@Override
	public void process(CtElement element) {
		// TODO Auto-generated method stub
		
	}

}
