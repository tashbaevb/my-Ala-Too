package com.example.application.views.comp;

import com.example.application.entity.Address;
import com.example.application.entity.Nationality;
import com.example.application.exception.BadRequestException;
import com.example.application.repo.AddressRepository;
import com.example.application.repo.NationalityRepository;
import com.example.application.service.MainService;
import com.example.application.service.MainViewFormData;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import jakarta.annotation.security.PermitAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@PageTitle("Person Form")
@Route(value = "main", layout = MainLayout.class)
@Uses(Icon.class)
@PermitAll
public class MainView extends Composite<VerticalLayout> {

    private final AddressRepository addressRepository;
    private final NationalityRepository nationalityRepository;

    private final MainService mainService;

    public MainView(AddressRepository addressRepository, NationalityRepository nationalityRepository, MainService mainService) {
        this.addressRepository = addressRepository;
        this.nationalityRepository = nationalityRepository;
        this.mainService = mainService;
        initLayout();
    }

    TextField textField = new TextField();
    TextField textField2 = new TextField();
    TextField textField3 = new TextField();
    TextField textField4 = new TextField();
    TextField textField5 = new TextField();
    DatePicker datePicker = new DatePicker();
    Select citizenshipSelect = new Select();
    Select nationalitySelect = new Select();
    Select militarySelect = new Select();
    Checkbox checkbox2 = new Checkbox();
    Checkbox checkbox4 = new Checkbox();
    TextField textField6 = new TextField();


    private void initLayout() {
        mainPage();
    }


    private void saveData() {
        System.out.println("saveData is working\n\n");
        MainViewFormData formData = gatherFormData();
        System.out.println(formData.toString() + "\n\n");

        if (formData.getName() != null) {
            mainService.saveData(formData);
        } else {
            System.out.println("name is null");
        }
    }

    private MainViewFormData gatherFormData() {
        MainViewFormData formData = new MainViewFormData();
        formData.setName(textField.getValue());
        System.out.println("formData Name " + textField.getValue());
        formData.setSurname(textField2.getValue());
        formData.setNameNative(textField3.getValue());
        formData.setSurnameNative(textField4.getValue());
        formData.setPatronymic(textField5.getValue());
        formData.setBirthDate(datePicker.getValue() != null ? datePicker.getValue().toString() : null);
        SampleItem sampleItem = (SampleItem) citizenshipSelect.getValue();

        System.out.println("the address: " + sampleItem.value);
        Optional<Address> citizenship = addressRepository.findByTitleKgOrTitleRuOrTitleEn(sampleItem.value,
                sampleItem.value, sampleItem.value);
        if (citizenship.isEmpty())
            throw new BadRequestException("no address with this name!");
        formData.setCitizenship(sampleItem.value);
        SampleItem nationalityItem = (SampleItem) nationalitySelect.getValue();

        formData.setNationality(nationalityItem.value);
        System.out.println(militarySelect.getValue());
        SampleItem militaryItem = (SampleItem) militarySelect.getValue();
        if (militaryItem != null)
            formData.setMilitary(militaryItem.value);
        formData.setGender(checkbox2.getValue() ? "Male" : "Female");
        formData.setMaritalStatus(checkbox4.getValue() ? "Single" : "Married");
        formData.setIin(textField6.getValue());
        return formData;
    }


    private void clearForm() {
        textField.setValue("");
        textField2.setValue("");
        textField3.setValue("");
        textField4.setValue("");
        textField5.setValue("");
        textField6.setValue("");
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void mainPage() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Checkbox checkbox = new Checkbox();

        HorizontalLayout layoutRow2 = new HorizontalLayout();
        Paragraph textMedium = new Paragraph();
        Checkbox checkbox2 = new Checkbox();
        Checkbox checkbox3 = new Checkbox();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        Paragraph textMedium2 = new Paragraph();
        Checkbox checkbox4 = new Checkbox();
        Checkbox checkbox5 = new Checkbox();
        TextField textField6 = new TextField();
        TextField textField7 = new TextField();
        Select select3 = new Select();
        TextField textField8 = new TextField();
        TextField textField9 = new TextField();
        DatePicker datePicker2 = new DatePicker();
        HorizontalLayout layoutRow4 = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();


        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);

        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");

        h3.setText("Personal Information");
        h3.setWidth("100%");

        textField.setLabel("First Name");

        textField2.setLabel("Last Name");

        textField3.setLabel("Native Name");

        textField4.setLabel("Native Surname");

