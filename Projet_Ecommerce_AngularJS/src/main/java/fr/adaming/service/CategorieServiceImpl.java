package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Service("categorieServiceBean")
@Transactional
public class CategorieServiceImpl implements ICategorieService {
	
	@Autowired
	ICategorieDao categorieDao;
	
	/**
	 * Red�finition de la m�thode d'ajout d'une cat�gorie
	 */
	
	@Override
	public void addCategorieService(Categorie categorie) {
		
		categorieDao.addCategorieDao(categorie);
		
	}
	
	/**
	 * Red�finition de la m�thode de suppression d'une cat�gorie
	 */

	@Override
	public void deleteCategorieService(Categorie categorie) {

		categorieDao.deleteCategorieDao(categorie);

	}
	
	/**
	 *Red�finition de la m�thode de modification d'une cat�gorie 
	 */

	@Override
	public void updateCategorieService(Categorie categorie) {

		categorieDao.updateCategorieDao(categorie);

	}
	
	/**
	 * Red�finition de la m�thode de r�cup�ration des cat�gories
	 */

	@Override
	public List<Categorie> getAllCategorieService() {

		return categorieDao.getAllCategorieDao();
	}
	
	/**
	 * Red�finition de la m�thode de r�cup�ration d'une cat�gorie via son nom
	 */

	@Override
	public Categorie getCategorieByNameService(String name) {
		
		return categorieDao.getCategorieByNameDao(name);
	}

}
