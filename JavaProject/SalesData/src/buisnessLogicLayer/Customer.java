package buisnessLogicLayer;

public class Customer {

	String firstname;
	String lastname; 
	int age;
	int id; 
	
	
	public	Customer(int id, String firstname, String lastname, int age) 
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age; 
		this.id = id; 
		
	}
	
	public	String getFirstname() {return this.firstname;}
	public	String getLastname() {return this.lastname;}
	public	int getId() {return this.id;}
	public	int getAge() {return this.age;}
	
	
}
