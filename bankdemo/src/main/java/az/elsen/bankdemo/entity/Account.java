package az.elsen.bankdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "account")
@DynamicInsert
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private String accountNo;
    private String iban;
    private Integer amount;
    private String currency;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @CreationTimestamp //avtomatik tarixin yazılması
    private Date dataDate;
    @ColumnDefault(value = "1") //defult dəyər qeyd etmək üçün
    private Integer active;

}
