package org.zk.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
}
