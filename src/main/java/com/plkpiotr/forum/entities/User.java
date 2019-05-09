package com.plkpiotr.forum.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class User implements UserDetails {

    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    public User(Map<String, Object> data) {
        if (data == null)
            _data = new HashMap<String, Object>();
        else
            _data = data;
    }


    private LocalDateTime createdDate;

    @Transient
    private Map<String, Object> _data;

    public void setRealId(String id) {
        _data.put("id", id);
    }

    public String getId() {
        return _data.get("id").toString();
    }


    @Override
    public String getUsername() {
        if (_data.containsKey("username"))
            return _data.get("username").toString();
        return null;
    }

    public void setUsername(String username) {
        _data.put("username", username);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }

    public String getPassword() {
        if (_data.containsKey("password"))
            return _data.get("password").toString();
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        _data.put("password", password);
    }

    public String getIntroduction() { if (_data.containsKey("introduction")) return _data.get("introduction").toString();return null; }

    public void setIntroduction(String introduction) {
        _data.put("introduction", introduction);
    }

    public LocalDateTime getCreatedDate() { if (_data.containsKey("createdDate")) return LocalDateTime.parse(_data.get("createdDate").toString(), formatter); return null; }

    public void setCreatedDate(LocalDateTime createdDate) { _data.put("createdDate", createdDate.toString()); }

    public String displayParsedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return this.createdDate.format(formatter);
    }
}