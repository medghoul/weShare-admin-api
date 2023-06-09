package org.medox.business;


import net.datafaker.Faker;
import org.medox.dao.UtilityDao;
import org.medox.model.Review;
import org.medox.model.Trip;
import org.medox.model.User;

import java.util.Locale;


public class InserimentoDati {

    public static void main(String[] args)throws Exception {
        Faker faker = new Faker(Locale.ITALIAN);
        UtilityDao.initFactory();
        for (int i = 0;
             i < 100;
             i++) {
            User user = new User();
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.bothify("???????????"));
            user.setPhoneNumber(faker.numerify("##########"));
            user.setRating((double) faker.number().numberBetween(1, 5));
            UserManager.saveUser(user);
        }
        UtilityDao.shutdownFactory();
    }

}




