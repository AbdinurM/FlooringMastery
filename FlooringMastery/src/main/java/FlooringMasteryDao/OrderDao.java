/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMasteryDao;

import FlooringMasteryDto.Order;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Nur
 */
public interface OrderDao {
   public Order addOrder(Order newOrder) throws FlooringException ;

    public List<Order> getAllOrder(LocalDate date) throws FlooringException;

    public Order getOrder(LocalDate date, int orderNumber) throws FlooringException;

    public void removeOrder(LocalDate date, int orderNumber) throws FlooringException;
    
    public Order editOrder(Order editOrder) throws FlooringException; 
    
    public String loadMode() throws FileNotFoundException, FlooringException;
}
