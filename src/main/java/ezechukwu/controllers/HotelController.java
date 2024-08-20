package ezechukwu.controllers;

import ezechukwu.model.Customer;
import ezechukwu.model.IRoom;
import ezechukwu.model.Reservation;
import ezechukwu.model.Room;
import ezechukwu.service.CustomerService;
import ezechukwu.service.ReservationService;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public class HotelController {
    private static HotelController INSTANCE;

    private HotelController(){}

    public static HotelController getInstance(){
        if(INSTANCE ==  null){
            INSTANCE = new HotelController();
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

    public void createACustomer(String email, String firstname, String lastName){
        CustomerService.getInstance().addCustomer(email, firstname,lastName);
    }

    public IRoom getRoom(String roomNumber){
        Optional<IRoom> room =  ReservationService.getInstance().getARoom(roomNumber);
        if(!room.isEmpty()){
            return room.get();
        }
        return null;
    }
    public Reservation bookARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        return ReservationService.getInstance().reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
        Customer customer = this.getCustomer(customerEmail);
        return ReservationService.getInstance().getCustomerReservation(customer);
    }
    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return ReservationService.getInstance().findFreeRooms(checkIn, checkOut);
    }
}
