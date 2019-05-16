package com.cougartalk.forum.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Topic {

    public Topic(Map<String, Object> data) {
        if (data == null)
            _data = new HashMap<String, Object>();
        else
            _data = data;
    }

    private Map<String, Object> _data;

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

    public String getCreatedDate() {

    if (_data.containsKey("createdDate")) return _data.get("createdDate").toString(); return null; }

    public void setCreatedDate(String createdDate) { _data.put("createdDate", createdDate); }

    public String getUserId() { if (_data.containsKey("userid")) return _data.get("userid").toString(); return null; }

    public void setUserId(String userid) {
        _data.put("userid", userid);
    }

    public String getUsername() { if (_data.containsKey("username")) return _data.get("username").toString(); return null; }

    public void setUsername(String userid) {
        _data.put("username", userid);
    }

    public String displayParsedCreatedDate() { if (_data.containsKey("createdDate")) return _data.get("createdDate").toString(); return null; }

}
