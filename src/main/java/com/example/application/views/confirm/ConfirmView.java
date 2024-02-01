package com.example.application.views.confirm;

import com.example.application.service.UserService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "confirm")
@AnonymousAllowed
public class ConfirmView extends Div
        implements HasUrlParameter<String> {
    private final UserService userService;

    public ConfirmView(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void setParameter(BeforeEvent event,
                             String parameter) {
        setText(String.format("Hello, %s!", parameter));
        userService.confirmUser(parameter);

    }
}