/** -*- mode: c++; c-indent-level: 4; c++-member-init-indent: 8; comment-column: 35; -*-

The above line is usefulin Emacs-like editors
 */

/*
Template for simple quadratic crossover operators
=================================================

Quadratic crossover operators modify the both genotypes
*/

#ifndef eoJobShopQuadCrossover_H
#define eoJobShopQuadCrossover_H

#include <eoOp.h>

/**
 *  Always write a comment in this format before class definition
 *  if you want the class to be documented by Doxygen
 *
 * THere is NO ASSUMPTION on the class GenoypeT.
 * In particular, it does not need to derive from EO
 */
template<class GenotypeT>
class eoJobShopQuadCrossover: public eoQuadOp<GenotypeT>
{
public:
  /**
   * Ctor - no requirement
   */
// START eventually add or modify the anyVariable argument
  eoJobShopQuadCrossover()
  //  eoJobShopQuadCrossover( varType  _anyVariable) : anyVariable(_anyVariable)
// END eventually add or modify the anyVariable argument
  {
    // START Code of Ctor of an eoJobShopEvalFunc object
    // END   Code of Ctor of an eoJobShopEvalFunc object
  }

  /// The class name. Used to display statistics
  string className() const { return "eoJobShopQuadCrossover"; }

  /**
   * eoQuad crossover - _genotype1 and _genotype2 are the (future)
   *       offspring, i.e. _copies_ of the parents, to be modified
   * @param eo1 The first parent
   * @param eo2 The second parent
   */
  bool operator()(eoJobshop & _eo1, eoJobShop & _eo2)
  { //2points
        bool oneAtLeastIsModified(true);
	unsigned int N = _eo1.getSize();
	unsigned int p1, p2; //random int;
	do
	{
		p1 =  rng.random(N);
		p2 =  rng.random(N);
	}
	while (fabs((double) point1-point2) <= 2);
	
	vector<eoJobShop> offspring;
	offspring[0] = generateOffspring(eo1, eo2, p1, p2);
	offspring[1] = generateOffspring(eo2, eo1, p1, p2);
	if ((eo1 != offspring[0]) || (eo2 != offspring[1]))
	{
		eo1 = offspring[0];
		eo2 = offspring[1];
		oneAtLeastIsModified = true;
	}
	else
	{
		oneAtLeastIsModified = false;
	}
    return oneAtLeastIsModified;
  }
	//BLOCK a MAJ !! 
  eoJobShop eoJobShopCrossoverQuad::generateOffspring(const eoJobShop & parent1, const eoJobShop & parent2, unsigned int p1, unsigned int p2)
{
	eoJobShop result = parent1;
	std::vector<bool> taken_values(result.size(), false);
	// vérifie et corrige point1 < point2
	if (p1 > p2)
		std::swap(p1, p2);
	for (unsigned int i =0; i <= point1; i++)
	{
		taken_values[parent1.getJobShop(i)[0]] = true; // 
	}
	for (unsigned int i =p2; i < result.size(); i++)
	{
		taken_values[parent1.getJobShop(i)[0]] = true;
	}
	unsigned int i = p1 + 1;
	unsigned j = 0;
	while (i < p2 && j < parent2.size())
	{
		if (!taken_values[parent2.getJobShop(i)[0]])
		{
			result[i] = parent2.getJobShop(i);
			i++;
		}
		j++;
	}
	return result;	
}
  
private:
// START Private data of an eoJobShopQuadCrossover object
  //  varType anyVariable;		   // for example ...
// END   Private data of an eoJobShopQuadCrossover object
};

#endif
