package vozidla;

import javax.swing.*;
import java.awt.Font;
import com.toedter.calendar.JYearChooser;

public class VozidloPridanie {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JYearChooser yearChooser;
	private JButton btnPrida;
	private JButton btnZaevidovanieNovhoModelu;
	private JPanel panel;

	public VozidloPridanie() {
		
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 265, 304);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 11, 228, 242);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		btnPrida = new JButton("Prida\u0165");		
		btnPrida.setBounds(10, 171, 201, 23);
		panel.add(btnPrida);

		JLabel lblPridanieNovhoZznamu = new JLabel(
				"Pridanie nov\u00E9ho vozidla");
		lblPridanieNovhoZznamu.setFont(new Font("Franklin Gothic Medium",
				Font.PLAIN, 14));
		lblPridanieNovhoZznamu.setBounds(10, 11, 212, 22);
		panel.add(lblPridanieNovhoZznamu);
		
		JLabel lblsloVozidla = new JLabel("\u010C\u00EDslo vozidla");
		lblsloVozidla.setBounds(10, 50, 89, 14);
		panel.add(lblsloVozidla);
		
		textField = new JTextField();
		textField.setBounds(125, 44, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Model");
		lblNewLabel.setBounds(10, 78, 89, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Domovsk\u00E1 vozov\u0148a");
		lblNewLabel_1.setBounds(10, 109, 127, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblRokEvidencie = new JLabel("Rok evidencie");
		lblRokEvidencie.setBounds(10, 146, 89, 14);
		panel.add(lblRokEvidencie);
		
		textField_1 = new JTextField();
		textField_1.setBounds(125, 75, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(125, 106, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		yearChooser = new JYearChooser();
		yearChooser.setBounds(125, 137, 86, 23);
		panel.add(yearChooser);
		
		btnZaevidovanieNovhoModelu = new JButton("Zaevidovanie nov\u00E9ho modelu");
		btnZaevidovanieNovhoModelu.setBounds(10, 205, 201, 23);
		panel.add(btnZaevidovanieNovhoModelu);
				
	}
		
	public JButton getBtnPrida() {
		return btnPrida;
	}
	public JButton getBtnZaevidovanieNovhoModelu() {
		return btnZaevidovanieNovhoModelu;
	}
	public JYearChooser getYearChooser() {
		return yearChooser;
	}
	public JTextField getTextField() {
		return textField;
	}
	public JTextField getTextField_1() {
		return textField_1;
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
}
