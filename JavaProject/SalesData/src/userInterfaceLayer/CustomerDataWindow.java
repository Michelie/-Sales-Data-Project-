package userInterfaceLayer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import buisnessLogicLayer.*;
import dataLayer.SQLConnectorSingleton;

import java.awt.GridLayout;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import dataLayer.*;
public class CustomerDataWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtName2;
	private JTextField txtName3;
	private JTextField txtName4;
	int i=0; 
	

	/**
	 * Create the frame.
	 */
	public CustomerDataWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Stack<Customer> customers = new Stack<Customer>(); 
		SQLConnectorSingleton con = SQLConnectorSingleton.getConnector();
		con.myConCustomerDataNoAge(customers);
		
		
		
		JButton btnNewButton = new JButton("Prev");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(i>0)
				{
					i--; 
					txtName.setText(customers.get(i).getFirstname());
					txtName2.setText(customers.get(i).getLastname());
					txtName3.setText(Integer.toString(customers.get(i).getAge()));
					txtName4.setText(Integer.toString(customers.get(i).getId()));
				}
			}
		});
		btnNewButton.setBounds(53, 30, 77, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i<(customers.size()-1))
				{
					i++; 
					txtName.setText(customers.get(i).getFirstname());
					txtName2.setText(customers.get(i).getLastname());
					txtName3.setText(Integer.toString(customers.get(i).getAge()));
					txtName4.setText(Integer.toString(customers.get(i).getId()));
				}
			}
		});
		btnNext.setBounds(244, 30, 77, 25);
		contentPane.add(btnNext);
		
		JLabel lblNewLabel = new JLabel("Fristname:");
		lblNewLabel.setBounds(12, 82, 77, 25);
		contentPane.add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setText(customers.get(i).getFirstname());
		txtName.setBounds(101, 83, 116, 22);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Lastname:");
		lblNewLabel_1.setBounds(12, 120, 60, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Age:");
		lblNewLabel_2.setBounds(12, 161, 60, 25);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Id:");
		lblNewLabel_3.setBounds(12, 199, 56, 25);
		contentPane.add(lblNewLabel_3);
		
		txtName2 = new JTextField();
		txtName2.setText(customers.get(i).getLastname());
		txtName2.setBounds(101, 122, 116, 22);
		contentPane.add(txtName2);
		txtName2.setColumns(10);
		
		txtName3 = new JTextField();
		txtName3.setText(Integer.toString(customers.get(i).getAge()));
		txtName3.setBounds(101, 162, 116, 22);
		contentPane.add(txtName3);
		txtName3.setColumns(10);
		
		txtName4 = new JTextField();
		txtName4.setText(Integer.toString(customers.get(i).getId()));
		txtName4.setBounds(101, 200, 116, 22);
		contentPane.add(txtName4);
		txtName4.setColumns(10);
	}
}
