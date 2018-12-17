package metier;

import java.util.List;

public class TestMetier {

	public static void main(String[] args) {
		ICatalogueMetier metier = new CatalogueMetierImpl();
		/*
		metier.addProduit(new Produit("REF05","AA",780,3));
		metier.addProduit(new Produit("REF06","BB",790,13));
		metier.addProduit(new Produit("REF07","CC",670,31));
		*/
		metier.addProduit(new Produit("PR05","Supprimer",679,31));
		
		System.out.println("-----------------------");
		List<Produit> prods=metier.listProduits();
		for(Produit p:prods) {
			System.out.println(p.getDesignation());
		}
		System.out.println("-----------------------");
		List<Produit> prods1=metier.produitsParMC("mp");
		for(Produit p:prods1) {
			System.out.println(p.getDesignation());
		}
		System.out.println("-----------------------");
		try {
		Produit p=metier.getProduit("PR09");
		System.out.println(p.getDesignation());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("-----------------------");
		try {
		Produit p=metier.getProduit("PR02");
		p.setPrix(1500);
		metier.updateProduit(p);
		Produit p2=metier.getProduit("PR02");
		System.out.println(p2.getPrix());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("-----------------------");
		metier.deleteProduit("PR05");
	}

}
