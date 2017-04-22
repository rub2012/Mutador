package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;


public class MutadorClassLoader extends ClassLoader {
	
	private File prepath;
	private String classPath;
	
	public MutadorClassLoader(File prepath, String classPath) {
        super();
        this.prepath = prepath;
        this.classPath = classPath;
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
        	if(!classPath.equals(s)){
        		return super.loadClass(s);
        	}
        	byte[] bytes = new byte[(int) prepath.length()];
        	FileInputStream fileInputStream = new FileInputStream(prepath);
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
