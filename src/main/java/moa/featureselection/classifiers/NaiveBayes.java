/*
 *    NaiveBayes.java
 *    Copyright (C) 2007 University of Waikato, Hamilton, New Zealand
 *    @author Richard Kirkby (rkirkby@cs.waikato.ac.nz)
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program. If not, see <http://www.gnu.org/licenses/>.
 *    
 */

package moa.featureselection.classifiers;



import com.github.javacliparser.FloatOption;
import com.github.javacliparser.IntOption;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import moa.classifiers.AbstractClassifier;
import moa.classifiers.MultiClassClassifier;
import moa.classifiers.core.attributeclassobservers.AttributeClassObserver;
import moa.classifiers.core.attributeclassobservers.GaussianNumericAttributeClassObserver;
import moa.classifiers.core.attributeclassobservers.NominalAttributeClassObserver;
import moa.core.AutoExpandVector;
import moa.core.DoubleVector;
import moa.core.Measurement;
import moa.core.StringUtils;
import moa.featureselection.algorithms.ExtremalFeatureSelection;
import moa.featureselection.algorithms.IncrInfoThAttributeEval;
import moa.featureselection.algorithms.OFSER;
import moa.featureselection.algorithms.OFS;
import moa.featureselection.common.MOAAttributeEvaluator;
import weka.attributeSelection.ASEvaluation;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.Ranker;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

/**
 * Naive Bayes incremental learner.
 *
 * <p>Performs classic bayesian prediction while making naive assumption that
 * all inputs are independent.<br /> Naive Bayes is a classiﬁer algorithm known
 * for its simplicity and low computational cost. Given n different classes, the
 * trained Naive Bayes classiﬁer predicts for every unlabelled instance I the
 * class C to which it belongs with high accuracy.</p>
 *
 * <p>Parameters:</p> <ul> <li>-r : Seed for random behaviour of the
 * classifier</li> </ul>
 *
 * @author Richard Kirkby (rkirkby@cs.waikato.ac.nz)
 * @version $Revision: 7 $
 */

public class NaiveBayes extends AbstractClassifier implements MultiClassClassifier {

	private static final long serialVersionUID = 1L;
	protected DoubleVector observedClassDistribution;

	/*
	 * Class constructor
	 */
	public NaiveBayes() {

	}

	/*
	 * Method used by MOA to display the class purpose at the GUI.
	 */

	public String getPurposeString() {
		 return "Naive Bayes classifier with feature selection: performs classic bayesian prediction while making naive assumption that all inputs are independent.";
	}

	protected AutoExpandVector<AttributeClassObserver> attributeObservers;

	/*
	 * Attribute for the user to select how many features the selected FS method has
	 * to choose.
	 */
	public static IntOption numFeaturesOption = new IntOption("numFeatures", 'f', "The number of features to select",
			10, 1, Integer.MAX_VALUE);
	/* Attribute for the user to select which FS method shall be used. */
	public static IntOption fsmethodOption = new IntOption("fsMethod", 'm',
			"Infotheoretic method to be used in feature selection: 0. No method. 1. Information Gain 2. Symmetrical Uncertainty 3. Chi-Squared 4. Cramers V-Test 5. Gain Ratio 6. EFS 7. OFS 8. OFSER",
			0, 0, 8);
	/* Attribute for the user to select the size of the window for model updates */
	public static IntOption winSizeOption = new IntOption("winSize", 'w', "Window size for model updates", 1, 1,
			Integer.MAX_VALUE);

	/* Attribute which will contain the selected FS method. */
	protected static MOAAttributeEvaluator fselector = null;

	/* Attribute which contains the amount of instances already processed. */
	protected int totalCount = 0;

	/* Attribute which contains all selected features. */
	protected Set<Integer> selectedFeatures = new HashSet<Integer>();

	/*
	 * Attribute of Weka's type AttributeSelection, necessary for using Weka's
	 * functions.
	 */
	protected static AttributeSelection selector = null;

