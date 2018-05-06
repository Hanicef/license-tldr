package yasp.group.rest;
import yasp.group.entity.License;
import yasp.group.service.LicenseNotFoundException;
import yasp.group.service.Service;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Path("/licences")
public class LicenceResource {
	@Inject
	private Service service;

	@GET
	@Produces("application/json")
	public Response getAllLicense(){
		List<License> result = service.getAllLicenses();
			return Response.ok(result).build();
	}
/*
	@GET
	@Produces("application/json")
	@Path("{licenseId}")
	public Response getById(@PathParam("licenseId") int id) {
		try{
			License result = service.getAllLicenses(id);
			return Response.ok(result).build();
		}catch (LicenseNotFoundException e){
			return Response.status(404).build();
		}
	}

	@GET
	@Produces("application/json")
	@Path("{licenseName}")
	public Response getByName(@PathParam("licenseName") int id) {
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
	}*/
}
