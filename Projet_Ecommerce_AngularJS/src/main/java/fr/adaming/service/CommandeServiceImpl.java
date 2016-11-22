package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Commande;

@Service
public class CommandeServiceImpl implements ICommandeService {

	
	@Autowired
	private ICommandeDao commandeDao;
	
	/**
	 * Créer une nouvelle commande
	 */
	@Override
	public void addCommandeService(Commande commande) {

		commandeDao.addCommandeDao(commande);
		
	}

	@Override
	public List<Commande> getCommandeByClientService(String nom) {

		return commandeDao.getCommandeByClientDao(nom);
	}

}
