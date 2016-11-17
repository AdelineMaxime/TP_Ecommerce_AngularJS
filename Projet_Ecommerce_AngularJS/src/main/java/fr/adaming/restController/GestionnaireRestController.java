package fr.adaming.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@RestController
@RequestMapping(value="/gestion")
public class GestionnaireRestController {
	
	
	@Autowired
	ICategorieService categorieService;

	@Autowired
	IProduitService produitService;
	
	
	@RequestMapping(value="/allCat", method=RequestMethod.GET, produces="application/json")
	public List<Categorie> getAllCategorie(){
		
		return categorieService.getAllCategorieService();
	}
	
	@RequestMapping(value="/allProd", method=RequestMethod.GET, produces="application/json")
	public List<Produit> getAllProduit(){
		
		return produitService.getAllProductService();
	}
	

}
