package jrg.decider.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ChoicePanel extends VerticalPanel implements ClickHandler, ChoiceDisplayer
{
    private Label _choice1;
    private Label _choice2;
    private Button _button1;
    private Button _button2;
    private ChoiceHandler _handler;
    
    public ChoicePanel(ChoiceHandler handler)
    {
        _handler = handler;
        _choice1 = new Label("Waiting for choice 1");
        _choice2 = new Label("Waiting for choice 2");
        _button1 = new Button("Choice 1", this);
        _button2 = new Button("Choice 2", this);
        
        add(_choice1);
        add(_button1);
        add(_choice2);
        add(_button2);
    
    }

    public void onClick(ClickEvent event)
    {
        if(event.getSource() == _button1)
        {
            _handler.choice(ChoiceHandler.OPTION1);
        }
        else if(event.getSource() == _button2)
        {
            _handler.choice(ChoiceHandler.OPTION2);
        }
       
    }

    public void setOptions(String option1, String option2)
    {
        _choice1.setText(option1);
        _choice2.setText(option2);
    }

}
