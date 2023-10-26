package in.ineuron.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	static Configuration configuration = null;
	static SessionFactory sessionFactory = null;
	static Session session = null;

	static {
		try {
			configuration = new Configuration();
			configuration.configure();
			sessionFactory = configuration.buildSessionFactory();

		}catch(HibernateException e) {
			e.printStackTrace();
		}

	}

	public static Session getSession()
	{
		if(session==null)
			session = sessionFactory.openSession();
		return session;
	}

	public  static void CloseSession(Session session)
	{
		if(session!=null)
			session.close();
	}

}
