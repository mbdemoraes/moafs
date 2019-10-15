API Documentation
==================

This section contains the documentation of all classes and functions of ``MOAFS``.

Naïve Bayes
------------

class moa.featureselection.classifiers.NaiveBayes()


This is an incremental version of the Naïve Bayes classifier. This is the main class of the ``MOAFS`` library. It receives the requests from MOA and performs 
prediction using (or not) the feature selection methods available at ``MOAFS``. Since it is using the Interleaved-Test-Then-Train approach, each instance is used
to test the model before it is used for training.


.. admonition:: public String getPurposeString()

   Method used by MOA to display the class purpose at the GUI.

    Parameters
        None

    Returns
        A string explaining the class purpose.

.. admonition:: public boolean isRandomizable()

   Necessary method from the AbstractClassifier class. It Gets whether this learner needs a random seed. Examples of methods that needs a random seed are bagging and boosting.

    Parameters
        None

    Returns
        False

.. admonition:: public void getModelDescription(StringBuilder out, int indent)

   Necessary method from the AbstractClassifier class. Returns a string representation of the model.

    Parameters
        * StringBuilder `out`:  A mutable sequence of characters. This class provides an API compatible with StringBuffer, but with no guarantee of synchronization. 
        * int `indent`: The number of characters to indent.

    Returns
        None

.. admonition:: protected Measurement[] getModelMeasurementsImpl()

   Necessary method from the AbstractClassifier class. Class for storing an evaluation measurement.

    Parameters
        None

    Returns
        Null

.. admonition:: public double[] getVotesForInstance(com.yahoo.labs.samoa.instances.Instance inst)

   Necessary method from the AbstractClassifier class. Predicts the class memberships for a given instance. If an instance is unclassified, the returned array elements must be all zero.

    Parameters
        * inst : The instance to be classified.

    Returns
        The prediction of the instance class using doNaiveBayesPrediction() method.

.. admonition:: public void resetLearningImpl()

   Necessary method from the AbstractClassifier class. Resets this classifier. It must be similar to starting a new classifier from scratch.

    Parameters
        None

    Returns
        None

.. admonition:: public void trainOnInstanceImpl(com.yahoo.labs.samoa.instances.Instance inst)

   	Necessary method from the AbstractClassifier class. Trains this classifier incrementally using the given instance.

    Parameters
        * inst : The instance to be classified.

    Returns
        None

.. admonition:: protected AttributeClassObserver newNominalClassObserver()
 	
	Interface for observing the class data distribution for an attribute. This observer monitors the class distribution of a given attribute. Used in naive Bayes and decision trees to monitor data statistics on leaves.

    Parameters
        None

    Returns
       * NominalAttributeClassObserver(): Class distribution of a given nominal attribute.

.. admonition:: protected AttributeClassObserver newNumericClassObserver()
 	
	Interface for observing the class data distribution for an attribute. This observer monitors the class distribution of a given attribute. Used in naive Bayes and decision trees to monitor data statistics on leaves.

    Parameters
        None

    Returns
       * new GaussianNumericAttributeClassObserver(): Class distribution of a given numeric attribute.

.. admonition:: private void performFeatureSelection(com.yahoo.labs.samoa.instances.Instance rinst)
 	
	Performs Feature Selection using the selected methods.

    Parameters
        * rinst : The instance to be reduced.

    Returns
       None

.. admonition:: public double[] doNaiveBayesPrediction(com.yahoo.labs.samoa.instances.Instance inst,
		DoubleVector observedClassDistribution, AutoExpandVector<AttributeClassObserver> attributeObservers) 
 	
	Performs classification using Naive Bayes using the selected features.

    Parameters
        * inst : The instance to be reduced.
        * observedClassDistribution : The observed class distribution.
        * attributeObservers: Attribute class distribution.

    Returns
       * votes: The predicted class of a given instance.





Information-based algorithms
-----------------------------------------------------------------------------------------------------

class moa.featureselection.algorithms.IncrInfoThAttributeEval()

Evaluates the worth of an attribute by measuring the information gain, chi-squared, symmetrical uncertainty, crammer's v-test or gain ratio with respect to the class.

