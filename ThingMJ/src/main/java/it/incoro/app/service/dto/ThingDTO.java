package it.incoro.app.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Thing entity.
 */
public class ThingDTO implements Serializable {

    private String id;

    @NotNull
    private String name;

    @NotNull
    private String description;
    
    private String[] actions;
    
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ThingDTO thingDTO = (ThingDTO) o;
        if (thingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), thingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ThingDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
