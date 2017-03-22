package procesadores.aor;

import helpers.Helper;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.UnaryOperatorKind;
import spoon.reflect.declaration.CtElement;

public class ProcesadorAORUpostdec extends AbstractProcessor<CtElement>{

	@Override
	public boolean isToBeProcessed(CtElement candidate){
		return Helper.MatchAORunary(candidate,UnaryOperatorKind.POSTDEC);
	}

	@Override
	public void process(CtElement candidate) {
		CtUnaryOperator op = (CtUnaryOperator)candidate;
		op.setKind(UnaryOperatorKind.POSTDEC);		
	}

}
