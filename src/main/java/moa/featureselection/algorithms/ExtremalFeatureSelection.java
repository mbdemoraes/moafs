/*
 *    ExtremeFeatureSelection.java
 *    Copyright (C) 2018 University of Campinas, Brazil
 *    @author Matheus Bernardelli (matheuzmoraes@gmail.com)
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

package moa.featureselection.algorithms;

import java.util.Arrays;
import com.yahoo.labs.samoa.instances.Instance;
import moa.featureselection.common.MOAAttributeEvaluator;
import weka.attributeSelection.ASEvaluation;
import weka.attributeSelection.AttributeEvaluator;
import weka.core.AlgVector;
import weka.core.Instances;
import weka.core.Utils;



/**
 * <!-- globalinfo-start --> Extreme Feature Selection :<br/>
 * <br/>
 * Evaluates the worth of an attribute through the computation of weights 
 * based on the Modified Balanced Winnow.<br/>
 * <br/>
 * Carvalho, V. R.; Cohen, W. W. Single-pass online learning. Proceedings of the
 * 12th ACM SIGKDD international conference on Knowledge discovery and data mining -
 * KDD ’06, p. 548, 2006.
 * doi: 10.1145/1150402.1150466 <br/>
 * <p/>
 * <!-- globalinfo-end -->
 * 
 * 
 * @author Matheus Bernardelli (matheuzmoraes@gmail.com)
 */

