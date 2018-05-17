package yasp.group.rest;
import org.json.JSONObject;
import yasp.group.entity.License;
import yasp.group.entity.LicenseSummary;
import yasp.group.entity.Summary;
import yasp.group.service.Service;

import javax.ejb.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.inject.Inject;
import java.util.List;
import java.util.ArrayList;

@Singleton
@Path("/licenses")
public class LicenceResource {
	@Inject
	private Service service;

	//Licences
	@GET
	@Path("/license")
	@Produces("application/json")
	public Response getAllLicense(){
		List<License> result = service.getAllLicenses();
			return Response.ok(result).build();
	}
	@GET
	@Path("/license/{id}")
    @Produces("application/json")
	public Response getLicenseById(@PathParam("id") int id) {
		License result = service.getLicenseById(id);
		return Response.ok(result).build();
	}



	//Summaries
	@GET
	@Path("/summary")
	@Produces("application/json")
	public Response getAllSummary(){
		List<Summary> result = service.getAllSummaries();
		return Response.ok(result).build();
	}
	@GET
	@Path("/summary/{id}")
    @Produces("application/json")
	public Response getSummaryById(@PathParam("id") int id) {
		Summary result = service.getSummaryById(id);
		return Response.ok(result).build();
	}
	@GET
	@Path("/sumFromLic/{id}")
    @Produces("application/json")
	public Response getSummaryFromLicense(@PathParam("id") int id) {
		License l = service.getLicenseById(id);
		List<Summary> result = service.getSummaryFromLicense(l);
		return Response.ok(result).build();
	}

	//POST
	@POST
	@Path("/license/{createLicense}")
    @Consumes("application/json")
	public Response createLicense(@PathParam("createLicense")JSONObject json) {
		License license = new License(json.getString("name"),json.getString("sourceURL"));
		service.createLicense(license);
		return Response.ok().build();
	}

	@POST
	@Path("/summary/{createSummary}")
    @Consumes("application/json")
	public Response createSummary(@PathParam("createSummary")JSONObject json) {
		Summary summary = new Summary(json.getString("name"),json.getString("description"));
		service.createSummary(summary);
		return Response.ok().build();
	}


	//PUT
    @PUT
    @Path("/editLicense/{license}")
    @Consumes("application/json")
    public Response editLicense(@PathParam("license") JSONObject json){
	    License l = service.getLicenseById(json.getInt("id"));
	    l.setName(json.getString("name"));
	    l.setSourceURL(json.getString("sourceURL"));
	    service.applyLicenseChanges(l);
	    return Response.ok().build();
    }
    @PUT
    @Path("/editSummary/{summary}")
    @Consumes("application/json")
    public Response editSummary(@PathParam("summary") JSONObject json){
        Summary s = service.getSummaryById(json.getInt("id"));
        s.setName(json.getString("name"));
        s.setDescription(json.getString("description"));
        service.applySummaryChanges(s);
        return Response.ok().build();
    }


    //DELETE
    @DELETE
    @Path("/del/li/{id}")
    public Response deleteLicense(@PathParam("id") int id){
	    service.deleteLicense(id);
	    return Response.ok().build();
    }
    @DELETE
    @Path("/del/su/{id}")
    public Response deleteSummary(@PathParam("id") int id){
        service.deleteSummary(id);
        return Response.ok().build();
    }
    @DELETE
    @Path("/del/lisu/{id}")
    public Response deleteLicenseSummary(@PathParam("id") int id){
        service.deleteLicenseSummary(id);
        return Response.ok().build();
    }



	//TestMethod
	@POST
	@Path("/test")
    @Produces("application/json")
	public Response createTest() {
		Summary summary = new Summary("TestSummary", "Ipsum Testum");
		License license = new License("TestLicence","http://www.Thisgoesnowhere.now");
		service.createSummary(summary);
		List<Summary> sum = new ArrayList<>();
		sum.add(summary);
		service.createLicenseFromSummaries(license, sum);
		return Response.ok(license).build();
	}
}
