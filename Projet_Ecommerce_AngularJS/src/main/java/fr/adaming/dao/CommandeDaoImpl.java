package fr.adaming.dao;

import java.util.List;

import org.hibernate.HibernateException;
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
	public List<Commande> getCommandeByClientDao(Client client) {

		Session s;
		try {
		    s = sf.getCurrentSession();
		} catch (HibernateException e) {
		    s = sf.openSession();
		}

		String req = "SELECT c FROM Commande c WHERE c.client.id_client=:cl";
		Query query = s.createQuery(req);
		query.setParameter("cl", client.getId_client());
		List<Commande> liste = query.list();
		
		return liste;
	}

}
