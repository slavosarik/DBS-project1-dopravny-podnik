package linka;

import javax.swing.*;
import java.awt.Font;

public class LinkaZmena {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPanel panel;
	private JButton btnPrida;


	public LinkaZmena() {
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

		JLabel lblVodiid = new JLabel("\u010C\u00EDslo");
		lblVodiid.setBounds(10, 44, 89, 14);
		panel.add(lblVodiid);

		JLabel lblVozidlo = new JLabel("Kone\u010Dn\u00E11");
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
		btnPrida.setBounds(10, 123, 161, 23);
		panel.add(btnPrida);

		JLabel lblPridanieNovhoZznamu = new JLabel("Zmena na linke");
		lblPridanieNovhoZznamu.setFont(new Font("Franklin Gothic Medium",
				Font.PLAIN, 14));
		lblPridanieNovhoZznamu.setBounds(10, 11, 212, 22);
		panel.add(lblPridanieNovhoZznamu);

		JLabel lblRodnslo = new JLabel("Kone\u010Dn\u00E12");
		lblRodnslo.setBounds(10, 98, 89, 14);
		panel.add(lblRodnslo);

		textField_2 = new JTextField();
		textField_2.setBounds(109, 95, 148, 20);
		textField_2.setColumns(10);
		panel.add(textField_2);
		
		

	}
	public JTextField getTextField() {
		return textField;
	}
	public JFrame getFrame() {
		return frame;
	}
	public JTextField getTextField_1() {
		return textField_1;
	}
	public JPanel getPanel() {
		return panel;
	}
	public JTextField getTextField_2() {
		return textField_2;
	}
	public JButton getBtnPrida() {
		return btnPrida;
	}
}
