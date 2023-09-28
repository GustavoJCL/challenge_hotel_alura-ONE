package com.alura.hotel.dao;

import com.alura.hotel.modelo.Reserva;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class ReservaDao {
  private EntityManager entityManager;

  public ReservaDao(EntityManagerFactory entityManagerFactory) {
    this.entityManager = entityManagerFactory.createEntityManager();
  }

  public void close() {
    entityManager.close();
  }

  public void create(Reserva reserva) {
    entityManager.getTransaction().begin();
    entityManager.persist(reserva);
    entityManager.getTransaction().commit();
  }

  public Reserva findById(int id) {
    return entityManager.find(Reserva.class, id);
  }

  public List<Reserva> findAll() {

    Query query = entityManager.createQuery("SELECT r FROM reserva r", Reserva.class);
    return query.getResultList();
  }

  public void update(Reserva reserva) {
    entityManager.getTransaction().begin();
    entityManager.merge(reserva);
    entityManager.getTransaction().commit();
  }

  public void delete(Reserva reserva) {
    entityManager.getTransaction().begin();
    entityManager.remove(reserva);
    entityManager.getTransaction().commit();
  }
}
