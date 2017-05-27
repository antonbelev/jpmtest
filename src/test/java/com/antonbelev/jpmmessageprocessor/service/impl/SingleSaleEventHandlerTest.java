package com.antonbelev.jpmmessageprocessor.service.impl;

import com.antonbelev.jpmmessageprocessor.datastore.DataStore;
import com.antonbelev.jpmmessageprocessor.exceptions.EventHandlerException;
import com.antonbelev.jpmmessageprocessor.model.Adjustment;
import com.antonbelev.jpmmessageprocessor.model.Event;
import com.antonbelev.jpmmessageprocessor.model.Sale;
import com.antonbelev.jpmmessageprocessor.model.enums.AdjustmentType;
import com.antonbelev.jpmmessageprocessor.model.enums.MessageType;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SingleSaleEventHandlerTest {
    private SingleSaleEventHandler singleSaleEventHandler;
    private List<Sale> sales;
    private Sale sale1 = new Sale("Apple", BigDecimal.valueOf(0.50), 1);
    private Sale sale2 = new Sale("Orange", BigDecimal.valueOf(0.75), 3);

    @Before
    public void setUp() {
        singleSaleEventHandler = new SingleSaleEventHandler();
        sales = new ArrayList<>();
        DataStore.salesHistory = sales;
    }

    @Test
    public void handleEvent_ok() throws Exception {
        Event event = new Event(MessageType.MULTI_SALE, sale1);
        singleSaleEventHandler.handleEvent(event);
        assertEquals(sale1, DataStore.salesHistory.get(0));
    }

    @Test(expected = EventHandlerException.class)
    public void handleEvent_unit_size_3_should_throw_exception() throws Exception {
        Event event = new Event(MessageType.MULTI_SALE, sale2);
        singleSaleEventHandler.handleEvent(event);
    }

    @Test(expected = EventHandlerException.class)
    public void handleEvent_incorrect_body_type() throws Exception {
        Event event = new Event(MessageType.MULTI_SALE, new Adjustment(AdjustmentType.MULTIPLY,
                "Apple", BigDecimal.valueOf(2)));
        singleSaleEventHandler.handleEvent(event);
    }

}