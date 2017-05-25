package helpers;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

public class CustomListener extends RunListener {

	private List<String> metodos;
	
	public CustomListener(){
		metodos = new ArrayList<String>();
	}	
	
	@Override
	public void testStarted(Description description){
		metodos.add(description.getMethodName());
	}
	
	public List<String> getMetodos(){
		return metodos;
	}
}
