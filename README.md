# Abstract

`MOAFS` is a library for the Massive Online Analysis (MOA) [1] framework. It is based on the [MOAReduction](https://linkinghub.elsevier.com/retrieve/pii/S0925231217302631) extension and contains the implementation of seven feature selection algorithms to be used as dimensionality reduction techniques in data streams classification problems, especially in the text-domain field.

# Installation and requirements

Simply download the `moafs.jar` from the lib directory in this repository and add the file to the "lib" folder in the directory where MOA is installed. Then add `moafs.jar` to the classpath when launching MOA:

Example (Windows):

> java -cp .;lib/moafs.jar;moa.jar;lib/guava-20.0.jar;lib/weka.jar;lib/jCOLIBRI2.jar -javaagent:sizeofag-1.0.0.jar moa.gui.GUI

Example (Linux/mac):

> java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.gui.GUI

Requirements: Java 8 and [MOA v2018.06](https://moa.cms.waikato.ac.nz/downloads/).

# Parameters

`MOAFS` uses a set of different parameters:

* -f: Reduction rate - The number of features to select (default = 10)
* -w: Processing window -  The number of instances to process using the specified reduction rate (default = 1)
* -m: Feature Selection Method - Feature selection method to be used. Options: 0. No method. 1. Information Gain 2. Symmetrical Uncertainty 3. Chi-Squared 4. Cramers V-Test 5. Gain Ratio 6. Extremal Feature Selection 7. Online Feature Selection

# Examples from the command line

Here is an example using the Interleaved-Test-Then-Train approach with the Online Feature Selection algorithm on the [Usenet1]() data set, selecting 20 features:

```
java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes -f 20 -m 3) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"
```

Another example on the same data set using Information Gain, selecting 40 features:

```
java -cp moafs.jar:moa.jar:weka.jar -javaagent:sizeofag-1.0.4.jar moa.DoTask "EvaluateInterleavedTestThenTrain -l (moa.featureselection.classifiers.NaiveBayes -f 40 -m 1) -s (ArffFileStream -f /home/athos/Documentos/datasets/usenet1.arff) -f 100"
```