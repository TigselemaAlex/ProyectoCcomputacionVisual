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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Kevinssg12
 */
public class ReporteCon extends javax.swing.JFrame {

    /**
     * Creates new form ReporteCon
     */
    public ReporteCon() {
        initComponents();
        //((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
    }

    public void reporteMasVendido() {
        try {
            Conexion cc = new Conexion();
            //Connection cn = cc.conectar();
            //Map id_producto = new HashMap();
            //id_producto.put("ID_PRO", buscaMasVendido());
            JasperReport reporte = JasperCompileManager.compileReport("src/reporte/masVendido.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, cc.conectar());
            JasperViewer.viewReport(print, false);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void reporteNoHayBodega() {
        try {
            Conexion cc = new Conexion();
            //Connection cn = cc.conectar();
            //Map id_producto = new HashMap();
            //id_producto.put("ID_PRO", buscaMasVendido());
            JasperReport reporte = JasperCompileManager.compileReport("src/reporte/noHayBodega.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, cc.conectar());
            JasperViewer.viewReport(print, false);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void reporteMasEntreSemana() {
        try {
            Conexion cc = new Conexion();
            //Connection cn = cc.conectar();
            //Map id_producto = new HashMap();
            //id_producto.put("ID_PRO", buscaMasVendido());
            JasperReport reporte = JasperCompileManager.compileReport("src/reporte/masEntreSemana.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, cc.conectar());
            JasperViewer.viewReport(print, false);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public String buscaMasVendido() {
        String[] registro = new String[3];
        try {

            Conexion cnx = new Conexion();
            Connection cn = cnx.conectar();
            String sql = "";
            sql = "SELECT P.ID_PRO, P.NOM_PRO, P.MAR_PRO, SUM(D.CAN_PRO_VEN_FAC) FROM PRODUCTOS P, detalle_factura D WHERE P.ID_PRO =( SELECT ID_PRO_FAC FROM detalle_factura GROUP BY ID_PRO_FAC HAVING SUM(CAN_PRO_VEN_FAC) = (SELECT MAX(B.TOT_SUM) from (SELECT SUM(d.CAN_PRO_VEN_FAC) AS TOT_SUM FROM detalle_factura d GROUP BY d.ID_PRO_FAC) AS B) ) AND P.ID_PRO = D.ID_PRO_FAC GROUP BY P.ID_PRO";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registro[0] = rs.getString("P.ID_PRO");
                //registro[1] = rs.getString("P.MAR_PRO");
                //registro[2] = rs.getString("SUM(D.CAN_PRO_VEN_FAC)");
                //System.out.println(registro[0].toString());
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return registro[0];
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
        jBtnMasVendido = new javax.swing.JButton();
        jBtnFaltaBodega = new javax.swing.JButton();
        jBtnMasVendidoSemana = new javax.swing.JButton();
        jBtnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(603, 603));

        jBtnMasVendido.setText("Mas Vendido");
        jBtnMasVendido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnMasVendidoActionPerformed(evt);
            }
        });

        jBtnFaltaBodega.setText("Productos Agotados");
        jBtnFaltaBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnFaltaBodegaActionPerformed(evt);
            }
        });

        jBtnMasVendidoSemana.setText("Mas Vendido entre Semana");
        jBtnMasVendidoSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnMasVendidoSemanaActionPerformed(evt);
            }
        });

        jBtnSalir.setText("Cerrar");
        jBtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnMasVendido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnFaltaBodega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnMasVendidoSemana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnMasVendido)
                .addGap(18, 18, 18)
                .addComponent(jBtnFaltaBodega)
                .addGap(18, 18, 18)
                .addComponent(jBtnMasVendidoSemana)
                .addGap(18, 18, 18)
                .addComponent(jBtnSalir)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(210, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnMasVendidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnMasVendidoActionPerformed
        reporteMasVendido();
    }//GEN-LAST:event_jBtnMasVendidoActionPerformed

    private void jBtnFaltaBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnFaltaBodegaActionPerformed
        reporteNoHayBodega();
    }//GEN-LAST:event_jBtnFaltaBodegaActionPerformed

    private void jBtnMasVendidoSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnMasVendidoSemanaActionPerformed
        reporteMasEntreSemana();
    }//GEN-LAST:event_jBtnMasVendidoSemanaActionPerformed

    private void jBtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBtnSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnFaltaBodega;
    private javax.swing.JButton jBtnMasVendido;
    private javax.swing.JButton jBtnMasVendidoSemana;
    private javax.swing.JButton jBtnSalir;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}