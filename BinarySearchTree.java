package BinarySearchTree;


/* BinarySearchTree class represents a Threaded binary search tree data structure 
 * that every node that do not have left child, pointing on the left to his predecessor
 * every node that have no right child, pointing on the right on his successor
 * this class have public functions:
 * insert 		- function to insert a node to the bst by given key
 * delete 		- function to delete a node of the bst by given key
 * successor 	- function to find successor of a node that given by his key
 * predecessor	- function to find predecessor of a node that given by his key
 * minimum		- function to find the node with the minimum value key
 * maximum		- function to find the node with the maximum value key
 * find			- function to check if the bst have node that contain the given key
 * preorder		- function to display the bst in preorder at the stdout
 * inorder		- function to display the bst in inorder at the stdout
 * postorder	- function to display the bst in postorder at the stdout
 * 
 */
public class BinarySearchTree {
	
	private Node root;
	private Node median;
	private int nodesCount;
	
		
	
		
	// new bst constructor sets the root and median as null
	public BinarySearchTree(){
		this.root = null;
		this.median = null;
	}
	
	// return the root of the bst
	public Node getRoot() {
		return this.root;
	}
	
	// set the root of the bst by given node as parameter
	public void setRoot(Node rootToSet) {
		this.root = rootToSet;
	}
	
	// return the median of the bst
	public Node getMedian() {
		return this.median;
	}
	
	// set the median of the bst by given node as parameter
	public void setMedian(Node medianToSet) {
		this.median = medianToSet;
	}
	
	// return the number of the nodes in the bst
	public int getNodesCount() {
		return this.nodesCount;
	}
	
