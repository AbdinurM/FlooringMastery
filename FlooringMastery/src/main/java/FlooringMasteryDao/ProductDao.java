/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMasteryDao;

import FlooringMasteryDto.Product;
import java.util.List;

/**
 *
 * @author Nur
 */
public interface ProductDao {
    public void productLoadFile() throws FlooringException;
    public List<Product> getAllProduct() throws FlooringException; 
}
