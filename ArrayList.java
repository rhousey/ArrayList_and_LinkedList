//Rebecca Housey
//CSE 017 
//ALA #9 
import java.util.Iterator; 

public class ArrayList<E> implements List<E>{
    private E[] elements; 
    private int size; 
    
    public ArrayList(){ 
        elements = (E[]) new Object[10];
        size = 0; 
    }
    
    public ArrayList(E[] objects){
        elements = (E[]) new Object[objects.length]; 
        for(int i =0; i < objects.length; i++){
            add(objects[i]); 
        }
    }
    
    @Override
    public int size(){ 
        return size; 
    }
    @Override
    public void clear(){ 
        elements = (E[]) new Object[size]; 
        size = 0; 
    }
        private void ensureCapacity(){ 
        if(size >= elements.length){
            E[] newElements = (E[])new Object[(int)(size*1.5)]; 
            System.arraycopy(elements, 0, newElements,0, size); 
            elements = newElements; 
        } 
    }
        
    private void checkIndex(int index){ 
        if(index > size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: "+ size); 
    }
    
    public void trimToSize(){ 
        if(size != elements.length){ 
            E[] newElements = (E[]) new Object[size]; 
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements; 
        } 
    }
    
    @Override
    public boolean contains(Object e){ 
        for(int i = 0; i< size; i++)
            if(e.equals(elements[i]))
                return true; 
        return false; 
    }
    
    @Override
    public int indexOf(Object e){ 
        for(int i = 0; i < size; i++)
            if(e.equals(elements[i]))
                return i; 
        return -1; 
    }
    
    @Override
    public int lastIndexOf(Object e){
        for(int i = size-1; i >= 0; i--){
        if(e.equals(elements[i])){
            return i;
        }
        }
        return -1; 
    }
    
    @Override
    public void add(int index, E item){
        checkIndex(index); 
        ensureCapacity(); 
        for(int i = size - 1; i >= index; i--)
            elements[i+1] = elements[i]; 
        elements[index] = item; 
        size++;
    }
    
    @Override
    public E get(int index){ 
        checkIndex(index); 
        return elements[index]; 
    }
    
    @Override
    public E remove(int index){
        checkIndex(index); 
        E item= elements[index]; 
        for(int i = index; i < size-1; i++)
            elements[i] = elements[i+1]; 
        elements[--size] = null;
        return item; 
    }
    
    @Override
    public E set(int index, E item){
        checkIndex(index); 
        E oldItem= elements[index]; 
        elements[index] = item; 
        return oldItem; 
    }
    
    @Override
 public String toString(){ 
        String output = "["; 
        for(int i =0; i < size; i++){ 
            output += elements[i].toString(); 
            if(i<size-1)
                output += ", "; 
        }
            return output + "]";
    }
    
    @Override
    public Iterator<E> iterator(){
        return new ArrayListIterator(); 
    }
    
    private class ArrayListIterator implements Iterator<E>{
        private int current = -1; 
        @Override
        public boolean hasNext(){ 
            return current<size ;
    }
        @Override
        public E next(){ 
            return elements[++current]; 
        }
        @Override
        public void remove(){
            if(current < 0){
             //s   throw (IllegalStateException); 
            System.out.println("Illegal state exception"); 
        } 
         ArrayList.this.remove(current--); 
        }
    }
      // method that returns the number of comparisons made while using 
   //iterators to find an item in the *ARRAY LIST*
    public int searchComparisons(E item){ 
        int comparisons = 0; 
        Iterator i = new ArrayList.ArrayListIterator();
        while(i.hasNext()){
            if(item.equals(i.next())){
                return ++comparisons; 
            }
            comparisons++; 
        }
        return comparisons; 
    }
}
