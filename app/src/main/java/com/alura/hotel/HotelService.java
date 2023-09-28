package com.alura.hotel;

import com.alura.hotel.dao.FormaPagoDao;
import com.alura.hotel.dao.HuespedDao;
import com.alura.hotel.dao.ReservaDao;
import com.alura.hotel.dao.UsuarioDao;
import com.alura.hotel.views.MenuPrincipal;
import java.awt.EventQueue;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HotelService {
  private FormaPagoDao formaPagoDao;
  private HuespedDao huespedDao;
  private ReservaDao reservaDao;
  private UsuarioDao usuarioDao;
  private EntityManagerFactory entityManagerFactory;

  public HotelService() {
    entityManagerFactory = Persistence.createEntityManagerFactory("hotel_alura_challenge");

    formaPagoDao = new FormaPagoDao(entityManagerFactory);
    huespedDao = new HuespedDao(entityManagerFactory);
    reservaDao = new ReservaDao(entityManagerFactory);
    usuarioDao = new UsuarioDao(entityManagerFactory);
  }

  public void close() {
    formaPagoDao.close();
    huespedDao.close();
    reservaDao.close();
    usuarioDao.close();
  }

  public void launchUI() {
    /** Launch the application. */
    // public static void main(String[] args) {
    // EventQueue.invokeLater(
    // new Runnable() {
    // public void run() {
    // try {
    // Login frame = new Login(usuarioDao);
    // frame.setVisible(true);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    // });
    // }
    EventQueue.invokeLater(
        new Runnable() {
          public void run() {
            try {
              MenuPrincipal frame = new MenuPrincipal(
                  formaPagoDao, huespedDao,
                  reservaDao, usuarioDao);
              frame.setVisible(true);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        });
  }
}
