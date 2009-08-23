package jrg.decider.client.predefines;

import jrg.decider.client.ChoiceNode;

public class Pixar extends ChoiceNode
{

	public Pixar()
	{
		super("Pixar Movies");

		addChild("Toy Story (1995)");
		addChild("A Bug's Life (1998)");
		addChild("Toy Story 2 (1999)");
		addChild("Monsters, Inc. (2001)");
		addChild("Finding Nemo (2003)");
		addChild("The Incredibles (2004)");
		addChild("Cars (2006)");
		addChild("Ratatouille (2007)");
		addChild("WALL-E (2008)");
		addChild("Up (2009)");

	}
}
