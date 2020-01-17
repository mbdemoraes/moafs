Getting started
===============

Installation
------------
In order to get started with ``MOAFS``, you need to download it on your computer. 
There are two main approaches to do this:

*   You can click on this link `MOAFS`_;
*   Or you can clone the github repository and get the file from your local copy:

.. _MOAFS: https://github.com/mbdemoraes/moafs/raw/master/lib/moafs.jar

.. code-block:: bash

  git clone https://github.com/mbdemoraes/moafs
  cd lib
 
After the download, just add the ``moafs.jar`` file in the ``lib`` folder on the directory where MOA is installed. It can be done manually or by terminal. 
Here is an example from terminal using ``cp`` command in Linux:

.. code-block:: bash

  cp moafs.jar /home/athos/Documentos/datasets/usenet1.arff

Where ``/home/athos/Documentos/datasets/usenet1.arff`` is the directory where MOA is installed.

Dependencies
------------

* Java 8;
* MOA v2018.06 (found at `MOA`_).

.. _MOA: https://moa.cms.waikato.ac.nz/downloads/

Sample datasets
----------------

Sample datasets can be found on the repository of this project at Github.

Sample outputs
----------------

Sample outputs can be found on the repository of this project at Github.

Running GUI
------------

To run MOA's GUI with ``MOAFS`` as an extension, use the following comands from a command line:

Example (Windows):
^^^^^^^^^^^^^^^^^^^

From the lib folder where your MOA is installed:

.. code-block:: bash

  java -cp .;moafs.jar;moa.jar -javaagent:sizeofag-1.0.4.jar moa.gui.GUI

Or using full location:

.. code-block:: bash

  java -cp .;full_path_to_MOA_location/lib/moafs.jar;moa.jar -javaagent:full_path_to_MOA_location/lib/sizeofag-1.0.4.jar moa.gui.GUI

Example (Linux/mac):
^^^^^^^^^^^^^^^^^^^^^

From the lib folder where your MOA is installed:

.. code-block:: bash

  java -cp moafs.jar:moa.jar -javaagent:sizeofag-1.0.4.jar moa.gui.GUI

Or using full location:

.. code-block:: bash

  java -cp full_path_to_MOA_location/lib/moafs.jar:moa.jar -javaagent:full_path_to_MOA_location/lib/sizeofag-1.0.4.jar moa.gui.GUI

How to test this library
-------------------------

1. Download the datasets in arff format which you want to experiment. There are sample datasets available in this repository;
2. Run MOA with MOAFS, from command line or using GUI;
3. Follow the examples demonstrated in the Examples sections.
