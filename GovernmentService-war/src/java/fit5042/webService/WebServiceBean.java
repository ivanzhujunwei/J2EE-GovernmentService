/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.webService;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@Named(value = "webServiceBean")
@SessionScoped
public class WebServiceBean implements Serializable
{

    private String serviceTemplateHTML;

    GovernmentServiceResourceClient gsrclient;
    
    /**
     * Creates a new instance of WebServiceBean
     */
    public WebServiceBean()
    {
    }

    public String getServiceTemplateHTML()
    {
        gsrclient = new GovernmentServiceResourceClient();
        return gsrclient.getHtml();
    }

    public void setServiceTemplateHTML(String serviceTemplateHTML)
    {
        this.serviceTemplateHTML = serviceTemplateHTML;
    }


    static class GovernmentServiceResourceClient
    {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:44043/GovernmentService-war/webresources";

        public GovernmentServiceResourceClient()
        {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("GovernmentService");
        }

        public String getHtml() throws ClientErrorException
        {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
        }

        public void putHtml(Object requestEntity) throws ClientErrorException
        {
            webTarget.request(javax.ws.rs.core.MediaType.TEXT_HTML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.TEXT_HTML));
        }

        public void close()
        {
            client.close();
        }
    }

}
