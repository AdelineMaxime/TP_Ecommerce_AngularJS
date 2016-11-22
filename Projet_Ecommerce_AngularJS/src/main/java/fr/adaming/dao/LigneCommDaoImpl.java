package fr.adaming.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Repository
public class LigneCommDaoImpl implements ILigneCommDao {

	@Autowired
	private SessionFactory sf;

	/**
	 * Ajouter une ligne de commande
	 */
	@Override
	public void addLigneCommDao(LigneCommande lc) {

		Session session = sf.openSession();
		session.save(lc);
	}

	/**
	 * Supprimer une ligne de commande
	 */
	@Override
	public void deleteLigneCommDao(LigneCommande lc) {

		Session session = sf.getCurrentSession();
		String req = "DELETE FROM LigneCommande l WHERE l.id_LC=:id";
		Query query = session.createQuery(req);
		query.setParameter("id", lc.getId_LC());
		query.executeUpdate();

	}

	/**
	 * Modifier une ligne de commande
	 */
	@Override
	public void updateLigneCommDao(int qte) {

		Session session = sf.getCurrentSession();
		String req = "UPDATE LigneCommande l SET l.quantite=:qte";
		Query query = session.createQuery(req);
		query.setParameter("qte", qte);
		query.executeUpdate();

	}

	/**
	 * Obtenir toutes les lignes de commande
	 */
	@Override
	public List<LigneCommande> getAllLigneCommDao() {

		Session session = sf.getCurrentSession();
		String req = "FROM LigneCommande";
		Query query = session.createQuery(req);
		@SuppressWarnings("unchecked")
		List<LigneCommande> liste = query.list();

		return liste;
	}

	@Override
	public List<LigneCommande> getLignCommByCommDao(Commande commande) {

		Session s;
		try {
		    s = sf.getCurrentSession();
		} catch (HibernateException e) {
		    s = sf.openSession();
		}
		
		String req = "SELECT l FROM LigneCommande l WHERE l.panier.id_panier=:id";
		Query query = s.createQuery(req);
		query.setParameter("id", commande.getId_commande());
		
		return query.list();
	}

}
