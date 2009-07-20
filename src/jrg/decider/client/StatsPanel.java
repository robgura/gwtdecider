package jrg.decider.client;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import java.lang.Integer;

public class StatsPanel extends HorizontalPanel
{
    private Controller _controller;
    
    private Label _maxQLabel;
    private int _maxQ;
    
    private Label _currentLabel;
    private int _current;
    
    
    StatsPanel(Controller controller)
    {
        _controller = controller;
        _current = 1;
        
        add(new Label("Question "));
        _currentLabel = new Label("Unknown");
        add(_currentLabel);
        
        int nodes = _controller.getRoot().getDesendantCount();       
        _maxQ = nodes * (nodes - 1) / 2;
        _maxQLabel = new Label("Unknown");
        add(new Label(" of "));
        add(_maxQLabel);

        setLabels();
    }
    
    public void loser(ChoiceNode node)
    {
        _maxQ -= node.getDesendantCount();
        _current++;
        setLabels();
    }
    
    private void setLabels()
    {
        _currentLabel.setText((new Integer(_current)).toString());
        _maxQLabel.setText((new Integer(_maxQ)).toString());
    }
    

}