        layoutRow.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        textField5.setLabel("Patronymic");
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, textField5);
        checkbox.setLabel("I haven`t");
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, checkbox);
        checkbox.getStyle().set("flex-grow", "1");
        checkbox.setMaxWidth("150px");
        checkbox.setMaxHeight("1px");

        datePicker.setLabel("Date of birth:");

        citizenshipSelect.setLabel("Citizenship");
        citizenshipSelect.setWidth("min-content");
        setCitizenshipSelect(citizenshipSelect);

        nationalitySelect.setLabel("Nationality:");
        nationalitySelect.setWidth("min-content");
        setNationalitySelect(nationalitySelect);

        layoutRow2.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");

        textMedium.setText("Gender:");
        textMedium.setWidth("200px");
        textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");
        checkbox2.setLabel("Male");
        layoutRow2.setAlignSelf(FlexComponent.Alignment.CENTER, checkbox2);
        checkbox2.setWidth("min-content");
        checkbox3.setLabel("Female");
        layoutRow2.setAlignSelf(FlexComponent.Alignment.CENTER, checkbox3);
        checkbox3.setWidth("min-content");

        layoutRow3.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set("flex-grow", "1");

        textMedium2.setText("Married status:");
        textMedium2.getStyle().set("flex-grow", "1");
        textMedium2.setMaxWidth("200px");
        textMedium2.getStyle().set("font-size", "var(--lumo-font-size-m)");
        checkbox4.setLabel("Single");
        layoutRow3.setAlignSelf(FlexComponent.Alignment.CENTER, checkbox4);
        checkbox4.setWidth("min-content");
        checkbox4.setMaxHeight("30px");
        checkbox5.setLabel("Married");
        layoutRow3.setAlignSelf(FlexComponent.Alignment.CENTER, checkbox5);
        checkbox5.setWidth("min-content");
        checkbox5.setMaxHeight("30px");

        textField6.setLabel("IIN:");
        textField7.setLabel("Information about military registration:");
        textField7.setWidth("100%");
        textField7.setMaxWidth("270px");

        select3.setLabel("Document Type:");
        select3.setWidth("min-content");
        // setSelectSampleData(select3);

        textField8.setLabel("Document no:");

        textField9.setLabel("Issued by:");

        datePicker2.setLabel("Issuance date");
        layoutRow4.addClassName(Gap.MEDIUM);
        layoutRow4.setWidth("100%");
        layoutRow4.getStyle().set("flex-grow", "1");

        buttonPrimary.setText("Save");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.addClickListener(event -> saveData());
        layoutRow4.add(buttonPrimary);


        buttonSecondary.setText("Cancel");
        buttonSecondary.setWidth("min-content");
        getContent().add(layoutColumn2);
        buttonSecondary.addClickListener(event -> clearForm());


        layoutColumn2.add(h3);
        layoutColumn2.add(textField);
        layoutColumn2.add(textField2);
        layoutColumn2.add(textField3);
        layoutColumn2.add(textField4);
        layoutColumn2.add(layoutRow);
        layoutRow.add(textField5);
        layoutRow.add(checkbox);
        layoutColumn2.add(datePicker);
        layoutColumn2.add(citizenshipSelect);
        layoutColumn2.add(nationalitySelect);
        layoutColumn2.add(layoutRow2);
        layoutRow2.add(textMedium);
        layoutRow2.add(checkbox2);
        layoutRow2.add(checkbox3);
        layoutColumn2.add(layoutRow3);
        layoutRow3.add(textMedium2);
        layoutRow3.add(checkbox4);
        layoutRow3.add(checkbox5);
        layoutColumn2.add(textField6);
        layoutColumn2.add(textField7);
        layoutColumn2.add(select3);
        layoutColumn2.add(textField8);
        layoutColumn2.add(textField9);
        layoutColumn2.add(datePicker2);
        getContent().add(layoutRow4);
        layoutRow4.add(buttonPrimary);
        layoutRow4.add(buttonSecondary);
    }

    private void setNationalitySelect(Select nationalitySelect) {
        List<Nationality> nationalities = nationalityRepository.findAll();

        List<SampleItem> sampleItems = new ArrayList<>();
        for (Nationality nationality : nationalities) {
            sampleItems.add(new SampleItem(nationality.getTitleEn(), nationality.getTitleEn(), null));
        }
        nationalitySelect.setItems(sampleItems);
        nationalitySelect.setItemLabelGenerator(item -> ((SampleItem) item).label());
        nationalitySelect.setItemEnabledProvider(item -> !Boolean.TRUE.equals(((SampleItem) item).disabled()));
    }

    private void setCitizenshipSelect(Select select) {
        List<Address> citizenship = addressRepository.findAll();

        List<SampleItem> sampleItems = new ArrayList<>();
        for (Address address : citizenship) {
            sampleItems.add(new SampleItem(address.getTitleEn(), address.getTitleEn(), null));
        }
        select.setItems(sampleItems);
        select.setItemLabelGenerator(item -> ((SampleItem) item).label());
        select.setItemEnabledProvider(item -> !Boolean.TRUE.equals(((SampleItem) item).disabled()));
    }
}