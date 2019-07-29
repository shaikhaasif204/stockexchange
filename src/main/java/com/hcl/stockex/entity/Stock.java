package com.hcl.stockex.entity;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "stock")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "Id")
public class Stock {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(name = "stock_name")
	private String stockName;

	@Column(name = "stock_type")
	private String stockType;

	@Column(name = "region")
	private String region;

	@Column(name = "market_open")
	private LocalTime marketOpen;

	@Column(name = "market_close")
	private LocalTime marketClose;

	@Column(name = "purchase_price")
	private Double purchasePrice;

	@Column(name = "confirm_price")
	private Double confirmPrice;

	@Column(name = "execute_price")
	private Double executePrice;

}
