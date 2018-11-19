package cz.muni.fi.pa165.entity.base;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@MappedSuperclass
public abstract class User extends BaseEntity {

    public final static String EMAIL_FIELD = "email";

    @NotNull
    @Column(nullable = false)
    private String name = "";

    @NotNull
    @Column(nullable = false)
    private String surname = "";

    @Email
    @NotNull
    @Column(nullable = false, unique = true)
    private String email = "";

    @NotNull
    @Column(nullable = false)
    private String passwordHash = "";

    public User(String name, String surname, String email, String passwordHash) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    protected User() {
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getSurname() {
        return surname;
    }

    public void setSurname(@NotNull String surname) {
        this.surname = surname;
    }

    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    @NotNull
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(@NotNull String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean hasName() {
        return !getName().isEmpty();
    }

    public boolean hasSurname() {
        return !getSurname().isEmpty();
    }

    public boolean hasEmail() {
        return !getEmail().isEmpty();
    }

    public boolean hasFullName() {
        return hasName() && hasSurname();
    }

    public boolean hasPassword() {
        return !getPasswordHash().isEmpty();
    }

    public boolean isConfigured() {
        return hasFullName()
                && hasEmail()
                && hasPassword();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + getName() + '\''
                + ", surname='" + getSurname() + '\''
                + ", email='" + getEmail() + '\''
                + ", password='" + getPasswordHash() + '\''
                + "} " + super.toString();
    }
}
