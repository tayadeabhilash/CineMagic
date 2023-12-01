package com.scrumandcoke.movietheaterclub.controller;

import com.scrumandcoke.movietheaterclub.dto.MovieDto;
import com.scrumandcoke.movietheaterclub.dto.ShowTimeDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.enums.Location;
import com.scrumandcoke.movietheaterclub.service.ShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/showtime")
public class ShowTimeController {

    @Autowired
    ShowTimeService showTimeService;

    @PostMapping
    public ResponseEntity<String> addShowTIme(@RequestBody ShowTimeDto showTimeDto) throws GlobalException {
        try {
            showTimeService.addShowTime(showTimeDto);
            return ResponseEntity.ok("showTime added successfully");
        } catch (GlobalException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding showTime: " + e.getMessage());
        }
    }

    @GetMapping("movie/{movieId}")
    public ResponseEntity<?> getshowTimesByMovieId(@PathVariable int movieId) {
        try {
            List<ShowTimeDto> showTimes = showTimeService.getShowTimesByMovie(movieId);
            return ResponseEntity.ok(showTimes);
        } catch (GlobalException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting showTimes: " + e.getMessage());
        }
    }

    @GetMapping("screen/{screenId}")
    public ResponseEntity<?> getshowTimesByScreenId(@PathVariable int screenId) {
        try {
            List<ShowTimeDto> showTimes = showTimeService.getShowTimesByTheaterScreenId(screenId);
            return ResponseEntity.ok(showTimes);
        } catch (GlobalException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting showTimes: " + e.getMessage());
        }
    }

    @GetMapping("screen/{screenId}/movie/{movieId}")
    public ResponseEntity<?> getshowTimesByScreenIdAndMovieId(@PathVariable int screenId, @PathVariable int movieId) {
        try {
            List<ShowTimeDto> showTimes = showTimeService.getShowTimesByTheaterScreenIdAndMovieId(screenId, movieId);
            return ResponseEntity.ok(showTimes);
        } catch (GlobalException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting showTimes: " + e.getMessage());
        }
    }

    @GetMapping("screen/{screenId}/movies")
    public ResponseEntity<?> getMoviesByScreenId(@PathVariable int screenId) {
        try {
            List<MovieDto> movies = showTimeService.getAllMoviesByTheaterScreenId(screenId);
            return ResponseEntity.ok(movies);
        } catch (GlobalException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting showTimes: " + e.getMessage());
        }
    }

    @GetMapping("location/{locationName}")
    public ResponseEntity<?> getshowTimesByLocation(@PathVariable Location locationName) {
        try {
            List<ShowTimeDto> showTimes = showTimeService.getShowTimesByLocation(locationName);
            return ResponseEntity.ok(showTimes);
        } catch (GlobalException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting showTimes: " + e.getMessage());
        }
    }

    @GetMapping("multiplex/{multiplexId}/screen/{screenId}")
    public ResponseEntity<?> getshowTimesByScreenId(@PathVariable int multiplexId, @PathVariable int screenId) {
        try {
            List<ShowTimeDto> showTimes = showTimeService.getShowTimesByTheaterScreenIdAndMultiplexId(screenId, multiplexId);
            return ResponseEntity.ok(showTimes);
        } catch (GlobalException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting showTimes: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWShowTimeById(@PathVariable int id) {
        try {
            ShowTimeDto showTimeDto = showTimeService.getShowTime(id);
            return ResponseEntity.ok(showTimeDto);
        } catch (GlobalException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error getting showTime: " + e.getMessage());
        }
    }

    @PutMapping
    public void updateshowTime(@RequestBody ShowTimeDto showTimeDto) throws GlobalException {
        showTimeService.updateShowTime(showTimeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteshowTime(@PathVariable int id) {
        try {
            showTimeService.deleteShowTime(id);
            return ResponseEntity.ok().build();
        } catch (GlobalException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error deleting showTime: " + e.getMessage());
        }
    }
}
