package jrg.decider.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DisplayWidget extends HorizontalPanel implements ClickHandler
{
    private Label _choice1;
    private Label _choice2;
    private Button _button1;
    private Button _button2;
    private Controller _controller;
    private TreePanel _treePanel;
    private StatsPanel _statsPanel;
    
    
    public DisplayWidget(Controller handler)
    {
        _controller = handler;
        
        addTreePanel();
        addButtonPanel();
        
        generateNewChoice();
    }
    
    private void addTreePanel()
    {
        _treePanel = new TreePanel();
        add(_treePanel);
    }
        
    private void addButtonPanel()
    {
        VerticalPanel buttonPanel = new VerticalPanel();
        _choice1 = new Label("Waiting for choice 1");
        _choice2 = new Label("Waiting for choice 2");
        _button1 = new Button("Choice 1", this);
        _button2 = new Button("Choice 2", this);
        
        _statsPanel = new StatsPanel(_controller);
        buttonPanel.add(new QuestionPanel());
        buttonPanel.add(_statsPanel);
        buttonPanel.add(_choice1);
        buttonPanel.add(_button1);
        buttonPanel.add(_choice2);
        buttonPanel.add(_button2);
        
        add(buttonPanel);
    
    }

    public void onClick(ClickEvent event)
    {
        if(event.getSource() == _button1)
        {
            ChoiceNode loser = _controller.getCurrentPair().node2;
            this._statsPanel.loser(loser);
            _controller.choice(Controller.OPTION1);
        }
        else if(event.getSource() == _button2)
        {
            ChoiceNode loser = _controller.getCurrentPair().node1;
            _statsPanel.loser(loser);
            _controller.choice(Controller.OPTION2);
        }
        
        generateNewChoice();
    }
    
    void generateNewChoice()
    {
        _treePanel.updateTree(_controller.getRoot());
        
        try
        {
            ChoiceNodePair pair = _controller.selectNewPair();
            setNewOptions(pair);
        }
        catch(NoMoreSelections e)
        {
            _button1.setEnabled(false);
            _button2.setEnabled(false);
        }
    }

    public void setNewOptions(ChoiceNodePair pair)
    {
        _choice1.setText(pair.node1.getName());
        _choice2.setText(pair.node2.getName());
    }
}
