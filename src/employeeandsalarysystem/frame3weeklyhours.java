package employeeandsalarysystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.table.DefaultTableModel;

public class frame3weeklyhours extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    public frame3weeklyhours() {
        setResizable(false);
        setTitle("Employee and Salary System");
        setIconImage(Toolkit.getDefaultToolkit().getImage(frame3weeklyhours.class.getResource("/employeeandsalarysystem_resources/ESS.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1170, 847);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Employee Number");
        model.addColumn("Last Name");
        model.addColumn("First Name");
        model.addColumn("Basic Salary");
        model.addColumn("Hourly Rate");

        // Load CSV data
        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("C:\\Users\\jireh angelo\\commision\\employeeandsalarysystem\\src\\employeeandsalarysystemdatabase\\employeeWEEKLYHOURS.csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            String csvSplitBy = ",";
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                model.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(70, 184, 1000, 248);
        contentPane.add(scrollPane);
    }
}