	// set the nodes number of the bst
	public void setNodesCount(int numToSet) {
		this.nodesCount = numToSet;
	}
	
	
	// ====================================================== insert and delete functions ====================================================== //
	
	
	/**
	 * function to insert a node to the bst
	 * time complexity O(h) the h the height of the bst
	 * @param - int keyToInsert the value of the node key
	 */
	public void insert(int keyToInsert) {
		

		Node newNode = new Node(keyToInsert);
		Node current = this.root;
		this.nodesCount++;
		
		// if the bst is empty sets the root as the node
		if (this.root == null) {
			this.root = newNode;
			this.median = this.root;
			System.out.println(keyToInsert + " Has been inserted");
			return;
		}
		
		while (true) {
			
			// key is smaller value then the current node
			if(current.getNumber() > keyToInsert) {
				// current node have left child
				if(haveLeftChild(current)) {
					// move left to smaller value then the current node value
					current = current.getLeft();
					
					// else the current node have no left child
				}else {
					// parent of the new node is current node
					newNode.setParent(current);
					// set current left point to the new node 
					current.setLeft(newNode);
					// new node point left to his predecessor 
					newNode.setLeft(predecessor(newNode.getNumber()));
					// new node point right to his successor
					newNode.setRight(successor(newNode.getNumber()));
					
					// if new node predecessor have no right child
					if(!haveRightChild(newNode.getLeft())) {
						// set the new node to be predecessor of his successor
						if(newNode.getLeft() != null) {
							newNode.getLeft().setRight(newNode);
						}
						
					// if new node successor have no left child
					}if(!haveLeftChild(newNode.getRight())) {
						// set the new node as successor of his predecessor
						if(newNode.getRight() != null) {
							newNode.getRight().setLeft(newNode);	
						}	
					}
					
					/* if the new node have more then one appearance in the bst and they do not have left child,
					 * set the left pointer of every node with same value as pointer to the node predecessor 
					 */
					current = newNode.getRight();
					while((haveRightChild(current) && current.getNumber() == current.getRight().getNumber() 
							&& (current.getRight().getLeft() == null || newNode.getNumber() > current.getRight().getLeft().getNumber()))) {
						current.getRight().setLeft(newNode);
						current = current.getRight();
						
					}
					break;
				}
				
				// the key to insert is greater then the current node key
			}else if (current.getNumber() < keyToInsert) {
				// if current node have right child move right to greater value node
				if(haveRightChild(current)) {
					current = current.getRight();
					
					// else the current node have no right child
				}else {
					// set new node parent pointer to point on the current node
					newNode.setParent(current);
					// set current node right pointer to point on the new node
					current.setRight(newNode);
					// set the new node right pointer to point to his successor
					newNode.setRight(successor(newNode.getNumber()));
					// set the new node left pointer to point on his predecessor
					newNode.setLeft(predecessor(newNode.getNumber()));
					
					// if the new node predecessor have no left child
					if(!haveRightChild(newNode.getLeft())) {
						// set the new node to be the predecessor of his successor
						if(newNode.getLeft() != null) {
							newNode.getLeft().setRight(newNode);
						}
					
					// if the new node successor have no right child
					}if(!haveLeftChild(newNode.getRight())) {
						// set the new node to be successor of his predecessor
						if(newNode.getRight() != null) {
							newNode.getRight().setLeft(newNode);	
						}	
					}
					
					/* if the new node have more then one appearance in the bst and they do not have left child,
					 * set the left pointer of every node with same value as pointer to the node predecessor 
					 */
					current = newNode.getRight();
					while((haveRightChild(current) && current.getNumber() == current.getRight().getNumber() 
							&& (current.getRight().getLeft() == null || newNode.getNumber() > current.getRight().getLeft().getNumber()))) {
						current.getRight().setLeft(newNode);
						current = current.getRight();
						
					}
					break;
				}
				
				// else new node value appears already in the bst
			}else {
				// move to the most right node with the same value
				while (haveRightChild(current) && newNode.getNumber() == current.getRight().getNumber()) {
					current = current.getRight();
				}
				// set the new node parent pointer to point on the current node
				newNode.setParent(current);
				// set the new node right pointer to point on the current node right pointer
				newNode.setRight(current.getRight());
				
				// if the current node have right child set the right child parent pointer to point on the new node
				if (haveRightChild(current))
					current.getRight().setParent(newNode);
				
				// set the current node right pointer to point on the new node
				current.setRight(newNode);	
				
				// if the current node do not have left child
				if(!haveLeftChild(current)) {
					//set new node left pointer to point on his predecessor
					newNode.setLeft(current.getLeft());
				// else if the current node have left child and the left child have right child
				}else if(haveRightChild(current.getLeft())) {
					//set new node left pointer to point on current node left subtree max value
					newNode.setLeft(maximum(current.getLeft().getNumber()));
				}else {
					// else set new node left pointer to point on the current left child
					newNode.setLeft(current.getLeft());
				}
					break;
			}
			
		}
		
		manageMedian(keyToInsert, true);
		System.out.println(keyToInsert + " Has been inserted");

		return;
		
	}
	
	
	
	/**
	 * This method delete the node that have the value given by parameter
	 * the method use the helping method deleteNode
	 * time complexity O(h) the h the height of the bst
	 * running time = the running time of the helping method that is O(h)
	 * @param value the value of the node to delete
	 */
	public void delete(int value) {
		    
		
		Node tempPred = predecessor(value);
		Node tempSucc = successor(value);
		this.nodesCount--;
		
		// set the predecessor of the successor of the node 
		// to be deleted to be the node predecessor
		if (tempSucc != null) {
			if (!haveLeftChild(tempSucc) || (tempSucc.getLeft().getNumber() == value && tempSucc.getLeft().getParent() != tempSucc)) {
				tempSucc.setLeft(tempPred);
			}
		}
		
		// set the successor of the predecessor of the node
		// to be deleted to be the node successor
		if (tempPred != null) {
			if (!haveRightChild(tempPred) || (tempPred.getRight().getNumber() == value &&  tempPred.getRight().getParent() != tempPred)) {
				tempPred.setRight(tempSucc);
			}
		}
		manageMedian(value, false);
		setRoot(deleteNode(this.root, value));
		
		System.out.println(value + " Has beed deleted");
		
		
    }

	
 
