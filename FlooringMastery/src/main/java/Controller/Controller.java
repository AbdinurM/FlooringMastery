/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FlooringMasteryDao.FlooringException;
import FlooringMasteryDto.Order;
import Service.ServiceLayerImpl;
import UI.FlooringMasteryView;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Nur
 */
public class Controller {
     FlooringMasteryView view;
    ServiceLayerImpl service;

    public Controller(FlooringMasteryView view, ServiceLayerImpl daoService) {
        this.view = view;
        this.service = daoService;

    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {

            try {
                try {
                    diplayDataSetting();
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found");
                }
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        keepGoing = false;
                        exitMessage();
                        break;
                    default:
                        unknownCommand();
                }
            } catch (FlooringException ex) {
                view.errorMessage(ex.getMessage());
            }

        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void displayOrders() throws FlooringException {
        try {
            LocalDate date = view.getLocalDate();
            List<Order> getAllOrder = service.getAllOrder(date);
            view.displayOrderList(getAllOrder);
        } catch (FlooringException ex) {
            view.errorMessage(ex.getMessage());
        }

    }

    private void addOrder() throws FlooringException {
        view.displayAddBanner();
        Order currentOrder = view.getNewOrderInfo(service.getAllProduct(), service.getAllTax());
        service.addOrder(currentOrder);
        view.displayAddSuccessBanner();
    }

    private void removeOrder() throws FlooringException {
        view.displayRemoveBanner();
        LocalDate removeOrder = view.getLocalDate();
        List<Order> getAllOrder = service.getAllOrder(removeOrder);
        view.displayOrderList(getAllOrder);
        int orderNum = view.getOrderNumber();
        service.removeOrder(removeOrder, orderNum);
        view.displayRemoveSuccessBanner();

    }

    private void editOrder() throws FlooringException { 
       LocalDate date = view.getLocalDate();
       List<Order> getAllOrder = service.getAllOrder(date);
        view.displayOrderList(getAllOrder);
       int orderNumber = view.getOrderNumber();
       Order order = view.editOrder(service.getAllProduct(), service.getAllTax(), service.getOrder(date, orderNumber));
       order.setDate(date);
       service.editOrder(order);
     
                

        }
      public void diplayDataSetting() throws FlooringException, FileNotFoundException {
       view.checkDataMode(service.loadMode());
}
    

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
