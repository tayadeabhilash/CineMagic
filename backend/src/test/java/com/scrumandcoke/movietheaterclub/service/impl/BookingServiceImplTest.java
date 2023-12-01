package com.scrumandcoke.movietheaterclub.service.impl;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.google.common.eventbus.EventBus;
import com.scrumandcoke.movietheaterclub.dto.BookingDto;
import com.scrumandcoke.movietheaterclub.entity.BookingEntity;
import com.scrumandcoke.movietheaterclub.entity.LocationEntity;
import com.scrumandcoke.movietheaterclub.entity.MovieEntity;
import com.scrumandcoke.movietheaterclub.entity.ShowTimeEntity;
import com.scrumandcoke.movietheaterclub.entity.TheaterScreenEntity;
import com.scrumandcoke.movietheaterclub.enums.BookingStatus;
import com.scrumandcoke.movietheaterclub.enums.Location;
import com.scrumandcoke.movietheaterclub.enums.PaymentMethod;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.mapper.BookingMapper;
import com.scrumandcoke.movietheaterclub.repository.BookingRepository;
import com.scrumandcoke.movietheaterclub.service.MovieService;
import com.scrumandcoke.movietheaterclub.service.ShowTimeService;
import com.scrumandcoke.movietheaterclub.service.UserService;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookingServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BookingServiceImplTest {
  @MockBean
  private BookingMapper bookingMapper;

  @MockBean
  private BookingRepository bookingRepository;

  @Autowired
  private BookingServiceImpl bookingServiceImpl;

  @MockBean
  private EventBus eventBus;

  @MockBean
  private MovieService movieService;

  @MockBean
  private ShowTimeService showTimeService;

  @MockBean
  private UserService userService;

  @Test
  void testGetAllBookings() {
    when(bookingMapper.toDto(Mockito.<List<BookingEntity>>any())).thenReturn(null);
    when(bookingRepository.findAll()).thenReturn(null);
    List<BookingDto> actualAllBookings = bookingServiceImpl.getAllBookings();
    verify(bookingMapper).toDto(Mockito.<List<BookingEntity>>any());
    verify(bookingRepository).findAll();
    assertNull(actualAllBookings);
  }

  @Test
  void testCreateBookingInvalidRequest() throws GlobalException {
    BookingDto booking = new BookingDto();
    booking.setSeatsBooked(1);
    assertThrows(GlobalException.class, () -> bookingServiceImpl.createBooking(booking));
  }

  @Test
  void testCreateBookingNullRequest() throws GlobalException {
    assertThrows(GlobalException.class, () -> bookingServiceImpl.createBooking(null));
  }

  @Test
  void testCancelBooking() throws GlobalException {
    MovieEntity movie = new MovieEntity();
    movie.setGenre("Genre");
    movie.setLanguage("en");
    movie.setMovieId(1);
    movie.setMovieName("Movie Name");
    movie.setPosterUrl("https://example.org/example");
    movie.setReleaseDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    movie.setRunningTime(1);
    movie.setSynopsis("Synopsis");

    LocationEntity locationEntity = new LocationEntity();
    locationEntity.setId(1);
    locationEntity.setLocation(Location.CAMPBELL);
    locationEntity.setName("Name");
    locationEntity.setTheaterScreenCount(3);

    TheaterScreenEntity theaterScreen = new TheaterScreenEntity();
    theaterScreen.setAddress("42 Main St");
    theaterScreen.setEmail("jane.doe@example.org");
    theaterScreen.setId(1);
    theaterScreen.setLocationEntity(locationEntity);
    theaterScreen.setName("Name");
    theaterScreen.setPhone("6625550144");
    theaterScreen.setSeatingCapacity(1);

    ShowTimeEntity showtime = new ShowTimeEntity();
    showtime.setAvailableSeats(1);
    showtime.setDiscountedPrice(10.0d);
    showtime.setId(1);
    showtime.setMovie(movie);
    showtime.setPrice(10.0d);
    showtime.setTheaterScreen(theaterScreen);
    showtime.setTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    BookingEntity bookingEntity = new BookingEntity();
    bookingEntity.setBookingDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    bookingEntity.setBookingId(1);
    bookingEntity.setBookingStatus(BookingStatus.CONFIRMED);
    bookingEntity.setCashAmount(10.0d);
    bookingEntity.setOnlineServiceFee(10.0d);
    bookingEntity.setPaymentMethod(PaymentMethod.CREDIT_CARD);
    bookingEntity.setPointsAmount(10.0d);
    bookingEntity.setSeatsBooked(1);
    bookingEntity.setShowtime(showtime);
    bookingEntity.setTotalAmount(10.0d);
    bookingEntity.setUserId("42");
    Optional<BookingEntity> ofResult = Optional.of(bookingEntity);
    when(bookingRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
    assertThrows(GlobalException.class, () -> bookingServiceImpl.cancelBooking(1));
    verify(bookingRepository).findById(Mockito.<Integer>any());
  }

  @Test
  void testCancelBooking2() throws GlobalException {
    MovieEntity movie = new MovieEntity();
    movie.setGenre("Genre");
    movie.setLanguage("en");
    movie.setMovieId(1);
    movie.setMovieName("Movie Name");
    movie.setPosterUrl("https://example.org/example");
    movie.setReleaseDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    movie.setRunningTime(1);
    movie.setSynopsis("Synopsis");

    LocationEntity locationEntity = new LocationEntity();
    locationEntity.setId(1);
    locationEntity.setLocation(Location.CAMPBELL);
    locationEntity.setName("Name");
    locationEntity.setTheaterScreenCount(3);

    TheaterScreenEntity theaterScreen = new TheaterScreenEntity();
    theaterScreen.setAddress("42 Main St");
    theaterScreen.setEmail("jane.doe@example.org");
    theaterScreen.setId(1);
    theaterScreen.setLocationEntity(locationEntity);
    theaterScreen.setName("Name");
    theaterScreen.setPhone("6625550144");
    theaterScreen.setSeatingCapacity(1);

    ShowTimeEntity showtime = new ShowTimeEntity();
    showtime.setAvailableSeats(1);
    showtime.setDiscountedPrice(10.0d);
    showtime.setId(1);
    showtime.setMovie(movie);
    showtime.setPrice(10.0d);
    showtime.setTheaterScreen(theaterScreen);
    showtime.setTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    BookingEntity bookingEntity = mock(BookingEntity.class);
    when(bookingEntity.getBookingStatus()).thenReturn(BookingStatus.CANCELLED);
    doNothing().when(bookingEntity).setBookingDate(Mockito.<Date>any());
    doNothing().when(bookingEntity).setBookingId(Mockito.<Integer>any());
    doNothing().when(bookingEntity).setBookingStatus(Mockito.<BookingStatus>any());
    doNothing().when(bookingEntity).setCashAmount(Mockito.<Double>any());
    doNothing().when(bookingEntity).setOnlineServiceFee(Mockito.<Double>any());
    doNothing().when(bookingEntity).setPaymentMethod(Mockito.<PaymentMethod>any());
    doNothing().when(bookingEntity).setPointsAmount(Mockito.<Double>any());
    doNothing().when(bookingEntity).setSeatsBooked(Mockito.<Integer>any());
    doNothing().when(bookingEntity).setShowtime(Mockito.<ShowTimeEntity>any());
    doNothing().when(bookingEntity).setTotalAmount(Mockito.<Double>any());
    doNothing().when(bookingEntity).setUserId(Mockito.<String>any());
    bookingEntity.setBookingDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    bookingEntity.setBookingId(1);
    bookingEntity.setBookingStatus(BookingStatus.CONFIRMED);
    bookingEntity.setCashAmount(10.0d);
    bookingEntity.setOnlineServiceFee(10.0d);
    bookingEntity.setPaymentMethod(PaymentMethod.CREDIT_CARD);
    bookingEntity.setPointsAmount(10.0d);
    bookingEntity.setSeatsBooked(1);
    bookingEntity.setShowtime(showtime);
    bookingEntity.setTotalAmount(10.0d);
    bookingEntity.setUserId("42");
    Optional<BookingEntity> ofResult = Optional.of(bookingEntity);
    when(bookingRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

    MovieEntity movie2 = new MovieEntity();
    movie2.setGenre("Genre");
    movie2.setLanguage("en");
    movie2.setMovieId(1);
    movie2.setMovieName("Movie Name");
    movie2.setPosterUrl("https://example.org/example");
    movie2.setReleaseDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    movie2.setRunningTime(1);
    movie2.setSynopsis("Synopsis");

    LocationEntity locationEntity2 = new LocationEntity();
    locationEntity2.setId(1);
    locationEntity2.setLocation(Location.CAMPBELL);
    locationEntity2.setName("Name");
    locationEntity2.setTheaterScreenCount(3);

    TheaterScreenEntity theaterScreen2 = new TheaterScreenEntity();
    theaterScreen2.setAddress("42 Main St");
    theaterScreen2.setEmail("jane.doe@example.org");
    theaterScreen2.setId(1);
    theaterScreen2.setLocationEntity(locationEntity2);
    theaterScreen2.setName("Name");
    theaterScreen2.setPhone("6625550144");
    theaterScreen2.setSeatingCapacity(1);
    ShowTimeEntity showTimeEntity = mock(ShowTimeEntity.class);
    doNothing().when(showTimeEntity).setAvailableSeats(Mockito.<Integer>any());
    doNothing().when(showTimeEntity).setDiscountedPrice(Mockito.<Double>any());
    doNothing().when(showTimeEntity).setId(Mockito.<Integer>any());
    doNothing().when(showTimeEntity).setMovie(Mockito.<MovieEntity>any());
    doNothing().when(showTimeEntity).setPrice(Mockito.<Double>any());
    doNothing().when(showTimeEntity).setTheaterScreen(Mockito.<TheaterScreenEntity>any());
    doNothing().when(showTimeEntity).setTime(Mockito.<Date>any());
    showTimeEntity.setAvailableSeats(1);
    showTimeEntity.setDiscountedPrice(10.0d);
    showTimeEntity.setId(1);
    showTimeEntity.setMovie(movie2);
    showTimeEntity.setPrice(10.0d);
    showTimeEntity.setTheaterScreen(theaterScreen2);
    showTimeEntity.setTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    assertThrows(GlobalException.class, () -> bookingServiceImpl.cancelBooking(1));
    verify(bookingEntity).getBookingStatus();
    verify(bookingEntity).setBookingDate(Mockito.<Date>any());
    verify(bookingEntity).setBookingId(Mockito.<Integer>any());
    verify(bookingEntity).setBookingStatus(Mockito.<BookingStatus>any());
    verify(bookingEntity).setCashAmount(Mockito.<Double>any());
    verify(bookingEntity).setOnlineServiceFee(Mockito.<Double>any());
    verify(bookingEntity).setPaymentMethod(Mockito.<PaymentMethod>any());
    verify(bookingEntity).setPointsAmount(Mockito.<Double>any());
    verify(bookingEntity).setSeatsBooked(Mockito.<Integer>any());
    verify(bookingEntity).setShowtime(Mockito.<ShowTimeEntity>any());
    verify(bookingEntity).setTotalAmount(Mockito.<Double>any());
    verify(bookingEntity).setUserId(Mockito.<String>any());
    verify(showTimeEntity).setAvailableSeats(Mockito.<Integer>any());
    verify(showTimeEntity).setDiscountedPrice(Mockito.<Double>any());
    verify(showTimeEntity).setId(Mockito.<Integer>any());
    verify(showTimeEntity).setMovie(Mockito.<MovieEntity>any());
    verify(showTimeEntity).setPrice(Mockito.<Double>any());
    verify(showTimeEntity).setTheaterScreen(Mockito.<TheaterScreenEntity>any());
    verify(showTimeEntity).setTime(Mockito.<Date>any());
    verify(bookingRepository).findById(Mockito.<Integer>any());
  }

  @Test
  void testGetAllBookingsByUserId() {
    when(bookingMapper.toDto(Mockito.<List<BookingEntity>>any())).thenReturn(null);
    when(bookingRepository.findByUserId(Mockito.<String>any())).thenReturn(null);
    List<BookingDto> actualAllBookingsByUserId = bookingServiceImpl.getAllBookingsByUserId("42");
    verify(bookingMapper).toDto(Mockito.<List<BookingEntity>>any());
    verify(bookingRepository).findByUserId(Mockito.<String>any());
    assertNull(actualAllBookingsByUserId);
  }
}
