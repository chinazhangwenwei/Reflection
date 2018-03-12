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
		//�����ȡ����
		testField(peopleClz);
		//�����ȡ����
		testMethod(peopleClz);
		//�����ȡ������
		testConstructor(peopleClz);
		//�������ʹ��
		testDemo(peopleClz);
			
	}
	
	//������Ի���ʹ��
	public static void testDemo(Class peopleClz){
		
		//���ù��췽�����ɶ���
		People people1=null;
		People people2 =null;
		try {
		//�������ɶ���
			System.out.println("------------------------------------------------------------------------");
			System.out.println("�������ɶ���");
		//���й��췽�����ɶ���
		Constructor con1=	peopleClz.getConstructor(int.class,String.class);
		people1 = (People)	con1.newInstance(10,"weiwei");
		System.out.println("����="+people1.getName());
		//�ǹ��й��췽�����ɶ���
		Constructor con2=	peopleClz.getDeclaredConstructor(int.class,String.class,int.class);
		//ע�����˽�й��췽��һ��Ҫ�����������Ϊtrue,�����쳣java.lang.IllegalAccessException;
		con2.setAccessible(true);
		people2 = (People) con2.newInstance(10,"wenwen",-100);
		System.out.println("����="+people2.getName()+"\tsex ="+people2.getSex());
		
		//���������������
		System.out.println("------------------------------------------------------------------------");
		System.out.println("��������������ԣ�");
		try {
			Field field1 = peopleClz.getDeclaredField("sex");
			//sex����Ȩ��Ϊdefalut,��Ҫ�����������Ϊtrue��
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
		
		//����������󷽷�
		System.out.println("------------------------------------------------------------------------");
		System.out.println("����������󷽷���");
		
		Method method1 = peopleClz.getDeclaredMethod("setName", String.class);
		Method method2 = peopleClz.getDeclaredMethod("getName");
		method1.invoke(people2, "��С����");
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
	
	
	//���Թ��췽��
	public static void testConstructor(Class peopleClz){
		
		Constructor cons [] = peopleClz.getConstructors();
		System.out.println("��ȡ���й��й��췽�������������ࣩ");
		for(Constructor con:cons){
			
			System.out.println(con);
		}
		System.out.println("��ȡָ�����й��췽�������������ࣩ");
		try {
			Constructor con1 = peopleClz.getConstructor(null);
			System.out.println(con1);
			Constructor con2 = peopleClz.getConstructor(int.class,String.class);
			System.out.println(con2);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("��ȡָ�����췽��");
		try {
			
			Constructor con3 = peopleClz.getDeclaredConstructor(int.class,String.class,int.class);
			System.out.println(con3);
			
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Constructor dCons [] = peopleClz.getDeclaredConstructors();
		System.out.println("��ȡ���й��췽��");
		for(Constructor con:dCons){
			
			System.out.println(con);
		}
		
	}
	//���Է���
	public static void testMethod(Class peopleClz){
		//��ȡ���й��з���
		Method methods [] = peopleClz.getMethods();
		System.out.println("��ȡ���й��з���(��������)��");
		for(Method method:methods){
			
			System.out.println(method);
			
		}
		
		//��ȡָ�����з���
		System.out.println("��ȡָ�����з�����");
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
		//��ȡ������������
		
		Method dMethods [] = peopleClz.getDeclaredMethods();
		System.out.println("��ȡ�������з���(����������)��");
		for(Method method:dMethods){
			
			System.out.println(method);
			
		}
		//��ȡ����ָ������
			System.out.println("��ȡָ������������");
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
	
	//��������
	public static void  testField(Class peopleClz){
		
		//��ȡPeople�����й������ԣ�
				Field allPFields[] = peopleClz.getFields();
				System.out.println("���й�������(��������)��");
				for(Field field :allPFields){
					
					System.out.println(field);
					
				}
				//��ȡָ����������
				try {
					Field ageField = peopleClz.getField("age");
					System.out.println("ָ���������ԣ�\n"+ageField);
					
				} catch (NoSuchFieldException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//��ȡָ�����ԣ�
				try {
					Field addressField = peopleClz.getDeclaredField("address");
					System.out.println("��ȡָ���������ԣ�\n"+addressField);
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//��ȡPeople����������(���ܻ�ȡ����)��
				Field allFields[] = peopleClz.getDeclaredFields();
				System.out.println("��������(����������)��");
				for(Field field :allFields){
					
					System.out.println(field);
					
				}
				
	}
	
	}
