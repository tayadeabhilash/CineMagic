package com.scrumandcoke.movietheaterclub.dto;

import com.scrumandcoke.movietheaterclub.enums.MemberType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NotNull
    @NonNull
    private String firstName;

    @NotNull
    @NonNull
    private String lastName;

    @NotNull
    @NonNull
    private String email;

    @NotNull
    @NonNull
    private String password;

    private MemberType memberType;
}
