package gui;

import java.awt.EventQueue;
import javax.swing.*;

import database.TableManager;

import java.awt.event.*;
import java.sql.SQLException;
import logic.*;

public class Menu_GUI {

	private JFrame frame;

	public static void setTheWindowLook() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_GUI window = new Menu_GUI();
					window.frame.setVisible(true);
					TableManager.getSingletonObject();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu_GUI() {
		initialize();
	}

	private void initialize() {
		setTheWindowLook();
		frame = new JFrame();
		frame.setBounds(100, 100, 176, 204);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 140, 144);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton button_3 = new JButton("V\u00FDprava");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new VypravaLogika();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_3.setBounds(10, 10, 119, 23);
		panel.add(button_3);

		JButton button_2 = new JButton("Zamestnanci");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new VodicLogika();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_2.setBounds(10, 43, 119, 23);
		panel.add(button_2);

		JButton button_1 = new JButton("Vozov\u00FD park");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new VozidloLogika();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(10, 76, 119, 23);
		panel.add(button_1);

		JButton button = new JButton("Linky");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new LinkaLogika();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setBounds(10, 109, 119, 23);
		panel.add(button);
	}

}
