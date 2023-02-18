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

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert //avtomatik defult dəyərləri atmaq üçün istifadə olunur
@Table(name = "customer")
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    //id-nin avtomatik artması üçün istifadə olunur.
    //Müxtəlif növ type-ları var. Oracle və Postgre sequence, MySql isə identity dəstəkləyir.
    @Column(name = "id")
    private Long id;
    private String name;
    private String surname;
    private String address;
    private Date dob;
    private String phone;
    private String cif;
    private String pin;
    private String seria;
    @CreationTimestamp //avtomatik tarixin yazılması
    private Date dataDate;
    @ColumnDefault(value = "1") //defult dəyər qeyd etmək üçün
    private Integer active;

}





