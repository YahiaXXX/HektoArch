package com.example.msshopqueries.Services;

import com.example.coreapis.Events.ProductEvents.*;
import com.example.msshopqueries.DAOs.ProductDAO;
import com.example.msshopqueries.Entites.Order;
import com.example.msshopqueries.Entites.ProductEntity;
import com.example.msshopqueries.Repositories.OrdersRepository;
import com.example.msshopqueries.Repositories.ProductReopsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service

public class ProductService {

    @Autowired
    private ProductReopsitory productReopsitory;
    @Autowired
    private OrdersRepository ordersRepository;

    public void addProduct(ProductEntity productEntity) {
        if (productReopsitory.findById(productEntity.getProductId()).isEmpty()) {
            productReopsitory.save(productEntity);
        } else {
            System.out.println(productEntity.getProductName() + "already exist");
        }
    }

    public void updateProduct(UpdateProductEvent event) {
        String newProductId = event.getProductId().replace("\"", "");
        System.out.println("*********** In update Product Function **************");
        if (productReopsitory.findById(newProductId).isPresent()) {
            ProductEntity product = productReopsitory.findById(newProductId).get();
            product.setPriceBefore(product.getProductPrice());
            product.setProductPrice(event.getProductPrice());
            product.setProductDesc(event.getProductDesc());
            product.setProductName(event.getProductName());
            product.setProductImageUrl(event.getProductImageUrl());
            product.setShopId(event.getShopId());
            product.setCategoryName(event.getCategoryName());
            product.setProductQuantity(event.getProductQuantity());
            product.setProductId(newProductId);
            //    productReopsitory.delete(productReopsitory.findById(newProductId).get());
            productReopsitory.save(product);
            System.out.println("*********** In update Product Function after save the new  product**************");
            System.out.println("*********** ************** " + productReopsitory.findById(newProductId));

        } else {
            System.out.println("******* This product " + event.getProductId() + " doesn't existe ***********");

        }
    }
    public void removeProductsFromOrder(RemoveProductsFromOrderEvent event) {
        String newOrderId = event.getOrderId().replace("\"", "");
        System.out.println("*********** In update Product Function **************");
        Order order=ordersRepository.findOrderByOrderId(newOrderId).orElseThrow();
        List<ProductEntity> productEntityList=productReopsitory.findProductEntitiesByOrdersContains(order);
        productEntityList.forEach(productEntity -> {
            productEntity.getOrders().remove(order);
            productEntity.setProductQuantity(productEntity.getProductQuantity());
            productReopsitory.save(productEntity);
        });
    }

    public void addQuantityToProduct(AddQuantityToProductEvent event) {
        String newProductId = event.getProductId().replace("\"", "");
        System.out.println("*********** In update Product Function **************");
        if (productReopsitory.findById(newProductId).isPresent()) {
            ProductEntity product = productReopsitory.findById(newProductId).get();
            product.setProductQuantity(event.getProductQuantity());
            product.setProductId(newProductId);
            //    productReopsitory.delete(productReopsitory.findById(newProductId).get());
            productReopsitory.save(product);
            System.out.println("*********** In update Product Function after save the new  product**************");
            System.out.println("*********** ************** " + productReopsitory.findById(newProductId));

        } else {
            System.out.println("******* This product " + event.getProductId() + " doesn't existe ***********");

        }
    }

    public void addProductToOrder(AddProductToOrderEvent event) {
        try {
            String newProductId = event.getProductId().replace("\"", "");
            String newOrderId = event.getOrder().getOrderId().replace("\"", "");
            System.out.println("*********** In AddProductToOrderEvent Function **************");
            if (productReopsitory.findById(newProductId).isPresent()) {
                ProductEntity product = productReopsitory.findById(newProductId).get();
                Order order = new Order();
                order.setOrderProductjoinCloumn(UUID.randomUUID().toString());
                order.setOrderId(newOrderId);
                order.setOrderStatus(event.getOrder().getOrderStatus());
                order.setClientId(event.getOrder().getClientId());
                //     order.setTotalPrice(event.getOrder().getTotalPrice());
                order.setOrderDate(event.getOrder().getOrdeDate());
                ordersRepository.save(order);
                System.out.println("*********** In AddProductToOrderEvent Function after Saving the Order **************");
                product.getOrders().add(order);
                //    productReopsitory.delete(productReopsitory.findById(newProductId).get());
                product.setProductQuantity(product.getProductQuantity() - event.getProductQuantity());
                productReopsitory.save(product);
                System.out.println("*********** In update Product Function after save the new  product**************");
                System.out.println("*********** ************** " + productReopsitory.findById(newProductId));

            } else {
                System.out.println("******* This product " + event.getProductId() + " doesn't existe ***********");

            }
        } catch (Exception e) {
            System.out.println("While addProductToOrder work");
            System.out.println(e);
        }

    }

