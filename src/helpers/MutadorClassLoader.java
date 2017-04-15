package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

import tmp.Pepe;

public class MutadorClassLoader extends ClassLoader {
	
	private File prepath;
	
	public MutadorClassLoader(File prepath) {
        super();
        this.prepath = prepath;
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
        	byte[] bytes = new byte[(int) prepath.length()];
        	FileInputStream fileInputStream = new FileInputStream(prepath);
            fileInputStream.read(bytes);
            return defineClass(s, bytes, 0, bytes.length);
        } catch (IOException | ClassNotFoundException ioe) {
            try {
                return super.loadClass(s);
            } catch (ClassNotFoundException ignore) { }
            ioe.printStackTrace(System.out);
            return null;
        }
    }

}
