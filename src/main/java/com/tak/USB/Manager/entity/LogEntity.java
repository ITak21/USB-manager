package com.tak.USB.Manager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@Table(name = "log_table")
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logIndex;

    @Column(name = "usb_index")
    private int uIndex;

    @Column(name = "pg_index")
    private int pIndex;

    @Column(name = "rent_nm")
    private String  rtName;

    @Column(name = "rent_gp")
    private String  rtGroup;

    @CreationTimestamp
    @Column(name = "rent_date")
    private java.sql.Date rtDate;

    @UpdateTimestamp
    @Column(name = "return_date")
    private java.sql.Date returnDate;

}
