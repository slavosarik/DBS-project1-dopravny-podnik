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
import java.util.Calendar;
import com.toedter.calendar.JDateChooser;

import database.TableManager;

public class PoruchaPridanie {

	private JFrame frame;
	private JTable table;
	private TableManager tableManager;
	private JDateChooser dateChooser_1, dateChooser_2;
	private int id;
	private JTextField textField;

	public PoruchaPridanie(JTable table, TableManager tableManager, int id) {
		this.table = table;
		this.tableManager = tableManager;
		this.id = id;
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 333, 244);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		final JPanel panel = new JPanel();
		panel.setBounds(10, 11, 297, 185);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblVozidlo = new JLabel("Ukon\u010Denie opravy");
		lblVozidlo.setBounds(10, 69, 116, 14);
		panel.add(lblVozidlo);

		JLabel lblLinka = new JLabel("Popis opravy:");
		lblLinka.setBounds(10, 94, 116, 14);
		panel.add(lblLinka);

		JButton btnPrida = new JButton("Prida\u0165");		
		btnPrida.setBounds(10, 150, 89, 23);
		panel.add(btnPrida);

		JLabel lblPridanieNovhoZznamu = new JLabel(
				"Pridanie nov\u00E9ho z\u00E1znamu");
		lblPridanieNovhoZznamu.setFont(new Font("Franklin Gothic Medium",
				Font.PLAIN, 14));
		lblPridanieNovhoZznamu.setBounds(10, 11, 212, 22);
		panel.add(lblPridanieNovhoZznamu);

		JLabel lblZnaka = new JLabel("Za\u010Diatok opravy");
		lblZnaka.setBounds(10, 44, 116, 14);
		panel.add(lblZnaka);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(177, 44, 110, 20);
		panel.add(dateChooser_1);

		dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(177, 69, 110, 20);
		panel.add(dateChooser_2);
		
		textField = new JTextField();
		textField.setBounds(10, 119, 277, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		btnPrida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = null;
				query = "INSERT INTO porucha "
						+ "(id_vozidla, datum_od, datum_do, popis)"
						+ " (SELECT "
						+ id + ", " + "'" + dateChooser_1.getCalendar().get(Calendar.YEAR)+"-"+(dateChooser_1.getCalendar().get(Calendar.MONTH)+1)+"-"+	dateChooser_1.getCalendar().get(Calendar.DATE)
						+ "', " + "'" + dateChooser_2.getCalendar().get(Calendar.YEAR)+"-"+(dateChooser_2.getCalendar().get(Calendar.MONTH)+1)+"-"+	dateChooser_2.getCalendar().get(Calendar.DATE) + "', '"
						+ textField.getText() + "')";

				//System.out.println("Queryyyyyy: " + query);
				try {
					tableManager.insert(table, query);
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
