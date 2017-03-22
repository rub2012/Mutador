package procesadores.cor;

import helpers.Helper;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

public class ProcesadorCORoo extends AbstractProcessor<CtElement>{

	@Override
	public boolean isToBeProcessed(CtElement candidate){
		return Helper.MatchCORbinary(candidate,BinaryOperatorKind.OR);
	}

	@Override
	public void process(CtElement candidate) {
		CtBinaryOperator op = (CtBinaryOperator)candidate;
		op.setKind(BinaryOperatorKind.OR);		
	}

}
