package model;

public class People  extends Animal{
	
	private String address;
	
	int sex;
	
	protected String name;
	
	public int age;

	public People(){
		
	}
	public People(int age,String name){
		
		this.age = age;
		this.name = name;
	}
	private People(int age,String name,int sex){
		
		this.age=age;
		this.name =name;
		this.sex = sex;
		
	}
		
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private void testReflec(){
	
		System.out.println("≤‚ ‘∑¥…‰");
	
	}

}
