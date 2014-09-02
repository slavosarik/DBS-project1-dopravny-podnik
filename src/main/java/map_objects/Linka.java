package map_objects;

import java.util.Set;

public class Linka {
	private int id_linka, cislo_linka;
	private String konecna1, konecna2;
	private Set<Vyprava> vypravy;

	public int getId_linka() {
		return id_linka;
	}

	public void setId_linka(int id_linka) {
		this.id_linka = id_linka;
	}

	public int getCislo_linka() {
		return cislo_linka;
	}

	public void setCislo_linka(int cislo_linka) {
		this.cislo_linka = cislo_linka;
	}

	public String getKonecna1() {
		return konecna1;
	}

	public void setKonecna1(String konecna1) {
		this.konecna1 = konecna1;
	}

	public String getKonecna2() {
		return konecna2;
	}

	public void setKonecna2(String konecna2) {
		this.konecna2 = konecna2;
	}

	public Set<Vyprava> getVypravy() {
		return vypravy;
	}

	public void setVypravy(Set<Vyprava> vypravy) {
		this.vypravy = vypravy;
	}

}