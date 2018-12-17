package metier;

import java.util.List;

public interface ICatalogueMetier {
	public void addProduit(Produit p);
	public List<Produit> listProduits();//afficher tt les produits
	public List<Produit> produitsParMC(String mc);//chercher produit par Mot-Clé
	public Produit getProduit(String ref);//selectionner produit
	public void updateProduit(Produit p);
	public void deleteProduit(String ref);
	
}
