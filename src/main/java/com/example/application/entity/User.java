package com.example.application.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    Integer id;

    String email;
    String password;
    @ManyToMany
    @JoinTable(
            name = "user_roles",  // Specify the name of the join table
            joinColumns = @JoinColumn(name = "user_id"),  // Name of the column in this table (users) that references the join table
            inverseJoinColumns = @JoinColumn(name = "role_code")  // Name of the column in the related table (roles) that references the join table
    )
    private List<Role> roles;


}