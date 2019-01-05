/**
Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

Example:

Assume that the iterator is initialized to the beginning of the list: [1,2,3].

Call next() gets you 1, the first element in the list.
Now you call peek() and it returns 2, the next element. Calling next() after that still return 2. 
You call next() the final time and it returns 3, the last element. 
Calling hasNext() after that should return false.
Follow up: How would you extend your design to be generic and work with all types, not just integer?
**/

class PeekingIterator implements Iterator<Integer> {
		Iterator<Integer> cur;
		int tmp;
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
		cur = iterator;
		tmp = Integer.MIN_VALUE;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        	if(tmp == Integer.MIN_VALUE) {
			tmp = cur.hasNext() ? cur.next() : Integer.MIN_VALUE;
		}
		return tmp;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		if(tmp != Integer.MIN_VALUE) {
			int res = tmp;
			tmp = Integer.MIN_VALUE;
			return res;
		}
		return cur.next();
	}

	@Override
	public boolean hasNext() {
	    return cur.hasNext() || tmp != Integer.MIN_VALUE;
	}
}