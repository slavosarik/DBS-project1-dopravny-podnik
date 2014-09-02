package map_objects;

import java.util.Set;

public class Znacka {
	private int id_znacka;
	private String znacka_name, krajina;
	private Set<Model> modely;

	public Set<Model> getModely() {
		return modely;
	}

	public void setModely(Set<Model> modely) {
		this.modely = modely;
	}

	public int getId_znacka() {
		return id_znacka;
	}

	public void setId_znacka(int id_znacka) {
		this.id_znacka = id_znacka;
	}

	public String getZnacka_name() {
		return znacka_name;
	}

	public void setZnacka_name(String znacka_name) {
		this.znacka_name = znacka_name;
	}

	public String getKrajina() {
		return krajina;
	}

	public void setKrajina(String krajina) {
		this.krajina = krajina;
	}

}