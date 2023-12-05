package com.demo.shop.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="tbl_user")
@Getter
@Setter
@RequiredArgsConstructor
public class User extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", length = 45, nullable = false)
    private String username;

    @Column(name="user_pass", length  = 250, nullable = false)
    private String password;

    @Column(name="user_email", length = 100, nullable = false)
    private String user_email;

    @Column(name="user_address", length = 100, nullable = false)
    private String user_address;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name="tbl_user_role",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();



}
