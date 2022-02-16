package pti.sb_squash_MVC.db;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import pti.sb_squash_MVC.model.Location;
import pti.sb_squash_MVC.model.Match;
import pti.sb_squash_MVC.model.User;

public class Database {

	private SessionFactory sessionFactory;

	public Database() {

		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() // configures settings from
																							// hibernate.cfg.xml
				.build();

		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	public void close() {
		sessionFactory.close();
	}

	public void saveUser(User user) {

		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();

		session.save(user);

		t.commit();
		session.close();
	}

	public void saveLocation(Location location) {

		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();

		session.save(location);

		t.commit();
		session.close();
	}

	public void saveMatch(Match match) {

		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();

		session.save(match);

		t.commit();
		session.close();

	}

	public List<Match> getMatches() {
		List<Match> matches = null;

		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();

		Query query = session.createQuery("SELECT m FROM Match m", Match.class);
		matches = query.getResultList();

		tr.commit();
		session.close();

		return matches;

	}

	public boolean registeredUser(String name, String pwd) {
		boolean registered = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("SELECT u FROM User u WHERE u.name = :name AND u.password = :pwd",
				User.class);
		query.setParameter("name", name);
		query.setParameter("pwd", pwd);
		List<User> users = query.getResultList();

		if (users.isEmpty() == false) {
			registered = true;
		}

		transaction.commit();
		session.close();
		return registered;
	}
}
