package view;

import controller.ReceiveAnswerButtonController;
import controller.StartServerButtonController;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.BroadcastServer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @ Author Chethiya
 */
public class SoftClicker extends JFrame {
    /**
     * Construct a new frame 
     *
     * @param mockServer the server simulator 
     */
    StartServerButtonController broadcastController;
    ReceiveAnswerButtonController unicastController;
    JTextField answerCount = new JTextField();
    
    public SoftClicker( ) {
        super("Soft Clicker - Dept. of Computer Science and Engineering, University of Moratuwa");
        
        
        final DefaultCategoryDataset dataset  = createDataset();
        
        JFreeChart barChart = ChartFactory.createBarChart(
            "Analysis of Answers",           
            "Answers",            
            "Number of Answers",            
            dataset,          
            PlotOrientation.VERTICAL,           
            true, true, false);
        
        ChartPanel chartPanel = new ChartPanel( barChart, false );        
        chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
        this.add(chartPanel, BorderLayout.CENTER);                
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
        
        
        // South Button Pannel
        
        JPanel southButtonPanel = new JPanel();
//        final JButton remButton = new JButton("Receive Answers");
//        remButton.setEnabled(false);
//        final JButton runServerButton = new JButton("Start Server");
//        southButtonPanel.add(runServerButton);
//        runServerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(runServerButton.getText().equals("Start Server")){
//                    broadcastController.startBroadcastSever();
//                    runServerButton.setText("Stop Server");
//                    remButton.setEnabled(true);
//                }else{
//                    
//                    if(broadcastController.isBroadcastSeverAlive()){
//                        broadcastController.stopBroadcastSever();
//                    }
//                    
//                    if(unicastController != null && unicastController.isUnicastSeverAlive()){
//                        unicastController.stopUnicastSever();
//                    }
//                    remButton.setEnabled(false);
//                    runServerButton.setText("Start Server");
//                }
//            }
//        });
        
//        southButtonPanel.add(remButton);        
//        remButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) { 
////                unicastController.startUnicastSever();
////                broadcastController.stopBroadcastSever();
////                remButton.setEnabled(false);
//            }
//        });
        this.add(southButtonPanel, BorderLayout.SOUTH);
        
        
        // East Button Panel
        
        JPanel eastButtonPanel = new JPanel();
        JButton refreshButton = new JButton("Refresh");
        southButtonPanel.add(refreshButton);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDataset(dataset);
            }
        });
        JButton csvButton = new JButton("Import to CSV");
        JButton tstButton = new JButton("Test");
        southButtonPanel.add(csvButton);
        southButtonPanel.add(tstButton);
        answerCount.setColumns(10);
        //answerCount.setSize(eastButtonPanel.getSize());
        //answerCount.setEditable(false);
//        JLabel answerCountLabel = new JLabel("Answer Count");
//        eastButtonPanel.add(answerCountLabel);
//        eastButtonPanel.add(answerCount);
        csvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button 2 Clicked!");
                int row = dataset.getRowCount();
                int col = dataset.getColumnCount();
                
                for(int i = 0; i < row; i++){
                    for(int j = 0; j < col; j++){
                        System.out.println(dataset.getValue(i, j));
                    }
                }
            }
        });
        this.add(eastButtonPanel, BorderLayout.EAST);
    }

    /**
     * Create a series
     * 
     * @ return the series
     */
    private DefaultCategoryDataset createDataset(){
        final String ans1 = "Answer 1";        
        final String ans2 = "Answer 2"; 
        final String ans3 = "Answer 3";        
        final String ans4 = "Answer 4";
        final String ans5 = "Answer 5"; 
        final String question = "Answer";
        
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 

        dataset.addValue( 0 , ans1 , ans1 );        
        dataset.addValue( 0 , ans2 , ans2 );        
        dataset.addValue( 0 , ans3 , ans3 ); 
        dataset.addValue( 0 , ans4 , ans4 );   
        dataset.addValue( 0 , ans5 , ans5 ); 

        return dataset; 
    }
    
   
    private void updateDataset(DefaultCategoryDataset dataset){
        dataset.clear();
        final String ans1 = "Answer 1";        
        final String ans2 = "Answer 2"; 
        final String ans3 = "Answer 3";        
        final String ans4 = "Answer 4";
        final String ans5 = "Answer 5"; 
        final String question = "Question";
        
        final String q1 = "Answer 1";        
        final String q2 = "Answer 2"; 
        final String q3 = "Answer 3";        
        final String q4 = "Answer 4";
        final String q5 = "Answer 5";
        

//        int[] answers = getAnswers();
//
//        dataset.addValue( answers[0] , q1 , ans1 );        
//        dataset.addValue( answers[1] , q2 , ans2 );        
//        dataset.addValue( answers[2] , q3 , ans3 ); 
//        dataset.addValue( answers[3] , q4 , ans4 );   
//        dataset.addValue( answers[4] , q5 , ans5 );
        
    }
    
//    private int[] getAnswers(){
//        return mockServer.getAnswers();
//    }

    /** Main method
     * @param args */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                SoftClicker clicker = new SoftClicker();
//                clicker.pack();
//                clicker.setLocationRelativeTo(null);
//                clicker.setVisible(true);
//            }
//         });
//    }
}

class MockServer{
    public int[] getAnswers(){
        int[] returnArray = new int[5]; 
        Random r = new Random();
        int Low = 0;
        int High = 150;
        
        for(int i = 0; i < 5; i++){
            returnArray[i] = r.nextInt(High-Low) + Low;
        }
        return returnArray;
    
    }
    
    public Object runServer(){
        System.out.println("Server is running! ");
        return null;
    }
    
    public Object stopServer(){
        System.out.println("Server has stopped! ");
        return null;
    }
    
    public Object collectAnswers(){
        System.out.println("Server is collecting answers! ");
        return null;
    } 
}
