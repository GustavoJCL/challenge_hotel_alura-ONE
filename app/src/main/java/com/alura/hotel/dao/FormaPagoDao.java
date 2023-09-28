package com.alura.hotel.dao;

import com.alura.hotel.modelo.FormaPago;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class FormaPagoDao {
  private EntityManager entityManager;

  public FormaPagoDao(EntityManagerFactory entityManagerFactory) {
    this.entityManager = entityManagerFactory.createEntityManager();
  }

  public void close() {
    entityManager.close();
  }

  public void create(FormaPago formaPago) {
    entityManager.getTransaction().begin();
    entityManager.persist(formaPago);
    entityManager.getTransaction().commit();
  }

  public FormaPago findById(int id) {
    return entityManager.find(FormaPago.class, id);
  }

  public List<FormaPago> findAll() {
    Query query = entityManager.createQuery("SELECT f FROM forma_pago f", FormaPago.class);
    return (List<FormaPago>) (List<FormaPago>) query.getResultList();
  }

  public void update(FormaPago formaPago) {
    entityManager.getTransaction().begin();
    entityManager.merge(formaPago);
    entityManager.getTransaction().commit();
  }

  public void delete(FormaPago formaPago) {
    entityManager.getTransaction().begin();
    entityManager.remove(formaPago);
    entityManager.getTransaction().commit();
  }
}
