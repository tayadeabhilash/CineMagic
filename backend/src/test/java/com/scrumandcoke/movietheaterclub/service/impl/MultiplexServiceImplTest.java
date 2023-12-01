package com.scrumandcoke.movietheaterclub.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.scrumandcoke.movietheaterclub.dto.LocationDto;
import com.scrumandcoke.movietheaterclub.entity.LocationEntity;
import com.scrumandcoke.movietheaterclub.enums.Location;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.repository.MultiplexRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MultiplexServiceImpl.class})
@ExtendWith(SpringExtension.class)
class MultiplexServiceImplTest {
  @MockBean
  private MultiplexRepository multiplexRepository;

  @Autowired
  private MultiplexServiceImpl multiplexServiceImpl;

  @Test
  void testAddMultiplexDuplicateNameError() throws GlobalException {
    when(multiplexRepository.existsByName(Mockito.<String>any())).thenReturn(true);
    assertThrows(GlobalException.class,
            () -> multiplexServiceImpl.addMultiplex(new LocationDto(1, "Name", Location.CAMPBELL, 3)));
    verify(multiplexRepository).existsByName(Mockito.<String>any());
  }

  @Test
  void testAddMultiplex() throws GlobalException {
    LocationEntity locationEntity = new LocationEntity();
    locationEntity.setId(1);
    locationEntity.setLocation(Location.CAMPBELL);
    locationEntity.setName("Name");
    locationEntity.setTheaterScreenCount(3);
    when(multiplexRepository.existsByName(Mockito.<String>any())).thenReturn(false);
    when(multiplexRepository.save(Mockito.<LocationEntity>any())).thenReturn(locationEntity);
    multiplexServiceImpl.addMultiplex(new LocationDto(1, "Name", Location.CAMPBELL, 3));
    verify(multiplexRepository).existsByName(Mockito.<String>any());
    verify(multiplexRepository).save(Mockito.<LocationEntity>any());
  }

  @Test
  void testAddMultiplexNullName() throws GlobalException {
    assertThrows(GlobalException.class,
            () -> multiplexServiceImpl.addMultiplex(new LocationDto(1, null, Location.CAMPBELL, 3)));
  }

  @Test
  void testAddMultiplexEmptyName() throws GlobalException {
    assertThrows(GlobalException.class,
            () -> multiplexServiceImpl.addMultiplex(new LocationDto(1, "", Location.CAMPBELL, 3)));
  }

  @Test
  void testAddMultiplexEmptyLocation() throws GlobalException {
    assertThrows(GlobalException.class, () -> multiplexServiceImpl.addMultiplex(new LocationDto(1, "Name", null, 3)));
  }

  @Test
  void testAddMultiplexTheaterCountZero() throws GlobalException {
    assertThrows(GlobalException.class,
            () -> multiplexServiceImpl.addMultiplex(new LocationDto(1, "Name", Location.CAMPBELL, 0)));
  }

  @Test
  void testAddMultiplexNullRequest() throws GlobalException {
    assertThrows(GlobalException.class, () -> multiplexServiceImpl.addMultiplex(null));
  }

  @Test
  void testAddMultiplexEntityNotFound() throws GlobalException {
    LocationDto multiplexDto = mock(LocationDto.class);
    when(multiplexDto.getName()).thenThrow(new EntityNotFoundException("An error occurred"));
    assertThrows(EntityNotFoundException.class, () -> multiplexServiceImpl.addMultiplex(multiplexDto));
    verify(multiplexDto).getName();
  }

  @Test
  void testGetAllMultiplexEntityNotFound() throws GlobalException {
    when(multiplexRepository.findAll()).thenThrow(new EntityNotFoundException("An error occurred"));
    assertThrows(GlobalException.class, () -> multiplexServiceImpl.getAllMultiplex());
    verify(multiplexRepository).findAll();
  }

  @Test
  void testGetAllMultiplexByLocationEntityNotFound() throws GlobalException {
    when(multiplexRepository.findByLocation(Mockito.<Location>any()))
            .thenThrow(new EntityNotFoundException("An error occurred"));
    assertThrows(GlobalException.class, () -> multiplexServiceImpl.getAllMultiplexByLocation(Location.CAMPBELL));
    verify(multiplexRepository).findByLocation(Mockito.<Location>any());
  }

  @Test
  void testUpdateMultiplexMultiplexNotFound() throws GlobalException {
    when(multiplexRepository.existsByNameAndIdNot(Mockito.<String>any(), Mockito.<Integer>any())).thenReturn(true);
    assertThrows(GlobalException.class,
            () -> multiplexServiceImpl.updateMultiplex(1, new LocationDto(1, "Name", Location.CAMPBELL, 3)));
    verify(multiplexRepository).existsByNameAndIdNot(Mockito.<String>any(), Mockito.<Integer>any());
  }

