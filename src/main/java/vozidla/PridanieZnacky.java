package vozidla;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import database.TableManager;

public class PridanieZnacky {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnPrida;
	private JPanel panel;

	/**
	 * Create the application.
	 */
	public PridanieZnacky(JTable table, TableManager tableManager) {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 242, 192);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 11, 205, 134);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblVodiid = new JLabel("N\u00E1zov");
		lblVodiid.setBounds(10, 44, 59, 14);
		panel.add(lblVodiid);

		JLabel lblVozidlo = new JLabel("Krajina");
		lblVozidlo.setBounds(10, 69, 46, 14);
		panel.add(lblVozidlo);

		textField = new JTextField();
		textField.setBounds(79, 41, 110, 20);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(79, 66, 110, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		btnPrida = new JButton("Prida\u0165");
		btnPrida.setBounds(10, 97, 89, 23);
		panel.add(btnPrida);

		JLabel lblPridanieNovhoZznamu = new JLabel(
				"Pridanie nov\u00E9ho z\u00E1znamu");
		lblPridanieNovhoZznamu.setFont(new Font("Franklin Gothic Medium",
				Font.PLAIN, 14));
		lblPridanieNovhoZznamu.setBounds(10, 11, 212, 22);
		panel.add(lblPridanieNovhoZznamu);

	}

	public JButton getBtnPrida() {
		return btnPrida;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JPanel getPanel() {
		return panel;
	}
}
