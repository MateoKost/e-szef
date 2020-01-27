package Entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Soldier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSoldier;
    @Column
    private String rank;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private int idRoom;

}
