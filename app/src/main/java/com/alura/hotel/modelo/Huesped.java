package com.alura.hotel.modelo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "huesped")
@Entity
public class Huesped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String nombre;
  private String apellido;
  private String nacionalidad;

  @Column(name = "fechaNacimiento")
  private Date fechaNacimiento;

  private String telefono;

  @ManyToOne
  @JoinColumn(name = "reserva", referencedColumnName = "id")
  private Reserva reserva;

  public Huesped(
      String nombre,
      String apellido,
      String nacionalidad,
      Date fechaNacimiento,
      String telefono,
      Reserva reservas) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.nacionalidad = nacionalidad;
    this.fechaNacimiento = fechaNacimiento;
    this.telefono = telefono;
    this.reserva = reservas;
  }

  public Huesped() {
    this.nombre = "";
    this.apellido = "";
    this.nacionalidad = "";
    this.fechaNacimiento = null;
    this.telefono = "";
  }

  public String getNacionalidad() {
    return nacionalidad;
  }

  public void setNacionalidad(String nacionalidad) {
    this.nacionalidad = nacionalidad;
  }

  public Reserva getReservas() {
    return reserva;
  }

  public void setReservas(Reserva reservas) {
    this.reserva = reservas;
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

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }
}
