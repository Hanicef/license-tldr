package yasp.group.rest;
import org.json.JSONException;
import org.json.JSONObject;
import yasp.group.entity.License;
import yasp.group.entity.Summary;
import yasp.group.service.Service;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.inject.Inject;
import java.util.List;
import java.util.ArrayList;

@Stateless
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
	//NOT IMPLEMENTED IN DAO
	/*
	@GET
	@Path("/licfromsum")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response getLicensesFromSummary(String sJson) {
		try {
			JSONObject json = new JSONObject(sJson);
			Summary s = service.getSummaryById(json.getInt("id"));
			List<License> result = service.getLicensesFromSummary(s);
			return Response.ok(result).build();
		}catch (EJBException e){
			System.err.println("provided ID does not exist");
			return Response.status(400).build();
		}
	}*/
	@GET
	@Path("/licfromsumId/{id}")
	@Produces("application/json")
	public Response getLicensesFromSummary(@PathParam("id") int id) {
		List<License> result = service.getLicensesFromSummary(id);
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
	@Path("/sumfromlic")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response getSummariesFromLicense(String sJson) {
		try {
			JSONObject json = new JSONObject(sJson);
			License l = service.getLicenseById(json.getInt("id"));
			List<Summary> result = service.getSummariesFromLicense(l);
			return Response.ok(result).build();
		}catch (EJBException e){
			System.err.println("provided ID does not exist");
			return Response.status(400).build();
		}
	}
	@GET
	@Path("/sumfromlicid/{id}")
	@Produces("application/json")
	public Response getSummaryByLicenseId(@PathParam("id") int id) {
		List<Summary> result = service.getSummariesFromLicense(id);
		return Response.ok(result).build();
	}


	//POST
	@POST
	@Path("/createlic")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createLicense(String sJson) {
		try {
			JSONObject json = new JSONObject(sJson);
			License license = new License(json.getString("name"), json.getString("sourceURL"));
			//TODO Search db for existing licence name
			service.createLicense(license);
			return Response.ok().build();
		}catch (JSONException e){
			System.err.println("Invalid json format" + e);
			return Response.status(400).build();
		}
	}

	@POST
	@Path("/createsum")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createSummary(String sJson) {
		try {
			JSONObject json = new JSONObject(sJson);
			Summary summary = new Summary(json.getString("name"),json.getString("description"));
			//TODO Search db for existing summary name
			service.createSummary(summary);
			return Response.ok().build();
		}catch (JSONException e){
			System.err.println("Invalid json format" + e);
			return Response.status(400).build();
		}
	}
	@GET
	@Path("/connectlicsum/{lid}/{sid}")
	public Response addSummaryToLicense(@PathParam("lid") int lId,
										@PathParam("sid") int sId) {
		try {
			License license = service.getLicenseById(lId);
			Summary summary = service.getSummaryById(sId);
			service.addSummaryToLicense(license, summary);
			return Response.ok().build();
		}catch (Exception e){
			System.err.println("Invalid argument"+e);
			return Response.status(400).build();
		}
	}



	//PUT
	@PUT
	@Path("/editlicense/{license}")
	@Consumes("application/json")
	public Response editLicense(@PathParam("license") JSONObject json){
		License l = service.getLicenseById(json.getInt("id"));
		l.setName(json.getString("name"));
		l.setSourceURL(json.getString("sourceURL"));
		service.applyLicenseChanges(l);
		return Response.ok().build();
	}
	@PUT
	@Path("/editsummary/{summary}")
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


	//TestMethod
	@POST
	@Path("/test")
	@Produces("application/json")
	public Response createTest() {
		Summary summary = new Summary("TestSummary", "Ipsum Testum");
		License license = new License("TestLicence","http://www.Thisgoesnowhere.now");
		service.createSummary(summary);
		service.createLicense(license);
		service.addSummaryToLicense(license,summary);
		return Response.ok(license).build();
	}
}
