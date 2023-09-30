package com.alura.hotel.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "forma_pago")
public class FormaPago {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String nombre;

  public FormaPago(String nombre) {
    this.nombre = nombre;
  }

  public FormaPago() {
    this.nombre = "";
  }

  public int getId() {
    return id;
  }

  //
  // public void setId(int id) {
  // this.id = id;
  // }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  // @Override
  // public String toString() {
  // return nombre;
  // }
}