.. admonition:: public String globalInfo()

   Returns a string describing this attribute evaluator.

    Parameters
        * numFeatures: Number of features to select.

    Returns
       A description of the evaluator suitable for displaying in the explorer/experimenter gui

.. admonition:: public String binarizeNumericAttributesTipText()

   Returns the tip text for this property

    Parameters
        * numFeatures: Number of features to select.

    Returns
       A tip text for this property suitable for displaying in the explorer/experimenter gui

.. admonition::  public void setBinarizeNumericAttributes(boolean b)

   Binarize numeric attributes.

    Parameters
        * b: if true, binarize numeric attributes

    Returns
       None 

.. admonition:: public boolean getBinarizeNumericAttributes()

   Get whether numeric attributes are just being binarized.

    Parameters
       None

    Returns
       True if missing values are being distributed. 

.. admonition::  public String missingMergeTipText()

   Returns the tip text for this property.

    Parameters
       None

    Returns
       Tip text for this property suitable for displaying in the explorer/experimenter gui

.. admonition::  public void setMissingMerge(boolean b)

   Distribute the counts for missing values across observed values

    Parameters
       * b: if true, distribute missing values.

    Returns
       None

.. admonition::  public boolean getMissingMerge()

   Get whether missing values are being distributed or not

    Parameters
       None

    Returns
       True if missing values are being distributed.


.. admonition:: public Capabilities getCapabilities()

   Returns the capabilities of this evaluator.

    Parameters
       None

    Returns
       The capabilities of this evaluator


.. admonition:: public void buildEvaluator(weka.core.Instances data)

   Initializes an information gain attribute evaluator. Discretizes all attributes that are numeric.

    Parameters
      *  data: data set of instances serving as training data

    Returns
       None

.. admonition::  public void updateEvaluator(Instance inst)

   Updates an information gain attribute evaluator. Discretizes all attributes that are numeric.

    Parameters
      * dnst: data set of instances serving as training data

    Returns
       None

.. admonition:: public void applySelection()

   Update the contingency tables and the rankings for each features using the counters. Counters are updated in each iteration.

    Parameters
       None

    Returns
       None

.. admonition:: protected void resetOptions()

   Reset options to their default values

    Parameters
       None

    Returns
       None

.. admonition:: protected void resetOptions()

   Evaluates an individual attribute by measuring the amount of information gained about the class given the attribute.

    Parameters
       * attribute: The index of the attribute to be evaluated

    Returns
       The info gain, chi-squared, symmetrical uncertainty, crammers or gain ratio

.. admonition::  public String toString() 

   Describe the attribute evaluator

    Parameters
       None

    Returns
       A description of the attribute evaluator as a string

.. admonition:: public String getRevision()

    Returns the revision string.

    Parameters
       None

    Returns
       The revision


Attribute Evaluator
----------------------

.. admonition:: class moa.featureselection.common.MOAAttributeEvaluator()

   Teste

Extremal Feature Selection
----------------------------

class moa.featureselection.algorithms.ExtremalFeatureSelection()

Evaluates the worth of an attribute through the computation of weights based on the Modified Balanced Winnow. Original paper on https://dl.acm.org/citation.cfm?doid=1150402.1150466.


.. admonition:: public ExtremalFeatureSelection(int numFeatures)
 	
	Constructor. 

    Parameters
        * numFeatures: Number of features to select.

    Returns
       None


.. admonition::  public String globalInfo()
 	
	Returns a string describing this attribute evaluator

    Parameters
       None

    Returns
       * A description of the evaluator suitable for displaying in the explorer/experimenter gui


.. admonition:: public String toString()
 	
	Describe the attribute evaluator

    Parameters
       None

    Returns
       * A description of the attribute evaluator as a string

.. admonition:: public String toString()
 	
	Describe the attribute evaluator

    Parameters
       None

    Returns
       * A description of the attribute evaluator as a string
    


.. admonition:: public static void getMaxMinValue(double[] numbers)
 	
	Get both maximum and minimum values of a given instance

    Parameters
        * numbers: Instance values

    Returns
       None

.. admonition:: public double[][] rankedAttributes(int[] m_attributeList, double[] m_attributeMerit)
 	
	Sorts instance's attribute values from best to worst

    Parameters
        * m_attributeList: Index of every attribute value
        * m_attributeMerit: Attribute values

    Returns
       None

