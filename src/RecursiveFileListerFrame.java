import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class RecursiveFileListerFrame extends JFrame{
   JFrame frame = new JFrame();
   JPanel mainPanel;
   JPanel topPanel;
   JPanel midPanel;
   JPanel botPanel;

   JLabel titleLble;

   JLabel fileLble;
   JTextArea fileDisplay;

   JButton quitBtn;
   JButton addBtn;

   public RecursiveFileListerFrame(){
       mainPanel = new JPanel();
       mainPanel.setLayout(new BorderLayout());
       createTopPanel();
       mainPanel.add(topPanel, BorderLayout.NORTH);
       createMiddlePanel();
       mainPanel.add(midPanel, BorderLayout.CENTER);
       createBottomPanel();
       mainPanel.add(botPanel, BorderLayout.SOUTH);

       add(mainPanel);

       Toolkit kit = Toolkit.getDefaultToolkit();
       Dimension screenSize = kit.getScreenSize();

       int screenH = screenSize.height;
       int screenW = screenSize.width;

       setSize(screenW / 2, screenH / 2);
       setLocation(screenW / 4, screenH / 4);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setVisible(true);

   }
   private void createTopPanel() {
       topPanel = new JPanel();
       titleLble = new JLabel("Recursive File Lister", JLabel.CENTER);
       titleLble.setFont(new Font("Times New Roman", Font.BOLD, 40));

       topPanel.add(titleLble);
   }
   private void createMiddlePanel(){
       midPanel = new JPanel();

       fileDisplay = new JTextArea(60, 60);

       midPanel.add(fileDisplay);
   }
   private void createBottomPanel(){
       botPanel = new JPanel();

       addBtn = new JButton("Search File");
       quitBtn = new JButton("Quit");

       quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

       addBtn.addActionListener((ActionEvent ae) -> {
           JFileChooser chooser = new JFileChooser();
           File chosenFile;
           String rec = "";

           File workingDir = new File(System.getProperty("user.dir"));
           chooser.setCurrentDirectory(workingDir);
           chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

           if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
               chosenFile = chooser.getSelectedFile();
               Search(chosenFile);
           }
       });
       botPanel.add(addBtn);
       botPanel.add(quitBtn);
   }
   private void Search(File toSearch) {
       File[] files = toSearch.listFiles();
       if (toSearch.isDirectory()) {
           for (File file : files) {
               if (file.isDirectory()) {
                   fileDisplay.append(("\ndirectory: " + file.toPath()));
                   Search(file);
               }
               else {
                   fileDisplay.append("\nfile: " + file.toPath());
               }
           }
       }
       else {
           fileDisplay.append("\nfile: " + toSearch.toPath());
       }
   }
}
