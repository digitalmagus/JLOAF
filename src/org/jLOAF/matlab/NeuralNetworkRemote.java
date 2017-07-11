package org.jLOAF.matlab;

import java.util.LinkedList;
import java.util.List;

import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;
import matlabcontrol.MatlabProxyFactoryOptions;

public class NeuralNetworkRemote {
 	static int nextIndex = 1;
    int index = 0;
    int XSIZE = 3, YSIZE = 3;
    public static MatlabProxy proxy = null;
    
	public NeuralNetworkRemote(String trace, int a_XSIZE, int a_YSIZE) {
	 	index = nextIndex++;
        XSIZE = a_XSIZE;
        YSIZE = a_YSIZE;
        String matlabCommand = "nnet" + index + " = learnNN([";
        matlabCommand+="'" + trace + "';";
        matlabCommand+="]," + XSIZE + "," + YSIZE + ");";

        System.out.println(matlabCommand);
        
        MatlabProxyFactoryOptions options = null;
        MatlabProxyFactory factory = null;
        if (proxy==null) {
            options = new MatlabProxyFactoryOptions.Builder().setUsePreviouslyControlledSession(true).build();
            factory = new MatlabProxyFactory(options);
        }
        try {
            if (proxy==null) proxy = factory.getProxy();
            proxy.eval(matlabCommand);
        }catch(Exception e) {
            e.printStackTrace();
        }
}

	public List<Double> run(List<Double> input) {
		String matlabCommand = "nnet" + index + "([";;
        for(Double i:input) {
             matlabCommand+=i + ";";
        }
        matlabCommand+="])";
        double []ret;
        try {
            ret = (double [])(proxy.returningEval(matlabCommand,1)[0]);
            List<Double> output = new LinkedList<Double>();
            for(double d:ret) {
                output.add(d);
            }
            return output;
        } catch (MatlabInvocationException ex) {
            ex.printStackTrace();
            return null;
        } 
	}
}
