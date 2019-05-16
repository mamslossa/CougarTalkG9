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

    /**
     * Sets the actual Id.
     *
     * @param id the string containing the actual Id.
     */
    public void setRealId(String id) {
        _data.put("id", id);
    }

    /**
     * Returns the Id, if null returns null
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String getId() {
        if (_data.containsKey("id"))
            return _data.get("id").toString();
        return null;
    }

    /**
     * Returns the title, if null returns null
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String getTitle() {
        if (_data.containsKey("title"))
            return _data.get("title").toString();
        return null;
    }

    /**
     * Sets the title.
     *
     * @param title the string containing the desired title.
     */
    public void setTitle(String title) {
        _data.put("title", title);
    }

    /**
     * Returns the content, if null returns null
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String getContent() { if (_data.containsKey("content")) return _data.get("content").toString(); return null; }

    /**
     * Sets the desired content.
     *
     * @param content the string containing the desired content.
     */
    public void setContent(String content) {
        _data.put("content", content);
    }

    /**
     * Returns the category, if null returns null
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String getCategory() { if (_data.containsKey("category")) return _data.get("category").toString(); return null;  }

    /**
     * Sets the desired category.
     *
     * @param category the string containing the desired category.
     */
    public void setCategory(String category) {
        _data.put("category", category);
    }

    /**
     * Returns the creation date, if null returns null
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String getCreatedDate() {
    if (_data.containsKey("createdDate")) return _data.get("createdDate").toString(); return null; }

    /**
     * Sets the creation date.
     *
     * @param createdDate the string containing the desired date of the creation.
     */
    public void setCreatedDate(String createdDate) { _data.put("createdDate", createdDate); }

    /**
     * Returns the user Id, if null returns null
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String getUserId() { if (_data.containsKey("userid")) return _data.get("userid").toString(); return null; }

    /**
     * Sets the user id.
     *
     * @param userid the string containing the desired id of the user.
     */
    public void setUserId(String userid) {
        _data.put("userid", userid);
    }

    /**
     * Returns the username, if null returns null
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String getUsername() { if (_data.containsKey("username")) return _data.get("username").toString(); return null; }

    /**
     * Sets the username.
     *
     * @param userid the string containing the desired name of the user.
     */
    public void setUsername(String userid) {
        _data.put("username", userid);
    }

    /**
     * Returns the parsed creation date, if null returns null.
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String displayParsedCreatedDate() { if (_data.containsKey("createdDate")) return _data.get("createdDate").toString(); return null; }

}
