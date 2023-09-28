package com.alura.hotel.modelo;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
  private Date fechaNacimiento;
  private String telefono;

  @OneToMany
  private List<Reserva> reservas;

  public Huesped(String nombre, String apellido, String nacionalidad,
      Date fechaNacimiento, String telefono,
      List<Reserva> reservas) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.nacionalidad = nacionalidad;
    this.fechaNacimiento = fechaNacimiento;
    this.telefono = telefono;
    this.reservas = reservas;
  }

  public String getNacionalidad() {
    return nacionalidad;
  }

  public void setNacionalidad(String nacionalidad) {
    this.nacionalidad = nacionalidad;
  }

  public List<Reserva> getReservas() {
    return reservas;
  }

  public void setReservas(List<Reserva> reservas) {
    this.reservas = reservas;
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
