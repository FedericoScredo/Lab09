package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.FermataPair;

public class MetroDAO {

	private List<Fermata> fermate = new ArrayList<Fermata>();
	private List<FermataPair> connessioni = new ArrayList<FermataPair>();
	
	public List<Fermata> getAllFermate() {

		final String sql = "SELECT * FROM fermata";

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			//System.out.println("entrato");
			while (rs.next()) {
				Fermata f = new Fermata(rs.getInt("id_Fermata"), rs.getString("nome"), new LatLng(rs.getDouble("coordx"), rs.getDouble("coordy")));
				//System.out.println("aggiunto "+f.toString());
				fermate.add(f);
			}

			st.close();
			conn.close();
			
			return fermate;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

	}
	
	public List<FermataPair> getAllCoppie(){
		
		final String sql = "SELECT id_stazP,id_stazA,connessione.id_linea,linea.velocita FROM connessione,linea";
		
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				FermataPair fp = new FermataPair(getFermata(rs.getInt("id_stazP")),getFermata(rs.getInt("id_stazA")),rs.getInt("id_linea"),rs.getInt("velocita"));
				if (!connessioni.contains(fp))
					connessioni.add(fp);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return connessioni;
		
	}
	
	private Fermata getFermata(int id){
		for (Fermata f: fermate){
			if (f.getIdFermata()==id)
				return f;
		}
		return null;
	}
	
}
