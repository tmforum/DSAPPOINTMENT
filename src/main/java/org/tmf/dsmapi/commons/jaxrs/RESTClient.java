package org.tmf.dsmapi.commons.jaxrs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import java.util.HashMap;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

@Stateless
public class RESTClient {

    private static Client jaxrsClient;
    private HashMap<String, WebTarget> webResources = new HashMap<String, WebTarget>();

    public void publishEvent(String callbackURL, Object requestEntity) {
        System.out.println("publishEvent " + requestEntity);
        WebTarget webResource = getWebResource(callbackURL);
        Entity entity = Entity.entity(requestEntity, MediaType.APPLICATION_JSON);
        try {
            webResource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publishEvent(String callbackURL, ObjectNode node) {
        System.out.println("publishEvent " + node);
        WebTarget webResource = getWebResource(callbackURL);
        Entity entity = Entity.entity(node, MediaType.APPLICATION_JSON);
        try {
            webResource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Client getJaxrsClient() {
        if (jaxrsClient == null) {
            ObjectMapper mapper = new ObjectMapper().
                    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).
                    configure(DeserializationFeature.WRAP_EXCEPTIONS, true).
                    configure(SerializationFeature.INDENT_OUTPUT, true).
                    configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            JacksonJaxbJsonProvider jacksonProvider = new JacksonJaxbJsonProvider();
            jacksonProvider.setMapper(mapper);
            ClientConfig config = new ClientConfig();
            config.register(jacksonProvider);
            jaxrsClient = ClientBuilder.newClient(config);
            jaxrsClient.register(JacksonFeature.class);
            jaxrsClient.property(ClientProperties.CONNECT_TIMEOUT, 3000);
            jaxrsClient.property(ClientProperties.READ_TIMEOUT, 3000);
        }
        return jaxrsClient;
    }

    // In memory caching, webResources and client are thread safe see jersey doc
    private WebTarget getWebResource(String endpointURL) {
        if (!webResources.containsKey(endpointURL)) {
            WebTarget webResource = getJaxrsClient().target(endpointURL).path("");
            webResources.put(endpointURL, webResource);
        }
        return webResources.get(endpointURL);
    }
}
