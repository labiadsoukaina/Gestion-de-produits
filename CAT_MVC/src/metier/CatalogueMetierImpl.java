package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogueMetierImpl implements ICatalogueMetier {

	@Override
	public void addProduit(Produit p) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement
					("insert into PRODUITS(REF_PROD,DESIGNATION,PRIX,QUANTITE) values (?,?,?,?)");
			ps.setString(1, p.getReference());
			ps.setString(2, p.getDesignation());
			ps.setDouble(3, p.getPrix());
			ps.setInt(4, p.getQuantite());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Produit> listProduits() {
		List<Produit> prods = new ArrayList<Produit>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement
					("select * from produits");
			ResultSet rs = ps.executeQuery();
			//pour chaque ligne on crée un Produit
			while(rs.next()) {
				Produit p = new Produit();
				p.setReference(rs.getString("REF_PROD"));
				p.setDesignation(rs.getString("DESIGNATION"));
				p.setPrix(rs.getDouble("PRIX"));
				p.setQuantite(rs.getInt("QUANTITE"));
				prods.add(p);
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prods;
	}

	@Override
	public List<Produit> produitsParMC(String mc) {
		List<Produit> prods = new ArrayList<Produit>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement
					("select * from produits where DESIGNATION like ?");
			ps.setString(1,"%"+mc+"%");
			ResultSet rs = ps.executeQuery();
			//pour chaque ligne on crée un Produit
			while(rs.next()) {
				Produit p = new Produit();
				p.setReference(rs.getString("REF_PROD"));
				p.setDesignation(rs.getString("DESIGNATION"));
				p.setPrix(rs.getDouble("PRIX"));
				p.setQuantite(rs.getInt("QUANTITE"));
				prods.add(p);
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prods;
	}

	@Override
	public Produit getProduit(String ref) {
		Produit p= null ;
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement
					("select * from produits where REF_PROD = ?");
			ps.setString(1, ref);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				p=new Produit();
				p.setReference(rs.getString("REF_PROD"));
				p.setDesignation(rs.getString("DESIGNATION"));
				p.setPrix(rs.getDouble("PRIX"));
				p.setQuantite(rs.getInt("QUANTITE"));
			}		
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(p==null) throw new RuntimeException("Produit " +ref+" introuvable");
		return p;
	}

	@Override
	public void updateProduit(Produit p) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement
					("update PRODUITS set DESIGNATION=?, PRIX=?, QUANTITE=? where REF_PROD=?");
			ps.setString(1, p.getDesignation());
			ps.setDouble(2, p.getPrix());
			ps.setInt(3, p.getQuantite());
			ps.setString(4, p.getReference());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteProduit(String ref) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement
					("delete from PRODUITS where REF_PROD=?");
			ps.setString(1, ref);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
