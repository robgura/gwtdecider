package jrg.decider.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Decider implements EntryPoint
{

    /**
     * This is the entry point method.
     */
    public void onModuleLoad()
    {
        DockPanel mainDockPanel = new DockPanel();
        RootPanel.get().add(mainDockPanel);

        Controller controller = new Controller();
        DisplayWidget choicePanel = new DisplayWidget(controller);
        mainDockPanel.add(choicePanel, DockPanel.CENTER);
    }
}
