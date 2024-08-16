package com.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;
	private String consumerName;
    private Date billDueDate;
    private BigDecimal billAmount;
    
    public Bill() {}
    public Bill(String consumerName, Date billDueDate, BigDecimal billAmount) {
        this.consumerName = consumerName;
        this.billDueDate = billDueDate;
        this.billAmount = billAmount;
    }
    public String getConsumerName() { return consumerName; }
    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public Date getBillDueDate() { return billDueDate; }
    public void setBillDueDate(Date billDueDate) {
        this.billDueDate = billDueDate;
    }

    public BigDecimal getBillAmount() {return billAmount; }
    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "consumerName='" + consumerName + '\'' +
                ", billDueDate=" + billDueDate +
                ", billAmount=" + billAmount +
                '}';
    }
}
