package org.zk.entities;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    public static final String ENTITY_SEQUENCE = "EntitySequence";
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = BaseEntity.ENTITY_SEQUENCE, initialValue = 100, sequenceName = "entity_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ENTITY_SEQUENCE)
    protected Long id;

    @Temporal(TemporalType.TIMESTAMP)
    protected Calendar createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    protected Calendar lastModifiedDate;

    public Long getId() {
        return id;
    }

    @PreUpdate
    @PrePersist
    @SuppressWarnings("unused")
    private void updateTimeStamps() {
        lastModifiedDate = Calendar.getInstance();
        if (createdDate == null) {
            createdDate = Calendar.getInstance();
        }
    }

    public Calendar getCreatedDate() {
        return createdDate == null ? null : (Calendar) createdDate.clone();
    }

    public Calendar getLastModifiedDate() {
        return lastModifiedDate == null ? null : (Calendar) lastModifiedDate.clone();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .toString();
    }
}
