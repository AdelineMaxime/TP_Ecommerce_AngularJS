package fr.adaming.dao;

import fr.adaming.model.Gestionnaire;

public interface IGestionnaireDao {

	//public void addGestionnaireDao(Gestionnaire gestionnaire);
	public int isExistGestionnaireDao(String nom, String password);
	public Gestionnaire getGestionnaireByNameDao(String loginGestion);
	
	
}
