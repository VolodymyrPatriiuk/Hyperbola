package org.eclipsercp.hyperbola;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction exitAction;
	private IWorkbenchAction aboutAction;
	private IWorkbenchAction addContactsEntryAction; 
	private IWorkbenchAction openChatAction; 
	
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}
	
	@Override
	protected void makeActions(IWorkbenchWindow window) {
		
		exitAction = ActionFactory.QUIT.create(window);
		register(exitAction);

		aboutAction = ActionFactory.ABOUT.create(window);
		register(aboutAction);
		
		addContactsEntryAction = new AddContactsEntryAction(window);
		register(addContactsEntryAction);
		
		openChatAction = new ChatAction(window);
		register(openChatAction);
	}
	
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		
		MenuManager hyperbolaMenu = new MenuManager("&Hyperbola", "hyperbola");
		hyperbolaMenu.add(addContactsEntryAction);
		hyperbolaMenu.add(openChatAction);
		hyperbolaMenu.add(exitAction);
		
		MenuManager helpMenu = new MenuManager("&Help", "help");
		helpMenu.add(aboutAction);

		menuBar.add(hyperbolaMenu);
		menuBar.add(helpMenu);
		
	}
	
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		IToolBarManager toolBar = new ToolBarManager(coolBar.getStyle());
		coolBar.add(toolBar);
		toolBar.add(addContactsEntryAction);
		toolBar.add(openChatAction);
	}
	
}

