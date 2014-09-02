package map_objects;

import java.util.Set;

public class Vozidlo {
	private int id_vozidlo, vozidlo_cislo, id_modely;
	private int rok_zaradenia;
	private String vozovna;
	private Set<Nehoda> nehody;
	private Set<Porucha> poruchy;
	private Set<Vyprava> vypravy;
	private Model model;

	public int getId_vozidlo() {
		return id_vozidlo;
	}

	public void setId_vozidlo(int id_vozidlo) {
		this.id_vozidlo = id_vozidlo;
	}

	public int getVozidlo_cislo() {
		return vozidlo_cislo;
	}

	public void setVozidlo_cislo(int vozidlo_cislo) {
		this.vozidlo_cislo = vozidlo_cislo;
	}

	public int getId_modely() {
		return id_modely;
	}

	public void setId_modely(int id_modely) {
		this.id_modely = id_modely;
	}

	public String getVozovna() {
		return vozovna;
	}

	public void setVozovna(String vozovna) {
		this.vozovna = vozovna;
	}

	public int getRok_zaradenia() {
		return rok_zaradenia;
	}

	public void setRok_zaradenia(int rok_zaradenia) {
		this.rok_zaradenia = rok_zaradenia;
	}

	public Set<Nehoda> getNehody() {
		return nehody;
	}

	public void setNehody(Set<Nehoda> nehody) {
		this.nehody = nehody;
	}

	public Set<Porucha> getPoruchy() {
		return poruchy;
	}

	public void setPoruchy(Set<Porucha> poruchy) {
		this.poruchy = poruchy;
	}

	public Set<Vyprava> getVypravy() {
		return vypravy;
	}

	public void setVypravy(Set<Vyprava> vypravy) {
		this.vypravy = vypravy;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

}