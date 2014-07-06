package org.zk.entities;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .toString();
    }
}
