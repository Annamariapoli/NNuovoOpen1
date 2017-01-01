package application;

import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import org.jgrapht.graph.DefaultWeightedEdge;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import bean.Airline;
import bean.Airport;
import bean.Route;
import db.Dao;

public class Model {
	
	private Dao dao = new Dao();
	private DefaultDirectedWeightedGraph<Airport, DefaultWeightedEdge> grafo = null;
	
	public List<Airline> getComp(){
		List<Airline> air = dao.getCompagnie();
		return air;
	}
	
	public List<Airport> getAirport(){
		List<Airport> air = dao.getAllAerei();
		return air;
	}

	
	public List<Route> getRotte(int codiceCom){
		List<Route> rotte = dao.getRotteDiQuellaCompagnia(codiceCom);
		return rotte;
	}
	public DefaultDirectedWeightedGraph<Airport, DefaultWeightedEdge> buildGraph(int codiceCompagnia){
		grafo = new DefaultDirectedWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, getAirport());
		for(Airport a1 : grafo.vertexSet()){
			for(Airport a2 : grafo.vertexSet()){
				List<Route> rotte = dao.getRotteDiQuellaCompagnia(codiceCompagnia);  //tra le rotte di quella compagnia
				for(Route r1 : rotte){
				if(a1.getAirportId()==r1.getSourceAirportId() && a2.getAirportId()==r1.getDestinationAirportId() || 
						a1.getAirportId()==r1.getDestinationAirportId() && a2.getAirportId()==r1.getSourceAirportId()){
					double distanza = LatLngTool.distance(a1.getCoords(), a2.getCoords(), LengthUnit.KILOMETER);
					Graphs.addEdge(grafo, a1, a2, distanza);
					
				     }
				}
			}
		}
		System.out.println(grafo.toString());
		return grafo ;
	}

	public static void main(String [] args){
		Model m = new Model();
		m.buildGraph(410);
	}
	
}
