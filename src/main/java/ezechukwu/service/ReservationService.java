package ezechukwu.service;

import ezechukwu.model.Customer;
import ezechukwu.model.IRoom;
import ezechukwu.model.Reservation;
import ezechukwu.model.Room;

import java.util.*;
import java.util.stream.Collectors;

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
            if(room.getRoomNumber().equals(roomId)){
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
    public Collection<IRoom> getAllRooms(){
        return rooms.stream().toList();
    }
    public Collection<IRoom> findFreeRooms(Date checkInDate, Date checkOutDate){
        List<IRoom> availableRooms = new ArrayList<>();
        Set<IRoom> reservedRooms = reservations.stream().map(reservation -> reservation.getRoom()).collect(Collectors.toSet());
        for(IRoom room : rooms){
            if(!reservedRooms.contains(room)){
                availableRooms.add(room);
            }else{
                // check if the reserved room has the same checkin and checkout date
                for(Reservation reservation: reservations){
                    if(reservation.getRoom().equals(room)){
                        if(reservation.getCheckInDate().before(checkInDate) && reservation.getCheckOutDate().before(checkOutDate)){
                            availableRooms.add(room);
                        }
                    }
                }

            }
        }
        return availableRooms;
    }
    public Collection<Reservation> getCustomerReservation(Customer customer){
        return reservations.stream().filter(reservation -> reservation.getCustomer() == customer).toList();
    }
    public Collection<Reservation> printAllReservations(){
        return reservations.stream().toList();
    }
}
