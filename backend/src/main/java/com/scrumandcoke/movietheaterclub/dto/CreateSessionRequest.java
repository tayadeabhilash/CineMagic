package com.scrumandcoke.movietheaterclub.dto;

import lombok.*;

import java.time.Duration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSessionRequest {

    @NonNull
    String userId;

    @NonNull
    Duration sessionDuration;
}
