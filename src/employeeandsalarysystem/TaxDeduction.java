package employeeandsalarysystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaxDeduction {
    private List<TaxBracket> taxBrackets;

    public TaxDeduction() {
        this.taxBrackets = loadTaxBracketsFromCSV();
    }

    public double computeTax(double monthlySalary) {
        double annualSalary = monthlySalary * 12;
        double tax = 0;
        
        for (TaxBracket bracket : taxBrackets) {
            if (annualSalary > bracket.lowerBound) {
                double taxableAmount = Math.min(annualSalary, bracket.upperBound) - bracket.lowerBound;
                tax += bracket.baseTax + (taxableAmount * bracket.rate);
            }
        }
        
        return tax / 12; // Convert annual tax to monthly
    }

    private List<TaxBracket> loadTaxBracketsFromCSV() {
        List<TaxBracket> brackets = new ArrayList<>();
        String csvFile = "C:\\Users\\jireh angelo\\commision\\employeeandsalarysystem\\src\\employeeandsalarysystemdatabase\\copyofwitholdingtax.csv";
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            Pattern rangePattern = Pattern.compile("\"?(\\d{1,3}(?:,\\d{3})*)(?: and below| to below (\\d{1,3}(?:,\\d{3})*))?\"?");
            
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Monthly Rate") || line.startsWith("NOTE") || 
                    line.startsWith("SAMPLE") || line.startsWith("Calculation") || 
                    line.trim().isEmpty()) {
                    continue;
                }
                
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (parts.length >= 2) {
                    String range = parts[0].trim();
                    String formula = parts[1].trim().replace("\"", "");
                    
                    Matcher matcher = rangePattern.matcher(range);
                    if (matcher.find()) {
                        double lowerBound = parseNumber(matcher.group(1));
                        double upperBound = matcher.group(2) != null ? parseNumber(matcher.group(2)) : Double.MAX_VALUE;
                        
                        double baseTax = 0;
                        double rate = 0;
                        
                        if (formula.equalsIgnoreCase("No withholding tax")) {
                            baseTax = 0;
                            rate = 0;
                        } else {
                            // CORRECTED REGEX PATTERN:
                            Pattern formulaPattern = Pattern.compile("\"?([\\d,]+)(?: plus)? (\\d+)% in excess of ([\\d,]+)\"?");
                            Matcher formulaMatcher = formulaPattern.matcher(formula);
                            
                            if (formulaMatcher.find()) {
                                baseTax = parseNumber(formulaMatcher.group(1));
                                rate = Double.parseDouble(formulaMatcher.group(2)) / 100;
                            }
                        }
                        
                        brackets.add(new TaxBracket(lowerBound, upperBound, rate, baseTax));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Fallback to default brackets if CSV fails
            brackets.add(new TaxBracket(0, 20832, 0, 0));
            brackets.add(new TaxBracket(20833, 33332, 0.20, 0));
            brackets.add(new TaxBracket(33333, 66666, 0.25, 2500));
            brackets.add(new TaxBracket(66667, 166666, 0.30, 10833));
            brackets.add(new TaxBracket(166667, 666666, 0.32, 40833.33));
            brackets.add(new TaxBracket(666667, Double.MAX_VALUE, 0.35, 200833.33));
        }
        
        return brackets;
    }

    private double parseNumber(String numberStr) {
        return Double.parseDouble(numberStr.replace(",", ""));
    }

    private static class TaxBracket {
        double lowerBound;
        double upperBound;
        double rate;
        double baseTax;

        public TaxBracket(double lowerBound, double upperBound, double rate, double baseTax) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
            this.rate = rate;
            this.baseTax = baseTax;
        }
    }
}