/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMasteryDao;

import FlooringMasteryDto.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Nur
 */
public class OrderFileImpl implements OrderDao {

    Map<String, Order> Orders = new HashMap();
    public static final String DELIMITER = ",";
    
    private static final String Training_FILE = "Mode.txt";
    
    private Boolean Production;

    @Override
    public Order addOrder(Order newOrder) throws FlooringException {
        LocalDate date = newOrder.getDate();
        List<Order> orderList = new ArrayList<>();
        try {
            orderList = getAllOrder(date);
        } catch (FlooringException e) {
            //no file created
        }

        if (orderList.isEmpty()) {
            newOrder.setOrderNumber(1);
            orderList.add(newOrder);
            writeOrderFile(orderList, date);
        } else {
            int orderNum = orderList
                    .stream()
                    .mapToInt(p -> (p.getOrderNumber()))
                    .max().getAsInt() + 1;
            newOrder.setOrderNumber(orderNum);
            orderList.add(newOrder);
            writeOrderFile(orderList, date);
            return newOrder;

        }
        return newOrder;

    }

    @Override
    public List<Order> getAllOrder(LocalDate date) throws FlooringException {
        List<Order> orderList = new ArrayList<>();
        Scanner scanner;

        try {

            String directoryToFile = filingDate(date);
            scanner = new Scanner(new BufferedReader(new FileReader(directoryToFile)));

        } catch (FileNotFoundException ex) {
            throw new FlooringException("File Is NOT Found", ex);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(",");
            Order currentOrder = new Order();

            currentOrder.setDate(date);
            currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
            currentOrder.setCustomerName(currentTokens[1]);
            currentOrder.setState(currentTokens[2]);
            currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
            currentOrder.setProductType(currentTokens[4]);
            currentOrder.setArea(new BigDecimal(currentTokens[5]));
            currentOrder.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
            currentOrder.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
            currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
            currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
            currentOrder.setTax(new BigDecimal(currentTokens[10]));
            currentOrder.setTotal(new BigDecimal(currentTokens[11]));

            orderList.add(currentOrder);

        }
        scanner.close();

        return orderList;

    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) throws FlooringException {
        List<Order> filingList = getAllOrder(date);
        Order getOrder = getAllOrder(date)
                .stream()
                .filter(s -> s.getOrderNumber() == orderNumber)
                .findFirst().orElse(null);
        return getOrder;
    }

    @Override
    public void removeOrder(LocalDate date, int orderNumber) throws FlooringException {
        List<Order> filingList = getAllOrder(date);
        List<Order> removeOrder = filingList
                .stream()
                .filter(o -> o.getOrderNumber() != orderNumber)
                .collect(Collectors.toList());
        writeOrderFile(removeOrder, date);

    }

    @Override
    public Order editOrder(Order editOrder) throws FlooringException {
        LocalDate date = editOrder.getDate();
        int orderNum = editOrder.getOrderNumber();

        List<Order> directoryToFile = getAllOrder(date);
        directoryToFile = directoryToFile
                .stream()
                .filter(o -> o.getOrderNumber() != orderNum)
                .collect(Collectors.toList());
        
        directoryToFile.add(editOrder);
        
        writeOrderFile(directoryToFile, date);

        return editOrder;

    }

    public String filingDate(LocalDate date) {
        LocalDate dates = date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String format = dates.format(formatter);
        return "Order" + format + ".txt";

    }

    public void writeOrderFile(List<Order> orderList, LocalDate date) throws FlooringException {
        if(Production == true) {
        PrintWriter out;

        try {
            String directoryToFile = filingDate(date);

            out = new PrintWriter(new FileWriter(directoryToFile));
        } catch (IOException e) {
            throw new FlooringException(
                    "File Can NOT Be Saved", e);
        }

        orderList.stream().map(currentOrder -> {
            out.println(currentOrder.getOrderNumber() + DELIMITER
                    + currentOrder.getCustomerName() + DELIMITER
                    + currentOrder.getState() + DELIMITER
                    + currentOrder.getTaxRate() + DELIMITER
                    + currentOrder.getProductType() + DELIMITER
                    + currentOrder.getArea() + DELIMITER
                    + currentOrder.getCostPerSquareFoot() + DELIMITER
                    + currentOrder.getLaborCostPerSquareFoot() + DELIMITER
                    + currentOrder.getMaterialCost() + DELIMITER
                    + currentOrder.getLaborCost() + DELIMITER
                    + currentOrder.getTax() + DELIMITER
                    + currentOrder.getTotal());
                return currentOrder;
            }).forEachOrdered(_item -> {
                out.flush();
            });
        

        out.close();
        }
    }
    
    @Override
    public String loadMode() throws FileNotFoundException, FlooringException {
        
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(Training_FILE)));
        }
     catch(FileNotFoundException e) {
    throw new FlooringException("Could not save Order", e);
     }
        
        String fileChoice = scanner.nextLine();
        
        Production = fileChoice.equals("Production");
        
        return fileChoice;
    
}    
}
