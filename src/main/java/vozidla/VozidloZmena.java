package vozidla;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

import database.TableManager;

public class VozidloZmena {

	private JFrame frame;
	private JTable table;
	private TableManager tableManager;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JYearChooser yearChooser;
	private int id;

	public VozidloZmena(JTable table, TableManager tableManager, int id) {
		this.table = table;
		this.tableManager = tableManager;
		this.id = id;
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 265, 269);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		final JPanel panel = new JPanel();
		panel.setBounds(10, 11, 228, 210);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnPrida = new JButton("Potvrdi\u0165 zmeny");		
		btnPrida.setBounds(10, 171, 201, 23);
		panel.add(btnPrida);

		JLabel lblPridanieNovhoZznamu = new JLabel("Zmena parametrov vozidla");
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
		
		btnPrida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int year = yearChooser.getYear();
				String query = "UPDATE vozidla SET vozidlo_cislo = "
						+ textField.getText()
						+ ", id_modely = (SELECT id_model FROM model WHERE model_name = '"
						+ textField_1.getText() + "'), vozovna = '"
						+ textField_2.getText() + "', rok_zaradenia = " + year
						+ " WHERE id_vozidlo = " + id;

				//System.out.println("Queryyyyyy: " + query);
				try {
					tableManager.updateItem(table, query);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				query = "SELECT id_vozidlo, vozidlo_cislo, znacka_name, model_name, vozovna  FROM vozidlo v JOIN model m ON v.id_modely = m.id_model JOIN znacka z ON z.id_znacka = m.id_znacky ORDER BY id_vozidlo";
				//System.out.println("Queryaaa: " + query);
				try {
					tableManager.update(table, query);
					table.setRowSelectionAllowed(true);
					table.setColumnSelectionAllowed(false);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(panel, "Zaznam bol pridany.", "",
						JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();

			}
		});

	}
}
