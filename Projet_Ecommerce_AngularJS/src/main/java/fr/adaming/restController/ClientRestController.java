package fr.adaming.restController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
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
	Map<Integer, Produit> allProducts = new HashMap<Integer, Produit>();

	
	@PostConstruct
	public void init() {
		
		List<Produit> produitsList = produitService.getAllProductService();
		for (Produit prod:produitsList) {
			allProducts.put(prod.getId_produit(), prod);
		}
	}
	
	
	// M�thodes
	// ------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/allProducts", method = RequestMethod.GET, produces = "application/json")
	public List<Produit> getAllProducts() {

		List<Produit> liste = new ArrayList<Produit>();
		for (Produit p:allProducts.values()) {
			liste.add(p);
		}
		
		return liste;
	}

	@RequestMapping(value = "/allCat", method = RequestMethod.GET, produces = "application/json")
	public List<Categorie> getAllCat() {
		
		return categorieService.getAllCategorieService();
	}

	@RequestMapping(value = "/prodByCat/{idCat}", method = RequestMethod.GET, produces = "application/json")
	public List<Produit> getProdByCat(@PathVariable("idCat") int id) {

		List<Produit> liste = new ArrayList<Produit>();
		List<Produit> liste2 = produitService.getProductByCatService(id);
		
		for (Produit prod:liste2) {
			Produit p = allProducts.get(prod.getId_produit());
			liste.add(p);
		}
		
		return liste;
	}

	@RequestMapping(value = "/addPanier/{nomProd}", method = RequestMethod.GET, produces = "application/json")
	public Panier addToPanier(@PathVariable("nomProd") String nom) {

		Produit p1Service = produitService.getProductByNameService(nom);
		Produit p1 = allProducts.get(p1Service.getId_produit());

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
			allProducts.replace(p1.getId_produit(), p1);
			

		}

		List<LigneCommande> listePanier = new ArrayList<LigneCommande>();
		double prix = 0;

		for (LigneCommande lc : articles.values()) {

			listePanier.add(lc);
			prix = prix + lc.getPrix();

		}
		panier.setListeLC(listePanier);
		panier.setPrixTotal(prix);

		return panier;
	}
	
	@RequestMapping(value="/panier", method=RequestMethod.GET, produces="application/json")
	public Panier getPanier() {
		
		return panier;
	}
	
	@RequestMapping(value="/deletePanier/{nomProd}", method=RequestMethod.GET, produces="application/json")
	public Panier deleteToPanier(@PathVariable("nomProd") String nom) {
		
		Produit p1Service = produitService.getProductByNameService(nom);
		Produit p1 = allProducts.get(p1Service.getId_produit());
		int qte = articles.get(p1.getId_produit()).getQuantite();
		
		p1.setQuantite(p1.getId_produit()+qte);
		allProducts.replace(p1.getId_produit(), p1);
		
		articles.remove(p1.getId_produit());
		
		List<LigneCommande> lcList = new ArrayList<LigneCommande>();
		Panier panierC = new Panier();
		panierC.setPrixTotal(0.00);
		
		for (LigneCommande lc:articles.values()) {
			lcList.add(lc);
			panierC.setPrixTotal(panierC.getPrixTotal()+lc.getPrix());
		}
		
		panier.setListeLC(lcList);
		panier.setPrixTotal(panierC.getPrixTotal());
		
		return panier;
	}
	
	@RequestMapping(value="/connecterClient/{nom}/{password}", method=RequestMethod.POST)
	public Client connexionClient(@PathVariable("nom") String nom, @PathVariable("password") String password) {
		
		int exist = clientService.isExistClientService(nom,password);
		System.out.println(exist);
		
		if (exist == 0) {
			
			return new Client(null, null, null, null, 0);
		} else {
			
			Client client = clientService.getClientByNameDao(nom);
			panier.setClientP(client);
			return client;
		}
	}
	
	@RequestMapping(value="/final/{nomClient}", method=RequestMethod.GET)
	public void finaliser(@PathVariable("nomClient") String nom) {
		
		// R�cup�rer le client correspondant au nom
		Client client = clientService.getClientByNameDao(nom);
		
		// Instancier une nouvelle commande
		Commande comm = new Commande();
		
		// R�cup�rer la date du jour
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		comm.setDate_commande(date);
		
		// Injecter les param�tres de la commande
		panier.setClientP(client);
		comm.setClient(client);
		comm.setPanier(panier);
		
		// Enregistrer les donn�es dans la BDD
		Panier panierC = panierService.addPanierService(panier);
		commandeService.addCommandeService(comm);
		
		// R�cup�rer la liste des articles command�s
		List<LigneCommande> lcList = new ArrayList<LigneCommande>();
		for (LigneCommande lc:articles.values()) {
			lcList.add(lc);
			lc.setPanier(panierC);
			ligneCommService.addLigneCommService(lc);
			// m�j la base de donn�es des produits
			Produit p = produitService.getProductByNameService(lc.getProduit().getNom());
			p.setQuantite(p.getQuantite()-lc.getQuantite());
			produitService.updateProductService(p);
		}
		
		// R�initialiser les donn�es du restController
		panier.setListeLC(null);
		panier.setPrixTotal(0.00);
		panier.setClientP(null);
		articles.clear();
		
	}
	
	@RequestMapping(value="/addClient", method=RequestMethod.POST, produces="application/json")
	public Client addClient(@RequestBody Client client) {
	
		int exist = clientService.isExistClientService(client.getNom());
		
		if (exist == 0) {	// Si aucun client avec le m�me nom exist
			clientService.addClientService(client);
			return client;
			
		} else {			// Si le client existe d�j�
			
			return new Client(null, null, null, null, 0);
		}
		
		
	}
	
	@RequestMapping(value="/commandes", method=RequestMethod.POST, produces="application/json",consumes="application/json")
	public List<Commande> getCommandeByClient(@RequestBody Client client) {
	
		List<Commande> c=commandeService.getCommandeByClientService(client);

		return c;
		
	}
	
	@RequestMapping(value="/oldPanier", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public List<LigneCommande> getPanierByCommande(@RequestBody Commande comm) {
		
//		Panier panier = panierService.getPanierByIdCommandeService(comm.getId_commande());
//		System.out.println("-------------------------"+panier);
		
		List<LigneCommande> liste = ligneCommService.getLignCommByCommService(comm);
		
		return liste;
	}
	
}
