package com.icn.content.text;

import com.icn.content.user.UserEntity;

import javax.persistence.*;
import java.util.Date;

@SequenceGenerator(sequenceName = "CONTENTSEQUENCE", name="CONTENTSEQUENCE",initialValue = 1,allocationSize = 1)
@Entity(name="content")
public class ContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "CONTENTSEQUENCE")
    @Column(name="id")
    private Integer contentId;
    @Column(name="title",nullable = false)
    private String title;
    @Column(name="maintext",nullable = false)
    private String maintext;
    @Column(name="datetime",nullable = false)
    private Date datetime;
    @Column(name="writer",nullable = false)
    private String writer;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID",nullable = false)
    private UserEntity userEntity;

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaintext() {
        return maintext;
    }

    public void setMaintext(String maintext) {
        this.maintext = maintext;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
