package jrg.decider.client;

import java.util.Iterator;
import java.util.LinkedList;

import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TreePanel extends VerticalPanel
{

    private Tree _tree;

    public TreePanel()
    {
        _tree = new Tree();
        add(_tree);
    }

    public void updateTree(ChoiceNode root)
    {
        _tree.clear();

        TreeItem rootItem = _tree.addItem(root.getName());

        updateTreeChildren(rootItem, root.getChildren());
    }

    private void updateTreeChildren(TreeItem parentItem,
            LinkedList<ChoiceNode> children)
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

}
