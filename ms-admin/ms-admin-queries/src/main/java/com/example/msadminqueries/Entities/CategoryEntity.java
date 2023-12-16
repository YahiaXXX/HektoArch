package com.example.msadminqueries.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="category_table")
public class CategoryEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String categoryId;
    @Column(unique = true)
    private String categoryName;

}
