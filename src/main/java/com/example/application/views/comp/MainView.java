package com.example.application.views.comp;

import com.example.application.entity.Address;
import com.example.application.entity.Military;
import com.example.application.entity.Nationality;
import com.example.application.repo.AddressRepository;
import com.example.application.repo.MilitaryRepository;
import com.example.application.repo.NationalityRepository;
import com.example.application.service.MainService;
import com.example.application.service.MainViewFormData;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.List;
import java.util.stream.Collectors;

@PageTitle("Person Form")
@Route(value = "main", layout = MainLayout.class)
@Uses(Button.class)
@PermitAll
public class MainView extends Composite<VerticalLayout> {

    private final AddressRepository addressRepository;
    private final NationalityRepository nationalityRepository;
    private final MilitaryRepository militaryRepository;
    private final MainService mainService;

    private final TextField firstName = new TextField("First Name");
    private final TextField lastName = new TextField("Last Name");
    private final TextField nativeName = new TextField("Native Name");
    private final TextField nativeSurname = new TextField("Native Surname");
    private final TextField patronymic = new TextField("Patronymic");
    private final DatePicker birthDate = new DatePicker("Date of Birth");
    private final Select<String> citizenshipSelect = new Select<>();
    private final Select<String> nationalitySelect = new Select<>();
    private final RadioButtonGroup<String> genderGroup = new RadioButtonGroup<>();
    private final RadioButtonGroup<String> maritalStatusGroup = new RadioButtonGroup<>();
    private final Select<String> militarySelect = new Select<>();
    private final TextField inn = new TextField("IIN");

    public MainView(AddressRepository addressRepository, NationalityRepository nationalityRepository, MilitaryRepository militaryRepository, MainService mainService) {
        this.addressRepository = addressRepository;
        this.nationalityRepository = nationalityRepository;
        this.militaryRepository = militaryRepository;
        this.mainService = mainService;
        initLayout();
    }

    private void initLayout() {
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();

        H3 title = new H3("Personal Information");

        citizenshipSelect.setLabel("Citizenship");
        citizenshipSelect.setWidth("250px");
        citizenshipSelect.setRequiredIndicatorVisible(true);

        nationalitySelect.setLabel("Nationality");
        nationalitySelect.setWidth("250px");
        nationalitySelect.setRequiredIndicatorVisible(true);

        genderGroup.setLabel("Gender");
        genderGroup.setItems("male", "female");
        genderGroup.setRequiredIndicatorVisible(true);

        maritalStatusGroup.setLabel("Marital Status");
        maritalStatusGroup.setItems("Single", "Married");
        maritalStatusGroup.setRequiredIndicatorVisible(true);

        militarySelect.setLabel("Military Status");
        militarySelect.setWidth("250px");
        militarySelect.setRequiredIndicatorVisible(true);

        Button saveButton = new Button("Save");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.addClickListener(event -> saveData());

        Button clearButton = new Button("Clear");
        clearButton.addClickListener(event -> clearForm());

        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, clearButton);

        mainLayout.add(title, firstName, lastName, nativeName, nativeSurname, patronymic,
                birthDate, citizenshipSelect, nationalitySelect, genderGroup, maritalStatusGroup, militarySelect, inn, buttonLayout);
        getContent().add(mainLayout);

        loadData();
    }

    private void loadData() {
        List<Address> citizenshipList = addressRepository.findAll();
        List<Nationality> nationalityList = nationalityRepository.findAll();
        List< Military > militaryList = militaryRepository.findAll();

        citizenshipSelect.setItems(citizenshipList.stream().map(Address::getTitleEn).collect(Collectors.toList()));
        nationalitySelect.setItems(nationalityList.stream().map(Nationality::getTitleEn).collect(Collectors.toList()));
        militarySelect.setItems(militaryList.stream().map(Military::getTitleEn).collect(Collectors.toList()));
    }

    private void saveData() {
        try {
            MainViewFormData formData = gatherFormData();

            if (isFormDataValid(formData)) {
                mainService.saveData(formData);
                Notification.show("Data saved successfully!");
            } else {
                Notification.show("Please fill in all required fields");
            }
        } catch (Exception e) {
            Notification.show(e.getMessage());
        }
    }

    private boolean isFormDataValid(MainViewFormData formData) {
        return formData.getName() != null && !formData.getName().isEmpty() &&
                formData.getSurname() != null && !formData.getSurname().isEmpty() &&
                formData.getNameNative() != null && !formData.getNameNative().isEmpty() &&
                formData.getSurnameNative() != null && !formData.getSurnameNative().isEmpty() &&
                formData.getPatronymic() != null && !formData.getPatronymic().isEmpty() &&
                formData.getBirthDate() != null && !formData.getBirthDate().isEmpty() &&
                formData.getCitizenship() != null && !formData.getCitizenship().isEmpty() &&
                formData.getNationality() != null && !formData.getNationality().isEmpty() &&
                formData.getGender() != null && !formData.getGender().isEmpty() &&
                formData.getMaritalStatus() != null && !formData.getMaritalStatus().isEmpty() &&
                formData.getIin() != null && !formData.getIin().isEmpty() &&
                formData.getMilitary() !=null && !formData.getMilitary().isEmpty();
    }

    private MainViewFormData gatherFormData() {
        MainViewFormData formData = new MainViewFormData();
        formData.setName(firstName.getValue());
        formData.setSurname(lastName.getValue());
        formData.setNameNative(nativeName.getValue());
        formData.setSurnameNative(nativeSurname.getValue());
        formData.setPatronymic(patronymic.getValue());
        formData.setBirthDate(birthDate.getValue() != null ? birthDate.getValue().toString() : null);
        formData.setCitizenship(citizenshipSelect.getValue());
        formData.setNationality(nationalitySelect.getValue());
        formData.setGender(genderGroup.getValue());
        formData.setMaritalStatus(maritalStatusGroup.getValue());
        formData.setMilitary(militarySelect.getValue());
        formData.setIin(inn.getValue());
        return formData;
    }

    private void clearForm() {
        firstName.clear();
        lastName.clear();
        nativeName.clear();
        nativeSurname.clear();
        patronymic.clear();
        birthDate.clear();
        citizenshipSelect.clear();
        nationalitySelect.clear();
        genderGroup.clear();
        maritalStatusGroup.clear();
        militarySelect.clear();
        inn.clear();
    }
}
