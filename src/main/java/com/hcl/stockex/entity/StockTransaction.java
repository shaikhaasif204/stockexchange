package com.hcl.stockex.entity;

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
@Table(name = "stock_transaction")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class StockTransaction {


@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(name = "user_id", nullable = false)
private User userId;


@Column(name = "stock_id")
private Stock stockId;

@Column(name = "quantaty")
private Long quantaty;

@Column(name = "stock_price")
private Double stockPrice;

@Column(name = "total_price")
private Double totalPrice;

@Column(name = "status")
private Integer status;



}

