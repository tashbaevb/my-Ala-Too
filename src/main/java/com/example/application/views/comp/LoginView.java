package com.example.application.views.comp;

import com.example.application.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@PageTitle("Login | My Ala-Too PMS")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm login = new LoginForm();
    private final UserService userService;

    public LoginView(UserService userService) {
        this.userService = userService;

        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("");

        login.addLoginListener(e -> auth(e.getUsername(), e.getPassword()));
        add(new H1("My Ala-Too PMS"), login);
        add(createRegistrationLink());
    }

    private void auth(String email, String password) {
        try {
            userService.auth(email, password);
            UI.getCurrent().navigate("main");

        }
        catch (Exception e){
            login.setError(true);
            Notification.show(e.getMessage());
        }

    }

    private Anchor createRegistrationLink() {
        Anchor registrationLink = new Anchor("register", "Don't have an account? Register here");
        return registrationLink;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}
