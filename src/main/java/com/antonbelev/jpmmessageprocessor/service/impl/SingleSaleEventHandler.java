package com.antonbelev.jpmmessageprocessor.service.impl;

import com.antonbelev.jpmmessageprocessor.datastore.DataStore;
import com.antonbelev.jpmmessageprocessor.exceptions.EventHandlerException;
import com.antonbelev.jpmmessageprocessor.model.Event;
import com.antonbelev.jpmmessageprocessor.service.EventHandler;
import com.antonbelev.jpmmessageprocessor.model.Sale;

public class SingleSaleEventHandler implements EventHandler {

    @Override
    public void handleEvent(Event event) throws EventHandlerException {
        if (!(event.getEventBody() instanceof Sale)) {
            throw new EventHandlerException("Event body for SingleSale event is not of type Sale");
        }

        Sale sale = (Sale) event.getEventBody();
        if (sale.getTotalUnits() != 1) {
            throw new EventHandlerException("SingleSale has incorrect totalUnits value=" + sale.getTotalUnits());
        }

        DataStore.addSale(sale);
    }
}
