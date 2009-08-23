package jrg.decider.client;

import java.util.LinkedList;

import jrg.decider.client.predefines.DisneyFood;
import jrg.decider.client.predefines.DrewMagicKingdom;
import jrg.decider.client.predefines.Pixar;

import com.google.gwt.user.client.Random;

public class Controller
{
    private ChoiceNode _root;

    private ChoiceNodePair _currentPair;
    
    static public final int OPTION1 = 1;
    static public final int OPTION2 = 2;

    public Controller()
    {
    	_root = new Pixar();
    	_root = new DrewMagicKingdom();
        //_root = new DisneyFood();
        //_root = new MagicKingdomRoot();
        //_root = new NumberTest();
    }

    public void choice(int selection)
    {
        if(selection == OPTION1)
        {
            setWinner(_currentPair.parent, _currentPair.node1, _currentPair.node2);
        }
        else if(selection == OPTION2)
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

    public ChoiceNode getRoot()
    {
        return _root;
    }

    private void setWinner(ChoiceNode parent, ChoiceNode winner, ChoiceNode loser)
    {
        parent.removeChild(loser);
        winner.addChild(loser);
    }

    
}
