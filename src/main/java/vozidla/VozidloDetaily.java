package vozidla;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JTextField;

import database.TableManager;

public class VozidloDetaily {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTextField textField;
	private JTextField textField_1;
	private TableManager tableManager;
	private int id;

	public VozidloDetaily(TableManager tm, int id) {
		this.id = id;
		this.tableManager = tm;
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 395, 564);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 359, 504);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 339, 76);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 162, 189, 95);
		panel.add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 342, 339, 114);
		panel.add(scrollPane_2);

		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);

		JLabel lblPodrobnInformcie = new JLabel(
				"Podrobn\u00E9 inform\u00E1cie:");
		lblPodrobnInformcie.setFont(new Font("Franklin Gothic Medium",
				Font.PLAIN, 15));
		lblPodrobnInformcie.setBounds(10, 11, 301, 26);
		panel.add(lblPodrobnInformcie);

		JLabel lblZznamyONehodch = new JLabel("Z\u00E1znamy o nehod\u00E1ch:");
		lblZznamyONehodch.setFont(new Font("Franklin Gothic Medium",
				Font.PLAIN, 15));
		lblZznamyONehodch.setBounds(10, 125, 148, 26);
		panel.add(lblZznamyONehodch);

		JLabel lblZznamyOOpravch = new JLabel("Z\u00E1znamy o oprav\u00E1ch:");
		lblZznamyOOpravch.setFont(new Font("Franklin Gothic Medium",
				Font.PLAIN, 15));
		lblZznamyOOpravch.setBounds(10, 305, 148, 26);
		panel.add(lblZznamyOOpravch);

		JLabel lblCelkovSpsobenkody = new JLabel(
				"Celkov\u00E1 v\u00FD\u0161ka \u0161k\u00F4d:");
		lblCelkovSpsobenkody.setBounds(10, 268, 222, 26);
		panel.add(lblCelkovSpsobenkody);

		JLabel lblCelkovPoetDn = new JLabel(
				"Celkov\u00FD po\u010Det dn\u00ED  odstavenia vozidla");
		lblCelkovPoetDn.setBounds(10, 467, 222, 26);
		panel.add(lblCelkovPoetDn);

		textField = new JTextField();
		textField.setBounds(242, 271, 107, 20);
		panel.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);

		textField_1 = new JTextField();
		textField_1.setBounds(242, 470, 95, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEditable(false);
		
		String queryValue;
		String query1 = "SELECT vozidlo_cislo, znacka_name, model_name, vozovna, rok_zaradenia, farba, kapacita, pocet_dveri, hmotnost, motor_objem, motor_vykon, krajina FROM vozidlo v JOIN model m ON m.id_model = v.id_modely JOIN znacka z ON z.id_znacka = m.id_znacky WHERE id_vozidlo = ";
		String query2 = "SELECT datum_nehody, skoda FROM nehoda WHERE id_vozidla = ";
		String query3 = "SELECT datum_od, datum_do, (DATEDIFF(datum_do, datum_od)) AS dlzka_opravy ,popis FROM porucha WHERE id_vozidla = ";
		
		try {
			tableManager.update(table, query1 + id);
			queryValue = "SELECT sum(skoda) AS sum FROM nehoda WHERE id_vozidla = ";
			
			tableManager.update(table_1, query2 + id);
			textField.setText(Integer.toString(tableManager.getValue(queryValue + id)));
			queryValue = "SELECT sum(DATEDIFF(datum_do, datum_od)) AS sum FROM porucha WHERE id_vozidla = ";
			
			tableManager.update(table_2, query3 + id);
			textField_1.setText(Integer.toString(tableManager.getValue(queryValue + id)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
