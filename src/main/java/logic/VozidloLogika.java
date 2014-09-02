package logic;

import java.awt.event.*;
import javax.swing.*;

import map_objects.Model;
import map_objects.Vozidlo;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.type.IntegerType;
import org.hibernate.type.Type;

import database.TableManager;
import gui.*;
import vozidla.*;
import java.sql.*;
import java.util.Calendar;

public class VozidloLogika {

	private VozidlaGui vg = null;
	private VozidloPridanie vp = null;
	private PridanieModelu pm = null;
	private PridanieZnacky pz = null;
	private JTable table = null;
	private String lastUsedString = null;
	private TableManager tableManager;
	final String query = "SELECT id_vozidlo, vozidlo_cislo, znacka_name, model_name, vozovna  FROM vozidlo v JOIN model m ON v.id_modely = m.id_model JOIN znacka z ON z.id_znacka = m.id_znacky";

	public VozidloLogika() throws SQLException {
		tableManager = TableManager.getSingletonObject();
		vg = new VozidlaGui();
		table = vg.getTable();
		tableManager.update(table, query);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		registerButtonsVozidla();
		vg.getComboBox_1().setModel(
				new DefaultComboBoxModel(tableManager.columnNames));
	}

	public void registerButtonsPridanieVozidla(final VozidloPridanie vp) {
		// Tlacidlo na pridanie vozidla
		vp.getBtnPrida().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int year = vp.getYearChooser().getYear();
				String query = "INSERT INTO vozidlo (vozidlo_cislo, id_modely, vozovna, rok_zaradenia) "
						+ "(SELECT "
						+ vp.getTextField().getText()
						+ ", "
						+ "(SELECT id_model FROM model WHERE model_name = '"
						+ vp.getTextField_1().getText()
						+ "'), "
						+ "'"
						+ vp.getTextField_2().getText() + "', " + +year + ")";

				System.out.println("Queryyyyyy: " + query);
				try {
					tableManager.insert(table, query);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				query = "SELECT id_vozidlo, vozidlo_cislo, znacka_name, model_name, vozovna  FROM vozidlo v JOIN model m ON v.id_modely = m.id_model JOIN znacka z ON z.id_znacka = m.id_znacky ORDER BY id_vozidlo";
				// System.out.println("Queryaaa: " + query);
				try {
					tableManager.update(table, query);
					table.setRowSelectionAllowed(true);
					table.setColumnSelectionAllowed(false);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				JOptionPane.showMessageDialog(vp.getPanel(),
						"Zaznam bol pridany.", "",
						JOptionPane.INFORMATION_MESSAGE);
				vp.getFrame().dispose();
			}
		});

