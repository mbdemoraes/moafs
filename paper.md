---
title: 'OFSER: Online Feature Selection with Evolving Regularization for Massive Online Analysis'
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
date: 12 August 2019
bibliography: paper.bib
---

# Summary

Online Feature Selection with Evolving Regularization (OFSER) is a proposed feature selection
algorithm for dimensionality reduction on data streams. It is a modified version of
the Online Feature Selection (OFS) method. The OFSER calculates a new regularization
value for each new instance instead of a fixed, user-input definition used by OFS. With this approach,
it increases or decreases regularization on demand, setting weights
for each attribute in an evoluationary way. This process improves the selection of new relevant features
even in the presence of the Concept Drift phenomenon, the change on data's probabilistic distribution 
over time.

The OFSER-lib is a library for the Massive Online Analysis (MOA) framework. It contains the implementation of OFSER and
a collection of seven other feature selection algorithms for performance comparisons, including incremental versions of Information Gain, Symmetrical Uncertainty, Chi-squared and Cramers V-Test, as well as OFS and Extremal Feature Selection (EFS) algorithms.



# Mathematics

Single dollars ($) are required for inline mathematics e.g. $f(x) = e^{\pi/x}$

Double dollars make self-standing equations:

$$\Theta(x) = \left\{\begin{array}{l}
0\textrm{ if } x < 0\cr
1\textrm{ else}
\end{array}\right.$$


# Citations

Citations to entries in paper.bib should be in
[rMarkdown](http://rmarkdown.rstudio.com/authoring_bibliographies_and_citations.html)
format.

For a quick reference, the following citation commands can be used:
- `@author:2001`  ->  "Author et al. (2001)"
- `[@author:2001]` -> "(Author et al., 2001)"
- `[@author1:2001; @author2:2001]` -> "(Author1 et al., 2001; Author2 et al., 2002)"


# Acknowledgements

This work was supported by the Brazilian Coordination for the Improvement of Higher Education Personnel (CAPES).

# References
