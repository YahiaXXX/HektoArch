package com.example.msauth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopModel {

    private String name;

    private String password;




    private Address address;

    private String numberPhone;



    private MultipartFile image;



}
