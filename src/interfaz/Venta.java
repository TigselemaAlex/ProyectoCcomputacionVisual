/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kaise
 */
public class Venta extends javax.swing.JInternalFrame {

    /**
     * Creates new form Venta
     */
    private String idUsu;
    private String nomUsu;
    private String apeUsu;

    public Venta(String idUsu, String nomUsu, String apeUsu) {
        initComponents();
        deshabilitarVenta();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.idUsu = idUsu;
        this.nomUsu = nomUsu;
        this.apeUsu = apeUsu;
        cargarCmbBodegas();
        String[] encabezado = {"Codigo", "Nombre", "Marca", "Cantidad", "Subtotal"};
        jTblProdVenta.setModel(new DefaultTableModel(null, encabezado) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        numFactura();
    }

    private void numFactura() {
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            String sql = "";
            sql = "SELECT MAX(ID_FAC) FROM FACTURAS";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                jTxtNumFact.setText(((Integer) (rs.getInt("MAX(ID_FAC)") + 1)).toString());
            } else {
                jTxtNumFact.setText("1");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarCmbBodegas() {
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            String sql = "";
            sql = "SELECT NOM_BOD FROM BODEGAS";
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            model.addElement("Todas");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                model.addElement(rs.getString("NOM_BOD"));
            }
            this.jCmbBodegas.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void cargarTabla() {
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            String[] datos = new String[6];
            String[] encabezado = {"Codigo", "Nombre", "Marca", "Precio Unitario", "Cantidad", "Bodega"};
            jTblArticulos.setModel(new DefaultTableModel(null, encabezado) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            DefaultTableModel model = (DefaultTableModel) this.jTblArticulos.getModel();
            String sql = "";
            sql = "SELECT P.ID_PRO, P.NOM_PRO, P.MAR_PRO,P.EST_PRO,P.PRE_UNI_PRO,D.CAN_PRO_BOD, B.NOM_BOD FROM PRODUCTOS P, DETALLE_BODEGAS D, BODEGAS B WHERE P.ID_PRO = D.ID_PRO_BOD && D.ID_BOD_PER = B.ID_BOD";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String bodegaCmb = jCmbBodegas.getSelectedItem().toString();
            while (rs.next()) {
                String aux = rs.getString("P.EST_PRO");
                String cant = rs.getString("D.CAN_PRO_BOD");
                if (aux.equals("Y") && !cant.equals("0")) {
                    if (bodegaCmb.equals("Todas")) {
                        datos[0] = rs.getString("P.ID_PRO");
                        datos[1] = rs.getString("P.NOM_PRO");
                        datos[2] = rs.getString("P.MAR_PRO");
                        datos[3] = rs.getString("P.PRE_UNI_PRO");
                        datos[4] = rs.getString("D.CAN_PRO_BOD");
                        datos[5] = rs.getString("B.NOM_BOD");
                        model.addRow(datos);
                    } else if (bodegaCmb.equals(rs.getString("B.NOM_BOD"))) {
                        datos[0] = rs.getString("P.ID_PRO");
                        datos[1] = rs.getString("P.NOM_PRO");
                        datos[2] = rs.getString("P.MAR_PRO");
                        datos[3] = rs.getString("P.PRE_UNI_PRO");
                        datos[4] = rs.getString("D.CAN_PRO_BOD");
                        datos[5] = rs.getString("B.NOM_BOD");
                        model.addRow(datos);
                    }
                }
            }
            this.jTblArticulos.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void buscarCliente() {
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String id = jTxtBusquedaCedula.getText();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Llene el campo de búsqueda", "Error al buscar Cliente", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String sql = "";
                sql = "SELECT * FROM CLIENTES WHERE ID_CLI='" + id + "'";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs.next()) {
                    jTxtCedula.setText(id);
                    jTxtNombre.setText(rs.getString("NOM_CLI"));
                    jTxtApellido.setText(rs.getString("APE_CLI"));
                    jTxtDireccion.setText(rs.getString("DIR_CLI"));
                    jTxtTelefono.setText(rs.getString("TEL_CLI"));
                    habilitarVenta();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "La cédula ingresada no es válida", "Error al buscar Cliente", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void habilitarVenta() {
        jBtnAgregar.setEnabled(true);
        jTblArticulos.setEnabled(true);
        jTblProdVenta.setEnabled(true);
        jFtxtCantidad.setEnabled(true);
        jCmbBodegas.setEnabled(true);
    }

    private void deshabilitarVenta() {
        jBtnAgregar.setEnabled(false);
        jBtnEliminar.setEnabled(false);
        jBtnFacturar.setEnabled(false);
        jTblArticulos.setEnabled(false);
        jTblProdVenta.setEnabled(false);
        jTxtTotal.setEnabled(false);
        jFtxtCantidad.setEnabled(false);
        jCmbBodegas.setEnabled(false);
    }

    private void borrarTxt() {
        this.jTxtApellido.setText(null);
        this.jTxtBusquedaCedula.setText(null);
        this.jTxtCedula.setText(null);
        this.jTxtDireccion.setText(null);
        this.jTxtNombre.setText(null);
        this.jTxtTelefono.setText(null);
        this.jTxtTotal.setText(null);
        this.jFtxtCantidad.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblArticulos = new javax.swing.JTable();
        jCmbBodegas = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jFtxtCantidad = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jTxtTotal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblProdVenta = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jBtnAgregar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jBtnEliminar = new javax.swing.JButton();
        jBtnFacturar = new javax.swing.JButton();
        jBtnCerrar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTxtBusquedaCedula = new javax.swing.JTextField();
        jBtnBuscar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTxtNombre = new javax.swing.JTextField();
        jTxtDireccion = new javax.swing.JTextField();
        jTxtApellido = new javax.swing.JTextField();
        jTxtNumFact = new javax.swing.JTextField();
        jTxtTelefono = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTxtCedula = new javax.swing.JTextField();

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTblArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTblArticulos);

        jCmbBodegas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCmbBodegas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCmbBodegasActionPerformed(evt);
            }
        });

        jLabel1.setText("Bodegas:");

        jLabel10.setText("Cantidad: ");

        jFtxtCantidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel11.setText("Total:");

        jTblProdVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTblProdVenta);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("VISTA PREVIA DE PRODUCTOS A VENDER");

        jBtnAgregar.setText("Agregar");
        jBtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAgregarActionPerformed(evt);
            }
        });

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Control Factura");

        jBtnEliminar.setText("Eliminar");
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
            }
        });

        jBtnFacturar.setText("Facturar");
        jBtnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnFacturarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jBtnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(jBtnFacturar)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnEliminar)
                    .addComponent(jBtnFacturar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBtnCerrar.setText("Cerrar");
        jBtnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTxtTotal)
                            .addComponent(jBtnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(26, 26, 26)
                                .addComponent(jCmbBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFtxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBtnAgregar)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jCmbBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jFtxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnAgregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnCerrar)))
                .addContainerGap())
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CLIENTE");

        jLabel3.setText("Cedula");

        jBtnBuscar.setText("Buscar");
        jBtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarActionPerformed(evt);
            }
        });

        jLabel4.setText("Nombre:");

        jLabel5.setText("Apellido: ");

        jLabel6.setText("Direccion:");

        jLabel7.setText("Telefono:");

        jLabel8.setText("Factura N°: ");

        jTxtNombre.setEnabled(false);

        jTxtDireccion.setEnabled(false);

        jTxtApellido.setEnabled(false);

        jTxtNumFact.setEnabled(false);

        jTxtTelefono.setEnabled(false);

        jLabel14.setText("Cedula:");

        jTxtCedula.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(8, 8, 8)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTxtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addComponent(jTxtDireccion))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTxtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(jTxtTelefono))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jTxtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jTxtNumFact, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtBusquedaCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jBtnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jTxtNumFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTxtBusquedaCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtnBuscar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(jTxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTxtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jTxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jTxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarActionPerformed
        buscarCliente();
    }//GEN-LAST:event_jBtnBuscarActionPerformed

    private void jCmbBodegasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCmbBodegasActionPerformed
        cargarTabla();
    }//GEN-LAST:event_jCmbBodegasActionPerformed

    private void jBtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAgregarActionPerformed
        agregarArticuloVenta();
        verTotal();
    }//GEN-LAST:event_jBtnAgregarActionPerformed

    private void jBtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActionPerformed
        eliminarProVenta();
        verTotal();
    }//GEN-LAST:event_jBtnEliminarActionPerformed

    private void jBtnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBtnCerrarActionPerformed

    private void jBtnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnFacturarActionPerformed
        facturar();
    }//GEN-LAST:event_jBtnFacturarActionPerformed

    private void facturar() {
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            String sql = "";
            sql = "INSERT INTO FACTURAS (ID_CLI_FAC, ID_VEN_FAC, FEC_FAC, TOT_FAC)VALUES(?,?,?,?)";
            PreparedStatement psd = cn.prepareStatement(sql);
            psd.setString(1, jTxtCedula.getText());
            psd.setString(2, idUsu);
            psd.setDate(3, new java.sql.Date(new Date().getTime()));
            psd.setDouble(4, Double.valueOf(jTxtTotal.getText()));
            int aux = 0;
            int n = psd.executeUpdate();
            if (n > 0) {
                for (int i = 0; i < jTblProdVenta.getRowCount(); i++) {
                    String idPro = jTblProdVenta.getValueAt(i, 0).toString();
                    Integer canProVen = Integer.valueOf(jTblProdVenta.getValueAt(i, 3).toString());
                    Integer idFactPer = Integer.valueOf(jTxtNumFact.getText());
                    sql = "INSERT INTO DETALLE_FACTURA (ID_PRO_FAC,CAN_PRO_VEN_FAC,ID_FAC_PER)VALUES (?,?,?)";
                    psd = cn.prepareStatement(sql);
                    psd.setString(1, idPro);
                    psd.setInt(2, canProVen);
                    psd.setInt(3, idFactPer);
                    int m = psd.executeUpdate();
                    if (m > 0) {
                        aux++;
                    } else {
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "La factura con erroes", "Error al crear la factura", JOptionPane.ERROR_MESSAGE);
            }
            if (aux == jTblProdVenta.getRowCount() && n > 0) {
                JOptionPane.showMessageDialog(null, "La factura se creo con éxito", "Generar factura", JOptionPane.INFORMATION_MESSAGE);
                porDefecto();
                //REPORTE
            } else {
                JOptionPane.showMessageDialog(null, "La factura con erroes", "Error al crear la factura", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void porDefecto() {
        borrarTxt();
        numFactura();
        deshabilitarVenta();
        String[] encabezado = {"Codigo", "Nombre", "Marca", "Cantidad", "Subtotal"};
        jTblProdVenta.setModel(new DefaultTableModel(null, encabezado) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        String[] encabezado1 = {"Codigo", "Nombre", "Marca", "Precio Unitario", "Cantidad", "Bodega"};
        jTblArticulos.setModel(new DefaultTableModel(null, encabezado1) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    private void eliminarProVenta() {
        int fila = jTblProdVenta.getSelectedRow();
        if (fila != -1) {
            DefaultTableModel model = (DefaultTableModel) jTblProdVenta.getModel();
            Integer cantProVen = Integer.valueOf(model.getValueAt(fila, 3).toString());
            try {
                Conexion cc = new Conexion();
                Connection cn = cc.conectar();
                String sql ="";
                sql = "SELECT CAN_PRO_BOD FROM DETALLE_BODEGAS WHERE ID_PRO_BOD='"+model.getValueAt(fila, 0).toString()+"'";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                Integer cantProBod = 0;
                if(rs.next()){
                    cantProBod = Integer.valueOf(rs.getString("CAN_PRO_BOD"));
                }
                Integer cantPro = cantProVen + cantProBod;
                sql = "UPDATE DETALLE_BODEGAS SET CAN_PRO_BOD=" + cantPro + " WHERE ID_PRO_BOD='" + model.getValueAt(fila, 0).toString() + "'";
                PreparedStatement psd = cn.prepareStatement(sql);
                psd.executeUpdate();
                cargarTabla();
            } catch (SQLException ex) {
                Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            }
            model.removeRow(fila);
            if (jTblProdVenta.getRowCount() == 0) {
                jBtnEliminar.setEnabled(false);
                jBtnFacturar.setEnabled(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun articulo", "Error al eliminar el articulo de la previsualizacion", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void agregarArticuloVenta() {
        if (jFtxtCantidad.getText().isEmpty() || Integer.valueOf(jFtxtCantidad.getText()) <= 0 || this.jTblArticulos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Verifique la cantidad o selecccione algun artículo", "Error al registrar articulo a la previsualicacion", JOptionPane.ERROR_MESSAGE);
        } else {
            int fila = this.jTblArticulos.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) jTblProdVenta.getModel();
            String idPro = jTblArticulos.getValueAt(fila, 0).toString();
            String nomPro = jTblArticulos.getValueAt(fila, 1).toString();
            String marPro = jTblArticulos.getValueAt(fila, 2).toString();
            Integer canProAlma = Integer.valueOf(jTblArticulos.getValueAt(fila, 4).toString());
            Integer canPro = Integer.valueOf(jFtxtCantidad.getText());
            Double prePro = Double.valueOf(jTblArticulos.getValueAt(fila, 3).toString());
            if (canProAlma >= canPro) {
                try {
                    DecimalFormat format = new DecimalFormat("#.00");
                    Double subTotal = (double) canPro * prePro;
                    subTotal = Double.valueOf(format.format(subTotal).replaceAll(",", "."));
                    Integer rest = canProAlma - canPro;
                    String[] datos = {idPro, nomPro, marPro, canPro.toString(), subTotal.toString()};
                    model.addRow(datos);
                    jTblProdVenta.setModel(model);
                    Conexion cc = new Conexion();
                    Connection cn = cc.conectar();
                    String sql = "";
                    sql = "UPDATE DETALLE_BODEGAS SET CAN_PRO_BOD=" + rest + " WHERE ID_PRO_BOD='" + idPro + "'";
                    PreparedStatement psd = cn.prepareStatement(sql);
                    psd.executeUpdate();
                    if (jTblProdVenta.getRowCount() > 0) {
                        jBtnEliminar.setEnabled(true);
                        jBtnFacturar.setEnabled(true);
                    }
                    cargarTabla();
                    this.jFtxtCantidad.setText(null);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cantidad insuficiente en stock MAX(" + canProAlma + ")", "Error al registrar articulo a la previsualicacion", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void verTotal() {
        Double total = 0d;
        for (int i = 0; i < jTblProdVenta.getRowCount(); i++) {
            total += Double.valueOf(jTblProdVenta.getValueAt(i, 4).toString());
        }
        DecimalFormat format = new DecimalFormat("#.00");
        total = Double.valueOf(format.format(total).replaceAll(",", "."));
        this.jTxtTotal.setText(total.toString());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAgregar;
    private javax.swing.JButton jBtnBuscar;
    private javax.swing.JButton jBtnCerrar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnFacturar;
    private javax.swing.JComboBox<String> jCmbBodegas;
    private javax.swing.JFormattedTextField jFtxtCantidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTblArticulos;
    private javax.swing.JTable jTblProdVenta;
    private javax.swing.JTextField jTxtApellido;
    private javax.swing.JTextField jTxtBusquedaCedula;
    private javax.swing.JTextField jTxtCedula;
    private javax.swing.JTextField jTxtDireccion;
    private javax.swing.JTextField jTxtNombre;
    private javax.swing.JTextField jTxtNumFact;
    private javax.swing.JTextField jTxtTelefono;
    private javax.swing.JTextField jTxtTotal;
    // End of variables declaration//GEN-END:variables
}
