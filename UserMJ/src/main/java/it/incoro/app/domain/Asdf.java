package it.incoro.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Asdf.
 */
@Document(collection = "asdf")
public class Asdf implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("asdxc")
    private String asdxc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAsdxc() {
        return asdxc;
    }

    public Asdf asdxc(String asdxc) {
        this.asdxc = asdxc;
        return this;
    }

    public void setAsdxc(String asdxc) {
        this.asdxc = asdxc;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Asdf asdf = (Asdf) o;
        if (asdf.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), asdf.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Asdf{" +
            "id=" + getId() +
            ", asdxc='" + getAsdxc() + "'" +
            "}";
    }
}
