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

/** Reserva */
@Table(name = "reserva")
@Entity
public class Reserva {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "fechaEntrada")
  private Date fechaEntrada;

  @Column(name = "fechaSalida")
  private Date fechaSalida;

  private Float valor;

  @Column(name = "idReserva")
  private int idReserva;

  @ManyToOne
  @JoinColumn(name = "formaPago", referencedColumnName = "id")
  private FormaPago formaPago;

  public Reserva(Date fechaEntrada, Date fechaSalida, Float valor, FormaPago formaPago) {
    this.fechaEntrada = fechaEntrada;
    this.fechaSalida = fechaSalida;
    this.valor = valor;
    this.formaPago = formaPago;
  }

  public Reserva() {
    this.fechaEntrada = new Date();
    this.fechaSalida = new Date();
    this.valor = 0f;
  }

  public int getId() {
    return id;
  }

  // public void setId(int id) {
  // this.id = id;
  // }
  public Date getFechaEntrada() {
    return fechaEntrada;
  }

  public void setFechaEntrada(Date fechaEntrada) {
    this.fechaEntrada = fechaEntrada;
  }

  public Date getFechaSalida() {
    return fechaSalida;
  }

  public void setFechaSalida(Date fechaSalida) {
    this.fechaSalida = fechaSalida;
  }

  public Float getValor() {
    return valor;
  }

  public void setValor(Float valor) {
    this.valor = valor;
  }

  public FormaPago getFormaPago() {
    return formaPago;
  }

  public void setFormaPago(FormaPago formaPago) {
    this.formaPago = formaPago;
  }
}
