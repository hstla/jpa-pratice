package Practical.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date oderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getOderDate() {
        return oderDate;
    }

    public void setOderDate(Date oderDate) {
        this.oderDate = oderDate;
    }
}
