package jrg.decider.client;

import java.util.Iterator;
import java.util.LinkedList;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

public class Controller implements ChoiceHandler
{
    private ChoiceDisplayer _displayer;
    
    private ChoiceNode _root;
    
    private ChoiceNodePair _currentPair;
    
    private Tree _tree;
    
    public Controller(Panel treePanel)
    {
        _root = new ChoiceNode("root");
        _root.addChild("McDonalds");
        _root.addChild("BurgerKing");
        _root.addChild("Wendys");
        _root.addChild("Taco Bell");
        _root.addChild("KFC");
        _root.addChild("Pizza Hut");
        _root.addChild("A&W");
        _root.addChild("Arbys");
        _root.addChild("Subway");
        
        _tree = new Tree();
        treePanel.add(_tree);
    }
    
    public void setDisplayer(ChoiceDisplayer displayer)
    {
        _displayer = displayer;
        updateDisplay();
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
        
        updateDisplay();
    }

    private void updateDisplay()
    {
        LinkedList<ChoiceNode> multis = new LinkedList<ChoiceNode>();
        _root.getMultiParents(multis);

        if(multis.size() > 0)
        {
            int index = Random.nextInt(multis.size());
            ChoiceNode chooseFrom = multis.listIterator(index).next();
            _currentPair = chooseFrom.getPair();
            if(_currentPair != null)
            {
                _displayer.setOptions(_currentPair.node1.getName(), _currentPair.node2.getName());
            }
        }
        else
        {
            _displayer.setOptions("You Are Done", "Don't Pick No More!");
        }
        
        updateTree();
    }
    
    private void updateTree()
    {
        _tree.clear();
        
        TreeItem rootItem = _tree.addItem(_root.getName());
        
        updateTreeChildren(rootItem, _root.getChildren());
    }
    
    private void updateTreeChildren(TreeItem parentItem, LinkedList<ChoiceNode> children)
    {
        Iterator<ChoiceNode> iter = children.iterator();
        while(iter.hasNext())
        {
            ChoiceNode node = iter.next();
            TreeItem newItem = parentItem.addItem(node.getName());
            parentItem.setState(true);
            updateTreeChildren(newItem, node.getChildren());
        }
    }

    private void setWinner(ChoiceNode parent, ChoiceNode winner, ChoiceNode loser)
    {
        parent.removeChild(loser);
        winner.addChild(loser);
    }
 
}
