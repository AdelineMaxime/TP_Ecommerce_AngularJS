package fr.adaming.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping(value="/categorie/{name}", method=RequestMethod.GET, produces="application/json")
	public Categorie getCategorieByName(@PathVariable("name") String name){
		
		return categorieService.getCategorieByNameService(name);
	}
	
	@RequestMapping(value="/produit/{name}", method=RequestMethod.GET, produces="application/json")
	public Produit getProduitByName(@PathVariable("name") String name){
		
		return produitService.getProductByNameService(name);
	}
	
	@RequestMapping(value="/allProd/{id}", method=RequestMethod.GET, produces="application/json")
	public List<Produit> getAllProduitByCat(@PathVariable("id") int id){
		
		return produitService.getProductByCatService(id);
	}
	
	@RequestMapping(value="/ajouterCat", method=RequestMethod.POST, produces="application/json")
	public void ajouterCategorie(@RequestBody Categorie categorie){
		
		categorieService.addCategorieService(categorie);
	}

	@RequestMapping(value="/ajouterProd", method=RequestMethod.POST, produces="application/json")
	public void ajouterProduit(@RequestBody Produit produit){
		
		produitService.addProductService(produit);
	}
	
	@RequestMapping(value="/supprimerCat/{name}", method=RequestMethod.DELETE, produces="application/json")
	public void supprimerCat(@PathVariable("name") String name){
		
		Categorie categorie = categorieService.getCategorieByNameService(name);
		
		categorieService.deleteCategorieService(categorie);
		
	}
	
	@RequestMapping(value="/supprimerProd/{name}", method=RequestMethod.DELETE, produces="application/json")
	public void supprimerProd(@PathVariable("name") String name){
		
		Produit produit = produitService.getProductByNameService(name);
		
		produitService.deleteProductService(produit);		
		
	}
	
	@RequestMapping(value="/modifierProd", method=RequestMethod.PUT, produces="application/json", consumes="application/json")
	public void modifierProduit(@RequestBody Produit produit){
		
		produitService.updateProductService(produit);
	}

}
