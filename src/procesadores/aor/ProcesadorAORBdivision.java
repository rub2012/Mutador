package procesadores.aor;

import helpers.Helper;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

public class ProcesadorAORBdivision extends AbstractProcessor<CtElement>{
	
	@Override
	public boolean isToBeProcessed(CtElement candidate){
		return Helper.MatchAORbinary(candidate,BinaryOperatorKind.DIV);
	}

	@Override
	public void process(CtElement candidate) {
		CtBinaryOperator op = (CtBinaryOperator)candidate;
		op.setKind(BinaryOperatorKind.DIV);		
	}
}
