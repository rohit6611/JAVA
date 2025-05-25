package com.example.demo.algo;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class AlgoClass {


	public static void selectionSort(int arr[],int n) {
		
		for(int i=0;i<n-1;i++) {
			int minidx=i;
			for(int j=i+1;j<n;j++) {
				if(arr[j]<arr[minidx]) {
					minidx=j;
				}
			}
			int temp=arr[minidx];
			arr[minidx]=arr[i];
			arr[i]=temp;
			
		}
		System.out.println(Arrays.toString(arr));
	}
	
	public static void bubbleSort(int arr[],int n) {
		
		for(int i=0;i<n-1;i++) {
			
			for(int j=0;j<n-i-1;j++) {
				if(arr[j]>arr[j+1]) {
					int temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
			
			
		}
		System.out.println(Arrays.toString(arr));
	}
	
	public static void insertionSort(int arr[],int n) {
		
		for(int i=1;i<n-1;i++) {
			int key=arr[i];
			int j=i-1;
			while(j>=0 && arr[j]>key) {
				arr[j+1]=arr[j];
				j=j-1;
			}
			arr[j+1]=key;
			
		}
		
		System.out.println(Arrays.toString(arr));
	}
	
	public static void merge(int arr[],int low,int high) {
		
		if(low<high) {
			
			int mid=low+(high-low)/2;
			
			merge(arr, low, mid);
			merge(arr, mid+1, high);
			
			sort(arr,low,mid,high);
		}
	}

	public static void quick(int arr[],int low,int high) {
		
		if(low>=high) {
			return;
		}
		int s=low;
		int e=high;
		int mid=s+(e-s)/2;
		int pivot=arr[mid];
		
		while(s<=e) {
			
			while(arr[s]<pivot) {
				s++;
			}
			while(arr[e]>pivot) {
				e--;
			}
			if(s<=e) {
				int temp=arr[s];
				arr[s]=arr[e];
				arr[e]=temp;
				s++;e--;
			}
			
		}
		
		quick(arr,low,e);
		quick(arr,s,high);
		
		
	}
	
	public static int binary(int arr[],int low,int high,int target) {
		
		while(low<=high) {
			
			int mid=low+(high-low)/2;
			if(arr[mid]==target) {
				return mid;
			}
			if(target>arr[mid]) {
				low=mid+1;
			}else {
				high=mid-1;
			}
		}
		return -1;
	}
	
	private static void sort(int[] arr, int low, int mid, int high) {
		int k=low;
		
		int n1=mid-low+1;
		int n2=high-mid;
		int left[]=new int[n1];
		int right[]=new int[n2];
		
		for(int i=0;i<n1;i++) {
			left[i]=arr[low+i];
		}
		for(int j=0;j<n2;j++) {
			right[j]=arr[mid+1+j];
		}
		
		int i=0,j=0;
		while(i<n1 && j<n2) {
			
			if(left[i]<=right[j]) {
				arr[k]=left[i];
				i++;
			}else {
				arr[k]=right[j];
				j++;
			}
			k++;
		}
		while(i<n1) {
			arr[k]=left[i];
			i++;k++;
		}
		while(j<n2) {
			arr[k]=right[j];
			j++;k++;
		}
		
	}
	
}
