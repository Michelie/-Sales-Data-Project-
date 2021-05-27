package buisnessLogicLayer;
/**
 * 
 */

/**
 * @author AFQ343
 *
 */
public class SnackItem
{
String name;
String flavor; 
String type;
  public  SnackItem()
    {
    	this.name = this.flavor = this.type ="?"; 
    }

 public	SnackItem(String name, String flavor, String type)
	{
		this.name = name;
		this.flavor = flavor;
		this.type = type; 
	}
	
 public	String getName() {return this.name;}
 public	String getFlavor() {return this.flavor;}
 public	String getType() {return this.type;}
}
