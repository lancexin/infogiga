package cn.infogiga.ds.pojo;

import java.sql.Clob;



/**
 * SmsConfig entity. @author MyEclipse Persistence Tools
 */

public class SmsConfig implements java.io.Serializable,cindy.web.pojo.POJO {


    // Fields    

     private Integer smsConfigId;
     private String code;
     private String context;
     private String phoneNumber;
     private String context2;


    // Constructors

    /** default constructor */
    public SmsConfig() {
    }
    
    public SmsConfig(Integer smsConfigId) {
		this.smsConfigId = smsConfigId;
	}

    
    /** full constructor */
    public SmsConfig(String code, String context, String phoneNumber, String context2) {
        this.code = code;
        this.context = context;
        this.phoneNumber = phoneNumber;
        this.context2 = context2;
    }

   
    // Property accessors

    public Integer getSmsConfigId() {
        return this.smsConfigId;
    }
    
    public void setSmsConfigId(Integer smsConfigId) {
        this.smsConfigId = smsConfigId;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getContext() {
        return this.context;
    }
    
    public void setContext(String context) {
        this.context = context;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContext2() {
        return this.context2;
    }
    
    public void setContext2(String context2) {
        this.context2 = context2;
    }
   








}