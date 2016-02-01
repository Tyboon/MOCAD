/** -*- mode: c++; c-indent-level: 4; c++-member-init-indent: 8; comment-column: 35; -*-

The above line is useful in Emacs-like editors
 */

/*
Template for simple mutation operators
======================================
*/

#ifndef eoJobShopObjectiveVectorTraits_H
#define eoJobShopObjectiveVectorTraits_H


#include <eoOp.h>  //voir la lib to import

class eoJobShopObjectiveVectorTraits: public moeoObjectiveVectorTraits {
public: 
	static bool minimizing(int i) { return true ;}
	static bool maximizing(int i) { return false;}
	static unsigned int nbObjective() {return 2;}
}

