package map_objects;

import java.sql.Date;

public class Nehoda {
	private int id_nehoda, id_vozidla, skoda;
	private Date datum_nehody;
	private Vozidlo vozidlo;

	public int getId_nehoda() {
		return id_nehoda;
	}

	public void setId_nehoda(int id_nehoda) {
		this.id_nehoda = id_nehoda;
	}

	public int getId_vozidla() {
		return id_vozidla;
	}

	public void setId_vozidla(int id_vozidla) {
		this.id_vozidla = id_vozidla;
	}

	public int getSkoda() {
		return skoda;
	}

	public void setSkoda(int skoda) {
		this.skoda = skoda;
	}

	public Date getDatum_nehody() {
		return datum_nehody;
	}

	public void setDatum_nehody(Date datum_nehody) {
		this.datum_nehody = datum_nehody;
	}

	public Vozidlo getVozidlo() {
		return vozidlo;
	}

	public void setVozidlo(Vozidlo vozidlo) {
		this.vozidlo = vozidlo;
	}

}