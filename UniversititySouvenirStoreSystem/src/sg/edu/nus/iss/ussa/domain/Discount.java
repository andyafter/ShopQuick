package sg.edu.nus.iss.ussa.domain;

import java.util.Date;

public abstract class Discount {

    private String code;
    private String description;
    private Date startDate;
    private int period;
    private double percent;
    private String status;

    public Discount(String discountCode, String discountDescription,
            Date startDate, int period, double percent, String Applicable) {
        this.code = discountCode;
        this.description = discountDescription;
        this.startDate = startDate;
        this.period = period;
        this.percent = percent;
        this.status = Applicable;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   

    public Discount getHigherDiscount(Discount discount) {
        if ((discount == null) || (this.getPercent() > discount.getPercent())) {
            return this;
        } else {
            return discount;
        }
    }

}