	/*
	 * Necessary method from the AbstractClassifier class. It Gets whether this
	 * learner needs a random seed. Examples of methods that needs a random seed are
	 * bagging and boosting.
	 * 
	 * @see moa.learners.Learner#isRandomizable()
	 */
	public boolean isRandomizable() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * Necessary method from the AbstractClassifier class. Returns a string
	 * representation of the model.
	 * 
	 * @see moa.classifiers.AbstractClassifier#getModelDescription(java.lang.
	 * StringBuilder, int)
	 */
	@Override
	public void getModelDescription(StringBuilder out, int indent) {
		for (int i = 0; i < observedClassDistribution.numValues(); i++) {
			StringUtils.appendIndented(out, indent, "Observations for ");
			out.append(getClassNameString());
			out.append(" = ");
			out.append(getClassLabelString(i));
			out.append(":");
			StringUtils.appendNewlineIndented(out, indent + 1, "Total observed weight = ");
			out.append(observedClassDistribution.getValue(i));
			out.append(" / prob = ");
			out.append(observedClassDistribution.getValue(i) / observedClassDistribution.sumOfValues());
			for (int j = 0; j < attributeObservers.size(); j++) {
				StringUtils.appendNewlineIndented(out, indent + 1, "Observations for ");
				out.append(getAttributeNameString(j));
				out.append(": ");

				out.append(attributeObservers.get(j));
			}
			StringUtils.appendNewline(out);
		}

	}

	/*
	 * Necessary method from the AbstractClassifier class. Class for storing an
	 * evaluation measurement.
	 * 
	 * @see moa.classifiers.AbstractClassifier#getModelMeasurementsImpl()
	 */
	@Override
	protected Measurement[] getModelMeasurementsImpl() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Necessary method from the AbstractClassifier class. Predicts the class *
	 * memberships for a given instance. If an instance is unclassified, the
	 * returned array elements must be all zero.
	 * 
	 * @see
	 * moa.classifiers.AbstractClassifier#getVotesForInstance(com.yahoo.labs.samoa.
	 * instances.Instance)
	 */
	@Override
	public double[] getVotesForInstance(com.yahoo.labs.samoa.instances.Instance inst) {
		return doNaiveBayesPrediction(inst, observedClassDistribution, attributeObservers);
	}

	/*
	 * Necessary method from the AbstractClassifier class. Resets this classifier.
	 * It must be similar to starting a new classifier from scratch.
	 * 
	 * @see moa.classifiers.AbstractClassifier#resetLearningImpl()
	 */
	@Override
	public void resetLearningImpl() {
		observedClassDistribution = new DoubleVector();
		attributeObservers = new AutoExpandVector<AttributeClassObserver>();

	}

	/*
	 * Necessary method from the AbstractClassifier class. Trains this classifier
	 * incrementally using the given instance.
	 * 
	 * @see
	 * moa.classifiers.AbstractClassifier#trainOnInstanceImpl(com.yahoo.labs.samoa.
	 * instances.Instance)
	 */
	@Override
	public void trainOnInstanceImpl(com.yahoo.labs.samoa.instances.Instance inst) {
		com.yahoo.labs.samoa.instances.Instance rinst = inst;
		if (fsmethodOption.getValue() != 0) {
			if (fselector == null) {
				if (fsmethodOption.getValue() == 8) {
					fselector = new OFSER(numFeaturesOption.getValue());
				}
				if (fsmethodOption.getValue() == 6) {
					fselector = new ExtremalFeatureSelection(numFeaturesOption.getValue());
				}
				else if (fsmethodOption.getValue() == 7) {
					fselector = new OFS(numFeaturesOption.getValue());					
				} else if ((fsmethodOption.getValue() == 1 || fsmethodOption.getValue() == 2 || fsmethodOption.getValue() == 3 || fsmethodOption.getValue() == 4 || fsmethodOption.getValue() == 5)) {
					fselector = new IncrInfoThAttributeEval(fsmethodOption.getValue());
				} 
			}

			try {
				if (inst == null) {
					System.err.println("Error: null instance");
				}
				fselector.updateEvaluator(inst);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (rinst.classValue() == -1.0D) {
			System.out.println("Error");
		}
		observedClassDistribution.addToValue((int) rinst.classValue(), rinst.weight());
		for (int i = 0; i < rinst.numAttributes() - 1; i++) {
			int instAttIndex = modelAttIndexToInstanceAttIndex(i, rinst);
			AttributeClassObserver obs = (AttributeClassObserver) attributeObservers.get(i);
			if (obs == null) {
				obs = rinst.attribute(instAttIndex).isNominal() ? newNominalClassObserver() : newNumericClassObserver();
				attributeObservers.set(i, obs);
			}

			double value = rinst.value(instAttIndex);
			if (rinst.value(instAttIndex) == -1.0D) {
				System.out.println("Value changed");
				value = 0.0D;
			}
			obs.observeAttributeClass(value, (int) rinst.classValue(), rinst.weight());
		}

		totalCount += 1;

	}

	/*
	 * Interface for observing the class data distribution for an attribute. This
	 * observer monitors the class distribution of a given attribute. Used in naive
	 * Bayes and decision trees to monitor data statistics on leaves.
	 */

	protected AttributeClassObserver newNominalClassObserver() {
		return new NominalAttributeClassObserver();
	}

	/*
	 * Interface for observing the class data distribution for an attribute. This
	 * observer monitors the class distribution of a given attribute. Used in naive
	 * Bayes and decision trees to monitor data statistics on leaves.
	 */

	protected AttributeClassObserver newNumericClassObserver() {
		return new GaussianNumericAttributeClassObserver();
	}

	/*
	 * Performs Feature Selection using the selected methods.
	 */

	private void performFeatureSelection(com.yahoo.labs.samoa.instances.Instance rinst) {
		weka.core.Instance winst = new DenseInstance(rinst.weight(), rinst.toDoubleArray());

		if ((fselector != null) && (fselector.isUpdated()) && (totalCount % winSizeOption.getValue() == 0)) {
			fselector.applySelection();
			selector = new AttributeSelection();
			Ranker ranker = new Ranker();
			ranker.setNumToSelect(Math.min(numFeaturesOption.getValue(), winst.numAttributes() - 1));
			selector.setEvaluator((ASEvaluation) fselector);
			selector.setSearch(ranker);

			ArrayList<weka.core.Attribute> list = new ArrayList<Attribute>();

			for (int i = 0; i < rinst.numAttributes(); i++) {
				list.add(new weka.core.Attribute(rinst.attribute(i).name(), i));
			}

			Instances single = new Instances("single", list, 1);
			single.setClassIndex(rinst.classIndex());
			single.add(winst);
			try {
				selector.SelectAttributes(single);
				System.out.println("Selected features: " + selector.toResultsString());
				selectedFeatures.clear();
				for (int att : selector.selectedAttributes()) {
					selectedFeatures.add(Integer.valueOf(att));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	/*
	 * Performs classification using Naive Bayes using the selected features.
	 */

	public double[] doNaiveBayesPrediction(com.yahoo.labs.samoa.instances.Instance inst,
		DoubleVector observedClassDistribution, AutoExpandVector<AttributeClassObserver> attributeObservers) 
	{
		com.yahoo.labs.samoa.instances.Instance sinst = inst;
		double[] votes = new double[observedClassDistribution.numValues()];
		double observedClassSum = observedClassDistribution.sumOfValues();
		
		
		if ((fsmethodOption.getValue() != 0) && (fselector != null))
			performFeatureSelection(sinst);

		
		for (int classIndex = 0; classIndex < votes.length; classIndex++) 
		{
			votes[classIndex] = (observedClassDistribution.getValue(classIndex) / observedClassSum);
			for (int attIndex = 0; attIndex < sinst.numAttributes() - 1; attIndex++) 
			{
				if ((selectedFeatures.isEmpty()) || (selectedFeatures.contains(Integer.valueOf(attIndex)))) 
				{
					int instAttIndex = modelAttIndexToInstanceAttIndex(attIndex, sinst);
					AttributeClassObserver obs = (AttributeClassObserver) attributeObservers.get(attIndex);
					if ((obs != null) && (!sinst.isMissing(instAttIndex))) 
					{
						votes[classIndex] *= obs.probabilityOfAttributeValueGivenClass(sinst.value(instAttIndex),
								classIndex);
					}
				}
			}
		}

		return votes;
	}

}
