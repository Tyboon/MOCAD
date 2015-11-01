#!/usr/bin/env python
# -*- coding: utf-8 -*-

def open_fasta (filename):
	'''
	Ouvre un fichier au format fasta(pas de fasta multi-séquence), enleve la ligne 
	de commentaire et les caractères de saut de ligne et charge les données dans une string
	:param filename: Le fichier fasta dans lequel on extrait des données.
	:type filename: file
	:return: Une chaine de caractère contenant les données du fichier fasta
	:rtype: string
	'''
	with open(filename,'r') as fasta:
		res = ''
		for line in fasta :
			if line[0] != '>':
				res+= line.strip('\n')
	return res

def open_fastq (filename) :
	'''
	Ouvre un fichier au format fastq
	:param filename : Le fichier fastq d'entrée
	:type filename : file
	:return : La liste des reads
	:rtype : List
	'''
	with open(filename,'r') as fastq : 
		res = []
		boo = False
		for line in fastq :
			if boo :
				res.append(line)
				boo = False
			elif line[0] == '@' :
				boo = True
			elif line[0] == '+' :
				boo = False
	return res
