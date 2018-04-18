package com.zl.property.model.hib.property;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 小区
 */
@Entity
@Table(name = "p_microdistrict")
public class Microdistrict {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "microdistrict_id", unique = true, nullable = false)
    private String microdistrictId;
    @Column(name = "microdistrict_name")
    private String microdistrictName;
    @Column(name = "microdistrict_manager")
    private String microdistrictManager;//负责人

    public String getMicrodistrictId() {
        return microdistrictId;
    }

    public void setMicrodistrictId(String microdistrictId) {
        this.microdistrictId = microdistrictId;
    }

    public String getMicrodistrictName() {
        return microdistrictName;
    }

    public void setMicrodistrictName(String microdistrictName) {
        this.microdistrictName = microdistrictName;
    }

    public String getMicrodistrictManager() {
        return microdistrictManager;
    }

    public void setMicrodistrictManager(String microdistrictManager) {
        this.microdistrictManager = microdistrictManager;
    }
}
