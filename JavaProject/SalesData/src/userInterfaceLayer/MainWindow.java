package userInterfaceLayer;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JTree;
import javax.swing.event.ChangeListener;

import buisnessLogicLayer.SalesGraph;
import buisnessLogicLayer.SalesGraphAbstractFactory;
import buisnessLogicLayer.SalesGraphType;
import buisnessLogicLayer.SnackItem;
import dataLayer.SQLConnectorSingleton;

import javax.swing.event.ChangeEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class MainWindow {
	boolean filterByAge = false; 
	boolean filterByType=false;
	boolean filterByFlavor=false; 
	String flavor ="?"; 
	String type="?"; 
	int startAge = 0;
	int endAge=18; 
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 597, 529);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Get stats");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
		        HistogramPanel panel = new HistogramPanel();
		        SalesGraph stats;
		        
		        if(filterByType)
		        {
		        	stats = SalesGraphAbstractFactory.getSalesGraph(SalesGraphType.TYPE,type,flavor);
		        }
		        else if(filterByFlavor)
		        {
		        	stats =  SalesGraphAbstractFactory.getSalesGraph(SalesGraphType.FLAVOR,type,flavor);	
		        }
		        else
		        {
		        	stats =  SalesGraphAbstractFactory.getSalesGraph(SalesGraphType.REGULAR,type,flavor);    
		        }
		        
		        SQLConnectorSingleton connect = SQLConnectorSingleton.getConnector(); 
		        
		        if(!filterByAge)
		        {
		        	connect.myCon(stats);
		        }
		        else
		        {
		        	connect.myConFilterAge(stats, startAge, endAge);
		        }
		        
		        stats.filter();
		        
		        
		        SnackItem item;
		        Integer sale;
		        while(stats.itemsAmount()>0)
		        {
		        	item=stats.getTopItem();
		        	sale =stats.getTopSale();
		        	stats.popItem();
		        	panel.addHistogramColumn(item.getName() +"\n sales",sale,Color.BLUE);
		        }
		      
		        panel.layoutHistogram();

		        JFrame frame = new JFrame("Histogram Panel");
		        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        frame.getContentPane().add( panel );
		        frame.setLocationByPlatform( true );
		        frame.pack();
		        frame.setVisible( true );
			}
		});
		btnNewButton.setBounds(234, 379, 97, 25);
		frame.getContentPane().add(btnNewButton);
		ButtonGroup bgHow = new ButtonGroup();
		
		
			JRadioButton rdbtnNewRadioButton = new JRadioButton("Type");
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filterByType=true;
					filterByFlavor=false; 
				}
			});
			rdbtnNewRadioButton.setBounds(69, 280, 57, 25);
			frame.getContentPane().add(rdbtnNewRadioButton);
		
			JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Flavor");
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filterByType=false;
					filterByFlavor=true; 
				}
			});
			rdbtnNewRadioButton_1.setBounds(129, 280, 63, 25);
			frame.getContentPane().add(rdbtnNewRadioButton_1);
		
			JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("None");
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filterByType=false;
					filterByFlavor=false; 
				}
			});
			rdbtnNewRadioButton_2.setSelected(true);
			rdbtnNewRadioButton_2.setBounds(189, 280, 127, 25);
			frame.getContentPane().add(rdbtnNewRadioButton_2);
		
		bgHow.add(rdbtnNewRadioButton);
		bgHow.add(rdbtnNewRadioButton_1);
		bgHow.add(rdbtnNewRadioButton_2);
			
			JLabel lblNewLabel = new JLabel("Filter by:");
			lblNewLabel.setBounds(12, 278, 108, 29);
			frame.getContentPane().add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Which type: ");
			lblNewLabel_1.setBounds(12, 315, 86, 16);
			frame.getContentPane().add(lblNewLabel_1);
			
			JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Drink");
			rdbtnNewRadioButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					type ="Drink"; 
				}
			});
			rdbtnNewRadioButton_3.setBounds(84, 311, 57, 25);
			frame.getContentPane().add(rdbtnNewRadioButton_3);
			
			JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("Food");
			rdbtnNewRadioButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					type ="Food"; 
				}
			});
			rdbtnNewRadioButton_4.setBounds(139, 311, 127, 25);
			frame.getContentPane().add(rdbtnNewRadioButton_4);
			
			ButtonGroup bgType = new ButtonGroup();
			
		bgType.add(rdbtnNewRadioButton_3);
		bgType.add(rdbtnNewRadioButton_4);
			
			JLabel lblNewLabel_2 = new JLabel("Which flavor:");
			lblNewLabel_2.setBounds(12, 344, 76, 16);
			frame.getContentPane().add(lblNewLabel_2);
			
			JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("Sweet");
			rdbtnNewRadioButton_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					flavor ="Sweet"; 
				}
			});
			rdbtnNewRadioButton_5.setBounds(94, 340, 65, 25);
			frame.getContentPane().add(rdbtnNewRadioButton_5);
			
			JRadioButton rdbtnNewRadioButton_6 = new JRadioButton("Salty");
			rdbtnNewRadioButton_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					flavor ="Salty"; 
				}
			});
			rdbtnNewRadioButton_6.setBounds(159, 341, 57, 25);
			frame.getContentPane().add(rdbtnNewRadioButton_6);
			
			JRadioButton rdbtnNewRadioButton_7 = new JRadioButton("Spicy");
			rdbtnNewRadioButton_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					flavor ="Spicy"; 
				}
			});
			rdbtnNewRadioButton_7.setBounds(214, 340, 63, 25);
			frame.getContentPane().add(rdbtnNewRadioButton_7);
			
			JRadioButton rdbtnNewRadioButton_8 = new JRadioButton("Sour");
			rdbtnNewRadioButton_8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					flavor="Sour"; 
				}
			});
			rdbtnNewRadioButton_8.setBounds(279, 340, 127, 25);
			frame.getContentPane().add(rdbtnNewRadioButton_8);
		
		ButtonGroup bgFlavor = new ButtonGroup();
		bgFlavor.add(rdbtnNewRadioButton_5);
		bgFlavor.add(rdbtnNewRadioButton_6);
		bgFlavor.add(rdbtnNewRadioButton_7);
		bgFlavor.add(rdbtnNewRadioButton_8);
		
		JLabel lblNewLabel_3 = new JLabel("Product filter:");
		lblNewLabel_3.setBounds(12, 260, 180, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Customer filter:");
		lblNewLabel_4.setBounds(0, 29, 147, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		JRadioButton rdbtnNewRadioButton_9 = new JRadioButton("Yes");
		rdbtnNewRadioButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterByAge = true; 
			}
		});
		rdbtnNewRadioButton_9.setBounds(84, 54, 57, 25);
		frame.getContentPane().add(rdbtnNewRadioButton_9);
		
		JRadioButton rdbtnNewRadioButton_9_1 = new JRadioButton("No");
		rdbtnNewRadioButton_9_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterByAge = false; 
			}
		});
		rdbtnNewRadioButton_9_1.setSelected(true);
		rdbtnNewRadioButton_9_1.setBounds(139, 54, 57, 25);
		frame.getContentPane().add(rdbtnNewRadioButton_9_1);
		
		
		ButtonGroup AgeFilterDecider = new ButtonGroup(); 
		AgeFilterDecider.add( rdbtnNewRadioButton_9); 
		AgeFilterDecider.add( rdbtnNewRadioButton_9_1); 
		
		
		
		
		JLabel lblNewLabel_5 = new JLabel("Age Filter:");
		lblNewLabel_5.setBounds(10, 56, 73, 21);
		frame.getContentPane().add(lblNewLabel_5);
		
	
		
		JLabel lblNewLabel_6 = new JLabel("Age grouping:");
		lblNewLabel_6.setBounds(6, 75, 114, 25);
		frame.getContentPane().add(lblNewLabel_6);
		
		JRadioButton rdbtnNewRadioButton_10 = new JRadioButton("18 and under");
		rdbtnNewRadioButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startAge = 0;
				endAge = 18; 
				
			}
		});
		rdbtnNewRadioButton_10.setBounds(0, 109, 127, 25);
		frame.getContentPane().add(rdbtnNewRadioButton_10);
		
		JRadioButton rdbtnNewRadioButton_11 = new JRadioButton("18 - 30");
		rdbtnNewRadioButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startAge = 18;
				endAge = 30;
			}
		});
		rdbtnNewRadioButton_11.setBounds(-1, 138, 127, 25);
		frame.getContentPane().add(rdbtnNewRadioButton_11);
		
		JRadioButton rdbtnNewRadioButton_11_1 = new JRadioButton("30 - 45");
		rdbtnNewRadioButton_11_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startAge = 30;
				endAge = 45;
			}
		});
		rdbtnNewRadioButton_11_1.setBounds(0, 172, 127, 25);
		frame.getContentPane().add(rdbtnNewRadioButton_11_1);
		
		JRadioButton rdbtnNewRadioButton_11_2 = new JRadioButton("45 - 65");
		rdbtnNewRadioButton_11_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startAge = 45;
				endAge = 65;
			}
			
		});
		rdbtnNewRadioButton_11_2.setBounds(0, 202, 127, 25);
		frame.getContentPane().add(rdbtnNewRadioButton_11_2);
		
		JRadioButton rdbtnNewRadioButton_11_3 = new JRadioButton("65 and over");
		rdbtnNewRadioButton_11_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startAge = 65;
				endAge = 999;
			}
		});
		rdbtnNewRadioButton_11_3.setBounds(-1, 232, 127, 25);
		frame.getContentPane().add(rdbtnNewRadioButton_11_3);
		
		ButtonGroup AgeRange = new ButtonGroup(); 
		AgeRange.add(rdbtnNewRadioButton_10);
		AgeRange.add(rdbtnNewRadioButton_11);
		AgeRange.add(rdbtnNewRadioButton_11_1);
		AgeRange.add(rdbtnNewRadioButton_11_2);
		AgeRange.add(rdbtnNewRadioButton_11_3);
		
		JButton btnNewButton_1 = new JButton("Get Customer Data");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerDataWindow cusWindow = new CustomerDataWindow(); 
				cusWindow.setVisible(true);
				cusWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton_1.setBounds(372, 29, 162, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Get Product Data");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemDataWindow itemWin = new ItemDataWindow(); 
				itemWin.setVisible(true);
				itemWin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton_2.setBounds(372, 109, 162, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Add Customers");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCustomerWindow addCusWin = new AddCustomerWindow();
				addCusWin.setVisible(true);
				addCusWin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
			
		});
		btnNewButton_3.setBounds(372, 67, 162, 25);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Add Products");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProductWindow addPrudWin = new AddProductWindow(); 
				addPrudWin.setVisible(true);
				addPrudWin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton_4.setBounds(372, 147, 162, 25);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Add Purchases");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPurchaseWindow addPurWin = new AddPurchaseWindow(); 
				addPurWin.setVisible(true);
				addPurWin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
			}
		});
		btnNewButton_5.setBounds(372, 185, 162, 25);
		frame.getContentPane().add(btnNewButton_5);
	}
}
