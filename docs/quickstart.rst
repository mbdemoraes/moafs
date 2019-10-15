Examples (command line)
=======================

To run MOA with ``MOAFS`` from command line, you can use the following commands. ``/home/athos/Documentos/datasets/usenet1.arff`` is the directory in your computer where the data set is located.

Parameters
-----------
``MOAFS`` uses a set of different parameters. They are: 

* ``-f``: Reduction rate - The number of features to select (default = 10)
* ``-w``: Processing window - The number of instances to process using the specified reduction rate (default = 1)
* ``-m``: Feature Selection Method - Feature selection method to be used. Options: 0. No method. 1. Information Gain 2. Symmetrical Uncertainty 3. Chi-Squared 4. Cramers V-Test 5. Gain Ratio 6. Extremal Feature Selection 7. Online Feature Selection


Classification without feature selection (No method)
----------------------------------------------------

.. code-block:: bash

  java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"


Information Gain
-----------------

.. code-block:: bash

  java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes -f 40 -m 1) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"


Symmetrical Uncertainty
------------------------

.. code-block:: bash

  java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes -f 40 -m 2) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"

Chi-Squared
------------

.. code-block:: bash

  java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes -f 40 -m 3) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"

Cramers V-Test
---------------

.. code-block:: bash

  java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes -f 40 -m 4) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"


Gain Ratio
----------

.. code-block:: bash

  java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes -f 40 -m 5) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"


Extremal Feature Selection
--------------------------

.. code-block:: bash

  java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes -f 40 -m 6) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"


Online Feature Selection
-------------------------

.. code-block:: bash

  java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes -f 40 -m 6) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"


Changes in Reduction rate
--------------------------

Simply change the value for the ``-f`` parameter in your command line. If you do not want to perform any reduction, just omit it. For instance:

.. code-block:: bash

  java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"

If you want a particular number, e.g. 4000 attributes, add it after the ``-f`` parameter:

.. code-block:: bash

  java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes -f 4000 -m 6) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"


Changes in Processing window
-----------------------------

Simply change the value for the ``-w`` parameter in your command line. If you do not want to specify a processing window, just omit it and the default (1) will be used. For instance: 

.. code-block:: bash

  java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"

If you want a particular number, e.g. 1000 instances, add it after the ``-w`` parameter:  

.. code-block:: bash

  java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes -f 4000 -m 6 -w 1000) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"


For further documentation on MOA, please refer to https://moa.cms.waikato.ac.nz/documentation/.