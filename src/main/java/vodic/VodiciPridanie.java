package vodic;

import javax.swing.*;
import java.awt.Font;

public class VodiciPridanie {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnPrida;
	private JPanel panel;

	public VodiciPridanie() {		
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
		textField.setColumns(10);
		panel.add(textField);

		textField_1 = new JTextField();
		textField_1.setBounds(109, 66, 148, 20);
		textField_1.setColumns(10);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setBounds(109, 97, 148, 20);
		textField_2.setColumns(10);
		panel.add(textField_2);

		JLabel lblPridanieNovhoZznamu = new JLabel(
				"Pridanie nov\u00E9ho zamestnanca");
		lblPridanieNovhoZznamu.setFont(new Font("Franklin Gothic Medium",
				Font.PLAIN, 14));
		lblPridanieNovhoZznamu.setBounds(10, 11, 212, 22);
		panel.add(lblPridanieNovhoZznamu);

		JLabel lblRodnslo = new JLabel("Rodn\u00E9 \u010D\u00EDslo");
		lblRodnslo.setBounds(10, 94, 89, 14);
		panel.add(lblRodnslo);

		btnPrida = new JButton("Prida\u0165");		
		panel.add(btnPrida);
		
		btnPrida.setBounds(10, 123, 89, 23);

	}
	public JButton getBtnPrida() {
		return btnPrida;
	}
	public JTextField getTextField() {
		return textField;
	}
	public JTextField getTextField_2() {
		return textField_2;
	}
	public JTextField getTextField_1() {
		return textField_1;
	}
	public JPanel getPanel() {
		return panel;
	}
	public JFrame getFrame() {
		return frame;
	}
}
