package buisnessLogicLayer;

public class Purchase {
	
	int customerID; 
	String Item; 
	
	public Purchase(int customerID, String Item) {this.customerID =customerID; this.Item= Item;}
	public int getID() {return this.customerID;}
	public String getItem() {return this.Item;}
	

}
