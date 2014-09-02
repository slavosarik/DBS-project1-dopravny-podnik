package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import vyprava.VypravaGui;
import vyprava.VypravaPridanie;
import vyprava.VypravaZmena;
import database.TableManager;

public class VypravaLogika {

	private VypravaGui vg = null;
	private VypravaPridanie vp = null;
	private VypravaZmena vz = null;
	private JTable table = null;
	final String query = "SELECT id_vyprava, cislo_linka, vozidlo_cislo, datum_vyprava, id_vodici FROM vyprava v JOIN linka l ON l.id_linka = v.id_linky JOIN vozidlo b ON b.id_vozidlo = v.id_vozidla";
	private String lastUsedString = query;
	private TableManager tableManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VypravaLogika() throws SQLException {
		tableManager = TableManager.getSingletonObject();
		vg = new VypravaGui();
		table = vg.getTable();
		tableManager.update(table, query);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		registerButtonsVyprava();
		vg.getComboBox_1().setModel(
				new DefaultComboBoxModel(tableManager.columnNames));
	}

	private void registerButtonsVypravaZmena(final VypravaZmena vz) {
		vz.getBtnPrida().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "UPDATE vyprava SET "
						+ "id_vozidla = (SELECT id_vozidlo FROM vozidlo WHERE vozidlo_cislo = "
						+ Integer.parseInt(vz.getTextField_1().getText())
						+ "), "
						+ "id_vodici = "
						+ Integer.parseInt(vz.getTextField().getText())
						+ ",  "
						+ "id_linky = (SELECT id_linka FROM linka WHERE cislo_linka = "
						+ Integer.parseInt(vz.getTextField_2().getText())
						+ "), "
						+ "datum_vyprava = '"
						+ vz.getDateChooser().getCalendar().get(Calendar.YEAR)
						+ "-"
						+ (vz.getDateChooser().getCalendar()
								.get(Calendar.MONTH) + 1)
						+ "-"
						+ vz.getDateChooser().getCalendar().get(Calendar.DATE)
						+ "' "
						+ "WHERE id_vyprava = "
						+ ((Number) table.getValueAt(table.getSelectedRow(), 0))
								.intValue();

				// System.out.println("Queryyyyyy: " + query);
				try {
					tableManager.updateItem(table, query);
					query = "SELECT id_vyprava, cislo_linka, vozidlo_cislo, datum_vyprava, id_vodici FROM vyprava v JOIN linka l ON l.id_linka = v.id_linky "
							+ "JOIN vozidlo b ON b.id_vozidlo = v.id_vozidla";
					tableManager.update(table, query);
				} catch (SQLException e) {
				}
				table.clearSelection();
				JOptionPane.showMessageDialog(vz.getPanel(),
						"Zaznam bol upraveny.", "",
						JOptionPane.INFORMATION_MESSAGE);
				vz.getFrame().dispose();

			}
		});
	}

	private void registerButtonsVypravaPridanie(final VypravaPridanie vp) {
		vp.getBtnPrida().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "INSERT INTO vyprava (id_vozidla, id_vodici, id_linky, datum_vyprava)"
						+ "(SELECT "
						+ "(SELECT id_vozidlo FROM vozidlo WHERE vozidlo_cislo = "
						+ Integer.parseInt(vp.getTextField_1().getText())
						+ "), "
						+ Integer.parseInt(vp.getTextField().getText())
						+ ","
						+ " (SELECT id_linka FROM linka WHERE cislo_linka = "
						+ Integer.parseInt(vp.getTextField_2().getText())
						+ "), '"
						+ vp.getDateChooser().getCalendar().get(Calendar.YEAR)
						+ "-"
						+ (vp.getDateChooser().getCalendar()
								.get(Calendar.MONTH) + 1)
						+ "-"
						+ vp.getDateChooser().getCalendar().get(Calendar.DATE)
						+ "')";
				System.out.println("Queryyyyyy: " + query);
				try {
					tableManager.insert(table, query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				query = "SELECT cislo_linka, vozidlo_cislo, datum_vyprava, id_vodici FROM vyprava v JOIN linka l ON l.id_linka = v.id_linky "
						+ "JOIN vozidlo b ON b.id_vozidlo = v.id_vozidla";
				// System.out.println("Queryaaa: " + query);
				try {
					tableManager.update(table, query);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				JOptionPane.showMessageDialog(vp.getPanel(),
						"Zaznam bol pridany.", "",
						JOptionPane.INFORMATION_MESSAGE);
				vp.getFrame().dispose();

			}
		});
	}

	private void registerButtonsVyprava() {
		vg.getBtnNewButton_3().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * String q = "SELECT znacka_name, model_name FROM vyprava v" +
				 * " JOIN linka l ON l.id_linka = v.id_linky" +
				 * " JOIN vozidlo b ON b.id_vozidlo = v.id_vozidla" +
				 * " JOIN model m ON m.id_model = b.id_modely" +
				 * " JOIN znacka z ON z.id_znacka = m.id_znacky" +
				 * " WHERE cislo_linka = " + vg.getTextField_1().getText() +
				 * " GROUP BY (model_name)" + " ORDER BY count(*) DESC" +
				 * " LIMIT 1"; try { tableManager.update(table, q); } catch
				 * (SQLException e) { e.printStackTrace(); }
				 */
				tableManager.vypravaNajcastejsieVypravovanyModel(table, vg
						.getTextField_1().getText());
			}
		});

		vg.getBtnNajastejieVypravenVozidl().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							lastUsedString = "SELECT b.vozidlo_cislo, z.znacka_name, m.model_name, count(*) FROM vyprava v "
									+ " JOIN vozidlo b ON b.id_vozidlo = v.id_vozidla "
									+ " JOIN model m ON m.id_model = b.id_modely JOIN znacka z ON z.id_znacka = m.id_znacky "
									+ " GROUP BY b.vozidlo_cislo "
									+ " ORDER BY count(*) ";
							System.out.println(lastUsedString + "DESC");
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

		vg.getBtnNewButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vp = new VypravaPridanie();
				registerButtonsVypravaPridanie(vp);
			}
		});

		table.getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = table.columnAtPoint(e.getPoint());
				String name = table.getColumnName(col);
				// System.out.println("Column index selected " + col + " " +
				// name);
				lastUsedString = lastUsedString + " ORDER BY " + name;
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
				// System.out.println(comboBox_1.getSelectedItem() + " "
				// + textField.getText());
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

		vg.getBtnVpravaVObdob().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String qr1 = "SELECT id_vyprava, cislo_linka, vozidlo_cislo, datum_vyprava, id_vodici FROM vyprava v JOIN linka l ON l.id_linka = v.id_linky JOIN vozidlo b ON b.id_vozidlo = v.id_vozidla "
						+ " WHERE datum_vyprava >= '"
						+ vg.getDateChooser_1().getCalendar()
								.get(Calendar.YEAR)
						+ "-"
						+ (vg.getDateChooser_1().getCalendar()
								.get(Calendar.MONTH) + 1)
						+ "-"
						+ vg.getDateChooser_1().getCalendar()
								.get(Calendar.DATE)
						+ "' AND datum_vyprava <= '"
						+ vg.getDateChooser_2().getCalendar()
								.get(Calendar.YEAR)
						+ "-"
						+ (vg.getDateChooser_2().getCalendar()
								.get(Calendar.MONTH) + 1)
						+ "-"
						+ vg.getDateChooser_2().getCalendar()
								.get(Calendar.DATE) + "'";
				try {
					tableManager.update(table, qr1);
				} catch (SQLException e) {
					e.printStackTrace();
				}

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

		vg.getBtnNewButton_1().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastUsedString = query;
				System.out.println(lastUsedString);
				try {
					tableManager.update(table, lastUsedString);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		vg.getBtnUpraviZznam().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(table.getValueAt(table.getSelectedRow(), 0));
				vz = new VypravaZmena();
				registerButtonsVypravaZmena(vz);

			}
		});

		vg.getBtnNewButton_2().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String delQuery = "DELETE FROM vyprava where id_vyprava = "
						+ ((Number) table.getValueAt(table.getSelectedRow(), 0))
								.intValue();
				try {
					tableManager.updateItem(table, delQuery);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				table.clearSelection();

				lastUsedString = "SELECT id_vyprava, cislo_linka, vozidlo_cislo, datum_vyprava, id_vodici FROM vyprava v JOIN linka l ON l.id_linka = v.id_linky JOIN vozidlo b ON b.id_vozidlo = v.id_vozidla";
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
	}

}