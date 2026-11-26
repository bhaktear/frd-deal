package abl.frd.model;
import java.math.BigDecimal;
import java.time.*;
import javax.persistence.*;

@Entity
@Table(name="daily_remittance_deals", uniqueConstraints = { @UniqueConstraint(name = "uk_deal_no", columnNames = "deal_no")},
    indexes = { }
)
public class DailyRemittanceDeal {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int  id;
    @Column(name = "deal_no", nullable = false, unique = true, length = 30)
    private String dealNo;
    @Column(name = "nostro_ac", nullable = false, unique = true, length = 30)
    private String nostroAc;
    @Column(name = "deal_date", nullable = false)
    private LocalDate dealDate;
    @Column(name = "exchange_code", nullable = false, length = 20)
    private String exchangeCode;
    @Column(name = "currency_code", nullable = false, length = 10)
    private String currencyCode;
    @Column(name="offer_rate", nullable = false, precision = 10, scale = 4)
    private BigDecimal offerRate;
    @Column(name="asked_rate", nullable = false, precision = 10, scale = 4)
    private BigDecimal askedRate;
    @Column(name="agreed_rate", nullable = false, precision = 10, scale = 4)
    private BigDecimal agreedRate;
    @Column(name = "is_agreed", columnDefinition = "TINYINT(1) DEFAULT 0")
    private int isAgreed = 0;
    @Column(name = "fc_amount")
    private Double fcAmount;
    @Column(name = "bdt_amount")
    private Double bdtAmount;
    private int userId;
    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;
    @Column(name = "updated_on", nullable = false)
    private LocalDateTime updatedOn;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDealNo() {
        return this.dealNo;
    }

    public void setDealNo(String dealNo) {
        this.dealNo = dealNo;
    }

    public LocalDate getDealDate() {
        return this.dealDate;
    }

    public void setDealDate(LocalDate dealDate) {
        this.dealDate = dealDate;
    }

    public String getExchangeCode() {
        return this.exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getOfferRate() {
        return this.offerRate;
    }

    public void setOfferRate(BigDecimal offerRate) {
        this.offerRate = offerRate;
    }

    public BigDecimal getAskedRate() {
        return this.askedRate;
    }

    public void setAskedRate(BigDecimal askedRate) {
        this.askedRate = askedRate;
    }

    public BigDecimal getAgreedRate() {
        return this.agreedRate;
    }

    public void setAgreedRate(BigDecimal agreedRate) {
        this.agreedRate = agreedRate;
    }

    public int getIsAgreed() {
        return this.isAgreed;
    }

    public void setIsAgreed(int isAgreed) {
        this.isAgreed = isAgreed;
    }

    public Double getFcAmount() {
        return this.fcAmount;
    }

    public void setFcAmount(Double fcAmount) {
        this.fcAmount = fcAmount;
    }

    public Double getBdtAmount() {
        return this.bdtAmount;
    }

    public void setBdtAmount(Double bdtAmount) {
        this.bdtAmount = bdtAmount;
    }


    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }




}
