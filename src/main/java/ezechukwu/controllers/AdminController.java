package ezechukwu.controllers;

import ezechukwu.model.Customer;
import ezechukwu.model.IRoom;
import ezechukwu.model.Reservation;
import ezechukwu.model.Room;
import ezechukwu.service.CustomerService;
import ezechukwu.service.ReservationService;

import java.util.Collection;
import java.util.Date;
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
    public void addARoom(IRoom room){
        ReservationService.getInstance().addRoom(room);
    }
    public void addRooms(List<IRoom> rooms){
        ReservationService.getInstance().addRooms(rooms);
    }
    public Collection<IRoom> getAllRooms(){
        return ReservationService.getInstance().getAllRooms();
    }
    public Collection<Customer> getAllCustomers(){
        return CustomerService.getInstance().getAllCustomers();
    }
    public Collection<Reservation> displayAllReservations(){
        return ReservationService.getInstance().printAllReservations();
    }
}
