package com.scrumandcoke.movietheaterclub.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.scrumandcoke.movietheaterclub.dto.TheaterScreenDto;
import com.scrumandcoke.movietheaterclub.entity.LocationEntity;
import com.scrumandcoke.movietheaterclub.entity.TheaterScreenEntity;
import com.scrumandcoke.movietheaterclub.enums.Location;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.repository.MultiplexRepository;
import com.scrumandcoke.movietheaterclub.repository.TheaterScreenRepository;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TheaterScreenImpl.class})
@ExtendWith(SpringExtension.class)
class TheaterScreenImplTest {
    @MockBean
    private MultiplexRepository multiplexRepository;

    @Autowired
    private TheaterScreenImpl theaterScreenImpl;

    @MockBean
    private TheaterScreenRepository theaterScreenRepository;

    @Test
    void testCreateTheaterScreen() throws GlobalException {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(1);
        locationEntity.setLocation(Location.CAMPBELL);
        locationEntity.setName("Name");
        locationEntity.setTheaterScreenCount(3);
        Optional<LocationEntity> ofResult = Optional.of(locationEntity);
        when(multiplexRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        LocationEntity locationEntity2 = new LocationEntity();
        locationEntity2.setId(1);
        locationEntity2.setLocation(Location.CAMPBELL);
        locationEntity2.setName("Name");
        locationEntity2.setTheaterScreenCount(3);

        TheaterScreenEntity theaterScreenEntity = new TheaterScreenEntity();
        theaterScreenEntity.setAddress("42 Main St");
        theaterScreenEntity.setEmail("jane.doe@example.org");
        theaterScreenEntity.setId(1);
        theaterScreenEntity.setLocationEntity(locationEntity2);
        theaterScreenEntity.setName("Name");
        theaterScreenEntity.setPhone("6625550144");
        theaterScreenEntity.setSeatingCapacity(1);
        when(theaterScreenRepository.save(Mockito.<TheaterScreenEntity>any())).thenReturn(theaterScreenEntity);
        theaterScreenImpl.createTheaterScreen(new TheaterScreenDto());
        verify(multiplexRepository).findById(Mockito.<Integer>any());
        verify(theaterScreenRepository).save(Mockito.<TheaterScreenEntity>any());
    }

    @Test
    void testCreateTheaterScreenEmpty() throws GlobalException {
        Optional<LocationEntity> emptyResult = Optional.empty();
        when(multiplexRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        assertThrows(GlobalException.class, () -> theaterScreenImpl.createTheaterScreen(new TheaterScreenDto()));
        verify(multiplexRepository).findById(Mockito.<Integer>any());
    }

    @Test
    void testGetTheaterScreenById() throws GlobalException {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(1);
        locationEntity.setLocation(Location.CAMPBELL);
        locationEntity.setName("Name");
        locationEntity.setTheaterScreenCount(3);

        TheaterScreenEntity theaterScreenEntity = new TheaterScreenEntity();
        theaterScreenEntity.setAddress("42 Main St");
        theaterScreenEntity.setEmail("jane.doe@example.org");
        theaterScreenEntity.setId(1);
        theaterScreenEntity.setLocationEntity(locationEntity);
        theaterScreenEntity.setName("Name");
        theaterScreenEntity.setPhone("6625550144");
        theaterScreenEntity.setSeatingCapacity(1);
        Optional<TheaterScreenEntity> ofResult = Optional.of(theaterScreenEntity);
        when(theaterScreenRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        TheaterScreenDto actualTheaterScreenById = theaterScreenImpl.getTheaterScreenById(1);
        verify(theaterScreenRepository).findById(Mockito.<Integer>any());
        assertEquals("42 Main St", actualTheaterScreenById.getAddress());
        assertEquals("6625550144", actualTheaterScreenById.getPhone());
        assertEquals("Name", actualTheaterScreenById.getName());
        assertEquals("jane.doe@example.org", actualTheaterScreenById.getEmail());
        assertEquals(1, actualTheaterScreenById.getId());
        assertEquals(1, actualTheaterScreenById.getSeatingCapacity());
        assertEquals(1, actualTheaterScreenById.getMultiplexId().intValue());
    }

    @Test
    void testGetTheaterScreenByIdEmpty() throws GlobalException {
        Optional<TheaterScreenEntity> emptyResult = Optional.empty();
        when(theaterScreenRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        assertThrows(GlobalException.class, () -> theaterScreenImpl.getTheaterScreenById(1));
        verify(theaterScreenRepository).findById(Mockito.<Integer>any());
    }

    @Test
    void testUpdateTheaterScreenInvalidSeatingCapacity() throws GlobalException {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(1);
        locationEntity.setLocation(Location.CAMPBELL);
        locationEntity.setName("Name");
        locationEntity.setTheaterScreenCount(3);

        TheaterScreenEntity theaterScreenEntity = new TheaterScreenEntity();
        theaterScreenEntity.setAddress("42 Main St");
        theaterScreenEntity.setEmail("jane.doe@example.org");
        theaterScreenEntity.setId(1);
        theaterScreenEntity.setLocationEntity(locationEntity);
        theaterScreenEntity.setName("Name");
        theaterScreenEntity.setPhone("6625550144");
        theaterScreenEntity.setSeatingCapacity(1);
        Optional<TheaterScreenEntity> ofResult = Optional.of(theaterScreenEntity);
        when(theaterScreenRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        assertThrows(GlobalException.class, () -> theaterScreenImpl.updateTheaterScreen(1, new TheaterScreenDto()));
        verify(theaterScreenRepository).findById(Mockito.<Integer>any());
    }

    @Test
    void testUpdateTheaterScreenEmptyResult() throws GlobalException {
        Optional<TheaterScreenEntity> emptyResult = Optional.empty();
        when(theaterScreenRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        assertThrows(GlobalException.class, () -> theaterScreenImpl.updateTheaterScreen(1, new TheaterScreenDto()));
        verify(theaterScreenRepository).findById(Mockito.<Integer>any());
    }
    @Test
    void testUpdateTheaterScreen() throws GlobalException {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(1);
        locationEntity.setLocation(Location.CAMPBELL);
        locationEntity.setName("Name");
        locationEntity.setTheaterScreenCount(3);
        Optional<LocationEntity> ofResult = Optional.of(locationEntity);
        when(multiplexRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        LocationEntity locationEntity2 = new LocationEntity();
        locationEntity2.setId(1);
        locationEntity2.setLocation(Location.CAMPBELL);
        locationEntity2.setName("Name");
        locationEntity2.setTheaterScreenCount(3);

        TheaterScreenEntity theaterScreenEntity = new TheaterScreenEntity();
        theaterScreenEntity.setAddress("42 Main St");
        theaterScreenEntity.setEmail("jane.doe@example.org");
        theaterScreenEntity.setId(1);
        theaterScreenEntity.setLocationEntity(locationEntity2);
        theaterScreenEntity.setName("Name");
        theaterScreenEntity.setPhone("6625550144");
        theaterScreenEntity.setSeatingCapacity(1);
        Optional<TheaterScreenEntity> ofResult2 = Optional.of(theaterScreenEntity);

        LocationEntity locationEntity3 = new LocationEntity();
        locationEntity3.setId(1);
        locationEntity3.setLocation(Location.CAMPBELL);
        locationEntity3.setName("Name");
        locationEntity3.setTheaterScreenCount(3);

        TheaterScreenEntity theaterScreenEntity2 = new TheaterScreenEntity();
        theaterScreenEntity2.setAddress("42 Main St");
        theaterScreenEntity2.setEmail("jane.doe@example.org");
        theaterScreenEntity2.setId(1);
        theaterScreenEntity2.setLocationEntity(locationEntity3);
        theaterScreenEntity2.setName("Name");
        theaterScreenEntity2.setPhone("6625550144");
        theaterScreenEntity2.setSeatingCapacity(1);
        when(theaterScreenRepository.save(Mockito.<TheaterScreenEntity>any())).thenReturn(theaterScreenEntity2);
        when(theaterScreenRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);
        TheaterScreenDto actualUpdateTheaterScreenResult = theaterScreenImpl.updateTheaterScreen(1,
                new TheaterScreenDto(1, "AMC", 1, 1,
                        "42 Main St", "6625550144", "jane.doe@example.org"));
        verify(multiplexRepository).findById(Mockito.<Integer>any());
        verify(theaterScreenRepository, atLeast(1)).findById(Mockito.<Integer>any());
        verify(theaterScreenRepository, atLeast(1)).save(Mockito.<TheaterScreenEntity>any());
        assertEquals("42 Main St", actualUpdateTheaterScreenResult.getAddress());
        assertEquals("6625550144", actualUpdateTheaterScreenResult.getPhone());
        assertEquals("Name", actualUpdateTheaterScreenResult.getName());
        assertEquals("jane.doe@example.org", actualUpdateTheaterScreenResult.getEmail());
        assertEquals(1, actualUpdateTheaterScreenResult.getId());
        assertEquals(1, actualUpdateTheaterScreenResult.getSeatingCapacity());
        assertEquals(1, actualUpdateTheaterScreenResult.getMultiplexId().intValue());
    }

    @Test
    void testUpdateTheaterScreenLocationNotFound() throws GlobalException {
        Optional<LocationEntity> emptyResult = Optional.empty();
        when(multiplexRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(1);
        locationEntity.setLocation(Location.CAMPBELL);
        locationEntity.setName("Name");
        locationEntity.setTheaterScreenCount(3);

        TheaterScreenEntity theaterScreenEntity = new TheaterScreenEntity();
        theaterScreenEntity.setAddress("42 Main St");
        theaterScreenEntity.setEmail("jane.doe@example.org");
        theaterScreenEntity.setId(1);
        theaterScreenEntity.setLocationEntity(locationEntity);
        theaterScreenEntity.setName("Name");
        theaterScreenEntity.setPhone("6625550144");
        theaterScreenEntity.setSeatingCapacity(1);
        Optional<TheaterScreenEntity> ofResult = Optional.of(theaterScreenEntity);

        LocationEntity locationEntity2 = new LocationEntity();
        locationEntity2.setId(1);
        locationEntity2.setLocation(Location.CAMPBELL);
        locationEntity2.setName("Name");
        locationEntity2.setTheaterScreenCount(3);

        TheaterScreenEntity theaterScreenEntity2 = new TheaterScreenEntity();
        theaterScreenEntity2.setAddress("42 Main St");
        theaterScreenEntity2.setEmail("jane.doe@example.org");
        theaterScreenEntity2.setId(1);
        theaterScreenEntity2.setLocationEntity(locationEntity2);
        theaterScreenEntity2.setName("Name");
        theaterScreenEntity2.setPhone("6625550144");
        theaterScreenEntity2.setSeatingCapacity(1);
        when(theaterScreenRepository.save(Mockito.<TheaterScreenEntity>any())).thenReturn(theaterScreenEntity2);
        when(theaterScreenRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        assertThrows(GlobalException.class,
                () -> theaterScreenImpl.updateTheaterScreen(1,
                        new TheaterScreenDto(1, "AMC", 1, 1,
                                "42 Main St", "6625550144", "jane.doe@example.org")));
        verify(multiplexRepository).findById(Mockito.<Integer>any());
        verify(theaterScreenRepository, atLeast(1)).findById(Mockito.<Integer>any());
        verify(theaterScreenRepository).save(Mockito.<TheaterScreenEntity>any());
    }

    @Test
    void testDeleteTheaterScreen() throws GlobalException {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(1);
        locationEntity.setLocation(Location.CAMPBELL);
        locationEntity.setName("Name");
        locationEntity.setTheaterScreenCount(3);

        TheaterScreenEntity theaterScreenEntity = new TheaterScreenEntity();
        theaterScreenEntity.setAddress("42 Main St");
        theaterScreenEntity.setEmail("jane.doe@example.org");
        theaterScreenEntity.setId(1);
        theaterScreenEntity.setLocationEntity(locationEntity);
        theaterScreenEntity.setName("Name");
        theaterScreenEntity.setPhone("6625550144");
        theaterScreenEntity.setSeatingCapacity(1);
        Optional<TheaterScreenEntity> ofResult = Optional.of(theaterScreenEntity);
        doNothing().when(theaterScreenRepository).deleteById(Mockito.<Integer>any());
        when(theaterScreenRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        theaterScreenImpl.deleteTheaterScreen(1);
        verify(theaterScreenRepository).deleteById(Mockito.<Integer>any());
        verify(theaterScreenRepository).findById(Mockito.<Integer>any());
        assertTrue(theaterScreenImpl.logger instanceof ch.qos.logback.classic.Logger);
        assertTrue(theaterScreenImpl.getAllTheaterScreens().isEmpty());
    }

    @Test
    void testDeleteTheaterScreenIdNotFound() throws GlobalException {
        Optional<TheaterScreenEntity> emptyResult = Optional.empty();
        when(theaterScreenRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        assertThrows(GlobalException.class, () -> theaterScreenImpl.deleteTheaterScreen(1));
        verify(theaterScreenRepository).findById(Mockito.<Integer>any());
    }

    @Test
    void testConfigureSeatingCapacity() throws GlobalException {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(1);
        locationEntity.setLocation(Location.CAMPBELL);
        locationEntity.setName("Name");
        locationEntity.setTheaterScreenCount(3);

        TheaterScreenEntity theaterScreenEntity = new TheaterScreenEntity();
        theaterScreenEntity.setAddress("42 Main St");
        theaterScreenEntity.setEmail("jane.doe@example.org");
        theaterScreenEntity.setId(1);
        theaterScreenEntity.setLocationEntity(locationEntity);
        theaterScreenEntity.setName("Name");
        theaterScreenEntity.setPhone("6625550144");
        theaterScreenEntity.setSeatingCapacity(1);
        Optional<TheaterScreenEntity> ofResult = Optional.of(theaterScreenEntity);

        LocationEntity locationEntity2 = new LocationEntity();
        locationEntity2.setId(1);
        locationEntity2.setLocation(Location.CAMPBELL);
        locationEntity2.setName("Name");
        locationEntity2.setTheaterScreenCount(3);

        TheaterScreenEntity theaterScreenEntity2 = new TheaterScreenEntity();
        theaterScreenEntity2.setAddress("42 Main St");
        theaterScreenEntity2.setEmail("jane.doe@example.org");
        theaterScreenEntity2.setId(1);
        theaterScreenEntity2.setLocationEntity(locationEntity2);
        theaterScreenEntity2.setName("Name");
        theaterScreenEntity2.setPhone("6625550144");
        theaterScreenEntity2.setSeatingCapacity(1);
        when(theaterScreenRepository.save(Mockito.<TheaterScreenEntity>any())).thenReturn(theaterScreenEntity2);
        when(theaterScreenRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        theaterScreenImpl.configureSeatingCapacity(1, 1);
        verify(theaterScreenRepository, atLeast(1)).findById(Mockito.<Integer>any());
        verify(theaterScreenRepository).save(Mockito.<TheaterScreenEntity>any());
    }
}
