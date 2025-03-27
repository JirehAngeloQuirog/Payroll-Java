package employeeandsalarysystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class frame2 extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private List<Employee> employees = new ArrayList<>();
    private JButton btnProcessPayroll;

    public frame2() {
        setResizable(true);
        setTitle("Employee and Salary System");
        setIconImage(Toolkit.getDefaultToolkit().getImage(frame2.class.getResource("/employeeandsalarysystem_resources/ESS.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1300, 800);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 192));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("EMPLOYEE INFORMATION");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 50));
        lblTitle.setBounds(300, 20, 700, 61);
        contentPane.add(lblTitle);

        // Create table model
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Employee #");
        model.addColumn("Last Name");
        model.addColumn("First Name");
        model.addColumn("Birthday");
        model.addColumn("Address");
        model.addColumn("Phone Number");
        model.addColumn("SSS #");
        model.addColumn("Philhealth #");
        model.addColumn("TIN #");
        model.addColumn("Pag-ibig #");
        model.addColumn("Status");
        model.addColumn("Position");
        model.addColumn("Immediate Supervisor");
        model.addColumn("Basic Salary");
        model.addColumn("Rice Subsidy");
        model.addColumn("Phone Allowance");
        model.addColumn("Clothing Allowance");
        model.addColumn("Gross Semi-monthly Rate");
        model.addColumn("Hourly Rate");

        // Load employee data
        loadEmployeeData(model);

        // Create table with improved formatting
        table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                ((JComponent)comp).setBorder(new EmptyBorder(5, 8, 5, 8));
                return comp;
            }
        };
        
        table.setRowHeight(30);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(250);
        table.getColumnModel().getColumn(5).setPreferredWidth(120);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(100);
        table.getColumnModel().getColumn(8).setPreferredWidth(100);
        table.getColumnModel().getColumn(9).setPreferredWidth(100);
        table.getColumnModel().getColumn(10).setPreferredWidth(80);
        table.getColumnModel().getColumn(11).setPreferredWidth(150);
        table.getColumnModel().getColumn(12).setPreferredWidth(200);
        table.getColumnModel().getColumn(13).setPreferredWidth(120);
        table.getColumnModel().getColumn(14).setPreferredWidth(100);
        table.getColumnModel().getColumn(15).setPreferredWidth(120);
        table.getColumnModel().getColumn(16).setPreferredWidth(120);
        table.getColumnModel().getColumn(17).setPreferredWidth(150);
        table.getColumnModel().getColumn(18).setPreferredWidth(100);

        // Enable sorting
        table.setAutoCreateRowSorter(true);

        // Add alternating row colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, 
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, 
                    hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 240, 240));
                }
                return c;
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    btnProcessPayroll.setEnabled(table.getSelectedRow() >= 0);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 100, 1200, 500);
        contentPane.add(scrollPane);

        // Add Process Payroll button
        btnProcessPayroll = new JButton("Process Payroll");
        btnProcessPayroll.setEnabled(false);
        btnProcessPayroll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processSelectedEmployee();
            }
        });
        btnProcessPayroll.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnProcessPayroll.setBounds(950, 620, 250, 50);
        contentPane.add(btnProcessPayroll);

        // Add Back button
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new frame1().setVisible(true);
            }
        });
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnBack.setBounds(50, 620, 150, 50);
        contentPane.add(btnBack);
    }

    private void loadEmployeeData(DefaultTableModel model) {
        String csvFile = "C:\\Users\\jireh angelo\\commision\\employeeandsalarysystem\\src\\employeeandsalarysystemdatabase\\employeedetails.csv";
        String line;
        String csvSplitBy = ",";
    
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip header if exists
            if (br.ready()) br.readLine();
            
            while ((line = br.readLine()) != null) {
                try {
                    String[] data = line.split(csvSplitBy);
                    if (data.length >= 20) {
                        // First try column 13 (Basic Salary), if zero try column 19
                        double basicSalary = safeParseDouble(data[13]);
                        if (basicSalary == 0) {
                            basicSalary = safeParseDouble(data[19]);
                        }
                        
                        model.addRow(new Object[]{
                            data[0],  // Employee Number
                            data[1],  // Last Name
                            data[2],  // First Name
                            data[3],  // Birthday
                            data[4],  // Address
                            data[5],  // Phone num
                            data[6],  // SSS #
                            data[7],  // Philhealth #
                            data[8],  // TIN #
                            data[9],  // Pag-ibig #
                            data[10], // Status
                            data[11], // Position
                            data[12], // Immediate Supervisor
                            formatCurrency(basicSalary), // Basic Salary
                            formatCurrency(data[14]),    // Rice Subsidy
                            formatCurrency(data[15]),    // Phone Allowance
                            formatCurrency(data[16]),    // Clothing Allowance
                            formatCurrency(data[17]),    // Gross Semi-monthly Rate
                            formatCurrency(data[18])     // Hourly Rate
                        });
                        
                        // Create employee objects with proper salary
                        int id = safeParseInt(data[0]);
                        String fullName = data[2] + " " + data[1];
                        String position = data[11];
                        
                        employees.add(new Employee(id, fullName, basicSalary, position) {
                            @Override
                            public double calculateBasicPay() {
                                return salary;
                            }
                            @Override
                            public double calculateDeductions() {
                                // 10% of salary for deductions
                                return salary * 0.10;
                            }
                            @Override
                            public double calculateBonuses() {
                                // 5% of salary as bonus
                                return salary * 0.05;
                            }
                            @Override
                            public double calculatePay() {
                                return salary + calculateBonuses() - calculateDeductions();
                            }
                        });
                    }
                } catch (Exception e) {
                    System.err.println("Error processing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading employee data: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private int safeParseInt(String value) {
        try {
            // Remove any non-numeric characters
            String cleanValue = value.replaceAll("[^\\d]", "");
            return Integer.parseInt(cleanValue);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    private double safeParseDouble(String value) {
        try {
            // Remove any non-numeric characters except decimal point
            String cleanValue = value.replaceAll("[^\\d.]", "");
            return Double.parseDouble(cleanValue);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private String formatCurrency(String value) {
        try {
            double amount = safeParseDouble(value);
            return formatCurrency(amount);
        } catch (Exception e) {
            return "N/A";
        }
    }

    private String formatCurrency(double amount) {
        return "₱" + String.format("%,.2f", amount);
    }

    private String getPositionFromTable(int rowIndex) {
        try {
            int positionCol = -1;
            for (int i = 0; i < table.getColumnCount(); i++) {
                if (table.getColumnName(i).equalsIgnoreCase("Position")) {
                    positionCol = i;
                    break;
                }
            }
            
            if (positionCol == -1) {
                return "Position Not Found";
            }
            
            Object value = table.getValueAt(rowIndex, positionCol);
            return value != null ? value.toString() : "Unknown";
        } catch (Exception e) {
            System.err.println("Error getting position: " + e.getMessage());
            return "Unknown";
        }
    }

    private void processSelectedEmployee() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0 && selectedRow < employees.size()) {
            Employee employee = employees.get(selectedRow);
            
            try {
                // Calculate payroll components
                double basicPay = employee.calculateBasicPay();
                double bonuses = employee.calculateBonuses();
                double deductions = employee.calculateDeductions();
                double grossPay = basicPay + bonuses;
                double taxableIncome = grossPay - deductions;
                double tax = new TaxDeduction().computeTax(taxableIncome);
                double netPay = grossPay - deductions - tax;
                
                // Create detailed breakdown
                double sssDeduction = basicPay * 0.045;
                double philhealthDeduction = basicPay * 0.025;
                double pagibigDeduction = basicPay * 0.02;
                
                // Create HTML message
                String message = String.format("<html>"
                    + "<h2>Payroll Processed Successfully</h2>"
                    + "<b>Employee:</b> %s<br>"
                    + "<b>Position:</b> %s<br><br>"
                    + "<b>Basic Pay:</b> %s<br>"
                    + "<b>Bonuses:</b> %s<br>"
                    + "<b>Gross Salary:</b> %s<br><br>"
                    + "<u>Deductions:</u><br>"
                    + "SSS: %s (4.5%%)<br>"
                    + "Philhealth: %s (2.5%%)<br>"
                    + "Pag-ibig: %s (2%%)<br>"
                    + "<b>Total Deductions:</b> %s<br><br>"
                    + "<b>Taxable Income:</b> %s<br>"
                    + "<b>Withholding Tax:</b> %s<br><br>"
                    + "<h3>NET PAY: %s</h3>"
                    + "</html>",
                    employee.getName(),
                    getPositionFromTable(selectedRow),
                    formatCurrency(basicPay),
                    formatCurrency(bonuses),
                    formatCurrency(grossPay),
                    formatCurrency(sssDeduction),
                    formatCurrency(philhealthDeduction),
                    formatCurrency(pagibigDeduction),
                    formatCurrency(deductions),
                    formatCurrency(taxableIncome),
                    formatCurrency(tax),
                    formatCurrency(netPay)
                );
                
                JOptionPane.showMessageDialog(
                    this,
                    message,
                    "Payroll Processed",
                    JOptionPane.INFORMATION_MESSAGE
                );
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                    this,
                    "Error processing payroll: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame2 frame = new frame2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}