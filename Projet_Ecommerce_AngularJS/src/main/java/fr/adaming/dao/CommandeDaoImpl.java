package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Repository
public class CommandeDaoImpl implements ICommandeDao {

	
	@Autowired
	private SessionFactory sf;
	
	/**
	 * Créer une nouvelle commande
	 */
	@Override
	public void addCommandeDao(Commande commande) {

		Session session = sf.openSession();
		session.save(commande);
		
	}

	@Override
	public List<Commande> getCommandeByClientDao(String nom) {

		Session s = sf.getCurrentSession();
		String req = "SELECT c FROM Commande c WHERE c.client.nom=:nomC";
		Query query = s.createQuery(req);
		query.setParameter("nomC", nom);
		
		return query.list();
	}

}
