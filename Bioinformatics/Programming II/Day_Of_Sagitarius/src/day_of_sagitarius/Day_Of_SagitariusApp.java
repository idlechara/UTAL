/*
 * Day_Of_SagitariusApp.java
 */

package day_of_sagitarius;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class Day_Of_SagitariusApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new Day_Of_SagitariusView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of Day_Of_SagitariusApp
     */
    public static Day_Of_SagitariusApp getApplication() {
        return Application.getInstance(Day_Of_SagitariusApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(Day_Of_SagitariusApp.class, args);
    }
}
