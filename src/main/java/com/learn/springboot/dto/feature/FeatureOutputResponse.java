package com.learn.springboot.dto.feature;

import com.learn.springboot.dto.MenuItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatureOutputResponse extends FeatureDto {
    private List<FeatureOutputResponse> items; // for nested items

    public FeatureOutputResponse(Long id, String title, String icon, Integer menuOrder, List<String> routerLink, List<FeatureOutputResponse> items) {
        super(id, title, icon, menuOrder, routerLink);
        this.items = items;
    }
}
