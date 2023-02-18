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
@DynamicInsert
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String receiptNo;
    @ManyToOne
    @JoinColumn(name = "dt_account_id")
    private Account dtAccount; //debtor --> gonderen
    private String crAccount; //creditor --> alan
    private Integer amount;
    @CreationTimestamp
    private Date trDate;
    @ColumnDefault(value = "1")
    private Integer active;
}
