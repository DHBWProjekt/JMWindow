package listener;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class JMPInternalFrameListener implements InternalFrameListener {
	JInternalFrame jmp;

	public JMPInternalFrameListener(JInternalFrame parent) {
		jmp = parent;
	}

	public void internalFrameOpened(InternalFrameEvent e) {
		System.out.println("JMPInternalFrameListener: Fenster wurde geöffnet");
	}

	public void internalFrameClosing(InternalFrameEvent e) {
		System.out
				.println("JMPInternalFrameListener: Fenster über Schließen Button geschlossen");
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		System.out.println("JMPInternalFrameListener: Fenster ist geschlossen");
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		System.out.println("JMPInternalFrameListener: Fenster wurde abgelegt");
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		System.out
				.println("JMPInternalFrameListener: Fenster wurde wieder aufgerufen");
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		System.out.println("JMPInternalFrameListener: Fenster wurde aktiviert");
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		System.out
				.println("JMPInternalFrameListener: Fenster wurde deaktiviert");
	}
}
