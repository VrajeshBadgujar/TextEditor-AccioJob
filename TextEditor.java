import com.sun.source.tree.NewArrayTree;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    //file menu items
    JMenuItem newFile, openFile, saveFile;
    //Edit menu items
    JMenuItem cut, copy, paste, selectAll, close;
    //textArea
    JTextArea textArea;

    TextEditor(){
        frame = new JFrame();
        menuBar = new JMenuBar();
        textArea = new JTextArea();

        //initializing menus
        file = new JMenu("File");
        edit = new JMenu("Edit");



        //add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);

        //Create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        //add textara to panel
        panel.add(textArea, BorderLayout.CENTER);

        //create scrollpane
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //add scroll pane to panel
        panel.add(scrollPane);

        //add panel to frame
        frame.add(panel);

        //intialize file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        close  = new JMenuItem("Close");

        //initialze edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //add action listeners to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        close.addActionListener(this);

        //adding file menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //add action listeners to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //adding edit menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //initializing menubar
        frame.setJMenuBar(menuBar);
        //adding text area to frame
//        frame.add(textArea);
        frame.setBounds(0,0,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
       if(actionEvent.getSource() == cut){
           textArea.cut();
       }
       if(actionEvent.getSource() == copy){
           textArea.copy();
       }
       if(actionEvent.getSource() == selectAll){
           textArea.selectAll();
       }
       if(actionEvent.getSource() == paste){
           textArea.paste();
       }
       if(actionEvent.getSource() == close){
           System.exit(0);
       }
       if(actionEvent.getSource() == openFile){
           JFileChooser fileChooser = new JFileChooser();
           int chooserOption = fileChooser.showOpenDialog(null);
           //if clicked on open button
           if(chooserOption == JFileChooser.APPROVE_OPTION){
               File file = fileChooser.getSelectedFile();
              //get path of the selected file
               String filePath = file.getPath();

               try{
                   FileReader fileReader = new FileReader(filePath);
                   BufferedReader bufferedReader = new BufferedReader(fileReader);
                   String intermediate = "", output = "";

                   //reading content of the file
                   while((intermediate = bufferedReader.readLine()) != null){
                       output += intermediate + "\n";
                   }
                   //set the output string to text area
                   textArea.setText(output);
               }
                catch (IOException fileNotFoundException) {
                  fileNotFoundException.printStackTrace();
               }
           }

       }

       if(actionEvent.getSource() == saveFile){
           //initialize file picker
           JFileChooser fileChooser = new JFileChooser("C:");
           //initialize file chooser
           int chooseOption = fileChooser.showSaveDialog(null);
           //if we click on save button
           if(chooseOption == JFileChooser.APPROVE_OPTION){
           File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+
                   ".txt");
           try{
               FileWriter fileWriter = new FileWriter(file);
               BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
               textArea.write(bufferedWriter);
               bufferedWriter.close();
           }
           catch(IOException ioException){
             ioException.printStackTrace();
           }
           }

       }
       if(actionEvent.getSource() == newFile){
           TextEditor newTextEditor = new TextEditor();
       }
    }

    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();
    }
}
