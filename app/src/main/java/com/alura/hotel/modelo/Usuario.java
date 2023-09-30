package com.alura.hotel.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** Usuario */
@Table(name = "usuario")
@Entity
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String nombre;
  private String apellido;
  private String password;

  public Usuario(String nombre, String apellido, String password) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.password = password;
  }

  public Usuario() {
    this.nombre = "";
    this.apellido = "";
    this.password = "";
  }

  public int getId() {
    return id;
  }

  // public void setId(int id) {
  // this.id = id;
  // }
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
