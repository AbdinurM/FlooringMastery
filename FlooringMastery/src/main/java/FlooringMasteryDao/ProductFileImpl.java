/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMasteryDao;

import FlooringMasteryDto.Product;
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
public class ProductFileImpl implements ProductDao {
 
    Map<String, Product> Products = new HashMap();
    public static final String PRODUCT_FILE = "Products.txt";
    public static final String DELIMITER = ",";

    
    @Override
    public void productLoadFile() throws FlooringException {
        Scanner scanner;

        try {
            

            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));

        } catch (FileNotFoundException ex) {
            throw new FlooringException("File Is NOT Found", ex);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(",");
           
            Product currentProduct = new Product();
            currentProduct.setProductType(currentTokens[0]);
            currentProduct.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));
            currentProduct.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[2]));
            
            Products.put(currentProduct.getProductType(), currentProduct);

        }
        scanner.close();

    }

    /**
     *
     * @return
     * @throws FlooringException
     */
    @Override
    public List<Product> getAllProduct() throws FlooringException {
       productLoadFile();
       return new ArrayList<>(Products.values());
    }
   
}
