package com.alura.hotel.dao;

import com.alura.hotel.modelo.Huesped;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class HuespedDao {
  private EntityManager entityManager;

  public HuespedDao(EntityManagerFactory entityManagerFactory) {
    this.entityManager = entityManagerFactory.createEntityManager();
  }

  public void close() {
    entityManager.close();
  }

  public void create(Huesped huesped) {
    entityManager.getTransaction().begin();
    entityManager.persist(huesped);
    entityManager.getTransaction().commit();
  }

  public Huesped findById(int id) {
    return entityManager.find(Huesped.class, id);
  }

  public List<Huesped> findAll() {
    Query query = entityManager.createQuery("SELECT r FROM huesped r", Huesped.class);
    return query.getResultList();
  }

  public void update(Huesped huesped) {
    entityManager.getTransaction().begin();
    entityManager.merge(huesped);
    entityManager.getTransaction().commit();
  }

  public void delete(Huesped huesped) {
    entityManager.getTransaction().begin();
    entityManager.remove(huesped);
    entityManager.getTransaction().commit();
  }
}
