To compile: javac src/*.java
To run: java Main [filename] [args]
args:
	0 for Nearest Neighbor without 2-opt optimization
	1 for Nearest Addition without 2-opt optimization
	2 for Nearest Neighbor with 2-opt optimization
	3 for Nearest Addition with 2-opt optimization
	No argument for default (decides Nearest Neighbor or Nearest Addition based on size, then 2-opt optimization)

You probably want `java Main (path-to-file).txt` for verifying the test cases
