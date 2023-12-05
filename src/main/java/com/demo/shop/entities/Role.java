package com.demo.shop.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbl_role")
@Getter
@Setter
@RequiredArgsConstructor
public class Role extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", length = 45, nullable = false)
    private String name;

    @Column(name="description", length = 200, nullable = false)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();
}
