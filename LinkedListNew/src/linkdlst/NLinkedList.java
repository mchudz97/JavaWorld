package linkdlst;

public class NLinkedList<E> {
	private Node<E> head;
	private Node<E> tail;
	NLinkedList(){
		clear();
	}
	public void clear(){ //czysci liste
		head=null;
		tail=null;
	}
	public Node<E> findNode(E value){ //szukanie po wartosci
		Node<E> currentNode=head;
		while(currentNode!=null)
		{
			if(currentNode.getValue()==value){
				return currentNode;
			}
			currentNode=currentNode.getNext();
		}
		return null;
	}
	public int size() {
	    int size = 0;
	    Node<E> currentNode = head;
	    while (currentNode != null) {
	        size++;
	        currentNode = currentNode.next;
	    }
	    return size;
	}
	public boolean isEmpty(){ // czy lista pusta
		return head==null;
	}
	public void killAllNodes(){ // usuwa wezly
		while(size()>0){
			remove(size()-1);
		}
	

	}
	public void addNext(E node){
		if(head==null){
			head=new Node<E>(node);
			tail=head;
		}
		else{
		Node<E> tempLast=tail;
		tail= new Node<E>(node);
		tail.previous=tempLast;
		tempLast.next=tail;
		}
	}
	public Node<E> getNode(int index){ // szukanie po indexie
		Node<E> currentNode=head;
		int currentIndex= index;
		while(currentIndex>0){
			currentNode=currentNode.getNext();
			currentIndex--;
		}
		return currentNode;
	}
	public boolean add(int index, E node){ //funkcja wstawiajaca element node pod index
		if(head==null && index==0){ // jak lista jest pusta
			head=new Node<E>(node);
			tail=head;
			return true;
		}
		Node<E> loadedNode=getNode(index);
		if(loadedNode==null){ // ostatnie miejsce (tail)
			Node<E> tempLast=tail;
			tail= new Node<E>(node);
			tail.previous=tempLast;
			tempLast.next=tail;
			return true;
		}
		if(loadedNode.getPrevious()==null){ //na poczatek
			Node<E> tempHead = head;
	        head = new Node<>(node);
	        head.next = tempHead;
	        tempHead.previous = head;
	        return true;
	    
		}
		Node<E> added =new Node<>(node); // w wewnetrzny wezel
		Node<E> previous =loadedNode.previous;
		previous.next=added;
		loadedNode.previous=added;
		added.next=loadedNode;
		return true;
	}
	public void print(){
		for(int i=0;i<size();i++){
			System.out.print(getNode(i).getValue()+ "   ");
		}
		System.out.println();
	}
	public E remove(int index) {
	    Node<E> nodeToRemove = getNode(index);
	    Node<E> previousNode = nodeToRemove.previous;
	    Node<E> nextNode = nodeToRemove.next;
	    E removedValue = nodeToRemove.value;

	    // usuwa 1wszy element
	    if (previousNode == null) {
	        if (nextNode == null) { // gdy jest sam
	            head = null;
	            tail = null;
	        }
	        else {
	            head = nextNode; // gdy jest powiazany z kolejnym elementem
	            nextNode.previous = null;
	        }
	        return removedValue;
	    }

	    //gdy jest ostatni
	    if (nextNode == null) {
	        tail = previousNode;
	        previousNode.next = null;
	        return removedValue;
	    }

	    previousNode.next = nextNode; // wewnetrzny element
	    nextNode.previous = previousNode;

	    return removedValue;
	}
	public NLinkedList<E> bezPowtorzen(){
		Node<E> currentNode= this.head;
		NLinkedList<E> output= new NLinkedList<E>();
		while(currentNode!=null){
			if(output.findNode(currentNode.getValue())==null){
				output.addNext(currentNode.getValue());
			}
			currentNode=currentNode.next;
		}
		return output;	
	}
	public NLinkedList<E> scal(NLinkedList<E> nl){
		NLinkedList<E> newL= new NLinkedList<E>(); 
		newL.head=this.head;
		newL.tail=nl.tail;
		this.tail.next=nl.head;
		nl.head.previous=this.tail;
		return newL;
		
		
		
		
	}

	static private class Node<E>{
		private E value;
		private Node<E> previous;
		private Node<E> next;
		public Node(E v){
			this(v, null);
		}
		public Node(E v, Node<E> p, Node<E> n){
			this.value=v;
			this.previous=p;
			this.next=n;
		}
		public Node(E v, Node<E> n){
			this.value=v;
			this.next=n;
		}
		public Node<E> getPrevious() {
			return previous;
		}
		public void setPrevious(Node<E> previous) {
			this.previous = previous;
		}
		public E getValue() {
			return value;
		}
		public void setValue(E value) {
			this.value = value;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
	
	
	}
	public static void main(String[] args) {
		NLinkedList<String> l= new NLinkedList<String>();
		l.add(0, "start");
		l.addNext("cos");
		//l.print();
		//l.killAllNodes();
		//l.killAllNodes();
		l.add(0, "start");
		l.addNext("cos");
		l.add(2, "313131");
		l.add(2, "b3");
		l.add(3, "addd");
		l.addNext("tekst");
		//System.out.println(l.findNode("cos").getValue());
		//l.remove(2);
		l.print();
		NLinkedList<String> l2= l.bezPowtorzen();
		l2.print();
		NLinkedList<String> l3= l.scal(l2);
		l3.print();
		
	}
}
