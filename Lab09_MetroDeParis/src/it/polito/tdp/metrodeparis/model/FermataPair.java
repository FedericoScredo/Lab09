package it.polito.tdp.metrodeparis.model;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class FermataPair {
	
	private Fermata f1;
	private Fermata f2;
	private int linea;
	private int velocita;
	
	public FermataPair(Fermata f1, Fermata f2, int linea, int velocita) {
		super();
		this.f1 = f1;
		this.f2 = f2;
		this.linea = linea;
		this.velocita = velocita;
	}

	public Fermata getF1() {
		return f1;
	}

	public Fermata getF2() {
		return f2;
	}

	public int getLinea() {
		return linea;
	}
	
	public double calcolaTempo(){
		double d= LatLngTool.distance(f1.getCoords(),f2.getCoords(), LengthUnit.KILOMETER);
		double t= d/velocita;
		return t*60;
	}

	@Override
	public String toString() {
		return "da "+ f1 + " a "+ f2 + " linea " + linea + " tempo "+ calcolaTempo();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((f1 == null) ? 0 : f1.hashCode());
		result = prime * result + ((f2 == null) ? 0 : f2.hashCode());
		result = prime * result + linea;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FermataPair other = (FermataPair) obj;
		if (f1 == null) {
			if (other.f1 != null)
				return false;
		} else if (!f1.equals(other.f1))
			return false;
		if (f2 == null) {
			if (other.f2 != null)
				return false;
		} else if (!f2.equals(other.f2))
			return false;
		if (linea != other.linea)
			return false;
		return true;
	}

	
	
}
