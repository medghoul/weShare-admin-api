package org.medox.rs;


import org.medox.exception.GenericException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class GenericExceptionMapper implements ExceptionMapper<GenericException> {
    @Override
    public Response toResponse(GenericException e) {
        return Utility.buildServerError(e.getMessage());
    }
}
