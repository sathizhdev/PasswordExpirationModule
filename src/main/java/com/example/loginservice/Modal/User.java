package com.example.loginservice.Modal;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    private static final long PASSWORD_EXPIRATION_TIME
            = 90;

    @Id
    @Column
    @NotNull
    private String userName;

    @Column
    private String password;


    @Column(name = "password_changed_time")
    //Current Date
    private LocalDateTime passwordChangedTime;



    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Override
    public String toString() {
        return "users{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", passwordChangedTime=" + passwordChangedTime +
                ", role=" + role +
                '}';
    }

    public User() {}

    public User(User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.passwordChangedTime = user.getPasswordChangedTime();
        this.role = user.getRole();
    }


    public boolean isPasswordExpired() {
        if (this.passwordChangedTime == null) return false;

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime changedTime = this.passwordChangedTime;

        long daysBetween = ChronoUnit.DAYS.between(changedTime,currentTime);

        if(daysBetween >= 90)
        {
            return true;
        }
        return false;
    }

}
