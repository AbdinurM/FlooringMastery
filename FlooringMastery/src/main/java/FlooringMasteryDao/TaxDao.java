/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMasteryDao;

import FlooringMasteryDto.Tax;
import java.util.List;

/**
 *
 * @author Nur
 */
public interface TaxDao {
     public void taxLoadFile() throws FlooringException;
    public List<Tax> getAllTax() throws FlooringException;   
}
