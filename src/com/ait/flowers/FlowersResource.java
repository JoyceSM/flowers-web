package com.ait.flowers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/flowers")
public class FlowersResource {

	FlowersDao dao = new FlowersDao();

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Flowers> findAll() {
		System.out.println("findAll");// ok
		return dao.findAll();

	}

	@GET
	@Path("{product_id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Flowers findById(@PathParam("product_id") String id) {// ok - 1004
		return dao.findById(Integer.parseInt(id));

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Flowers create(Flowers product) {
		System.out.println("Creating product");// ok - add 1011
		return dao.create(product);

	}

	@PUT
	@Path("{product_id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Flowers update(Flowers product, @PathParam("product_id") int id) {
		System.out.println("Updating product: " + product.getProductName());
		product.setProductId(id);// ok - update 1011
		dao.update(product);
		return product;

	}

	@DELETE
	@Path("{product_id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void remove(@PathParam("product_id") int id) {//ok-1001
		dao.remove(id);
	}

	@GET
	@Path("searchByDescription/{query}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Flowers> findByDescription(@PathParam("query") String query) {
		System.out.println("findByDescription: " + query);//ok
		return dao.findByDescription(query);
	}
	
	@GET
	@Path("searchByOccasion/{query}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Flowers> findByOccasion(@PathParam("query") String query) {
		System.out.println("findByOccasion " + query);//ok
		return dao.findByOccasion(query);
	}

}
