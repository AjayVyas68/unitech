package com.unitechApi.Payload.response;

import java.util.Date;
import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String name;
    private String email;
    // private String email;
    private String phoneno;
    private String address;
    //	private int pin;
    private int status;
    private Date datetime;


    private final List<String> roles;

    public JwtResponse(String accessToken, Long id, String useremail, String username,
                       String phoneno, String address, int status, Date datetime,
                       List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.email = useremail;
        this.name = username;
        this.phoneno = phoneno;
        this.address = address;
        this.status = status;
//		this.pin =pin;
        this.datetime = datetime;

        this.roles = roles;
    }


    public String getPhoneno() {
        return phoneno;
    }


    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public int getStatus() {
        return status;
    }


    public void setStatus(int status) {
        this.status = status;
    }


    public Date getDatetime() {
        return datetime;
    }


    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }


//	public int getPin() {
//		return pin;
//	}
//
//
//	public void setPin(int pin) {
//		this.pin = pin;
//	}

}
