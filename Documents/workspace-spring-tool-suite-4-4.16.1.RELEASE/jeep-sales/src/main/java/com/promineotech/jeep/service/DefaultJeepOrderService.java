/**
 * 
 */
package com.promineotech.jeep.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.jeep.dao.JeepOrderDao;
import com.promineotech.jeep.entity.Color;
import com.promineotech.jeep.entity.Customer;
import com.promineotech.jeep.entity.Engine;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.Option;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;
import com.promineotech.jeep.entity.Tire;
import lombok.extern.slf4j.Slf4j;

/**
 * @author angva
 *
 */
@Service
@Slf4j
public class DefaultJeepOrderService implements JeepOrderService { 
  
  @Autowired
  private JeepOrderDao jeepOrderDao;

  @Override
  @Transactional
  public Order createOrder(OrderRequest orderRequest) {
    log.info("Order request in order service layer :: {}", orderRequest);
    Order order = Order.builder()
        .customer(getCustomer(orderRequest))
        .model(getModel(orderRequest))
        .color(getColor(orderRequest))
        .tire(getTire(orderRequest))
        .options(getOption(orderRequest))
        .engine(getEngine(orderRequest))
        .price(getOption(orderRequest).stream().map(Option::getPrice).reduce(BigDecimal.ZERO, BigDecimal:: add))
        .build();

    return jeepOrderDao.saveOrder(order.getCustomer(), order.getModel() , order.getColor(), order.getEngine(), order.getTire(), order.getPrice(), order.getOptions());

  }

  /**
   * 
   * @param orderRequest
   * @return
   */
  private List<Option> getOption(OrderRequest orderRequest) {
    return jeepOrderDao.fetchOptions(orderRequest.getOptions());
  }

  /**
   * 
   * @param orderRequest
   * @return
   */
  private Tire getTire(OrderRequest orderRequest) {
    return jeepOrderDao.fetchTire(orderRequest.getTire())
        .orElseThrow(() -> new NoSuchElementException(
            "Tire with ID=" + orderRequest.getTire() + " was not found"));
  }

  /**
   * 
   * @param orderRequest
   * @return
   */
  private Engine getEngine(OrderRequest orderRequest) {
    return jeepOrderDao.fetchEngine(orderRequest.getEngine())
        .orElseThrow(() -> new NoSuchElementException(
            "Engine with ID=" + orderRequest.getEngine() + " was not found"));
  }

  /**
   * 
   * @param orderRequest
   * @return
   */
  private Color getColor(OrderRequest orderRequest) {
    return jeepOrderDao.fetchColor(orderRequest.getColor())
        .orElseThrow(() -> new NoSuchElementException(
            "Color with ID=" + orderRequest.getColor() + " was not found"));
  }

  /**
   * 
   * @param orderRequest
   * @return
   */
  private Jeep getModel(OrderRequest orderRequest) {
    return jeepOrderDao
        .fetchModel(orderRequest.getModel(), orderRequest.getTrim(),
            orderRequest.getDoors())
        .orElseThrow(() -> new NoSuchElementException("Model with ID="
            + orderRequest.getModel() + ", trim=" + orderRequest.getTrim()
            + orderRequest.getDoors() + " was not found"));
  }

  /**
   * 
   * @param orderRequest
   * @return
   */
  private Customer getCustomer(OrderRequest orderRequest) {
    return jeepOrderDao.fetchCustomer(orderRequest.getCustomer())
        .orElseThrow(() -> new NoSuchElementException("Customer with ID="
            + orderRequest.getCustomer() + " was not found"));
  }

  
}
