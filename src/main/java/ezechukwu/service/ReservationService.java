package ezechukwu.service;

import ezechukwu.model.Customer;
import ezechukwu.model.IRoom;
import ezechukwu.model.Reservation;
import ezechukwu.model.Room;

import java.util.*;

public class ReservationService {
    private final Set<IRoom> rooms = new HashSet<>();
    private final Set<Reservation> reservations =  new HashSet<>();
    private static ReservationService INSTANCE;

    private ReservationService() {
    }
    public static ReservationService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ReservationService();
        }
        return INSTANCE;
    }

    public void addRooms(List<IRoom> rooms){
        for(IRoom room : rooms){
            this.addRoom(room);
        }
    }
    public void addRoom(IRoom room){
        rooms.add(room);
    }
    public Optional<IRoom> getARoom(String roomId){
        for(IRoom room : rooms){
            if(room.getRoomNumber() == roomId){
                return Optional.of(room);
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

//    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
//
//    }
    public Collection<Reservation> getCustomerReservation(Customer customer){
        return reservations.stream().filter(reservation -> reservation.getCustomer() == customer).toList();
    }
    public void printAllReservations(){
        System.out.println(reservations.stream().toList());
    }
}
