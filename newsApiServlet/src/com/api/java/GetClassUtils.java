package com.api.java;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetClassUtils {

    public static void main(String[] args) {
    	Map<String, Object> map = getClassData();
    	List<String> names  = (List<String>) map.get("names");
    	for (String string : names) {
			System.out.println(string);
		}
    }
    
    /** 
    * @author hezhoujun
    * @Title: getClassData 
    * @Description: 获取类名、备注、URL parttern list
    * @return Map<String,Object>
    */
    public static Map<String, Object> getClassData(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<String> names = new ArrayList<>();
    	List<String> simpleNames = new ArrayList<>();
    	List<String> descriptions = new ArrayList<>();
    	List<String> urlPatterns = new ArrayList<>();
    	try {
    		List<Class> classes = getClassesByPackageName("com.api.api");
    		for (Class<?> class1 : classes) {
    			String name = class1.getName();
    			String simpleName = class1.getSimpleName();
				if(!simpleName.equals("BaseServlet")){
					names.add(name);
					simpleNames.add(simpleName);
					Method m = class1.getMethod("getDescription", null);
					descriptions.add(m.invoke(class1.newInstance(), null).toString());
					Method m2 = class1.getMethod("getUrlPattern", null);
					urlPatterns.add(m2.invoke(class1.newInstance(), null).toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	map.put("names", names);
    	map.put("simpleNames", simpleNames);
    	map.put("descriptions", descriptions);
    	map.put("urlPatterns", urlPatterns);
    	return map;
    }
    

    private List<Class> getAllImplClassesByInterface(Class c) {

	// 给一个接口，返回这个接口的所有实现类
	List<Class> returnClassList = new ArrayList<Class>();// 返回结果
	// 如果不是一个接口，则不做处理
	if (c.isInterface()) {
	    String packageName = c.getPackage().getName();// 获得当前包名
	    try {
		List<Class> allClass = getClassesByPackageName(packageName);// 获得当前包下以及包下的所有类
		for (int i = 0; i < allClass.size(); i++) {
		    /**
		     * 判定此 Class 对象所表示的类或接口与指定的 Class 参数cls所表示的类或接口是否相同，
		     * 或是否是其超类或(超)接口，如果是则返回 true，否则返回 false。
		     */
		    if (c.isAssignableFrom(allClass.get(i))) {
			if (!c.equals(allClass.get(i))) {// 本身加不进去
			    returnClassList.add(allClass.get(i));
			}
		    }
		}
	    } catch (ClassNotFoundException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return returnClassList;

    }

    // 从一个包中查找出所有类,在jar包中不能查找
    private static List<Class> getClassesByPackageName(String packageName) throws IOException, ClassNotFoundException {
	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	String path = packageName.replace('.', '/');
	Enumeration<URL> resources = classLoader.getResources(path);
	List<File> dirs = new ArrayList<File>();
	while (resources.hasMoreElements()) {
	    URL resource = resources.nextElement();
	    dirs.add(new File(resource.getFile()));
	}
	ArrayList<Class> classes = new ArrayList<Class>();
	for (File directory : dirs) {
	    classes.addAll(findClasses(directory, packageName));
	}
	return classes;
    }

    private static List<Class> findClasses(File directory, String packageName)
	    throws ClassNotFoundException {
	List<Class> classes = new ArrayList<Class>();
	if (!directory.exists()) {
	    return classes;
	}
	File[] files = directory.listFiles();
	for (File file : files) {
	    if (file.isDirectory()) {
		// 递归查找文件夹【即对应的包】下面的所有文件
		assert !file.getName().contains(".");
		classes.addAll(findClasses(file, packageName + '.' + file.getName()));
	    } else if (file.getName().endsWith(".class")) {
		classes.add(Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() - 6)));
	    }
	}
	return classes;
    }


}