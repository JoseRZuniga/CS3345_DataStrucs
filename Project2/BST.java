/*
  Name: Jose Rodriguez-Zuniga
  NetId: jpr160230
  Class: 3345.001
  Project 02

  Description:
  Create a class called MySearchTree.  MySearchTree will implement a binary
  search tree.  MySearchTree will be a generic class storing a value of the
  generic type.

  It should have the following methods.  The methods should
   all operate on the object making the call (none are static).

   All of the methods should use recursion (except for main).

   10 points
   a) add: Adds a node to the tree containing the passed value.

   10 points
   b) find: Returns true if the value passed is in the tree.

   10 points
   c) leafCount: Returns the count of all of the leaves in the tree.

   10 points
   d) parentCount: Returns the count of all of the parents in the tree.

   10 points
   e) height: Returns the height of the tree.

   10 points
   f) isPerfect: Returns true if the tree is a perfect tree.
      (A perfect tree is filled at every level).

   10 points
   g) ancestors: Returns the ancestor values of the passed value.

   10 points
   h) inOrderPrint: Prints node values using an inorder traversal.

   10 points
   i) preOrderPrint: Prints node values using a preorder traversal.

   10 points
   j) Main: Demonstrates all of the above methods.

*/


public class BST <T extends Comparable<T>> {

  private class Node<T>{
      private Node<T> leftChild;
      private Node<T> rightChild;

      public Node(T data, Node<T> left, Node<T> right){
        leftChild = left;
        rightChild = right;
        this.data = data;
      }

      public Node(T data){
        this(data, null, null);
      }
  }

  private Node<T> root;

  public BST(){
    root = null;
  }

  /* add: Adds a node to the tree containing the passed value */
  public void add(T data){
    root = addNode(data, root);
  }

  private Node<T> addNode(T data, Node<T> root){
    
    if(root == null){
      root = new Node<T>(data, null, null);
      return root;
    }

    if(data.compareTo(root.data) < 0){
      root.leftChild = addNode(data, root.leftChild);
    }
    else if(data.compareTo(root.data) > 0){
      root.rightChild = addNode(data, root.rightChild);
    }

    return root;
  }

  
  public boolean find(T data){
    return search(data, root);
  }  

  private boolean search(T data, Node<T> root){

    if(root == null){
      return true;
    }
    if(root.data == data){
      return true;
    }
    if(data.compareTo(root.data) < 0){
      return search(data, root.leftChild);
    }
    if(data.compareTo(root.data) > 0){
      return search(data, root.rightChild);
    }
    else{
      return false;
    }
  }
  

  /* leafCount: Returns the count of all of the leaves in the tree */
  public int leafCount(){
    return getLeafCount(root);
  }

  private int getLeafCount(Node<T> root){
    if(root == null){
      return 0;
    }
    if(root.leftChild == null && root.rightChild == null){
      return 1;
    }
    else{
      return getLeafCount(root.leftChild) + getLeafCount(root.rightChild);
    }
  }


  public int parentCount(){
    return getParentCount(data, root);
  }

  private int getParentCount(T data, Node<T> root){
    if(root == null || data == null){
      return 0;
    }
    else{
        if(root.leftChild == data || root.rightChild == data)
            return root;
        else {
            if (root.element < data.element) {
                return getParentCount(root.rightChild, data);
            }
            else {
                return getParentCount(root.leftChild, data);
            }
        }
    }
  }

  /* height: Returns the height of the tree */
  public int height(){
    return getHeight(root);
  }

  private int getHeight(Node<T> root){
    if(root == null){
      return 0;
    }

    return 1 + Math.max(getHeight(root.leftChild), getHeight(root.rightChild));
  }

  /* isPerfect: Returns true if the tree is a perfect tree (A perfect tree is filled at every level) */
  public boolean isPerfect(){
    return checkIsPerfect(root);
  }

  private boolean checkIsPerfect(Node<T> root){

    if(root == null){
      return true;
    }

    int leftChildHeight = getHeight(root.leftChild);
    int rightChildHeight = getHeight(root.rightChild);

    if (Math.abs(leftChildHeight - rightChildHeight) <= 1 && checkIsPerfect(root.leftChild) && checkIsPerfect(root.rightChild)){
      return true;
    }
    else{
      return false;
    }
  }

  /* ancestors: Returns the ancestor values of the passed value */
  public boolean ancestors(T data){
    return getAncestors(data, root);
  }

  private boolean getAncestors(T data, Node<T> root){
    if(root == null){
      return false;
    }
    
    if(root.data == data){
      return true; 
    }

    if(getAncestors(data, root.leftChild) || getAncestors(data, root.rightChild)){
      System.out.print(root.data + " ");
      return true;
    }
    else{
      return false;
    }
  }


  /* inOrderPrint: Prints node values using an inorder traversal */
  public void inOrderPrint(){
    inOrder(root);
  }

  private void inOrder(Node<T> root){
    if(root == null){
      return;
    }
    if(root.leftChild != null){
      inOrder(root.leftChild);
    }
    System.out.print(root.data + " ");
    if(root.rightChild != null){
      inOrder(root.rightChild);
    }
  }

  /* preOrderPrint: Prints node values using a preorder traversal */
  public void preOrderPrint(){
    preOrder(root);
  }

  private void preOrder(Node<T> root){
    if(root == null){
      return;
    }
    
    System.out.print(root.data + " ");

    preOrder(root.leftChild);
    preOrder(root.rightChild);
  }

  /* Main: Demonstrates all of the above methods */
  public static void main(String[] args){

    BST<Integer> test = new BST<Integer>();

    test.add(20);
    test.add(10);
    test.add(30);

    test.inOrderPrint();
    System.out.println();
    test.preOrderPrint();
    System.out.println();

    if(test.find(25) == true){
      System.out.println("This is in the Search Tree"); 
    }
    else{
      System.out.println("This is not in the Search Tree");
    }

    if(test.find(55)){
      System.out.println("This is in the Search Tree"); 
    }
    else{
      System.out.println("This is not in the Search Tree");
    }

    System.out.println(test.leafCount());
    System.out.println(test.parentCount());

    test.ancestors(5);
    
    System.out.println(test.height());

    if(test.isPerfect()){
      System.out.println("This is a Perfect Tree"); 
    }
    else{
      System.out.println("This is not a Perfect Tree");
    }

    System.out.println();

    test.add(40);
    test.add(15);
    test.add(5);
    test.add(25);

    test.inOrderPrint();
    System.out.println();
    test.preOrderPrint();
    System.out.println();

    System.out.println(test.leafCount());
    //System.out.println(test.parentCount());

    test.ancestors(5);
    System.out.println();

    System.out.println(test.height());
  }
}
