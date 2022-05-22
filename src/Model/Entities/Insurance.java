package Model.Entities;

import java.sql.Date;

public class Insurance {
    private Integer id;
    private Integer docNumber;
    private String  paymentCode;
    private String  jobVerificationCode;
    private Date    date;
    private CustomerType  customerType;
    private Service service;
    private Company  company;
    private Integer userId;

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

    public CustomerType getCustomerType() {
        return customerType;
    }

    public Insurance setCustomerType(CustomerType customerType) {
        customerType = customerType;
        return this;
    }

    public Service getService() {
        return service;
    }

    public Insurance setService(Service service) {
        this.service = service;
        return this;
    }

    public Company getCompany() {
        return company;
    }

    public Insurance setCompany(Company  company) {
        this.company = company;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public Insurance setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
}
