package entities.billspayment;

import javax.persistence.*;

@Entity
@Table(name = "billing_details")
@Inheritance(strategy = InheritanceType.JOINED)
public class BillingDetail extends BaseEntity{
    private BankUser bankUser;
    private String number;

    public BillingDetail(){}

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public BankUser getUser() {
        return bankUser;
    }

    public void setUser(BankUser bankUser) {
        this.bankUser = bankUser;
    }
    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
