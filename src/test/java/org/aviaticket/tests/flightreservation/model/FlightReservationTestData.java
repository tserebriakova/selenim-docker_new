package org.aviaticket.tests.flightreservation.model;


import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@NoArgsConstructor
@AllArgsConstructor

public class FlightReservationTestData {

        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String street;
        private String city;
        private String zip;
        private String passengersCount;
        private String expectedPrice;
}