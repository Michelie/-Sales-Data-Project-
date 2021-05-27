
package dataLayer;
import java.sql.*;
import java.util.Stack;

import buisnessLogicLayer.*; 


public class SQLConnectorSingleton 
{
/* Private constructor for singleton */	
	
	private SQLConnectorSingleton()
	{
		
	}
	
	
	static SQLConnectorSingleton instance = null;
	
	
// method that only returns one instance 
	
	
	static public SQLConnectorSingleton getConnector()
	{
		if(instance == null)
		{
			instance = new SQLConnectorSingleton();
		}
		
		return instance; 
		
	}
	
	public void myCon(SalesGraph graph) 
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("select s.name,flavor,type,count(id) from snackitems as s"
				+ " join snackbought as l using(name) group by s.name;");
		while(rs.next())
		{
		graph.addItem(new SnackItem(rs.getString(1),rs.getString(2),rs.getString(3)),new Integer(rs.getInt(4)));
		}
		con.close();
		
		}
		catch(Exception e) {System.out.println(e);}
	}
	
	
	
	
	
	
	public void myConFilterAge(SalesGraph graph,int start, int end)
	{
		
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("select s.name,flavor,type,count(id) from snackitems as s"
				+ " join snackbought as l using(name)" +" join snackbuyer as t using(Id) "+" where (t.Age < "+Integer.toString(end)+" and t.age > "+ Integer.toString(start) +") group by s.name;");
		while(rs.next())
		{
		graph.addItem(new SnackItem(rs.getString(1),rs.getString(2),rs.getString(3)),new Integer(rs.getInt(4)));
		}
		con.close();
		
		}
		catch(Exception e) {System.out.println(e);}
		
	}
	
	
	public void myConCustomerDataNoAge(Stack<Customer> customers) 
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from snackbuyer;");
		while(rs.next())
		{
		customers.push(new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
		}
		con.close();
		
		}
		catch(Exception e) {System.out.println(e);}
	}
	
	public void myConProductNoFilter(Stack<SnackItem> Items) 
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from snackitems;");
		while(rs.next())
		{
		Items.push(new SnackItem(rs.getString(1),rs.getString(2),rs.getString(3)));
		}
		con.close();
		
		}
		catch(Exception e) {System.out.println(e);}
	}
	
	
	public void myConInsertCustomer(Customer cus) throws Exception
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
		Statement stmt = con.createStatement();
		stmt.executeUpdate("insert into snackbuyer(Id,FirstName,LastName,Age) Values("+Integer.toString(cus.getId())+",\""+cus.getFirstname() +"\",\""+cus.getLastname()+"\","+Integer.toString(cus.getAge())+");");
		
		con.close();
		
		}
		catch(Exception e) {throw e; }
	}
	
	public void myConInsertProduct(SnackItem t) throws Exception
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
		Statement stmt = con.createStatement();
		stmt.executeUpdate("insert into snackitems(Name,Flavor,Type) values(\""+t.getName()+"\",\""+t.getFlavor()+"\",\""+t.getType()+"\")");
		
		con.close();
		
		}
		catch(Exception e) {throw e; }
	}
	public void myConGetProductNames(Stack<String> items)
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("select name from snackitems;");
		while(rs.next())
		{
		items.push(rs.getString(1));
		}
		con.close();
		
		}
		catch(Exception e) {System.out.println(e);}
	}
	
	public void myConGetCustomerId(Stack<Integer> ids)
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("select id from snackbuyer;");
		while(rs.next())
		{
		ids.push(Integer.parseInt(rs.getString(1)));
		}
		con.close();
		
		}
		catch(Exception e) {System.out.println(e);}
	}
	public void myConInsertPurchase(Purchase pur) throws Exception 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
			Statement stmt = con.createStatement();
			stmt.executeUpdate("insert into snackbought values ("+pur.getID()+",\""+pur.getItem()+"\")");
			
		}
		catch(Exception e) {throw e;}
	}
	
}
