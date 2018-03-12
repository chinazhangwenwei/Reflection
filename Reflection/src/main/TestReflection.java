package main;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import model.People;
/**
 * 
 * @author 53048
 *
 */
public class TestReflection {
	
	
	public static void main(String args []){
		
		Class peopleClz = null;
		try {
			peopleClz = Class.forName("model.People");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//反射获取属性
		testField(peopleClz);
		//反射获取方法
		testMethod(peopleClz);
		//反射获取构造器
		testConstructor(peopleClz);
		//反射基本使用
		testDemo(peopleClz);
			
	}
	
	//反射测试基本使用
	public static void testDemo(Class peopleClz){
		
		//调用构造方法生成对象
		People people1=null;
		People people2 =null;
		try {
		//反射生成对象
			System.out.println("------------------------------------------------------------------------");
			System.out.println("反射生成对象：");
		//公有构造方法生成对象：
		Constructor con1=	peopleClz.getConstructor(int.class,String.class);
		people1 = (People)	con1.newInstance(10,"weiwei");
		System.out.println("名字="+people1.getName());
		//非公有构造方法生成对象：
		Constructor con2=	peopleClz.getDeclaredConstructor(int.class,String.class,int.class);
		//注意调用私有构造方法一定要设置下面语句为true,否则报异常java.lang.IllegalAccessException;
		con2.setAccessible(true);
		people2 = (People) con2.newInstance(10,"wenwen",-100);
		System.out.println("名字="+people2.getName()+"\tsex ="+people2.getSex());
		
		//反射操作对象属性
		System.out.println("------------------------------------------------------------------------");
		System.out.println("反射操作对象属性：");
		try {
			Field field1 = peopleClz.getDeclaredField("sex");
			//sex访问权限为defalut,需要设置下面语句为true。
			field1.setAccessible(true);
			field1.set(people2, 100);
			System.out.println("sex ="+people2.getSex());
			
			Field field2 = peopleClz.getDeclaredField("address");
			field2.setAccessible(true);
			field2.set(people2, "Beijing in China");
			System.out.println("address ="+field2.get(people2));
			
			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//反射操作对象方法
		System.out.println("------------------------------------------------------------------------");
		System.out.println("反射操作对象方法：");
		
		Method method1 = peopleClz.getDeclaredMethod("setName", String.class);
		Method method2 = peopleClz.getDeclaredMethod("getName");
		method1.invoke(people2, "王小波！");
		Object objString = (String) method2.invoke(people2);
		System.out.println(objString);
		
		Method method3= peopleClz.getDeclaredMethod("testReflec");
		method3.setAccessible(true);
		method3.invoke(people2, null);
				
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
	
	
	//测试构造方法
	public static void testConstructor(Class peopleClz){
		
		Constructor cons [] = peopleClz.getConstructors();
		System.out.println("获取所有公有构造方法（不包含父类）");
		for(Constructor con:cons){
			
			System.out.println(con);
		}
		System.out.println("获取指定公有构造方法（不包含父类）");
		try {
			Constructor con1 = peopleClz.getConstructor(null);
			System.out.println(con1);
			Constructor con2 = peopleClz.getConstructor(int.class,String.class);
			System.out.println(con2);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("获取指定构造方法");
		try {
			
			Constructor con3 = peopleClz.getDeclaredConstructor(int.class,String.class,int.class);
			System.out.println(con3);
			
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Constructor dCons [] = peopleClz.getDeclaredConstructors();
		System.out.println("获取所有构造方法");
		for(Constructor con:dCons){
			
			System.out.println(con);
		}
		
	}
	//测试方法
	public static void testMethod(Class peopleClz){
		//获取所有公有方法
		Method methods [] = peopleClz.getMethods();
		System.out.println("获取所有公有方法(包含父类)：");
		for(Method method:methods){
			
			System.out.println(method);
			
		}
		
		//获取指定公有方法
		System.out.println("获取指定公有方法：");
		try {
		Method setSex = 	peopleClz.getMethod("setSex", int.class);
		System.out.println(setSex);		
		
		Method getSex = 	peopleClz.getMethod("getSex", null);
		System.out.println(getSex);		
		
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取本类声明方法
		
		Method dMethods [] = peopleClz.getDeclaredMethods();
		System.out.println("获取本类所有方法(不包含父类)：");
		for(Method method:dMethods){
			
			System.out.println(method);
			
		}
		//获取本类指定方法
			System.out.println("获取指定声明方法：");
				try {
		
				Method testReflec = 	peopleClz.getDeclaredMethod("testReflec", null);
				System.out.println(testReflec);		
				
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}
	
	//测试属性
	public static void  testField(Class peopleClz){
		
		//获取People类所有共有属性：
				Field allPFields[] = peopleClz.getFields();
				System.out.println("所有公有属性(包含父类)：");
				for(Field field :allPFields){
					
					System.out.println(field);
					
				}
				//获取指定公有属性
				try {
					Field ageField = peopleClz.getField("age");
					System.out.println("指定公有属性：\n"+ageField);
					
				} catch (NoSuchFieldException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//获取指定属性：
				try {
					Field addressField = peopleClz.getDeclaredField("address");
					System.out.println("获取指定任意属性：\n"+addressField);
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//获取People类所有属性(不能获取父类)：
				Field allFields[] = peopleClz.getDeclaredFields();
				System.out.println("所有属性(不包含父类)：");
				for(Field field :allFields){
					
					System.out.println(field);
					
				}
				
	}
	
	}
