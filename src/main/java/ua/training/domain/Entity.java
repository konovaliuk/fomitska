package ua.training.domain;

import java.io.Serializable;

public class Entity implements Serializable, Cloneable {
    private Long id;

    public Entity() {
    }

    public Entity(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
