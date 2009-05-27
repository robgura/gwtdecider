package jrg.decider.client;

import java.util.Iterator;
import java.util.LinkedList;
import com.google.gwt.user.client.Random;

public class ChoiceNode
{
    private String _name;
    
    private LinkedList<ChoiceNode> _children;
    
    ChoiceNode(String name)
    {
        _name = name;
        _children = new LinkedList<ChoiceNode>();
    }
    
    String getName()
    {
        return _name;
    }
    
    void addChild(ChoiceNode node)
    {
        _children.add(node);
    }
    
    void addChild(String name)
    {
        addChild(new ChoiceNode(name));
    }
    
    void removeChild(ChoiceNode node)
    {
        _children.remove(node);
    }

    public LinkedList<ChoiceNode> getChildren()
    {
        return _children;
    }

    int getChildrenCount()
    {
        return _children.size();
    }
    
    int getDesendantCount()
    {
        int count = 0;
        
        Iterator<ChoiceNode> choiceNodeIter =_children.iterator();
        
        while(choiceNodeIter.hasNext())
        {
            ChoiceNode child = choiceNodeIter.next();
            count += child.getDesendantCount() + 1;
        }
        return count;
    }
    
    ChoiceNodePair getPair()
    {
        ChoiceNodePair pair = null;
        if(_children.size() >= 2)
        {
            int index1 = Random.nextInt(_children.size());
            int index2 = Random.nextInt(_children.size() - 1);
            
            if(index2 >= index1)
            {
                index2++;
            }
            
            pair = new ChoiceNodePair(this, _children.listIterator(index1).next(), _children.listIterator(index2).next());
        }
        return pair;
    }

    public void getMultiParents(LinkedList<ChoiceNode> multis)
    {
        if(_children.size() >= 2)
        {
            multis.add(this);
        }
        
        Iterator<ChoiceNode> iter = _children.iterator();
        while(iter.hasNext())
        {
            ChoiceNode node = iter.next();
            node.getMultiParents(multis);
        }
    }


}
