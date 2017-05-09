package helpers;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

public class CustomListener extends RunListener {
	
	private Class<?> clase;
	
	public CustomListener(Class<?> clase){
		this.clase=clase;
	}
	
	@Override
	public void testStarted(Description description){
		System.out.println(description.getMethodName());
		//description.
	}

}
