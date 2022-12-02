package smack.testing;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		System.out.println("Hello RCP World!");
		
		XMPPConnection connection = new XMPPConnection("eclipsercp.org");
		connection.login("reader", "secret", Long.toString(System.currentTimeMillis()));
		Chat chat = connection.createChat("eliza@eclipsercp.org");
		chat.sendMessage("Hi There!");
		Message message = chat.nextMessage(5000);
		System.out.println("Returned message: " + (message == null ? "<timed out>" : message.getBody()));
		
		return IApplication.EXIT_OK;
	}

	@Override
	public void stop() {
		// nothing to do
	}
}
