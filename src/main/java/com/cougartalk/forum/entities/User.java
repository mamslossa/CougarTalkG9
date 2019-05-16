package com.cougartalk.forum.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;

public class User implements UserDetails {

    public User(Map<String, Object> data) {
        if (data == null)
            _data = new HashMap<String, Object>();
        else
            _data = data;
    }


    private Map<String, Object> _data;

    /**
     * Sets the actual Id.
     *
     * @param id the string containing the desired the actual Id.
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
        return _data.get("id").toString();
    }

    /**
     * Returns the username, if null returns null
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    @Override
    public String getUsername() {
        if (_data.containsKey("username"))
            return _data.get("username").toString();
        return null;
    }

    /**
     * Sets the username.
     *
     * @param username the string containing the desired name of the user.
     */
    public void setUsername(String username) {
        _data.put("username", username);
    }

    /**
     * Returns the authorities.
     *
     * @return {@code Collections.singletonList} containing the authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }

    /**
     * Returns the password, if null returns null
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String getPassword() {
        if (_data.containsKey("password"))
            return _data.get("password").toString();
        return null;
    }

    /**
     * Returns account non-expiration state
     *
     * @return {@code boolean} true.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Returns account non-locked state
     *
     * @return {@code boolean} true.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Returns credentials non-expiration state
     *
     * @return {@code boolean} true.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Returns account enabled state
     *
     * @return {@code boolean} true.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Sets the password.
     *
     * @param password the string containing the desired password.
     */
    public void setPassword(String password) {
        _data.put("password", password);
    }

    /**
     * Returns the introduction, if null returns null
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String getIntroduction() { if (_data.containsKey("introduction")) return _data.get("introduction").toString();return null; }

    /**
     * Sets the introduction.
     *
     * @param introduction the string containing the desired introduction.
     */
    public void setIntroduction(String introduction) {
        _data.put("introduction", introduction);
    }

    /**
     * Returns the creation date, if null returns null
     *
     * @return {@code string} itself if it is non-null, {@code ""} null otherwise.
     */
    public String getCreatedDate() { if (_data.containsKey("createdDate")) return _data.get("createdDate").toString(); return null; }

    /**
     * Sets the creation date.
     *
     * @param createdDate the string containing the actual creation date.
     */
    public void setCreatedDate(String createdDate) { _data.put("createdDate", createdDate); }

}
