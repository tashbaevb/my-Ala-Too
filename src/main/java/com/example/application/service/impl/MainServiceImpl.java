package com.example.application.service.impl;

import com.example.application.entity.*;
import com.example.application.repo.*;
import com.example.application.service.MainService;
import com.example.application.service.MainViewFormData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class MainServiceImpl implements MainService {

    private final ApplicantRepository applicantRepository;
    private final StudInfoRepository studInfoRepository;
    private final AddressRepository addressRepository;
    private final AddressTypeRepository addressTypeRepository;
    private final NationalityRepository nationalityRepository;
    private final GenderRepository genderRepository;
    private final MaritalStatusRepository maritalStatusRepository;
    private final MilitaryRepository militaryRepository;


    @Override
    public void saveData(MainViewFormData formData) {

        AddressType addressType = new AddressType();
        addressType.setTitleKg("TypeKg");
        addressType.setTitleRu("TypeRu");
        addressType.setTitleEn("TypeEn");
        addressTypeRepository.save(addressType);

        Address address = new Address();
        address.setAddressType(addressType);
        address.setP(null);
        address.setTitleKg("TitleKg");
        address.setTitleRu("TitleRu");
        address.setTitleEn("TitleEn");
        addressRepository.save(address);

        Nationality nationality = new Nationality();
        nationality.setTitleKg("NatKg");
        nationality.setTitleRu("NatRu");
        nationality.setTitleEn("NatEn");
        nationalityRepository.save(nationality);

        Gender gender = new Gender();
        gender.setTitleKg("GenderKg");
        gender.setTitleRu("GenderRu");
        gender.setTitleEn("GenderEn");
        genderRepository.save(gender);

        MaritalStatus maritalStatus = new MaritalStatus();
        maritalStatus.setTitleKg("MaritalKg");
        maritalStatus.setTitleRu("MaritalRu");
        maritalStatus.setTitleEn("MaritalEn");
        maritalStatusRepository.save(maritalStatus);

        Military military = new Military();
        military.setTitleKg("MilitaryKg");
        military.setTitleRu("MilitaryRu");
        military.setTitleEn("MilitaryEn");
        militaryRepository.save(military);

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
        studInfo.setCitizenship(address);
        studInfo.setNationality(nationality);
        studInfo.setGender(gender);
        studInfo.setMaritalStatus(maritalStatus);
        studInfo.setIin(formData.getIin());
        studInfo.setMilitary(military);
        studInfoRepository.save(studInfo);
    }
}
