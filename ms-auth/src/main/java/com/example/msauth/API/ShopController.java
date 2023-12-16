package com.example.msauth.API;


import com.example.msauth.Registration.EmailValidator;
import com.example.msauth.Registration.JwtUtils;
import com.example.msauth.entities.*;
import com.example.msauth.repository.ShopRepository;
import com.example.msauth.repository.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/shop")

@CrossOrigin(origins = "http://localhost:3000")

public class ShopController {

    private EmailValidator emailValidator;


    @Autowired
    private JavaMailSender mailSender;


    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ShopRepository shopRepository;


    @Value("${upload.directory}")
    private String uploadDirectory;
    public ShopController(EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
        // this.passwordEncoder = new BCryptPasswordEncoder();

    }

    @PostMapping("/createShop")
    public ResponseEntity<Object> createUser(@RequestBody Shop req ,
                             @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) throws Exception {

        System.out.println(token);
        boolean isValidEmail = emailValidator.
                test(req.getEmail());

        if (!isValidEmail) {
            System.out.println("email not valid");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("email not valid");

        }

        //LoginAdmin


        if(token.equals("No token found in cookie")){
            System.out.println("Please login to continue");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please login to continue");

        }

        Claims claims = jwtUtils.verify(token);
        if(claims == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token Not Valid");

        }
        User user =  userRepository.findByUserId(claims.getIssuer());

        if(!user.getRole().equals(UserRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You Are Not allowed");

        }


        User userExists = userRepository
                .findByEmail(req.getEmail());


        if (userExists != null) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("email already taken");


        }

        Shop shopExists = shopRepository
                .findByEmail(req.getEmail());

        if (shopExists != null) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("email already taken");

        }







        String userId = UUID.randomUUID().toString();
        String password = "anes123";

        Shop shop  = new Shop(userId,null, req.getEmail() ,password,false,false,UserRole.SHOP,new Address(),null,null);



        shopRepository.save(shop);



        String textMail = "\nEmail : " +  req.getEmail() + "\n Password : " + password+"\n";


        sendSimpleEmail(req.getEmail() , buildEmail(user.getName(), textMail,""));

