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

    // Create Movie 1
    Movie m1 = new Movie();
    m1.setTitle("Barbie");
    m1.setGenre(Genre.COMEDY);
    m1.setAge(18);
    m1.setShowLength(LocalTime.of(2, 30, 30));
    m1.setPosterUrl("https://image.tmdb.org/t/p/w500//iuFNMS8U5cb6xfzi51Dbkovj7vM.jpg");

    // Create Movie 2
    Movie m2 = new Movie();
    m2.setTitle("Morbius");
    m2.setGenre(Genre.ACTION);
    m2.setAge(16);
    m2.setShowLength(LocalTime.of(2, 15, 0));
    m2.setPosterUrl("https://image.tmdb.org/t/p/w500//6JjfSchsU6daXk2AKX8EEBjO3Fm.jpg");

    // Create Movie 3
    Movie m3 = new Movie();
    m3.setTitle("Inception");
    m3.setGenre(Genre.SCIENCE_FICTION);
    m3.setAge(12);
    m3.setShowLength(LocalTime.of(2, 20, 0));
    m3.setPosterUrl("https://image.tmdb.org/t/p/w500//oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg");

    // Create Movie 4
    Movie m4 = new Movie();
    m4.setTitle("Joker");
    m4.setGenre(Genre.DRAMA);
    m4.setAge(18);
    m4.setShowLength(LocalTime.of(2, 5, 0));
    m4.setPosterUrl("https://image.tmdb.org/t/p/w500//udDclJoHjfjb8Ekgsd4FDteOkCU.jpg");

    // Save all movies to the repository
    movieRepository.saveAll(Arrays.asList(m1, m2, m3, m4));

    // Setup Viewing for Movie 1
    Viewing v1 = new Viewing();
    v1.setHall(1L);
    v1.setMovie(m1);
    v1.setShowTime(LocalDateTime.of(2023, 10, 19, 18, 30, 30));
    v1.setShowEndTime(LocalDateTime.of(2021, 5, 5, 14, 30, 30));
    viewingRepository.save(v1);

    Viewing v1new = new Viewing();
    v1new.setHall(5L);
    v1new.setMovie(m1);
    v1new.setShowTime(LocalDateTime.of(2023, 10, 17, 18, 30, 30));
    v1new.setShowEndTime(LocalDateTime.of(2021, 5, 5, 14, 30, 30));
    viewingRepository.save(v1new);

    Viewing morbius1 = new Viewing();
    morbius1.setHall(2L);
    morbius1.setMovie(m2);
    morbius1.setShowTime(LocalDateTime.of(2023, 10, 22, 19, 30, 30));
    morbius1.setShowEndTime(LocalDateTime.of(2021, 5, 5, 14, 30, 30));
    viewingRepository.save(morbius1);

    Viewing morbius2 = new Viewing();
    morbius2.setHall(2L);
    morbius2.setMovie(m2);
    morbius2.setShowTime(LocalDateTime.of(2023, 10, 22, 20, 30, 30));
    morbius2.setShowEndTime(LocalDateTime.of(2021, 5, 5, 14, 30, 30));
    viewingRepository.save(morbius2);

    Viewing morbius3 = new Viewing();
    morbius3.setHall(1L);
    morbius3.setMovie(m2);
    morbius3.setShowTime(LocalDateTime.of(2023, 10, 22, 21, 30, 30));
    morbius3.setShowEndTime(LocalDateTime.of(2021, 5, 5, 14, 30, 30));
    viewingRepository.save(morbius3);

    Viewing v3 = new Viewing();
    v3.setHall(3L);
    v3.setMovie(m3);
    v3.setShowTime(LocalDateTime.of(2023, 10, 17, 20, 30, 30));
    v3.setShowEndTime(LocalDateTime.of(2021, 5, 5, 14, 30, 30));
    viewingRepository.save(v3);

    Viewing v3new = new Viewing();
    v3new.setHall(3L);
    v3new.setMovie(m3);
    v3new.setShowTime(LocalDateTime.of(2023, 10, 25, 20, 30, 30));
    v3new.setShowEndTime(LocalDateTime.of(2021, 5, 5, 14, 30, 30));
    viewingRepository.save(v3new);

    Viewing v4 = new Viewing();
    v4.setHall(4L);
    v4.setMovie(m4);
    v4.setShowTime(LocalDateTime.of(2023, 10, 16, 21, 30, 30));
    v4.setShowEndTime(LocalDateTime.of(2021, 5, 5, 14, 30, 30));
    viewingRepository.save(v4);

    Viewing v4new = new Viewing();
    v4new.setHall(4L);
    v4new.setMovie(m4);
    v4new.setShowTime(LocalDateTime.of(2023, 10, 19, 21, 30, 30));
    v4new.setShowEndTime(LocalDateTime.of(2021, 5, 5, 14, 30, 30));
    viewingRepository.save(v4new);


    // Setup Ticket for Movie 1
    Ticket t1 = new Ticket();
    t1.setUser(u1);
    t1.setHall(1L);
    t1.setPrice(100);
    t1.setDateOfPurchase(LocalDateTime.of(2021, 5, 5, 12, 30, 30));
    t1.setSeats(new ArrayList<Seat>());
    t1.setViewing(v1);
    ticketRepository.save(t1);

    // Setup Seats for Ticket for Movie 1
    testSeat.setSeatNumber(1L);
    testSeat.setTicket(t1);
    testSeat.setSeatRow(seatRow1);
    seatRepository.save(testSeat);

    // Update Ticket with Viewing for Movie 1
    t1.setViewing(v1);
    ticketRepository.save(t1);



    // Update Movie 1 with Viewing
    m1.setViewing(new ArrayList<>(Arrays.asList(v1, v1new)));
    movieRepository.save(m1);

    // Update Movie 2 with Viewing
    m2.setViewing(new ArrayList<>(Arrays.asList(morbius1, morbius2, morbius3)));
    movieRepository.save(m2);

    // Update Movie 3 with Viewing
    m3.setViewing(new ArrayList<>(Arrays.asList(v3, v3new)));
    movieRepository.save(m3);

    // Update Movie 4 with Viewing
    m4.setViewing(new ArrayList<>(Arrays.asList(v4, v4new)));
    movieRepository.save(m4);
  }
}
