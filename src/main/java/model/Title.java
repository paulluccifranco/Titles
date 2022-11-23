package model;

import java.math.BigDecimal;
import java.util.Date;

public class Title {
    private String name;
    private String dni;
    private BigDecimal number;
    private BigDecimal book;
    private BigDecimal invoice;
    private BigDecimal cymat;
    private Date dateCymat;
    private BigDecimal sp1;
    private Date dateSp1;
    private BigDecimal biology;
    private Date dateBiology;
    private BigDecimal fundaments;
    private Date dateFundaments;
    private BigDecimal cares;
    private Date dateCares;
    private BigDecimal practice;
    private Date datePracticeF;
    private BigDecimal average;
    private Date dateAverage;

    public Title() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public BigDecimal getBook() {
        return book;
    }

    public void setBook(BigDecimal book) {
        this.book = book;
    }

    public BigDecimal getInvoice() {
        return invoice;
    }

    public void setInvoice(BigDecimal invoice) {
        this.invoice = invoice;
    }

    public BigDecimal getCymat() {
        return cymat;
    }

    public void setCymat(BigDecimal cymat) {
        this.cymat = cymat;
    }

    public Date getDateCymat() {
        return dateCymat;
    }

    public void setDateCymat(Date dateCymat) {
        this.dateCymat = dateCymat;
    }

    public BigDecimal getSp1() {
        return sp1;
    }

    public void setSp1(BigDecimal sp1) {
        this.sp1 = sp1;
    }

    public Date getDateSp1() {
        return dateSp1;
    }

    public void setDateSp1(Date dateSp1) {
        this.dateSp1 = dateSp1;
    }

    public BigDecimal getBiology() {
        return biology;
    }

    public void setBiology(BigDecimal biology) {
        this.biology = biology;
    }

    public Date getDateBiology() {
        return dateBiology;
    }

    public void setDateBiology(Date dateBiology) {
        this.dateBiology = dateBiology;
    }

    public BigDecimal getFundaments() {
        return fundaments;
    }

    public void setFundaments(BigDecimal fundaments) {
        this.fundaments = fundaments;
    }

    public Date getDateFundaments() {
        return dateFundaments;
    }

    public void setDateFundaments(Date dateFundaments) {
        this.dateFundaments = dateFundaments;
    }

    public BigDecimal getCares() {
        return cares;
    }

    public void setCares(BigDecimal cares) {
        this.cares = cares;
    }

    public Date getDateCares() {
        return dateCares;
    }

    public void setDateCares(Date dateCares) {
        this.dateCares = dateCares;
    }

    public BigDecimal getPractice() {
        return practice;
    }

    public void setPractice(BigDecimal practice) {
        this.practice = practice;
    }

    public Date getDatePracticeF() {
        return datePracticeF;
    }

    public void setDatePracticeF(Date datePracticeF) {
        this.datePracticeF = datePracticeF;
    }

    public BigDecimal getAverage() {
        return average;
    }

    public void setAverage(BigDecimal average) {
        this.average = average;
    }

    public Date getDateAverage() {
        return dateAverage;
    }

    public void setDateAverage(Date dateAverage) {
        this.dateAverage = dateAverage;
    }

    @Override
    public String toString() {
        return "Title{" +
                "name='" + name + '\'' +
                ", dni='" + dni + '\'' +
                ", number='" + number + '\'' +
                ", book='" + book + '\'' +
                ", invoice='" + invoice + '\'' +
                ", cymat=" + cymat +
                ", dateCymat=" + dateCymat +
                ", sp1=" + sp1 +
                ", dateSp1=" + dateSp1 +
                ", biology=" + biology +
                ", dateBiology=" + dateBiology +
                ", fundaments=" + fundaments +
                ", dateFundaments=" + dateFundaments +
                ", cares=" + cares +
                ", dateCares=" + dateCares +
                ", practice=" + practice +
                ", datePracticeF=" + datePracticeF +
                ", average=" + average +
                ", dateAverage=" + dateAverage +
                '}';
    }
}
