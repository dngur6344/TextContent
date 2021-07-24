package com.icn.content.user;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="T_USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCENAME") //oracle의 경우 이런식으로 기본키 할당을 해줘야함.
    @SequenceGenerator(sequenceName = "SEQUENCENAME", name = "SEQUENCENAME", allocationSize = 1)
    private Integer id;

    @Column(name = "user_name", length = 20, unique = true, nullable = false)
    private String username;
    @Column(length = 400, nullable = false)
    private String password;
    @Column(name = "user_type", nullable = false)
    private int userType;
    /*@Column(nullable = false)
    private Date date;*/
    @Column(name="korean_name", nullable = false)
    private String koreanname;
    @Column(name="birthday",nullable = false)
    private String birthday;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getKoreanname() {
        return koreanname;
    }

    public void setKoreanname(String koreanname) {
        this.koreanname = koreanname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}