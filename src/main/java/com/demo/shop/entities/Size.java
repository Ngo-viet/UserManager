package com.demo.shop.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_size")
@Getter
@Setter
@RequiredArgsConstructor
public class Size extends BaseEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "size_name")
    private String size_name;

    @ManyToMany(mappedBy = "sizes", fetch = FetchType.LAZY)
    private Set<Product> products =new HashSet<>();


}
