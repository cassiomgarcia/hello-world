REM -------------------------------------------------------------
REM Renomeia e apaga a keyStore atual
REM -------------------------------------------------------------
copy alphaville.ks alphaville.ks.old
del alphavile.ks

REM -------------------------------------------------------------
REM Gera a keystore
REM -------------------------------------------------------------
keytool -genkey -keyalg RSA -alias alphaville.ks -keystore alphaville.ks -storepass ti_alphaville -validity 360 -keysize 2048 -dname "CN=ALPHAVILLE URBANISMO S A, OU=Fiscal, O=ICP-Brasil,L=SAO PAULO,ST=SP,C=BR"

REM -------------------------------------------------------------
REM chave de NF-e SP
REM -------------------------------------------------------------
keytool -importkeystore -srckeystore alphaville.pfx -srcstoretype pkcs12 -destkeystore alphaville.ks -deststoretype JKS -storepass ti_alphaville

REM -------------------------------------------------------------
REM Chaves Raiz da Sefaz - Devem ser atualizadas quando expiradas
REM Obter no site https://www.fazenda.sp.gov.br/nfe/url_webservices/url_webservices.asp na op��o "Certificados Digitais dos WebServices da SEFAZ/SP"
REM -------------------------------------------------------------
keytool -import -file CertificadosHomologacaoNFe2013.cer -alias CertificadosHomologacaoNFe2013.cer -keystore alphaville.ks -storepass ti_alphaville

keytool -import -file CertificadosProducaoNFe2013.cer -alias CertificadosProducaoNFe2013.cer -keystore alphaville.ks -storepass ti_alphaville

REM -------------------------------------------------------------
REM  Lista os certificados
REM -------------------------------------------------------------
keytool -list -storepass ti_alphaville -keystore alphaville.ks
