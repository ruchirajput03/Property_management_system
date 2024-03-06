package com.anudip.daoclasses;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.anudip.entityclasses.HibernateUtil;
import com.anudip.entityclasses.Lease;

public class LeaseDAO {
	public void addLease(Lease lease) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(lease);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Lease getLeaseById(long leaseId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Lease.class, leaseId);
		}
	}

	public void updateLease(Lease lease) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(lease);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteLease(long leaseId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Lease lease = session.get(Lease.class, leaseId);
			if (lease != null) {
				session.delete(lease);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<Lease> getAllLease() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Lease", Lease.class).list();
		}

	}
}
