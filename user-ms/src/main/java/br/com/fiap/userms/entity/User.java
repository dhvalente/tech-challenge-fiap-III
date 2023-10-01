package br.com.fiap.userms.entity;

import br.com.fiap.userms.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(name = "tb_users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    private String name;

    private String lastName;

    private LocalDate birthDate;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDateTime registrationDate;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Address address;

    @PrePersist
    private void setRegistrationDate(){
        setRegistrationDate(LocalDateTime.now());
    }

}