//package br.com.brasil01.manter;

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


public class Main implements Serializable {

	private static final long serialVersionUID = 1L;

	public Main() {

	}

	public static void main(String[] args) throws Exception {
		// tpAmb 1= Hom, 2 = Prod
		//String soapRequest = "<SOAP_REQUEST><?xml version=\"1.0\" encoding=\"utf-8\"?><soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"><soap12:Header><nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2\"><cUF>35</cUF><versaoDados>3.10</versaoDados></nfeCabecMsg></soap12:Header><soap12:Body><nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2\"><consSitNFe versao=\"3.10\" xmlns=\"http://www.portalfiscal.inf.br/nfe\"><tpAmb>2</tpAmb><xServ>CONSULTAR</xServ><chNFe>35150800767378000115550010008336911107071318</chNFe></consSitNFe></nfeDadosMsg></soap12:Body></soap12:Envelope></SOAP_REQUEST>";
		//String soapRequest = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"><soap12:Header><nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2\"><cUF>35</cUF><versaoDados>3.10</versaoDados></nfeCabecMsg></soap12:Header><soap12:Body><nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2\"><consSitNFe versao=\"3.10\" xmlns=\"http://www.portalfiscal.inf.br/nfe\"><tpAmb>2</tpAmb><xServ>CONSULTAR</xServ><chNFe>35150800767378000115550010008336911107071318</chNFe></consSitNFe></nfeDadosMsg></soap12:Body></soap12:Envelope>";
		String soapRequest = 
							"<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"+
							   "<soap12:Header>"+
							      "<nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2\" versao=\"2.00\">"+
							         "<cUF>35</cUF>"+
							         "<versaoDados>3.10</versaoDados>"+
							      "</nfeCabecMsg>"+
							   "</soap12:Header>"+
							   "<soap12:Body>"+
							      "<nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2\">"+
							         "<consSitNFe versao=\"3.10\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">"+
							            "<tpAmb>2</tpAmb>"+
							            "<xServ>CONSULTAR</xServ>"+
							            "<chNFe>35150800767378000115550010008336911107071318</chNFe>"+
							         "</consSitNFe>"+
							      "</nfeDadosMsg>"+
							   "</soap12:Body>"+
							"</soap12:Envelope>";

		
		String pathURL = "https://nfe.fazenda.sp.gov.br/ws/nfeconsulta2.asmx"; // prod
		//String pathURL = "https://homologacao.nfe.fazenda.sp.gov.br/ws/nfeconsulta2.asmx"; // HML

		//String method = "POST";
		//String acceptEncoding = "gzip,deflate";
		//String contentType = "application/soap+xml;charset=UTF-8;action=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2/nfeConsultaNF2\"";
		//String userAgent = "";
		//String soapAction = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2";
		//String host = "nfe.fazenda.sp.gov.br";

		//tring keyStoreType = "pkcs12";
		//String keyStore = "/home/oracle/osb/certificado/alphaville/cacerts";
		//String keyStorePassword = "changeit";
		//String trustStoreType = "jks";
		//String trustStore = "/home/oracle/osb/certificado/alphaville/cacerts";
		//String trustStorePassword = "changeit";

		//String keyStoreType = "JKS";
		//String keyStore = "/home/oracle/osb/certificado/alphaville/alphaville.ks";
		//String keyStorePassword = "ti_alphaville";
		//String trustStoreType = "JKS";
		//String trustStore = "/home/oracle/osb/certificado/alphaville/NFeCacerts";
		//String trustStorePassword = "changeit";

		String keyStoreType = "JKS";
		String keyStore = "C:/Temp/certificado/alphaville.ks";
		String keyStorePassword = "ti_alphaville";
		String trustStoreType = "JKS";
		String trustStore = "C:/Temp/certificado/NFeCacerts";
		String trustStorePassword = "changeit";

		//String unsafeRenegotiation = "X";
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

			URL urltransp = new URL(pathURL);
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
