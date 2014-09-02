package vyprava;

import javax.swing.*;
import java.sql.SQLException;
import com.toedter.calendar.JDateChooser;

public class VypravaGui {

	private JFrame frame;
	private JTable table;
	private JDateChooser dateChooser;
	private JTextField textField;
	final JComboBox<String> comboBox = new JComboBox<String>(new String[] { "Ascending",
			"Descending" });
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_1;
	private JButton btnNewButton_1;
	private JTextField textField_1;
	private JButton btnNewButton;
	private JButton btnNewButton_3;
	private JButton btnVyhadaPoda;
	private JButton btnNewButton_2;
	private JButton btnUpraviZznam;
	private JButton btnNajastejieVypravenVozidl;
	private JDateChooser dateChooser_1;
	private JButton btnVpravaVObdob;
	private JDateChooser dateChooser_2;

	public VypravaGui() throws SQLException {	
		initialize();
		frame.setVisible(true);
	}

	@SuppressWarnings({ "rawtypes" })
	private void initialize() throws SQLException {

		frame = new JFrame();
		frame.setBounds(100, 100, 496, 591);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnNewButton = new JButton("Prida\u0165 v\u00FDpravu");
		btnNewButton.setBounds(10, 268, 163, 23);
		frame.getContentPane().add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 456, 246);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setToolTipText("Pre usporiadaný výber kliknite na header.");
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);

		JLabel lblZoradiPoda = new JLabel("Zoradi\u0165 pod\u013Ea");
		lblZoradiPoda.setBounds(185, 520, 82, 20);
		frame.getContentPane().add(lblZoradiPoda);

		comboBox.setBounds(277, 520, 129, 20);
		comboBox.setSelectedIndex(-1);
		frame.getContentPane().add(comboBox);

		btnVyhadaPoda = new JButton("Vyh\u013Eada\u0165 pod\u013Ea");
		btnVyhadaPoda.setBounds(10, 370, 129, 23);
		frame.getContentPane().add(btnVyhadaPoda);

		comboBox_1 = new JComboBox();
		comboBox_1.setSelectedIndex(-1);
		comboBox_1.setBounds(149, 370, 147, 22);
		frame.getContentPane().add(comboBox_1);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(315, 370, 151, 23);
		dateChooser.setVisible(false);
		frame.getContentPane().add(dateChooser);

		btnNewButton_1 = new JButton("Obnovi\u0165");
		btnNewButton_1.setBounds(10, 519, 129, 23);
		frame.getContentPane().add(btnNewButton_1);

		btnNajastejieVypravenVozidl = new JButton(
				"Naj\u010Dastej\u0161ie vypraven\u00E9 vozidl\u00E1");
		btnNajastejieVypravenVozidl.setBounds(10, 404, 286, 23);
		frame.getContentPane().add(btnNajastejieVypravenVozidl);

		btnUpraviZznam = new JButton("Upravi\u0165 z\u00E1znam");
		btnUpraviZznam.setBounds(10, 302, 163, 23);
		frame.getContentPane().add(btnUpraviZznam);

		btnNewButton_2 = new JButton("Vymaza\u0165 z\u00E1znam");
		btnNewButton_2.setBounds(10, 336, 163, 23);
		frame.getContentPane().add(btnNewButton_2);

		textField = new JTextField();
		textField.setBounds(315, 370, 151, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(370, 437, 96, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		btnNewButton_3 = new JButton(
				"Naj\u010Dastej\u0161ie vypraven\u00E9 zna\u010Dky na linke:");		
		btnNewButton_3.setBounds(10, 438, 286, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		btnVpravaVObdob = new JButton("V\u00FDprava v obdob\u00ED");
		btnVpravaVObdob.setBounds(10, 472, 151, 23);
		frame.getContentPane().add(btnVpravaVObdob);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(200, 475, 96, 20);
		frame.getContentPane().add(dateChooser_1);
		
		dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(370, 475, 96, 20);
		frame.getContentPane().add(dateChooser_2);
		
		JLabel lblOd = new JLabel("od");
		lblOd.setBounds(166, 476, 46, 14);
		frame.getContentPane().add(lblOd);
		
		JLabel lblDo = new JLabel("do");
		lblDo.setBounds(312, 476, 46, 14);
		frame.getContentPane().add(lblDo);
		textField.setVisible(false);
		
		
	}
	public JTextField getTextField() {
		return textField;
	}
	public JButton getBtnNewButton() {
		return btnNewButton;
	}
	public JButton getBtnNewButton_3() {
		return btnNewButton_3;
	}
	public JDateChooser getDateChooser() {
		return dateChooser;
	}
	public JButton getBtnVyhadaPoda() {
		return btnVyhadaPoda;
	}
	public JButton getBtnNewButton_2() {
		return btnNewButton_2;
	}
	public JButton getBtnUpraviZznam() {
		return btnUpraviZznam;
	}
	@SuppressWarnings("rawtypes")
	public JComboBox getComboBox_1() {
		return comboBox_1;
	}
	public JTextField getTextField_1() {
		return textField_1;
	}
	@SuppressWarnings("rawtypes")
	public JComboBox getComboBox() {
		return comboBox;
	}
	public JButton getBtnNajastejieVypravenVozidl() {
		return btnNajastejieVypravenVozidl;
	}
	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}
	public JFrame getFrame() {
		return frame;
	}
	public JTable getTable() {
		return table;
	}
	public JDateChooser getDateChooser_1() {
		return dateChooser_1;
	}
	public JButton getBtnVpravaVObdob() {
		return btnVpravaVObdob;
	}
	public JDateChooser getDateChooser_2() {
		return dateChooser_2;
	}
}
