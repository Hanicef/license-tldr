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

	@GET
	@Path("/license")
	@Produces("application/json")
	public Response getAllLicense(){
		List<License> result = service.getAllLicenses();
			return Response.ok(result).build();
	}

	@GET
	@Path("/summary")
	@Produces("application/json")
	public Response getAllSummary(){
		List<Summary> result = service.getAllSummaries();
		return Response.ok(result).build();
	}

	@GET
	@Produces("application/json")
	@Path("/license/{licenseId}")
	public Response getLicenseById(@PathParam("licenseId") int id) {
			License result = service.getLicenseById(id);
			return Response.ok(result).build();
	}
	@GET
	@Produces("application/json")
	@Path("/summary/{summaryId}")
	public Response getSummaryById(@PathParam("summaryId") int id) {
		Summary result = service.getSummaryById(id);
		return Response.ok(result).build();
	}

	@GET
	@Produces("application/json")
	@Path("/sumfromlic/{SumFromLicId}")
	public Response getSummaryByLicenseId(@PathParam("SumFromLicId") int id) {
		List<Summary> result = service.getSummariesFromLicense(id);
		return Response.ok(result).build();
	}
	@POST
	@Path("/license/{createLicense}")
	public Response createLicense(@PathParam("createLicense")JSONObject json) {
		License license = new License(json.getString("name"),json.getString("sourceURL"));
		service.createLicense(license);
		return Response.ok().build();
	}

	@POST
	@Consumes("application/json")
	@Path("/summary/{createSummary}")
	public Response createSummary(@PathParam("createSummary")JSONObject json) {
		Summary summary = new Summary(json.getString("name"),json.getString("description"));
		service.createSummary(summary);
		return Response.ok().build();
	}

	//TestMethod
	@POST
	@Produces("application/json")
	@Path("/test")
	public Response createTest() {
		Summary summary = new Summary("TestSummary", "Ipsum Testum");
		License license = new License("TestLicence","http://www.Thisgoesnowhere.now");
		service.createSummary(summary);
		List<Summary> sum = new ArrayList<>();
		sum.add(summary);
		service.createLicenseFromSummaries(license, sum);

		return Response.ok(license).build();
	}

	/*
	@POST
	@Path("{createLicSum}")
	public Response createLicenseSummary(@PathParam("createLicSum")JSONObject json) {
		License license = new License(json.)
		LicenseSummary ls = new LicenseSummary()
		Summary summary = new Summary(json.getString("name"),json.getString("description"));
		service.createSummary(summary);
		return Response.ok().build();
	}
	*/

	/*
	//Necessary?
	@GET
	@Produces("application/json")
	@Path("{SumFromLic}")
	public Response getSummaryByLicense(@PathParam("SumFromLic") License license) {
		List<Summary> result = service.getSummaryFromLicense(license);
		return Response.ok(result).build();
	}
	*/
	/*
	@PUT
	@Path("{editLicense}")
	@Produces("application/json")
	@Consumes("application/json")
	public Response applyLicenseChanges (@PathParam("editLicense") int id) {
			service.applyLicenseChanges(getLicenseById(id));
			return Response.ok(service.getById(id)).build();
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
	*/
}
