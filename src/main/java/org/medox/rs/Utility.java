package org.medox.rs;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Utility {
    public static Response buildOkResponse(Object entity) {
        return Response
                .status(Response.Status.OK)
                .entity(entity)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response buildBadRequestResponse(Object entity) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(entity)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response buildNoContentResponse() {
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    public static Response buildNotFoundResponse() {
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    public static Response buildServerError(String message) {
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(message)
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
    public static Response buildUnauthorizedRequest(String message) {
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(message)
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
    public static Response bulidStringResponse(String message) {
        return Response
                .status(Response.Status.OK)
                .entity(message)
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
