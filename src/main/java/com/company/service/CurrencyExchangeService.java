package com.company.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.company.model.CurrencyConversion;

@FeignClient(name = "currency-exchange-service", url = "http://localhost:8001/exchange-service")
public interface CurrencyExchangeService {

	@GetMapping(value = "from/{from}/to/{to}")
	public CurrencyConversion getCurrencyExchangeService(@PathVariable(name = "from") String from,
			@PathVariable(name = "to") String to);

}
