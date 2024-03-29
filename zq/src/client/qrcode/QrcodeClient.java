
package client.qrcode;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import javax.xml.namespace.QName;
import org.codehaus.xfire.XFireRuntimeException;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbTypeRegistry;
import org.codehaus.xfire.service.Endpoint;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.soap.AbstractSoapBinding;
import org.codehaus.xfire.transport.TransportManager;

public class QrcodeClient {

    private static XFireProxyFactory proxyFactory = new XFireProxyFactory();
    private HashMap endpoints = new HashMap();
    private Service service0;

    public QrcodeClient() {
        create0();
        Endpoint QrcodeHttpPortEP = service0 .addEndpoint(new QName("http://qrcode.service.infogiga.cn", "QrcodeHttpPort"), new QName("http://qrcode.service.infogiga.cn", "QrcodeHttpBinding"), "http://localhost:8888/online/service/Qrcode");
        endpoints.put(new QName("http://qrcode.service.infogiga.cn", "QrcodeHttpPort"), QrcodeHttpPortEP);
        Endpoint QrcodePortTypeLocalEndpointEP = service0 .addEndpoint(new QName("http://qrcode.service.infogiga.cn", "QrcodePortTypeLocalEndpoint"), new QName("http://qrcode.service.infogiga.cn", "QrcodePortTypeLocalBinding"), "xfire.local://Qrcode");
        endpoints.put(new QName("http://qrcode.service.infogiga.cn", "QrcodePortTypeLocalEndpoint"), QrcodePortTypeLocalEndpointEP);
    }

    public Object getEndpoint(Endpoint endpoint) {
        try {
            return proxyFactory.create((endpoint).getBinding(), (endpoint).getUrl());
        } catch (MalformedURLException e) {
            throw new XFireRuntimeException("Invalid URL", e);
        }
    }

    public Object getEndpoint(QName name) {
        Endpoint endpoint = ((Endpoint) endpoints.get((name)));
        if ((endpoint) == null) {
            throw new IllegalStateException("No such endpoint!");
        }
        return getEndpoint((endpoint));
    }

    public Collection getEndpoints() {
        return endpoints.values();
    }

    private void create0() {
        TransportManager tm = (org.codehaus.xfire.XFireFactory.newInstance().getXFire().getTransportManager());
        HashMap props = new HashMap();
        props.put("annotations.allow.interface", true);
        AnnotationServiceFactory asf = new AnnotationServiceFactory(new Jsr181WebAnnotations(), tm, new AegisBindingProvider(new JaxbTypeRegistry()));
        asf.setBindingCreationEnabled(false);
        service0 = asf.create(QrcodePortType.class, props);
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://qrcode.service.infogiga.cn", "QrcodePortTypeLocalBinding"), "urn:xfire:transport:local");
        }
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://qrcode.service.infogiga.cn", "QrcodeHttpBinding"), "http://schemas.xmlsoap.org/soap/http");
        }
    }

    public QrcodePortType getQrcodeHttpPort() {
        return ((QrcodePortType)(this).getEndpoint(new QName("http://qrcode.service.infogiga.cn", "QrcodeHttpPort")));
    }

    public QrcodePortType getQrcodeHttpPort(String url) {
        QrcodePortType var = getQrcodeHttpPort();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public QrcodePortType getQrcodePortTypeLocalEndpoint() {
        return ((QrcodePortType)(this).getEndpoint(new QName("http://qrcode.service.infogiga.cn", "QrcodePortTypeLocalEndpoint")));
    }

    public QrcodePortType getQrcodePortTypeLocalEndpoint(String url) {
        QrcodePortType var = getQrcodePortTypeLocalEndpoint();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public static void main(String[] args) {
        

        QrcodeClient client = new QrcodeClient();
        
		//create a default service endpoint
        QrcodePortType service = client.getQrcodeHttpPort();
        service.isNew("3301B001A2B00002", "GA01100", "ZMCC_GE_AM01b01M", "3301B001A2", "Template_G00001");
		//TODO: Add custom client code here
        		//
        		//service.yourServiceOperationHere();
        
		System.out.println("test client completed");
        		System.exit(0);
    }

}