        return ResponseEntity.ok("Please Check Your E-mail " + req.getEmail());




    }






    @PostMapping("/loginShop")
    public ResponseEntity<Object> loginUser(@RequestBody UserLogin request , HttpServletResponse response ) {


        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("email not valid");

        }



        Shop shop = shopRepository
                .findByEmail(request.getEmail());

        if (shop == null) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("email does not exist");

        }

        if(!shop.getPassword().equals(request.getPassword())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wrong Password");

        }



        //Set Cookie
        sendTokenToCookie(shop.getShopId() , response);
        return ResponseEntity.ok(shop.isActive());


    }



    @PutMapping("/activateShop")
    public ResponseEntity<Object> updateUser(
            @CookieValue(value = "token", defaultValue = "No token found in cookie") String token,
            @RequestBody ShopModel shop
    ) throws Exception {

        System.out.println("karim");

        if(token.equals("No token found in cookie")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No token found in cookie");
        }

        Claims claims = jwtUtils.verify(token);
        if(claims == null ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token Not valid");
        }

        Shop existingShop = shopRepository.findByShopId(claims.getIssuer());
        System.out.println("existingShop " + existingShop);
        if (existingShop == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop not found");
        }



        if(shop.getName() != null && shop.getPassword() != null && shop.getAddress() !=null
                && shop.getNumberPhone() != null


        ) {
            existingShop.setName(shop.getName());
            existingShop.setPassword(shop.getPassword());
            existingShop.setNumberPhone(shop.getNumberPhone());
            existingShop.setAddress(shop.getAddress());
            existingShop.setEnabled(true);
            existingShop.setActive(true);
            shopRepository.save(existingShop);

            return ResponseEntity.ok(existingShop);
        }
         else {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("One of the values is null");
         }

    }


    @PutMapping("/uploadImage")
    public ResponseEntity<Object> uploadImage(
            @CookieValue(value = "token", defaultValue = "No token found in cookie") String token,
            @RequestParam("image") MultipartFile image
    ) {
        try {
            // Generate a unique filename for the uploaded image
            Claims claims = jwtUtils.verify(token);
            System.out.println(token);
            if(claims == null ){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token Not valid");
            }

            Shop existingShop = shopRepository.findByShopId(claims.getIssuer());
            if(existingShop!=null){


            System.out.println("MY SHOP = "+existingShop);
            String fileName = UUID.randomUUID().toString() + "_" +
                    image.getOriginalFilename();

            Path filePath = Paths.get(uploadDirectory, fileName);
            Files.write(filePath, image.getBytes());
            System.out.println();
            existingShop.setImage(fileName);
            shopRepository.save(existingShop );

            return ResponseEntity.ok(existingShop);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop Not found");

            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to upload Image.");

        }
    }



    @GetMapping("/getStatus")
    public ResponseEntity<Object> getStatus(
            @CookieValue(value = "token", defaultValue = "No token found in cookie") String token
    ){


        Claims claims = jwtUtils.verify(token);
        System.out.println(token);
        if(claims == null ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token Not valid");
        }

        Shop existingShop = shopRepository.findByShopId(claims.getIssuer());
        return ResponseEntity.status(HttpStatus.OK).body(existingShop.isActive());



    }



    @PatchMapping("/updateShop")
    public ResponseEntity<Object> updateShop(
            @CookieValue(value = "token", defaultValue = "No token found in cookie") String token,
            @RequestBody ShopModel shop) throws Exception {
        // Retrieve the existing user from the repository

        if(token.equals("No token found in cookie")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No token found in cookie");
        }
        Claims claims = jwtUtils.verify(token);
        if(claims == null ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token Not valid");
        }

        Shop existingShop = shopRepository.findByShopId(claims.getIssuer());
        if (existingShop == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop not found");
        }



        if(shop.getName() != null ){
            existingShop.setName(shop.getName());
        }
        if(shop.getPassword() != null ){
            existingShop.setPassword(shop.getPassword());
        }
        if(shop.getNumberPhone() != null ){
            existingShop.setNumberPhone(shop.getNumberPhone());
        }
        if(shop.getAddress() != null ){
            existingShop.setAddress(shop.getAddress());
        }


       shopRepository.save(existingShop);
            return ResponseEntity.ok(existingShop);


    }






    @GetMapping("/logout")
    public String logoutUser(HttpServletResponse response) {

        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0); // delete cookie
        cookie.setPath("/"); // Set the path to match the original cookie's path
        response.addCookie(cookie);

        return "Logged out successfully";
    }

    @GetMapping("/getAllShops")
    public List<Shop> getAll() {
        return shopRepository.findAll();
    }


    @GetMapping("/getShopByID")
    public ResponseEntity<Object> getShopByID(@RequestParam("id") String shopID) {
        Shop shop =  shopRepository.findByShopId(shopID);
        if (shop != null) {
            return ResponseEntity.ok(shop);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop not found");

    }

    @GetMapping("/getShopByEmail")
    public  ResponseEntity<Object>  getShopByEmail(@RequestParam("email") String email) {
        Shop shop =  shopRepository.findByEmail(email);
        if (shop != null) {
            return ResponseEntity.ok(shop);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop not found");

    }





    @PutMapping("/disableShop")
    public ResponseEntity<Object> disableShop(@RequestParam("shopId") String shopId,
                                             @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) throws Exception {



        //LoginAdmin


        if(token.equals("No token found in cookie")){
            System.out.println("Please login to continue");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please login to continue");

        }

        Claims claims = jwtUtils.verify(token);
        if(claims == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token Not Valid");

        }
        User user =  userRepository.findByUserId(claims.getIssuer());

        if(!user.getRole().equals(UserRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You Are Not allowed");

        }


        Shop shopExists = shopRepository
                .findByShopId(shopId);

        if (shopExists == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Show not Found");

        }


        shopExists.setEnabled(false);
        shopRepository.save(shopExists);






        return ResponseEntity.ok("Shop has been enable successfully");




    }

    @PutMapping("/enableShop")
    public ResponseEntity<Object> enableShop(@RequestParam("shopId") String shopId,
                                             @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) throws Exception {



        //LoginAdmin


        if(token.equals("No token found in cookie")){
            System.out.println("Please login to continue");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please login to continue");

        }

        Claims claims = jwtUtils.verify(token);
        if(claims == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token Not Valid");

        }
        User user =  userRepository.findByUserId(claims.getIssuer());

        if(!user.getRole().equals(UserRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You Are Not allowed");

        }


        Shop shopExists = shopRepository
                .findByShopId(shopId);

        if (shopExists == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Show not Found");

        }




        shopExists.setEnabled(true);
        shopRepository.save(shopExists);






        return ResponseEntity.ok("Shop has been disabled successfully");




    }





    @GetMapping("/getcurrentShop")
    public ResponseEntity<Object> getcurrentUser(
            @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) throws Exception {


        if(token.equals("No token found in cookie")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No token found in cookie");
        }
        Claims claims = jwtUtils.verify(token);
        if(claims == null ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token Not valid");
        }


        Shop shop = shopRepository.findByShopId(claims.getIssuer());

        if (shop != null) {
            return ResponseEntity.ok(shop);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token Not valid");
        }


    }












    private static int expiryDurationCookie = 600000 * 60;;

    private void sendTokenToCookie(String user_id , HttpServletResponse response){
        String token_user = jwtUtils.generateJwt(user_id);
        Cookie tokenCookie = new Cookie("token", token_user);
        tokenCookie.setMaxAge(expiryDurationCookie); // Set the cookie's expiration time in seconds
        tokenCookie.setPath("/");
        response.addCookie(tokenCookie);
    }
    private String buildEmail(String name, String information,String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Create a Shop</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please login with this information: \n"+information +"\n</p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Login Now</a> </p></blockquote>\n  <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }


    public void sendSimpleEmail(String toEmail,
                                String body
    ) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(body, true);
            helper.setTo(toEmail);
            helper.setSubject("Create a Shop");
            helper.setFrom("karimamine1544@gmail.com");
            mailSender.send(mimeMessage);
            System.out.println("send email");

        } catch (MessagingException e) {
            System.out.println("failed to send email");
            throw new IllegalStateException("failed to send email");
        }


    }
}
