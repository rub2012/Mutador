package procesadores.aor;

import helpers.Helper;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

public class ProcesadorAORBsuma extends AbstractProcessor<CtElement>{

	@Override
	public boolean isToBeProcessed(CtElement candidate){
		return Helper.MatchAORbinary(candidate,BinaryOperatorKind.PLUS);
	}

	@Override
	public void process(CtElement candidate) {
		CtBinaryOperator op = (CtBinaryOperator)candidate;
		op.setKind(BinaryOperatorKind.PLUS);		
	}

}
