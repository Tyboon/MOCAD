/** -*- mode: c++; c-indent-level: 4; c++-member-init-indent: 8; comment-column: 35; -*-

The above line is usefulin Emacs-like editors
 */

/*
Template for creating a new representation in EO
================================================

Mandatory:
- a default constructor (constructor without any argument)
- the I/O functions (readFrom and printOn)

However, if you are using dynamic memory, there are 2 places
to allocate it: the default constructor (if possible?), or, more in
the EO spirit, the eoInit object, that you will need to write anyway
(template file init.tmpl).

But remember that a COPY CONSTRUCTOR will be used in many places in EO,
so make sure that the default copy constructor works, or, even better,
do write your own if in doubt.
And of course write the corresponding destructor!

*/

#ifndef _eoJobShop_h
#define _eoJobShop_h

/**
 *  Always write a comment in this format before class definition
 *  if you want the class to be documented by Doxygen

 * Note that you MUST derive your structure from EO<fitT>
 * but you MAY use some other already prepared class in the hierarchy
 * like eoVector for instance, if you handle a vector of something....

 * If you create a structure from scratch,
 * the only thing you need to provide are
 *        a default constructor
 *        IO routines printOn and readFrom
 *
 * Note that operator<< and operator>> are defined at EO level
 * using these routines
 */
template< class FitT>
class eoJobShop: public EO<FitT> {
public:
  /** Ctor: you MUST provide a default ctor.
   * though such individuals will generally be processed
   * by some eoInit object
   */
  eoJobShop()
  {
    // START Code of default Ctor of an eoJobShop object
    // END   Code of default Ctor of an eoJobShop object
  }

  /** Copy Ctor: you MUST provide a copy ctor if the default
   * one is not what you want
   * If this is the case, uncomment and fill the following
   */
  /*
  eoJobShop(const eoJobShop &)
  {
    // START Code of copy Ctor of an eoJobShop object
    // END   Code of copy Ctor of an eoJobShop object
  }
  */


  virtual ~eoJobShop()
  {
    // START Code of Destructor of an eoEASEAGenome object
    // END   Code of Destructor of an eoEASEAGenome object
  }

  virtual string className() const { return "eoJobShop"; }

  /** printing... */
    void printOn(ostream& os) const
    {
      // First write the fitness
      EO<FitT>::printOn(os);
      os << ' ';
    // START Code of default output

	/** HINTS
	 * in EO we systematically write the sizes of things before the things
	 * so readFrom is easier to code (see below)
	 */

    // END   Code of default output
    }

  /** reading...
   * of course, your readFrom must be able to read what printOn writes!!!
   */
    void readFrom(istream& is)
      {
	// of course you should read the fitness first!
	EO<FitT>::readFrom(is);
    // START Code of input

	/** HINTS
	 * remember the eoJobShop object will come from the default ctor
	 * this is why having the sizes written out is useful
	 */

    // END   Code of input
      }
      
      eoVector<int> getJobShop(int i) 
	{
			return jobs[i];
	}
	
	unsigned int getsize()
	{
		return jobs.size();
	}
	
	void putJobShop(int j, eoVector<int> job)
	{
		jobs[j] = jobs;
	}

	int getBlock(int i) 
	{
			return blocks[i];
	}
	
	void addBlock(int i)
	{
			// jouter au bonne endroit
	}
	
	void deleteBlock(int i)
	{
		//TODO
	}
	
	void modifyBlock(int i) 
	{
		//TODO à voir si utile
	}
private:			   // put all data here
	eoVector<eoVector<int>> jobs ; //num data, idle time
	eoVector<int> blocks; //locus, completude
};

#endif
