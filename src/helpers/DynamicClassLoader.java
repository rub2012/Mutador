package helpers;
import java.io.IOException;
import java.io.InputStream;

public class DynamicClassLoader extends ClassLoader {

	private String sourceDir;

	public DynamicClassLoader(String sourceDir) {
		super();
		this.sourceDir = sourceDir;
	}

	@Override
	public Class<?> loadClass(String className) throws ClassNotFoundException {
		//resolveClass(cls);
        return findClass(className);
	}
	
	@Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
		if (className.equals("tmp.Pepe")) {
			try {
				InputStream is = this.getClass().getClassLoader().getResourceAsStream(this.sourceDir + "/" + className.replace('.', '/') + ".class");
				byte[] buf = new byte[10000];
				int len = is.read(buf);
				return defineClass(className, buf, 0, len);
			} catch (IOException e) {
				throw new ClassNotFoundException("", e);
			}
		}
		return getParent().loadClass(className);
	}
}