package com.greff.foodapi.domain.model;

import java.util.Objects;

public class Client {

    private Long id;
    private String name;
    private String email;
    private String cellphone;
    private Boolean profileActive;

    public Client() {
    }

    public Client(Long id, String name, String email, String cellphone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Boolean getProfileActive() {
        return profileActive;
    }

    public void setProfileActive(Boolean profileActive) {
        this.profileActive = profileActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return getId().equals(client.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
