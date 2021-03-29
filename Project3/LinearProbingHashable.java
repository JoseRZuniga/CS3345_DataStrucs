public class LinearProbingHashable<K, V> {
// initialize size of table and entry counter
int size = 13;
int entryCounter = 0;
// create an entry
private static class Entry<K, V> {
    K tableKey;
    V tableValue;
    String status;
}       

Entry<K, V> table[] = new Entry[size];
// insert new entry into table
public boolean insert(K key, V value) {
    // throw exception if key is null
    if ( key == null ) {
        throw new IllegalArgumentException("Invalid key");
    }
    // rehash if the table is half full
    if ( entryCounter >= ( size / 2) ) {
        rehash();
    }
    // return false if duplicate
    for ( int i = 0; i < table.length; i++ ) {
        if ( table[i] != null) {
            if ( table[i].tableValue.equals(value) == true )
                return false;
        }
    }
    // calculate hash value
    int hashVal = 0;
    String keyToString = key.toString();
    for ( int i = 0; i < keyToString.length(); i++ ) {
        hashVal = 37 * hashVal + keyToString.charAt(i);
    }
    hashVal %= size;
    if ( hashVal < 0 )
        hashVal += size;
    // if entry is empty, insert key and value into the table and return true
    if ( table[hashVal] == null) {
        table[hashVal] = new Entry<K, V>();
        table[hashVal].tableKey = key;
        table[hashVal].tableValue = value;
        entryCounter++;
        return true;            
    }
    // element collision
    else {
        // linear probing
        for ( int i = hashVal + 1; i < table.length; i++ ) {
            // if probe reaches end, restart from 0
            if ( table[i] == null )
                i = 0;
            // if entry is empty, insert key and value and return true
            if ( table[i].tableKey == null ) {
                table[hashVal] = new Entry<K, V>();
                table[hashVal].tableKey = key;
                table[i].tableValue = value;
                entryCounter++;
                return true;
            }
        }
    }
    return false;               
}

public V find(K key) {
    // look for the key
    for ( int i = 0; i < table.length; i++ ) {
        // if key is found, return its value
        if ( table[i].tableKey.equals(key) == true )
            return table[i].tableValue;
    }
    // if key is not found, return null
    return null;

}

public boolean delete(K key) {
    // look for the key
    for ( int i = 0; i < table.length; i++) {
        // if key is found, change entry's status and return true
        if ( table[i].tableKey.equals(key) == true) {
            table[i].status = "deleted";
            return true;
        }
    }
    // if key is not found, return false
    return false;
}

private void rehash() {

    size = size * 2;




}

public int getHashValue(K key) {
    if ( key == null ) {
        throw new IllegalArgumentException("Invalid key");
    }
    // search the table
    for ( int i = 0; i == table.length; i++) {
        // if the key exists, exit the loop
        if ( table[i].tableKey.equals(key) == true )
            break;
        // if key is not in the table, return -1
        else if ( table[i].tableKey.equals(key) == false && i == table.length )
            return -1;
    }
    // calculate 
    int hashVal = 0;
    String keyToString = key.toString();
    for ( int i = 0; i < keyToString.length(); i++ ) {
        hashVal = 37 * hashVal + keyToString.charAt(i);
    }
    hashVal %= size;
    if ( hashVal < 0 )
        hashVal += size;
    return hashVal;

}

public int getLocation(K key) {
    if ( key == null ) {
        throw new IllegalArgumentException("Invalid key");
    }
    // search for the key
    for ( int i = 0; i < table.length; i++) {
        // return its position if found
        if ( table[i].tableKey.equals(key) == true)
            return i;           
    }
    // if not found, return -1
    return -1;
}

public String toString() {
    int i = 0;
    // call recursive function
    return toString(i); 
}
// print string recursively
private String toString(int i) {
    // return blank at the end of the table
    if ( i == table.length )
        return "";
    // print null when entry is empty
    if (table[i].tableValue.equals(null) == true)
        System.out.println(i + " " + "null");
    // print value
    else
        System.out.println(i + " " + table[i].tableValue);
    i++;
    return toString(i);
}

public static void main(String[] args) {

    LinearProbingHashable<String, Integer> hashTable = new LinearProbingHashable<String, Integer>();

    hashTable.insert("1", 10);
    hashTable.insert("2", 13);
    hashTable.insert("3", 25);
    hashTable.insert("4", 60);
    hashTable.insert("5", 113);
    hashTable.insert("6", 225);
    
    hashTable.toString();

    hashTable.getHashValue("4");

}


}