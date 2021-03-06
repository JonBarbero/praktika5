package fss;

import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.*;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.*;
import weka.filters.supervised.attribute.*;
import weka.filters.unsupervised.instance.Randomize;
import weka.filters.unsupervised.instance.RemovePercentage;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class praktika5 {
	
	
	private static Instances data;
	
	public static void main (String args[]) throws Exception {
		
		DataSource source = new DataSource(args[0]);
		data = source.getDataSet();
		if(data.classIndex() == -1) {
			data.setClassIndex(data.numAttributes()-1);
		}
		
		//Metodoak
		

	}
	
	
	private static void holdout () throws Exception{
		
		 Randomize randomize = new Randomize();
	     randomize.setRandomSeed(1);
	     randomize.setInputFormat(data);
	     Instances random = Filter.useFilter(data, randomize);
	        
	     RemovePercentage removetrain = new RemovePercentage();    
	     removetrain.setInputFormat(random);
	     removetrain.setInvertSelection(true);
	     removetrain.setPercentage(70.0);
	     Instances train = Filter.useFilter(random, removetrain);
	        

	     RemovePercentage removetest = new RemovePercentage();
	     removetest.setInputFormat(random);
	     removetest.setInvertSelection(false);
	     removetest.setPercentage(70.0);
	     Instances test = Filter.useFilter(random, removetest);

	     Evaluation holdout=new Evaluation (train);
	     holdout.evaluateModel(naivebayes, test);
	
	}
	
	

}
