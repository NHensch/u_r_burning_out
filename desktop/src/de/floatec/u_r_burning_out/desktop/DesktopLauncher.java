package de.floatec.u_r_burning_out.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.floatec.u_r_burning_out.URBurningOut;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width=800;
        config.height=520;
		new LwjglApplication(new URBurningOut(), config);
	}
}
