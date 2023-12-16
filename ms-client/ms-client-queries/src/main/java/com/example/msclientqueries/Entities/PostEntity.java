package com.example.msclientqueries.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="post_table")
public class PostEntity {
    @Id
    private String postId;
    private String postTitle;
    private String postDescription;
    private String postImageUrl;
    private String clientId;
}
