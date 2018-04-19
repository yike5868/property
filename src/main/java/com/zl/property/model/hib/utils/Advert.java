package com.zl.property.model.hib.utils;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;

@Configuration
@Entity
@Table(name = "p_advert")
public class Advert {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "id", unique = true, nullable = false)
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "html_message")
    private String htmlMessage;
    @Column(name = "user_Id")
    private String userId;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "version")
    private String verison;
    @Column(name = "enable")
    private String enable;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHtmlMessage() {
        return htmlMessage;
    }

    public void setHtmlMessage(String htmlMessage) {
        this.htmlMessage = htmlMessage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
