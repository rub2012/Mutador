package procesadores.aor;

import helpers.Helper;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

public class ProcesadorAORBmultiplicacion extends AbstractProcessor<CtElement>{

	@Override
	public void process(CtElement candidate) {
		if (Helper.MatchAORbinary(candidate,BinaryOperatorKind.MUL)){
			CtBinaryOperator op = (CtBinaryOperator)candidate;
			op.setKind(BinaryOperatorKind.MUL);
		}
		else{
			return;
		}
		
	}

}
