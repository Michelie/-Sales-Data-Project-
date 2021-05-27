package buisnessLogicLayer;
import java.util.Stack; 

	

	interface FilterFlavor
	{
		public void filterByFlavor(); 
	}
	
	interface FilterType
	{
		public void filterByType();
	}
	
	
	public abstract class SalesGraph
	{
	public Stack<SnackItem> items; 
	public Stack<Integer> sales; 
	
	public	SalesGraph() 
		{
		this.items = new Stack<SnackItem>(); 
		this.sales = new Stack<Integer>(); 
		
		}
		
		public void addItem(SnackItem item, Integer sale)
		{
			items.push(item);
			sales.push(sale); 
		}

		
		public void popItem()
		{
		 items.pop();
		 sales.pop();	
		}
		public SnackItem getTopItem() {return items.peek();}
		public int getTopSale() {return sales.peek();}
		
		public int itemsAmount() {return items.size();}
			
		public abstract void filter(); 
	}

	