    public void addProductsToOrder(AddProductsToOrderEvent event) {
        try {
            String newOrderId = event.getOrder().getOrderId().replace("\"", "");
            Order order = new Order();
            order.setOrderProductjoinCloumn(UUID.randomUUID().toString());
            order.setOrderId(newOrderId);
            order.setOrderStatus(event.getOrder().getOrderStatus());
            order.setClientId(event.getOrder().getClientId());
            //  order.setTotalPrice(event.getOrder().getTotalPrice());
            order.setOrderDate(event.getOrder().getOrdeDate());
           ordersRepository.save(order);
            for (int i = 0; i < event.getProductsIds().size(); i++) {
                Integer eq = event.getProductsQuantities().get(i);
                System.out.println("***********0000000 event.getProductsQuantities().get(i) " + eq);
                String newProductId = event.getProductsIds().get(i).replace("\"", "");
                if (productReopsitory.findById(newProductId).isPresent()) {
                    System.out.println("*********** In AddProductsToOrderEvent Function **************");
                    ProductEntity product = productReopsitory.findById(newProductId).get();
                    System.out.println("*********** In AddProductsToOrderEvent Function after Saving the Order **************");
                    AtomicBoolean inIt= new AtomicBoolean(false);
                    product.getOrders().forEach(order1 -> {
                        if (Objects.equals(order1.getOrderId(), order.getOrderId()) || Objects.equals(order1.getOrderProductjoinCloumn(), order.getOrderProductjoinCloumn())){
                            inIt.set(true);
                        }
                    });
                    if (!inIt.get()) {
                        product.getOrders().add(order);
                        product.setProductQuantity(product.getProductQuantity() - eq);
                        productReopsitory.deleteById(product.getProductId());
                        productReopsitory.save(product);
                    }

                } else {
                    System.out.println("******* This product " + newProductId + " doesn't existe ***********");
                }

            }
        } catch (Exception e) {
            System.out.println("While addProductsToOrder work");
            System.out.println(e);
        }


    }

    public void deleteProduct(String productId) {

        try {
            String newProductId = productId.replace("\"", "");
            if (productReopsitory.findById(newProductId).isPresent()) {
                productReopsitory.delete(productReopsitory.findById(newProductId).get());
            } else {
                System.out.println("******* This product " + newProductId + " doesn't existe ***********");

            }
        } catch (Exception e) {
            System.out.println("While deleteProduct work");
            System.out.println(e);
        }

    }

    public List<ProductDAO> getAllProducts() {
        List<ProductDAO> productDAOList = new ArrayList<>();
        List<ProductEntity> productEntities = productReopsitory.findAll();
        return getProductDAOS(productDAOList, productEntities);
    }

    public List<ProductDAO> findProductEntitiesByCategoryName(String categoryName) {
        List<ProductDAO> productDAOList = new ArrayList<>();
        List<ProductEntity> productEntities = productReopsitory.findProductEntitiesByCategoryName(categoryName);
        return getProductDAOS(productDAOList, productEntities);
    }

    public List<ProductDAO> findProductsByCategoryName(String categoryName) {
        List<ProductDAO> productDAOList = new ArrayList<>();
        List<ProductEntity> productEntities = productReopsitory.findProductEntitiesByCategoryName(categoryName);
        return getProductDAOS(productDAOList, productEntities);
    }

    public List<ProductDAO> findProductEntitiesByShopId(String shopId) {
        List<ProductDAO> productDAOList = new ArrayList<>();
        List<ProductEntity> productEntities = productReopsitory.findProductEntitiesByShopId(shopId);
        return getProductDAOS(productDAOList, productEntities);
    }

    public List<ProductDAO> findProductEntitiesByProductPriceIsLessThanEqual(Double price) {
        List<ProductDAO> productDAOList = new ArrayList<>();
        List<ProductEntity> productEntities = productReopsitory.findProductEntitiesByProductPriceIsLessThanEqual(price);
        return getProductDAOS(productDAOList, productEntities);
    }

    public List<ProductDAO> findProductEntitiesByProductPriceIsGreaterThanEqual(Double price) {
        List<ProductDAO> productDAOList = new ArrayList<>();
        List<ProductEntity> productEntities = productReopsitory.findProductEntitiesByProductPriceIsGreaterThanEqual(price);
        return getProductDAOS(productDAOList, productEntities);
    }

    private List<ProductDAO> getProductDAOS(List<ProductDAO> productDAOList, List<ProductEntity> productEntities) {
        productEntities.forEach(product -> {
            ProductDAO productDAO = new ProductDAO();
            EntityToDao(productDAO, product);
            productDAOList.add(productDAO);
        });

        return productDAOList;
    }

    public ProductDAO getProductById(String productId) {
        ProductDAO productDAO = new ProductDAO();

        if (productReopsitory.findById(productId).isPresent()) {
            ProductEntity product = productReopsitory.findById(productId).get();
            EntityToDao(productDAO, product);

        } else {
            System.out.println("******* This Product " + productId + " doesn't existe ***********");
        }
        return productDAO;
    }

    private void EntityToDao(ProductDAO productDAO, ProductEntity product) {
        productDAO.setProductId(product.getProductId());
        productDAO.setProductName(product.getProductName());
        productDAO.setProductDesc(product.getProductDesc());
        productDAO.setProductImageUrl(product.getProductImageUrl());
        productDAO.setProductPrice(product.getProductPrice());
        productDAO.setProductQuantity(product.getProductQuantity());
        productDAO.setPriceBefore(product.getPriceBefore());
        productDAO.setShopId(product.getShopId());
        productDAO.setCategoryName(product.getCategoryName());
        productDAO.setOrders(product.getOrders());
    }
}
