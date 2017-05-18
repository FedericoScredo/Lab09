package it.polito.tdp.metrodeparis.dao;

import java.util.List;

import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.FermataPair;

public class TestDAO {

	public static void main(String[] args) {
		
		MetroDAO metroDAO = new MetroDAO();
		
		System.out.println("Lista fermate");
		List<Fermata> fermate = metroDAO.getAllFermate();
		System.out.println(fermate.toString());
		
		System.out.println("Lista connessioni");
		List<FermataPair> conness = metroDAO.getAllCoppie();
		for(FermataPair fp : conness )
			System.out.println(fp.toString());
		
	}

}
