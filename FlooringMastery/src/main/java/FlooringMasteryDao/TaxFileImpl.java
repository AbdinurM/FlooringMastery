/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMasteryDao;

import FlooringMasteryDto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Nur
 */
public class TaxFileImpl implements TaxDao {
Map<String, Tax> Taxes = new HashMap();
    public static final String TAX_FILE = "Taxes.txt";
    public static final String DELIMITER = ",";

@Override
    public void taxLoadFile() throws FlooringException {
        Scanner scanner;

        try {
            

            scanner = new Scanner(new BufferedReader(new FileReader(TAX_FILE)));

        } catch (FileNotFoundException ex) {
            throw new FlooringException("File Is NOT Found", ex);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(",");
           
            Tax currentTax = new Tax();
            currentTax.setStateName(currentTokens[0]);
            currentTax.setTaxRate(new BigDecimal(currentTokens[1]));
           
            
            Taxes.put(currentTax.getStateName(), currentTax);

        }
        scanner.close();

    }

@Override
    public List<Tax> getAllTax() throws FlooringException {
       taxLoadFile();
       return new ArrayList<>(Taxes.values());
    }
    
}
