package map_objects;

import java.util.Set;

public class Model {
	private int id_model, motor_objem, motor_vykon, pocet_dveri,
			hmotnost, kapacita;
	private String model_name, farba;
	private Znacka znacka;
	private Set<Vozidlo> vozidla;

	public Znacka getZnacka() {
		return znacka;
	}

	public void setZnacka(Znacka znacka) {
		this.znacka = znacka;
	}

	public int getId_model() {
		return id_model;
	}

	public void setId_model(int id_model) {
		this.id_model = id_model;
	}

	public int getMotor_objem() {
		return motor_objem;
	}

	public void setMotor_objem(int motor_objem) {
		this.motor_objem = motor_objem;
	}

	public int getMotor_vykon() {
		return motor_vykon;
	}

	public void setMotor_vykon(int motor_vykon) {
		this.motor_vykon = motor_vykon;
	}

	public int getPocet_dveri() {
		return pocet_dveri;
	}

	public void setPocet_dveri(int pocet_dveri) {
		this.pocet_dveri = pocet_dveri;
	}

	public int getHmotnost() {
		return hmotnost;
	}

	public void setHmotnost(int hmotnost) {
		this.hmotnost = hmotnost;
	}

	public int getKapacita() {
		return kapacita;
	}

	public void setKapacita(int kapacita) {
		this.kapacita = kapacita;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public String getFarba() {
		return farba;
	}

	public void setFarba(String farba) {
		this.farba = farba;
	}

	public Set<Vozidlo> getVozidla() {
		return vozidla;
	}

	public void setVozidla(Set<Vozidlo> vozidla) {
		this.vozidla = vozidla;
	}

}