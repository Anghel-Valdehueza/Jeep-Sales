/**
 * 
 */
package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

/**
 * @author angva
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
    "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, 
    config = @SqlConfig(encoding = "utf-8"))


class FetchJeepTest {
    @Autowired
    private TestRestTemplate restTemplate;
      
    @LocalServerPort
    private int serverPort;
    List<Jeep> buildExpected(JeepModel model, String trim) {
      List<Jeep> jeeps = new ArrayList<>();
      jeeps.add(Jeep.builder()
                      .modelPK(1L)
                      .modelId(JeepModel.WRANGLER)
                      .trimLevel("Sport")
                      .numDoors(2)
                      .wheelSize(17)
                      .basePrice(BigDecimal.valueOf(28475))
              .build());

      jeeps.add(Jeep.builder()
                      .modelPK(2L)
              .modelId(JeepModel.WRANGLER)
              .trimLevel("Sport")
              .numDoors(4)
              .wheelSize(17)
              .basePrice(BigDecimal.valueOf(31975))
              .build());

      Collections.sort(jeeps);
      return jeeps;
  }

    @Test
    void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
        //Given: valid model, trim and URI
        JeepModel model = JeepModel.WRANGLER;
        String trim = "Sport";
        String uri = String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim);
        
        //When: a connection is made to the URI
        ResponseEntity<List<Jeep>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        
        //Then: a success (OK - 200) status code is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Jeep> actual = response.getBody();
        List<Jeep> expected = buildExpected(model, trim);
        
        assertThat(actual).isEqualTo(expected);

    }

}