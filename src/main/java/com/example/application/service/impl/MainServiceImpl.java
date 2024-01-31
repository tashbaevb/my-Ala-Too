package com.example.application.service.impl;

import com.example.application.entity.*;
import com.example.application.repo.*;
import com.example.application.service.MainService;
import com.example.application.service.MainViewFormData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MainServiceImpl implements MainService {

    private final ApplicantRepository applicantRepository;
    private final StudInfoRepository studInfoRepository;
    private final GenderRepository genderRepository;
    private final AddressRepository addressRepository;
    private final NationalityRepository nationalityRepository;
    private final MaritalStatusRepository maritalStatusRepository;
    private final MilitaryRepository militaryRepository;


    @Override
    public void saveData(MainViewFormData formData) {
        System.out.println("tyhe citizenship:1121"+formData.getCitizenship());

        Optional<Address>  address = addressRepository.findByTitleEn(formData.getCitizenship());
        if (address.isEmpty()){
            System.out.println("address is null" + address.get());
        }
        else {
            System.out.println("address is not null");
            System.out.println(address.get().getTitleEn());
        }
        Optional<Nationality>   nationality = nationalityRepository.findByTitleEn(formData.getNationality());
        Optional<Gender>   gender = genderRepository.findByTitleEn(formData.getGender());
        Optional<MaritalStatus> maritalStatus = maritalStatusRepository.findByTitleEn(formData.getMaritalStatus());
        Optional<Military>   military = militaryRepository.findByTitleEn(formData.getMilitary());










        Applicant applicant = new Applicant();
        applicant.setName(formData.getName());
        applicant.setSurname(formData.getSurname());
        applicant.setName_native(formData.getNameNative());
        applicant.setSurname_native(formData.getSurnameNative());
        applicant.setPatronymic(formData.getPatronymic());
        applicantRepository.save(applicant);

        StudInfo studInfo = new StudInfo();
        studInfo.setApplicant(applicant);
        studInfo.setBirthDate(new Date());
        studInfo.setCitizenship(address.get());
        studInfo.setNationality(nationality.get());
        if (gender.isPresent())
            studInfo.setGender(gender.get());
        else {
            System.out.println("gender is null");
        }
        if (maritalStatus.isPresent())
            studInfo.setMaritalStatus(maritalStatus.get());
        else {
            System.out.println("marital is null");
        }
        studInfo.setIin(formData.getIin());
        if (military.isPresent())
            studInfo.setMilitary(military.get());
        else {
            System.out.println("military is null");
        }
        studInfoRepository.save(studInfo);
    }
}
