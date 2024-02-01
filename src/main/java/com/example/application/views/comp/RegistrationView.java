package com.example.application.views.comp;

import com.example.application.exception.BadRequestException;
import com.example.application.service.MailSenderService;
import com.example.application.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.UUID;

@Route("register")
@PageTitle("REgister | Vaadin CRM")
@AnonymousAllowed
public class RegistrationView extends VerticalLayout {

    private final UserService userService;

    public RegistrationView(UserService userService) {
        this.userService = userService;

        TextField usernameField = new TextField("Username");
        PasswordField passwordField = new PasswordField("Password");
        PasswordField confirmPasswordField = new PasswordField("Confirm Password");

        Button registerButton = new Button("Register", event -> registerUser(usernameField.getValue(), passwordField.getValue(), confirmPasswordField.getValue()));

        add(usernameField, passwordField,confirmPasswordField, registerButton);
    }

    private void registerUser(String username, String password, String rePassword) {

        try {
            userService.registerUser(username, password, rePassword);
            UI.getCurrent().navigate(LoginView.class);

        }
        catch (Exception e){
            Notification.show(e.getMessage());
        }


    }
}

