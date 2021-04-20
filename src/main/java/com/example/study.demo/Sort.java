package com.example.study.demo;

import java.util.Arrays;

public class Sort {

    public static void main(String[] args) {
        int[] arr = new int []{-100,-98,-1,2,3,4};

        int num1 = Integer.MIN_VALUE,num2 = Integer.MIN_VALUE,num3 = Integer.MIN_VALUE,num4 = Integer.MAX_VALUE,num5 = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < num5){
                num4 = num5;
                num5 = arr[i];
            }else if(arr[i] < num4){
                num4 = arr[i];
            }

            if(arr[i] > num1){
                num3 = num2;
                num2 = num1;
                num1 = arr[i];
            }else if(arr[i] > num2){
                num3 = num2;
                num2 = arr[i];
            }else if(arr[i] > num3){
                num3 = arr[i];
            }
        }

        System.out.println(num1*num4*num5 > num1*num2*num3?num1*num4*num5:num1*num2*num3);
    }

    public static void quickSort(int[] array,int start,int end){
        if(start > end) return;

        int i = start;
        int j = end;
        int current = array[start];

        while (i != j){
            while (j > i && array[j] > current){
                j--;
            }
            while (j > i && array[i] <= current){
                i++;
            }

            if(j > i){
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        array[start] = array[i];
        array[i] = current;

        quickSort(array,start,i-1);
        quickSort(array,i+1,end);

//        System.out.println(Arrays.toString(array));
    }
}
