package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IGestionnaireDao;
import fr.adaming.model.Gestionnaire;

@Service("gestionServiceBean")
@Transactional
public class GestionnaireServiceImpl implements IGestionnaireService {
	
	@Autowired
	IGestionnaireDao gestDao;

	@Override
	public int isExistGestionnaireService(String nom, String password) {
		
		return gestDao.isExistGestionnaireDao(nom, password);
	}

	@Override
	public Gestionnaire getGestionnaireByNameDao(String nomGestion) {
		
		return gestDao.getGestionnaireByNameDao(nomGestion);
	}

}
