package com.slotizen.venus.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permissions", indexes = {
        @Index(name = "idx_permissions_name", columnList = "name")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;   // e.g. view_dashboard

    @Column(length = 255)
    private String description;
}
