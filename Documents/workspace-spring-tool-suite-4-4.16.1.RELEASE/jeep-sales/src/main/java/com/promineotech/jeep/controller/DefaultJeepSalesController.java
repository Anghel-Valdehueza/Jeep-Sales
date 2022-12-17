package com.promineotech.jeep.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.service.JeepSalesService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;

@RestController
@OpenAPIDefinition(info = @Info(title = "Jeep Sales Service"), servers = {
        @Server(url = "http://localhost:8080", description = "Local server.")
})
@Slf4j
public class DefaultJeepSalesController implements JeepSalesController {
    @Autowired
    private JeepSalesService jeepSalesService;

    @Operation(
            summary = "Returns a list of Jeeps",
            description = "Returns a list of Jeeps given an optional model and/or trim",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "A list of Jeeps is returned",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Jeep.class))),
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
                @Parameter(name = "model", allowEmptyValue = false, description = "The model name (i.e., 'WRANGLER')"),
                @Parameter(name = "trim", allowEmptyValue = false)
            }
    )
    @Override
    public List<Jeep> fetchJeeps(JeepModel model, String trim) {
        // TODO Auto-generated method stub
        return null;
    }

}

