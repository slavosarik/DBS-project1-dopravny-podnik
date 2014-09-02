package vozidla;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

public class VozidlaGui {
	
	private JFrame frame;
	private JTable table;
	private JDateChooser dateChooser;

	private JTextField textField;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_1;
	private JButton btnNewButton_1;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	final JComboBox comboBox = new JComboBox(new String[] { "Ascending",
			"Descending" });
	private JButton btnNewButton;
	private JButton btnUpraviZznam;
	private JButton btnNajporuchovejieVozidlo;
	private JButton btnNewButton_4;
	private JButton btnVyhadaPoda;
	private JButton btnNajastejieVypravenVozidl;
	private JButton btnNewButton_2;
	private JButton btnPridadrbu;
	private JButton btnPridaNehodu;
	private JButton btnNewButton_3;

	public VozidlaGui() {
		initialize();
		frame.setVisible(true);
	}

	@SuppressWarnings({ "rawtypes" })
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 510, 549);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnNewButton = new JButton("Prida\u0165 vozidlo");
		btnNewButton.setBounds(10, 268, 129, 23);
		frame.getContentPane().add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 470, 246);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setToolTipText("Pre usporiadaný výber kliknite na header.");
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);

		JLabel lblZoradiPoda = new JLabel("Zoradi\u0165 pod\u013Ea");
		lblZoradiPoda.setBounds(220, 480, 82, 20);
		frame.getContentPane().add(lblZoradiPoda);

		comboBox.setBounds(312, 480, 129, 20);
		comboBox.setSelectedIndex(-1);
		frame.getContentPane().add(comboBox);
		btnVyhadaPoda = new JButton("Vyh\u013Eada\u0165 pod\u013Ea");

		btnVyhadaPoda.setBounds(10, 412, 129, 23);
		frame.getContentPane().add(btnVyhadaPoda);

		comboBox_1 = new JComboBox();
		comboBox_1.setSelectedIndex(-1);
		comboBox_1.setBounds(149, 412, 96, 22);
		frame.getContentPane().add(comboBox_1);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(290, 412, 151, 23);
		dateChooser.setVisible(false);
		frame.getContentPane().add(dateChooser);

		btnNewButton_1 = new JButton("Obnovi\u0165");
		btnNewButton_1.setBounds(10, 479, 129, 23);
		frame.getContentPane().add(btnNewButton_1);

		btnNajastejieVypravenVozidl = new JButton("Najviac vypravene vozidl\u00E1");
		btnNajastejieVypravenVozidl.setBounds(10, 446, 239, 23);
		frame.getContentPane().add(btnNajastejieVypravenVozidl);

		btnUpraviZznam = new JButton("Upravi\u0165 z\u00E1znam");
		btnUpraviZznam.setBounds(10, 302, 129, 23);
		frame.getContentPane().add(btnUpraviZznam);

		btnNewButton_2 = new JButton("Vymaza\u0165 z\u00E1znam");
		btnNewButton_2.setBounds(149, 302, 183, 23);
		frame.getContentPane().add(btnNewButton_2);

		textField = new JTextField();
		textField.setBounds(290, 412, 151, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);

		btnPridadrbu = new JButton("Prida\u0165 \u00FAdr\u017Ebu");
		btnPridadrbu.setBounds(149, 268, 183, 23);
		frame.getContentPane().add(btnPridadrbu);

		btnPridaNehodu = new JButton("Prida\u0165 nehodu");
		btnPridaNehodu.setBounds(344, 268, 136, 23);
		frame.getContentPane().add(btnPridaNehodu);

		btnNewButton_3 = new JButton("Zobrazi\u0165 podrobnosti");
		btnNewButton_3.setBounds(10, 378, 235, 23);
		frame.getContentPane().add(btnNewButton_3);

		btnNajporuchovejieVozidlo = new JButton(
				"Najporuchovej\u0161ie vozidl\u00E1");

		btnNajporuchovejieVozidlo.setBounds(10, 336, 174, 23);
		frame.getContentPane().add(btnNajporuchovejieVozidlo);

		btnNewButton_4 = new JButton(
				"Ktor\u00FD model m\u00E1 najvy\u0161\u0161\u00ED priemern\u00FD vek");

		btnNewButton_4.setBounds(259, 336, 221, 23);
		frame.getContentPane().add(btnNewButton_4);

	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtnUpraviZznam() {
		return btnUpraviZznam;
	}

	public JButton getBtnNajporuchovejieVozidlo() {
		return btnNajporuchovejieVozidlo;
	}

	public JButton getBtnNewButton_4() {
		return btnNewButton_4;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBox() {
		return comboBox;
	}

	public JButton getBtnVyhadaPoda() {
		return btnVyhadaPoda;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBox_1() {
		return comboBox_1;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}

	public JButton getBtnNajastejieVypravenVozidl() {
		return btnNajastejieVypravenVozidl;
	}

	public JButton getBtnNewButton_2() {
		return btnNewButton_2;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JButton getBtnPridadrbu() {
		return btnPridadrbu;
	}

	public JButton getBtnPridaNehodu() {
		return btnPridaNehodu;
	}

	public JButton getBtnNewButton_3() {
		return btnNewButton_3;
	}
}
