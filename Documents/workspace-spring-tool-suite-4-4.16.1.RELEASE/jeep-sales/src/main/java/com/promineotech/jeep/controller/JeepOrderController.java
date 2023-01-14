/**
 * 
 */
package com.promineotech.jeep.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;

/**
 * @author angva
 *
 */
@RequestMapping("/orders") 
@Validated
public interface JeepOrderController {
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Order createOrder(@RequestBody OrderRequest orderRequest);

}