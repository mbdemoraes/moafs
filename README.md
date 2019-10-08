#Abstract

`MOAFS` is a library for the Massive Online Analysis (MOA) [1] framework. It is based on the MOAReduction [2] extension and contains the implementation of seven feature selection algorithms used as dimensionality reduction techniques in data streams classification problems, especially in the text-domain field.

#Installation and requirements

Simply download the `moafs.jar` and add this file in the "lib" folder in the directory where MOA is installed. Then add `moafs.jar` to the classpath when launching MOA:

Example (Windows):

> java -cp .;lib/moafs.jar;moa.jar;lib/guava-20.0.jar;lib/weka.jar;lib/jCOLIBRI2.jar -javaagent:sizeofag-1.0.0.jar moa.gui.GUI

Example (Linux/mac):

> java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.gui.GUI

Requirements: Java 8 and [MOA (v2018.06)] (https://moa.cms.waikato.ac.nz/downloads/).

#Example from the command line

java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes -f 4000 -m 3) -s (ArffFileStream -f /home/athos/Documentos/datasets/spam_assassin.arff) -f 100"
