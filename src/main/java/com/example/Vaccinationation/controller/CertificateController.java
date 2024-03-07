package com.example.Vaccinationation.controller;

import com.example.Vaccinationation.dto.responseDto.CertificateResponseDto;
import com.example.Vaccinationation.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/certificate")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @GetMapping("/generateCertificate/{id}")
    public ResponseEntity generateCertificate(@PathVariable("id")Integer id){
        try{
            CertificateResponseDto certificateResponseDto=certificateService.generateCertificate(id);
            return new ResponseEntity(certificateResponseDto, HttpStatus.OK);
        }
        catch (Exception e){
          return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
