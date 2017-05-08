import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public final class UI extends JFrame {
    //The employee objects with subclass stored by an array list
    private final ArrayList<Employee> employee = new ArrayList<>();
    
    //Constructor for constructing the user interface objects
    public UI() {
        //Initiating the frame
        setSize(1000, 500);
        setTitle("INT2014 Assignment");
        
        setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(new JLabel("HR System of After-School Tutorial Class", JLabel.CENTER));
        
        
        //A panel storing the two main contents panel
        JPanel contentPanel = new JPanel(new GridLayout(1, 2));
        JPanel rPanel = new JPanel();
        
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(){
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        
        rPanel = createRightPanel(rPanel, model, table);
        
        JPanel lPanel = new JPanel(new GridLayout(12, 2));
        lPanel = createLeftPanel(lPanel, model, table);
        
        
        //Add the contents panel into the main frame
        contentPanel.add(lPanel);
        contentPanel.add(rPanel);
        
        add(contentPanel);
        
        //GridBagConstraints gbc = new GridBagConstraints();
        
        /*gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        add(new JLabel("HR System of After-School Tutorial Class", JLabel.CENTER), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JPanel lPanel = new JPanel(new GridLayout(11, 2));
        lPanel = createLeftPanel(lPanel);
        add(lPanel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        JPanel rPanel = new JPanel(new GridBagLayout());
        lPanel = createRightPanel(rPanel);
        add(rPanel, gbc);*/
        
    }
    
    //Method for creating the left panel
    private JPanel createLeftPanel(JPanel panel, DefaultTableModel model, JTable table) {
        //
        JLabel labels[] = new JLabel[10];
        String [] labelNames = {"Staff ID", "Name", "Assigned School Code", "Hourly Salary", 
                            "Working Hour", "Salary", "Bonus", "MPF", "Total Salary", "Month"};
        
        JTextField textfields[] = new JTextField[9];
        
        JButton buttons[] = new JButton[4];
        String [] buttonNames = {"Add", "Delete", "Save", "Load"};
        
        final DefaultComboBoxModel months = new DefaultComboBoxModel();
        String [] monthsName = {"", "January", "Feburary", "March", "April", 
                                        "May", "June", "July", "August", 
                                        "September", "October", "November", "December"};
        
        for (int i = 0; i < monthsName.length; i++) {
            months.addElement(monthsName[i]);
        }
        
        final JComboBox monthComboBox = new JComboBox(months);
        
        for (int i = 0; i < labelNames.length; i++) {
            labels[i] = new JLabel(labelNames[i]);
            
            if (i <= 8) {
                textfields[i] = new JTextField(5);
                
                if (i <= 2) {
                    textfields[i].addFocusListener(new textFieldFocusListener(labels[i]));
                } else if (i >= 5){
                    textfields[i].setEditable(false);
                }
            } else {
                monthComboBox.addFocusListener(new textFieldFocusListener(labels[i]));
            }
        }
   
        //Assign specific focus listener to the staff id, mpf and bonus textfields
        textfields[0].addFocusListener(new staffIDFocusListener(labels[0], textfields[0], textfields[6], textfields[7], textfields[5], textfields[8], panel));
        textfields[3].addFocusListener(new salaryFocusListener(labels[3], textfields[3], labels[4], textfields[4], textfields[5], textfields[6], textfields[7], textfields[8], panel, textfields[0]));
        textfields[4].addFocusListener(new salaryFocusListener(labels[4], textfields[4], labels[3], textfields[3], textfields[5], textfields[6], textfields[7], textfields[8], panel, textfields[0]));
        textfields[6].setText("0");
        textfields[7].setText("0");
        
        for (int i = 0; i < labelNames.length; i++) {
            panel.add(labels[i]);
            
            if (i <= 8) {
                panel.add(textfields[i]);
            } else {
                panel.add(monthComboBox);
            }
        }
        
        for (int i = 0; i < buttonNames.length; i++) {
            buttons[i] = new JButton(buttonNames[i]);
            
            
            switch (i) {
                case 0:
                    buttons[i].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (checkInput(textfields, monthComboBox)) {
                                char type = textfields[0].getText().toUpperCase().charAt(0);
                                switch (type) {
                                     case 'C':
                                            employee.add(new Coordinator(textfields[0].getText(), textfields[1].getText(), textfields[2].getText(), 
                                               Double.parseDouble(textfields[3].getText()), Integer.parseInt(textfields[4].getText()), Double.parseDouble(textfields[5].getText()), 
                                                Double.parseDouble(textfields[8].getText()), String.valueOf(monthComboBox.getSelectedItem()),
                                                Double.parseDouble(textfields[7].getText())));
                                                JOptionPane.showMessageDialog(panel, "Added Coordinator");
                                                showEmployeeInfo(employee, model);
                                                clearInput(textfields, monthComboBox);
                                            break;
                                        case 'T':
                                            employee.add(new Tutor(textfields[0].getText(), textfields[1].getText(), textfields[2].getText(), 
                                               Double.parseDouble(textfields[3].getText()), Integer.parseInt(textfields[4].getText()), Double.parseDouble(textfields[5].getText()), 
                                                Double.parseDouble(textfields[8].getText()), String.valueOf(monthComboBox.getSelectedItem()),
                                                Double.parseDouble(textfields[6].getText())));
                                                JOptionPane.showMessageDialog(panel, "Added Teacher");
                                                showEmployeeInfo(employee, model);
                                                clearInput(textfields, monthComboBox);
                                            break;
                                        default:
                                            JOptionPane.showMessageDialog(panel, "Please input correct staff ID");
                                            textfields[0].requestFocus();
                                    }
                            } else {
                                JOptionPane.showMessageDialog(panel, "Please complete the form");
                            }
                        }
                    });
                    break;
                case 1:
                    buttons[i].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int i = table.getSelectedRow();
                            if(i >= 0){
                                model.removeRow(i);
                                employee.remove(i);
                            } else{
                                JOptionPane.showMessageDialog(panel, "No data is selected");
                            }
                        }
                    });
                    break;
                case 2:
                    buttons[i].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (employee.size() > 0) {
                                final JFileChooser fileDialog = new JFileChooser();
                                FileNameExtensionFilter filter = new FileNameExtensionFilter(".dat files", "dat");
                                fileDialog.setFileFilter(filter);
                                
                                int rVal = fileDialog.showSaveDialog(panel);
                                
                                if (rVal == JFileChooser.APPROVE_OPTION) {
                                    String filename = fileDialog.getCurrentDirectory().toString() + "\\" + fileDialog.getSelectedFile().getName() + ".dat";
                                    System.out.println(filename);
                                    
                                    try {
                                        String nl = System.getProperty("line.separator");
                                        try (DataOutputStream output = new DataOutputStream(new FileOutputStream(filename))) {
                                            for (int i = 0; i < employee.size(); i++) {
                                                char type  = employee.get(i).getStaffID().toUpperCase().charAt(0);
                                                switch (type) {
                                                    case 'T':
                                                        Tutor tutor = (Tutor) employee.get(i);
                                                        
                                                        output.writeBytes(tutor.getStaffID());
                                                        output.writeBytes(";");
                                                        output.writeBytes(tutor.getName());
                                                        output.writeBytes(";");
                                                        output.writeBytes(tutor.getSchoolCode());
                                                        output.writeBytes(";");
                                                        output.writeBytes(Double.toString(tutor.gethSalary()));
                                                        output.writeBytes(";");
                                                        output.writeBytes(Integer.toString(tutor.getWorkHour()));
                                                        output.writeBytes(";");
                                                        output.writeBytes(Double.toString(tutor.getSalary()));
                                                        output.writeBytes(";");
                                                        output.writeBytes(Double.toString(tutor.getTotalSalary()));
                                                        output.writeBytes(";");
                                                        output.writeBytes(tutor.getMonth());
                                                        output.writeBytes(";");
                                                        output.writeBytes(Double.toString(tutor.getBonus()));
                                                        output.writeBytes(";");
                                                        output.writeBytes(nl);
                                                        break;
                                                        
                                                    case 'C':
                                                        
                                                        Coordinator coordinator = (Coordinator) employee.get(i);
                                                        
                                                        output.writeBytes(coordinator.getStaffID());
                                                        output.writeBytes(";");
                                                        output.writeBytes(coordinator.getName());
                                                        output.writeBytes(";");
                                                        output.writeBytes(coordinator.getSchoolCode());
                                                        output.writeBytes(";");
                                                        output.writeBytes(Double.toString(coordinator.gethSalary()));
                                                        output.writeBytes(";");
                                                        output.writeBytes(Integer.toString(coordinator.getWorkHour()));
                                                        output.writeBytes(";");
                                                        output.writeBytes(Double.toString(coordinator.getSalary()));
                                                        output.writeBytes(";");
                                                        output.writeBytes(Double.toString(coordinator.getTotalSalary()));
                                                        output.writeBytes(";");
                                                        output.writeBytes(coordinator.getMonth());
                                                        output.writeBytes(";");
                                                        output.writeBytes(Double.toString(coordinator.getMPF()));
                                                        output.writeBytes(";");
                                                        output.writeBytes(nl);
                                                        break;
                                                        
                                                    default:
                                                        break;
                                                }
                                            }
                                        }
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                                        JOptionPane.showMessageDialog(panel, ex, "Error", JOptionPane.ERROR_MESSAGE);
                                    } catch (IOException ex) {
                                        JOptionPane.showMessageDialog(panel, ex, "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(panel, "No data");
                            }
                        }
                    });
                    break;
                case 3:
                    buttons[i].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            final JFileChooser fileDialog = new JFileChooser();
                            FileNameExtensionFilter filter = new FileNameExtensionFilter(".dat files", "dat");
                            fileDialog.setFileFilter(filter);
                            
                            int returnVal = fileDialog.showOpenDialog(panel);
                            
                            if (returnVal == JFileChooser.APPROVE_OPTION) {
                                File file = fileDialog.getSelectedFile();
                                
                                model.setRowCount(0);//Clear Table
                                employee.clear(); //Clear all records within the employee object
                                
                                BufferedReader reader;
                                try {
                                    reader = new BufferedReader(new FileReader(file));
                                    String line = reader.readLine();
                                    while (line != null) {
                                        String [] parts = line.split(";");
                                        char type  = parts[0].toUpperCase().charAt(0);
                                        
                                        switch (type) {
                                            case 'T':
                                                employee.add(new Tutor(parts[0], parts[1], parts[2], 
                                                Double.parseDouble(parts[3]), Integer.parseInt(parts[4]), Double.parseDouble(parts[5]), 
                                                Double.parseDouble(parts[6]), parts[7],
                                                Double.parseDouble(parts[8])));
                                                showEmployeeInfo(employee, model);
                                                break;
                                            case 'C':
                                                employee.add(new Coordinator(parts[0], parts[1], parts[2], 
                                                Double.parseDouble(parts[3]), Integer.parseInt(parts[4]), Double.parseDouble(parts[5]), 
                                                Double.parseDouble(parts[6]), parts[7],
                                                Double.parseDouble(parts[8])));
                                                showEmployeeInfo(employee, model);
                                                break;
                                            default:
                                                break;
                                        }
                                        
                                        line = reader.readLine();
                                    }
                                    
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    });
                break;
            }
            
            panel.add(buttons[i]);
        }
        return panel;
    }
    
    class textFieldFocusListener implements FocusListener {
        private final JLabel label;
        
        public textFieldFocusListener(JLabel label) {
            this.label = label;
        }
        public void focusGained(FocusEvent e) {
            label.setForeground(Color.red);
        }
        public void focusLost(FocusEvent e) {
            label.setForeground(Color.black);
        }
    }
    
    class staffIDFocusListener implements FocusListener {
        private final JLabel staffIDLabel;
        private final JTextField staffIDTextField;
        private final JTextField bonusTextField;
        private final JTextField mpfTextField;
        private final JTextField salaryTextField;
        private final JTextField totalSalaryTextField;
        private final JPanel panel;
        
        
        public staffIDFocusListener(JLabel staffIDLabel, JTextField staffIDTextField, JTextField bonusTextField, JTextField mpfTextField, JTextField salaryTextField, JTextField totalSalaryTextField, JPanel panel) {
            this.staffIDLabel = staffIDLabel;
            this.staffIDTextField = staffIDTextField;
            this.bonusTextField = bonusTextField;
            this.mpfTextField = mpfTextField;
            this.salaryTextField = salaryTextField;
            this.totalSalaryTextField = totalSalaryTextField;
            this.panel = panel;
        }
        
        public void focusGained(FocusEvent e) {
            staffIDLabel.setForeground(Color.red);
        }
        
        public void focusLost(FocusEvent e) {
            if (!staffIDTextField.getText().isEmpty()) {
                char type = staffIDTextField.getText().toUpperCase().charAt(0);
                switch (type) {
                    case 'T':
                        if(!salaryTextField.getText().isEmpty()) {
                            double salary = Double.parseDouble(salaryTextField.getText());
                            double bonus = salary *  0.1;
                            double totalSalary = salary + bonus;

                            bonusTextField.setText(Double.toString(bonus));
                            totalSalaryTextField.setText(Double.toString(totalSalary));
                        }
                        mpfTextField.setText("0");
                        break;
                    case 'C':
                        if(!salaryTextField.getText().isEmpty()) {
                            double salary = Double.parseDouble(salaryTextField.getText());
                            double mpf = salary *  0.05;
                            double totalSalary = salary + mpf;

                            mpfTextField.setText(Double.toString(mpf));
                            totalSalaryTextField.setText(Double.toString(totalSalary));
                        }
                        bonusTextField.setText("0");
                        break;
                    default:
                        JOptionPane.showMessageDialog(panel, "Please input correct staff ID.", "Staff ID problem", JOptionPane.ERROR_MESSAGE);
                        staffIDTextField.requestFocus();
                }
            }
        }
    }
    
    class salaryFocusListener implements FocusListener {
        private final JLabel currentLabel;
        private final JTextField currentTextField;
        private final JLabel otherLabel;
        private final JTextField otherTextField;
        private final JTextField salaryTextField;
        private final JTextField bonusTextField;
        private final JTextField mpfTextField;
        private final JTextField totalSalaryTextField;
        private final JTextField staffIDTextField;
        private final JPanel panel;
        
        public salaryFocusListener(JLabel currentLabel, JTextField currentTextField, JLabel otherLabel, JTextField otherTextField, JTextField salaryTextField, JTextField bonusTextField, JTextField mpfTextField, JTextField totalSalaryTextField, JPanel panel, JTextField staffIDTextField) {
            this.currentLabel = currentLabel;
            this.currentTextField = currentTextField;
            this.otherLabel = otherLabel;
            this.otherTextField = otherTextField;
            this.salaryTextField = salaryTextField;
            this.bonusTextField = bonusTextField;
            this.mpfTextField = mpfTextField;
            this.totalSalaryTextField = totalSalaryTextField;
            this.panel = panel;
            this.staffIDTextField = staffIDTextField;
        }
        public void focusGained(FocusEvent e) {
            currentLabel.setForeground(Color.red);
            if (staffIDTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please input the Staff ID fitst", "Empty Staff ID", JOptionPane.ERROR_MESSAGE);
                staffIDTextField.requestFocus();
            }
        }
        public void focusLost(FocusEvent e) {
            if (!staffIDTextField.getText().isEmpty()) {
                char type = staffIDTextField.getText().toUpperCase().charAt(0);
                switch (type) {
                    case 'T':
                        try {
                            currentLabel.setForeground(Color.black);
                             if (!currentTextField.getText().isEmpty() && !otherTextField.getText().isEmpty()) {

                                int hour = 0;
                                int working = 0;

                                switch (currentLabel.getText()) {
                                    case "Hourly Salary":
                                        hour = Integer.parseInt(currentTextField.getText());
                                        working = Integer.parseInt(otherTextField.getText());
                                        break;
                                    case "Working Hour":
                                        hour = Integer.parseInt(otherTextField.getText());
                                        working = Integer.parseInt(currentTextField.getText());
                                }


                                double salary = hour * working;

                                salaryTextField.setText(Double.toString(salary));

                                if (working > 10) {
                                    double bonus = salary * 0.1;
                                    salary += bonus;
                                    bonusTextField.setText(Double.toString(bonus));
                                } else {
                                    bonusTextField.setText("0");
                                }
                                totalSalaryTextField.setText(Double.toString(salary));
                            } else if (!currentTextField.getText().isEmpty()) {
                                Integer.parseInt(currentTextField.getText());
                            }
                             mpfTextField.setText("0");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(panel, "Please input number", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                            currentTextField.requestFocus();
                        }
                        break;
                    case 'C':
                        try {
                            currentLabel.setForeground(Color.black);
                             if (!currentTextField.getText().isEmpty() && !otherTextField.getText().isEmpty()) {

                                int hour = 0;
                                int working = 0;

                                switch (currentLabel.getText()) {
                                    case "Hourly Salary":
                                        hour = Integer.parseInt(currentTextField.getText());
                                        working = Integer.parseInt(otherTextField.getText());
                                        break;
                                    case "Working Hour":
                                        hour = Integer.parseInt(otherTextField.getText());
                                        working = Integer.parseInt(currentTextField.getText());
                                }


                                double salary = hour * working;

                                salaryTextField.setText(Double.toString(salary));

                                double mpf = salary * 0.05;
                                mpfTextField.setText(Double.toString(mpf));

                                totalSalaryTextField.setText(Double.toString(salary + mpf));
                            } else if (!currentTextField.getText().isEmpty()) {
                                Integer.parseInt(currentTextField.getText());
                            }
                             bonusTextField.setText("0");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(panel, "Please input number", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                            currentTextField.requestFocus();
                        }
                        break;
                    default:
                        break;

                }
            }
            currentLabel.setForeground(Color.black);
        }
    }
    
    //Method for creating the right panel
    private JPanel createRightPanel(JPanel panel, DefaultTableModel model, JTable table) {
        
        /*GridBagConstraints gbc = new GridBagConstraints();
        /*gbc.fill = GridBagConstraints.HORIZONTAL;   
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JButton("Sort by School Code"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new JButton("Sort by Month"), gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(new JButton("Sort by Total"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        panel.add(new JButton("Sort by Staff ID"), gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.gridwidth = 4;
        //gbc.gridheight = 13;
        gbc.gridx = 0;
        gbc.gridy = 0;*/
        Object[] columns = {"Staff ID", "Name", "Assigned School Code", "Hourly Salary", "Working Hour", "Salary", "Bonus", "MPF", "Total Salary", "Month"};
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        JScrollPane pane = new JScrollPane(table);
        
        panel.add(pane);
        
        return panel;
    }
    
    private void clearInput(JTextField[] textfields, JComboBox combobox) {
        for (int i = 0; i < textfields.length; i ++) {
            textfields[i].setText("");
        }
        combobox.setSelectedIndex(0);
        textfields[0].requestFocus();
    }
    
    private boolean checkInput(JTextField[] textfield, JComboBox combobox) {
        for (int i = 0; i < textfield.length; i++) {
            if (!textfield[i].getText().isEmpty()) {
                if (combobox.getSelectedIndex() != 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void showEmployeeInfo(ArrayList<Employee> employees, DefaultTableModel model) {
        char type = employees.get(employees.size() - 1).getStaffID().toUpperCase().charAt(0);
        switch (type) {
            case 'T':
                Tutor tutor = (Tutor) employees.get(employees.size() - 1);
                    
                Object [] tutorData = {tutor.getStaffID(), tutor.getName(), tutor.getSchoolCode(), Double.toString(tutor.gethSalary()), Integer.toString(tutor.getWorkHour()), Double.toString(tutor.getSalary()), Double.toString(tutor.getBonus()), "N/A", Double.toString(tutor.getTotalSalary()), tutor.getMonth()};
                model.addRow(tutorData);
                    
                break;
            case 'C':
                Coordinator coordinator = (Coordinator) employees.get(employees.size() - 1);
                
                Object [] coordinatorData = {coordinator.getStaffID(), coordinator.getName(), coordinator.getSchoolCode(), Double.toString(coordinator.gethSalary()), Integer.toString(coordinator.getWorkHour()), Double.toString(coordinator.getSalary()), "N/A", Double.toString(coordinator.getMPF()), Double.toString(coordinator.getTotalSalary()), coordinator.getMonth()};
                model.addRow(coordinatorData);
                
                break;
            default:
                break;
            }
    }
}