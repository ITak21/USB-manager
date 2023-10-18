package com.tak.USB.Manager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usb_table")
public class UsbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usbIndex;

    @Column(name = "usb_nm")
    private String usbName;

    @Column(name = "usb_return")
    private Integer usbReturn;
}
