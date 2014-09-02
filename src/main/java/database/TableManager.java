package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import map_objects.Model;
import map_objects.Vodic;
import map_objects.Vozidlo;
import map_objects.Vyprava;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.IntegerType;
import org.hibernate.type.Type;

public class TableManager {

	private TableManager() {
		sf = HibernateUtil.getSessionFactory();
	}

	public static TableManager getSingletonObject() {
		if (ref == null)
			ref = new TableManager();
		return ref;
	}

	SessionFactory sf;
	private static TableManager ref;

	public Vector<String> columnNames = null;

	public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

		ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

		columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
			// System.out.println(metaData.getColumnName(column));
		}

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();

			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}

		return new DefaultTableModel(data, columnNames);

	}

	public DefaultTableModel buildTableModel(List<Object[]> result,
			String[] collumnNamesArray) throws SQLException {

		columnNames = new Vector<String>();
		for (String s : collumnNamesArray) {
			columnNames.add(s);
		}

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for (Object[] row : result) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
				vector.add(row[columnIndex]);
			}
			data.add(vector);
		}

		return new DefaultTableModel(data, columnNames);

	}

	public void setTableAlignment(JTable table) {
		JTableHeader header = table.getTableHeader();
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table
				.getTableHeader().getDefaultRenderer();
		header.setDefaultRenderer(renderer);
		renderer.setHorizontalAlignment(JLabel.CENTER);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		int rowNumber = table.getColumnCount();
		for (int i = 0; i < rowNumber; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
	}

	/************** POUZITIE MAPOVACA *************/

	@SuppressWarnings("unchecked")
	public void vodicVypisPodlaPoctuNehod(JTable table) {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();

		@SuppressWarnings("deprecation")
		Criteria c = session
				.createCriteria(Vodic.class, "vodic")
				.createAlias("vodic.vypravy", "vyprava",
						CriteriaSpecification.LEFT_JOIN)
				.createAlias("vyprava.vozidlo", "vozidlo",
						CriteriaSpecification.LEFT_JOIN)
				.createAlias(
						"vozidlo.nehody",
						"nehoda",
						CriteriaSpecification.LEFT_JOIN,
						Restrictions.eqProperty("vyprava.datum_vyprava",
								"nehoda.datum_nehody"))
				.addOrder(Order.desc("pocet_nehod"))
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("id_vodic"),
										"id_vodic")
								.add(Projections.property("vodic_meno"), "meno")
								.add(Projections.property("vodic_priezvisko"),
										"priezvisko")
								.add(Projections.count("nehoda.id_nehoda"),
										"pocet_nehod")
								.add(Projections.groupProperty("id_vodic")));

		List<Object[]> results = null;
		results = c.list();
		String[] collumns = { "id_vodic", "meno", "priezvisko", "pocet_nehod" };

		t.commit();
		session.close();

		updateTableWithData(table, results, collumns);
	}

	@SuppressWarnings("unchecked")
	public void vodicVypisPodlaSposobenejSkody(JTable table) {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();

		@SuppressWarnings("deprecation")
		Criteria c = session
				.createCriteria(Vodic.class, "vodic")
				.createAlias("vodic.vypravy", "vyprava",
						CriteriaSpecification.LEFT_JOIN)
				.createAlias("vyprava.vozidlo", "vozidlo",
						CriteriaSpecification.LEFT_JOIN)
				.createAlias(
						"vozidlo.nehody",
						"nehoda",
						CriteriaSpecification.LEFT_JOIN,
						Restrictions.eqProperty("vyprava.datum_vyprava",
								"nehoda.datum_nehody"))
				.addOrder(Order.desc("sposobene_skody"))
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("id_vodic"),
										"id_vodic")
								.add(Projections.property("vodic_meno"),
										"_meno")
								.add(Projections.property("vodic_priezvisko"),
										"vodic_priezvisko")
								.add(Projections.sum("nehoda.skoda"),
										"sposobene_skody")
								.add(Projections.groupProperty("id_vodic")));

		List<Object[]> results = null;
		results = c.list();
		String[] collumns = { "id_vodic", "meno", "priezvisko",
				"sposobene_skody" };

		t.commit();
		session.close();

		updateTableWithData(table, results, collumns);
	}

	@SuppressWarnings("unchecked")
	public void vozidlaPriemernyVek(JTable table) {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();

		Criteria c = session
				.createCriteria(Model.class, "model")
				.createAlias("model.vozidla", "vozidlo")
				.addOrder(Order.desc("vek"))
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("model_name"),
										"model_name")
								.add(Projections
										.sqlProjection(
												"ROUND(AVG(YEAR(now()) - rok_zaradenia)) AS vek",
												new String[] { "vek" },
												new Type[] { new IntegerType() }),
										"vek")
								.add(Projections.groupProperty("model_name")));
		List<Object[]> results = null;
		results = c.list();
		String[] collumns = { "model", "priemerny vek" };

		t.commit();
		session.close();

		updateTableWithData(table, results, collumns);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public void vozidlaNajdlhsieOdstavene(JTable table) {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();

		Criteria c = session.createCriteria(Vozidlo.class, "vozidlo");
		c.createAlias("vozidlo.poruchy", "porucha",
				CriteriaSpecification.LEFT_JOIN);
		c.createAlias("vozidlo.model", "model", CriteriaSpecification.LEFT_JOIN);
		c.createAlias("model.znacka", "znacka", CriteriaSpecification.LEFT_JOIN);
		c.addOrder(Order.desc("odstavka"));
		c.setProjection(Projections
				.projectionList()
				.add(Projections.property("vozidlo_cislo"), "vozidlo_cislo")
				.add(Projections.property("znacka.znacka_name"), "znacka")
				.add(Projections.property("model.model_name"), "model")
				.add(Projections.sqlProjection(
						"YEAR(now()) - {alias}.rok_zaradenia as vek",
						new String[] { "vek" },
						new Type[] { new IntegerType() }))
				.add(Projections
						.sqlProjection(
								" COALESCE(SUM(DATEDIFF(datum_do,datum_od)),0) as odstavka",
								new String[] { "odstavka" },
								new Type[] { new IntegerType() }), "odstavka")
				.add(Projections.groupProperty("vozidlo_cislo")));

		List<Object[]> results = null;
		results = c.list();

		for (Object[] o : results) {

			for (Object i : o) {
				System.out.print(i + "  - ");
			}
			System.out.println("");
		}

		String[] collumns = { "vozidlo_cislo", "znacka", "model", "vek",
				"odstavene dni" };

		t.commit();
		session.close();

		updateTableWithData(table, results, collumns);
	}

	@SuppressWarnings("unchecked")
	public void vypravaNajcastejsieVypravovanyModel(JTable table, String cislo) {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();

		Criteria c = session
				.createCriteria(Vyprava.class, "vyprava")
				.createAlias("vyprava.linka", "linka")
				.createAlias("vyprava.vozidlo", "vozidlo")
				.createAlias("vozidlo.model", "model")
				.createAlias("model.znacka", "znacka")
				.add(Restrictions.eq("linka.cislo_linka",
						Integer.parseInt(cislo)))
				.addOrder(Order.desc("count"))
				.setMaxResults(1)
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("znacka.znacka_name"),
										"znacka")
								.add(Projections.property("model.model_name"),
										"model")
								.add(Projections.rowCount(), "count")
								.add(Projections
										.groupProperty("model.model_name")));

		List<Object[]> results = null;
		results = c.list();
		String[] collumns = { "znacka", "model" };

		t.commit();
		session.close();

		updateTableWithData(table, results, collumns);
	}

	/************* KONIEC POUZITIA MAPOVACA **********/

	public void updateTableWithData(JTable table, List<Object[]> results,
			String[] collumns) {
		try {
			table.setModel(buildTableModel(results, collumns));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(table);
		tca.adjustColumns();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setTableAlignment(table);
	}

	public void update(JTable table, String query) throws SQLException {

		// System.out.println(query);
		PreparedStatement ps = null;

		// SessionFactory sf = HibernateUtil.getSessionFactory();

		// Session session = HibernateUtil.getSessionFactory().openSession();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();

		// Session session = em.unwrap(Session.class);
		SessionFactoryImplementor sfi = (SessionFactoryImplementor) session
				.getSessionFactory();
		@SuppressWarnings("deprecation")
		ConnectionProvider cp = sfi.getConnectionProvider();
		Connection conn = cp.getConnection();
		ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		conn.commit();

		table.setModel(buildTableModel(rs));

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(table);
		tca.adjustColumns();

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setTableAlignment(table);
		cp.closeConnection(conn);
		t.commit();
		session.close();

		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(false);

	}

	public void insert(JTable table, String query) throws SQLException {

		// System.out.println(query);
		PreparedStatement ps = null;

		// Session session = HibernateUtil.getSessionFactory().openSession();

		Session session = sf.openSession();
		Transaction t = session.beginTransaction();

		// Query query = session.createSQLQuery("")

		SessionFactoryImplementor sfi = (SessionFactoryImplementor) session
				.getSessionFactory();
		@SuppressWarnings("deprecation")
		ConnectionProvider cp = sfi.getConnectionProvider();
		Connection conn = cp.getConnection();
		ps = conn.prepareStatement(query);

		ps.executeUpdate();
		conn.commit();
		cp.closeConnection(conn);
		t.commit();
		session.close();

		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);

	}

	public void updateItem(JTable table, String query) throws SQLException {

		// Session session = HibernateUtil.getSessionFactory().openSession();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();

		PreparedStatement ps = null;
		try {

			SessionFactoryImplementor sfi = (SessionFactoryImplementor) session
					.getSessionFactory();
			@SuppressWarnings("deprecation")
			ConnectionProvider cp = sfi.getConnectionProvider();
			Connection conn = cp.getConnection();
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			conn.commit();
			cp.closeConnection(conn);
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		t.commit();// transaction is commited
		session.close();

		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(false);
	}

	public int getValue(String query) throws SQLException {

		int v = 0;

		// Session session = HibernateUtil.getSessionFactory().openSession();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();

		SessionFactoryImplementor sfi = (SessionFactoryImplementor) session
				.getSessionFactory();
		@SuppressWarnings("deprecation")
		ConnectionProvider cp = sfi.getConnectionProvider();
		Connection conn = cp.getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();
			conn.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		while (rs.next())
			v = rs.getInt("sum");

		t.commit();
		session.close();
		return v;

	}
}
