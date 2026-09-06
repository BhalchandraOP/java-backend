package com.bhalchandra.eventProcessor.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="wallets")
public class WEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private UUID userId;
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// Defining UserId as UNiversal uid to be created unique every time 
	public UUID getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = this.userId;
	}
public WEntity(Long id, UUID userId, BigDecimal balance) {
		super();
		this.id = id;
		this.userId = userId;
		this.balance = balance;
	}

	//Using BigDecimal just avoid float
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal newBalance) {
		this.balance = this.balance;
	}

	@Column(nullable = false)
	private BigDecimal balance;
	
	
}
