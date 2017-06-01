package procesadores.abs;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

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
