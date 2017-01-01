package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

import bean.Airline;
import bean.Airport;
import bean.Route;

public class Dao {
	
	public List<Airline> getCompagnie(){
		Connection conn = DBConnect.getConnection();
		String query =" select * from airline";
		List<Airline> air = new LinkedList<Airline>();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()){
				Airline a = new Airline(res.getInt("Airline_ID"), res.getString("Name"), res.getString("Alias"), res.getString("IATA"), res.getString("icao"), res.getString("callsign"), res.getString("country"), res.getString("active"));
				air.add(a);
			}
			conn.close();
			return air;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}
	

	public List<Airport > getAllAerei(){
		Connection conn = DBConnect.getConnection();
		String query =" select * from airport";
		List<Airport> air = new LinkedList<>();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()){
				Airport a = new Airport(res.getInt("Airport_ID"), res.getString("name"), res.getString("city"), res.getString("country"),
						res.getString("IATA_FAA"),res.getString("ICAO"),
						new LatLng (res.getDouble("Latitude"), res.getDouble("Longitude")),
						res.getFloat("timezone"), res.getString("dst"),res.getString("tz"));
				air.add(a);
			}
			conn.close();
			return air;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Route> getRotteDiQuellaCompagnia(int codiceCom){
		Connection conn = DBConnect.getConnection();
		String query =" select * from route r where r.Airline_ID=?";
		List<Route> rotte = new LinkedList<>();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, codiceCom);
			ResultSet res = st.executeQuery();
			while(res.next()){
				Route r = new Route(res.getString("Airline"),
						res.getInt("Airline_ID"),
						res.getString("Source_airport"),
						res.getInt("Source_airport_ID"),
						res.getString("Destination_airport"),
						res.getInt("Destination_airport_ID"),
						res.getString("Codeshare"),
						res.getInt("stops"),
						res.getString("Equipment"));
				rotte.add(r);
			}
			conn.close();
			return rotte;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
		
	}
	
	
}
