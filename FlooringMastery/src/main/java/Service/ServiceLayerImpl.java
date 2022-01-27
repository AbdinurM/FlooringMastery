/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import FlooringMasteryDao.FlooringException;
import FlooringMasteryDao.OrderDao;
import FlooringMasteryDao.ProductDao;
import FlooringMasteryDao.TaxDao;
import FlooringMasteryDto.Order;
import FlooringMasteryDto.Product;
import FlooringMasteryDto.Tax;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Nur
 */
public class ServiceLayerImpl {
    OrderDao orderDao;
    ProductDao productDao;
    TaxDao taxDao;

    public ServiceLayerImpl(OrderDao orderDao, ProductDao productDao, TaxDao taxDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
    }
    
  

    
    public Order addOrder(Order newOrder) throws FlooringException {
        orderDao.addOrder(newOrder);
        return newOrder;
        
    }

    
    public List<Order> getAllOrder(LocalDate date) throws FlooringException {
        return orderDao.getAllOrder(date);
    }

    
    public Order getOrder(LocalDate date, int orderNumber) throws FlooringException {
        return orderDao.getOrder(date, orderNumber);
    }

    
    public void removeOrder(LocalDate date, int orderNumber) throws FlooringException {
       orderDao.removeOrder(date, orderNumber);
    }

    
    public Order editOrder(Order editOrder) throws FlooringException {
        orderDao.editOrder(editOrder);
        return editOrder;
    }

    
    public List<Product> getAllProduct() throws FlooringException {
        return productDao.getAllProduct();
    }

    
    public List<Tax> getAllTax() throws FlooringException {
        return taxDao.getAllTax();
    }
    
   public String loadMode() throws FlooringException, FileNotFoundException {
      return orderDao.loadMode();
    }   
    
}
