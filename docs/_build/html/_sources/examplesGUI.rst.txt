Examples (GUI)
==============

No method
---------

.. code-block:: bash

  java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"


Information Gain
-----------------

.. code-block:: bash

  java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes -f 40 -m 1) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"


Symmetrical Uncertainty
-----------------------

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
-----------

.. code-block:: bash

  java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes -f 40 -m 5) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"



Extremal Feature Selection
---------------------------

.. code-block:: bash

  java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes -f 40 -m 6) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"


Online Feature Selection
-------------------------

.. code-block:: bash

  java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes -f 40 -m 6) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"




