/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import FlooringMasteryDto.Order;
import FlooringMasteryDto.Product;
import FlooringMasteryDto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Nur
 */
public class FlooringMasteryView {
        UserIO io;

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print(" <<Flooring Program>>");
        io.print("1.Display orders");
        io.print("2. Add an Order");
        io.print("3. Edit An Order");
        io.print("4. Remove an Order");
        io.print("5. Quit");
          return io.readInt("Please select from the above choices.", 1, 5);
    }

    public Order getNewOrderInfo(List<Product> getAllProduct, List<Tax> getAllTax) {
        LocalDate date = io.readLocalDate("Please Enter Date:");
        String customerName = io.readString("Please Enter Your Name:");
        Tax state = getTax(getAllTax);
        Product productType = getProduct(getAllProduct);
        BigDecimal area = io.readBigDecimal("Please Enter Area");

        Order currentOrder = new Order();
        currentOrder.setDate(date);
        currentOrder.setCustomerName(customerName);
        currentOrder.setOrderNumber(currentOrder.getOrderNumber() + 1);
        currentOrder.setState(state.getStateName());
        currentOrder.setTaxRate(state.getTaxRate());
        currentOrder.setProductType(productType.getProductType());
        currentOrder.setArea(area);
        currentOrder.setCostPerSquareFoot(productType.getCostPerSquareFoot());
        currentOrder.setLaborCostPerSquareFoot(productType.getLaborCostPerSquareFoot());

        return currentOrder;

    }

    public Product getProduct(List<Product> getAllProduct) {
        Product userProductChoice = null;
        boolean validInput = true;
        while (validInput) {
            String product = io.readString("Please Enter A Product:");
            
            List<Product> products = getAllProduct
                    .stream()
                    .filter(p -> product.equalsIgnoreCase(p.getProductType()))
                    .collect(Collectors.toList());

            if (!products.isEmpty()) {
                userProductChoice = products.get(0);
                validInput = false;
            }

        }
        return userProductChoice;

    }

    public Tax getTax(List<Tax> getAllTax) {
        Tax userTaxChoice = null;
        boolean validInput = true;
        while (validInput) {
            String tax = io.readString("Please Enter The State - Use State Initials:");
            
            List<Tax> taxes = getAllTax
                    .stream()
                    .filter(t -> tax.equalsIgnoreCase(t.getStateName()))
                    .collect(Collectors.toList());

            if (!taxes.isEmpty()) {
                userTaxChoice = taxes.get(0);
                validInput = false;
            }

        }
        return userTaxChoice;

    }

    public LocalDate getLocalDate() {
        LocalDate date = io.readLocalDate("Please Enter Order Date. Use MM/dd/yyyy FORMAT");
        return date;
    }

    public int getOrderNumber() {
        int orderNumber = io.readInt("Please Enter Your Order Number");
        return orderNumber;
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Order successfully REMOVED. Please hit enter to continue");
    }

    public void displayEditBanner() {
        io.readString("==EDIT ORDER==");
    }

    public void displayEditSuccessBanner() {
        io.readString("Order successfully EDITED. Please hit enter to continue");
    }

    public void displayAddBanner() {
        io.print("==ADD ORDER==");

    }

    public void displayAddSuccessBanner() {
        io.readString("Order successfully ADDED.  Please hit enter to continue");
    }

    public void displayUnknownCommandBanner() {
        io.readString("Unknown Command");
    }

    public void displayAllBanner() {
        io.readString("==DISPLAY ALL ORDERS");
    }

    public void displayRemoveBanner() {
        io.readString("==REMOVE ORDER==");

    }

    public void displayExitBanner() {
        io.print("GoodBye!");
    }

    public void displayGetOrder(Order order) {
        io.print("Order Number :" + order.getOrderNumber());
        io.print("Customer Name :" + order.getCustomerName());
        io.print("State :" + order.getState());
        io.print("Product Type :" + order.getProductType());
        io.print("Area :" + order.getArea());
    }

    public Product editProduct(List<Product> getAllProduct, String v) {
        Product userProductChoice = null;
        boolean validInput = true;
        while (validInput) {
            String product = io.readString("Please Enter A Product"+ "(" + v + ")");
            if (product.isEmpty()) {
                validInput = false;
            }
            
            List<Product> products = getAllProduct
                    .stream()
                    .filter(p -> product.equalsIgnoreCase(p.getProductType()))
                    .collect(Collectors.toList());
            if (!products.isEmpty()) {
                userProductChoice = products.get(0);
                validInput = false;

            }
        }
        return userProductChoice;
    }

    public Order editOrder(List<Product> getAllProduct, List<Tax> getAllTax, Order editOrder) {
        String customerName = io.readString("Please Enter Your Name" + "("+(editOrder.getCustomerName()+")"));
        Tax state = editTax(getAllTax, editOrder.getState());
        Product productType = editProduct(getAllProduct, editOrder.getProductType());
        String area = io.readString("Please Enter Area" + "("+(editOrder.getArea()+")"));

        if (customerName.isEmpty()) {
            customerName = editOrder.getCustomerName();
        }
        if (state != null)
        {
            editOrder.setState(state.getStateName());
            editOrder.setTaxRate(state.getTaxRate());
        }
        if (productType != null)
        {
            editOrder.setProductType(productType.getProductType());
            editOrder.setCostPerSquareFoot(productType.getCostPerSquareFoot());
            editOrder.setLaborCostPerSquareFoot(productType.getLaborCostPerSquareFoot());
            
        }
                
                
        if ( !area.toString().isEmpty()) {
            BigDecimal ar = new BigDecimal(area);
            editOrder.setArea(ar);
        }
        
        
            
        Order currentOrder = new Order();
        currentOrder.setCustomerName(customerName);
        currentOrder.setOrderNumber(editOrder.getOrderNumber());
        currentOrder.setState(editOrder.getState());
        currentOrder.setTaxRate(editOrder.getTaxRate());
        currentOrder.setProductType(editOrder.getProductType());
        currentOrder.setArea(editOrder.getArea());
        currentOrder.setLaborCostPerSquareFoot(editOrder.getCostPerSquareFoot());
        currentOrder.setCostPerSquareFoot(editOrder.getLaborCostPerSquareFoot());

        return currentOrder;

    }

    public Tax editTax(List<Tax> getAllTax, String s) {
        Tax userTaxChoice = null;
        boolean validInput = true;
        while (validInput) {
            String state = io.readString("Please Enter The State You Live In:" + "(" + s + ")");
            if (state.isEmpty()) {
                validInput = false;
            }
            List<Tax> taxes = getAllTax
                    .stream()
                    .filter(t -> state.equalsIgnoreCase(t.getStateName()))
                    .collect(Collectors.toList());

            if (!taxes.isEmpty()) {
                userTaxChoice = taxes.get(0);
                validInput = false;
            }
        }
        return userTaxChoice;
    }

    public void displayOrderList(List<Order> orderList) {
        for (Order currentOrder : orderList) {
            io.print(currentOrder.getOrderNumber() + ","
                    + currentOrder.getCustomerName() + ","
                    + currentOrder.getState() + ","
                    + currentOrder.getTaxRate() + ","
                    + currentOrder.getProductType() + ","
                    + currentOrder.getCostPerSquareFoot() + ","
                    + currentOrder.getLaborCostPerSquareFoot() + ","
                    + currentOrder.getTax() + ","
                    + currentOrder.getArea() + ","
                    + currentOrder.getMaterialCost() + ","
                    + currentOrder.getLaborCost() + ","
                    + currentOrder.getTotal());

        }
        io.readString("Please hit enter to continue.");
    }

    public void errorMessage(String ex) {
        io.print("ERROR");
        io.print(ex);
    }

    public void orderNotFound() {
        io.print("Order NOT Found");
    }

    public void checkDataMode(String fileCheck) {
                
        if(fileCheck.equals("Production")) {
            io.print("You are in Production Mode orders will be saved!");
        }
        else {
        io.print("You are in Training Mode order will not be saved!");
    }
        

}
}
