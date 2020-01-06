Getting started
===============

Installation
------------
In order to get started with ``MOAFS``, you need to download it on your computer. 
There are two main ways to to this:

*   You can click on this link `MOAFS`_;
*   Or you can clone the github repository and get the file from your local copy:

.. _MOAFS: https://github.com/mbdemoraes/moafs/raw/master/lib/moafs.jar

.. code-block:: bash

  git clone https://github.com/mbdemoraes/moafs
  cd lib
 
After the download, just add the ``moafs.jar`` file in the ``lib`` folder on the directory where MOA is installed. It can be done manually or via terminal. 
Here is an example doing this from terminal using ``cp`` command in Linux:

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

Sample datasets can be found on the repository at Github.

Running GUI
------------

To run MOA's GUI with MOAFS as an extension, use the following comands from a command line:

Example (Windows):

.. code-block:: bash

  java -cp .;moafs.jar;moa.jar -javaagent:sizeofag-1.0.4.jar moa.gui.GUI

Example (Linux/mac):

.. code-block:: bash

  java -cp moafs.jar:moa.jar -javaagent:sizeofag-1.0.4.jar moa.gui.GUI
