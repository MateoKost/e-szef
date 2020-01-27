package Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    @Column
    private String email;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private int kompania;
    @Column
    private String password;
}
