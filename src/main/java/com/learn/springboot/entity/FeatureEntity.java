package com.learn.springboot.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "features")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name = "icon")
    private String icon;
    @Column(name = "router_link")
    private List<String> routerLink = new ArrayList<>();

    @Column(name = "status")
    private Boolean status = true;

    @Column(name = "menu_order")
    private Integer menuOrder;

}
