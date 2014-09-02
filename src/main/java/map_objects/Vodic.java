package map_objects;

import java.util.Set;

public class Vodic {
	private int id_vodic, rodne_cislo;
	private String vodic_meno, vodic_priezvisko;
	private Set<Vyprava> vypravy;
	
	
	public int getId_vodic() {
		return id_vodic;
	}

	public void setId_vodic(int id_vodic) {
		this.id_vodic = id_vodic;
	}

	public int getRodne_cislo() {
		return rodne_cislo;
	}

	public void setRodne_cislo(int rodne_cislo) {
		this.rodne_cislo = rodne_cislo;
	}

	public String getVodic_meno() {
		return vodic_meno;
	}

	public void setVodic_meno(String vodic_meno) {
		this.vodic_meno = vodic_meno;
	}

	public String getVodic_priezvisko() {
		return vodic_priezvisko;
	}

	public void setVodic_priezvisko(String vodic_priezvisko) {
		this.vodic_priezvisko = vodic_priezvisko;
	}

	public Set<Vyprava> getVypravy() {
		return vypravy;
	}

	public void setVypravy(Set<Vyprava> vypravy) {
		this.vypravy = vypravy;
	}

}