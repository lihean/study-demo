package com.example.study.demo;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {

        int[] arr = new int []{2,5,7,2,1,4,4,9,2};

        System.out.println((65536*2)>>>16);
//        quickSort(arr,0,arr.length-1);
    }

    static void quickSort(int[] arr,int start,int end){

        if(start > end) return;

        int temp = arr[start];
        int i = start;
        int j = end;

        while (i != j){
            while (arr[j] > temp && j > i) j--;
            while (arr[i] <= temp && j > i) i++;

            if(j > i){
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }

        arr[start] = arr[i];
        arr[i] = temp;

        quickSort(arr,start,i - 1);
        quickSort(arr,i+1,end);

        System.out.println(Arrays.toString(arr));
    }
}
