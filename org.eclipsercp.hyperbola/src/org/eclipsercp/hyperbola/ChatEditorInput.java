package org.eclipsercp.hyperbola;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class ChatEditorInput implements IEditorInput {

	private String participant; 
	
	public ChatEditorInput(String participant) {
		super();
		Assert.isNotNull(participant);
		this.participant = participant;
	}
	
	@Override
	public <T> T getAdapter(Class<T> adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public String getToolTipText() {
		return participant;
	}
	
	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return participant;
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			return true;
		}
		if (!(obj instanceof ChatEditorInput)) {
			return false;
		}
		ChatEditorInput other = (ChatEditorInput)obj;
		return participant.equals(other.participant);
	}
	
	@Override
	public int hashCode() {
		return participant.hashCode();
	}
	
}
