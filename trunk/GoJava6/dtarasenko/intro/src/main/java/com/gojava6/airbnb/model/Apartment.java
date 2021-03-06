package com.gojava6.airbnb.model;

import com.gojava6.airbnb.services.ReservationService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Apartment {

    private static final Logger log = Logger.getLogger(Apartment.class);//TODO

    private int apartmentId;
    private String city;
    private String apartmentType;
    private int userId;

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

//    public void reserve(Date startDate, Date endDate, User user) {
//
//        log.info("Start date: " + startDate.getDate() + " " + (startDate.getMonth() + 1)
//                + " " + (startDate.getYear() + 1900) + ". End date: " + endDate.getDate()
//                + " " + (endDate.getMonth() + 1) + " " + (endDate.getYear() + 1900) + ".");
//
//        long start = startDate.getTime();
//        long end = endDate.getTime();
//
//        if (start > end) {
//            System.out.println("\nPlease corrent input dates");
//            log.info("Input date error");
//        }
//        else {
//            if (isAvailable(start, end)) {
//                reservationDateList.add(new Reservation(user, end, start));
//                System.out.println("Reservation is completed");
//                log.info("Reservation is completed");
//
//            }
//            else {
//                System.out.println("Reservation is not available");
//                log.info("Reservation is not available");
//            }
//        }
//    }

    public boolean isAvailable(long start, long end) { //TODO
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        ReservationService reservationService = (ReservationService) context.getBean("reservationService");

        List<Reservation> reservationList = reservationService.getApartmentReservationList(apartmentId);

        if (reservationList.isEmpty()) {
            return true;
        } else {
            for (Reservation rd : reservationList) {
                if (!(start < rd.getStart() && end < rd.getStart()) &&
                        !(start > rd.getEnd() && end > rd.getEnd())) {
                    return false;
                }
            }
        }
        return true;
    } //TODO

    @Override
    public String toString() {
        return "Apartment{" +
                "apartmentId=" + apartmentId +
                ", city='" + city + '\'' +
                ", apartmentType=" + apartmentType +
                ", userId=" + userId +
                '}';
    }
}
