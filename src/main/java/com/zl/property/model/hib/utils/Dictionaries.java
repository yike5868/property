package com.zl.property.model.hib.utils;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;

@Configuration
@Entity
@Table(name = "p_dictionaries")
public class Dictionaries {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "dictionaries_id", unique = true, nullable = false)
    private String dictionariesId;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "type")
    private String type;

    public String getDictionariesId() {
        return dictionariesId;
    }

    public void setDictionariesId(String dictionariesId) {
        this.dictionariesId = dictionariesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
