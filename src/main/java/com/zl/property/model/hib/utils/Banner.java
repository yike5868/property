package com.zl.property.model.hib.utils;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;

@Configuration
@Entity
@Table(name = "p_dictionaries")
public class Banner {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "banner_id", unique = true, nullable = false)
    private String bannerId;
    @Column(name = "banner_message")
    private String bannerMessage;
    @Column(name = "banner_url")
    private String bannerUrl;
    /**
     * 需要跳转到那个页面
     */
    @Column(name = "banner_jump_url")
    private String bannerJumpUrl;

    @Column(name = "version")
    private String version;

    public String getBannerJumpUrl() {
        return bannerJumpUrl;
    }

    public void setBannerJumpUrl(String bannerJumpUrl) {
        this.bannerJumpUrl = bannerJumpUrl;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerMessage() {
        return bannerMessage;
    }

    public void setBannerMessage(String bannerMessage) {
        this.bannerMessage = bannerMessage;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