		vp.getBtnZaevidovanieNovhoModelu().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pm = new PridanieModelu(table, tableManager);
						registerButtonsPridanieModelu(pm);
					}
				});
	}

	public void registerButtonsPridanieModelu(final PridanieModelu pm) {
		pm.getBtnPrida().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = null;
				query = "INSERT INTO model "
						+ "(id_znacky, model_name, farba, motor_objem, motor_vykon, pocet_dveri, hmotnost, kapacita)"
						+ " (SELECT "
						+ "(SELECT id_znacka FROM znacka WHERE znacka_name = '"
						+ pm.getTextField_1().getText() + "'), " + "'"
						+ pm.getTextField().getText() + "', " + "'"
						+ pm.getTextField_2().getText() + "', "
						+ pm.getTextField_3().getText() + ", "
						+ pm.getTextField_4().getText() + ", "
						+ pm.getTextField_5().getText() + ", "
						+ pm.getTextField_6().getText() + ", "
						+ pm.getTextField_7().getText() + ")";

				// System.out.println("Queryyyyyy: " + query);
				try {
					tableManager.insert(table, query);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				JOptionPane.showMessageDialog(pm.getPanel(),
						"Zaznam bol pridany.", "",
						JOptionPane.INFORMATION_MESSAGE);
				pm.getFrame().dispose();

			}
		});

		pm.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pz = new PridanieZnacky(table, tableManager);
				registerButtonsPridanieZnacky(pz);
			}
		});

	}

	public void registerButtonsPridanieZnacky(final PridanieZnacky pz) {
		pz.getBtnPrida().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "INSERT INTO znacka (znacka_name, krajina) VALUES "
						+ "('"
						+ pz.getTextField().getText()
						+ "', '"
						+ pz.getTextField_1().getText() + "')";

				// System.out.println("Queryyyyyy: " + query);
				try {
					tableManager.insert(table, query);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				JOptionPane.showMessageDialog(pz.getPanel(),
						"Zaznam bol pridany.", "",
						JOptionPane.INFORMATION_MESSAGE);
				pz.getFrame().dispose();
			}
		});
	}

	public void registerButtonsVozidla() {
		// Tlacidlo na spustenie okna na pridavanie vozidiel
		vg.getBtnNewButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vp = new VozidloPridanie();
				registerButtonsPridanieVozidla(vp);
			}
		});

		// Tlacidlo na otvorenia okna na zmenu parametrov vozidla
		vg.getBtnUpraviZznam().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VozidloZmena(table, tableManager, ((Number) table
						.getValueAt(vg.getTable().getSelectedRow(), 0))
						.intValue());
				table.clearSelection();
				table.setRowSelectionAllowed(true);
				table.setColumnSelectionAllowed(false);
			}
		});

		vg.getBtnNajporuchovejieVozidlo().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/*String q = " SELECT vozidlo_cislo, znacka_name, model_name, YEAR(now()) -rok_zaradenia AS vek, " +
								" COALESCE(SUM(DATEDIFF(datum_do,datum_od)),0) odstavka FROM vozidlo v " +
								" LEFT JOIN porucha p ON p.id_vozidla = v.id_vozidlo " +
								" JOIN model m ON m.id_model = v.id_modely " +
								" JOIN znacka z ON z.id_znacka = m.id_znacky " +
								" GROUP BY vozidlo_cislo " +
								" ORDER BY odstavka DESC ";
						try {
							tableManager.update(table, q);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}*/
						tableManager.vozidlaNajdlhsieOdstavene(table);
						
					}
				});

		vg.getBtnNewButton_4().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*String q = " SELECT model_name, ROUND(AVG(YEAR(now()) - rok_zaradenia)) AS vek FROM model m 
				 * JOIN vozidlo v ON m.id_model = v.id_modely 
				 * GROUP BY model_name 
				 * ORDER BY vek DESC";
				try {
					tableManager.update(table, q);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				*/
				tableManager.vozidlaPriemernyVek(table);
				/*Criteria c = session.createCriteria(Model.class, "model")
				.createAlias("model.vozidla", "vozidlo")
				.addOrder(Order.desc("vek"))
				.setProjection(Projections.projectionList()
						.add(Projections.property("model_name"), "model_name")				
						.add(Projections.sqlProjection("ROUND(AVG(YEAR(now()) - rok_zaradenia)) AS vek",
						new String[]{"vek"}, new Type[]{new IntegerType()}), "vek")
						.add(Projections.groupProperty("model_name")));*/
			}
		});

		table.getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// System.out.println("Column index selected " + col + " " +
				// name);
				lastUsedString = lastUsedString
						+ " ORDER BY "
						+ table.getColumnName(table.columnAtPoint(e.getPoint()));
				try {
					// System.out.println(lastUsedString);
					tableManager.update(table, lastUsedString);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (lastUsedString != null
						&& vg.getComboBox().getSelectedIndex() != -1) {
					if (vg.getComboBox().getSelectedIndex() == 0)
						try {
							// System.out.println(lastUsedString + " ASC");
							tableManager.update(table, lastUsedString + " ASC");
						} catch (SQLException e) {
							e.printStackTrace();
						}
					if (vg.getComboBox().getSelectedIndex() == 1)
						try {
							// System.out.println(lastUsedString + " DESC");
							tableManager
									.update(table, lastUsedString + " DESC");
						} catch (SQLException e) {
							e.printStackTrace();
						}
					// System.out.println(comboBox.getSelectedIndex());
				}
			}
		};
		vg.getComboBox().addActionListener(actionListener);

		vg.getBtnVyhadaPoda().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String qr1 = null;
				// System.out.println(comboBox_1.getSelectedItem() +
				// " "+
				// textField.getText());
				if (vg.getComboBox_1().getSelectedIndex() != -1)
					if ("datum_vyprava".equals(vg.getComboBox_1()
							.getSelectedItem())) {
						qr1 = " WHERE "
								+ vg.getComboBox_1().getSelectedItem()
								+ " = '"
								+ vg.getDateChooser().getCalendar()
										.get(Calendar.YEAR)
								+ "-"
								+ (vg.getDateChooser().getCalendar()
										.get(Calendar.MONTH) + 1)
								+ "-"
								+ vg.getDateChooser().getCalendar()
										.get(Calendar.DATE) + "'";
						try {
							lastUsedString = query + qr1;
							// System.out.println(lastUsedString);
							tableManager.update(table, lastUsedString);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						vg.getDateChooser().setDate(null);
						vg.getDateChooser().setVisible(false);
					} else {
						try {
							qr1 = " WHERE "
									+ vg.getComboBox_1().getSelectedItem()
									+ " = '" + vg.getTextField().getText()
									+ "'";
							lastUsedString = query + qr1;
							// System.out.println(lastUsedString);
							tableManager.update(table, lastUsedString);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						vg.getTextField().setText("");
						vg.getTextField().setVisible(false);
					}
				vg.getComboBox_1().setSelectedIndex(-1);
			}
		});

		vg.getBtnNewButton_1().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastUsedString = query;
				// System.out.println(lastUsedString);
				try {
					tableManager.update(table, lastUsedString);
				} catch (SQLException e1) {
					e1.printStackTrace();
					table.setRowSelectionAllowed(true);
					table.setColumnSelectionAllowed(false);
				}
			}
		});

		vg.getBtnNajastejieVypravenVozidl().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							lastUsedString = "SELECT b.vozidlo_cislo, count(*) FROM vyprava v JOIN vozidlo b ON b.id_vozidlo = v.id_vozidla GROUP BY b.vozidlo_cislo ORDER BY count(*) ";
							// System.out.println(lastUsedString + "DESC");
							tableManager
									.update(table, lastUsedString + " DESC");
						} catch (SQLException e) {
							e.printStackTrace();
						}
						vg.getComboBox_1().setSelectedIndex(-1);
						vg.getTextField().setVisible(false);
						vg.getDateChooser().setVisible(false);
					}
				});

		vg.getBtnNewButton_2().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String delQuery = "DELETE FROM vozidlo WHERE id_vozidlo = "
						+ ((Number) table.getValueAt(table.getSelectedRow(), 0))
								.intValue();
				try {
					System.out.println(delQuery);
					tableManager.updateItem(table, delQuery);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				table.clearSelection();

				lastUsedString = query;
				try {
					tableManager.update(table, lastUsedString);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				table.setRowSelectionAllowed(true);
				table.setColumnSelectionAllowed(false);
				JOptionPane.showMessageDialog(vg.getFrame().getContentPane(),
						"Zaznam bol vymazany.", "",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		
		ActionListener actionListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if ("datum_vyprava"
						.equals(vg.getComboBox_1().getSelectedItem())) {
					vg.getDateChooser().setVisible(true);
				} else
					vg.getTextField().setVisible(true);
			}
		};
		vg.getComboBox_1().addActionListener(actionListener1);

		vg.getBtnPridadrbu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PoruchaPridanie(table, tableManager, ((Number) table
						.getValueAt(table.getSelectedRow(), 0)).intValue());
				table.clearSelection();
				table.setRowSelectionAllowed(true);
				table.setColumnSelectionAllowed(false);
			}
		});

		vg.getBtnPridaNehodu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NehodyPridanie(table, tableManager, ((Number) table
						.getValueAt(table.getSelectedRow(), 0)).intValue());
				table.clearSelection();
				table.setRowSelectionAllowed(true);
				table.setColumnSelectionAllowed(false);
			}
		});

		vg.getBtnNewButton_3().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VozidloDetaily(tableManager, ((Number) table.getValueAt(
						table.getSelectedRow(), 0)).intValue());
				table.clearSelection();
				table.setRowSelectionAllowed(true);
				table.setColumnSelectionAllowed(false);

			}
		});
	}
}
