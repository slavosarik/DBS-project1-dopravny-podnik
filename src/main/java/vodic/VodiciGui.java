package vodic;


import javax.swing.*;

import database.TableManager;

import java.awt.event.*;
import java.sql.SQLException;


public class VodiciGui {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	

	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JButton btnPridanieZamestnanca;
	private JButton btnZmenaDetailov;
	private JButton btnPrepustenieZamestnanca;
	private JButton btnVyh;
	private JButton btnNewButton;
	private JButton btnObnovi;
	private JButton btnNewButton_2;
	private JButton btnNewButton_1;
	


	public VodiciGui() throws SQLException {
		initialize();
		frame.setVisible(true);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() throws SQLException {

		frame = new JFrame();
		frame.setBounds(100, 100, 407, 519);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 371, 462);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 351, 168);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setToolTipText("Pre usporiadaný výber kliknite na header.");

		btnPridanieZamestnanca = new JButton("Pridanie zamestnanca");
		btnPridanieZamestnanca.setBounds(10, 190, 180, 23);
		panel.add(btnPridanieZamestnanca);

		btnZmenaDetailov = new JButton("Zmena detailov");
		btnZmenaDetailov.setBounds(10, 224, 180, 23);
		panel.add(btnZmenaDetailov);

		btnPrepustenieZamestnanca = new JButton("Prepustenie zamestnanca");
		btnPrepustenieZamestnanca.setBounds(10, 258, 180, 23);
		panel.add(btnPrepustenieZamestnanca);

		btnVyh = new JButton("Vyh\u013Eadanie zamestnanca");
		btnVyh.setBounds(10, 292, 180, 23);
		panel.add(btnVyh);

		comboBox = new JComboBox();
		comboBox.setBounds(209, 293, 152, 20);
		comboBox.setSelectedIndex(-1);
		panel.add(comboBox);
		ActionListener actionListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				textField.setVisible(true);
			}
		};
		comboBox.addActionListener(actionListener1);		

		textField = new JTextField();
		textField.setBounds(209, 326, 152, 20);		
		textField.setColumns(10);
		textField.setVisible(false);
		panel.add(textField);
		
		btnObnovi = new JButton("Obnovi\u0165");
		btnObnovi.setBounds(10, 428, 180, 23);
		panel.add(btnObnovi);

		btnNewButton = new JButton("Detaily zamestnanca");
		btnNewButton.setBounds(10, 326, 180, 23);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Najviac sp\u00F4soben\u00FDch neh\u00F4d");
		btnNewButton_1.setBounds(10, 360, 180, 23);
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Najviac \u0161kodov\u00FD vodi\u010Di");
		btnNewButton_2.setBounds(10, 394, 180, 23);
		panel.add(btnNewButton_2);
		
	}
	public JTable getTable() {
		return table;
	}
	public JButton getBtnPridanieZamestnanca() {
		return btnPridanieZamestnanca;
	}
	public JButton getBtnZmenaDetailov() {
		return btnZmenaDetailov;
	}
	public JButton getBtnPrepustenieZamestnanca() {
		return btnPrepustenieZamestnanca;
	}
	public JFrame getFrame() {
		return frame;
	}
	public JButton getBtnVyh() {
		return btnVyh;
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
	public JTextField getTextField() {
		return textField;
	}
	public JButton getBtnNewButton() {
		return btnNewButton;
	}
	public JButton getBtnObnovi() {
		return btnObnovi;
	}
	public JButton getBtnNewButton_2() {
		return btnNewButton_2;
	}
	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}
}
