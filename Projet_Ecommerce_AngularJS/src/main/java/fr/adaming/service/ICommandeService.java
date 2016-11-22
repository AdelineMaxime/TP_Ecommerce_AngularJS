package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

public interface ICommandeService {

	public void addCommandeService(Commande commande);
	public List<Commande> getCommandeByClientService(Client client);
	
}
