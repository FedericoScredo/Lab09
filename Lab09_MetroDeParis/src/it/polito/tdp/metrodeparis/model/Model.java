package it.polito.tdp.metrodeparis.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import it.polito.tdp.metrodeparis.dao.MetroDAO;

public class Model {
	
	private MetroDAO dao = new MetroDAO();
	private List<Fermata> fermate;
	private WeightedGraph<Fermata,DefaultWeightedEdge> grafo;
	private Map<Fermata,Fermata> alberovisite = new HashMap<Fermata,Fermata>();
	private double pesototale=0;

	public List<Fermata> getFermate() {
		// TODO Auto-generated method stub
		if (fermate==null){
			fermate  = new ArrayList<Fermata>();
			fermate = dao.getAllFermate();
		}
		return fermate;
	}
	
	public List<Fermata> getRaggiungibili(Fermata partenza) {
		
		WeightedGraph<Fermata,DefaultWeightedEdge> g = this.getGrafo();
		BreadthFirstIterator<Fermata,DefaultWeightedEdge> bfi = new BreadthFirstIterator<Fermata,DefaultWeightedEdge>(g,partenza);
		
		List<Fermata> ragg=new ArrayList<Fermata>();
		Map<Fermata,Fermata> albero = new HashMap<Fermata,Fermata>();
		
		albero.put(partenza, null);
		
		bfi.addTraversalListener(new FermataTraversalListener(g,albero));
		
		while(bfi.hasNext()){
			ragg.add(bfi.next());
		}
		
		this.alberovisite = albero; 
		
		return ragg;
	}
	
	private WeightedGraph<Fermata,DefaultWeightedEdge> getGrafo(){
		
		if (grafo==null){
			this.creaGrafo();
		}
		return grafo;
	
	}
	
	private void creaGrafo(){
		
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class) ;
		
		// crea i vertici del grafo
		Graphs.addAllVertices(grafo, fermate) ;
	
		// crea gli archi del grafo -- versione 3
		for(FermataPair cp : dao.getAllCoppie()) {
			grafo.addEdge(cp.getF1(), cp.getF2());
			//System.out.println("creato arco tra "+cp.getF1()+" e "+cp.getF2()+" della linea "+cp.getLinea()+" di peso "+cp.calcolaTempo());
			grafo.setEdgeWeight(grafo.getEdge(cp.getF1(),cp.getF2()),cp.calcolaTempo());
			
		}
		
	}

	public List<Fermata> percorsoMinimo(Fermata partenza,Fermata arrivo){
		
		DijkstraShortestPath<Fermata,DefaultWeightedEdge> dsp = new DijkstraShortestPath(grafo,partenza,arrivo);
		
		pesototale = dsp.getPath().getWeight();
		
		return Graphs.getPathVertexList(dsp.getPath());
		
	}
	
	public double getPeso(){
		return pesototale;
	}
}
