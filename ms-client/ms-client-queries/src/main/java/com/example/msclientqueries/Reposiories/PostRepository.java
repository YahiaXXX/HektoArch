package com.example.msclientqueries.Reposiories;

import com.example.msclientqueries.Entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PostRepository extends JpaRepository<PostEntity,String> {
    @Override
    List<PostEntity> findAll();

    List<PostEntity> findPostEntitiesByClientId(String clientId);
}
