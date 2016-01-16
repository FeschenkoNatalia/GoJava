package ua.com.goit.gojava7.kickstarter.dao.jdbc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtil {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getList(String queryText, Object...args) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(queryText);
		appendParametersToQuery(query, args);
		List<T> list = query.list();
    	session.close();
    	return list;
	}
	
	public <T> T get(String queryText, Object...args) {
		T result = null;
		List<T> list = getList(queryText, args);
		if (!list.isEmpty()) {
			result = list.get(0);
		}
    	return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> getForSQL(String queryText, Object...args) {
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(queryText);
		appendParametersToQuery(query, args);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<HashMap<String,Object>> list = query.list();
    	session.close();
    	return list;
	}
	
	public void executeUpdate(String queryText, Object...args) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(queryText);
		appendParametersToQuery(query, args);
		query.executeUpdate();
		session.close();
	}

	private void appendParametersToQuery(Query query, Object... args) {
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
	}

	public <T> void save(List<T> elements) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		for (T item : elements) {
			session.save(item);
		}
		tx.commit();
		session.close();
	}

	public <T> void save(T element) {
		List<T> list = new ArrayList<>();
		list.add(element);
		save(list);
	}

}