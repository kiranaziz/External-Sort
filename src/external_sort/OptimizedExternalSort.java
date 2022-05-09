package external_sort;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * An {@code OptimizedExternalSort} sorts data elements in ascending order according to the {@linkplain Comparable
 * natural ordering} of the elements. An {@code OptimizedExternalSort} can also be used as an {@code Iterator} over all
 * of these sorted data elements. {@code OptimizedExternalSort} is faster than {@code ExternalSort} due to its lower
 * disk I/O overhead.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 *
 * @param <T>
 *            the type of the elements
 */
public abstract class OptimizedExternalSort<T extends Comparable<? super T>> extends ExternalSort<T> {

	/**
	 * Constructs an {@code OptimizedExternalSort}
	 * 
	 * @param iterator
	 *            the input {@code Iterator}
	 * @param degree
	 *            the number of runs that are merged together to form a new run
	 * @param bufferSize
	 *            the buffer size
	 * @param fileNamePrefix
	 *            the prefix that all of the run names (i.e., the names of the run files) will commonly have
	 * @param out
	 *            a {@code PrintStream}
	 * @throws IOException
	 *             if an I/O error occurs
	 * @throws ClassNotFoundException
	 *             if the class of a serialized object cannot be found
	 */
	public OptimizedExternalSort(Iterator<T> iterator, int degree, int bufferSize, String fileNamePrefix,
			PrintStream out) throws FileNotFoundException, IOException, ClassNotFoundException {
		super(iterator, degree, bufferSize, fileNamePrefix, out);
	}

	// TODO complete this class (5 points) so that it runs faster than ExternalSort due to lower disk I/O overhead

	
//		My optimization for Part 6 of the assignment can be seen in the ExternalSort.java file.
//		I added the following line to my original merge() function:
//	
//		Collections.sort(list.subList(1, list.size()));
//	
// 		I decided to sort the list of run names beforehand to reduce
//		the number of buffer reads, buffer writes, and overall time.
//		I commented out this code for now, but to run my Part 6 and see the optimization,
//	 	just uncomment the line with the sorting.
	

}
