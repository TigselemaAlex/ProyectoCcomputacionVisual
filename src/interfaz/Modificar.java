/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import interfaz.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author José Pazmiño
 */
public class Modificar extends javax.swing.JInternalFrame {

    /**
     * Creates new form Modificar
     */
    public Modificar() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        cargarCmbBodegas();
        desactivar();

        jTbArticulos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jTbArticulos.getSelectedRow() != -1) {
                    int fila = jTbArticulos.getSelectedRow();
                    jTxtNombre.setText(jTbArticulos.getValueAt(fila, 1).toString());
                    jTxtMarca.setText(jTbArticulos.getValueAt(fila, 2).toString());
                    jFTxtPrecioU.setText(jTbArticulos.getValueAt(fila, 3).toString().replace(".", ","));
                    jFTxtCantidad.setText(jTbArticulos.getValueAt(fila, 4).toString());
                    jCmbBodegas.setSelectedItem(jTbArticulos.getValueAt(fila, 5).toString());
                    habilitar();
                }
            }
        });
        cargarTabla();
    }

    private void habilitar() {
        this.jTxtMarca.setEnabled(true);
        this.jTxtNombre.setEnabled(true);
        this.jFTxtCantidad.setEnabled(true);
        this.jFTxtPrecioU.setEnabled(true);
        this.jBtnAceptar.setEnabled(true);
        this.jBtnCancelar.setEnabled(true);
        this.jCmbBodegas.setEnabled(true);
    }

    private void desactivar() {
        this.jTxtMarca.setText(null);
        this.jTxtNombre.setText(null);
        this.jFTxtCantidad.setText(null);
        this.jFTxtPrecioU.setText(null);

        this.jTxtMarca.setEnabled(false);
        this.jTxtNombre.setEnabled(false);
        this.jFTxtCantidad.setEnabled(false);
        this.jFTxtPrecioU.setEnabled(false);
        this.jBtnAceptar.setEnabled(false);
        this.jBtnCancelar.setEnabled(false);
        this.jCmbBodegas.setSelectedIndex(0);
        this.jCmbBodegas.setEnabled(false);
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
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void cargarTabla() {
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            String[] datos = new String[6];
            String[] encabezado = {"Codigo", "Nombre", "Marca", "Precio Unitario", "Cantidad", "Bodega"};
            jTbArticulos.setModel(new DefaultTableModel(null, encabezado) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            DefaultTableModel model = (DefaultTableModel) this.jTbArticulos.getModel();
            String sql = "";
            sql = "SELECT P.ID_PRO, P.NOM_PRO, P.MAR_PRO,P.PRE_UNI_PRO,P.EST_PRO,D.CAN_PRO_BOD, B.NOM_BOD FROM PRODUCTOS P, DETALLE_BODEGAS D, BODEGAS B WHERE P.ID_PRO = D.ID_PRO_BOD && D.ID_BOD_PER = B.ID_BOD";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String aux = rs.getString("P.EST_PRO");
                if (aux.equals("Y")) {
                    datos[0] = rs.getString("P.ID_PRO");
                    datos[1] = rs.getString("P.NOM_PRO");
                    datos[2] = rs.getString("P.MAR_PRO");
                    datos[3] = rs.getString("P.PRE_UNI_PRO");
                    datos[4] = rs.getString("D.CAN_PRO_BOD");
                    datos[5] = rs.getString("B.NOM_BOD");
                    model.addRow(datos);
                }
            }
            this.jTbArticulos.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTxtNombre = new javax.swing.JTextField();
        jTxtMarca = new javax.swing.JTextField();
        jCmbBodegas = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jFTxtPrecioU = new javax.swing.JFormattedTextField();
        jFTxtCantidad = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTbArticulos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jBtnAceptar = new javax.swing.JButton();
        jBtnCancelar = new javax.swing.JButton();
        jBtnCerrar = new javax.swing.JButton();

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtMarca)
                            .addComponent(jTxtNombre)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jFTxtPrecioU, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 93, Short.MAX_VALUE))))
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
                .addContainerGap(13, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jBtnAceptar.setText("Aceptar");
        jBtnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAceptarActionPerformed(evt);
            }
        });

        jBtnCancelar.setText("Cancelar");
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });

        jBtnCerrar.setText("Cerrar");
        jBtnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(jBtnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnAceptar)
                .addGap(18, 18, 18)
                .addComponent(jBtnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnCerrar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAceptarActionPerformed
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String nombre, marca, precio, cantidad, bodega;
        nombre = jTxtNombre.getText();
        marca = jTxtMarca.getText();
        precio = jFTxtPrecioU.getText().replaceAll(",", ".");
        cantidad = jFTxtCantidad.getText();
        bodega = jCmbBodegas.getSelectedItem().toString();
        if (!(nombre.equals("") || marca.equals("") || precio.equals("")
                || cantidad.equals("") || cantidad.equals("0") || precio.equals("0") || bodega.equals("Seleccione una"))) {
            try {
                String sql = "";
                sql = "UPDATE PRODUCTOS SET NOM_PRO = '" + nombre + "', MAR_PRO='" + marca + "',PRE_UNI_PRO='" + precio + "' WHERE ID_PRO ='" + jTbArticulos.getValueAt(jTbArticulos.getSelectedRow(), 0).toString() + "'";
                PreparedStatement psd = cn.prepareStatement(sql);
                psd.executeUpdate();
                String idBodega = "";
                sql = "SELECT ID_BOD FROM BODEGAS WHERE NOM_BOD = '" + jCmbBodegas.getSelectedItem().toString() + "'";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs.next()) {
                    idBodega = rs.getString("ID_BOD");
                }
                sql = "UPDATE DETALLE_BODEGAS SET CAN_PRO_BOD='" + cantidad + "', ID_BOD_PER='" + idBodega + "' WHERE ID_PRO_BOD = '" + jTbArticulos.getValueAt(jTbArticulos.getSelectedRow(), 0).toString() + "'";
                psd = cn.prepareStatement(sql);
                psd.executeUpdate();
                cargarTabla();
                desactivar();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Existen campos no llenos o inválidos", "Error al ingresar articulo", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jBtnAceptarActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        desactivar();
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jBtnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBtnCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAceptar;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnCerrar;
    private javax.swing.JComboBox<String> jCmbBodegas;
    private javax.swing.JFormattedTextField jFTxtCantidad;
    private javax.swing.JFormattedTextField jFTxtPrecioU;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTbArticulos;
    private javax.swing.JTextField jTxtMarca;
    private javax.swing.JTextField jTxtNombre;
    // End of variables declaration//GEN-END:variables
}