  @Test
  void testUpdateMultiplex() throws GlobalException {
    LocationEntity locationEntity = new LocationEntity();
    locationEntity.setId(1);
    locationEntity.setLocation(Location.CAMPBELL);
    locationEntity.setName("Name");
    locationEntity.setTheaterScreenCount(3);
    Optional<LocationEntity> ofResult = Optional.of(locationEntity);

    LocationEntity locationEntity2 = new LocationEntity();
    locationEntity2.setId(1);
    locationEntity2.setLocation(Location.CAMPBELL);
    locationEntity2.setName("Name");
    locationEntity2.setTheaterScreenCount(3);
    when(multiplexRepository.save(Mockito.<LocationEntity>any())).thenReturn(locationEntity2);
    when(multiplexRepository.existsByNameAndIdNot(Mockito.<String>any(), Mockito.<Integer>any())).thenReturn(false);
    when(multiplexRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
    multiplexServiceImpl.updateMultiplex(1, new LocationDto(1, "Name", Location.CAMPBELL, 3));
    verify(multiplexRepository).existsByNameAndIdNot(Mockito.<String>any(), Mockito.<Integer>any());
    verify(multiplexRepository).findById(Mockito.<Integer>any());
    verify(multiplexRepository).save(Mockito.<LocationEntity>any());
  }

  @Test
  void testUpdateMultiplexNotFound() throws GlobalException {
    when(multiplexRepository.existsByNameAndIdNot(Mockito.<String>any(), Mockito.<Integer>any())).thenReturn(false);
    Optional<LocationEntity> emptyResult = Optional.empty();
    when(multiplexRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
    assertThrows(GlobalException.class,
            () -> multiplexServiceImpl.updateMultiplex(1, new LocationDto(1, "Name", Location.CAMPBELL, 3)));
    verify(multiplexRepository).existsByNameAndIdNot(Mockito.<String>any(), Mockito.<Integer>any());
    verify(multiplexRepository).findById(Mockito.<Integer>any());
  }

  @Test
  void testUpdateMultiplexInvalidId() throws GlobalException {
    assertThrows(GlobalException.class,
            () -> multiplexServiceImpl.updateMultiplex(0, new LocationDto(1, "Name", Location.CAMPBELL, 3)));
  }

  @Test
  void testUpdateMultiplexInvalidName() throws GlobalException {
    assertThrows(GlobalException.class,
            () -> multiplexServiceImpl.updateMultiplex(1, new LocationDto(1, null, Location.CAMPBELL, 3)));
  }

  @Test
  void testDeleteMultiplex() throws GlobalException {
    doNothing().when(multiplexRepository).deleteById(Mockito.<Integer>any());
    multiplexServiceImpl.deleteMultiplex(1);
    verify(multiplexRepository).deleteById(Mockito.<Integer>any());
    assertTrue(multiplexServiceImpl.logger instanceof ch.qos.logback.classic.Logger);
    assertTrue(multiplexServiceImpl.getAllMultiplex().isEmpty());
  }

  @Test
  void testDeleteMultiplexNotFound() throws GlobalException {
    doThrow(new EntityNotFoundException("An error occurred")).when(multiplexRepository)
            .deleteById(Mockito.<Integer>any());
    assertThrows(GlobalException.class, () -> multiplexServiceImpl.deleteMultiplex(1));
    verify(multiplexRepository).deleteById(Mockito.<Integer>any());
  }

  @Test
  void testGetMultiplex() throws GlobalException {
    LocationEntity locationEntity = new LocationEntity();
    locationEntity.setId(1);
    locationEntity.setLocation(Location.CAMPBELL);
    locationEntity.setName("Name");
    locationEntity.setTheaterScreenCount(3);
    Optional<LocationEntity> ofResult = Optional.of(locationEntity);
    when(multiplexRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
    LocationDto actualMultiplex = multiplexServiceImpl.getMultiplex(1);
    verify(multiplexRepository).findById(Mockito.<Integer>any());
    assertEquals("Name", actualMultiplex.getName());
    assertEquals(1, actualMultiplex.getId());
    assertEquals(3, actualMultiplex.getTheaterScreenCount());
    assertEquals(Location.CAMPBELL, actualMultiplex.getLocation());
  }

  @Test
  void testGetMultiplexEmptyResult() throws GlobalException {
    Optional<LocationEntity> emptyResult = Optional.empty();
    when(multiplexRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
    assertThrows(GlobalException.class, () -> multiplexServiceImpl.getMultiplex(1));
    verify(multiplexRepository).findById(Mockito.<Integer>any());
  }

  @Test
  void testGetMultiplexNotFound() throws GlobalException {
    when(multiplexRepository.findById(Mockito.<Integer>any()))
            .thenThrow(new EntityNotFoundException("An error occurred"));
    assertThrows(GlobalException.class, () -> multiplexServiceImpl.getMultiplex(1));
    verify(multiplexRepository).findById(Mockito.<Integer>any());
  }
}
