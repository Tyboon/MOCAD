#!/usr/bin/env python
# -*- coding: utf-8 -*-

import util
import indexation 
import align
import math

def extend(u,v,k = None) :
	if k == None :
		k = math.ceil(0.05*len(u))
	matrix, len_read, len_gen, read, genome = indexation.init(u,v,k)
	score,_,_ = indexation.backtrap(matrix, len_read, len_genome, read, genome)
	return score

def best_extend(r, genome, len_seed, len_read, len_genome) :
	max = Integer.MIN_VALUE
	for i in range(len_read - len_seed) :
		score_front = -1
		score_back = -1
		if r-2*len_read >= 0 :
			score_front = extend(genome[r-i:r], genome[r-2*len_read : r])
		if r+seed+1+2*read < len_genome :
			score_back = extend(genome[r+len_seed+1:r+read-i], genome[r+len_seed+1:r+seed+1+2*read])
		if score_front != -1 and score_back != -1 and score_front+score_back > max : 
			max = score_front+score_back
	return max

if __name__ == "__main__" :
	genome = util.open_fasta('data/genome.fasta')
	read = util.open_fasta('data/read.fasta')
	len_gen = len(genome)
	len_read = len(read)

	SA = indexation.createSA(genome)
	seed_size = int(math.ceil(math.log(len_gen, 4)))
	results_seed = indexation.search(genome, len_gen, SA, read[0:seed_size], seed_size)
	ind = -1
	max = Integer.MIN_VALUE
	for r in results_seed :
		score = best_extend(r, genome, seed_size, len_read)
		if score > max :
			max = score
			ind = r
	print max, ind