.. admonition:: findLowestScore()
 	
	Find the lowest scores to include in the selected features according to authors criteria

    Parameters
       None

    Returns
       None

.. admonition:: public void updateModels(double[] normalizedData, double trueClass)
 	
	Necessary method from the ASEvalution class. Updates the positive and negative models.

    Parameters
        * normalizedData:  Instance normalized data
        * trueClass: Instance true class

    Returns
       None

.. admonition:: public void updateEvaluator(Instance inst)
 	
	Necessary method from the MOAAttributeEvalutor interface. Updates the evaluator with the new information.

    Parameters
        * inst:  The instance to be classified.

    Returns
       None

.. admonition:: public void applySelection()
 	
	Used to verify if a selection is needed.

    Parameters
       None

    Returns
       None


.. admonition:: public double evaluateAttribute(int attribute)
 	
	Evaluate attribute relevance.

    Parameters
       * attribute: The index of the attribute to be evaluated.

    Returns
       None


.. admonition::public void buildEvaluator(Instances data)
 	
	Generates a attribute evaluator. Has to initialize all fields of the evaluator that are not being set via options.

    Parameters
       * data: set of instances serving as training data.

    Returns
       None




Online Feature Selection
-------------------------

class moa.featureselection.algorithms.OFS()

Evaluates the worth of an attribute through the computation of weights using a linear classifier with sparse projection. Original paper on https://doi.ieeecomputersociety.org/10.1109/TKDE.2013.32.

.. admonition:: public String globalInfo()

   Returns a string describing this attribute evaluator.

    Parameters
        * numFeatures: Number of features to select.

    Returns
       A description of the evaluator suitable for displaying in the explorer/experimenter gui

.. admonition:: public String binarizeNumericAttributesTipText()

   Returns the tip text for this property

    Parameters
        * numFeatures: Number of features to select.

    Returns
       A tip text for this property suitable for displaying in the explorer/experimenter gui

.. admonition::  public void setBinarizeNumericAttributes(boolean b)

   Binarize numeric attributes.

    Parameters
        * b: if true, binarize numeric attributes

    Returns
       None 

.. admonition:: public boolean getBinarizeNumericAttributes()

   Get whether numeric attributes are just being binarized.

    Parameters
       None

    Returns
       True if missing values are being distributed. 

.. admonition::  public String missingMergeTipText()

   Returns the tip text for this property.

    Parameters
       None

    Returns
       Tip text for this property suitable for displaying in the explorer/experimenter gui

.. admonition::  public void setMissingMerge(boolean b)

   Distribute the counts for missing values across observed values

    Parameters
       * b: if true, distribute missing values.

    Returns
       None

.. admonition::  public boolean getMissingMerge()

   Get whether missing values are being distributed or not

    Parameters
       None

    Returns
       True if missing values are being distributed.


.. admonition:: public Capabilities getCapabilities()

   Returns the capabilities of this evaluator.

    Parameters
       None

    Returns
       The capabilities of this evaluator


.. admonition:: public void buildEvaluator(weka.core.Instances data)

   Initializes an sparse projection attribute evaluator.

    Parameters
      *  data: data set of instances serving as training data

    Returns
       None

.. admonition::  public void updateEvaluator(Instance inst)

   Updates an attribute evaluator using the sparse projection approach.

    Parameters
      * dnst: data set of instances serving as training data

    Returns
       None

.. admonition:: public void applySelection()

   Used to verify if a selection is needed.

    Parameters
       None

    Returns
       None

.. admonition:: protected void resetOptions()

   Reset options to their default values

    Parameters
       None

    Returns
       None

.. admonition:: protected void resetOptions()

   Evaluates an individual attribute by measuring its relevance through the sparse projection approach.

    Parameters
       * attribute: The index of the attribute to be evaluated

    Returns
       Attribute rank based on the sparse projection approach

.. admonition::  public String toString() 

   Describe the attribute evaluator

    Parameters
       None

    Returns
       A description of the attribute evaluator as a string

.. admonition:: public String getRevision()

    Returns the revision string.

    Parameters
       None

    Returns
       The revision