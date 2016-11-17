package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

@Service("clientServiceBean")
@Transactional
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientDao clientDao;

	/**
	 * Cr�er un nouveau client
	 */
	@Override
	public void addClientService(Client client) {

		clientDao.addClientDao(client);
	}

	/**
	 * V�rifier s'il existe un compte avec le nom et le mdp propos�s Retourne 0
	 * s'il n'en existe pas
	 */
	@Override
	public int isExistClientService(String nom, String password) {

		return clientDao.isExistClientDao(nom, password);
	}

	/**
	 * V�rifier s'il existe d�j� un compte avec le m�me nom de client Retourne 0
	 * s'il n'en existe pas
	 */
	@Override
	public int isExistClientService(String nom) {

		return clientDao.isExistClientDao(nom);
	}

	/**
	 * R�cup�rer un client par son nom
	 */
	@Override
	public Client getClientByNameDao(String nomClient) {

		return clientDao.getClientByNameDao(nomClient);
	}

}
