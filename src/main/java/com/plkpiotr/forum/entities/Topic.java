package com.plkpiotr.forum.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Topic {

    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    public Topic(Map<String, Object> data) {
        if (data == null)
            _data = new HashMap<String, Object>();
        else
            _data = data;
    }

    private Map<String, Object> _data;

    @Column(length = 1024)
    private String code;

    public void setRealId(String id) {
        _data.put("id", id);
    }

    public String getId() {
        if (_data.containsKey("id"))
            return _data.get("id").toString();
        return null;
    }

    public String getTitle() {
        if (_data.containsKey("title"))
            return _data.get("title").toString();
        return null;
    }

    public void setTitle(String title) {
        _data.put("title", title);
    }

    public String getContent() { if (_data.containsKey("content")) return _data.get("content").toString(); return null; }

    public void setContent(String content) {
        _data.put("content", content);
    }

    public String getCategory() { if (_data.containsKey("category")) return _data.get("category").toString(); return null;  }

    public void setCategory(String category) {
        _data.put("category", category);
    }

    public LocalDateTime getCreatedDate() { if (_data.containsKey("createdDate")) return LocalDateTime.parse(_data.get("createdDate").toString(), formatter); return null; }

    public void setCreatedDate(LocalDateTime createdDate) { _data.put("createdDate", createdDate.toString()); }

    public Optional getCode() {
        return Optional.ofNullable(code);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() { if (_data.containsKey("userid")) return _data.get("userid").toString(); return null; }

    public void setUserId(User userid) {
        _data.put("userid", userid);
    }

    public String displayParsedCreatedDate() { if (_data.containsKey("createdDate")) return _data.get("createdDate").toString(); return null; }

    public String displayCode() {
        if (Optional.ofNullable(code).isPresent())
            return Optional.ofNullable(code).get();
        else
            return "";
    }
}
