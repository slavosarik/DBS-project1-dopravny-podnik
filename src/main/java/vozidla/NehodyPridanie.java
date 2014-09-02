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

public class NehodyPridanie {

	private JFrame frame;
	private JTextField textField_2;
	private JTable table;
	private TableManager tableManager;
	private JDateChooser dateChooser_1;
	private int id;

	
	public NehodyPridanie(JTable table, TableManager tableManager, int id) {
		this.table = table;
		this.tableManager = tableManager;
		this.id = id;
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 288, 202);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		final JPanel panel = new JPanel();
		panel.setBounds(10, 11, 251, 142);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblVozidlo = new JLabel("Sposoben\u00E1 \u0161koda");
		lblVozidlo.setBounds(10, 81, 102, 14);
		panel.add(lblVozidlo);

		textField_2 = new JTextField();
		textField_2.setBounds(122, 78, 110, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JButton btnPrida = new JButton("Prida\u0165");		
		btnPrida.setBounds(10, 106, 89, 23);
		panel.add(btnPrida);

		JLabel lblPridanieNovhoZznamu = new JLabel(
				"Pridanie nov\u00E9ho z\u00E1znamu o nehode");
		lblPridanieNovhoZznamu.setFont(new Font("Franklin Gothic Medium",
				Font.PLAIN, 14));
		lblPridanieNovhoZznamu.setBounds(10, 11, 222, 22);
		panel.add(lblPridanieNovhoZznamu);

		JLabel lblZnaka = new JLabel("D\u00E1tum nehody");
		lblZnaka.setBounds(10, 44, 89, 14);
		panel.add(lblZnaka);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(122, 44, 110, 20);
		panel.add(dateChooser_1);
		
		btnPrida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query  = "INSERT INTO nehoda "
						+ "(id_vozidla, datum_nehody, skoda) VALUES" + " ("
						+ id + ",'"
						+ dateChooser_1.getCalendar().get(Calendar.YEAR) + "-"
						+ (dateChooser_1.getCalendar().get(Calendar.MONTH) + 1)
						+ "-" + dateChooser_1.getCalendar().get(Calendar.DATE)
						+ "', "+textField_2.getText()+")";

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
