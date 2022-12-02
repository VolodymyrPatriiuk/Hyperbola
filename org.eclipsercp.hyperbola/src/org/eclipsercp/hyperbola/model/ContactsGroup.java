package org.eclipsercp.hyperbola.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactsGroup extends Contact {

	private List<Contact> entries;

	private ContactsGroup parent;

	private String name;

	private Set<IContactsListener> listeners;

	public ContactsGroup(ContactsGroup parent, String name) {
		this.name = name;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public ContactsGroup getParent() {
		return parent;
	}

	public void setParent(ContactsGroup parent) {
		if (this.parent != null) {
			throw new IllegalStateException("cannot change parent, once set"); //$NON-NLS-1$
		}
		this.parent = parent;
	}

	public void rename(String newName) {
		this.name = newName;
		fireContactsChanged(null);
	}

	public void addEntry(Contact entry) {
		if (entries == null) {
			entries = new ArrayList<Contact>(5);
		}
		entries.add(entry);
		fireContactsChanged(null);
	}

	public void removeEntry(Contact entry) {
		if (entries != null) {
			entries.remove(entry);
			if (entries.isEmpty()) {
				entries = null;
			}
		}
		fireContactsChanged(null);
	}

	public Contact[] getEntries() {
		if (entries == null) {
			return new Contact[0];
		}
		return (Contact[]) entries.toArray(new Contact[entries.size()]);
	}

	public void addContactsListener(IContactsListener listener) {
		if (parent != null) {
			parent.addContactsListener(listener);
		} else {
			if (listeners == null) {
				listeners = new HashSet<IContactsListener>();
			}
			listeners.add(listener);
		}
	}

	public void removeContactsListener(IContactsListener listener) {
		if (parent != null) {
			parent.removeContactsListener(listener);
		} else {
			if (listeners != null) {
				listeners.remove(listener);
				if (listeners.isEmpty()) {
					listeners = null;
				}
			}
		}
	}

	protected void fireContactsChanged(ContactsEntry entry) {
		if (parent != null) {
			parent.fireContactsChanged(entry);
		} else {
			if (listeners != null) {
				for (IContactsListener listener : listeners) {
					listener.contactsChanged(this, entry);
				}
			}
		}
	}
}