package map_objects;

import java.sql.Date;

public class Vyprava {
	private int id_vyprava, id_vozidla, id_vodici, id_linky;
	private Date datum_vyprava;
	private Vozidlo vozidlo;
	private Vodic vodic;
	private Linka linka;

	public int getId_vyprava() {
		return id_vyprava;
	}

	public void setId_vyprava(int id_vyprava) {
		this.id_vyprava = id_vyprava;
	}

	public int getId_vozidla() {
		return id_vozidla;
	}

	public void setId_vozidla(int id_vozidla) {
		this.id_vozidla = id_vozidla;
	}

	public int getId_vodici() {
		return id_vodici;
	}

	public void setId_vodici(int id_vodici) {
		this.id_vodici = id_vodici;
	}

	public int getId_linky() {
		return id_linky;
	}

	public void setId_linky(int id_linky) {
		this.id_linky = id_linky;
	}

	public Date getDatum_vyprava() {
		return datum_vyprava;
	}

	public void setDatum_vyprava(Date datum_vyprava) {
		this.datum_vyprava = datum_vyprava;
	}

	public Vozidlo getVozidlo() {
		return vozidlo;
	}

	public void setVozidlo(Vozidlo vozidlo) {
		this.vozidlo = vozidlo;
	}

	public Vodic getVodic() {
		return vodic;
	}

	public void setVodic(Vodic vodic) {
		this.vodic = vodic;
	}

	public Linka getLinka() {
		return linka;
	}

	public void setLinka(Linka linka) {
		this.linka = linka;
	}

}