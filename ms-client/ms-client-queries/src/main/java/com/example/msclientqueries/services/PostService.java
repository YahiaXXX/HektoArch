package com.example.msclientqueries.Services;

import com.example.msclientqueries.DAOs.PostDAO;
import com.example.msclientqueries.Entities.PostEntity;
import com.example.msclientqueries.Reposiories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository serviceRepository;

    public void addPost(PostEntity postEntity){
        if(serviceRepository.findById(postEntity.getPostId()).isEmpty()){
            serviceRepository.save(postEntity);
        }else{
            System.out.println(postEntity.getPostTitle() +"already exist");
        }

    }

    public void updatePost(PostEntity post) {
        String newPostId = post.getPostId().replace("\"", "");
        if (serviceRepository.findById(newPostId).isPresent()) {
        //    serviceRepository.delete(serviceRepository.findById(newPostId).get());
            serviceRepository.save(post);
        } else {
            System.out.println("******* This post " + post.getPostId() + " doesn't existe ***********");

        }
    }

    public void deletePost(String postId){
        String newPostId = postId.replace("\"", "");
        if(serviceRepository.findById(newPostId).isPresent()){
            serviceRepository.delete(serviceRepository.findById(newPostId).get());
        }else{
            System.out.println("******* This post "+newPostId+" doesn't existe ***********");

        }
    }
    public List<PostDAO> getAllPosts(){
        List<PostDAO> postDAOList=new ArrayList<>();
        List<PostEntity> postEntities=serviceRepository.findAll();
        return getPostDAOS(postDAOList, postEntities);
    }
    public List<PostDAO> findPostEntitiesByClientId(String clientId){
        List<PostDAO> postDAOList=new ArrayList<>();
        List<PostEntity> postEntities=serviceRepository.findPostEntitiesByClientId(clientId);
        return getPostDAOS(postDAOList, postEntities);
    }

    private List<PostDAO> getPostDAOS(List<PostDAO> postDAOList, List<PostEntity> postEntities) {
        postEntities.forEach(post -> {
            PostDAO postDAO = new PostDAO();
            postDAO.setPostId(post.getPostId());
            postDAO.setPostDescription(post.getPostDescription());
            postDAO.setPostTitle(post.getPostTitle());
            postDAO.setPostImageUrl(post.getPostImageUrl());
            postDAO.setClientId(post.getClientId());
            postDAOList.add(postDAO);
        });

        return postDAOList;
    }

    public PostDAO getPostById(String postId){
        PostDAO postDAO=new PostDAO();

        if(serviceRepository.findById(postId).isPresent()){
            PostEntity post=serviceRepository.findById(postId).get();
            postDAO.setPostId(post.getPostId());
            postDAO.setPostDescription(post.getPostDescription());
            postDAO.setPostTitle(post.getPostTitle());
            postDAO.setPostImageUrl(post.getPostImageUrl());
            postDAO.setClientId(post.getClientId());

        }else{
            System.out.println("******* This categorty "+postId+" doesn't existe ***********");
        }
        return postDAO;
    }
}
