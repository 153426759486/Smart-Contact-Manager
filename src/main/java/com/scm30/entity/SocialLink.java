package com.scm30.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
//import lombok.*;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
@Entity
public class SocialLink {
    @Id
    private Long id;
    private String Link;
    private String title;

    @ManyToOne
    private Contact contact;
}
