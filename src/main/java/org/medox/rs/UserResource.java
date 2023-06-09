package org.medox.rs;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.datafaker.Faker;
import org.json.JSONArray;
import org.json.JSONObject;
import org.medox.business.UserManager;
import org.medox.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Path("/users")
public class UserResource {
    private static final String SECRET_KEY = "4D6251655468576D5A7133743677397A24432646294A404E635266556A586E32";
    Faker faker = new Faker(Locale.ITALIAN);

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id) {
        return Utility.buildOkResponse(UserManager.getUser(id));
    }

    @POST
    @Consumes("application/json")
    public Response insertUser(User user) {
        return Utility.buildOkResponse(UserManager.saveUser(user));
    }

    @POST
    @Path("/login")
    @Consumes("application/json")
    public Response getUserByEmailPassword(User user1) {
        User user = UserManager.getUserByEmailPassword(user1.getEmail(), user1.getPassword());
        if (user != null) {
            // Genera il token JWT
            String token = Jwts.builder()
                               .setSubject(user.getFirstName())
                               .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // scade dopo 24 ore
                               .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                               .compact();
            // Restituisci il token al client
            System.out.println(token);
            JSONObject json = new JSONObject();
            json.put("id", user.getId());
            json.put("token", token);
            json.put("message", "Welcome back");
            return Utility.buildOkResponse(json.toString());
        } else {
            // Restituisci un errore di autenticazione se le credenziali non sono valide
            return Utility.buildUnauthorizedRequest("Credenziali non valide");
        }
    }

    @POST
    @Path("/login/forgot")
    @Consumes("application/json")
    public Response findUserByEmail(String json) throws Exception {
        JSONObject jsonObj = new JSONObject(json);
        String email = jsonObj.getString("email");
        User user = UserManager.findUserByEmail(email);
        if (user != null) {
            Font font = new Font("LucidaSans", Font.BOLD, 14);
            AttributedString OTP1 = new AttributedString(faker.numerify("######"));
            String OTP = faker.numerify("######");
            String message = "Verify your email address\n" +
                    "\n" +
                    "Dear " + user.getFirstName() + " " + user.getLastName() + ",\n" +
                    "\n" +
                    "A request to reset your password or unlock your account was made for your mail address," + email + ". To continue with this request, please enter the code below on the verification page:\n" +
                    "\n" + OTP +
                    "\n" +
                    "If you did not make this change or you believe an unauthorised person has accessed your account, please verify your account information is accurate and up-to-date.\n" +
                    "\n" +
                    "Sincerely,\n" +
                    "\n" +
                    "We share Support";
            new GMailer().sendMail("Reset password", message, email);
            String text = "Hello, world!";
            new GMailer().sendMail("Test", text, "wajdikhelifi06@gmail.com");
            JSONObject json2 = new JSONObject();
            json2.put("id", user.getId());
            json2.put("OTP", OTP);
            return Utility.buildOkResponse(json2.toString());
        }
        return Utility.buildNotFoundResponse();
    }

    @PUT
    @Path("/login/forgot")
    @Consumes("application/json")
    public Response resetPassword(String json) {
        JSONObject jsonObj = new JSONObject(json);
        Long id = jsonObj.getLong("id");
        String password = jsonObj.getString("password");
        UserManager.resetPassword(id, password);
        return Utility.buildNoContentResponse();
    }

}
