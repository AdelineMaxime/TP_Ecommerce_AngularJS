package fr.adaming.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Panier;

@Repository
public class PanierDaoImpl implements IPanierDao {

	
	@Autowired
	private SessionFactory sf;
	
	/**
	 * Créer un nouveau panier
	 */
	@Override
	public Panier addPanierDao(Panier panier) {

		Session session = sf.openSession();
		session.save(panier);
		
		return panier;
	}

	@Override
	public Panier getPanierByIdCommandeDao(int id) {

		Session s;
		try {
		    s = sf.getCurrentSession();
		} catch (HibernateException e) {
		    s = sf.openSession();
		}
		String req = "SELECT p FROM Panier p WHERE p.id_panier=:id";
		Query query = s.createQuery(req);
		query.setParameter("id", id);
		
		return (Panier) query.uniqueResult();
	}

}
