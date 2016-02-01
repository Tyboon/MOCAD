/** -*- mode: c++; c-indent-level: 4; c++-member-init-indent: 8; comment-column: 35; -*-

The above line is usefulin Emacs-like editors
 */

/*
Template for evaluator in EO, a functor that computes the fitness of an EO
==========================================================================
*/

#ifndef _eoJobShopEvalFunc_h
#define _eoJobShopEvalFunc_h

// include whatever general include you need
#include <stdexcept>
#include <fstream>

// include the base definition of eoEvalFunc
#include "eoEvalFunc.h"
#include "Data.h"
/**
  Always write a comment in this format before class definition
  if you want the class to be documented by Doxygen
*/
template <class EOT>
class eoJobShopEvalFunc : public eoEvalFunc<EOT>
{
public:

  eoJobShopEvalFunc(Data d)

  {
	data = d;
  }
  
  vector<int> tard_early (_eo) 
  {
	  int t = 0;
	  int comp = 0;
	  vector<vector<int>> results;
	  vector<int> res;
	  for (int i = 0; i < data.getN(); i++) 
	  {
		vector<int> j = _eo.getJobShop(i);
		Jobs job = data.getJob(j[0]);
		results[i][0] = max(0, job.getD() - (comp + job.getP() + j[1])) ; // tardiness
		results[i][1] = max(0, comp + j[1] - (job.getD() - job.getP())) ; //earliness
		comp += j[1] + job.getP();
	  }
	  res[0] = sum(results[:][0]);
	  res[1] = sum(results[:][1]);
	  return res;
  }

  /** Actually compute the fitness
   *
   * @param EOT & _eo the EO object to evaluate
   *                  it should stay templatized to be usable
   *                  with any fitness type
   */
  void operator()(eoJobShop & _eo)
  {
    // test for invalid to avoid recomputing fitness of unmodified individuals
    if (_eo.invalid())
      {
	eoJobShopObjectiveVector objVec;
	vector<int> res = tard_early(_eo);
	objVec[0] = res[0];
	objVec[1] = res[1];
	_eo.objectiveVector(objVec);
      }
  }

private:
	Data data ;

};


#endif
