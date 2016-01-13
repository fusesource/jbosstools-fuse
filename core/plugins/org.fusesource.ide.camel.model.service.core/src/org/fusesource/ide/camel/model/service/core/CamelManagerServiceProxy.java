/******************************************************************************* 
 * Copyright (c) 2011 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.fusesource.ide.camel.model.service.core;

import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.Map;

import org.fusesource.ide.camel.model.service.core.catalog.CamelModel;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author André Dietisheim
 */
public class CamelManagerServiceProxy extends ServiceTracker<ICamelManagerService, ICamelManagerService>
		implements ICamelManagerService {

	private String serviceVersion;
	public CamelManagerServiceProxy(BundleContext context, String serviceVersion) throws InvalidSyntaxException {
		super(
				context,
				context.createFilter(MessageFormat
						.format("(&(objectClass={0})(camel.version={1}))", ICamelManagerService.class.getCanonicalName(), serviceVersion)), null); //$NON-NLS-1$
		this.serviceVersion = serviceVersion;
		open();
	}
	
	public String getServiceVersion() {
		return serviceVersion;
	}

    private ICamelManagerService checkedGetService() throws CamelManagerException {
    	ICamelManagerService service = getService();
		if (service == null) {
			throw new CamelManagerException("Could not acquire Camel Management service"); //$NON-NLS-1$
		}
		return service;
	}

    /* (non-Javadoc)
     * @see org.fusesource.ide.camel.model.service.core.ICamelManagerService#getCamelModel()
     */
    @Override
    public CamelModel getCamelModel() {
    	CamelModel cm = checkedGetService().getCamelModel();
    	cm.setCamelVersion(this.serviceVersion);
    	return cm;
    }
    
    /* (non-Javadoc)
     * @see org.fusesource.ide.camel.model.service.core.ICamelManagerService#getCamelSchemaProvider()
     */
    @Override
    public CamelSchemaProvider getCamelSchemaProvider() {
    	return checkedGetService().getCamelSchemaProvider();
    }

    /* (non-Javadoc)
     * @see org.fusesource.ide.camel.model.service.core.ICamelManagerService#createEndpointUri(java.lang.String, java.util.Map)
     */
    @Override
    public String createEndpointUri(String scheme, Map<String, String> properties) throws URISyntaxException {
    	return checkedGetService().createEndpointUri(scheme, properties);
    }
    
    /* (non-Javadoc)
     * @see org.fusesource.ide.camel.model.service.core.ICamelManagerService#createEndpointXml(java.lang.String, java.util.Map)
     */
    @Override
    public String createEndpointXml(String scheme, Map<String, String> properties) throws URISyntaxException {
    	return checkedGetService().createEndpointXml(scheme, properties);
    }
    
    /* (non-Javadoc)
     * @see org.fusesource.ide.camel.model.service.core.ICamelManagerService#getEndpointProperties(java.lang.String)
     */
    @Override
    public Map<String, String> getEndpointProperties(String uri) throws URISyntaxException {
    	return checkedGetService().getEndpointProperties(uri);
    }
    
    /* (non-Javadoc)
     * @see org.fusesource.ide.camel.model.service.core.ICamelManagerService#getEndpointScheme(java.lang.String)
     */
    @Override
    public String getEndpointScheme(String uri) {
    	return checkedGetService().getEndpointScheme(uri);
    }
    
    /*
     * (non-Javadoc)
     * @see org.fusesource.ide.camel.model.service.core.ICamelManagerService#testExpression(java.lang.String, java.lang.String)
     */
    @Override
    public String testExpression(String language, String expression) {
    	return checkedGetService().testExpression(language, expression);
    }
}
