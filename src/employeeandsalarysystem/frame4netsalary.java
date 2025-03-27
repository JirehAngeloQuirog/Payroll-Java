package employeeandsalarysystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frame4netsalary extends JFrame {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private JPanel contentPane;
private JTable table;
private JTextField textFieldBasicSalary;
private JTextField textFieldRiceSubsidy;
private JTextField textFieldPhoneAllowance;
private JTextField textFieldClothingAllowance;
private JTextField resultLabel;

public static void main(String[] args) {
	EventQueue.invokeLater(() -> {
        try {
        frame4netsalary frame = new frame4netsalary();
        	frame.setVisible(true);
        		} catch (Exception e) {
        			e.printStackTrace();
            }
        });
    }

    public frame4netsalary() {
        setTitle("Employee Salary System");
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(frame4netsalary.class.getResource("/employeeandsalarysystem_resources/ESS.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1132, 793);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblEmployeesWeeklyNet = new JLabel("EMPLOYEE'S WEEKLY NET SALARY");
        lblEmployeesWeeklyNet.setBounds(0, 56, 1118, 61);
        lblEmployeesWeeklyNet.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmployeesWeeklyNet.setFont(new Font("Tahoma", Font.BOLD, 50));
        contentPane.add(lblEmployeesWeeklyNet);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Employee #");
        model.addColumn("Last Name");
        model.addColumn("First Name");
        model.addColumn("Basic Salary");
        model.addColumn("Rice Subsidy");
        model.addColumn("Phone Allowance");
        model.addColumn("Clothing Allowance");

        table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.getColumnModel().getColumn(6).setPreferredWidth(150);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(70, 125, 1000, 248);
        contentPane.add(scrollPane);

        textFieldBasicSalary = new JTextField();
        textFieldBasicSalary.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textFieldBasicSalary.setBounds(434, 398, 279, 25);
        contentPane.add(textFieldBasicSalary);
        textFieldBasicSalary.setColumns(10);

        textFieldRiceSubsidy = new JTextField();
        textFieldRiceSubsidy.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textFieldRiceSubsidy.setColumns(10);
        textFieldRiceSubsidy.setBounds(434, 433, 279, 25);
        contentPane.add(textFieldRiceSubsidy);

        textFieldPhoneAllowance = new JTextField();
        textFieldPhoneAllowance.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textFieldPhoneAllowance.setColumns(10);
        textFieldPhoneAllowance.setBounds(434, 468, 279, 25);
        contentPane.add(textFieldPhoneAllowance);

        textFieldClothingAllowance = new JTextField();
        textFieldClothingAllowance.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textFieldClothingAllowance.setColumns(10);
        textFieldClothingAllowance.setBounds(434, 503, 279, 25);
        contentPane.add(textFieldClothingAllowance);

        JLabel lblBasicSalary = new JLabel("Basic Salary");
        lblBasicSalary.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblBasicSalary.setBounds(296, 396, 128, 27);
        contentPane.add(lblBasicSalary);

        JLabel lblRiceSub = new JLabel("Rice Subsidy");
        lblRiceSub.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblRiceSub.setBounds(290, 430, 134, 27);
        contentPane.add(lblRiceSub);

        JLabel lblPhoneAllowance = new JLabel("Phone Allowance");
        lblPhoneAllowance.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPhoneAllowance.setBounds(249, 465, 180, 27);
        contentPane.add(lblPhoneAllowance);

        JLabel lblClothingallowance = new JLabel("Clothing Allowance");
        lblClothingallowance.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblClothingallowance.setBounds(235, 501, 194, 27);
        contentPane.add(lblClothingallowance);

        resultLabel = new JTextField();
        resultLabel.setEditable(false);
        resultLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        resultLabel.setColumns(10);
        resultLabel.setBounds(434, 610, 279, 49);
        contentPane.add(resultLabel);

        JButton btnCalculate = new JButton("Calculate");
        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateGrossWeeklySalary();
            }
        });
        btnCalculate.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnCalculate.setBounds(502, 565, 154, 35);
        contentPane.add(btnCalculate);

        JButton btnMainMenu = new JButton("Main Menu");
        btnMainMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame2 jf2 = new frame2();
				jf2.setVisible(true); // Use setVisible() instead of show()
				
				dispose(); // close current frame
        	}
        });
        btnMainMenu.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnMainMenu.setBounds(916, 383, 154, 35);
        contentPane.add(btnMainMenu);
        
        JLabel lblNetWeeklySalary = new JLabel("Net Weekly Salary");
        lblNetWeeklySalary.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNetWeeklySalary.setBounds(235, 620, 194, 27);
        contentPane.add(lblNetWeeklySalary);

        // Read data from CSV and populate the table model
        String line = "";
        String csvSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\jireh angelo\\commision\\employeeandsalarysystem\\src\\employeeandsalarysystemdatabase\\employeeGrossSalary.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                model.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateGrossWeeklySalary() {
        // Calculate net weekly salary
        double basicSalary = Double.parseDouble(textFieldBasicSalary.getText());
        double riceSubsidy = Double.parseDouble(textFieldRiceSubsidy.getText());
        double phoneAllowance = Double.parseDouble(textFieldPhoneAllowance.getText());
        double clothingAllowance = Double.parseDouble(textFieldClothingAllowance.getText());
        
        // Assuming gross semi-monthly rate is 2 * gross weekly salary
        double grossSemiMonthlyRate = calculateGrossWeeklySalary(basicSalary, riceSubsidy, phoneAllowance, clothingAllowance);
        double grossWeeklySalary = grossSemiMonthlyRate / 2;

        double netWeeklySalary = grossWeeklySalary - calculateWithholdingTax(grossSemiMonthlyRate)
                - calculateSSSContribution(basicSalary) - calculatePagIBIGContribution(basicSalary);

        resultLabel.setText(String.format("Net Weekly Salary: Php %.2f", netWeeklySalary));
    }

    private double calculateGrossWeeklySalary(double basicSalary, double riceSubsidy, double phoneAllowance, double clothingAllowance) {
        // Implement your calculation logic here
        // Example:
        return basicSalary + riceSubsidy + phoneAllowance + clothingAllowance;
    }

    private double calculateWithholdingTax(double grossSemiMonthlyRate) {
        // Implement your calculation logic here
        // Example:
        // For the sake of completion, I'm providing a sample calculation based on the provided tax brackets
        double withholdingTax;
        if (grossSemiMonthlyRate <= 20832) {
            withholdingTax = 0;
        } else if (grossSemiMonthlyRate <= 33332) {
            withholdingTax = (grossSemiMonthlyRate - 20832) * 0.20;
        } else if (grossSemiMonthlyRate <= 66667) {
            withholdingTax = 2500 + (grossSemiMonthlyRate - 33332) * 0.25;
        } else if (grossSemiMonthlyRate <= 166667) {
            withholdingTax = 10833.33 + (grossSemiMonthlyRate - 66667) * 0.30;
        } else if (grossSemiMonthlyRate <= 666667) {
            withholdingTax = 40833.33 + (grossSemiMonthlyRate - 166667) * 0.32;
        } else {
            withholdingTax = 200833.33 + (grossSemiMonthlyRate - 666667) * 0.35;
        }
        return withholdingTax / 2; // Assuming withholding tax is applied semi-monthly
    }

    private double calculateSSSContribution(double basicSalary) {
        // Implement your calculation logic here
        // Example:
        // Assuming a simplified calculation based on the provided SSS contribution table
        if (basicSalary <= 3250) {
            return 135 / 2; // Assuming it's applied semi-monthly
        } else if (basicSalary <= 20000) {
            return 157.5 / 2;
        } else {
            // Handle other ranges
            // You can parse the SSS contribution table and calculate accordingly
            return 0;
        }
    }

    private double calculatePagIBIGContribution(double basicSalary) {
        // Implement your calculation logic here
        // Example:
        // Assuming a simplified calculation based on the provided PagIBIG contribution table
        if (basicSalary <= 1000) {
            return basicSalary * 0.01; // Employee's contribution rate
        } else {
            return basicSalary * 0.02;
        }
    }
}
       


