package com.plkpiotr.forum.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Answer {

    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    public Answer(Map<String, Object> data) {
        if (data == null)
            _data = new HashMap<String, Object>();
        else
            _data = data;
    }

    private Map<String, Object> _data;

    public String getId() { if (_data.containsKey("id")) return _data.get("id").toString(); return null; }

    public void setRealId(String id) {
        _data.put("id", id);
    }

    public String getContent() { if (_data.containsKey("content")) return _data.get("content").toString(); return null; }

    public void setContent(String content) {
        _data.put("content", content);
    }

    public boolean isUseful() {
        if (_data.containsKey("useful")) return true; return false; }

    public void setUseful(boolean useful) {
        _data.put("useful", useful);
    }

    public LocalDateTime getCreatedDate() { if (_data.containsKey("createdDate")) return LocalDateTime.parse(_data.get("createdDate").toString(), formatter); return null; }

    public void setCreatedDate(LocalDateTime createdDate) { _data.put("createdDate", createdDate.toString()); }

/*    public Optional getCode() {
        return Optional.ofNullable(code);
    }

    public void setCode(String code) {
        this.code = code;
    } */

    public String getUserId() { if (_data.containsKey("userid")) return _data.get("userid").toString(); return null; }

    public void setUserId(String userid) {
        _data.put("userid", userid);
    }

    public String getTopicId() { if (_data.containsKey("topicid")) return _data.get("topicid").toString(); return null; }

    public void setTopicId(String topicId) { _data.put("topicid", topicId); }

    public String displayParsedCreatedDate() { if (_data.containsKey("createdDate")) return _data.get("createdDate").toString(); return null; }

/*    public String displayCode() {
        if (Optional.ofNullable(code).isPresent())
            return Optional.ofNullable(code).get();
        else
            return "";
    } */

    public String displayBeginning() {
        String content = getContent();
        return (content.length() < 32) ? content.concat("...") : content.substring(0, 30).concat("...");
    }
}
