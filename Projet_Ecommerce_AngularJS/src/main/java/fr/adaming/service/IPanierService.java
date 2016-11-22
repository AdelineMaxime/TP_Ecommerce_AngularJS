package fr.adaming.service;

import fr.adaming.model.Panier;

public interface IPanierService {

	public Panier addPanierService(Panier panier);
	public Panier getPanierByIdCommandeService(int id);
	
}
