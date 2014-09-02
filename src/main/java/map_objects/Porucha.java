package map_objects;

import java.sql.Date;

public class Porucha {
	private int id_porucha, id_vozidla;
	private Date datum_od, datum_do;
	private String popis;
	private Vozidlo vozidlo;
	
	public int getId_porucha() {
		return id_porucha;
	}
	public void setId_porucha(int id_porucha) {
		this.id_porucha = id_porucha;
	}
	public int getId_vozidla() {
		return id_vozidla;
	}
	public void setId_vozidla(int id_vozidla) {
		this.id_vozidla = id_vozidla;
	}
	public Date getDatum_od() {
		return datum_od;
	}
	public void setDatum_od(Date datum_od) {
		this.datum_od = datum_od;
	}
	public Date getDatum_do() {
		return datum_do;
	}
	public void setDatum_do(Date datum_do) {
		this.datum_do = datum_do;
	}
	public String getPopis() {
		return popis;
	}
	public void setPopis(String popis) {
		this.popis = popis;
	}
	public Vozidlo getVozidlo() {
		return vozidlo;
	}
	public void setVozidlo(Vozidlo vozidlo) {
		this.vozidlo = vozidlo;
	}

	
}