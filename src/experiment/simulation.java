package experiment;


import org.jLOAF.Agent;
import org.jLOAF.action.Action;
import org.jLOAF.agents.GenericAgent;
import org.jLOAF.casebase.Case;
import org.jLOAF.casebase.CaseBase;
import org.jLOAF.inputs.AtomicInput;
import org.jLOAF.inputs.Feature;
import org.jLOAF.inputs.Input;
import org.jLOAF.reasoning.SimpleKNN;
import org.jLOAF.reasoning.WeightedKNN;
import org.jLOAF.sim.atomic.Equality;
import org.jLOAF.sim.atomic.EuclideanDistance;
import org.jLOAF.sim.atomic.PercentDifference;

public class simulation {

	public static void main(String [] args){
		//create a casebase and testbase
		Feature f1 = new Feature(1.0);
		Feature f2 = new Feature(2.0);
		Feature f3 = new Feature(3.0);
		Feature f4 = new Feature(3.0);
		Feature f5 = new Feature(8.0);
		
		Input i1 = new AtomicInput("1", f1);
		Input i2 = new AtomicInput("2", f2);
		Input i3 = new AtomicInput("3", f3);
		Input i4 = new AtomicInput("4", f4);
		Input i5 = new AtomicInput("5", f5);
			
		Action a1 = new Action("down");
		Action a2 = new Action("down");
		Action a3 = new Action("up");
		Action a4 = new Action("up");
		Action a5 = new Action("up");
	
		
		Case c1 = new Case(i1, a1);
		Case c2 = new Case(i2, a2);
		Case c3 = new Case(i3, a3);
		Case c4 = new Case(i4, a4);
		Case c5 = new Case(i5, a5);
		
		CaseBase cb = new CaseBase();
		cb.add(c1);
		cb.add(c2);
		cb.add(c3);
		cb.add(c4);
		cb.add(c5);
		
		
		//CaseBase cb = CaseBase.load("Left_1.cb");
		//CaseBase tb = CaseBase.load("Right_1.cb");
		
		//set similarity strategy
		AtomicInput.setClassStrategy(new PercentDifference());
		
		//testcase
		Input i6 = new AtomicInput("test", new Feature(1.5));
		Action a6 = new Action("down");
		Case c6 = new Case(i6,a6);
		
		//create generic agent
		int k = 5;
		Agent a = new GenericAgent(cb);
		a.setR(new SimpleKNN(k,cb));
		
		Action predicted = a.getR().selectAction(c6.getInput());
		System.out.println("Action Predicted: " + predicted.getName() +", and the correct action is " + a6.getName() + ".");
	}
}
