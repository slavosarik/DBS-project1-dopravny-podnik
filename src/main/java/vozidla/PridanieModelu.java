package vozidla;


import javax.swing.*;

import database.TableManager;

import java.awt.Font;

public class PridanieModelu {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JButton btnPrida;
	private JPanel panel;
	private JButton button;

	public PridanieModelu(JTable table, TableManager tableManager) {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 288, 373);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 11, 251, 312);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblVodiid = new JLabel("Model");
		lblVodiid.setBounds(10, 44, 102, 14);
		panel.add(lblVodiid);

		JLabel lblVozidlo = new JLabel("Farba");
		lblVozidlo.setBounds(10, 94, 102, 14);
		panel.add(lblVozidlo);

		JLabel lblLinka = new JLabel("Objem motora");
		lblLinka.setBounds(10, 119, 102, 14);
		panel.add(lblLinka);

		JLabel lblDtum = new JLabel("V\u00FDkon motoru");
		lblDtum.setBounds(10, 144, 102, 14);
		panel.add(lblDtum);

		textField = new JTextField();
		textField.setBounds(122, 41, 110, 20);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(122, 66, 110, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(122, 91, 110, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		btnPrida = new JButton("Prida\u0165");		
		btnPrida.setBounds(10, 244, 89, 23);
		panel.add(btnPrida);

		JLabel lblPridanieNovhoZznamu = new JLabel(
				"Pridanie nov\u00E9ho z\u00E1znamu");
		lblPridanieNovhoZznamu.setFont(new Font("Franklin Gothic Medium",
				Font.PLAIN, 14));
		lblPridanieNovhoZznamu.setBounds(10, 11, 212, 22);
		panel.add(lblPridanieNovhoZznamu);

		JLabel lblNewLabel = new JLabel("Po\u010Det dver\u00ED");
		lblNewLabel.setBounds(10, 169, 102, 14);
		panel.add(lblNewLabel);

		JLabel lblHmotnos = new JLabel("Hmotnos\u0165");
		lblHmotnos.setBounds(10, 194, 102, 14);
		panel.add(lblHmotnos);

		JLabel lblKapacita = new JLabel("Kapacita");
		lblKapacita.setBounds(10, 219, 102, 14);
		panel.add(lblKapacita);

		textField_3 = new JTextField();
		textField_3.setBounds(122, 116, 110, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(122, 141, 110, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(122, 166, 110, 20);
		panel.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(122, 191, 110, 20);
		panel.add(textField_6);
		textField_6.setColumns(10);

		textField_7 = new JTextField();
		textField_7.setBounds(122, 217, 110, 20);
		panel.add(textField_7);
		textField_7.setColumns(10);

		JLabel lblZnaka = new JLabel("Zna\u010Dka");
		lblZnaka.setBounds(10, 69, 89, 14);
		panel.add(lblZnaka);

		button = new JButton("Zaevidovanie novej zna\u010Dky");
		button.setBounds(10, 278, 201, 23);
		panel.add(button);	
		

	}
	public JButton getBtnPrida() {
		return btnPrida;
	}
	public JTextField getTextField_1() {
		return textField_1;
	}
	public JTextField getTextField_7() {
		return textField_7;
	}
	public JTextField getTextField_5() {
		return textField_5;
	}
	public JTextField getTextField() {
		return textField;
	}
	public JTextField getTextField_6() {
		return textField_6;
	}
	public JTextField getTextField_4() {
		return textField_4;
	}
	public JTextField getTextField_3() {
		return textField_3;
	}
	public JTextField getTextField_2() {
		return textField_2;
	}
	public JPanel getPanel() {
		return panel;
	}
	public JFrame getFrame() {
		return frame;
	}
	public JButton getButton() {
		return button;
	}
}
