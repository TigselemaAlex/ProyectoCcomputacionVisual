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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kevinssg12
 */
public class IngresoP extends javax.swing.JInternalFrame {

    String[] encabezado;

    /**
     * Creates new form IngresoP
     */
    public IngresoP() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        cargarCmbBodegas();
        String[] encabezado = {"Codigo", "Nombre", "Marca", "Precio Unitario", "Cantidad", "Bodega"};
        jTbArticulos.setModel(new DefaultTableModel(null, encabezado) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        deshabilitarTodo();
        this.encabezado = encabezado;
    }

    private void cargarCmbBodegas() {
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            String sql = "";
            sql = "SELECT NOM_BOD FROM BODEGAS";
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            model.addElement("Seleccione una");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                model.addElement(rs.getString("NOM_BOD"));
            }
            this.jCmbBodegas.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(IngresoP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deshabilitarTxt() {
        jTxtCodigo.setEnabled(false);
        jTxtNombre.setEnabled(false);
        jTxtMarca.setEnabled(false);
        jFTxtPrecioU.setEnabled(false);
        jFTxtCantidad.setEnabled(false);
    }

    public void habilitarTxt() {
        jTxtCodigo.setEnabled(true);
        jTxtNombre.setEnabled(true);
        jTxtMarca.setEnabled(true);
        jFTxtPrecioU.setEnabled(true);
        jFTxtCantidad.setEnabled(true);
    }

    public void deshabilitarTodo() {
        deshabilitarTxt();
        this.jBtnNuevo.setEnabled(true);
        this.jBtnAgregar.setEnabled(false);
        this.jBtnBorrar.setEnabled(false);
        this.jBtnBorrarArticulo.setEnabled(false);
        this.jBtnCerrar.setEnabled(true);
        this.jBtnCancelar.setEnabled(false);
        this.jBtnEditar.setEnabled(false);
        this.jBtnGuardar.setEnabled(false);
        this.jBtnCambiar.setEnabled(false);
        this.jBtnCancelarEdicion.setEnabled(false);
        this.jCmbBodegas.setEnabled(false);
        this.jCmbBodegas.setSelectedIndex(0);
        System.out.println(jTbArticulos.getRowCount());
        if (jTbArticulos.getRowCount() > 0) {
            this.jBtnEditar.setEnabled(true);
            this.jBtnGuardar.setEnabled(true);
            this.jBtnBorrarArticulo.setEnabled(true);
        }
    }

    public void habilitarNuevo() {
        jTxtCodigo.setEnabled(true);
        jTxtNombre.setEnabled(true);
        jTxtMarca.setEnabled(true);
        jFTxtPrecioU.setEnabled(true);
        jFTxtCantidad.setEnabled(true);
        jBtnGuardar.setEnabled(false);
        this.jBtnCancelar.setEnabled(true);
        this.jCmbBodegas.setEnabled(true);
        this.jBtnAgregar.setEnabled(true);
        this.jBtnBorrar.setEnabled(true);
        this.jBtnNuevo.setEnabled(false);
    }

    public void borrarTxt() {
        jTxtCodigo.setText(null);
        jTxtNombre.setText(null);
        jTxtMarca.setText(null);
        jFTxtPrecioU.setText(null);
        jFTxtCantidad.setText(null);
    }

    public void agregarTabla() {
        String id, nombre, marca, precio, cantidad, bodega;
        id = jTxtCodigo.getText();
        nombre = jTxtNombre.getText();
        marca = jTxtMarca.getText();
        precio = jFTxtPrecioU.getText();
        cantidad = jFTxtCantidad.getText();
        bodega = jCmbBodegas.getSelectedItem().toString();
        if (!(id.equals("") || nombre.equals("") || marca.equals("") || precio.equals("")
                || cantidad.equals("") || cantidad.equals("0,00") || precio.equals("0") || bodega.equals("Seleccione una"))) {
            String[] registro = {id, nombre, marca, precio, cantidad, bodega};
            DefaultTableModel tabla = (DefaultTableModel) jTbArticulos.getModel();
            tabla.addRow(registro);
            jTbArticulos.setModel(tabla);
            borrarTxt();
            deshabilitarTodo();
        } else {
            JOptionPane.showMessageDialog(null, "Existen campos no llenos o inválidos", "Error al ingresar articulo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cerrar() {
        this.dispose();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTxtCodigo = new javax.swing.JTextField();
        jTxtNombre = new javax.swing.JTextField();
        jTxtMarca = new javax.swing.JTextField();
        jCmbBodegas = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jFTxtPrecioU = new javax.swing.JFormattedTextField();
        jFTxtCantidad = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jBtnAgregar = new javax.swing.JButton();
        jBtnCerrar = new javax.swing.JButton();
        jBtnNuevo = new javax.swing.JButton();
        jBtnBorrar = new javax.swing.JButton();
        jBtnGuardar = new javax.swing.JButton();
        jBtnCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTbArticulos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jBtnBorrarArticulo = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jBtnCambiar = new javax.swing.JButton();
        jBtnCancelarEdicion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Codigo: ");

        jLabel2.setText("Nombre: ");

        jLabel3.setText("Marca:");

        jLabel4.setText("Precio Unitario:");

        jLabel6.setText("Bodega:");

        jLabel7.setText("Cantidad:");

        jFTxtPrecioU.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jFTxtCantidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtMarca)
                            .addComponent(jTxtNombre)
                            .addComponent(jTxtCodigo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jFTxtPrecioU, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jFTxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jCmbBodegas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTxtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jFTxtPrecioU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jFTxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCmbBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jBtnAgregar.setText("Agregar");
        jBtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAgregarActionPerformed(evt);
            }
        });

        jBtnCerrar.setText("Cerrar");
        jBtnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCerrarActionPerformed(evt);
            }
        });

        jBtnNuevo.setText("Nuevo");
        jBtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNuevoActionPerformed(evt);
            }
        });

        jBtnBorrar.setText("Borrar");
        jBtnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBorrarActionPerformed(evt);
            }
        });

        jBtnGuardar.setText("Guardar");
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        jBtnCancelar.setText("Cancelar");
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jBtnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnNuevo)
                .addGap(18, 18, 18)
                .addComponent(jBtnBorrar)
                .addGap(18, 18, 18)
                .addComponent(jBtnAgregar)
                .addGap(18, 18, 18)
                .addComponent(jBtnCancelar)
                .addGap(18, 18, 18)
                .addComponent(jBtnGuardar)
                .addGap(18, 18, 18)
                .addComponent(jBtnCerrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTbArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTbArticulos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jBtnBorrarArticulo.setText("Borrar");
        jBtnBorrarArticulo.setMaximumSize(new java.awt.Dimension(119, 25));
        jBtnBorrarArticulo.setMinimumSize(new java.awt.Dimension(119, 25));
        jBtnBorrarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBorrarArticuloActionPerformed(evt);
            }
        });

        jBtnEditar.setText("Editar");
        jBtnEditar.setMaximumSize(new java.awt.Dimension(119, 25));
        jBtnEditar.setMinimumSize(new java.awt.Dimension(119, 25));
        jBtnEditar.setPreferredSize(new java.awt.Dimension(119, 25));
        jBtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditarActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Control de Productos");

        jBtnCambiar.setText("Cambiar");
        jBtnCambiar.setMaximumSize(new java.awt.Dimension(119, 25));
        jBtnCambiar.setMinimumSize(new java.awt.Dimension(119, 25));
        jBtnCambiar.setPreferredSize(new java.awt.Dimension(119, 25));
        jBtnCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCambiarActionPerformed(evt);
            }
        });

        jBtnCancelarEdicion.setText("Cancelar");
        jBtnCancelarEdicion.setMaximumSize(new java.awt.Dimension(119, 25));
        jBtnCancelarEdicion.setMinimumSize(new java.awt.Dimension(119, 25));
        jBtnCancelarEdicion.setPreferredSize(new java.awt.Dimension(119, 25));
        jBtnCancelarEdicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarEdicionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jBtnBorrarArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jBtnCancelarEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnCancelarEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jBtnBorrarArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnBorrarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBorrarArticuloActionPerformed
        borrarArticulo();
    }//GEN-LAST:event_jBtnBorrarArticuloActionPerformed

    private void borrarArticulo() {
        if (this.jTbArticulos.getSelectedRow() != -1) {
            DefaultTableModel model = (DefaultTableModel) jTbArticulos.getModel();
            model.removeRow(jTbArticulos.getSelectedRow());
            jTbArticulos.setModel(model);
        }
    }

    private void jBtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNuevoActionPerformed
        habilitarNuevo();
    }//GEN-LAST:event_jBtnNuevoActionPerformed

    private void jBtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAgregarActionPerformed
        agregarTabla();
    }//GEN-LAST:event_jBtnAgregarActionPerformed

    private void jBtnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCerrarActionPerformed
        cerrar();
    }//GEN-LAST:event_jBtnCerrarActionPerformed

    private void jBtnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBorrarActionPerformed
        borrarTxt();
    }//GEN-LAST:event_jBtnBorrarActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        guardarArticulos();
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        editarArticulo();

    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jBtnCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCambiarActionPerformed
        guardarCambioArticuloTabla();
    }//GEN-LAST:event_jBtnCambiarActionPerformed

    private void jBtnCancelarEdicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarEdicionActionPerformed
        this.deshabilitarTodo();
        borrarTxt();
        this.jTbArticulos.setEnabled(true);
    }//GEN-LAST:event_jBtnCancelarEdicionActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        borrarTxt();
        deshabilitarTodo();
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void editarArticulo() {
        if (this.jTbArticulos.getSelectedRow() != -1) {
            this.jTxtCodigo.setText(jTbArticulos.getValueAt(this.jTbArticulos.getSelectedRow(), 0).toString());
            this.jTxtMarca.setText(jTbArticulos.getValueAt(this.jTbArticulos.getSelectedRow(), 2).toString());
            this.jTxtNombre.setText(jTbArticulos.getValueAt(this.jTbArticulos.getSelectedRow(), 1).toString());
            this.jFTxtPrecioU.setText(jTbArticulos.getValueAt(this.jTbArticulos.getSelectedRow(), 3).toString());
            this.jFTxtCantidad.setText(jTbArticulos.getValueAt(this.jTbArticulos.getSelectedRow(), 4).toString());
            this.jCmbBodegas.setSelectedItem(jTbArticulos.getValueAt(this.jTbArticulos.getSelectedRow(), 5).toString());
            habilitarTxt();
            this.jTbArticulos.setEnabled(false);
            habilitarBotonEditar();
        }
    }

    private void habilitarBotonEditar() {
        this.jBtnCambiar.setEnabled(true);
        jBtnAgregar.setEnabled(false);
        jBtnBorrar.setEnabled(false);
        jBtnBorrarArticulo.setEnabled(false);
        jBtnGuardar.setEnabled(false);
        jBtnNuevo.setEnabled(false);
        jBtnEditar.setEnabled(false);
        jBtnCancelarEdicion.setEnabled(true);
        jCmbBodegas.setEnabled(true);
    }

    private void guardarArticulos() {
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        DefaultTableModel model = (DefaultTableModel) jTbArticulos.getModel();
        DefaultTableModel auxModel = new DefaultTableModel(null, encabezado);
        int aux = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                String sql = "";
                sql = "INSERT INTO PRODUCTOS (ID_PRO,NOM_PRO,MAR_PRO,PRE_UNI_PRO,EST_PRO)VALUES(?,?,?,?,?)";
                PreparedStatement psd = cn.prepareStatement(sql);
                psd.setString(1, model.getValueAt(i, 0).toString());
                psd.setString(2, model.getValueAt(i, 1).toString());
                psd.setString(3, model.getValueAt(i, 2).toString());
                psd.setDouble(4, Double.valueOf(model.getValueAt(i, 3).toString().replaceAll(",", ".")));
                psd.setString(5, "Y");
                int n = psd.executeUpdate();
                if (n > 0) {
                    aux++;
                }
                String idBodega = "";
                sql = "SELECT ID_BOD FROM BODEGAS WHERE NOM_BOD = '" + jTbArticulos.getValueAt(i, 5).toString() + "'";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs.next()) {
                    idBodega = rs.getString("ID_BOD");
                }
                sql = "INSERT INTO DETALLE_BODEGAS (ID_PRO_BOD, CAN_PRO_BOD,ID_BOD_PER) VALUES (?,?,?)";
                psd = cn.prepareStatement(sql);
                psd.setString(1, model.getValueAt(i, 0).toString());
                psd.setString(2, model.getValueAt(i, 4).toString());
                psd.setString(3, idBodega);
                psd.executeUpdate();

            } catch (SQLException ex) {
                String[] datos = {model.getValueAt(i, 0).toString(), model.getValueAt(i, 1).toString(),
                    model.getValueAt(i, 2).toString(), model.getValueAt(i, 3).toString(),
                    model.getValueAt(i, 4).toString(), model.getValueAt(i, 5).toString()};
                auxModel.addRow(datos);
            }
        }
        if (aux == model.getRowCount()) {
            jTbArticulos.setModel(new DefaultTableModel(null, encabezado));
        } else {
            JOptionPane.showMessageDialog(null, "Verifique el dódigo del producto", "Guardar articulos", JOptionPane.ERROR_MESSAGE);
            jTbArticulos.setModel(auxModel);
        }
    }

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAgregar;
    private javax.swing.JButton jBtnBorrar;
    private javax.swing.JButton jBtnBorrarArticulo;
    private javax.swing.JButton jBtnCambiar;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnCancelarEdicion;
    private javax.swing.JButton jBtnCerrar;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnNuevo;
    private javax.swing.JComboBox<String> jCmbBodegas;
    private javax.swing.JFormattedTextField jFTxtCantidad;
    private javax.swing.JFormattedTextField jFTxtPrecioU;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTbArticulos;
    private javax.swing.JTextField jTxtCodigo;
    private javax.swing.JTextField jTxtMarca;
    private javax.swing.JTextField jTxtNombre;
    // End of variables declaration//GEN-END:variables

    private void guardarCambioArticuloTabla() {
        String id, nombre, marca, precio, cantidad, bodega;
        id = jTxtCodigo.getText();
        nombre = jTxtNombre.getText();
        marca = jTxtMarca.getText();
        precio = jFTxtPrecioU.getText();
        cantidad = jFTxtCantidad.getText();
        bodega = jCmbBodegas.getSelectedItem().toString();
        if (!(id.equals("") || nombre.equals("") || marca.equals("") || precio.equals("")
                || cantidad.equals("") || cantidad.equals("0,00") || precio.equals("0") || bodega.equals("Seleccione una"))) {
            String[] registro = {id, nombre, marca, precio, cantidad, bodega};
            DefaultTableModel tabla = (DefaultTableModel) jTbArticulos.getModel();
            tabla.insertRow(this.jTbArticulos.getSelectedRow(), registro);
            tabla.removeRow(this.jTbArticulos.getSelectedRow() + 1);
            jTbArticulos.setModel(tabla);
            borrarTxt();
            deshabilitarTodo();
            this.jTbArticulos.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Existen campos no llenos o inválidos", "Error al ingresar articulo", JOptionPane.ERROR_MESSAGE);
        }
    }
}
