package com.example.kino_xp.config;

import com.example.kino_xp.model.*;
import com.example.kino_xp.repository.*;
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
  HallRepository hallRepository;

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

    userRepository.save(u1);

    //SETUP TEST HALL
    Hall hall1 = new Hall();
    hall1.setId(1);
    hall1.setSeatRows(new ArrayList<SeatRow>(Arrays.asList(seatRow1)));

    hallRepository.save(hall1);

    Movie m1 = new Movie();
    m1.setTitle("Barbie");
    m1.setGenre(Genre.COMEDY);
    m1.setAge(18);
    m1.setShowLength(LocalTime.of(2, 30, 30));

    movieRepository.save(m1);

    Viewing v1 = new Viewing();
    v1.setHall(1);
    v1.setId(1);
    v1.setTicket(null);
    v1.setShowTime(LocalDateTime.of(2021, 5, 5, 12, 30, 30));
    v1.setShowEndTime(LocalDateTime.of(2021, 5, 5, 14, 30, 30));
    viewingRepository.save(v1);
  }
}
