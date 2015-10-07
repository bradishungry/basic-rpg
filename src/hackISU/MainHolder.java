package hackISU;

import javax.swing.JFrame;

public class MainHolder {

	public static void main(String[] args) {
		JFrame window = new JFrame("RPG");
		window.setContentPane(new Game());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}

}
