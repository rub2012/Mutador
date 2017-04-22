package main;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.commons.io.FileUtils;

import helpers.CustomURLclassLoader;
import helpers.DynamicClassLoader;
import helpers.MutadorClassLoader;
import tmp.Pepe;

public class TestMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, MalformedURLException, ClassNotFoundException {
		Pepe a1 = new Pepe();
		a1.m1();
//		System.out.println(a1.getClass().getName()+" hashcode: "+a1.getClass().hashCode());		
//		a1.m1(); // imprime version 2
//		
		String a = "target/classes/version1/tmp/Pepe.class";
		File s = new File(a);
		File orig = new File("target/classes/tmp/Pepe.class");
		
		try {
		    FileUtils.copyFile(s, orig);
		} catch (IOException e) {
		    e.printStackTrace();
		}
//		
		MutadorClassLoader loader = new MutadorClassLoader(s.getAbsoluteFile());
		Thread.currentThread().setContextClassLoader(loader);
		loader.loadClass("tmp.Pepe");
		
		Class<?> g = Class.forName("tmp.Pepe", true, loader);
		Object a3 = g.newInstance();
		Method ss = g.getDeclaredMethod("m1");
		ss.invoke(a3);
//		Pepe g = (Pepe) loader1.loadClass("tmp.Pepe").newInstance();
//		g.m1();
//		@SuppressWarnings("deprecation")
//		URLClassLoader loader1 = new CustomURLclassLoader(new URL[] {s.toURL()}, Thread.currentThread().getContextClassLoader());
//		Pepe g = (Pepe) loader1.loadClass("tmp.Pepe").newInstance();
//		g.m1();
//		Thread.currentThread().setContextClassLoader(loader1);
//		loader1.loadClass("tmp.Pepe");
		
//		String aa = "target/classes/version1";
//		DynamicClassLoader loaden = new DynamicClassLoader(aa);
//		loaden.loadClass("tmp.Pepe");
		
//		loader.loadClass("tmp.Pepe");
//		System.out.println(a2.getName()+" hashcode: "+a2.hashCode());
//		Object a3 = a2.newInstance();
//		Method ss = a2.getDeclaredMethod("m1");
//		ss.invoke(a3);
//		System.out.println(a3.getClass().getName()+" hashcode: "+a3.getClass().hashCode());
		
		//((Pepe) a3).m1();
		Pepe a5 = new Pepe();
		//System.out.println(a5.getClass().getName()+" hashcode: "+a5.getClass().hashCode());
		a5.m1(); // aca debera ver version 1

	}

}
