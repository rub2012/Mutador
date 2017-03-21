package procesadores.cor;

import helpers.Helper;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

public class ProcesadorCORxor extends AbstractProcessor<CtElement>{

	@Override
	public void process(CtElement candidate) {
		if (Helper.MatchCORbinary(candidate,BinaryOperatorKind.BITXOR)){
			CtBinaryOperator op = (CtBinaryOperator)candidate;
			op.setKind(BinaryOperatorKind.BITXOR);
		}
		else{
			return;
		}
		
	}

}