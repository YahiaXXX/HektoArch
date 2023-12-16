package com.example.msauth.API;


import com.example.msauth.Proxies.ShoppingCartProxy;
import com.example.msauth.Registration.EmailValidator;
import com.example.msauth.Registration.JwtUtils;
import com.example.msauth.entities.Token;
import com.example.msauth.entities.User;
import com.example.msauth.entities.UserLogin;
import com.example.msauth.entities.UserRole;
import com.example.msauth.repository.ConfirmationTokenRepository;
import com.example.msauth.repository.UserRepository;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/user")
@CrossOrigin(origins = "http://localhost:3000")

public class UserController {

    private  EmailValidator emailValidator;
    @Autowired
    private  UserRepository userRepository;


    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;


    @Autowired
    private JavaMailSender mailSender;


    @Autowired
    private JwtUtils jwtUtils;


    @Autowired
    private ShoppingCartProxy shoppingCartProxy;

    private static int expiryDurationCookie = 600000 * 60;;


    public UserController(EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
       // this.passwordEncoder = new BCryptPasswordEncoder();

    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody User request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            return "email not valid";

        }


        User userExists = userRepository
                .findByEmail(request.getEmail());

        if (userExists != null) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            return "email already taken";

        }
        String userId = UUID.randomUUID().toString();


        User user  = new User(userId, request.getName(),
                request.getEmail(),request.getPassword(),false, UserRole.USER);

//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);

        userRepository.save(user);



        String token = UUID.randomUUID().toString();

        Token confirmationToken = new Token(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                userId
        );



        confirmationTokenRepository.save(confirmationToken);
        String link = "http://localhost:8010/v1/api/user/confirm?token=" + token;


        sendSimpleEmail(user.getEmail() , buildEmail(user.getName(), link));






        return "Please Check Your E-mail " + user.getEmail();


    }



    @GetMapping(path = "confirm")
    public String activateUser(@RequestParam("token") String token , HttpServletResponse response) {
        System.out.println("azkfpazokfkoazpfa");
        Token confirmationToken = confirmationTokenRepository
                .findByToken(token);
        if (confirmationToken == null) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            return "Token not found";

        }
        if (confirmationToken.getConfirmedAt() != null) {
            return "email already confirmed";

        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            return "token expired";

        }

            confirmationTokenRepository.updateConfirmedAt(
                    token, LocalDateTime.now());


        userRepository.enableAppUser(confirmationToken.getUser_id());


        String token_user = jwtUtils.generateJwt(confirmationToken.getUser_id());


        sendTokenToCookie(token_user, response);

        //ShoppingCart is created Automatically when the user account is confirmed
        //shoppingCartProxy.createShoppingCart(token);
        //***********************************************
        return token_user;


    }




    @PostMapping("/login")
    public String loginUser(@RequestBody UserLogin request , HttpServletResponse response ) {

        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            return "email not valid";

        }



        User user = userRepository
                .findByEmail(request.getEmail());

        if (user == null) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            return "email does not exist";
        }

        if(!user.getPassword().equals(request.getPassword())){
            return "Wrong Password";
        }
        if(!user.isEnabled()){


            Token token =
                    confirmationTokenRepository.findByUserId(user.getUserId());

            if(token.getExpiresAt().isAfter(LocalDateTime.now())){
                return "Your E-mail is not active , Please Check Your E-mail " ;

            }else {

                String token_id = UUID.randomUUID().toString();

                Token confirmationToken = new Token(
                        token_id,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(15),
                        user.getUserId()
                );
                confirmationTokenRepository.save(confirmationToken);
                String link = "http://localhost:8010/v1/api/user/confirm?token=" + token_id;


                sendSimpleEmail(user.getEmail() , buildEmail(user.getName(), link));

                return "Your E-mail is not active , Please Check Your E-mail " + user.getEmail();
            }







        }



        String token_user = jwtUtils.generateJwt(user.getUserId());


        sendTokenToCookie(token_user, response);


        return token_user;
    }



    @GetMapping("/logout")
    public String logoutUser(HttpServletResponse response) {

        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0); // delete cookie
        cookie.setPath("/"); // Set the path to match the original cookie's path
        response.addCookie(cookie);

        return "Logged out successfully";
    }

    @GetMapping("/getAllUsers")
    public List<User> getAll() {
        return userRepository.findAll();
    }


    @GetMapping("/getUserByID")
    public ResponseEntity<Object> getUserByID(@RequestParam("id") String usedId) {
        User user = userRepository.findByUserId(usedId);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");


    }

    @GetMapping("/getUserByEmail")
    public ResponseEntity<User> getUserByEmail(@RequestParam("email") String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return null;
    }


    @GetMapping("/getcurrentUser")
    public ResponseEntity<Object> getcurrentUser(
            @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) throws Exception {


        if(token.equals("No token found in cookie")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No token found in cookie");
        }
        Claims claims = jwtUtils.verify(token);
        if(claims == null ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token Not valid");
        }


        User user = userRepository.findByUserId(claims.getIssuer());

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token Not valid");
        }


    }

    /*
     @GetMapping("/getcurrentUser")
    public ResponseEntity<Object> getcurrentUser(
            @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) throws Exception {

        System.out.println("Token//////////////" + token);

        if(token.equals("No token found in cookie")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No token found in cookie");
        }
        Claims claims = jwtUtils.verify(token);
        if(claims == null ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token Not valid");
        }

        User user = userRepository.findByUserId(claims.getIssuer());
        System.out.println(user);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token Not valid");
        }


    }
     */

    @PutMapping("/updateUser")
    public User updateUser( @RequestParam("id") String usedId,@RequestBody UserLogin updatedUser) {
        // Retrieve the existing user from the repository
        User existingUser = userRepository.findByUserId(usedId);

        if (existingUser != null) {
            // Update the attributes of the existing user with the new values
            if(updatedUser.getName()!=null){
                existingUser.setName(updatedUser.getName());

            }
            if(updatedUser.getPassword()!=null){
                existingUser.setPassword(updatedUser.getPassword());

            }


            // Save the updated user in the repository
            return userRepository.save(existingUser);
        }

        return null; // Or throw an exception indicating the user was not found
    }
















    private void sendTokenToCookie(String token , HttpServletResponse response){
        Cookie tokenCookie = new Cookie("token", token);
        tokenCookie.setMaxAge(expiryDurationCookie); // Set the cookie's expiration time in seconds
        tokenCookie.setPath("/");
        response.addCookie(tokenCookie);
    }

    private String buildEmail(String name, String link) {
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
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
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
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
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
            helper.setSubject("Confirm your email");
            helper.setFrom("karimamine1544@gmail.com");
            mailSender.send(mimeMessage);
            System.out.println("send email");

        } catch (MessagingException e) {
            System.out.println("failed to send email");
            throw new IllegalStateException("failed to send email");
        }


    }

}
