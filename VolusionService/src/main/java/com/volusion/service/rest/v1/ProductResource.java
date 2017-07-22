package com.volusion.service.rest.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.volusion.service.rest.v1.dto.ProductDto;

/**
 * Resource endpoint to handle product object requests.
 * @author shankar meenkeri
 *
 */
@Path("/v1/products")
public interface ProductResource {

    /**
     * Gets a product by it's key.
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getProduct(@PathParam("id") Long id);
    
    /**
     * Creates a product record based on the data passed in.  Returns the id of the record.
     * @param sampleDto
     * @return The id of the new record
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(ProductDto sampleDto);
    
}
