package fr.adaming.restController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.adaming.model.Categorie;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommService;
import fr.adaming.service.IPanierService;
import fr.adaming.service.IProduitService;

@RestController
@RequestMapping(value = "/client")
public class ClientRestController {

	@Autowired
	ICategorieService categorieService;
	@Autowired
	IClientService clientService;
	@Autowired
	ICommandeService commandeService;
	@Autowired
	ILigneCommService ligneCommService;
	@Autowired
	IPanierService panierService;
	@Autowired
	IProduitService produitService;

	Panier panier=new Panier();
	Map<Integer, LigneCommande> articles = new HashMap<Integer, LigneCommande>();

	// Méthodes
	// ------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/allProducts", method = RequestMethod.GET, produces = "application/json")
	public List<Produit> getAllProducts() {

		return produitService.getAllProductService();
	}

	@RequestMapping(value = "/allCat", method = RequestMethod.GET, produces = "application/json")
	public List<Categorie> getAllCat() {

		return categorieService.getAllCategorieService();
	}

	@RequestMapping(value = "/prodByCat/{idCat}", method = RequestMethod.GET, produces = "application/json")
	public List<Produit> getProdByCat(@PathVariable("idCat") int id) {

		return produitService.getProductByCatService(id);
	}

	@RequestMapping(value = "/addPanier/{nomProd}", method = RequestMethod.GET, produces = "application/json")
	public Panier addToPanier(@PathVariable("nomProd") String nom) {

		Produit p1 = produitService.getProductByNameService(nom);

		if (p1.getQuantite() == 0) {

		} else {

			LigneCommande lc1 = articles.get(p1.getId_produit());

			if (lc1 == null) {

				LigneCommande lc = new LigneCommande();
				lc.setProduit(p1);
				lc.setQuantite(1);
				lc.setPrix(p1.getPrix());

				articles.put(p1.getId_produit(), lc);

			} else {

				lc1.setQuantite(lc1.getQuantite() + 1);
				lc1.setPrix(p1.getPrix() * lc1.getQuantite());

			}

			p1.setQuantite(p1.getQuantite() - 1);
			produitService.updateProductService(p1);

		}

		List<LigneCommande> listePanier = new ArrayList<LigneCommande>();
		double prix = 0;

		for (LigneCommande lc : articles.values()) {

			listePanier.add(lc);
			prix = prix + lc.getPrix();

		}
		System.out.println(panier);
		panier.setListeLC(listePanier);
		panier.setPrixTotal(prix);

		return panier;
	}
	
	@RequestMapping(value="/panier", method=RequestMethod.GET, produces="application/json")
	public Panier getPanier() {
		
		return panier;
	}
	
	public Panier deleteToPanier(@PathVariable("nomProd") String nom) {
		
	}
}
