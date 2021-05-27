package userInterfaceLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import buisnessLogicLayer.SnackItem;
import dataLayer.SQLConnectorSingleton;

public class ItemDataWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtName2;
	private JTextField txtName3;
	int i=0;  


	/**
	 * Create the frame.
	 */
	public ItemDataWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Stack<SnackItem> items = new Stack<SnackItem>(); 
		SQLConnectorSingleton con = SQLConnectorSingleton.getConnector();
		con.myConProductNoFilter(items);
		
		
		
		JButton btnNewButton = new JButton("Prev");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(i>0)
				{
					i--; 
					txtName.setText(items.get(i).getName());
					txtName2.setText(items.get(i).getFlavor());
					txtName3.setText(items.get(i).getType());
				
				}
			}
		});
		btnNewButton.setBounds(53, 30, 77, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i<(items.size()-1))
				{
					i++; 
					txtName.setText(items.get(i).getName());
					txtName2.setText(items.get(i).getFlavor());
					txtName3.setText(items.get(i).getType());
					
				}
			}
		});
		btnNext.setBounds(244, 30, 77, 25);
		contentPane.add(btnNext);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(12, 82, 77, 25);
		contentPane.add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setText(items.get(i).getName());
		txtName.setBounds(112, 82, 116, 22);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Flavor:");
		lblNewLabel_1.setBounds(12, 120, 77, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Type:");
		lblNewLabel_2.setBounds(12, 161, 60, 25);
		contentPane.add(lblNewLabel_2);
		
		txtName2 = new JTextField();
		txtName2.setText(items.get(i).getFlavor());
		txtName2.setBounds(112, 121, 116, 22);
		contentPane.add(txtName2);
		txtName2.setColumns(10);
		
		txtName3 = new JTextField();
		txtName3.setText(items.get(i).getType());
		txtName3.setBounds(112, 161, 116, 22);
		contentPane.add(txtName3);
		txtName3.setColumns(10);
	}
}
