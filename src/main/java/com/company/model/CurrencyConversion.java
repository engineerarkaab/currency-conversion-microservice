package com.company.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CurrencyConversion extends ExchangeValue implements Serializable {
	private static final long serialVersionUID = -6726270902671597422L;
	private Integer quantity;
	private BigDecimal calculatedAmount;

}
