package com.nerdery.voting.model;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class TimeStampEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date created;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    public Date getCreated() {
        return created;
    }
}
