package com.example.kino_xp.config;

import com.example.kino_xp.model.Seat;
import com.example.kino_xp.model.SeatRow;
import com.example.kino_xp.model.User;
import com.example.kino_xp.repository.*;
import com.example.kino_xp.model.Genre;
import com.example.kino_xp.model.Movie;
import com.example.kino_xp.model.Viewing;
import com.example.kino_xp.model.Ticket;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class InitData implements CommandLineRunner {
  @Autowired
  UserRepository userRepository;

  @Autowired
  MovieRepository movieRepository;

  @Autowired
  ViewingRepository viewingRepository;
  
  @Autowired
  SeatRepository seatRepository;

  @Autowired
  SeatRowRepository seatRowRepository;

  @Autowired
  TicketRepository ticketRepository;

  @Override
  public void run(String... args) throws Exception {
    //SETUP TEST USER
    User u1 = new User();
    u1.setName("Mikkel");
    u1.setEmail("MikkelsEmail@Email.com");
    u1.setAdmin(true);
    u1.setPassword("$2a$12$u6UI8steCkpOVSVEpvO5UeAuK28jEIeOkBSpXjsTFbRYKb1JXsVlW"); //password i plain text

    userRepository.save(u1);

    //Test Ticket
    Seat testSeat = new Seat();
    SeatRow seatRow1 = new SeatRow();
    //SETUP TEST SEAT_ROW
    seatRow1.setSeatList(new ArrayList<Seat>(Arrays.asList(testSeat)));
    seatRow1.setSeatRowNumber(1);
    seatRowRepository.save(seatRow1);

    Ticket t1 = new Ticket();
    t1.setUser(u1);
    t1.setHall(1);
    t1.setPrice(100);
    t1.setDateOfPurchase(LocalDateTime.of(2021, 5, 5, 12, 30, 30));
    t1.setSeats(new ArrayList<Seat>());
    ticketRepository.save(t1);


    //SETUP TEST SEAT
    testSeat.setSeatNumber(1);
    testSeat.setTicket(t1);
    testSeat.setSeatRow(seatRow1);
    seatRepository.save(testSeat);


    //Test movie
    Movie m1 = new Movie();
    m1.setTitle("Barbie");
    m1.setGenre(Genre.COMEDY);
    m1.setAge(18);
    m1.setShowLength(LocalTime.of(2, 30, 30));

    movieRepository.save(m1);
    //Test Viewing
    Viewing v1 = new Viewing();
    v1.setHall(1);
    v1.setId(1);
    v1.setTicket(t1);
    v1.setMovie(m1);
    v1.setShowTime(LocalDateTime.of(2021, 5, 5, 12, 30, 30));
    v1.setShowEndTime(LocalDateTime.of(2021, 5, 5, 14, 30, 30));
    viewingRepository.save(v1);




  }
}
