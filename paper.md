---
title: 'MOAFS: A Massive Online Analysis library for feature selection in data streams'
tags:
  - Java
  - feature selection
  - data streams
  - concept drift
  - moa
authors:
  - name: Matheus Bernardelli de Moraes
    orcid: 0000-0002-9485-0334
    affiliation: "1" # (Multiple affiliations must be quoted)
  - name: André Leon Sampaio Gradvohl
    orcid: 0000-0002-6520-9740
    affiliation: 1
affiliations:
 - name: Faculty of Technology, University of Campinas
   index: 1
date: 08 October 2019
bibliography: paper.bib
---

# Summary

Data streams are continuous, potentially unbounded and high-dimensional data, transmitted at high-volume and high-velocity, which turns impracticable its storage in traditional database mechanisms [@Ramirez-Gallego:2017]. In such cases, data streams have to be processed and analyzed online. However, as it is potentially unbounded, it is expected a change in data probabilistic distribution over time, the Concept Drift phenomenon. This phenomenon turns the online data process and analysis completely dynamic. Using classification algorithms is one approach to learn from data streams, as it will categorize the data into different classes for future decisions. However, data streams high dimensionality imposes a challenge on the classification process, since it increases both computational cost and time, as well as aggravate the concept drift impacts. To solve this problem, online feature selection algorithms have been proposed to reduce data dimensionality by removing irrelevant and redundant attributes from the data streams. 

# Statement of need

However, not every algorithm performs efficiently in all environments, especially when handling concept drift. Therefore, to perform different experiments on some of the most relevant feature selection algorithms proposed for data streams classification problems, `MOAFS` was created. `MOAFS` is a library for the Massive Online Analysis (MOA) [@Bifet:2010] framework, and it is based on the MOAReduction [@Ramirez-Gallego:2017] extension. It contains seven feature selection algorithms to be used as dimensionality reduction techniques in data streams classification problems, especially in the text-domain field, since they are not directly available on MOA. `MOAFS` includes incremental versions of information-based filter algorithms such as Information Gain and Gain Ratio [@Quinlan:1986], Symmetrical Uncertainty used by the Fast Correlation-Based Filter [@Yu:2003], Chi-squared [@Pearson:1990] and Cramers V-Test [@Cramer:1946], as well as wrapper algorithms such as Online Feature Selection [@Wang:2014] and Extremal Feature Selection [@Carvalho:2006]. 


# Acknowledgements

This work was supported by the Brazilian Coordination for the Improvement of Higher Education Personnel (CAPES).

# References
