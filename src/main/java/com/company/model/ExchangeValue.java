package com.company.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeValue implements Serializable {
	private static final long serialVersionUID = -4468515565436996277L;
	private Long id;
	private String from;
	private String to;
	private BigDecimal conversionMulitple;
	private transient Integer port;
}
