package helpers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MutadorClassLoader extends ClassLoader {
	
	private String prepath;
	
	public MutadorClassLoader(ClassLoader parent,String prepath) {
        super(parent);
        this.prepath = prepath;
    }

	@Override
    public Class loadClass(String name) throws ClassNotFoundException {
        if(!"tmp.Pepe".equals(name))
                return super.loadClass(name);

        try {
            String url = prepath;
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();

            while(data != -1){
                buffer.write(data);
                data = input.read();
            }

            input.close();

            byte[] classData = buffer.toByteArray();

            return defineClass("tmp.Pepe",
                    classData, 0, classData.length);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
