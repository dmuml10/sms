package org.test.sms.common.entity.general;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.test.sms.common.entity.AppEntity;
import org.test.sms.common.enums.general.LanguageType;
import org.test.sms.common.enums.general.StatusType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = User.SEQUENCE_NAME, sequenceName = User.SEQUENCE_NAME, allocationSize = AppEntity.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "SMSUSER")
public class User extends AppEntity {

    static final String SEQUENCE_NAME = SEQUENCE_PREFIX + "USER" + SEQUENCE_SUFFIX;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    private long id;

    @Column(unique = true)
    private String username;

    @JsonIgnore
    private String password;

    private String email;

    private String name;

    @Enumerated(EnumType.STRING)
    private StatusType status;

    @Enumerated(EnumType.STRING)
    private LanguageType language;

    @ManyToOne
    private UserGroup userGroup;

    public User() {}

    public User(long id) {
        super(id);
    }

    public User(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public User(String username, String password, StatusType status, UserGroup userGroup) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.userGroup = userGroup;
    }

    public User(long id, String username, String email, String name, StatusType status, LanguageType language) {
        this(id);

        this.username = username;
        this.email = email;
        this.name = name;
        this.status = status;
        this.language = language;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LanguageType getLanguage() {
        return language;
    }

    public void setLanguage(LanguageType language) {
        this.language = language;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
}