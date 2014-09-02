package vodic;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import database.TableManager;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class VodiciZmena {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public JTable table;
	public TableManager tableManager;
	public int id;
	private JButton btnPrida;
	private JPanel panel;

	public VodiciZmena(JTable table, TableManager tableManager, int id) {
		this.table = table;
		this.tableManager = tableManager;
		this.id = id;
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 303, 217);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 11, 267, 157);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblVodiid = new JLabel("Meno");
		lblVodiid.setBounds(10, 44, 89, 14);
		panel.add(lblVodiid);

		JLabel lblVozidlo = new JLabel("Priezvisko");
		lblVozidlo.setBounds(10, 69, 89, 14);
		panel.add(lblVozidlo);

		textField = new JTextField();
		textField.setBounds(109, 41, 148, 20);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(109, 66, 148, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		btnPrida = new JButton("Potvrdi\u0165 zmeny");
		btnPrida.setBounds(10, 123, 119, 23);
		panel.add(btnPrida);

		JLabel lblPridanieNovhoZznamu = new JLabel(
				"Zmena \u00FAdajov zamestnanca");
		lblPridanieNovhoZznamu.setFont(new Font("Franklin Gothic Medium",
				Font.PLAIN, 14));
		lblPridanieNovhoZznamu.setBounds(10, 11, 212, 22);
		panel.add(lblPridanieNovhoZznamu);

		JLabel lblRodnslo = new JLabel("Rodn\u00E9 \u010D\u00EDslo");
		lblRodnslo.setBounds(10, 94, 89, 14);
		panel.add(lblRodnslo);

		textField_2 = new JTextField();
		textField_2.setBounds(109, 92, 148, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		

	}
	public JTextField getTextField() {
		return textField;
	}
	public JTextField getTextField_1() {
		return textField_1;
	}
	public JButton getBtnPrida() {
		return btnPrida;
	}
	public JTextField getTextField_2() {
		return textField_2;
	}
	public JFrame getFrame() {
		return frame;
	}
	public JPanel getPanel() {
		return panel;
	}
}
