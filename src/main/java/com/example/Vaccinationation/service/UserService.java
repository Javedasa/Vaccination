package com.example.Vaccinationation.service;

import com.example.Vaccinationation.dto.requestDto.UserRequestDto;
import com.example.Vaccinationation.dto.responseDto.UserResponseDto;
import com.example.Vaccinationation.model.User;
import com.example.Vaccinationation.repository.UserRepository;
import com.example.Vaccinationation.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public int countOfUserWhoHaveNotTakenDose1NotDose2() {
        List<User>userList=userRepository.findAll();
        int count=0;
        for(User user :userList){
            if(!user.isDose2Taken() && !user.isDose1Taken()) {
                count++;
            }
        }
        return count;
    }

    public int countOfUserTakenDose1NotDose2() {
        List<User>userList=userRepository.findAll();
        int count=0;
        for(User user :userList){
            if(user.isDose1Taken() && !user.isDose2Taken()){
                count++;
            }
        }
        return count;
    }

    public int countOfUserWhoAreFullyVaccinated() {
        List<User>userList=userRepository.findAll();
        int count=0;
        for(User user :userList){
            if(user.isDose1Taken() && user.isDose2Taken()){
                count++;
            }
        }
        return count;
    }

    public int allMaleWhoHaveNotTakenDose1NotDose2() {
        List<User>userList=userRepository.findAll();
        List<User>maleUserList=new ArrayList<>();
        for(User user:userList){
            if(user.getGender().equals("MALE")){
                maleUserList.add(user);
            }
        }
        int count=0;
        for(User user :maleUserList){
            if(!user.isDose2Taken() && !user.isDose1Taken()) count++;
        }
        return count;
    }

    public int allFemaleWhoAreFullyVaccinated() {
        List<User>userList=userRepository.findAll();
        List<User>femaleUserList=new ArrayList<>();
        for(User user:userList){
            if(user.getGender().equals("FEMALE")){
                femaleUserList.add(user);
            }
        }
        int count=0;
        for(User user :femaleUserList){
            if(user.isDose2Taken() && user.isDose1Taken()){
                count++;
            }
        }
        return count;
    }
}
