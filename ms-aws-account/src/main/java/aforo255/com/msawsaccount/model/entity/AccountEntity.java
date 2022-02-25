package aforo255.com.msawsaccount.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="account")
public class AccountEntity  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdAccount ;
    @Column(name="total_amount")
    private double TotalAmount ;
    @Column(name="id_customer")
    private Integer IdCustomer ;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_customer" , insertable =false , updatable = false)
    @Fetch(FetchMode.JOIN)
    private CustomerEntity customer ;
}
