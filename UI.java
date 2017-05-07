import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public final class UI extends JFrame {
    public static final int width = 1000;
    public static final int height = 500;
    public static final ImageIcon img = new ImageIcon("icon.png");
    private ArrayList<Employee> employee = new ArrayList<>();
    
    public UI() {
        setSize(width, height);
        setTitle("INT2014 Assignment"); 
        setIconImage(img.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(new JLabel("HR System of After-School Tutorial Class", JLabel.CENTER));
        
        JPanel contentPanel = new JPanel(new GridLayout(1, 2));
        
        JPanel rPanel = new JPanel();
        
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable();
        rPanel = createRightPanel(rPanel, model, table);
        
        JPanel lPanel = new JPanel(new GridLayout(12, 2));
        lPanel = createLeftPanel(lPanel, model, table);
        
        
        
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
    
    public JPanel createLeftPanel(JPanel panel, DefaultTableModel model, JTable table) {
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
            
            if (i == 0) {
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
            } else if (i == 1) {
                buttons[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int i = table.getSelectedRow();
                        if(i >= 0){
                            model.removeRow(i);
                        } else{
                            System.out.println("Delete Error");
                        }
                    }
                });
            }
            
            panel.add(buttons[i]);
        }
        return panel;
    }
    
    class textFieldFocusListener implements FocusListener {
        private JLabel label;
        
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
        private JLabel staffIDLabel;
        private JTextField staffIDTextField;
        private JTextField bonusTextField;
        private JTextField mpfTextField;
        private JTextField salaryTextField;
        private JTextField totalSalaryTextField;
        private JPanel panel;
        
        
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
        private JLabel currentLabel;
        private JTextField currentTextField;
        private JLabel otherLabel;
        private JTextField otherTextField;
        private JTextField salaryTextField;
        private JTextField bonusTextField;
        private JTextField mpfTextField;
        private JTextField totalSalaryTextField;
        private JTextField staffIDTextField;
        private JPanel panel;
        
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
                        } catch (Exception ex) {
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
                        } catch (Exception ex) {
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

    public JPanel createRightPanel(JPanel panel, DefaultTableModel model, JTable table) {
        
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
        JScrollPane pane = new JScrollPane(table);
        
        panel.add(pane);
        
        return panel;
    }
    
    public void clearInput(JTextField[] textfields, JComboBox combobox) {
        for (int i = 0; i < textfields.length; i ++) {
            textfields[i].setText("");
        }
        combobox.setSelectedIndex(0);
        textfields[0].requestFocus();
    }
    
    public boolean checkInput(JTextField[] textfield, JComboBox combobox) {
        for (int i = 0; i < textfield.length; i++) {
            if (!textfield[i].getText().isEmpty()) {
                if (combobox.getSelectedIndex() != 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void showEmployeeInfo(ArrayList<Employee> employees, DefaultTableModel model) {
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