package fr.adaming.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommService;
import fr.adaming.service.IPanierService;
import fr.adaming.service.IProduitService;

@RestController
@RequestMapping(value="/client")
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
	
	
	// Méthodes ------------------------------------------------------------------------------------------------------
	
	@RequestMapping(value="/allProducts", method=RequestMethod.GET, produces="application/json")
	public List<Produit> getAllProducts() {
		
		return produitService.getAllProductService();
	}
	
	@RequestMapping(value="/allCat", method=RequestMethod.GET, produces="application/json")
	public List<Categorie> getAllCat() {
		
		return categorieService.getAllCategorieService();
	}
	
	
}
