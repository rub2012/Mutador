package procesadores.cor;

import helpers.Helper;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

public class ProcesadorCORyy extends AbstractProcessor<CtElement>{

	@Override
	public void process(CtElement candidate) {
		if (Helper.MatchCORbinary(candidate,BinaryOperatorKind.AND)){
			CtBinaryOperator op = (CtBinaryOperator)candidate;
			op.setKind(BinaryOperatorKind.AND);
		}
		else{
			return;
		}
		
	}

}
