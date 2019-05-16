package com.cougartalk.forum.entities;

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

    public Answer(Map<String, Object> data) {
        if (data == null)
            _data = new HashMap<String, Object>();
        else
            _data = data;
    }

    private Map<String, Object> _data;

    /**
     * Returns the Id, if null returns null
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String getId() { if (_data.containsKey("id")) return _data.get("id").toString(); return null; }

    /**
     * Sets the Id.
     *
     * @param id : the desired id.
     */
    public void setRealId(String id) {
        _data.put("id", id);
    }

    /**
     * Returns the content, if null returns null
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String getContent() { if (_data.containsKey("content")) return _data.get("content").toString(); return null; }

    /**
     * Sets the content.
     *
     * @param content : the desired content.
     */
    public void setContent(String content) {
        _data.put("content", content);
    }

    /**
     * Gets the useful rating.
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public boolean isUseful() {
        if (_data.containsKey("useful")) return Boolean.valueOf(_data.get("useful").toString()); return false; }

    /**
     * Sets the useful rating.
     *
      * @param useful : the desired usefulness.
     */
    public void setUseful(boolean useful) {
        _data.put("useful", useful);
    }

    /**
     * Returns the creation date, if null returns null.
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String getCreatedDate() { if (_data.containsKey("createdDate")) return _data.get("createdDate").toString(); return null; }

    /**
     * Sets the creation date.
     *
     * @param createdDate : the designed creation date.
     */
    public void setCreatedDate(String createdDate) { _data.put("createdDate", createdDate); }

    /**
     * Returns the user Id, if null returns null.
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String getUserId() { if (_data.containsKey("userid")) return _data.get("userid").toString(); return null; }

    /**
     * Sets the user Id.
     *
     * @param userid : the actual user Id.
     */
    public void setUserId(String userid) {
        _data.put("userid", userid);
    }

    /**
     * Returns the username, if null returns null.
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String getUsername() { if (_data.containsKey("username")) return _data.get("username").toString(); return null; }

    /**
     * Sets the username.
     *
     * @param username : the desired username.
     */
    public void setUsername(String username) {
        _data.put("username", username);
    }

    /**
     * Returns the topic Id, if null returns null.
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String getTopicId() { if (_data.containsKey("topicid")) return _data.get("topicid").toString(); return null; }

    /**
     * Sets the topic Id.
     *
     * @param topicId : the desired topic id.
     */
    public void setTopicId(String topicId) { _data.put("topicid", topicId); }

    /**
     * Returns the topic title, if null returns null.
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String getTopicTitle() { if (_data.containsKey("topictitle")) return _data.get("topictitle").toString(); return null; }

    /**
     * Sets the topic title.
     *
     * @param topicTitle : the desired topic title.
     */
    public void setTopicTitle(String topicTitle) { _data.put("topictitle", topicTitle); }

    /**
     * Returns the parsed creation date, if null returns null.
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String displayParsedCreatedDate() { if (_data.containsKey("createdDate")) return _data.get("createdDate").toString(); return null; }

    /**
     * Returns a part of the content of the answer.
     *
     * @return {@code string} the beginning of the content of the answer
     */
    public String displayBeginning() {
        String content = getContent();
        return (content.length() < 32) ? content.concat("...") : content.substring(0, 30).concat("...");
    }
}
