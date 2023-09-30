package com.alura.hotel.views;

import com.alura.hotel.dao.FormaPagoDao;
import com.alura.hotel.dao.HuespedDao;
import com.alura.hotel.dao.ReservaDao;
import com.alura.hotel.dao.UsuarioDao;
import com.alura.hotel.modelo.FormaPago;
import com.alura.hotel.modelo.Huesped;
import com.alura.hotel.modelo.Reserva;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

  private JPanel contentPane;
  private JTextField txtBuscar;
  private JTable tbHuespedes;
  private JTable tbReservas;
  private DefaultTableModel modelo;
  private DefaultTableModel modeloHuesped;
  private JLabel labelAtras;
  private JLabel labelExit;
  int xMouse, yMouse;

  private FormaPagoDao formaPagoDao;
  private HuespedDao huespedDao;
  private ReservaDao reservaDao;
  private UsuarioDao usuarioDao;

  /** Launch the application. */
  // public static void main(String[] args) {
  // EventQueue.invokeLater(
  // new Runnable() {
  // public void run() {
  // try {
  // Busqueda frame = new Busqueda();
  // frame.setVisible(true);
  // } catch (Exception e) {
  // e.printStackTrace();
  // }
  // }
  // });
  // }

  /** Create the frame. */
  public Busqueda(
      FormaPagoDao formaPagoDao,
      HuespedDao huespedDao,
      ReservaDao reservaDao,
      UsuarioDao usuarioDao) {
    this.formaPagoDao = formaPagoDao;
    this.huespedDao = huespedDao;
    this.reservaDao = reservaDao;
    this.usuarioDao = usuarioDao;

    setIconImage(
        Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 910, 571);
    contentPane = new JPanel();
    contentPane.setBackground(Color.WHITE);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    setLocationRelativeTo(null);
    setUndecorated(true);

    txtBuscar = new JTextField();
    txtBuscar.setBounds(536, 127, 193, 31);
    txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    contentPane.add(txtBuscar);
    txtBuscar.setColumns(10);

    JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
    lblNewLabel_4.setForeground(new Color(12, 138, 199));
    lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
    lblNewLabel_4.setBounds(331, 62, 280, 42);
    contentPane.add(lblNewLabel_4);

    JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
    panel.setBackground(new Color(12, 138, 199));
    panel.setFont(new Font("Roboto", Font.PLAIN, 16));
    panel.setBounds(20, 169, 865, 328);
    contentPane.add(panel);

    tbReservas = new JTable();
    tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
    modelo = (DefaultTableModel) tbReservas.getModel();
    modelo.addColumn("Numero de Reserva");
    modelo.addColumn("Fecha Check In");
    modelo.addColumn("Fecha Check Out");
    modelo.addColumn("Valor");
    modelo.addColumn("Forma de Pago");
    buscarReservas("");

    JScrollPane scroll_table = new JScrollPane(tbReservas);
    panel.addTab(
        "Reservas",
        new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")),
        scroll_table,
        null);
    scroll_table.setVisible(true);

    tbHuespedes = new JTable();
    tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
    modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
    modeloHuesped.addColumn("Número de Huesped");
    modeloHuesped.addColumn("Nombre");
    modeloHuesped.addColumn("Apellido");
    modeloHuesped.addColumn("Fecha de Nacimiento");
    modeloHuesped.addColumn("Nacionalidad");
    modeloHuesped.addColumn("Telefono");
    modeloHuesped.addColumn("Número de Reserva");
    buscarHuespedes("");
    JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
    panel.addTab(
        "Huéspedes",
        new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
        scroll_tableHuespedes,
        null);
    scroll_tableHuespedes.setVisible(true);

    JLabel lblNewLabel_2 = new JLabel("");
    lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
    lblNewLabel_2.setBounds(56, 51, 104, 107);
    contentPane.add(lblNewLabel_2);

    JPanel header = new JPanel();
    header.addMouseMotionListener(
        new MouseMotionAdapter() {
          @Override
          public void mouseDragged(MouseEvent e) {
            headerMouseDragged(e);
          }
        });
    header.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
            headerMousePressed(e);
          }
        });
    header.setLayout(null);
    header.setBackground(Color.WHITE);
    header.setBounds(0, 0, 910, 36);
    contentPane.add(header);

    JPanel btnAtras = new JPanel();
    btnAtras.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            MenuUsuario usuario = new MenuUsuario(formaPagoDao, huespedDao, reservaDao, usuarioDao);
            usuario.setVisible(true);
            dispose();
          }

          @Override
          public void mouseEntered(MouseEvent e) {
            btnAtras.setBackground(new Color(12, 138, 199));
            labelAtras.setForeground(Color.white);
          }

          @Override
          public void mouseExited(MouseEvent e) {
            btnAtras.setBackground(Color.white);
            labelAtras.setForeground(Color.black);
          }
        });
    btnAtras.setLayout(null);
    btnAtras.setBackground(Color.WHITE);
    btnAtras.setBounds(0, 0, 53, 36);
    header.add(btnAtras);

    labelAtras = new JLabel("<");
    labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
    labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
    labelAtras.setBounds(0, 0, 53, 36);
    btnAtras.add(labelAtras);

    JPanel btnexit = new JPanel();
    btnexit.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            MenuUsuario usuario = new MenuUsuario(formaPagoDao, huespedDao, reservaDao, usuarioDao);
            usuario.setVisible(true);
            dispose();
          }

          @Override
          public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este
            // cambiará de color
            btnexit.setBackground(Color.red);
            labelExit.setForeground(Color.white);
          }

          @Override
          public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este
            // volverá al estado original
            btnexit.setBackground(Color.white);
            labelExit.setForeground(Color.black);
          }
        });
    btnexit.setLayout(null);
    btnexit.setBackground(Color.WHITE);
    btnexit.setBounds(857, 0, 53, 36);
    header.add(btnexit);

    labelExit = new JLabel("X");
    labelExit.setHorizontalAlignment(SwingConstants.CENTER);
    labelExit.setForeground(Color.BLACK);
    labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
    labelExit.setBounds(0, 0, 53, 36);
    btnexit.add(labelExit);

    JSeparator separator_1_2 = new JSeparator();
    separator_1_2.setForeground(new Color(12, 138, 199));
    separator_1_2.setBackground(new Color(12, 138, 199));
    separator_1_2.setBounds(539, 159, 193, 2);
    contentPane.add(separator_1_2);

    JPanel btnbuscar = new JPanel();
    btnbuscar.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            if (panel.getSelectedIndex() == 0) {
              buscarReservas(txtBuscar.getText());
            } else if (panel.getSelectedIndex() == 1) {
              buscarHuespedes(txtBuscar.getText());
            }
          }
        });
    btnbuscar.setLayout(null);
    btnbuscar.setBackground(new Color(12, 138, 199));
    btnbuscar.setBounds(748, 125, 122, 35);
    btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    contentPane.add(btnbuscar);

    JLabel lblBuscar = new JLabel("BUSCAR");
    lblBuscar.setBounds(0, 0, 122, 35);
    btnbuscar.add(lblBuscar);
    lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
    lblBuscar.setForeground(Color.WHITE);
    lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

    JPanel btnEditar = new JPanel();
    btnEditar.setLayout(null);
    btnEditar.setBackground(new Color(12, 138, 199));
    btnEditar.setBounds(635, 508, 122, 35);
    btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnEditar.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            int selectedRowIndex = tbReservas.getSelectedRow();
            if (panel.getSelectedIndex() == 0) {
              Reserva reserva = getSelectedEditarReserva(selectedRowIndex);
              reservaDao.update(reserva);
            } else if (panel.getSelectedIndex() == 1) {
              Huesped huesped = getSelectedEditarHuesped(selectedRowIndex);
              huespedDao.update(huesped);
            }
          }
        });
    contentPane.add(btnEditar);

    JLabel lblEditar = new JLabel("EDITAR");
    lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
    lblEditar.setForeground(Color.WHITE);
    lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
    lblEditar.setBounds(0, 0, 122, 35);
    btnEditar.add(lblEditar);

    JPanel btnEliminar = new JPanel();
    btnEliminar.setLayout(null);
    btnEliminar.setBackground(new Color(12, 138, 199));
    btnEliminar.setBounds(767, 508, 122, 35);
    btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnEliminar.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            int selectedRowIndex = tbReservas.getSelectedRow();
            if (panel.getSelectedIndex() == 0) {
              Reserva reserva = getSelectedEliminarReserva(selectedRowIndex);
              reservaDao.delete(reserva);
            } else if (panel.getSelectedIndex() == 1) {
              Huesped huesped = getSelectedEliminarHuesped(selectedRowIndex);
              huespedDao.delete(huesped);
            }
          }
        });
    contentPane.add(btnEliminar);

    JLabel lblEliminar = new JLabel("ELIMINAR");
    lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
    lblEliminar.setForeground(Color.WHITE);
    lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
    lblEliminar.setBounds(0, 0, 122, 35);
    btnEliminar.add(lblEliminar);
    setResizable(false);
  }

  // Código que permite mover la ventana por la pantalla según la posición de
  // "x" y "y"
  private void headerMousePressed(java.awt.event.MouseEvent evt) {
    xMouse = evt.getX();
    yMouse = evt.getY();
  }

  private void headerMouseDragged(java.awt.event.MouseEvent evt) {
    int x = evt.getXOnScreen();
    int y = evt.getYOnScreen();
    this.setLocation(x - xMouse, y - yMouse);
  }

  private void buscarReservas(String searchedValue) {
    List<Reserva> reservasValues = reservaDao.findAll();
    List<Reserva> filteredReservas;
    if (searchedValue != "") {
      filteredReservas = reservasValues.stream()
          .filter(
              reserva -> reserva.getFormaPago().getNombre().contains(searchedValue)
                  || reserva.getFechaEntrada().toString().contains(searchedValue)
                  || reserva.getFechaSalida().toString().contains(searchedValue)
                  || reserva.getValor().toString().contains(searchedValue))
          .collect(Collectors.toList());
      // modelo.addColumn("Numero de Reserva");
      // modelo.addColumn("Fecha Check In");
      // modelo.addColumn("Fecha Check Out");
      // modelo.addColumn("Valor");
      // modelo.addColumn("Forma de Pago");

    } else {
      filteredReservas = reservasValues;
    }
    for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
      modelo.removeRow(i);
    }
    for (Reserva reser : filteredReservas) {
      modelo.addRow(
          new Object[] {
              reser.getId(), reser.getFechaEntrada().toString(),
              reser.getFechaSalida().toString(), reser.getValor().toString(),
              reser.getFormaPago().getNombre()
          });
    }
  }

  private void buscarHuespedes(String searchedValue) {
    // modeloHuesped.addColumn("Número de Huesped");
    // modeloHuesped.addColumn("Nombre");
    // modeloHuesped.addColumn("Apellido");
    // modeloHuesped.addColumn("Fecha de Nacimiento");
    // modeloHuesped.addColumn("Nacionalidad");
    // modeloHuesped.addColumn("Telefono");
    // modeloHuesped.addColumn("Número de Reserva");
    List<Huesped> huespedesValues = huespedDao.findAll();
    List<Huesped> filteredHuespedes;
    if (searchedValue != "") {
      filteredHuespedes = huespedesValues.stream()
          .filter(
              huesped -> huesped.getNombre().contains(searchedValue)
                  || String.valueOf(huesped.getId()).contains(searchedValue)
                  || huesped.getApellido().contains(searchedValue)
                  || huesped.getFechaNacimiento().toString().contains(searchedValue)
                  || huesped.getNacionalidad().contains(searchedValue)
                  || huesped.getTelefono().contains(searchedValue)
                  || String.valueOf(huesped.getReservas().getId()).contains(searchedValue))
          .collect(Collectors.toList());
    } else {
      filteredHuespedes = huespedesValues;
    }
    for (int i = modeloHuesped.getRowCount() - 1; i >= 0; i--) {
      modeloHuesped.removeRow(i);
    }
    for (Huesped huesped : filteredHuespedes) {
      modeloHuesped.addRow(
          new Object[] {
              huesped.getId(),
              huesped.getNombre(),
              huesped.getApellido(),
              huesped.getFechaNacimiento().toString(),
              huesped.getNacionalidad(),
              huesped.getTelefono(),
              String.valueOf(huesped.getReservas().getId())
          });
    }
  }

  private Reserva getSelectedEliminarReserva(int selectedRowIndex) {
    if (selectedRowIndex != -1) {
      String idValueString = (String) modelo.getValueAt(selectedRowIndex, 0);
      int idValue = Integer.parseInt(idValueString);
      return reservaDao.findById(idValue);
    }
    return null;
  }

  private Huesped getSelectedEliminarHuesped(int selectedRowIndex) {
    if (selectedRowIndex != -1) {
      String idValueString = (String) modeloHuesped.getValueAt(selectedRowIndex, 0);
      int idValue = Integer.parseInt(idValueString);
      return huespedDao.findById(idValue);
    }
    return null;
  }

  private Reserva getSelectedEditarReserva(int selectedRowIndex) {
    if (selectedRowIndex != -1) {
      String idValueString = (String) modelo.getValueAt(selectedRowIndex, 0);
      String fechaEntradaString = (String) modelo.getValueAt(selectedRowIndex, 1);
      String fechaSalidaString = (String) modelo.getValueAt(selectedRowIndex, 2);
      String valorString = (String) modelo.getValueAt(selectedRowIndex, 3);
      String formaPagoString = (String) modelo.getValueAt(selectedRowIndex, 4);

      int idValue = Integer.parseInt(idValueString);
      Date fechaEntrada = new Date(fechaEntradaString);
      Date fechaSalida = new Date(fechaSalidaString);
      Float valor = Float.parseFloat(valorString);
      FormaPago formaPago = formaPagoDao.findByName(formaPagoString).get(0);

      Reserva reservaModified = reservaDao.findById(idValue);
      reservaModified.setFechaEntrada(fechaEntrada);
      reservaModified.setFechaSalida(fechaSalida);
      reservaModified.setValor(valor);
      reservaModified.setFormaPago(formaPago);
      return reservaModified;
    }
    return null;
  }

  private Huesped getSelectedEditarHuesped(int selectedRowIndex) {
    if (selectedRowIndex != -1) {
      String idValueString = (String) modeloHuesped.getValueAt(selectedRowIndex, 0);
      String nombreString = (String) modeloHuesped.getValueAt(selectedRowIndex, 1);
      String apellidoString = (String) modeloHuesped.getValueAt(selectedRowIndex, 2);
      String fechaNacimientoString = (String) modeloHuesped.getValueAt(selectedRowIndex, 3);
      String nacionalidadString = (String) modeloHuesped.getValueAt(selectedRowIndex, 4);
      String telefonoString = (String) modeloHuesped.getValueAt(selectedRowIndex, 5);
      String reservaString = (String) modeloHuesped.getValueAt(selectedRowIndex, 6);

      int idValue = Integer.parseInt(idValueString);
      Date fechaNacimiento = new Date(fechaNacimientoString);
      Huesped huespedModified = huespedDao.findById(idValue);
      huespedModified.setNombre(nombreString);
      huespedModified.setApellido(apellidoString);
      huespedModified.setFechaNacimiento(fechaNacimiento);
      huespedModified.setNacionalidad(nacionalidadString);
      huespedModified.setTelefono(telefonoString);
      huespedModified.setReservas(reservaDao.findById(Integer.parseInt(reservaString)));
      return huespedModified;
    }
    return null;
  }
}
