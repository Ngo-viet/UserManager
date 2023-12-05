package com.demo.shop.entities;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "created_date")
    private Date created_date;

    @Column(name = "modified_by")
    private String modified_by;

    @Column(name = "modified_date")
    private Date modified_date;


    public String getCreated_by() {
        return created_by;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public String getModified_by() {
        return modified_by;
    }

    public Date getModified_date() {
        return modified_date;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }

    public void setModified_date(Date modified_date) {
        this.modified_date = modified_date;
    }
}
