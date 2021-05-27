package buisnessLogicLayer;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class SalesGraphTest {

	@Test
	void test() {
		
		SalesGraph stats = new SalesGraphFilterFlavor("A"); 
		stats.addItem(new SnackItem("A","A","A"),new Integer(2));
		stats.addItem(new SnackItem("B","A","A"),new Integer(4));
		stats.addItem(new SnackItem("C","B","B"),new Integer(4));
		stats.addItem(new SnackItem("D","B","C"),new Integer(111));
		
		Assert.assertEquals("Wrong number of elements",4,stats.itemsAmount()); 
		
		stats.filter(); 
		
		SnackItem item = new SnackItem(); 
		Integer in = new Integer(0); 
	
		while(stats.itemsAmount() > 0)
		{
		
			item = stats.getTopItem();
			stats.popItem();
			if(item.getFlavor().equals("A")== false)
			{
				fail("Didn't filter correctly");
			}
			
		}
	 
	
	    stats = new SalesGraphFilterFlavor("A"); 
		stats.addItem(new SnackItem("A","A","A"),new Integer(2));
		stats.addItem(new SnackItem("B","A","A"),new Integer(4));
		stats.addItem(new SnackItem("C","B","B"),new Integer(4));
		stats.addItem(new SnackItem("D","B","C"),new Integer(111));
		
		Assert.assertEquals("Wrong number of elements",4,stats.itemsAmount()); 
		
		stats.filter(); 
		

		
		while(stats.itemsAmount() > 0)
		{
		
			item = stats.getTopItem();
			stats.popItem();
			if(!item.getType().equals("A"))
			{
				fail("Didn't filter correctly");
			}
			
		}
	    
		
		
	}

}
