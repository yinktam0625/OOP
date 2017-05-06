import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    public static final int width = 800;
    public static final int height = 500;
    public static final ImageIcon img = new ImageIcon("icon.png");
    
    public UI() {
        setSize(width, height);
        setTitle("INT2014 Assignment"); 
        setIconImage(img.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        JLabel title = new JLabel("HR System of after-school tutorial class");
        add(title, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JPanel lPanel = new JPanel(new GridLayout(15, 2));
        lPanel = createLeftPanel(lPanel);
        add(lPanel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        JPanel rPanel = new JPanel(new GridBagLayout());
        lPanel = createRightPanel(rPanel);
        add(rPanel, gbc);
        
    }
    
    public JPanel createLeftPanel(JPanel panel) {
        JLabel labels[] = new JLabel[13];
        String [] labelNames = {"Staff ID", "Name", "Age", "Gender", 
                "Mobile Number", "Major", "Assigned School Code", "Hourly Salary", 
                            "Working Hour", "Salary", "Bonus", "Total Salary", "Month"};
        
        JTextField textfields[] = new JTextField[13];
        String [] textfieldNames = {"staffIDText", "nameText", "ageText", "genderText", 
                        "mobileNumberText", "majorText", "assignedSchoolCodeText", "hourlySalaryText", 
                                        "workingHourText", "salaryText", "bonusText", "totalSalaryText"};
        
        JButton buttons[] = new JButton[4];
        String [] buttonNames = {"Add", "Delete", "Save", "Load"};
        
        final DefaultComboBoxModel months = new DefaultComboBoxModel();
        String [] monthsName = {"January", "Feburary", "March", "April", 
                                        "May", "June", "July", "August", 
                                        "September", "October", "November", "December"};
        
        for (int i = 0; i < monthsName.length; i++) {
            months.addElement(monthsName[i]);
        }
        
        final JComboBox monthComboBox = new JComboBox(months);
        
        for (int i = 0; i < labelNames.length; i++) {
            labels[i] = new JLabel(labelNames[i]);
            panel.add(labels[i]);
            
            if (i <= 11) {
                panel.add(textfields[i] = new JTextField(5));
            } else {
                panel.add(monthComboBox);
            }
        }
        
        for (int i = 0; i < buttonNames.length; i++) {
            buttons[i] = new JButton(buttonNames[i]);
            panel.add(buttons[i]);
        }
        
        return panel;
    }
    
    public void setButtonActions(JButton[] buttons) {
        
    }
    
    public JPanel createRightPanel(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;   
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
        gbc.gridwidth = 4;
        gbc.gridheight = 13;
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        JTable table = createTable();
        panel.add(new JScrollPane(table), gbc);
        
        return panel;
    }
    
    public JTable createTable() {
        
        String [] columns = {"#", "Staff ID", "Name", "Age", "Gender", 
                "Mobile Number", "Major", "Assigned School Code", "Hourly Salary", 
                            "Working Hour", "Salary", "Bonus", "Total Salary", "Month"};
        
        Object[][] data = new Object[][] {
            {"#", "Staff ID", "Name", "Age", "Gender", 
                "Mobile Number", "Major", "Assigned School Code", "Hourly Salary", 
                            "Working Hour", "Salary", "Bonus", "Total Salary", "Month"},
            {"#", "Staff ID", "Name", "Age", "Gender", 
                "Mobile Number", "Major", "Assigned School Code", "Hourly Salary", 
                            "Working Hour", "Salary", "Bonus", "Total Salary", "Month"},
            {"#", "Staff ID", "Name", "Age", "Gender", 
                "Mobile Number", "Major", "Assigned School Code", "Hourly Salary", 
                            "Working Hour", "Salary", "Bonus", "Total Salary", "Month"},
            {"#", "Staff ID", "Name", "Age", "Gender", 
                "Mobile Number", "Major", "Assigned School Code", "Hourly Salary", 
                            "Working Hour", "Salary", "Bonus", "Total Salary", "Month"}
        };
        
        JTable table = new JTable(data, columns);
        
        return table;
    }
    
}
