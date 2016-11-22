package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

public interface ICommandeDao {

	public void addCommandeDao(Commande commande);
	public List<Commande> getCommandeByClientDao(String nom);
	
}
