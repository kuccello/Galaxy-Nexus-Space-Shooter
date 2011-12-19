package krispy.kirorun;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class KiroRunDesktop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new JoglApplication(new KiroRun(),"Kiro Run", 800, 480, false);
	}

}
