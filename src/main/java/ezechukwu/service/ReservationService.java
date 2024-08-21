package ezechukwu.service;

import ezechukwu.model.Customer;
import ezechukwu.model.IRoom;
import ezechukwu.model.Reservation;
import ezechukwu.model.Room;

import java.time.LocalDate;
import java.time.ZoneId;
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

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) throws Exception {
        Set<IRoom> reservedRooms = reservations.stream().map(reservation -> reservation.getRoom()).collect(Collectors.toSet());
        Reservation reserve = new Reservation(customer, room, checkInDate, checkOutDate);
        if(!reservedRooms.contains(room)){
            reservations.add(reserve);
            return reserve;
        }else{
            boolean okToAddReservation = true;
            for(Reservation reservation: reservations){
                if(reservation.getRoom().equals(room)){
                    if(this.areDatesInRangeOrOverlap(convertToLocalDate(checkInDate), convertToLocalDate(checkOutDate), convertToLocalDate(reservation.getCheckInDate()), convertToLocalDate(reservation.getCheckOutDate()))){
                        okToAddReservation = false;
                    }
                }
            }
            if(okToAddReservation){
                reservations.add(reserve);
                return reserve;
            }else{
                throw new Exception("Room is already reserved");
            }
        }

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
            }
            else{
                // check if the reserved room has the same checkin and checkout date
                boolean okToAddRoom = true;
                for(Reservation reservation: reservations){
                    if(reservation.getRoom().equals(room)){
                        if(this.areDatesInRangeOrOverlap(convertToLocalDate(checkInDate), convertToLocalDate(checkOutDate), convertToLocalDate(reservation.getCheckInDate()), convertToLocalDate(reservation.getCheckOutDate()))){
                            okToAddRoom = false;
                        }
                    }
                }
                if(okToAddRoom){
                    availableRooms.add(room);
                }

            }
        }
        return availableRooms;
    }
    public Collection<IRoom> findRecommendedRooms(Date checkInDate, Date checkOutDate){
        return this.findFreeRooms(this.addSevenDays(checkInDate), this.addSevenDays(checkOutDate));
    }
    public static boolean areDatesInRangeOrOverlap(LocalDate date1, LocalDate date2, LocalDate startDate, LocalDate endDate){
        // Normalize the dates so date1 is before date2
        if (date1.isAfter(date2)) {
            LocalDate temp = date1;
            date1 = date2;
            date2 = temp;
        }

        // Check if date1 or date2 are within the startDate and endDate range
        if ((date1.isEqual(startDate) || date1.isAfter(startDate)) && date1.isBefore(endDate.plusDays(1))) {
            return true;
        }
        if ((date2.isEqual(startDate) || date2.isAfter(startDate)) && date2.isBefore(endDate.plusDays(1))) {
            return true;
        }

        // Check if the ranges overlap
        if (date1.isBefore(startDate) && date2.isAfter(endDate)) {
            return true;
        }

        return false;
    }
    public static LocalDate convertToLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    public static Date addSevenDays(Date originalDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(originalDate);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        return calendar.getTime();
    }

    public Collection<Reservation> getCustomerReservation(Customer customer){
        return reservations.stream().filter(reservation -> reservation.getCustomer() == customer).toList();
    }
    public Collection<Reservation> printAllReservations(){
        return reservations.stream().toList();
    }
}
