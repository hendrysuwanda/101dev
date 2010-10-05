package org.softlang.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.softlang.company.Employee;

public class EmployeeView {

	private Employee empl;
	int line = 0;
	JFrame frame;
	JPanel panel;

	public EmployeeView(Employee empl) {
		this.empl = empl;
		setConfig();
		setGui();
	}

	public void setGui() {
		JLabel label1 = new JLabel("Employee:");
		label1.setBounds(10, (line += 20), 150, 20);
		this.panel.add(label1);
		final JTextField tf1 = new JTextField(01);
		tf1.setBounds(10, (line += 20), 150, 20);
		tf1.setText(empl.getPerson().getName());
		tf1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf1.getText();
				empl.getPerson().setName(name);
				System.out.println(empl.getPerson().getName() + " updated");
			}

		});
		this.panel.add(tf1);

		JLabel label2 = new JLabel("Address:");
		label2.setBounds(10, (line += 20), 150, 20);
		this.panel.add(label2);
		final JTextField tf2 = new JTextField(01);
		tf2.setBounds(10, (line += 20), 150, 20);
		tf2.setText(empl.getPerson().getAddress());
		tf2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String address = tf2.getText();
				empl.getPerson().setAddress(address);
				System.out.println("Address of " + empl.getPerson().getName()
						+ " updated");
			}

		});
		this.panel.add(tf2);

		JLabel label3 = new JLabel("Salary:");
		label3.setBounds(10, (line += 20), 150, 20);
		this.panel.add(label3);
		final JTextField tf3 = new JTextField(01);
		tf3.setBounds(10, (line += 20), 150, 20);
		tf3.setText(String.valueOf(empl.getSalary()));
		tf3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int salary = Integer.valueOf(tf3.getText());
				empl.setSalary(salary);
				System.out.println("Salary of " + empl.getPerson().getName()
						+ " updated " + salary);
			}
		});
		this.panel.add(tf3);

		JButton jbn = new JButton("<<");
		jbn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				System.out.println("back!");
			}
		});
		jbn.setBounds(10, (line += 30), 50, 30);
		this.panel.add(jbn);
		
//		JButton del = new JButton("Delete");
//		del.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {	
//				// ....
//			}
//		});
//		del.setBounds(60, line, 80, 30);
//		this.panel.add(del);
		
		this.panel.setLayout(null);
		this.frame.getContentPane().add(this.panel, BorderLayout.CENTER);
		this.frame.setVisible(true);
	}

	public void setConfig() {
		this.frame = new JFrame("Employee-ID: " + empl.getEmployeeid());
		this.frame.setSize(200, 210);
		this.panel = new JPanel();
	}

}