   /**
    * recursive function to delete node of the bst
    * time complexity O(h) the h the height of the bst
    * @param root - the root of the tree
    * @param key - the key of the node to be deleted
    * @return the current node
    */
    private Node deleteNode(Node root, int key)
    {
        // if the tree is empty or the key is the root key 
    	// and the root is the only node in the tree 
        if ((root == null) || (key == this.root.getNumber() && !haveLeftChild(root) && !haveRightChild(root))) {       	
        	return null;
        }
 
        // recur down the tree 
        if (key < root.getNumber())
            root.setLeft(deleteNode(root.getLeft(), key));
        else if (key > root.getNumber())
            root.setRight(deleteNode(root.getRight(), key));
 
        // if key is same as root's key, then This is the node
        // to be deleted
        else
        {
        	// skip nods with same value
        	if (haveRightChild(root) && (root.getRight().getNumber() == root.getNumber())) {
        		root.setRight(deleteNode(root.getRight(), key));
        	}
        	// if the node to be deleted have both right and left child
        	else if (haveLeftChild(root) && haveRightChild(root)) {
        		
        		// set the value of the nod as the minimum value of his right subtree
        		Node tempMin = minimum(root.getRight().getNumber());	
				root.setNumber(tempMin.getNumber());
				// move down on the right subtree to delete the right subtree minimum value
				root.setRight(deleteNode(root.getRight(), tempMin.getNumber()));
				
				
			// node to delete have only right child
			}else if (haveRightChild(root)) {
				
				// set the right child parent pointer to point on the current root parent
				root.getRight().setParent(root.getParent());
				// skip to the right child to set the current root as the current root right child
				
				root = root.getRight();
				
			// node to delete have only left child
			}else if (haveLeftChild(root)) {
				// set the left child parent pointer to point on the current root parent
				root.getLeft().setParent(root.getParent());
				// skip to the left child to set the current root as the current root left child
				root = root.getLeft();
			// node to delete have no child
			}else {
				// if the current root is right child of his parent
				if (root.getParent() != null && root.getParent().getRight() != null && root.getParent().getRight().equals(root)) {
					root = root.getRight();
				// else it is the left child
				}else
					root = root.getLeft();
			}
        }				
 
        return root;
    }
	
	
	
	
	
	
	
	
	
	// ==================================================== median, successor, predecessor functions ==================================================== // 

	
	
	
	/**
	 * function to manage the median of the bst
	 * time complexity constant
	 * @param newKey - the value of the node that have been inserter to the tree or deleted
	 * @param insert - true if the given key inserter to the tree of false if deleted
	 */
	private void manageMedian(int newKey, boolean insert ) {
		
		if(this.median != null) {
			// number of nods in the tree is even
			if(nodesCount % 2 == 0) {
				// newKey inserted to the tree
				if (insert) {
					if(newKey <= this.median.getNumber()){
						if (this.median.getParent() != null && this.median.getParent().getNumber() == this.median.getNumber()) {
							this.median = this.median.getParent();
						}else {
							if (haveRightChild(this.median)) {
								this.median = predecessor(this.median.getNumber());	
							}else {
								this.median = this.median.getLeft();
							}
						}
					}
					
				// newKey deleted from the tree 
				}else {
					if (newKey >=  this.median.getNumber()) {
						if (this.median.getParent() != null && this.median.getParent().getNumber() == this.median.getNumber()) {
							this.median = this.median.getParent();
						}else{
							if (haveRightChild(this.median)) {
								this.median = predecessor(this.median.getNumber());	
							}else {
								this.median = this.median.getLeft();
							}
						}
					}
				}	
			// the number of nods in the tree is odd
			}else {
				// the key was inserted
				if (insert) {
					if(newKey >= this.median.getNumber() ) {
						if (haveRightChild(this.median) && this.median.getRight().getNumber() != this.median.getNumber() ) {
							this.median = successor(this.median.getNumber());
						}else {
							
							this.median = this.median.getRight();							
						}
					}
				// the key was deleted
				}else {
					if(newKey <= this.median.getNumber()) {
						if (haveRightChild(this.median) && this.median.getRight().getNumber() != this.median.getNumber() ) {
							this.median = successor(this.median.getNumber());
						}else {
							this.median = this.median.getRight();
						}
					}
				}
			}
		}	
	}
	
