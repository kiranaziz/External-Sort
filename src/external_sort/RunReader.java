package external_sort;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A {@code RunReader} reads objects from a run. A {@code RunReader} can also be viewed as an {@code Iterator} over the
 * objects stored in a run.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class RunReader implements Iterator<Object> {
	LinkedList<Object> list = new LinkedList<Object>();
	Object nextItem;
	static int count = 0;
	ListIterator<Object> m = list.listIterator();
	Iterator<Object> i;
	
	/**
	 * The {@code ExternalSort} that uses this {@code RunReader}.
	 */
	ExternalSort<?> externalSort;

	/**
	 * The input buffer size
	 */
	int bufferSize;

	/**
	 * A {@code FileInputStream} for reading data from the run (i.e., the file)
	 */
	FileInputStream in;

	/**
	 * The length of the run.
	 */
	long runLength;

	/**
	 * The number of bytes read from the run.
	 */
	long bytesRead = 0;


	/**
	 * Constructs a {@code RunReader}.
	 * 
	 * @param fileName
	 *            the name of the file (i.e., the run)
	 * @param bufferSize
	 *            the input buffer size
	 * @param externalSort
	 *            the {@code ExternalSort} that uses the {@code RunReader}
	 * @throws IOException
	 *             if an I/O error occurs
	 * @throws ClassNotFoundException
	 *             if the class of a serialized object cannot be found
	 * 
	 */
	public RunReader(String fileName, int bufferSize, ExternalSort<?> externalSort)
			throws IOException, ClassNotFoundException {
		this.externalSort = externalSort;
		this.bufferSize = bufferSize;
		File file = new File(fileName);
		runLength = file.length();
		in = new FileInputStream(file);
	}

	/**
	 * Constructs an {@code InputBuffer} and fills it using the data from the run.
	 * 
	 * @return the filled {@code InputBuffer}
	 * @throws IOException
	 *             if an I/O error occurs
	 * @throws ClassNotFoundException
	 *             if the class of a serialized object cannot be found
	 */
	InputBuffer read() throws IOException, ClassNotFoundException {
		InputBuffer b = new InputBuffer(bufferSize);
		bytesRead += in.read(b.toByteArray());
		externalSort.increaseBufferReadCount();
		return b;
	}


	/**
	 * Determines whether or not this {@code RunReader} has more objects to iterate over (i.e., {@link #next} would
	 * return an object rather than throwing an exception).
	 *
	 * @return {@code true} if this {@code RunReader} has more objects to iterate over; {@code false} otherwise
	 */
	@Override
	public boolean hasNext() {
		// TODO complete this method (10 points)
		
		try {
			while(bytesRead < runLength) {
				i = read().iterator(); //input buffer 
				while(i.hasNext()) {		
					list.add(i.next());
				}				
			}
		} catch (ClassNotFoundException e1) {

		} catch (IOException e1) {

		}
		

		return m.hasNext();
	}

	/**
	 * Returns the next object in the iteration.
	 *
	 * @return the next object in the iteration
	 * @throws NoSuchElementException
	 *             if the iteration has no more objects to iterate over
	 */
	@Override
	public Object next() {
		// TODO complete this method (10 points)

		if(nextItem == null) {
			m = list.listIterator();
		}
		
		nextItem = m.next();
		return nextItem;
	}

}
