package com.company.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.company.model.CurrencyConversion;
import com.company.service.CurrencyExchangeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("currency")
@Slf4j
public class CurrencyConversionController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CurrencyExchangeService currencyExchangeService;

	@GetMapping("from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion getCurrencyConversion(@PathVariable(name = "from") String from,
			@PathVariable(name = "to") String to, @PathVariable(name = "quantity") Integer quantity) {
		log.info("Inside currency conversion controller");
		Map<String, String> parameters = new HashMap<>();
		parameters.put("from", from);
		parameters.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity(
				"http://localhost:8001/exchange-service/from/{from}/to/{to}", CurrencyConversion.class, parameters);
		CurrencyConversion currencyConversion = responseEntity.getBody();
		currencyConversion.setQuantity(quantity);
		currencyConversion
				.setCalculatedAmount(currencyConversion.getConversionMulitple().multiply(BigDecimal.valueOf(quantity)));
		return currencyConversion;
	}

	@GetMapping("feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion getCurrencyConversionFeign(@PathVariable(name = "from") String from,
			@PathVariable(name = "to") String to, @PathVariable(name = "quantity") Integer quantity) {
		log.info("Inside currency conversion controller");
		Map<String, String> parameters = new HashMap<>();
		parameters.put("from", from);
		parameters.put("to", to);

		CurrencyConversion currencyConversion = currencyExchangeService.getCurrencyExchangeService(from, to);
		currencyConversion.setQuantity(quantity);
		currencyConversion
				.setCalculatedAmount(currencyConversion.getConversionMulitple().multiply(BigDecimal.valueOf(quantity)));
		return currencyConversion;
	}

}
