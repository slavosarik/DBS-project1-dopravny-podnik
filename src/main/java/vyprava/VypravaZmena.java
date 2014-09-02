package vyprava;

import javax.swing.*;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;

public class VypravaZmena {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JDateChooser dateChooser_1;
	private JPanel panel;
	private JButton btnPrida;

	public VypravaZmena() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 242, 237);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 11, 205, 178);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblVodiid = new JLabel("vodi\u010D_id");
		lblVodiid.setBounds(10, 44, 59, 14);
		panel.add(lblVodiid);

		JLabel lblVozidlo = new JLabel("vozidlo");
		lblVozidlo.setBounds(10, 69, 46, 14);
		panel.add(lblVozidlo);

		JLabel lblLinka = new JLabel("linka");
		lblLinka.setBounds(10, 94, 46, 14);
		panel.add(lblLinka);

		JLabel lblDtum = new JLabel("d\u00E1tum");
		lblDtum.setBounds(10, 119, 46, 14);
		panel.add(lblDtum);

		textField = new JTextField();
		textField.setBounds(79, 41, 110, 20);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(79, 66, 110, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(79, 91, 110, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		btnPrida = new JButton("Prida\u0165");
		btnPrida.setBounds(10, 144, 89, 23);
		panel.add(btnPrida);

		JLabel lblPridanieNovhoZznamu = new JLabel("\u00DAprava z\u00E1znamu");
		lblPridanieNovhoZznamu.setFont(new Font("Franklin Gothic Medium",
				Font.PLAIN, 14));
		lblPridanieNovhoZznamu.setBounds(10, 11, 179, 22);
		panel.add(lblPridanieNovhoZznamu);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(79, 119, 110, 20);
		panel.add(dateChooser_1);		

	}
	public JPanel getPanel() {
		return panel;
	}
	public JTextField getTextField_1() {
		return textField_1;
	}
	public JFrame getFrame() {
		return frame;
	}
	public JTextField getTextField() {
		return textField;
	}
	public JTextField getTextField_2() {
		return textField_2;
	}
	public JDateChooser getDateChooser() {
		return dateChooser_1;
	}
	public JButton getBtnPrida() {
		return btnPrida;
	}
}
