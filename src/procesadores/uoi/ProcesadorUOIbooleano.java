package procesadores.uoi;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtTypedElement;

public class ProcesadorUOIbooleano extends AbstractProcessor<CtElement>{
	
	@Override
	public boolean isToBeProcessed(CtElement candidate){
		Class<?> tipo = ((CtTypedElement<?>) candidate).getType().getActualClass();
		return tipo == boolean.class;
	}

	@Override
	public void process(CtElement element) {
		// TODO Auto-generated method stub
		
	}

}
