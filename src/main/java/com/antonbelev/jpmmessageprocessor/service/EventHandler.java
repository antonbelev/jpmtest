package com.antonbelev.jpmmessageprocessor.service;

import com.antonbelev.jpmmessageprocessor.exceptions.EventHandlerException;
import com.antonbelev.jpmmessageprocessor.model.Event;

public interface EventHandler {
    void handleEvent(Event event) throws EventHandlerException;
}
