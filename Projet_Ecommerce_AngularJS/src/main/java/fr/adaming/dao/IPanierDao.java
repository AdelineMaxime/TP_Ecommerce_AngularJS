package fr.adaming.dao;

import fr.adaming.model.Panier;

public interface IPanierDao {

	public Panier addPanierDao(Panier panier);
	public Panier getPanierByIdCommandeDao(int id);
}
