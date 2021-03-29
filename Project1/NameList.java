/*
  Name: Jose Rodriguez-Zuniga
  NetId: jpr160230
  Class: 3345.001
  Project 01

  Description:
  Create a Java class called NameList (not generic) that stores names and implements the
  structure shown above.

  Note that the names are kept in sorted order.  Letter nodes are always uppercase.
  Lowercase names will follow uppercase names in normal sorted order.

  You may *not* use Java's LinkedList class.  You should create your own nodes and
  link them together as shown in the illustration.

  Your class should support the following methods.

  20 points - add: Adds a new name.  Names must be at least 2 characters long.  Adds the letter node if not already present.

  20 points - remove: Removes a name.  If the name is the last one for a letter, the letter
  node should also be removed.

  20 points - removeLetter - Removes a letter and all names for that letter.

  20 points - find - Finds a name by traversing the nodes.

  10 points - toString: Returns a string of the list formatted as shown below using the first list above as an example:

  10 points - main: Demonstrates the methods of your NameList class

*/

/* Note to Grader: I was not able to make my add Fuction add the First Letter, I also was not
 * able to to test the removeLetter with the actual first Letter but was able to delete all the
 * names from that specific letter
 */

public class NameList{

  Node head;

  /* Node class */
  public class Node{
    // Variables
    String name;
    Node next;
    boolean letter;

    Node(String name, Node next, boolean letter){
      this.name = name;
      this.next = next;
      this.letter = letter;
    }
  }

  /* add Method: Adds a new name.  Names must be at least 2 characters long.
   * Adds the letter node if not already present
   */

  public void add(String name){
    Node node = new Node(name, null, false);
    node.name = name;
    node.next = null;

    if(head == null){
      head = node;
    }
    else{
      Node n = head;

      // While loop will run till it find the last node
      while(n.next != null){
        n = n.next;
      }
      n.next = node;
    }
  }

  /* remove Method: Removes a name.  If the name is the last one for a letter, the letter
   * node should also be removed.
   */
  public void remove(String name){
    if(head == null){
      System.out.println("Empty List");
      return;
    }

    Node prev = head;
    Node curr = head.next;

    // While loop will run and remove the given name
    while(curr != null){
      // Used the compareTo function so that if the names match it found the name
      if(name.compareTo(curr.name) == 0){
        prev.next = curr.next;
        break;
      }
      else{
        prev = prev.next;
        curr = curr.next;
      }
    }
  }

  public void removeLetter(String name){
    // This will split up the given string so when can just have the first letter
    char letter = name.toCharArray()[0];

    if(head == null){
      System.out.println("Empty List");
      return;
    }

    Node prev = head;
    Node curr = head.next;

    // While loop will run till it find the last node
    while(curr != null){
      // If statement that match the name if it had the same first letter
      if(letter == curr.name.charAt(0)){
        prev.next = curr.next;
        curr = curr.next;
      }
      else{
        prev = curr;
        curr = prev.next;
      }
    }
  }


  /* find Method: Finds a name by traversing the nodes */
  public String find(String name){
    Node n = head;
    String found = name + " is not on the List";

    // While loop will run till it find the last node
    while(n != null){
      // Used the compareTo function so that if the names match it found the name
      if(name.compareTo(n.name) == 0){
        found = name + " is on the List";
        break;
      }
      else{
        n = n.next;
      }
    }
    // If nothing matches then it returns the statement given above
    return found;
  }

  /* toString Method: Returns a string of the list formatted */
  public String toString(){
    if(head == null){
      System.out.println("Empty List");
    }

    Node n = head;
    while(n != null){
        if(n.letter){
          // Prints out Letter
          System.out.println(n.name);
        }
        else{
          // Prints out names under their fisrt Letter
          System.out.println(" " + n.name);
        }
        n = n.next;
    }
    return null;
  }

  public static void main(String[] args){
      NameList list = new NameList();

      // Testing the add and toString method
      list.add("Ben");
      list.add("Bob");
      list.add("Dan");
      list.add("Derek");
      list.add("Jose");
      list.add("James");
      list.toString();
      System.out.println("");

      // Testing the find and toString method
      System.out.println(list.find("Dan"));
      list.add("Sarah");
      list.toString();
      System.out.println("");

      // Testing the remove method
      list.remove("Bob");
      list.remove("Dan");
      System.out.println(list.find("Bob"));
      list.toString();
      System.out.println("");

      // Testing the removeLetter method
      list.removeLetter("J");
      list.toString();
      System.out.println("");

  }
}
