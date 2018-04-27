package com.zl.property.model.hib.property;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 楼栋
 */
@Entity
@Table(name = "p_building")
public class Building {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "building_id", unique = true, nullable = false)
    private String buildingId;

    @Column(name = "microdistrict_id")
    private String microdistrictId;
    @Column(name = "building_name")
    private String buildingName;
    @Column(name = "building_manager")
    private String buildingManager;

    public String getMicrodistrictId() {
        return microdistrictId;
    }

    public void setMicrodistrictId(String microdistrictId) {
        this.microdistrictId = microdistrictId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingManager() {
        return buildingManager;
    }

    public void setBuildingManager(String buildingManager) {
        this.buildingManager = buildingManager;
    }
}
