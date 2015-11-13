package view;

import controller.ReceiveAnswerButtonController;
import controller.SaveAnswersController;
import controller.StartServerButtonController;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author THARU
 */
public class ServerWindow extends javax.swing.JFrame {

    StartServerButtonController broadcastController;
    ReceiveAnswerButtonController unicastController;
    /**
     * Creates new form 
     */
    public ServerWindow() {
        super("Soft Clicker - Dept. of Computer Science and Engineering, University of Moratuwa");
        initComponents();   
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("cse.jpg")));
        /*
        * initiate
        */
        broadcastController = new StartServerButtonController();
        unicastController = new ReceiveAnswerButtonController(this);
        startServerButton.setEnabled(false);
        receiveAnswersButton.setEnabled(false);
        count.setText("0");
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
        startServerButton = new javax.swing.JButton();
        receiveAnswersButton = new javax.swing.JButton();
        sumaryButton = new javax.swing.JButton();
        count = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        newQuestion = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(725, 465));
        setPreferredSize(new java.awt.Dimension(725, 465));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        startServerButton.setFont(new java.awt.Font("Trajan Pro", 1, 14)); // NOI18N
        startServerButton.setText("Start Server");
        startServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startServerButtonActionPerformed(evt);
            }
        });

        receiveAnswersButton.setFont(new java.awt.Font("Trajan Pro", 1, 14)); // NOI18N
        receiveAnswersButton.setText("Receive Answers");
        receiveAnswersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiveAnswersButtonActionPerformed(evt);
            }
        });

        sumaryButton.setFont(new java.awt.Font("Trajan Pro", 1, 14)); // NOI18N
        sumaryButton.setText("Summary");
        sumaryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sumaryButtonActionPerformed(evt);
            }
        });

        count.setEditable(false);
        count.setFont(new java.awt.Font("Tahoma", 1, 150)); // NOI18N
        count.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        count.setText("0");
        count.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/logoUoM.png"))); // NOI18N

        newQuestion.setFont(new java.awt.Font("Trajan Pro", 1, 14)); // NOI18N
        newQuestion.setText("New Question");
        newQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newQuestionActionPerformed(evt);
            }
        });

        backButton.setFont(new java.awt.Font("Trajan Pro", 1, 14)); // NOI18N
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(receiveAnswersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sumaryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(count, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(newQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(startServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(receiveAnswersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(sumaryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(count, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startServerButtonActionPerformed
    
        /*
        * when strat server button is clicked
        */
        if(startServerButton.getText().equalsIgnoreCase("Start Server")){
            newQuestion.setEnabled(false);                                      // disable new question button 
            
            if(!Utils.isServerState()){                                         // if unicast server is still running 
                if(unicastController != null &&                                 // then stop it
                        unicastController.isUnicastSeverAlive()){
                    unicastController.stopUnicastSever();
                }
            }
            
            Utils.setServerState(true);                                         // set server is running
            if(!broadcastController.isBroadcastSeverAlive()){                   // if broadcast server is still running
                broadcastController.stopBroadcastSever();                       // then stop it
                if(broadcastController.startBroadcastSever()){                  // start broadcast server 
                    startServerButton.setText("Stop Server");                   // rename this button as STOP SERVER
                    receiveAnswersButton.setEnabled(true);                      // enable receive answer button
                }
                else{
                    /*
                    * if broadcast server is not started, then there is a connection problem
                    */
                    JOptionPane.showMessageDialog(null, 
                            "Please check your PC is connected to correct access point",
                            "Connection Error",JOptionPane.ERROR_MESSAGE);
                }
            }
            
        }else{
            /*
            * when Stop server button is clicked
            */
            
            // stop broadcast server if it is still running
            if(broadcastController.isBroadcastSeverAlive()){
                broadcastController.stopBroadcastSever();
            }
            
            // enable NEW QUESTION button
            newQuestion.setEnabled(true);
            
            // disable START SERVER button
            startServerButton.setEnabled(false);
            
            // if answers has received save them in a tempari file which is in SoftclickerUOM folder in C partition
            if(!receiveAnswersButton.isEnabled() && 
                    !Utils.isSaved() && !Utils.getData().isEmpty()){
//                final JOptionPane optionPane = new JOptionPane("save results?",
//                                                        JOptionPane.QUESTION_MESSAGE,
//                                                        JOptionPane.YES_NO_OPTION);
//                int response = JOptionPane.showConfirmDialog(optionPane,"save results?");
//                if (response == JOptionPane.YES_OPTION) {
                    int currentCount = Utils.getQuestionCount();          
                    Utils.setQuestionCount(currentCount+1);                     // increment question count
                    SaveAnswersController s = new SaveAnswersController();
                    s.save();
                    Utils.setSaved(true);                                       // set answer is saved
//                }                
            }
            
            // set server is stopped
            Utils.setServerState(false);
            
            // disable RECEIVE ANSWER button
            receiveAnswersButton.setEnabled(false);
            
            // rename this button as START SERVER
            startServerButton.setText("Start Server");
        }
    }//GEN-LAST:event_startServerButtonActionPerformed

    private void sumaryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sumaryButtonActionPerformed
        // start GraphWindow screen
        GraphWindow clicker = new GraphWindow();
        clicker.pack();
        clicker.setLocationRelativeTo(null);
        clicker.setVisible(true);
    }//GEN-LAST:event_sumaryButtonActionPerformed

    private void receiveAnswersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiveAnswersButtonActionPerformed
        // start unicast server
        unicastController.startUnicastSever(ServerWindow.this);
        
        // stop broadcast server
        broadcastController.stopBroadcastSever();
        
        // disable this button
        receiveAnswersButton.setEnabled(false); 
        
        // set server is started
        Utils.setServerState(true);
        
        // set answers has not been yet
        Utils.setSaved(false);
    }//GEN-LAST:event_receiveAnswersButtonActionPerformed

    private void countActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_countActionPerformed

    private void newQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newQuestionActionPerformed
        // go to new question
        count.setText("0");
        
        // enable START SERVER button 
        startServerButton.setEnabled(true);
        
        // disable NEW QUESTION button
        newQuestion.setEnabled(false);
        
        // clear previously received from the buffer
        Utils.getData().clear();
    }//GEN-LAST:event_newQuestionActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // go back to SSIDWindow
        SSIDWindow.start();
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    public JTextField getCountVariable(){
        return count;
    }
  
    
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ServerWindow mainWindow = new ServerWindow();
                mainWindow.setLocationRelativeTo(null);
                mainWindow.setVisible(true);
                mainWindow.addWindowListener(new java.awt.event.WindowAdapter(){
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        /*
                        * delete all the tempory files in SoftclickerUOM folder 
                        *                 in C partition, when close this window
                        */
                        try{
                            File file = new File("C:\\SoftclickerUOM");
                            for(File f: file.listFiles()) {
                                f.delete(); 
                            }
                        }catch(Exception e){

                                e.printStackTrace();

                        }
                    }
                });
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JTextField count;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton newQuestion;
    private javax.swing.JButton receiveAnswersButton;
    private javax.swing.JButton startServerButton;
    private javax.swing.JButton sumaryButton;
    // End of variables declaration//GEN-END:variables
}
