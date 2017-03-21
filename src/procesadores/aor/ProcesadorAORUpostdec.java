package procesadores.aor;

import helpers.Helper;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.UnaryOperatorKind;
import spoon.reflect.declaration.CtElement;

public class ProcesadorAORUpostdec extends AbstractProcessor<CtElement>{

	@Override
	public void process(CtElement candidate) {
		if (Helper.MatchAORunary(candidate,UnaryOperatorKind.POSTDEC)){
			CtUnaryOperator op = (CtUnaryOperator)candidate;
			op.setKind(UnaryOperatorKind.POSTDEC);
		}
		else{
			return;
		}
		
	}

}
