package com.example.application.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MainViewFormData {

    private String name;
    private String surname;
    private String patronymic;
    private String nameNative;
    private String surnameNative;
    private String birthDate;
    private String citizenship;
    private String nationality;
    private String gender;
    private String maritalStatus;
    private String military;
    private String iin;
}
