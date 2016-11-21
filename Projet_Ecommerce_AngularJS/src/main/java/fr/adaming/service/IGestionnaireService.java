package fr.adaming.service;

import fr.adaming.model.Gestionnaire;

public interface IGestionnaireService {

	//public void addGestionnaireDao(Gestionnaire gestionnaire);
	public int isExistGestionnaireService(String nom, String password);
	public Gestionnaire getGestionnaireByNameDao(String nomGestion);
	
}