	/**
	 * This method finding the Successor 
	 * time complexity O(h) the h the height of the bst
	 * @param value the value of the node which you want to find the Successor
	 * @return the node that represent the Successor
	 */
	
	public Node successor(int value)
    {
    
		Node node = findActualNode(value);
		if (node == null)
            return null;
		
		// skip same value nodes
		while (haveRightChild(node) && node.getNumber() == node.getRight().getNumber()) {
			node = node.getRight();
		}
		
		// if the node have right child
        if (haveRightChild(node)) {
        	// the successor is the minimum of his right subtree
            return minimum(node.getRight().getNumber());
        }
        
        // as we traverse left up the tree we traverse smaller values
        // the first node on the right is the next larger number
        Node parent = node.getParent();
        Node current = node;
        while (parent != null && current.equals(parent.getRight()))
        {
            current = parent;
            parent = parent.getParent();
        }
       
        return parent;
      
	 }
		
		
  
    

	/**
	 * This method finding the Predecessor 
	 * time complexity O(h) the h the height of the bst
	 * @param value the value of the node which you want to find the Predecessor
	 * @return the node that represent the Predecessor
	 */

	public Node predecessor(int value)
    {
		
		Node node = findActualNode(value);
		
		if (node == null) 
			return null;
       	
		
		// if the node have left child the predecessor will be the max of his left subtree
		if (haveLeftChild(node))
			return maximum(node.getLeft().getNumber());
		
		
		// as we traverse right up the tree we traverse higher values
        // the first node on the left is the next smaller number
        Node parent = node.getParent();
        Node current = node;
        while (parent != null && current.equals(parent.getLeft()))
        {
            current = parent;
            parent = parent.getParent();
        }
       
        return parent;
               
		
		
    }
	
	/**
	 * function to check if the node have left child
	 * time complexity constant
	 * @param nodeToCheck - the node to check if it have left child
	 * @return boolean true (have left child) or false
	 */
	private boolean haveLeftChild(Node nodeToCheck) {
		if (nodeToCheck != null) {
			if (nodeToCheck.getLeft() != null) {
				if(nodeToCheck.getLeft().getParent() != null) {
					// if the parent pointer of his left pointer is current node
					// then it is his left child
					if (nodeToCheck.getLeft().getParent().equals(nodeToCheck)) {
						return true;
					}
				}
				
			}
		}		
		return false;
	}
	
	/**
	 * function to check if the node have right child
	 * time complexity constant
	 * @param nodeToCheck - the node to check if it have left child
	 * @return boolean true (have right child) or false
	 */
	private boolean haveRightChild(Node nodeToCheck) {
		if (nodeToCheck != null) {
			if (nodeToCheck.getRight() != null) {
				if(nodeToCheck.getRight().getParent() != null) {
					// if the parent pointer of right left pointer is current node
					// then it is his right child
					if (nodeToCheck.getRight().getParent().equals(nodeToCheck)) {
						return true;
					}
				}
				
			}
		}
			
		return false;
	}
	
	
	
	
	// ========================================================== find Node in tree ========================================================== //
	
