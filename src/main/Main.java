package main;

import java.util.List;

import funcion.Funcion;
import procesadores.aor.ProcesadorAORBresta;
import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.visitor.filter.TypeFilter;

public class Main {

	public static void main(String[] args) {
		ProcesadorAORBresta proc = new ProcesadorAORBresta();
		Launcher launcher = new Launcher();
		launcher.addInputResource("src/funcion/Funcion.java");
		TypeFilter<CtBinaryOperator<?>> expresion = new TypeFilter<CtBinaryOperator<?>>(CtBinaryOperator.class);
		launcher.run();
		CtModel modelo = launcher.getModel();
		List<CtBinaryOperator<?>> elementos;
		elementos = modelo.getElements(expresion);
		CtBinaryOperator elem = elementos.get(0);
		proc.isToBeProcessed(elem);
		proc.process(elem);
		
		
		System.out.println(elem);
		launcher.prettyprint();
	}

}
