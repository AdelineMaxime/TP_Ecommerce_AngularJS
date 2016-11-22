package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.adaming.dao.IPanierDao;
import fr.adaming.model.Panier;

@Service
public class PanierServiceImpl implements IPanierService {

	
	@Autowired
	private IPanierDao panierService;
	
	/**
	 * Créer un nouveau panier
	 */
	@Override
	public Panier addPanierService(Panier panier) {

		return panierService.addPanierDao(panier);
	}

	@Override
	public Panier getPanierByIdCommandeService(int id) {

		return panierService.getPanierByIdCommandeDao(id);
	}

}
