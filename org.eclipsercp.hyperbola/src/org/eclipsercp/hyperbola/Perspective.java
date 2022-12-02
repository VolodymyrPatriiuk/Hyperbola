package org.eclipsercp.hyperbola;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	@Override	
	public void createInitialLayout(IPageLayout layout) {
		// var.1
		layout.setEditorAreaVisible(false);
		layout.addView(ContactsView.ID, IPageLayout.LEFT, 0.5F, layout.getEditorArea());
		
		// var.2
		//layout.addStandaloneView(ContactsView.ID, true, IPageLayout.LEFT, 1.0F, layout.getEditorArea());
	
	}
}
