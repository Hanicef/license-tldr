package yasp.group.rest;
import yasp.group.entity.License;
import yasp.group.service.LicenseNotFoundException;
import yasp.group.service.Service;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Stateless
@Path("/licences")
public class LicenceResource {
    @Inject
    private Service service;

    @GET
    @Produces("application/json")
    @Path("{licenseId}")
    public Response getById(@PathParam("licenseId") int id) {
        try{
            License result = service.getById(id);
            return Response.ok(result).build();
        }catch (LicenseNotFoundException e){
            return Response.status(404).build();
        }
    }

    @GET
    @Produces("application/json")
    @Path("{licenseName}")
    public Response getById(@PathParam("licenseName") int id) {
        try{
            License result = service.getByName(id);
            return Response.ok(result).build();
        }catch (LicenseNotFoundException e){
            return Response.status(404).build();
        }
    }
    @PUT
    @Path("{edit}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response editLicense (@PathParam("edit") int id, License l) {
        try {
            service.editLicense(id, l.getName(), l.getSummary(), l.getSourceURL());
            return Response.ok(service.getById(id)).build();
        }catch (LicenseNotFoundException e){
            return Response.status(404).build();
        }
    }
}
