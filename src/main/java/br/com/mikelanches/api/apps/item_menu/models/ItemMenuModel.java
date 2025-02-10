package br.com.mikelanches.api.apps.item_menu.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
// Imports
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


// Create item_menu table
@Entity
@Table(name = "item_menu")
@Getter
@Setter
public class ItemMenuModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name
    @Column(name = "name_item_menu", length = 255, nullable = false, unique = false)
    @JsonProperty("name_item_menu")
    private String name;

    // Description
    @Column(name = "description_item_menu", length = 500, nullable = false, unique = false)
    @JsonProperty("description_item_menu")
    private String description;

    // Image
    @Column(name = "image_item_menu", nullable = true)
    @JsonProperty("image_item_menu")
    private String image;
    
}





