package com.example.msclientqueries.Services;

import com.example.coreapi.DTOs.OrderStatus;
import com.example.msclientqueries.DAOs.OrderDAO;
import com.example.msclientqueries.Entities.OrderEntity;
import com.example.msclientqueries.Entities.Product;
import com.example.msclientqueries.Proxies.ProductCommandProxy;
import com.example.msclientqueries.Reposiories.OrderRepository;
import com.example.msclientqueries.Reposiories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductsRepository productsRepository;
    public OrderService(OrderRepository orderRepository, ProductsRepository productsRepository){
         this.orderRepository=orderRepository;
        this.productsRepository = productsRepository;
    }

    public void addOrder(OrderEntity order,List<Product> products) {
        if (orderRepository.findById(order.getOrderId()).isEmpty()) {
            TestIfProductExist(order,products);
        } else {
            System.out.println("******* This order " + order.getOrderId() + " already existe ***********");
        }
    }
    public void updateOrder(OrderEntity order,List<Product> products) {
        String newOrderId = order.getOrderId().replace("\"", "");
        if (orderRepository.findById(newOrderId).isPresent()) {
            System.out.println("before make the list empty");
            OrderEntity order1=orderRepository.findById(newOrderId).get();
            order1.getProducts().forEach(product -> {
                productsRepository.deleteById(product.getProductIdJoinColumn());
            });
            order1.getProducts().clear();
            System.out.println("after make the list empty and before fill again");

            AtomicReference<Double> totalPrice= new AtomicReference<>(0.0);
            products.forEach(product -> {
                totalPrice.set(totalPrice.get() + (product.getProductPrice() * Double.parseDouble(product.getProductQuantity().toString())));
                productsRepository.save(product);
                System.out.println("Product ********************* "+product.getProductIdJoinColumn());
                System.out.println("totalPrice.get()********* ********************* "+totalPrice.get());
                order1.getProducts().add(product);
            });
            order1.setTotalPrice(totalPrice.get());
            System.out.println("totalPrice ********* ********************* "+totalPrice.get());

            System.out.println("after fill the list ");

            orderRepository.save(order1);
        } else {
            System.out.println("******* This order " + order.getOrderId() + " doesn't existe ***********");

        }
    }

    public void updateOrderStatus(String orderid,OrderStatus orderStatus) {
        String newOrderId = orderid.replace("\"", "");
        if (orderRepository.findById(newOrderId).isPresent()) {
            OrderEntity order=orderRepository.findById(newOrderId).get();
            order.setOrderStatus(orderStatus);
            orderRepository.save(order);
        } else {
            System.out.println("******* This order " + orderid + " doesn't existe ***********");

        }
    }

    public void removeProductFromOrder(String orderId, String productIdJoinColumn){
        String newOrderId = orderId.replace("\"", "");
        if (orderRepository.findById(newOrderId).isPresent()) {
           // orderRepository.deleteById(newOrderId);
            if(productsRepository.findById(productIdJoinColumn).isPresent()){
                orderRepository.findById(newOrderId).get().getProducts().remove(productsRepository.findById(productIdJoinColumn).get());
                if(orderRepository.findById(newOrderId).get().getProducts().isEmpty()){
                    orderRepository.deleteById(newOrderId);
                }
                productsRepository.deleteById(productIdJoinColumn);
            }
        }
    }
    public void addProductToOrder(String orderId, Product product){
        String newOrderId = orderId.replace("\"", "");
        System.out.println("****************************** before ************************");
        if (orderRepository.findById(newOrderId).isPresent()) {
            productsRepository.save(product);
            System.out.println("****************************** in ************************");

            OrderEntity order=orderRepository.findById(newOrderId).get();
            order.setTotalPrice(order.getTotalPrice()+product.getProductPrice()* product.getProductQuantity());
            order.getProducts().add(product);
            orderRepository.save(order);
            System.out.println("****************************** after ************************");

        }
    }
    public void addProductsToOrder(String orderId, List<Product> products){
        String newOrderId = orderId.replace("\"", "");
        System.out.println("****************************** before addProductsToOrder************************");
        if (orderRepository.findById(newOrderId).isPresent()) {
            OrderEntity order=orderRepository.findById(newOrderId).get();
            products.forEach(product -> {
                productsRepository.save(product);
                System.out.println("****************************** in addProductsToOrder************************");
                order.setTotalPrice(order.getTotalPrice()+(product.getProductPrice() * Double.parseDouble(product.getProductQuantity().toString())));
                order.getProducts().add(product);
                orderRepository.save(order);
                System.out.println("****************************** after addProductsToOrder************************");
            });
        }
    }
    private void TestIfProductExist(OrderEntity order,List<Product> products) {

        AtomicReference<Double> totalPrice= new AtomicReference<>(0.0);
        products.forEach(productD -> {
            System.out.println(productD.getProductName()+"***"+productD.getProductIdJoinColumn());

                if(productsRepository.findById(productD.getProductIdJoinColumn()).isEmpty()){
                    String uid=  UUID.randomUUID().toString();
                    productD.setProductIdJoinColumn(uid);
                }
            totalPrice.set(totalPrice.get() + (productD.getProductPrice() * Double.parseDouble(productD.getProductQuantity().toString())));
                  productsRepository.save(productD);

            });
           order.setProducts(products);
           System.out.println("+++***33 "+ order.getProducts().toString());
           System.out.println("+++***3388888 "+ order);
           order.setTotalPrice(totalPrice.get());
       orderRepository.save(order);


    }


    public List<OrderDAO> getAllOrders(){
        List<OrderDAO> orderDAOList=new ArrayList<>();
        List<OrderEntity> orderEntities=orderRepository.findAll();
        return getOrderDAOS(orderDAOList, orderEntities);
    }

    private List<OrderDAO> getOrderDAOS(List<OrderDAO> orderDAOList, List<OrderEntity> orderEntities) {
        orderEntities.forEach(order -> {
            OrderDAO orderDAO = new OrderDAO();
            orderDAO.setOrderId(order.getOrderId());
            orderDAO.setOrderStatus(order.getOrderStatus());
            orderDAO.setOrdeDate(order.getOrdeDate());
//            orderDAO.setShoppingCartId(order.getShoppingCartId());
            orderDAO.setClientId(order.getClientId());
            orderDAO.setProducts(order.getProducts());
            orderDAO.setTotalPrice(order.getTotalPrice());
            orderDAOList.add(orderDAO);
        });

        return orderDAOList;
    }

    public OrderDAO getOrderById(String orderId){
        OrderDAO orderDAO=new OrderDAO();

        if(orderRepository.findById(orderId).isPresent()){
            OrderEntity order=orderRepository.findById(orderId).get();
            orderDAO.setOrderId(order.getOrderId());
            orderDAO.setOrderStatus(order.getOrderStatus());
            orderDAO.setOrdeDate(order.getOrdeDate());
            orderDAO.setProducts(order.getProducts());
//            orderDAO.setShoppingCartId(order.getShoppingCartId());
            orderDAO.setClientId(order.getClientId());

        }else{
            System.out.println("******* This categorty "+orderId+" doesn't existe ***********");
        }
        return orderDAO;
    }

    public List<OrderDAO> getOrdersByClientId(String clientId){

        List<OrderDAO> orderDAOList=new ArrayList<>();
        List<OrderEntity> orderEntities=orderRepository.findOrderEntitiesByClientId(clientId);

        return getOrderDAOS(orderDAOList, orderEntities);
    }

    public List<OrderDAO> getOrdersByStatus(String orderStatus){
        List<OrderDAO> orderDAOList=new ArrayList<>();
        List<OrderEntity> orderEntities=new ArrayList<>();
        boolean exists = false;

        switch (orderStatus) {
            case "NEW":
                orderEntities=orderRepository.findAllByOrderStatus(OrderStatus.NEW);
                exists = true;
                break;
            case "PENDING":
                orderEntities=orderRepository.findAllByOrderStatus(OrderStatus.PENDING);
                exists = true;
                break;
            case "SHIPPED":
                orderEntities=orderRepository.findAllByOrderStatus(OrderStatus.SHIPPED);
                exists = true;
                break;
            case "DELIVERED":
                orderEntities=orderRepository.findAllByOrderStatus(OrderStatus.DELIVERED);
                exists = true;
                break;
            case "CLOSED":
                orderEntities=orderRepository.findAllByOrderStatus(OrderStatus.CLOSED);
                exists = true;
                break;
            case "ON_HOLD":
                orderEntities=orderRepository.findAllByOrderStatus(OrderStatus.ON_HOLD);
                exists = true;
                break;
            default:
                exists = false;
                break;
        }
        if (exists) {
            return getOrderDAOS(orderDAOList, orderEntities);
        }
        System.out.println(orderStatus+" does not exist.");

        return orderDAOList;

    }
    public List<OrderDAO> findAllByOrderStatusAndClientId(String orderStatus,String clientId){
        List<OrderDAO> orderDAOList=new ArrayList<>();
        List<OrderEntity> orderEntities=new ArrayList<>();
        boolean exists = false;
        switch (orderStatus) {
            case "NEW":
                orderEntities=orderRepository.findAllByOrderStatusAndClientId(OrderStatus.NEW,clientId);
                exists = true;
                break;
            case "PENDING":
                orderEntities=orderRepository.findAllByOrderStatusAndClientId(OrderStatus.PENDING,clientId);
                exists = true;
                break;
            case "SHIPPED":
                orderEntities=orderRepository.findAllByOrderStatusAndClientId(OrderStatus.SHIPPED,clientId);
                exists = true;
                break;
            case "DELIVERED":
                orderEntities=orderRepository.findAllByOrderStatusAndClientId(OrderStatus.DELIVERED,clientId);
                exists = true;
                break;
            case "CLOSED":
                orderEntities=orderRepository.findAllByOrderStatusAndClientId(OrderStatus.CLOSED,clientId);
                exists = true;
                break;
            case "ON_HOLD":
                orderEntities=orderRepository.findAllByOrderStatusAndClientId(OrderStatus.ON_HOLD,clientId);
                exists = true;
                break;
            default:
                exists = false;
                break;
        }
        if (exists) {
            return getOrderDAOS(orderDAOList, orderEntities);
        }
        System.out.println(orderStatus+" does not exist.");

        return orderDAOList;

    }


    public void deleteOrder(String orderId){
        String newOrderId = orderId.replace("\"", "");
        if(orderRepository.findById(newOrderId).isPresent()){
            List<Product> products=orderRepository.findById(newOrderId).get().getProducts();
            orderRepository.delete(orderRepository.findById(newOrderId).get());
            products.forEach(product -> {
                productsRepository.deleteById(product.getProductIdJoinColumn());
            });

        }else{
            System.out.println("******* This order "+newOrderId+" doesn't existe ***********");

        }
    }
}
