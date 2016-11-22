package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Gestionnaire;

@Repository
public class GestionnaireDaoImpl implements IGestionnaireDao {
	
	
	@Autowired
	private SessionFactory sf;

	@Override
	public int isExistGestionnaireDao(String login, String password) {
		
		Session session = sf.getCurrentSession();
		String req = "SELECT c FROM Gestionnaire c WHERE c.login=:nom AND c.mdp=:mdp";
		Query query = session.createQuery(req);
		query.setParameter("nom", login);
		query.setParameter("mdp", password);
		
		return query.list().size();
	}

	@Override
	public Gestionnaire getGestionnaireByNameDao(String loginGestion) {

		Session session = sf.getCurrentSession();
		String req = "SELECT c FROM Gestionnaire c WHERE c.login=:nom";
		Query query = session.createQuery(req);
		query.setParameter("nom", loginGestion);

		return  (Gestionnaire) query.uniqueResult();
		
		
	}

}
