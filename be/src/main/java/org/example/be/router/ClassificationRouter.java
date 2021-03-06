package org.example.be.router;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.example.be.model.json.ClassificationGetAllResponse;
import org.example.be.model.json.ClassificationGetByIdResponse;
import org.example.be.model.json.ClassificationRequest;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Tag(name = "Image classification API", description = "Endpoints regarding image classifications")
@Path("/api/v1/image-classification")
public interface ClassificationRouter {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Read all image classifications")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    name = "success",
                    description = "Success",
                    content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = ClassificationGetAllResponse.class, type = SchemaType.ARRAY))
            ),
            @APIResponse(
                    responseCode = "500",
                    name = "timeout",
                    description = "Internal Server Error"
            )
    })
    Response readAll();

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Operation(summary = "Read one image classification")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    name = "success",
                    description = "Success",
                    content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = ClassificationGetByIdResponse.class))
            ),
            @APIResponse(
                    responseCode = "400",
                    name = "error",
                    description = "Bad request"
            ),
            @APIResponse(
                    responseCode = "404",
                    name = "not found",
                    description = "Not found"
            ),
            @APIResponse(
                    responseCode = "500",
                    name = "timeout",
                    description = "Internal Server Error"
            )
    })
    Response readOne(@PathParam("id") UUID id);

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create image classification")
    @APIResponses({
            @APIResponse(
                    responseCode = "201",
                    name = "success",
                    description = "Success"
            ),
            @APIResponse(
                    responseCode = "400",
                    name = "error",
                    description = "Bad request"
            ),
            @APIResponse(
                    responseCode = "500",
                    name = "internalError",
                    description = "Internal Server Error"
            )
    })
    Response create(@RequestBody(
            description = "Image classification request",
            required = true,
            content = @Content(schema = @Schema(implementation = ClassificationRequest.class))) @Valid @MultipartForm ClassificationRequest request);

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Operation(summary = "Delete one image classification")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    name = "success",
                    description = "No content"
            ),
            @APIResponse(
                    responseCode = "400",
                    name = "error",
                    description = "Bad request"
            ),
            @APIResponse(
                    responseCode = "404",
                    name = "not found",
                    description = "Not found"
            ),
            @APIResponse(
                    responseCode = "500",
                    name = "timeout",
                    description = "Internal Server Error"
            )
    })
    Response delete(@PathParam("id") UUID id);

}
