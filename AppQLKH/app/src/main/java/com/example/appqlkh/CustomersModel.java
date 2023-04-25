package com.example.appqlkh;

public class CustomersModel {
    private String CusName;
    private String CusAddress;
    private String CusPhone;
    private String CusEmail;
    private String id;

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String cusName) {
        CusName = cusName;
    }

    public String getCusAddress() {
        return CusAddress;
    }

    public void setCusAddress(String cusAddress) {
        CusAddress = cusAddress;
    }

    public String getCusPhone() {
        return CusPhone;
    }

    public void setCusPhone(String cusPhone) {
        CusPhone = cusPhone;
    }

    public String getCusEmail() {
        return CusEmail;
    }

    public void setCusEmail(String cusEmail) {
        CusEmail = cusEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public CustomersModel(String id,String cusName, String cusAddress, String cusPhone, String cusEmail) {
        this.id = id;
        CusName = cusName;
        CusAddress = cusAddress;
        CusPhone = cusPhone;
        CusEmail = cusEmail;
    }
}
