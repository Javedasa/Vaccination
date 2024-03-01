package com.example.Vaccinationation.service;

import com.example.Vaccinationation.dto.UserRequestDto;
import com.example.Vaccinationation.dto.UserResponseDto;
import com.example.Vaccinationation.model.User;
import com.example.Vaccinationation.repository.UserRepository;
import com.example.Vaccinationation.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        //convert from userRequestDto to user entity


      User savedUser=userRepository.save(UserTransformer.userRequestDtoToUser(userRequestDto));

      //now convert from user entity to userResponseDto
      return UserTransformer.userToUserResponseDto(savedUser);
    }

    public UserResponseDto findByEmailId(String emailId) {
        User user=userRepository.findByEmailId(emailId);
         return UserTransformer.userToUserResponseDto(user);
    }

    public String updateName(String contactNumber, String name) {
        User user=userRepository.findByContactNumber(contactNumber);
        if(user!=null){
            user.setName(name);
        }
        userRepository.save(user);
        return "name updated successfully";
    }

//    public String countOfUserWhoHaveNotTakenDose1NotDose2() {
//        List<User>userList=userRepository.countOfUserWhoHaveNotTakenDose1NotDose2();
//        return "Total count of user who have not taken dose1 and not dose2 is "+userList.size();
//    }

//    public String countOfUserTakenDose1NotDose2() {
//       List<User> userList =userRepository.countOfUserTakenDose1NotDose2();
//        return "Total count of user who has taken dose1 but not dose2 is "+userList.size();
//    }
}
