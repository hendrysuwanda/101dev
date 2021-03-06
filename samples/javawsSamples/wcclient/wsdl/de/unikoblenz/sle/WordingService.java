
package de.unikoblenz.sle;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "WordingService", targetNamespace = "http://sle.unikoblenz.de/", wsdlLocation = "http://localhost:8080/wcservice/Wording?wsdl")
public class WordingService
    extends Service
{

    private final static URL WORDINGSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(de.unikoblenz.sle.WordingService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = de.unikoblenz.sle.WordingService.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:8080/wcservice/Wording?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:8080/wcservice/Wording?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        WORDINGSERVICE_WSDL_LOCATION = url;
    }

    public WordingService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WordingService() {
        super(WORDINGSERVICE_WSDL_LOCATION, new QName("http://sle.unikoblenz.de/", "WordingService"));
    }

    /**
     * 
     * @return
     *     returns Wording
     */
    @WebEndpoint(name = "WordingPort")
    public Wording getWordingPort() {
        return super.getPort(new QName("http://sle.unikoblenz.de/", "WordingPort"), Wording.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Wording
     */
    @WebEndpoint(name = "WordingPort")
    public Wording getWordingPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://sle.unikoblenz.de/", "WordingPort"), Wording.class, features);
    }

}
