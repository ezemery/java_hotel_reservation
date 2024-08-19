package ezechukwu.controllers;

import ezechukwu.model.Customer;
import ezechukwu.model.IRoom;
import ezechukwu.service.CustomerService;
import ezechukwu.service.ReservationService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class AdminController {
    private static AdminController INSTANCE;
    private AdminController(){}

    public static AdminController getInstance(){
        if(INSTANCE == null){
            INSTANCE = new AdminController();
        }

        return INSTANCE;
    }
    public Customer getCustomer(String email){
        Optional<Customer> customer =  CustomerService.getInstance().getCustomer(email);
        if(!customer.isEmpty()){
            return customer.get();
        }else{
            return null;
        }
    }
    public void addRoom(List<IRoom> rooms){
        ReservationService.getInstance().addRooms(rooms);
    }
//    public Collection<IRoom> getAllRooms(){
//        return ReservationService.getInstance().findRooms();
//    }
    public Collection<Customer> getAllCustomers(){
        return CustomerService.getInstance().getAllCustomers();
    }
    public void displayAllReservations(){
        ReservationService.getInstance().printAllReservations();
    }
}
