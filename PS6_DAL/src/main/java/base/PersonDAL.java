package base;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.PersonDomainModel;
import domain.StudentDomainModel;
import util.HibernateUtil;

public class PersonDAL {
	public static PersonDomainModel addPerson(PersonDomainModel homeSlice) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int personID = 0;
		try {
			tx = session.beginTransaction();
			session.save(homeSlice);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return homeSlice;
	}


	public static ArrayList<PersonDomainModel> getPeople() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel peepsGet = null;		
		ArrayList<PersonDomainModel> peeps = new ArrayList<PersonDomainModel>();
		
		try {
			tx = session.beginTransaction();	
			
			List people = session.createQuery("FROM PersonDomainModel").list();
			for (Iterator iterator = people.iterator(); iterator.hasNext();) {
				PersonDomainModel homeSlice = (PersonDomainModel) iterator.next();
				peeps.add(homeSlice);

			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return peeps;
	}		
	
	public static PersonDomainModel getPerson(UUID personID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel homeSliceGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			Query query = session.createQuery("from PersonDomainModel where personId = :id ");
			query.setParameter("id", personID.toString());
			
			List<?> list = query.list();
			homeSliceGet = (PersonDomainModel)list.get(0);
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return homeSliceGet;
	}
	
	
	public static void deletePerson(UUID personID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel homeSliceGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			PersonDomainModel homeSlice = (PersonDomainModel) session.get(PersonDomainModel.class, personID);
			session.delete(homeSlice);
		
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}	
	public static PersonDomainModel updatePerson(PersonDomainModel homeSlice) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel homeSliceGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			session.update(homeSlice);
	
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return homeSlice;
	}		
	

}
