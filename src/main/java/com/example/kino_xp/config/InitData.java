package com.example.kino_xp.config;

import com.example.kino_xp.model.Seat;
import com.example.kino_xp.model.SeatRow;
import com.example.kino_xp.model.User;
import com.example.kino_xp.repository.SeatRepository;
import com.example.kino_xp.repository.SeatRowRepository;
import com.example.kino_xp.repository.UserRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class InitData implements CommandLineRunner
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    SeatRowRepository seatRowRepository;

    @Override
    public void run(String... args) throws Exception {

        //SETUP TEST USER
        User u1 = new User();
        u1.setName("Mikkel");
        u1.setEmail("MikkelsEmail@Email.com");
        u1.setAdmin(true);
        u1.setPassword("$2a$12$u6UI8steCkpOVSVEpvO5UeAuK28jEIeOkBSpXjsTFbRYKb1JXsVlW"); //password i plain text

        userRepository.save(u1);

        //SETUP TEST SEAT
        Seat testSeat = new Seat();
        testSeat.setReserved(true);
        testSeat.setSeatNumber(1);

        seatRepository.save(testSeat);

        //SETUP TEST SEAT_ROW
        SeatRow seatRow1 = new SeatRow();
        seatRow1.setSeatList(new ArrayList<Seat>(Arrays.asList(testSeat)));
        seatRow1.setSeatRowNumber(1);

        seatRowRepository.save(seatRow1);
    }
}
