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

    // Create Seats
    Seat testSeat = new Seat();
    Seat testSeat2 = new Seat();
    Seat testSeat3 = new Seat();

// Create Seat Rows
    SeatRow seatRow1 = new SeatRow();
    SeatRow seatRow2 = new SeatRow();
    SeatRow seatRow3 = new SeatRow();

// Setup Test Seats and Seat Rows
    seatRow1.setSeatList(new ArrayList<Seat>(Arrays.asList(testSeat)));
    seatRow1.setSeatRowNumber(1L);
    seatRowRepository.save(seatRow1);

    seatRow2.setSeatList(new ArrayList<Seat>(Arrays.asList(testSeat2)));
    seatRow2.setSeatRowNumber(2L);
    seatRowRepository.save(seatRow2);

    seatRow3.setSeatList(new ArrayList<Seat>(Arrays.asList(testSeat3)));
    seatRow3.setSeatRowNumber(3L);
    seatRowRepository.save(seatRow3);

// Setup Test User
    User u1 = new User();
    u1.setName("Mikkel");
    u1.setEmail("MikkelsEmail@Email.com");
    u1.setAdmin(true);
    u1.setPassword("$2a$12$u6UI8steCkpOVSVEpvO5UeAuK28jEIeOkBSpXjsTFbRYKb1JXsVlW"); //password in plain text
    userRepository.save(u1);

// Setup Viewing
    Viewing v1 = new Viewing();
    v1.setId(1L);
    viewingRepository.save(v1);

// Setup Ticket
    Ticket t1 = new Ticket();
    t1.setUser(u1);
    t1.setHall(1L);
    t1.setPrice(100);
    t1.setDateOfPurchase(LocalDateTime.of(2021, 5, 5, 12, 30, 30));
    t1.setSeats(new ArrayList<Seat>());
    t1.setViewing(v1);
    ticketRepository.save(t1);

// Setup Seats for Ticket
    testSeat.setSeatNumber(1L);
    testSeat.setTicket(t1);
    testSeat.setSeatRow(seatRow1);
    seatRepository.save(testSeat);

// Create Movie
    Movie m1 = new Movie();
    m1.setTitle("Barbie");
    m1.setGenre(Genre.COMEDY);
    m1.setAge(18);
    m1.setShowLength(LocalTime.of(2, 30, 30));

// Setup Viewing Details
    v1.setHall(1L);
    v1.setTickets(Arrays.asList(t1));
    v1.setMovie(m1);
    v1.setShowTime(LocalDateTime.of(2021, 5, 5, 12, 30, 30));
    v1.setShowEndTime(LocalDateTime.of(2021, 5, 5, 14, 30, 30));

// Update Ticket with Viewing
    t1.setViewing(v1);
    ticketRepository.save(t1);

// Update Movie with Viewing
    m1.setViewing(new ArrayList<>(Arrays.asList(v1)));
    movieRepository.save(m1);

// Update Viewing
    viewingRepository.save(v1);
  }
}
