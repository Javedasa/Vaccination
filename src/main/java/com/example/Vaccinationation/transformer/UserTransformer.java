package com.example.Vaccinationation.transformer;

import com.example.Vaccinationation.dto.UserRequestDto;
import com.example.Vaccinationation.dto.UserResponseDto;
import com.example.Vaccinationation.model.User;

public class UserTransformer {
    //this class is used for to transform from UserRequestDto to User
    //and from user to userResponseDto

    public static User userRequestDtoToUser(UserRequestDto userRequestDto){
        User user=User.builder()
                .name(userRequestDto.getName())
                .age(userRequestDto.getAge())
                .gender(userRequestDto.getGender())
                .contactNumber(userRequestDto.getContactNumber())
                .emailId(userRequestDto.getEmailId())
                .build();
        return user;
    }

    public static UserResponseDto userToUserResponseDto(User user){

        UserResponseDto userResponseDto=UserResponseDto.builder()
                .name(user.getName())
                .contactNumber(user.getContactNumber())
                .message("This is information related to this user")
                .build();

        return userResponseDto;
    }
}
