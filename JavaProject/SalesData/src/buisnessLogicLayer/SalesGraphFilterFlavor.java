package buisnessLogicLayer;

public class SalesGraphFilterFlavor extends SalesGraph implements FilterFlavor
{
String flavor; 


public SalesGraphFilterFlavor(String flavor ) 
{
	super(); 
	this.flavor =flavor; 
}

	@Override
	public void filterByFlavor()
	{
		
		for(int i=0;i<items.size();i++)
		{
			if(!(items.get(i).flavor.equals(flavor)))
			{
				items.remove(i);
				sales.remove(i);
				i=i-1; 
			}
		}
		
	}
	
	public void filter()
	{
	this.filterByFlavor();
		
	}
	
}
