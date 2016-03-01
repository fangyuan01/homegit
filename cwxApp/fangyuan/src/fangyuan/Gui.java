package fangyuan;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Gui {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		Frame window = new Frame("title");
		window.setBounds(300, 300, 400, 400);
		window.setLayout(null);
		window.addWindowListener(new WindowListener() {

			public void windowOpened(WindowEvent e) {
				System.out.println("窗口被打开");
			}

			public void windowIconified(WindowEvent e) {
				System.out.println("窗口最小化");

			}

			public void windowDeiconified(WindowEvent e) {
				System.out.println("窗口从最小化到还原");

			}

			public void windowDeactivated(WindowEvent e) {
				System.out.println("窗口已经停用");

			}

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowActivated(WindowEvent e) {
			}
		});

		Button button = new Button("按我");
		button.setBounds(0, 0, 200, 100);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println("按了");
				Test a = new Test();
				a.test();
			}
		});

		window.add(button);

		window.setVisible(true);
	}

}
