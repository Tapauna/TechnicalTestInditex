package com.inditex.technicaltest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import org.openapitools.model.RateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TechnicaltestApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void integrationTest1() {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("/getRates")
				.queryParam("dateOfApplication", "2020-06-14T10:00:00Z")
				.queryParam("productId", 35455)
				.queryParam("chainId", 1);
		RateResponseDTO responseDTO=new RateResponseDTO();
		responseDTO.setBrandId(1);
		responseDTO.setProductId(35455);
		responseDTO.setStartDate(OffsetDateTime.parse("2020-06-14T00:00:00Z"));
		responseDTO.setEndDate(OffsetDateTime.parse("2020-12-31T23:59:59Z"));
		responseDTO.setRateId(1);
		responseDTO.setPrice(new BigDecimal("35.50"));
		ResponseEntity<RateResponseDTO> response=restTemplate.getForEntity(uriBuilder.toUriString(),RateResponseDTO.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(responseDTO,response.getBody());
	}

	@Test
	void integrationTest2() {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("/getRates")
				.queryParam("dateOfApplication", "2020-06-14T16:00:00Z")
				.queryParam("productId", 35455)
				.queryParam("chainId", 1);
		RateResponseDTO responseDTO=new RateResponseDTO();
		responseDTO.setBrandId(1);
		responseDTO.setProductId(35455);
		responseDTO.setStartDate(OffsetDateTime.parse("2020-06-14T15:00:00Z"));
		responseDTO.setEndDate(OffsetDateTime.parse("2020-06-14T18:30:00Z"));
		responseDTO.setRateId(2);
		responseDTO.setPrice(new BigDecimal("25.45"));
		ResponseEntity<RateResponseDTO> response=restTemplate.getForEntity(uriBuilder.toUriString(),RateResponseDTO.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(responseDTO,response.getBody());
	}

	@Test
	void integrationTest3() {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("/getRates")
				.queryParam("dateOfApplication", "2020-06-14T21:00:00Z")
				.queryParam("productId", 35455)
				.queryParam("chainId", 1);
		RateResponseDTO responseDTO=new RateResponseDTO();
		responseDTO.setBrandId(1);
		responseDTO.setProductId(35455);
		responseDTO.setStartDate(OffsetDateTime.parse("2020-06-14T00:00:00Z"));
		responseDTO.setEndDate(OffsetDateTime.parse("2020-12-31T23:59:59Z"));
		responseDTO.setRateId(1);
		responseDTO.setPrice(new BigDecimal("35.50"));
		ResponseEntity<RateResponseDTO> response=restTemplate.getForEntity(uriBuilder.toUriString(),RateResponseDTO.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(responseDTO,response.getBody());
	}

	@Test
	void integrationTest4() {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("/getRates")
				.queryParam("dateOfApplication", "2020-06-15T10:00:00Z")
				.queryParam("productId", 35455)
				.queryParam("chainId", 1);
		RateResponseDTO responseDTO=new RateResponseDTO();
		responseDTO.setBrandId(1);
		responseDTO.setProductId(35455);
		responseDTO.setStartDate(OffsetDateTime.parse("2020-06-15T00:00:00Z"));
		responseDTO.setEndDate(OffsetDateTime.parse("2020-06-15T11:00:00Z"));
		responseDTO.setRateId(3);
		responseDTO.setPrice(new BigDecimal("30.50"));
		ResponseEntity<RateResponseDTO> response=restTemplate.getForEntity(uriBuilder.toUriString(),RateResponseDTO.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(responseDTO,response.getBody());
	}

	@Test
	void integrationTest5() {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("/getRates")
				.queryParam("dateOfApplication", "2020-06-16T21:00:00Z")
				.queryParam("productId", 35455)
				.queryParam("chainId", 1);
		RateResponseDTO responseDTO=new RateResponseDTO();
		responseDTO.setBrandId(1);
		responseDTO.setProductId(35455);
		responseDTO.setStartDate(OffsetDateTime.parse("2020-06-15T16:00:00Z"));
		responseDTO.setEndDate(OffsetDateTime.parse("2020-12-31T23:59:59Z"));
		responseDTO.setRateId(4);
		responseDTO.setPrice(new BigDecimal("38.95"));
		ResponseEntity<RateResponseDTO> response=restTemplate.getForEntity(uriBuilder.toUriString(),RateResponseDTO.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(responseDTO,response.getBody());
	}

}
