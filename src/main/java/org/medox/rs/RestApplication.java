package org.medox.rs;


import org.medox.dao.UtilityDao;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classi = new HashSet<>();
        classi.add(GenericExceptionMapper.class);
        classi.add(UserResource.class);
        classi.add(JacksonConfiguration.class);
        classi.add(ResponseHeaderFilter.class);
        UtilityDao.initFactory();

        return classi;
    }
}
