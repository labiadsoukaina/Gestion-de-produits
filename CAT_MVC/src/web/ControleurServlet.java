package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.CatalogueMetierImpl;
import metier.ICatalogueMetier;
import metier.Produit;

public class ControleurServlet extends HttpServlet{
	private ICatalogueMetier metier;
	
	@Override
	public void init() throws ServletException {
		// Cette methode s'execute lors du chargement de la Servlet en memoire
		metier= new CatalogueMetierImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) 
					throws ServletException, IOException {
		 doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, 
			HttpServletResponse resp) 
					throws ServletException, IOException {
		ProduitModel model = new ProduitModel();
		req.setAttribute("model", model);//stocker le model dans l'objet Request
		String action = req.getParameter("action");
		if(action!=null) {
			if(action.equals("chercher")) {
				model.setMotCle(req.getParameter("motCle"));
				List<Produit> produits = metier.produitsParMC(model.getMotCle());
				model.setProduits(produits);//stocker le RSLT dans le model
				
			}
			else if(action.equals("delete")) {
				String ref=req.getParameter("ref");
				metier.deleteProduit(ref);
				model.setProduits(metier.listProduits());
			}
			else if(action.equals("edit")) {
				String ref=req.getParameter("ref");
				Produit p=metier.getProduit(ref);
				model.setProduit(p);
				model.setMode("edit");
				model.setProduits(metier.listProduits());
			}
			else if(action.equals("save")) {
				try {
			     model.getProduit().setReference(req.getParameter("reference"));
			     model.getProduit().setDesignation(req.getParameter("designation"));
			     model.getProduit().setPrix(Double.parseDouble(req.getParameter("prix")));
			     model.getProduit().setQuantite(Integer.parseInt(req.getParameter("quantite")));
				 model.setMode(req.getParameter("mode"));
				 
			     if(model.getMode().equals("ajout"))
			    	 metier.addProduit(model.getProduit());
			     else if(model.getMode().equals("edit"))
			    	 metier.updateProduit(model.getProduit());
			     model.setProduits(metier.listProduits());
				}catch(Exception e) {
					model.setErrors(e.getMessage());
				}
			}
		}
		
		
		req.getRequestDispatcher("VueProduits.jsp").
		 forward(req, resp);
	}
	

}
