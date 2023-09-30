package com.alura.hotel.dao;

import com.alura.hotel.modelo.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class UsuarioDao {
  private EntityManager entityManager;

  public UsuarioDao(EntityManagerFactory entityManagerFactory) {
    this.entityManager = entityManagerFactory.createEntityManager();
  }

  public void close() {
    entityManager.close();
  }

  public void create(Usuario usuario) {
    entityManager.getTransaction().begin();
    entityManager.persist(usuario);
    entityManager.getTransaction().commit();
  }

  public Usuario findById(int id) {
    return entityManager.find(Usuario.class, id);
  }

  public boolean findByNamePassword(String name, String password) {
    TypedQuery<Long> query = entityManager.createQuery(
        "SELECT COUNT(u) FROM Usuario u WHERE u.nombre = :name AND u.password = :password",
        Long.class);
    query.setParameter("name", name);
    query.setParameter("password", password);
    Long count = query.getSingleResult();
    return count > 0;
  }

  public List<Usuario> findAll() {
    Query query = entityManager.createQuery("SELECT h FROM Usuario h", Usuario.class);
    return query.getResultList();
  }

  public void update(Usuario usuario) {
    entityManager.getTransaction().begin();
    entityManager.merge(usuario);
    entityManager.getTransaction().commit();
  }

  public void delete(Usuario usuario) {
    entityManager.getTransaction().begin();
    entityManager.remove(usuario);
    entityManager.getTransaction().commit();
  }
}
