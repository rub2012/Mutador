package tmp;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;

import helpers.Helper;

public class PruebaDeVersiones {
	public static void main(String[] args) {
		new Pepe().m1();
		
		String a = "target/classes/version1/tmp/Pepe.class";
		URI s = new File(a).toURI();
		try {
			s.toURL().toString();
			Helper.cambiarContexto(s.toURL().toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Pepe().m1(); // aca debera ver version 1
	}
}
