package org.jboss.as.security;

import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.PathElement;
import org.jboss.as.controller.SimpleAttributeDefinition;
import org.jboss.as.controller.SimpleResourceDefinition;
import org.jboss.as.controller.registry.ManagementResourceRegistration;
import org.jboss.dmr.ModelNode;
import org.jboss.dmr.ModelType;

/**
 * @author <a href="mailto:matija.vizintin@gmail.com">Matija Vižintin</a>
 */
public class CertMappingResourceDefinition extends SimpleResourceDefinition {
    public static final CertMappingResourceDefinition INSTANCE = new CertMappingResourceDefinition();

    public static final SimpleAttributeDefinition CODE =
            new SimpleAttributeDefinition(Constants.CODE, new ModelNode("SubjectDN"), ModelType.STRING, false);

    private CertMappingResourceDefinition() {
        super(
                PathElement.pathElement(Constants.CERT_MAPPING, Constants.CLASSIC),
                SecurityExtension.getResourceDescriptionResolver(Constants.CERT_MAPPING), CertMappingResourceDefinitionAdd.INSTANCE,
                new SecurityDomainReloadRemoveHandler());
    }

    @Override public void registerAttributes(ManagementResourceRegistration resourceRegistration) {
        resourceRegistration.registerReadWriteAttribute(CODE, null, new SecurityDomainReloadWriteHandler(CODE));
    }

    static class CertMappingResourceDefinitionAdd extends SecurityDomainReloadAddHandler {
        static final CertMappingResourceDefinitionAdd INSTANCE = new CertMappingResourceDefinitionAdd();

        @Override protected void populateModel(ModelNode operation, ModelNode model) throws OperationFailedException {
            CODE.validateAndSet(operation, model);
        }
    }
}
