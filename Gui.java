
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;




public class Gui {
    private JFrame mainFrame;
    private JLabel titleLabel;
    private JMenuBar menuBar;
    private String filename;
    
    public Gui() {
        prepareGUI();
    }
    
    private void prepareGUI(){
      mainFrame = new JFrame("OOP Assignment");
      mainFrame.setSize(400,400);
      mainFrame.setResizable(false);
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //testing
      mainFrame.setLayout(new GridLayout(14,1));
      titleLabel = new JLabel("HR System", JLabel.CENTER);
      
      
    }
    
    
    public void contentObjects() throws IOException {
         
         //Initialize GUI objects
         menuBar = new JMenuBar();
         JMenu fileMenu = new JMenu("File");
         JMenuItem openFile = new JMenuItem("Open");
         openFile.setMnemonic(KeyEvent.VK_O);
         openFile.setActionCommand("Open");
         JMenuItem exit = new JMenuItem("Exit");
         exit.setActionCommand("Exit");
         fileMenu.add(openFile);
         fileMenu.addSeparator();
         fileMenu.add(exit);
         menuBar.add(fileMenu);
         
         
         
         
         
         JLabel sidLabel = new JLabel("Staff ID: ", JLabel.CENTER);
         final JTextField sidText = new JTextField(10);
         JLabel nameLabel = new JLabel("Name: ", JLabel.CENTER);
         final JTextField nameText = new JTextField(10);
         JLabel ageLabel = new JLabel("Age: ", JLabel.CENTER);
         final JTextField ageText = new JTextField(10);
         JLabel genderLabel = new JLabel("Gender: ", JLabel.CENTER);
         final JTextField genderText = new JTextField(10);
         JLabel mobileLabel = new JLabel("Mobile number: ", JLabel.CENTER);
         final JTextField mobileText = new JTextField(10);
         JLabel postLabel = new JLabel("Post: ", JLabel.CENTER);
         final JTextField postText = new JTextField(10);
         JLabel addressLabel = new JLabel("Address: ", JLabel.CENTER);
         final JTextField addressText = new JTextField(10);
         JLabel salaryLabel = new JLabel("Salary: ", JLabel.CENTER);
         final JTextField salaryText = new JTextField(10);
         JLabel mpfLabel = new JLabel("MPF: ", JLabel.CENTER);
         final JTextField mpfText = new JTextField(10);
         JLabel bonusLabel = new JLabel("Bonus: ", JLabel.CENTER);
         final JTextField bonusText = new JTextField(10);
         JLabel haLabel = new JLabel("Housing Allowance: ", JLabel.CENTER);
         final JTextField haText = new JTextField(10);
         JLabel taLabel = new JLabel("Travelling Allowrance: ", JLabel.CENTER);
         final JTextField taText = new JTextField(10);
         
         JButton addMan = new JButton("Add manager");
         JButton addGen = new JButton("Add general staff");
         JButton showRecord = new JButton("Show record");
         
         
         //testing

         
 
         JPanel sidPanel = new JPanel(new GridLayout(0, 2));
         sidPanel.add(sidLabel);
         sidPanel.add(sidText);
         
         JPanel namePanel = new JPanel(new GridLayout(0, 2));
         namePanel.add(nameLabel);
         namePanel.add(nameText);
         JPanel agePanel = new JPanel(new GridLayout(0, 2));
         agePanel.add(ageLabel);
         agePanel.add(ageText);  
         
         JPanel genederPanel = new JPanel(new GridLayout(0, 2));
         genederPanel.add(genderLabel);
         genederPanel.add(genderText);
         
         JPanel mobilePanel = new JPanel(new GridLayout(0, 2));
         mobilePanel.add(mobileLabel);
         mobilePanel.add(mobileText);
         
         JPanel postPanel = new JPanel(new GridLayout(0, 2));
         postPanel.add(postLabel);
         postPanel.add(postText);
         
         JPanel addressPanel = new JPanel(new GridLayout(0, 2));
         addressPanel.add(addressLabel);
         addressPanel.add(addressText);
         
         JPanel salaryPanel = new JPanel(new GridLayout(0, 2));
         salaryPanel.add(salaryLabel);
         salaryPanel.add(salaryText);
         
         JPanel mpfPanel = new JPanel(new GridLayout(0, 2));
         mpfPanel.add(mpfLabel);
         mpfPanel.add(mpfText);
         
         JPanel bonusPanel = new JPanel(new GridLayout(0, 2));
         bonusPanel.add(bonusLabel);
         bonusPanel.add(bonusText);
         
         JPanel haPanel = new JPanel(new GridLayout(0, 2));
         haPanel.add(haLabel);
         haPanel.add(haText);
         
         JPanel taPanel = new JPanel(new GridLayout(0, 2));
         taPanel.add(taLabel);
         taPanel.add(taText);
         
         JPanel buttonsPanel = new JPanel(new FlowLayout());
         buttonsPanel.add(addMan);
         buttonsPanel.add(addGen);
         buttonsPanel.add(showRecord);
         
         

         
         //Initialize an employee object array
         ArrayList<Employee> emp = new ArrayList<Employee>();
         
         
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd-HH-mm-ss");
        filename = dateFormat.format(currentDate) + ".dat";
         
        //Button action for adding manager information
        addMan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    char s = sidText.getText().charAt(0);
                    if (Character.toString(s).toUpperCase().contentEquals("M")) {
                        emp.add (new Manager(sidText.getText(), nameText.getText(), 
                        Integer.parseInt(ageText.getText()), genderText.getText(), 
                        mobileText.getText(), postText.getText(), addressText.getText(), 
                        Integer.parseInt(salaryText.getText()), Integer.parseInt(mpfText.getText()),
                        Integer.parseInt(haText.getText()), Integer.parseInt(taText.getText())));
                           
                        writeDat(filename, emp.get(emp.size()-1).toString());
                        
                        JOptionPane.showMessageDialog(mainFrame, emp.get(emp.size()-1));
                        sidText.setText("");
                        nameText.setText("");
                        ageText.setText("");
                        genderText.setText("");
                        mobileText.setText("");
                        postText.setText("");
                        addressText.setText("");
                        salaryText.setText("");
                        mpfText.setText("");
                        bonusText.setText("");
                        haText.setText("");
                        taText.setText("");
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "You are not entering manager information.");
                    }
                } catch (StringIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Staff ID is empty. ", "Staff ID error", JOptionPane.ERROR_MESSAGE);
                    sidText.requestFocus();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "You have not finished the form. ", "Empty input", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainFrame, ex);
                } 
            }
        });
            
        //Button action for adding general staff information
        addGen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    char s = sidText.getText().charAt(0);
                    if (Character.toString(s).toUpperCase().contentEquals("G")) {
                        
                        emp.add (new GeneralStaff(sidText.getText(), nameText.getText(), 
                        Integer.parseInt(ageText.getText()), genderText.getText(), 
                        mobileText.getText(), postText.getText(), addressText.getText(), 
                        Integer.parseInt(salaryText.getText()), Integer.parseInt(mpfText.getText()),
                        Integer.parseInt(bonusText.getText())));
                        
                        writeDat(filename, emp.get(emp.size()-1).toString());
                        JOptionPane.showMessageDialog(mainFrame, emp.get(emp.size()-1));
                           
                        sidText.setText("");
                        nameText.setText("");
                        ageText.setText("");
                        genderText.setText("");
                        mobileText.setText("");
                        postText.setText("");
                        addressText.setText("");
                        salaryText.setText("");
                        mpfText.setText("");
                        bonusText.setText("");
                        haText.setText("");
                        taText.setText("");
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "You are not entering general staff information.");
                        sidText.requestFocus();
                    }
                } catch (StringIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Staff ID is empty. ", "Input Error", JOptionPane.ERROR_MESSAGE);
                    sidText.requestFocus();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Wrong input", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (HeadlessException | IOException ex) {
                    JOptionPane.showMessageDialog(mainFrame, ex);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainFrame, ex);
                }
            }
        });
         
            
        //Button action for showing current employees' information
        showRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainFrame, empRecord(emp));
            }
        });
         
         
         
        //Statements for importing .dat files
        final JFileChooser fileDialog = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".dat files", "dat");
        fileDialog.setFileFilter(filter);
        
        //The action method of the open menu item within the file menu
        openFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileDialog.showOpenDialog(mainFrame);
            
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = fileDialog.getSelectedFile();
                        filename = fileDialog.getSelectedFile().toString();
                        JOptionPane.showMessageDialog(mainFrame, readDat(emp, file));
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(mainFrame, ex.getMessage());
                    }
                }      
             }
          });
        
        //The action method of the exit menu item within the file menu
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
         
        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(titleLabel);
        mainFrame.add(sidPanel);
        mainFrame.add(namePanel);
        mainFrame.add(agePanel);
        mainFrame.add(genederPanel);
        mainFrame.add(mobilePanel);
        mainFrame.add(postPanel);
        mainFrame.add(addressPanel);
        mainFrame.add(salaryPanel);
        mainFrame.add(mpfPanel);
        mainFrame.add(bonusPanel);
        mainFrame.add(haPanel);
        mainFrame.add(taPanel);
        
        
        
        
        
        
        mainFrame.add(buttonsPanel);
        mainFrame.setVisible(true);
         
     }
     
     //Method for getting information from a dat file
     public String readDat(ArrayList<Employee> emp, File file) throws FileNotFoundException, IOException {
         emp.clear();
         BufferedReader reader = new BufferedReader(new FileReader(file));
         String line = reader.readLine();
         while (line != null) {
            String [] parts = line.split(";");
            char c  = parts[0].toUpperCase().charAt(0);
            switch (c) {
                case 'M':
                    emp.add(new Manager(parts[0], parts[1], 
                                   Integer.parseInt(parts[2]), parts[3], 
                                   parts[4], parts[5], parts[6], 
                                   Integer.parseInt(parts[7]), Integer.parseInt(parts[8]),
                                   Integer.parseInt(parts[9]), Integer.parseInt(parts[10])));
                    break;
                    
                case 'G':
                    emp.add(new GeneralStaff(parts[0], parts[1], 
                                   Integer.parseInt(parts[2]), parts[3], 
                                   parts[4], parts[5], parts[6], 
                                   Integer.parseInt(parts[7]), Integer.parseInt(parts[8]),
                                   Integer.parseInt(parts[9])));
                    break;
                default:
                    return "Error!";
            }
            line = reader.readLine();
         }
         return "File has been read.";
     }
     
     //Method for adding information into a dat file
     public void writeDat(String filename, String empINFO) throws IOException {
         BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
         writer.append(empINFO);
         writer.newLine();
         writer.close();
     }
    
    //A method for showing the details of all the employee objects
    public String empRecord(ArrayList<Employee> emp) {
        if (emp.isEmpty()) {
            return "No record.";
        } else {
            String record = "The sample records of the employees are shown below: \n";
            for (int i=0; i < emp.size(); i++) {
                record += "Sample record " + (i+1) + ": " +emp.get(i) + '\n';
            }
            return record;
        }
    }
    
    public static String checkTextfield(JFrame frame, JLabel label, JTextField textfield, String datatype) throws Exception{
        try {
            if (textfield.getText().isEmpty()) {
                throw new StringIndexOutOfBoundsException(label.getText().substring(0, label.getText().length()-2) + " is empty");
            }
        } catch (StringIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Input error", JOptionPane.ERROR_MESSAGE);
            textfield.requestFocus();
        } 
        return textfield.getText();
    }
            
} 
          