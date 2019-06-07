package it.incoro.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Thing.
 */
@Document(collection = "thing")
public class Thing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @NotNull
    @Field("description")
    private String description;
    
    @Field("actions")
    private String [] actions;
    
    @Field("events")
    private String[] events;
    
    
    public String[] getActions() {
        return actions;
    }
    public void setActions(String[] actions) {
        this.actions = actions;
    }
    
    public String[] getEvents() {
        return events;
    }
    public void setEvents(String[] events) {
        this.events = events;
    }
    
    
    
    
    

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Thing name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Thing description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Thing thing = (Thing) o;
        if (thing.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), thing.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Thing{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", description='" + getDescription() + "'" +
            ", actions='" + getActions().toString() + "'"+
            ", events='" + getEvents().toString() + "'" +
            "}";
            
    }
}
