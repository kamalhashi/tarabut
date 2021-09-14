package com.tarabut.customer.query.handlers;

import com.tarabut.customer.core.events.AddCustomerEvent;
import com.tarabut.customer.core.events.DeleteCustomerEvent;
import com.tarabut.customer.core.events.UpdateCustomerEvent;

public interface CustomerEventHandler {
    void on(AddCustomerEvent event);
    void on(UpdateCustomerEvent event);
    void on(DeleteCustomerEvent event);


}