public class ExtremalFeatureSelection extends ASEvaluation implements
AttributeEvaluator, MOAAttributeEvaluator{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The promotion coefficient. According to the authors criteria.**/
	protected double m_Alpha = 1.5;

	/** The demotion coefficient. According to the authors criteria.**/
	protected double m_Beta = 0.5;

	/** Prediction threshold. According to the authors criteria.**/
	protected double m_Threshold = 1.0;

	/** Predefined margin. According to the authors criteria. **/
	protected double marginM = 1.0;

	/** Accumulated mistake count (for statistics) **/
	protected int m_Mistakes = 0;

	/** Starting weights for the prediction vector(s) **/
	protected double m_defaultWeight = 2.0;

	protected static double maxValue = 0.0;
	protected static double minValue = 0.0;

	protected double score = 0.0;
	protected AlgVector rankedAttributes = null;
	protected AlgVector rankedAttributes90 = null;

	/** The weight vectors for prediction **/
	private AlgVector m_predPosVector = null;
	private AlgVector m_predNegVector = null;
	
	private boolean rankAttributes = true;
    protected int m_numAttribs = 0;

	private int numFeatures = 10;
	private boolean updated = false;
	private int instanceConunter = 0;
	
	
	public ExtremalFeatureSelection(int numFeatures) {
		this.numFeatures = numFeatures;
	}

	
	/**
	   * Returns a string describing this attribute evaluator
	   * 
	   * @return a description of the evaluator suitable for displaying in the
	   *         explorer/experimenter gui
	   */
	  public String globalInfo() {
	    return "Extreme Feature Selection with Modified Balanced Winnow";
	  }
	  
	  /**
	   * Describe the attribute evaluator
	   * 
	   * @return a description of the attribute evaluator as a string
	   */
	  @Override
	  public String toString() {
	    StringBuffer text = new StringBuffer();

	    if (rankedAttributes == null) {
	      text.append("Ranked attributes has not been initialized");
	    } else {
	      text.append("\n Extreme Feature Selection with Modified Balanced Winnow");
	     
	    }

	    text.append("\n");
	    return text.toString();
	  }

	public static void getMaxMinValue(double[] numbers){
		maxValue = numbers[0];
		minValue = numbers[0];
		for(int i=1;i < numbers.length;i++){
			if(numbers[i] > maxValue){
				maxValue = numbers[i];
			} else if(numbers[i] < minValue){
				minValue = numbers[i];
			}

		}

	}
	
	public double[][] rankedAttributes(int[] m_attributeList, double[] m_attributeMerit){
	    int i, j;

	    int[] ranked = Utils.sort(m_attributeMerit);
	    // reverse the order of the ranked indexes
	    double[][] bestToWorst = new double[ranked.length][2];

	    for (i = ranked.length - 1, j = 0; i >= 0; i--) {
	      bestToWorst[j++][0] = ranked[i];
	    }

	    // convert the indexes to attribute indexes
	    for (i = 0; i < bestToWorst.length; i++) {
	      int temp = ((int) bestToWorst[i][0]);
	      bestToWorst[i][0] = m_attributeList[temp];
	      bestToWorst[i][1] = m_attributeMerit[temp];
	    }


	    return bestToWorst;
	    
	}
	
	public void findLowestScore(){
		
		
		int[] m_attributeList = new int[m_numAttribs];
		double[] m_attributeMerit = new double[m_numAttribs];
		
		for (int i = 0; i < m_attributeList.length; i++) {
			  m_attributeList[i] = i;
		      m_attributeMerit[i] = rankedAttributes90.getElement(i);
		}
		
		double[][] tempRanked = rankedAttributes(m_attributeList, m_attributeMerit);
	    int[] rankedAttributes = new int[m_attributeList.length];

	    for (int i = 0; i < m_attributeList.length; i++) {
	      rankedAttributes[i] = (int) tempRanked[i][0];
	    }
	    
	    //upgrade the lowest score
		int attributesToUpgrade = (int) Math.round(numFeatures*0.10);
		
		int worstToBest = rankedAttributes.length-1;
		for(int j=0; j<attributesToUpgrade; j++) {
			rankedAttributes90.setElement(rankedAttributes[worstToBest], (rankedAttributes90.getElement(rankedAttributes[0] + 1)));
			worstToBest--;
		}
	}

	/**
	 * Updates the positive and negative models.
	 * @param normalizedData Instance normalized data
	 * @param trueClass Instance true class
	 * @throws Exception
	 */
	public void updateModels(double[] normalizedData, double trueClass) throws Exception {

		m_Mistakes++;

		int n1 = normalizedData.length; 
		for(int l = 0 ; l < n1; l++) {
			if(trueClass > 0) { 
				m_predPosVector.setElement(l,  (m_predPosVector.getElement(l)*m_Alpha*(1+normalizedData[l])));		  
				m_predNegVector.setElement(l,  (m_predNegVector.getElement(l)*m_Beta*(1-normalizedData[l])));  
			} else {
				m_predPosVector.setElement(l,  (m_predPosVector.getElement(l)*m_Beta*(1+normalizedData[l])));		  
				m_predNegVector.setElement(l,  (m_predNegVector.getElement(l))*m_Alpha*(1-normalizedData[l]));  
			}
			rankedAttributes.setElement(l, Math.abs(m_predPosVector.getElement(l) - m_predNegVector.getElement(l)));
			rankedAttributes90 = rankedAttributes;
		}
	}

	/**
	 * 
	 */

	public void updateEvaluator(Instance inst) throws Exception {

		if(m_predPosVector == null && m_predNegVector == null) {
			m_numAttribs = inst.numAttributes()-1;
			m_predPosVector = new AlgVector(new double[inst.numAttributes()]);
			m_predNegVector = new AlgVector(new double[inst.numAttributes()]);
			rankedAttributes = new AlgVector(new double[inst.numAttributes()]);
			
			if((numFeatures*0.10 >= m_numAttribs)) {
				rankAttributes = false;
			}
			
			for(int i = 0; i < (inst.numAttributes()); i++) {
				m_predPosVector.setElement(i, m_defaultWeight); 
				m_predNegVector.setElement(i, m_defaultWeight); 
				rankedAttributes.setElement(i, 0);

			}
		}
			
		//Augmentation and normalization step
		double[] rawx = Arrays.copyOfRange(inst.toDoubleArray(), 0, inst.numAttributes() - 1);
		double[] normalizedData = new double[rawx.length +1];
		getMaxMinValue(rawx);

		for(int j=0; j < normalizedData.length; j++) {
			if(j == normalizedData.length -1) {
				normalizedData[j] = 1.0;
			} else {
				normalizedData[j] = (rawx[j]-minValue)/(maxValue-minValue);
			}
			
		}

		AlgVector x = new AlgVector(normalizedData);
		score = (m_predPosVector.dotMultiply(x)) - (m_predNegVector.dotMultiply(x)) - m_Threshold; 
		double classValue = inst.classValue();
		
		if(classValue==0) {
			classValue = -1.0;
		}
		
		double result = score * classValue;
		
		if(result <= marginM){
			updateModels(normalizedData, classValue);
			//if there are less features than included in option, consider all features
			if((numFeatures*0.10 < m_numAttribs)) {
				rankAttributes = true;
			}
			
		}
		
		updated = true;


	}

	public void applySelection() {
		updated = false;

	}

	public boolean isUpdated() {
		return updated;
	}

	public double evaluateAttribute(int attribute) throws Exception {
		
		if(rankAttributes==true) {
			findLowestScore();
			rankAttributes = false;
		}
		//return rankedAttributes.getElement(attribute);
		return rankedAttributes90.getElement(attribute);
	}

	@Override
	public void buildEvaluator(Instances data) throws Exception {
		// TODO Auto-generated method stub

	}

}
