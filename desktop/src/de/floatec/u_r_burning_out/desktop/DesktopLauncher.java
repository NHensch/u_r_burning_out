package de.floatec.u_r_burning_out.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.floatec.u_r_burning_out.BurningOut;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width=1024;
        config.height=750;
		new LwjglApplication(new BurningOut(), config);
	}
}
