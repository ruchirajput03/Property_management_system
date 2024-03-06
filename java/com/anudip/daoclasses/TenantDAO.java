package com.anudip.daoclasses;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.anudip.entityclasses.HibernateUtil;
import com.anudip.entityclasses.Tenant;

public class TenantDAO {
	public void addTenant(Tenant tenant) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(tenant);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Tenant getTenantById(int tenantId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Tenant.class, tenantId);
		}
	}

	public void updateTenant(Tenant tenant) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(tenant);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteTenant(int tenantId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Tenant tenant = session.get(Tenant.class, tenantId);
			if (tenant != null) {
				session.delete(tenant);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<Tenant> getAllTenant() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Tenant", Tenant.class).list();
		}

	}
}
