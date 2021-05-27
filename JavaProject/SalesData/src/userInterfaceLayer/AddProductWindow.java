package userInterfaceLayer;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import buisnessLogicLayer.*;
import dataLayer.SQLConnectorSingleton;

import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import dataLayer.*;

public class AddProductWindow extends JFrame {

	
	String type ="Drink"; 
	String flavor="Salty"; 
	
	private JPanel contentPane;
	private JTextField textField;

	public AddProductWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] Flavors = {"Salty","Sweet","Sour","Spicy"};
		String[] Types = {"Drink","Food"};
		
		JList list_1 = new JList(Types);
		list_1.setSelectedIndex(0);
		list_1.setSelectedIndices(new int[] {0});
		list_1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				type = Types[list_1.getSelectedIndex()]; 
			}
		});
		list_1.setBounds(320, 109, 101, 36);
		contentPane.add(list_1);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(153, 108, 134, 54);
		contentPane.add(scrollPane);
		
		JList list = new JList(Flavors);
		list.setSelectedIndex(0);
		scrollPane.setViewportView(list);
		list.setSelectedIndices(new int[] {0});
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				flavor =Flavors[list.getSelectedIndex()];
			}
			
			
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(4);
		
		textField = new JTextField();
		textField.setText("");
		textField.setBounds(12, 107, 105, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(27, 85, 72, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Flavor:");
		lblNewLabel_1.setBounds(180, 85, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Type:");
		lblNewLabel_2.setBounds(344, 85, 56, 16);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Add Item ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLConnectorSingleton conn = SQLConnectorSingleton.getConnector(); 
				try 
				{
					if(textField.getText().length() < 1)
					{
						throw new Exception("Product name can't be empty"); 
					}
					
					conn.myConInsertProduct(new SnackItem(textField.getText(),flavor,type));
				}
				catch (Exception e1)
				{
				
					JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getMessage(), JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(168, 196, 157, 44);
		contentPane.add(btnNewButton);
	}
}
