package jrg.decider.client;

import java.util.LinkedList;

import com.google.gwt.user.client.Random;

public class Controller implements ChoiceHandler
{
    private ChoiceNode _root;

    private ChoiceNodePair _currentPair;

    public Controller()
    {
        _root = new MagicKingdomRoot();
    }

    public void choice(int selection)
    {
        if(selection == ChoiceHandler.OPTION1)
        {
            setWinner(_currentPair.parent, _currentPair.node1, _currentPair.node2);
        }
        else if(selection == ChoiceHandler.OPTION2)
        {
            setWinner(_currentPair.parent, _currentPair.node2, _currentPair.node1);
        }
    }

    public ChoiceNodePair getCurrentPair()
    {
        return _currentPair;
    }

    public ChoiceNodePair selectNewPair() throws NoMoreSelections
    {
        LinkedList<ChoiceNode> multis = new LinkedList<ChoiceNode>();
        _root.getMultiParents(multis);
    
        if(multis.size() > 0)
        {
            int index = Random.nextInt(multis.size());
            ChoiceNode chooseFrom = multis.listIterator(index).next();
            _currentPair = chooseFrom.getPair();
        }
        else
        {
            throw new NoMoreSelections();
        }
        
        return _currentPair;
    }

    private void setWinner(ChoiceNode parent, ChoiceNode winner, ChoiceNode loser)
    {
        parent.removeChild(loser);
        winner.addChild(loser);
    }

    public ChoiceNode getRoot()
    {
        return _root;
    }

    
}
