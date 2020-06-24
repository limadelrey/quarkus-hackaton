package org.example.ui;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Main {

    @Inject
    Template index;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance index() {
        return index.data("index", null);
    }

}
