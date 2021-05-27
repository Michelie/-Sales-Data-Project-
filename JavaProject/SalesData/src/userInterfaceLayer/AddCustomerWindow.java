package userInterfaceLayer;
/** wtf is going on with github???*/ 


import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import buisnessLogicLayer.*;
import dataLayer.SQLConnectorSingleton;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import dataLayer.*;
public class AddCustomerWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtfield;
	private JTextField txtfield_1;
	private JTextField txtLastname;
	private JTextField textField_3;

	
	
	public AddCustomerWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 275, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtfield = new JTextField();
		txtfield.setText("0");
		txtfield.setBounds(100, 42, 100, 22);
		contentPane.add(txtfield);
		txtfield.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Id:");
		lblNewLabel.setBounds(46, 47, 29, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Firstname:");
		lblNewLabel_1.setBounds(8, 80, 75, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Lastname:");
		lblNewLabel_1_1.setBounds(8, 106, 92, 22);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Age:");
		lblNewLabel_1_2.setBounds(31, 141, 44, 16);
		contentPane.add(lblNewLabel_1_2);
		
		txtfield_1 = new JTextField();
		txtfield_1.setText("firstname");
		txtfield_1.setColumns(10);
		txtfield_1.setBounds(100, 77, 100, 22);
		contentPane.add(txtfield_1);
		
		txtLastname = new JTextField();
		txtLastname.setText("lastname");
		txtLastname.setColumns(10);
		txtLastname.setBounds(100, 106, 100, 22);
		contentPane.add(txtLastname);
		
		textField_3 = new JTextField();
		textField_3.setText("0");
		textField_3.setColumns(10);
		textField_3.setBounds(100, 135, 100, 22);
		contentPane.add(textField_3);
		
		JButton btnNewButton = new JButton("Add Customer ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
				String temp_str = txtfield.getText(); 
				boolean inc_str = false;
				
				
				for(int j =0;j<temp_str.length(); j++)
				{
					if(!Character.isDigit(temp_str.charAt(j)))
					{
						inc_str= true; 
					}
					
				}
				
				if(inc_str)
				{
					throw new Exception("Id must be a number");
				}
				
				 temp_str = textField_3.getText();
				 for(int j =0;j<temp_str.length(); j++)
					{
						if(!Character.isDigit(temp_str.charAt(j)))
						{
							inc_str= true; 
						}
						
					}
				 if(inc_str)
				 {
					 throw new Exception("Age must be a number");
				 }
				 
				 if(txtfield.getText().length()<1 || txtLastname.getText().length()<1 || txtfield_1.getText().length() <1 || textField_3.getText().length() < 1)
				 {
					 throw new Exception("none of the fields can be empty");
				 }
				 
				 SQLConnectorSingleton conn = SQLConnectorSingleton.getConnector(); 
				 conn.myConInsertCustomer(new Customer(Integer.parseInt(txtfield.getText()),  txtfield_1.getText(),txtLastname.getText(), Integer.parseInt(textField_3.getText())));
			
			}
				catch(Exception exp) {JOptionPane.showMessageDialog(null, exp.getMessage(), exp.getMessage(), JOptionPane.INFORMATION_MESSAGE); }
			}
		});
		btnNewButton.setBounds(63, 177, 137, 63);
		contentPane.add(btnNewButton);
		
		
	}

}
