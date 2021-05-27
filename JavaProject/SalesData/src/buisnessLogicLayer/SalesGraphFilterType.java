package buisnessLogicLayer;

public class SalesGraphFilterType extends SalesGraph implements FilterType {

String type; 

public SalesGraphFilterType(String type)
{
	super();
	this.type=type; 
	
}

public void filterByType()
{
	for(int i=0;i<items.size();i++)
	{
		if(!(items.get(i).type.equals(type)))
		{
			items.remove(i);
			sales.remove(i);
			i=i-1; 
		}
	}
}


public void filter()
{
	this.filterByType();
}
}
