/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import Codec.*;

/**
 *
 * @author THARU
 */
public class StartingWindow extends javax.swing.JFrame {

    /**
     * Creates new form StartingWindow
     */
    public StartingWindow() {
        initComponents();
        // set image as the icon 
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("cse.jpg")));
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
        welcomeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(725, 465));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(725, 465));
        jPanel1.setPreferredSize(new java.awt.Dimension(725, 465));
        jPanel1.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/logoUoM.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(90, 70, 560, 100);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/cse.jpg"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(50, 200, 120, 76);

        jLabel3.setFont(new java.awt.Font("Trajan Pro", 1, 60)); // NOI18N
        jLabel3.setText("SOFT CLICKER");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(190, 210, 490, 60);

        welcomeButton.setFont(new java.awt.Font("Trajan Pro", 1, 18)); // NOI18N
        welcomeButton.setText("WELCOME");
        welcomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                welcomeButtonActionPerformed(evt);
            }
        });
        jPanel1.add(welcomeButton);
        welcomeButton.setBounds(260, 310, 184, 45);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void welcomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_welcomeButtonActionPerformed
        /*
        * show wait dialog until connection check is done
        */
        final JOptionPane optionPane = new JOptionPane("Please wait...................", 
                JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        final JDialog waitingDialog = new JDialog();
        waitingDialog.setTitle("Processing");
        waitingDialog.setContentPane(optionPane);
        waitingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        waitingDialog.pack();
        waitingDialog.setLocationRelativeTo(null);

        SwingWorker connectionChecker = new SwingWorker<String, Void>() {

            @Override
            protected String doInBackground() {
                if(Extractor.isConnected() && Extractor.getConnectedSSID()!=null){
                    /*
                    * if computer is connected to correct accesspoint proceed to SSID window and close this window
                    */
//                    if(!WelcomeButtonController.isInternetReachable()){
                        waitingDialog.dispose();
                        SSIDWindow.start();
                        dispose();
//                    }
//                    else{
//                        waitingDialog.setVisible(false);
//                        JOptionPane.showMessageDialog(null, 
//                                "Please check your PC is connected to correct access point","Connection Error",
//                                JOptionPane.ERROR_MESSAGE);
//                    }
                }
                else{
                    /*
                    * if computer is not connected to correct AP then show connection error 
                    */
                    waitingDialog.setVisible(false);
                    JOptionPane.showMessageDialog(null, 
                            "Please check your PC is connected to correct access point","Connection Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }
         };
        connectionChecker.execute();
        waitingDialog.setVisible(true);        
    }//GEN-LAST:event_welcomeButtonActionPerformed
                                 

    /**
     * @param args the command line arguments
     */
    public static void start() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartingWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartingWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartingWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartingWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                StartingWindow startingWindow = new StartingWindow();
                startingWindow.setVisible(true);
                startingWindow.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton welcomeButton;
    // End of variables declaration//GEN-END:variables
}
