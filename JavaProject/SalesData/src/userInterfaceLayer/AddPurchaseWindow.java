package userInterfaceLayer;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import buisnessLogicLayer.*;
import dataLayer.SQLConnectorSingleton;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import dataLayer.*;
public class AddPurchaseWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	int i = 0;
	int j = 0; 


	
	public AddPurchaseWindow() {
		Stack<Integer> customerIds = new Stack<Integer>(); 
		Stack<String> productNames = new Stack<String>();
		SQLConnectorSingleton conn = SQLConnectorSingleton.getConnector(); 
		conn.myConGetProductNames(productNames);
		conn.myConGetCustomerId(customerIds);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 364, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setText(Integer.toString(customerIds.get(i)));
		textField.setBounds(123, 51, 116, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText(productNames.get(j));
		textField_1.setBounds(123, 143, 116, 29);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("<-");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i>0)
				{
					i--; 
					textField.setText(Integer.toString(customerIds.get(i)));
					
				}
			}
		});
		btnNewButton.setBounds(123, 13, 46, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("->");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i<(customerIds.size() -1))
				{
					i++;
					textField.setText(Integer.toString(customerIds.get(i)));
				}
			}
		});
		btnNewButton_1.setBounds(192, 13, 46, 25);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Customer Id:");
		lblNewLabel.setBounds(20, 51, 91, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Product Name:");
		lblNewLabel_1.setBounds(12, 143, 99, 29);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("<-");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(j>0)
				{
					j--; 
					textField_1.setText(productNames.get(j));
				}
				
			}
		});
		btnNewButton_2.setBounds(123, 111, 46, 25);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1_1 = new JButton("->");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(j<(productNames.size() - 1))
				{
					j++; 
					textField_1.setText(productNames.get(j));
				}
			}
		});
		btnNewButton_1_1.setBounds(192, 111, 46, 25);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_3 = new JButton("Add Purchase");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					conn.myConInsertPurchase(new Purchase(customerIds.get(i).intValue(),productNames.get(j)));
				} catch (Exception e1) 
				{
					JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getMessage(), JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton_3.setBounds(74, 206, 205, 49);
		contentPane.add(btnNewButton_3);
	}

}
