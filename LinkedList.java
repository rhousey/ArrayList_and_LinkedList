//Rebecca Housey
//CSE 017 
//ALA #9 
import java.util.Iterator;
public class LinkedList<E> implements List<E> {
    private Node<E> head, tail; 
    private int size; 
    
    public LinkedList(){ 
        head = tail = null;
        size = 0; 
    }
    
    public LinkedList(E[] elements){ 
        for(int i =0; i< elements.length; i++){ 
            add(elements[i]); 
        }
    }
    //Node class (inner)
    private class Node<E>{
        E value; 
        Node<E> next; 
        Node(E item){
            value = item; 
            next = null;
        }
    }
    
  @Override  
    public int size(){ 
        return size; 
    }
    //getting element at the head
    public E getFirst(){ 
        if(size==0){
            return null;
        }
        else{
        return head.value; 
    }
    }
    //getting an element at the end
    public E getLast(){ 
        if(size == 0){ 
            return null; 
        } 
        else{ 
            return tail.value; 
        }
    }
    //adding an element at the head
    public void addFirst(E item){ 
        Node<E> newNode = new Node<E>(item); 
        newNode.next = head; 
        head = newNode; 
        size++; 
        if(tail == null){ 
            tail = head; 
        }
    }
    //adding an element to the end
    public void addLast(E item){ 
        Node<E> newNode = new Node<>(item); 
        if(tail == null){ 
            head = tail = newNode; 
        }
        else{
            tail.next = newNode; 
            tail = newNode; 
    }
        size ++; 
    }
    
    @Override
    public void add(int index, E item){
        if(index == 0)
            addFirst(item); 
        else if(index>= size)
            addLast(item); 
        else{
            Node<E> current = head; 
            for(int i =0; i < index-1; i++){ 
                current = current.next; 
            }
            Node<E> temp = current.next; 
            Node<E> newNode = new Node<>(item); 
            current.next = newNode; 
            newNode.next = temp; 
            size++;
        }
    }
    //removing element from head
    public E removeFirst(){ 
        if(size ==0){
            return null;
        }
        Node<E> temp = head; 
        head = head.next; 
        size--; 
        if(head == null){ 
            tail = null; 
        }
        return temp.value;
    }
    //removing element from end
    public E removeLast(){
        if(size ==0){
            return null;
        }
        Node<E> current = head; 
        for(int i = 0; i < size-2; i++)
            current = current.next; 
            Node<E> temp = tail; 
        if(head == tail){
            head = tail=null; 
            size = 0; 
        } 
        else{ 
            tail = current; 
            tail.next = null; 
            size--; 
        }
        return temp.value; 
    }
    
   
    @Override
    public E remove(int index){ 
        if(index < 0|| index>= size){ 
            return null; 
        }
        else if(index == 0){ 
            return removeFirst(); 
        }
        else if(index == size - 1){
            return removeLast(); 
        }
        else{ 
        Node<E> temp; 
        Node<E> current = head; 
        for(int i =0; i <index-1; i++)
        current = current.next; 
        temp = current.next; 
        current.next = temp.next ;
        size--; 
        return temp.value; 
    }
    }
    
    @Override
    public void clear(){
        size = 0; 
        head = tail = null;
    }
    
    @Override
    public String toString(){ 
        String output = "["; 
        Node <E> current = head; 
        for(int i =0; i< size; i++){ 
            output+= current.value; 
            current = current.next; 
            if(i<size-1)
                output+= ", ";
        }
        output += "]"; 
        return output; 
        }
    
    private void checkIndex(int index){ 
        if(index >= size|| index<0){ 
            throw new IndexOutOfBoundsException("Index: " + index  + ", Size: " + size); 
        }
    }
    
    @Override
    public E get(int index){
        if(size ==0)
            return null; 
        checkIndex(index); 
        Node<E> current = head; 
        for(int i =0; i< index; i++){ 
            current = current.next; 
        }
            return current.value;
    }
    
    @Override
    public E set(int index, E item){ 
        if(index< 0 || index>= size)
            return null; 
        Node<E> current = head; 
        for(int i =0; i< index; i++)
            current = current.next; 
        E temp= current.value; 
        current.value = item; 
        return temp; 
    } 
    
    @Override
   public boolean contains(Object e){ 
       Node<E> current = head; 
       for(int i =0; i< size; i++){
           if(e.equals(current.value)){
               return true;
           }
       }
       return false; 
   } 
   
    @Override
   public int indexOf(Object e){ 
       Node<E> current = head; 
       for(int i =0; i< size; i++){ 
           if(current.value.equals(e))
               return i; 
           current = current.next; 
       }
           return -1; 
   }
   
    @Override
   public int lastIndexOf(Object e){
       for(int i = size-1; i>= 0; i--){
           if(get(i).equals(e))
               return i; 
       } 
       return -1; 
   }
   
    @Override
   public Iterator<E> iterator(){
    return new LinkedListIterator(); 
}
   //Linked List iteration
   private class LinkedListIterator implements Iterator<E>{
       private Node<E> current = head; 
       
      
       @Override
       public boolean hasNext(){ 
           return current != null; 
       }
       @Override
       public E next(){ 
           E temp = current.value; 
           current = current.next; 
           return temp; 
       }
       @Override
       public void remove(){ 
           if(current == null)
               throw new IllegalStateException(); 
               LinkedList.this.remove(indexOf(current.value)); 
           }
   }
   // method that returns the number of comparisons made while using 
   //iterators to find an item in the *LINKED LIST*
   public int searchComparisons(E item){
       int comparisons = 0; 
       Iterator i = new LinkedList.LinkedListIterator(); 
       while(i.hasNext()){
           if(item.equals(i.next())){            
              return ++comparisons; 
           }
           comparisons++; 
       }
       return comparisons;
   }
}
