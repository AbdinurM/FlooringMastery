/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import FlooringMasteryDao.FlooringException;
import FlooringMasteryDto.Order;
import FlooringMasteryDto.Product;
import FlooringMasteryDto.Tax;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Nur
 */
public interface ServiceLayer {
    public Order addOrder(Order newOrder)throws FlooringException;

    public List<Order> getAllOrder(LocalDate date) throws FlooringException;

    public Order getOrder(LocalDate date, int orderNumber) throws FlooringException;

    public void removeOrder(LocalDate date, int orderNumber) throws FlooringException;
    
    public Order editOrder(Order editOrder) throws FlooringException; 
    
    public List<Product>getAllProduct()  throws FlooringException; 
    
     public List<Tax>getAllTax()  throws FlooringException;
}
