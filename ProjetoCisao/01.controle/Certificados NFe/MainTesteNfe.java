import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.security.Security;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPMessage;


public class MainTesteNfe implements Serializable {

	private static final long serialVersionUID = 1L;

	public MainTesteNfe() {

	}

	public static void main(String[] args) throws Exception {
		String soapRequest = "<SOAP_REQUEST><soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Header><nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2\"><cUF>35</cUF><versaoDados>3.10</versaoDados></nfeCabecMsg></soap:Header><soap:Body><nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2\"><consSitNFe versao=\"3.10\" xmlns=\"http://www.portalfiscal.inf.br/nfe\"><tpAmb>1</tpAmb><xServ>CONSULTAR</xServ><chNFe>35150800767378000115550010008336911107071318</chNFe></consSitNFe></nfeDadosMsg></soap:Body></soap:Envelope></SOAP_REQUEST>";
		String url = "https://nfe.fazenda.sp.gov.br/ws/nfeconsulta2.asmx";
		//String method = "POST";
		//String acceptEncoding = "gzip,deflate";
		//String contentType = "application/soap+xml;charset=UTF-8;action=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2/nfeConsultaNF2\"";
		//String userAgent = "";
		//String soapAction = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2";
		//String host = "nfe.fazenda.sp.gov.br";
		//String unsafeRenegotiation = "X";

		//String keyStoreType = "pkcs12";
		//String keyStore = "/home/oracle/osb/certificado/alphaville/cacerts";
		//String keyStorePassword = "changeit";
		//String trustStoreType = "jks";
		//String trustStore = "/home/oracle/osb/certificado/alphaville/cacerts";
		//String trustStorePassword = "changeit";

		String keyStoreType = "pkcs12";
		String keyStore = "/home/oracle/osb/certificado/alphaville/alphaville.ks";
		String keyStorePassword = "ti_alphaville";
		String trustStoreType = "jks";
		String trustStore = "/home/oracle/osb/certificado/alphaville/alphaville.ks";
		String trustStorePassword = "ti_alphaville";

		String retorno = "";

		try {
			System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

			System.clearProperty("javax.net.ssl.keyStore");
			System.clearProperty("javax.net.ssl.keyStorePassword");
			System.clearProperty("javax.net.ssl.trustStore");

			System.setProperty("javax.net.ssl.keyStoreType", keyStoreType);
			System.setProperty("javax.net.ssl.keyStore", keyStore);
			System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);

			System.setProperty("javax.net.ssl.trustStoreType", trustStoreType);
			System.setProperty("javax.net.ssl.trustStore", trustStore);
			System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);  

			URL urltransp = new URL(url);
			MimeHeaders header = new MimeHeaders();
			header.addHeader("Content-Type", "application/soap+xml");

			MessageFactory factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
			SOAPMessage message = factory.createMessage(header,new ByteArrayInputStream(soapRequest.getBytes()));

			System.out.println("message: " + message.toString());
			System.out.println("urltransp: " + urltransp);

			SOAPConnection conSoap = SOAPConnectionFactory.newInstance().createConnection();
			SOAPMessage resWs = conSoap.call(message, urltransp);

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			resWs.writeTo(out);
			retorno = out.toString();
		} catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
			retorno = ex.getMessage();
		}

		System.out.println("RETORNO: "+ retorno);

	}

}
