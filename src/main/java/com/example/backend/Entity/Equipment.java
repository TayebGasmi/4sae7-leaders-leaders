package com.example.backend.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Equipment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Long idEquipment;

    String name;

    String description;

    Date dateManufacture;

    Date dateEndUsefullLife;

    int quantity;

    boolean favorite;

    @OneToOne(mappedBy = "equipment")
    Offer offer;


}
