package it.incoro.app.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Asdf entity.
 */
public class AsdfDTO implements Serializable {

    private String id;

    private String asdxc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAsdxc() {
        return asdxc;
    }

    public void setAsdxc(String asdxc) {
        this.asdxc = asdxc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AsdfDTO asdfDTO = (AsdfDTO) o;
        if (asdfDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), asdfDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AsdfDTO{" +
            "id=" + getId() +
            ", asdxc='" + getAsdxc() + "'" +
            "}";
    }
}
