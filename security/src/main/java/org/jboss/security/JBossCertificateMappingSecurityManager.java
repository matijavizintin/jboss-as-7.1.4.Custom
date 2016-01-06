package org.jboss.security;

/**
 *  Security manager for certificate subject mapping
 *
 *  @author <a href="matija.vizintin@gmail.com">Matija Vižintin</a>
 */
public class JBossCertificateMappingSecurityManager implements BaseSecurityManager {
    private String name;
    private String mappingClassName;
    private CertificatePrincipal certificatePrincipalMapping;

    public JBossCertificateMappingSecurityManager(String securityDomainName) {
        this.name = securityDomainName;
    }

    public String getSecurityDomain() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getMappingClassName() {
        return mappingClassName;
    }

    public void setMappingClassName(String mappingClassName) {
        this.mappingClassName = mappingClassName;
    }

    public CertificatePrincipal getCertificatePrincipalMapping() {
        return certificatePrincipalMapping;
    }

    public void setCertificatePrincipalMapping(CertificatePrincipal certificatePrincipalMapping) {
        this.certificatePrincipalMapping = certificatePrincipalMapping;
    }

    public void processCertificateMapping() throws Exception {
        ClassLoader loader = SecurityActions.getContextClassLoader();
        Class clazz = loader.loadClass(mappingClassName);
        certificatePrincipalMapping = (CertificatePrincipal) clazz.newInstance();
    }
}
