package com.volusion.service.rest.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.volusion.service.rest.v1.dto.OrderDto;

/**
 * Resource endpoint to handle order object requests.
 * @author shankar meenkeri
 * 
 */
@Path("/v1/orders")
public interface OrderResource {

    /**
     * Gets a order by it's id
     * 
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrder(@PathParam("id") Long id);
    
    /**
     * Creates a order record based on the data passed in.  Returns the id of the record.
     * @param orderDto
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(OrderDto orderDto);
    
    /**
     * Updates a order record based on the data passed in. Returns the id of the record.
     * @param orderDto
     * @return The id of the updated record
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrder(OrderDto orderDto);
    
    /**
     * Delete a order record by it's id
     * @param id
     */
    @DELETE
    @Path("/{id}")
    public Response deleteOrder(@PathParam("id") Long id);
}
