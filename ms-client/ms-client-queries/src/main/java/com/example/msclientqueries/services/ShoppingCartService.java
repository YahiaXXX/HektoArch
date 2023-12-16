package com.example.msclientqueries.Services;

import com.example.msclientqueries.DAOs.ShoppingCartDAO;
import com.example.msclientqueries.Entities.ShoppingCartEntity;
import com.example.msclientqueries.Reposiories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    public void CreateShoppingCart(ShoppingCartEntity shoppingCart){
        if(shoppingCartRepository.findById(shoppingCart.getClientId()).isEmpty()){
            shoppingCartRepository.save(shoppingCart);
        }else{
            System.out.println(shoppingCart.getShoppingCartId() +"already exist");
        }

    }

    public ShoppingCartDAO getShoppingCartById(String shoppingCartId){
        ShoppingCartDAO shoppingCartDAO=new ShoppingCartDAO();

        if(shoppingCartRepository.findById(shoppingCartId).isPresent()){
            ShoppingCartEntity shoppingCart=shoppingCartRepository.findById(shoppingCartId).get();
            shoppingCartDAO.setShoppingCartId(shoppingCart.getShoppingCartId());
            shoppingCartDAO.setClientId(shoppingCart.getClientId());
        }else{
            System.out.println("******* This categorty "+shoppingCartId+" doesn't existe ***********");
        }
        return shoppingCartDAO;
    }
    public ShoppingCartDAO getShoppingCartByClientId(String clientId){
        ShoppingCartDAO shoppingCartDAO=new ShoppingCartDAO();

        if(shoppingCartRepository.findShoppingCartEntityByClientId(clientId).isPresent()){
            ShoppingCartEntity shoppingCart=shoppingCartRepository.findShoppingCartEntityByClientId(clientId).get();
            shoppingCartDAO.setShoppingCartId(shoppingCart.getShoppingCartId());
            shoppingCartDAO.setClientId(shoppingCart.getClientId());
        }else{
            System.out.println("******* This clientId "+clientId+" doesn't existe ***********");
        }
        return shoppingCartDAO;
    }
}
