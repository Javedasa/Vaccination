package com.example.Vaccinationation.service;

import com.example.Vaccinationation.dto.responseDto.CertificateResponseDto;
import com.example.Vaccinationation.exception.UserNotFoundException;
import com.example.Vaccinationation.model.Certificate;
import com.example.Vaccinationation.model.User;
import com.example.Vaccinationation.repository.CertificateRepository;
import com.example.Vaccinationation.repository.UserRepository;
import com.example.Vaccinationation.transformer.CertificateTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CertificateService {


    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private UserRepository userRepository;


    //generate certificate with appointment id
    public CertificateResponseDto generateCertificate(Integer id) throws Exception {
       Optional<User> user=userRepository.findById(id);
       if(!user.isPresent()){
           throw new UserNotFoundException("User Not Found Exception");
       }
       User user1=user.get();

           Certificate savedCertificate=user1.getCertificate();
           CertificateResponseDto certificateResponseDto= CertificateTransformer.CertificateToCertificateResponseDto(savedCertificate);
         return certificateResponseDto;

    }

    public Certificate createCertificate(User user) {
        Certificate certificate=new Certificate();
        certificate.setUser(user);
        user.setCertificate(certificate);
        return certificate;
    }
}
