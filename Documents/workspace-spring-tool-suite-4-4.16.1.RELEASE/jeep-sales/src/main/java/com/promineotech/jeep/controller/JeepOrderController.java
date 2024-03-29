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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * @author angva
 *
 */
@RequestMapping("/orders") 
@Validated
public interface JeepOrderController {
  //@formatter:off
  @Operation(
          summary = "Create an order for a Jeep",
          description = "Returns the created Jeep",
          responses = {
             @ApiResponse(responseCode = "201",
                 description = "The created Jeep is returned",
                 content = @Content(
                     mediaType = "application/json",
                     schema = @Schema(implementation = Order.class))),
             @ApiResponse(responseCode = "400",
                 description = "The request parameters are invalid",
                 content = @Content(mediaType = "application/json")),
             @ApiResponse(responseCode = "404",
                 description = "No jeeps were found with the input criteria",
                 content = @Content(mediaType = "application/json")),
             @ApiResponse(responseCode = "500",
                 description = "An unplanned error occurred.",
                 content = @Content(mediaType = "application/json"))
          },
          parameters = {
              @Parameter(name = "orderRequest", 
                  required = true, description = "The order as JSON")
          }  
  )
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Order createOrder(@RequestBody OrderRequest orderRequest);
  //@formatter:on
}
