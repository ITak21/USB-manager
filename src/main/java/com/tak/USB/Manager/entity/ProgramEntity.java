package com.tak.USB.Manager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "program_table")
public class ProgramEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pg_index")
    private int pgIndex;



    @Column(name = "pg_nm")
    private String pgName;


}
