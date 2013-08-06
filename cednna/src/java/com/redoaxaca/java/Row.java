package com.redoaxaca.java;

public class Row {

	Integer fligth, fligthOut;
	String airCraft;
	String ori, est, des;
	String eta;
	Integer dia, vuelo;
	String fecha_inicial;
	String fecha_final;
	String edt, itd, oad;
	String tiempoEstacion;
	String intervalo;
	String aeronave;

	public Row() {

	}

	public String getAeronave() {
		return aeronave;
	}

	public void setAeronave(String aeronave) {
		this.aeronave = aeronave;
	}

	public String getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(String intervalo) {
		this.intervalo = intervalo;
	}

	public String getTiempoEstacion() {
		return tiempoEstacion;
	}

	public void setTiempoEstacion(String tiempoEstacion) {
		this.tiempoEstacion = tiempoEstacion;
	}

	public String getItd() {
		return itd;
	}

	public void setItd(String itd) {
		this.itd = itd;
	}

	public String getOad() {
		return oad;
	}

	public void setOad(String oad) {
		this.oad = oad;
	}

	public String getFIni() {
		return fecha_inicial;
	}

	public void setFIni(String fecha_inicial) {
		this.fecha_inicial = fecha_inicial;
	}

	public String getFFin() {
		return fecha_final;
	}

	public void setFFin(String fecha_final) {
		this.fecha_final = fecha_final;
	}

	public Integer getFligth() {
		return fligth;
	}

	public void setFligth(Integer fligth) {
		this.fligth = fligth;
	}

	public Integer getFligthOut() {
		return fligthOut;
	}

	public void setFligthOut(Integer fligthOut) {
		this.fligthOut = fligthOut;
	}

	public String getAirCraft() {
		return airCraft;
	}

	public void setAirCraft(String airCraft) {
		this.airCraft = airCraft;
	}

	public String getOri() {
		return ori;
	}

	public void setOri(String ori) {
		this.ori = ori;
	}

	public String getEst() {
		return est;
	}

	public void setEst(String est) {
		this.est = est;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public String getEdt() {
		return edt;
	}

	public void setEdt(String edt) {
		this.edt = edt;
	}

	public Integer getDay() {
		return dia;
	}

	public void setDay(Integer day) {
		this.dia = day;
	}

	public Integer getVuelo() {
		return vuelo;
	}

	public void setVuelo(Integer vuelo) {
		this.vuelo = vuelo;
	}

	// public String toString(){
	// return
	// est + " " + fligth + " " + fligthOut+ " " + airCraft+ " " +
	// ori+ " " + est+ " " + des+ " " +
	// eta+ " " + edt+ " " +
	// dia+ " " +
	// vuelo + " " + intervalo;
	// }

	public String toString() {
		return fligth + " " + fligthOut + airCraft + ori + " " + est + " "
				+ des + eta + dia + " " + vuelo + fecha_inicial
				+ fecha_final + edt + " " + itd + " " + oad + tiempoEstacion
				+ intervalo + aeronave;
	}

}
