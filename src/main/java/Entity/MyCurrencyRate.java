package Entity;

import javax.persistence.*;

@Entity
@Table(name = "CurrencyRate")
public class MyCurrencyRate {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private double UAHRate;
    @Column
    private double USDRate;
    @Column
    private double EURRate;

    public MyCurrencyRate() {
    }

    public MyCurrencyRate(double UAHRate, double USDRate, double EURRate) {
        this.UAHRate = UAHRate;
        this.USDRate = USDRate;
        this.EURRate = EURRate;
    }

    public double getUAHRate() {
        return UAHRate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUAHRate(double UAHRate) {
        this.UAHRate = UAHRate;
    }

    public double getUSDRate() {
        return USDRate;
    }

    public void setUSDRate(double USDRate) {
        this.USDRate = USDRate;
    }

    public double getEURRate() {
        return EURRate;
    }

    public void setEURRate(double EURRate) {
        this.EURRate = EURRate;
    }
}
