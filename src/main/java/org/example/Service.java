package org.example;

import java.util.Arrays;

public class Service implements StringList{
    private  Integer capasity = 0;
    private Integer[] element = new Integer[10];

    @Override
    public Integer add(Integer item) {
        element[capasity] = item;
        capasity ++;
        return item;
    }
    @Override
    public Integer add(int index, Integer item) {
        checkIten(item);
        checkIndex(index);
        if (capasity >= element.length) {
            throw  new IllegalArgumentException("Нет места");
            // grow();  3е задание
        }
       // System.arraycopy(element,index,element,index +1,capasity-1);// 3е задание
       // capasity++;
        for (int i = element.length - 1; i >= index+1; i--) {
            element[i] = element[i-1];
        }
        return element[index] = item;
    }

    //3е задание:
    private void grow() {
        Integer[] newElement = new Integer[(int) (element.length * 1.5D)];
        System.arraycopy(element,0,newElement,0,capasity);
        this.element = newElement;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkIten(item);

        if (index > capasity) {
            throw new ArrayIndexOutOfBoundsException();
        }
        element[index] = null;
        return element[index] = item;
    }

    @Override
    public Integer remove(Integer item) throws DataException {
        for (int i = 0; i < capasity; i++) {
            if (element[i] == item) {
                for (int i1 = i; i1 < capasity-1; i1++) {
                    element[i] = element[i+1];
                }
                capasity --;
                return item;
            }
        }throw new DataException();
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);
        Integer element1 = element[index];
        if (index+1 < capasity){
            for (int i = index; i <capasity-1; i++) {
                element[i] = element[i+1];
            }}
        element[capasity] = null;
        capasity --;
        return element1;
    }

    @Override
    public boolean contains(Integer item) {
       checkIten(item);
       Integer[]  arrayForSearch = toArray();
       sortInsertion(arrayForSearch);  //3е задание: mergeSort( arrayForSearch);
       int min = 0;
       int max = arrayForSearch.length -1;
        while (min <= max) {
            int mid = (min + max)/2;
            if (item.equals(arrayForSearch[mid])) {
                return true;
            }
            if (item < arrayForSearch[mid]) {
                max = mid - 1;
            } else {
                min = mid +1;
            }
        }
        return  false;
    }

    @Override
    public int indexOf(String item) {
        return 0;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < element.length; i++) {
            if (element[i] == item) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = capasity -1; i <=0; i--) {
            if (element[i] == item) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return element[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (size() != otherList.size()) {
            return false;
        }
        for (Integer i = 0; i < capasity; i++) {
            if (!get(i).equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return capasity;
    }

    @Override
    public boolean isEmpty() {
        return size() ==0;
    }

    @Override
    public void clear() { Arrays.fill(element, 0);
        capasity =0;

    }

    @Override
    public Integer[] toArray() {
        Integer[] element1;
        return element1 = element.clone();
    }
    private void checkIten(Integer iten) {
        if (iten == null) {
            throw new IllegalArgumentException("Список не может содеожать пустоту");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > capasity) {
            throw new IllegalArgumentException("Индекс должен быть в пределах массива");
        }
    }
    private static void sortInsertion(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j= i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j-1];
                j--;
            }
            arr[j]= temp;
        }
    }
// 3е задание
    private static void mergeSort(Integer[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[arr.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }
    public static void merge(Integer[] arr, Integer[] left, Integer[] right) {

        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }
}
