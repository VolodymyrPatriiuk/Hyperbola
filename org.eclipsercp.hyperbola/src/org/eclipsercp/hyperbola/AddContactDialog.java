package org.eclipsercp.hyperbola;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;

public class AddContactDialog extends InputDialog {

	public AddContactDialog(Shell parentShell, String dialogTitle, String dialogMessage, String initialValue,
			IInputValidator validator) {
		super(parentShell, dialogTitle, dialogMessage, initialValue, validator);
	}

	public AddContactDialog(Shell parentShell) {
		this(parentShell, "New contact", "Add new contact in the format: <name>,<nickname>,<server>", null, null);
	}

}
