package it.polito.tdp.metrodeparis.model;

import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;


public class FermataTraversalListener implements TraversalListener<Fermata, DefaultWeightedEdge> {
	
	private Graph<Fermata,DefaultWeightedEdge> g;
	private Map<Fermata,Fermata> map;

	public FermataTraversalListener(Graph<Fermata, DefaultWeightedEdge> g, Map<Fermata, Fermata> map) {
		super();
		this.g = g;
		this.map = map;
	}

	@Override
	public void connectedComponentFinished(ConnectedComponentTraversalEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectedComponentStarted(ConnectedComponentTraversalEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void edgeTraversed(EdgeTraversalEvent<Fermata, DefaultWeightedEdge> evento) {
		// TODO Auto-generated method stub
		
		Fermata c1 = g.getEdgeSource(evento.getEdge());
		Fermata c2 = g.getEdgeTarget(evento.getEdge());
		//DefaultWeightedEdge e = evento.getEdge();
		
		
		if (map.containsKey(c1) && map.containsKey(c2))
			return;
		
		if (!map.containsKey(c1)){
			map.put(c1,c2);
		}
		else
			map.put(c2,c1);
	}

	@Override
	public void vertexFinished(VertexTraversalEvent<Fermata> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void vertexTraversed(VertexTraversalEvent<Fermata> arg0) {
		// TODO Auto-generated method stub

	}

}
