
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Daniel Tenecora
 */
public class TablaTransiciones extends javax.swing.JFrame {

    /**
     * Creates new form TablaTransiciones
     */
    int estados = Deterministico.numestados;
    String alfabeto = Deterministico.simbolos;
    static String cadena = "";
    static String resultado = "";
    DefaultTableModel model;

    public TablaTransiciones() {
        model = new DefaultTableModel();
        setTitle("TABLA DE TRANSICIONES");
        initComponents();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        mostrarMatriz(estados, alfabeto);
    }

//<-------------------------------------------------------------------------------------------------------------------------------------->
    // Funcion que genera la tabla de transicion
    public void mostrarMatriz(int estados, String alfabeto) {
        int filas = estados;
        int columnas = alfabeto.length();
        model.addColumn("E / A");
        for (int i = 0; i < columnas; i++) {
            model.addColumn(alfabeto.substring(i, i + 1));
        }
        for (int i = 0; i < filas; i++) {
            Vector fila = new Vector();
            fila.add("q" + i);
            for (int j = 1; j < alfabeto.length(); j++) {
                fila.add("");
            }
            model.addRow(fila);
        }
        TablaTrans.setModel(model);
    }

//<-------------------------------------------------------------------------------------------------------------------------------------->
    // Funcion que verifica que la tabla de transicion no sea nula    
    public boolean nulos(int estados, String alfabeto) {
        boolean nulo = false;
        int filas = estados;
        int columnas = alfabeto.length();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j <= columnas; j++) {
                if (columnas != 0) {
                    if (TablaTrans.getValueAt(i, j) == null) {
                        nulo = true;
                    }
                }
            }
        }
        return nulo;
    }

//<-------------------------------------------------------------------------------------------------------------------------------------->
    // Funcion que verifica que se ingresen estado validos
    public void validaestados(int estados, String alfabeto) {
        boolean bandera = false;
        int filas = estados;
        int columnas = alfabeto.length();
        String conjuntoestados = "";
        for (Object estado : Deterministico.estados) {
            conjuntoestados = conjuntoestados + estado;
        }
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j <= columnas; j++) {
                if (TablaTrans.getValueAt(i, j).toString().matches("[" + conjuntoestados + "]*") || TablaTrans.getValueAt(i, j).equals("-")) {
                    bandera = false;
                } else {
                    bandera = true;
                    break;
                }
            }
            if (bandera == true) {
                break;
            }
        }
        if (bandera == true) {
            JOptionPane.showMessageDialog(this, "Los estados de la tabla son incorrectos", "Error", 0);
        }
    }

//<-------------------------------------------------------------------------------------------------------------------------------------->
    // Funcion para el reconocimiento de cadenas
    public String evaluacion(int estados, String alfabeto, String cadena) {
        ArrayList cad = new ArrayList();
        boolean bandera = false;
        String resp = "";
        int aux1 = 0;
        int aux2 = 0;
        String auxacep = Deterministico.esinicial;
        int filas = estados;
        int columnas = alfabeto.length();
        for (int m = 0; m < cadena.length(); m++) {
            String escucha = Character.toString(cadena.charAt(m));
            cad.add(escucha);
        }
        for (int m = 0; m < cad.size(); m++) {
            for (int i = 1; i <= columnas; i++) {
                String col = TablaTrans.getColumnName(i);
                if (col.equals(cad.get(m))) {
                    aux2 = i;
                    for (int j = 0; j < filas; j++) {
                        if (auxacep.equals(TablaTrans.getValueAt(j, 0))) {
                            aux1 = j;
                        }
                    }
                }
            }
            if (auxacep.equals("-")) {
                auxacep = "-";
            } else {
                auxacep = (String) TablaTrans.getValueAt(aux1, aux2);
            }
        }

        for (Object finales : Deterministico.estadosaceptacion) {
            if (auxacep.equals(finales)) {
                bandera = true;
            }
        }
        if (bandera == true) {
            resp = "'" + cadena + "'" + " es reconocida por el automata";
        } else {
            resp = "'" + cadena + "'" + " no es reconocida por el automata";
        }
        return resp;
    }

//<-------------------------------------------------------------------------------------------------------------------------------------->
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        TablaTrans = new javax.swing.JTable();
        Aceptar = new javax.swing.JButton();
        Cerrar = new javax.swing.JButton();
        txtvalidar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TablaTrans = new javax.swing.JTable(){
            public boolean isCellEditable (int row,int colum){
                if (colum == 0){
                    return false;
                }
                return true;
            }
        };
        TablaTrans.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        TablaTrans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TablaTrans.getTableHeader().setResizingAllowed(false);
        TablaTrans.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(TablaTrans);

        Aceptar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Aceptar.setText("Aceptar");
        Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarActionPerformed(evt);
            }
        });

        Cerrar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Cerrar.setText("Cerrar");
        Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarActionPerformed(evt);
            }
        });

        txtvalidar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setText("Ingrese una cadena a evaluar:");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 3, 12)); // NOI18N
        jLabel2.setText("Para representar la inexistencia de transicion usar: -");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtvalidar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Aceptar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Cerrar)))
                        .addGap(85, 85, 85))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtvalidar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Aceptar)
                    .addComponent(Cerrar))
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarActionPerformed
        // TODO add your handling code here:
        cadena = txtvalidar.getText();
        if (cadena.equals("") || cadena.equals(" ")) {
            JOptionPane.showMessageDialog(this, "La Cadena no puede estar vacia", "Error", 0);
        } else if (cadena.matches("[" + Deterministico.simbolos + "]*")) {
            boolean verif = nulos(estados, alfabeto);
            if (verif == true) {
                JOptionPane.showMessageDialog(this, "La tabla de transicion esta vacia", "Error", 0);
            } else {
                validaestados(estados, alfabeto);
                resultado = evaluacion(estados, alfabeto, cadena);
                System.out.println(resultado);
                Resultado resul = new Resultado();
                resul.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "La cadena contiene sibolos diferentes al Alfabeto", "Error", 0);
        }

    }//GEN-LAST:event_AceptarActionPerformed

    private void CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_CerrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TablaTransiciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TablaTransiciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TablaTransiciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TablaTransiciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TablaTransiciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JButton Cerrar;
    private javax.swing.JTable TablaTrans;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtvalidar;
    // End of variables declaration//GEN-END:variables
}
