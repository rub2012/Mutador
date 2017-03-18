package procesadores.aor;

import helpers.Helper;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.UnaryOperatorKind;
import spoon.reflect.declaration.CtElement;

public class ProcesadorAORUpostinc extends AbstractProcessor<CtElement>{

	@Override
	public void process(CtElement candidate) {
		if (Helper.MatchAORunary(candidate)){
			CtUnaryOperator op = (CtUnaryOperator)candidate;
			op.setKind(UnaryOperatorKind.POSTINC);
		}
		else{
			return;
		}
		
	}

}
