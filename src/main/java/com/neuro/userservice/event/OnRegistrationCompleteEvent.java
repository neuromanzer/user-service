package com.neuro.userservice.event;

import com.neuro.userservice.model.User;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private User user;

    public OnRegistrationCompleteEvent(User user, String appUrl) {
        super(user);
        this.user = user;
        this.appUrl = appUrl;
    }
}
