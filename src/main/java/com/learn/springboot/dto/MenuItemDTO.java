package com.learn.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDTO {
    private String label;
    private String icon;
    private String routerLink;
    private List<MenuItemDTO> items; // for nested items
}