	/**
	 * function to find if the node with the parameter value exist in the bst
	 * time complexity O(h) the h the height of the bst
	 * @param numberToFind - the value of the bode to be found
	 * @return true if the node exist or false if it is not exist
	 */
	public boolean find(int numberToFind){
		
		Node current = this.root;
		
		while(current != null){
		
			// the node found
			if(current.getNumber() == numberToFind){
				return true;
			
			// the value to be found is greater the the current node value and the current node have right child
			}else if((current.getNumber() < numberToFind) && haveRightChild(current) ) {				
				current = current.getRight();
				
			// the value to be found is less then current node value and the current node have left child
			}else if ((current.getNumber() > numberToFind) && (haveLeftChild(current))){
				current = current.getLeft();
				
			}else {
				return false;
			}
	}
		
		return false;
	}
	
	
	/**
	 * function to find the actual node pointer by the given key
	 * time complexity O(h) the h the height of the bst
	 * @param numberToFind the value of the node to be found
	 * @return the node with the value given by parameter
	 */
	private Node findActualNode(int numberToFind) {
		
		Node current = this.root;
		
		while(current != null){
			if (current.getNumber() == numberToFind) {
				return current;
			}
			else if(haveLeftChild(current) &&(current.getNumber() > numberToFind)) {
				current = current.getLeft();
			}else if (haveRightChild(current) && (current.getNumber() < numberToFind))
				current = current.getRight();
			else
				return null;
		}
		
		return null;
		
	}
	
	
	
	
	
	
	// ====================================================== find minimum and maximum Node ====================================================== //

	
	
	/**
	 * function to find minimum of the subtree which root is the node which holds
	 * the value given by parameter
	 * time complexity O(h) the h the height of the bst
	 * @param currRootValue the value of the root node
	 * @return the minimum value node of the subtree
	 */
	public Node minimum(int currRootValue){
		
		Node currNode = findActualNode(currRootValue);
	
		if ( currNode == null){
			return null;
		}
	 
		// while the current node have left child move to the left
		while(haveLeftChild(currNode)) {
				
			currNode = currNode.getLeft();
			
		}		
		
		return currNode;
	}

	/**
	 * function to find maximum of the subtree which root is the node which holds
	 * the value given by parameter
	 * time complexity O(h) the h the height of the bst
	 * @param currRootValue the value of the root node
	 * @return the maximum value node of the subtree
	 */
	public Node maximum(int currRootValue){
		
		
	  Node currNode = findActualNode(currRootValue);
		
	  if ( currNode == null ){
	    return null;
	  }

	 
	  while(haveRightChild(currNode)){
	    currNode = currNode.getRight();
	  }
	 
	  return currNode;
	}
	
	

	// ==================================================== display preorder, postorder, inorder ==================================================== //
	
	/**
	 * function to display the bst in preorder
	 * time complexity O(n) as the function moves thru every tree node once	
	 * @param currRoot the root of the tree to be displayed
	 */
	public void preorder(Node currRoot){
		
		
		if (currRoot != null) {
			System.out.print(currRoot.getNumber() + " ");
			
			if (haveLeftChild(currRoot)) {
				preorder(currRoot.getLeft());
			}
			if (haveRightChild(currRoot)) {
				preorder(currRoot.getRight());
			}
		}else
			System.out.print("The tree is empty\n");
			
			
		

		
	}
	/**
	 * function to display the bst in inorder
	 * time complexity O(n) as the function moves thru every tree node once
	 * @param currRoot the root of the tree to be displayed
	 */
	public void inorder(Node currRoot){
			
			if(currRoot != null){
				
				if (haveLeftChild(currRoot)) {
					inorder(currRoot.getLeft());
				}
				
				System.out.print(currRoot.getNumber() + " ");
				
				if (haveRightChild(currRoot)) {
					inorder(currRoot.getRight());	
				}
					
			}
			
	}
	
	/**
	 * function to display the bst in postorder
	 * time complexity O(n) as the function moves thru every tree node once
	 * @param currRoot the root of the tree to be displayed
	 */
	public void postorder(Node currRoot){
		
		if(currRoot != null){
			
			if (haveLeftChild(currRoot)) {
			postorder(currRoot.getLeft());
			}
			
			if (haveRightChild(currRoot)) {
			postorder(currRoot.getRight());
			}
			
			System.out.print(currRoot.getNumber() + " ");
		}
		
	}
}