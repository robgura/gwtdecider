package jrg.decider.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
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
        DockPanel mainPanel = new DockPanel();
        RootPanel.get().add(mainPanel);
        
        mainPanel.add(new QuestionPanel(), DockPanel.NORTH);
        
        Panel treePanel = new HorizontalPanel();
        mainPanel.add(treePanel, DockPanel.WEST);
        
        Controller controller = new Controller(treePanel);
        ChoicePanel choicePanel = new ChoicePanel(controller);
        controller.setDisplayer(choicePanel);
        mainPanel.add(choicePanel, DockPanel.CENTER);
        

    }
}
