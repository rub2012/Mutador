package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;

public class CustomURLclassLoader extends URLClassLoader{
	private File file;

	public CustomURLclassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
		try {
			file = new File(urls[0].toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public Class<?> loadClass(String s) {
		Class<?> cls = findClass(s);
		//resolveClass(cls);
        return cls;
    }

    @Override
    public Class<?> findClass(String s) {
        try {
        	if(!"tmp.Pepe".equals(s)){
        		return super.loadClass(s);
        	}
        	byte[] bytes = new byte[(int) file.length()];
        	FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytes);
            return defineClass(s, bytes, 0, bytes.length);
        } catch (IOException | ClassNotFoundException ioe) {
            try {
            	System.out.println("Clase no encontrada");
                return super.loadClass(s);
            } catch (ClassNotFoundException ignore) { }
            ioe.printStackTrace(System.out);
            return null;
        }
    }

}
