package com.anudip.daoclasses;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.anudip.entityclasses.BookingRequest;
import com.anudip.entityclasses.HibernateUtil;

public class BookingRequestDAO {
	public void addBookingRequest(BookingRequest bookingRequest) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(bookingRequest);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public BookingRequest getBookingRequestById(long requestId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(BookingRequest.class, requestId);
		}
	}

	public void updateBookingRequest(BookingRequest bookingRequest) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(bookingRequest);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteBookingRequest(long requestId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			BookingRequest bookingRequest = session.get(BookingRequest.class, requestId);
			if (bookingRequest != null) {
				session.delete(bookingRequest);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<BookingRequest> getAllRequest() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM BookingRequest", BookingRequest.class).list();
		}
	}
}
