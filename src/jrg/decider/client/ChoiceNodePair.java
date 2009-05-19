package jrg.decider.client;

public class ChoiceNodePair
{
    public ChoiceNode node1;
    public ChoiceNode node2;
    public ChoiceNode parent;
    public ChoiceNodePair(ChoiceNode p, ChoiceNode n1, ChoiceNode n2)
    {
        parent = p;
        node1 = n1;
        node2 = n2;
    }

}
