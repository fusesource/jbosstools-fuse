/*******************************************************************************
 * Copyright (c) 2020 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.fusesource.ide.branding.tests.integration.wizards;

import org.junit.Test;

public class NewCamelTestWizardWithCamelContextIT extends AbstractNewCamelTestWizardIT {
	
	@Test
	public void testCreateTestForRouteUsingCamelContext() throws Exception {
		createTestFor(fuseProject.createEmptyCamelFile());
	}
}