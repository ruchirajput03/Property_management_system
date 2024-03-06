package com.anudip.daoclasses;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.anudip.entityclasses.HibernateUtil;
import com.anudip.entityclasses.Owner;

public class OwnerDAO {
	public void addOwner(Owner owner) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(owner);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Owner getOwnerById(int ownerId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Owner.class, ownerId);
		}
	}

	public void updateOwner(Owner owner) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(owner);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteOwner(int ownerId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Owner owner = session.get(Owner.class, ownerId);
			if (owner != null) {
				session.delete(owner);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<Owner> getAllUsers() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Owner", Owner.class).list();
		}

	}
}
