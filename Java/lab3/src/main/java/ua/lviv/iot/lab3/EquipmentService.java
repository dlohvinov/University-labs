package ua.lviv.iot.lab3;

import ua.lviv.iot.lab3.model.EngeneeringEquipment;
import ua.lviv.iot.lab3.model.Equipment;
import ua.lviv.iot.lab3.persistence.dao.EngeneeringEquipmentDao;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@Path("/equipments")
public class EquipmentService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EngeneeringEquipmentDao dao;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Equipment createEquipment(final EngeneeringEquipment engeneeringEquipment) {
        dao.persist(engeneeringEquipment);
        return engeneeringEquipment;
    }

    @GET
    @Path("{id}/")
    @Produces (MediaType.APPLICATION_JSON)
    public Equipment readEquipment(@PathParam("id") final Integer id) {
        return dao.findById(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEquipment (final EngeneeringEquipment engeneeringEquipment) {
        dao.update(engeneeringEquipment);
        return Response.ok().build();
    }


    @DELETE
    @Path("{id}/")
    @Produces (MediaType.APPLICATION_JSON)
    public void deleteEquipment(@PathParam("id") final Integer id) {
        dao.remove(id);

    }
}