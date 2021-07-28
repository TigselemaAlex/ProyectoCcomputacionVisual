/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kaise
 */
public class Inventario extends javax.swing.JInternalFrame {

    /**
     * Creates new form Inventario
     */
    public Inventario() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        cargarCmbBodegas();
        cargarTabla();
        
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
            String[] encabezado = {"Codigo", "Nombre", "Marca", "Cantidad", "Bodega"};
            jTblArticulos.setModel(new DefaultTableModel(null, encabezado) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            DefaultTableModel model = (DefaultTableModel) this.jTblArticulos.getModel();
            String sql = "";
            sql = "SELECT P.ID_PRO, P.NOM_PRO, P.MAR_PRO,P.EST_PRO,D.CAN_PRO_BOD, B.NOM_BOD FROM PRODUCTOS P, DETALLE_BODEGAS D, BODEGAS B WHERE P.ID_PRO = D.ID_PRO_BOD && D.ID_BOD_PER = B.ID_BOD";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String bodegaCmb = jCmbBodegas.getSelectedItem().toString();
            while (rs.next()) {
                String aux = rs.getString("P.EST_PRO");
                if (aux.equals("Y")) {
                    if (bodegaCmb.equals("Todas")) {
                        datos[0] = rs.getString("P.ID_PRO");
                        datos[1] = rs.getString("P.NOM_PRO");
                        datos[2] = rs.getString("P.MAR_PRO");
                        datos[3] = rs.getString("D.CAN_PRO_BOD");
                        datos[4] = rs.getString("B.NOM_BOD");
                        model.addRow(datos);
                    } else if(bodegaCmb.equals(rs.getString("B.NOM_BOD"))){
                        datos[0] = rs.getString("P.ID_PRO");
                        datos[1] = rs.getString("P.NOM_PRO");
                        datos[2] = rs.getString("P.MAR_PRO");
                        datos[3] = rs.getString("D.CAN_PRO_BOD");
                        datos[4] = rs.getString("B.NOM_BOD");
                        model.addRow(datos);
                    }
                }
            }
            this.jTblArticulos.setModel(model);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTblArticulos = new javax.swing.JTable();
        jBtnCerrar = new javax.swing.JButton();
        jCmbBodegas = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        jTblArticulos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTblArticulos);

        jBtnCerrar.setText("Cerrar");
        jBtnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCerrarActionPerformed(evt);
            }
        });

        jCmbBodegas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCmbBodegas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCmbBodegasItemStateChanged(evt);
            }
        });

        jLabel1.setText("Bodegas:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jCmbBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCmbBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnCerrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCmbBodegasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCmbBodegasItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_jCmbBodegasItemStateChanged

    private void jBtnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBtnCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCerrar;
    private javax.swing.JComboBox<String> jCmbBodegas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblArticulos;
    // End of variables declaration//GEN-END:variables
}
