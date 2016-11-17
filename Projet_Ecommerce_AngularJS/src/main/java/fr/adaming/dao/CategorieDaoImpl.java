package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import fr.adaming.model.Categorie;

@Repository
public class CategorieDaoImpl implements ICategorieDao {
	
	@Autowired
	private SessionFactory sf;
	
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	/**
	 * Red�finition de la m�thode d'ajout d'une cat�gorie
	 */

	@Override
	public void addCategorieDao(Categorie categorie) {
		
		Session session = sf.getCurrentSession();
		session.save(categorie);

	}
	
	/**
	 * Red�finition de la m�thode de suppression d'une cat�gorie
	 */
	
	@Override
	public void deleteCategorieDao(Categorie categorie) {
		
		Session session = sf.getCurrentSession();
		
		String req="Delete from Categorie c where c.id_categorie=:id";
		Query query=session.createQuery(req);
		query.setParameter("id", categorie.getId_categorie());
		query.executeUpdate();
		
	}
	
	/**
	 *Red�finition de la m�thode de modification d'une cat�gorie 
	 */
	
	@Override
	public void updateCategorieDao(Categorie categorie) {
		
		Session session = sf.getCurrentSession();
		String req="Update Categorie c Set c.nom=:catNom where c.id_categorie=:id";
		Query query=session.createQuery(req);
		query.setParameter("catNom", categorie.getNom());
		query.setParameter("id", categorie.getId_categorie());
		query.executeUpdate();

	}
	
	/**
	 * Red�finition de la m�thode de r�cup�ration des cat�gories
	 */

	@Override
	public List<Categorie> getAllCategorieDao() {
		
		Session session = sf.getCurrentSession();
		String req="From Categorie";
		Query query= session.createQuery(req);
		List<Categorie> catListe= query.list();
		
		return catListe;
	}
	
	/**
	 * Red�finition de la m�thode de r�cup�ration d'une cat�gorie via son nom
	 */

	@Override
	public Categorie getCategorieByNameDao(String name) {
		
		Session session = sf.getCurrentSession();
		String req="Select c from Categorie c where c.nom=:nom_cat";
		Query query = session.createQuery(req);
		query.setParameter("nom_cat", name);
		
		List<Categorie> liste = query.list();
		Categorie cat= liste.get(0);		
		return cat;
	}
	
	

}
