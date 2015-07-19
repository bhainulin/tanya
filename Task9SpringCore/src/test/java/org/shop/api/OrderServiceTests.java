package org.shop.api;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:context.xml", "classpath:data-initializer.xml"})
public class OrderServiceTests {

    OrderService orderService;
    
    @Test
    public void testCreateOrderUserItemArray() {
        fail("Not yet implemented");
        //orderService.createOrder(user, items);
    }

    @Test
    public void testCreateOrderUserProposalArray() {
        fail("Not yet implemented");
        //orderService.createOrder(user, proposals);
    }

    @Test
    public void testGetOrderById() {
        fail("Not yet implemented");
        //orderService.getOrderById(id);
    }

    @Test
    public void testUpdateOrder() {
        fail("Not yet implemented");
        //orderService.updateOrder(order);
    }

    @Test
    public void testGetOrdersByUser() {
        fail("Not yet implemented");
        //orderService.getOrdersByUser(user);
    }

    @Test
    public void testGetOrdersByUserId() {
        fail("Not yet implemented");
        //orderService.getOrdersByUserId(userId);
    }
}
