package br.com.mike_lanches.api.apps.contact.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import jakarta.persistence.Table;
import lombok.Setter;

// Create model - contact
@Entity
@Table(name = "contact")
@Getter
@Setter
public class ContactModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name
    @Column(name = "name_contact", length = 255, nullable = false, unique = false)
    @JsonProperty("name_contact")
    private String name;

    // Email
    @Column(name = "email_contact", length = 255, nullable = false, unique = false)
    @JsonProperty("email_contact")
    private String email;

    // Message
    @Column(name = "message_contact", length = 500, nullable = false, unique = false)
    @JsonProperty("message_contact")
    private String message;
    
}
