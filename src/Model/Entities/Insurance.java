package Model.Entities;

import java.sql.Date;

public class Insurance {
    private Integer id;
    private Integer docNumber;
    private String  paymentCode;
    private String  jobVerificationCode;
    private Date    date;
    private String  CustomerType;
    private Service service;
    private String  company;

    public Insurance() {}

    public Integer getId() {
        return id;
    }

    public Insurance setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getDocNumber() {
        return docNumber;
    }

    public Insurance setDocNumber(Integer docNumber) {
        this.docNumber = docNumber;
        return this;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public Insurance setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
        return this;
    }

    public String getJobVerificationCode() {
        return jobVerificationCode;
    }

    public Insurance setJobVerificationCode(String jobVerificationCode) {
        this.jobVerificationCode = jobVerificationCode;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Insurance setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getCustomerType() {
        return CustomerType;
    }

    public Insurance setCustomerType(String customerType) {
        CustomerType = customerType;
        return this;
    }

    public Service getService() {
        return service;
    }

    public Insurance setService(Service service) {
        this.service = service;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public Insurance setCompany(String company) {
        this.company = company;
        return this;
    }
}
