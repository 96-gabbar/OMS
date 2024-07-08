package org.autech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "user_details")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userApiKey;
    private List<String> userGroups;
}
