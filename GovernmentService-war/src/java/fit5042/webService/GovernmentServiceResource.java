/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.webService;

import fit5042.repository.ServiceStorageBean;
import fit5042.repository.entities.Service;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@Path("GovernmentService")
public class GovernmentServiceResource
{

    @Context
    private UriInfo context;
    
    @EJB
    ServiceStorageBean serviceStorageBean;

    /**
     * Creates a new instance of GovernmentServiceResource
     */
    public GovernmentServiceResource()
    {
    }

    /**
     * Retrieves representation of an instance of fit5042.webService.GovernmentServiceResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHtml()
    {
        StringBuilder sb = new StringBuilder("{");
        for (Service s: serviceStorageBean.getServiceTemplates()){
            sb.append("\""+s.getType()+"\":");
            sb.append("{");
            sb.append("\"type\":").append("\"").append(s.getType()).append("\",");
            sb.append("\"description\":").append("\"").append(s.getDescription()).append("\"");
            sb.append("},");
        }
        String json = sb.toString();
        System.out.println("----------"+json);
        json = json.substring(0, json.length()-1);
        json = json +  "}";
//            sb.append("{\"insurance\":{\"type\":\"insurance\",\"description\":\"insurance desc\"},\""
//                    + ""
//                    + "child care\":{\"type\":\"child care\",\"description\":\"child care desc\"}}");
        System.out.println("----------"+json);
//        {
//	"insurance":
//				{
//					"type":"insurance",
//					"description":"insurance desc"
//				},
//	"child care":
//				{
//					"type":"child care",
//					"description":"child care desc"
//				}			
//	
//}
        return json;
    }

    /**
     * PUT method for updating or creating an instance of GovernmentServiceResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content)
    {
    }
}
