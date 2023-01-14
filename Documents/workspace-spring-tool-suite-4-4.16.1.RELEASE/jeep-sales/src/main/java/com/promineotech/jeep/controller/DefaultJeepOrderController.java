/**
 * 
 */
package com.promineotech.jeep.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;

/**
 * @author angva
 *
 */
@RestController 
public class DefaultJeepOrderController {
  @Autowired
  private JeepOrderService jeepOrderService;
  @Override
  public Order createOrder(OrderRequest orderRequest) {
    log.info("Order Request in controller layer :: {}", orderRequest);

    return jeepOrderService.createOrder(orderRequest);
  }

  
}
