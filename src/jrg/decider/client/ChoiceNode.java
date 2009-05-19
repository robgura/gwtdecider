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
    
    void removeChild(ChoiceNode node)
    {
        _children.remove(node);
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
            System.out.println("index1 " + index1);
            System.out.println("index2 " + index2);
            System.out.println("Size " + _children.size());
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

    public LinkedList<ChoiceNode> getChildren()
    {
        return _children;
    }


}