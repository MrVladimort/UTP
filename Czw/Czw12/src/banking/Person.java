package banking;
public class Person {
	protected String name;
	public int random;
	
	public Person(String name){
		this.name = name; 
		random = (int)(Math.random()*100);
	}
	public String getName(){
		return name;
	}
	
	public String concat(String str) {
		return "CONCATINATION: \n\t " + name + " " + random + " " +str;
	}
	
	public void abs(String str) {
		System.out.println("PERSON: " + str);
	}
	
	public String toString() {
		return name + " " + random;
	}
}
