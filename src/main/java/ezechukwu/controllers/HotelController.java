package ezechukwu.controllers;

import ezechukwu.model.Customer;
import ezechukwu.model.IRoom;
import ezechukwu.model.Reservation;
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
        CustomerService.getInstance().addCustomers(email, firstname,lastName);
    }

    public IRoom getRoom(String roomNumber){
        Optional<IRoom> room =  ReservationService.getInstance().getARoom(roomNumber);
        if(!room.isEmpty()){
            room.get();
        }
        return null;
    }
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        return ReservationService.getInstance().reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
        return ReservationService.getInstance().getCustomerReservation(getCustomer(customerEmail));
    }
//    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
//        return  ReservationService.getInstance().findRooms(checkIn, checkOut);
//    }
}
