package searchengine.dictionary;

	/* 
	 * PQueueADT.java
	 * 
	 * ADT for Priority Queue implemented with Minimum Priority or Maximum Priority
	 */
	 
	public interface PQueueADT <E extends Comparable<E>> {
	 
		/*
		 * Inserts the element in the priority Queue
		 */
		public void enqueue(E value);
	 
		/*
		 * Deletes the Priority(first element) element from the Queue.
		 */
		public E dequeue();
		/*
		 * Display all elements;
		 */
		public void disp();
		/*
		 * Returns the number of elements of the Queue
		 */
		public int size();
	 
		/*
		 * Returns true if Queue is empty and false otherwise. 
		 */
		public boolean is_empty();
	 
		/*
		 * Returns the Priority(first element) element of the Queue 
		 */
		public E front();
	 
	}
