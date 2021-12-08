#include "List.h"
#include <stdio.h>
#include <stdlib.h>

//int initList(List *list);
//int initList_size(List *list, int size);
//int destoryList(List *list);
//int isEmpty(List list);
//int insert(List *list,int i, type value);
//type get(List list,int i);
//int deleteByIndex(List *list, int index);
//int search(List list, type value);
int boostLenth(List *list,int newSize);

/*
	仅支持内部使用，不对外
	功能：增加数组长度
*/
int boostLenth(List *list,int newSize){
	int *newData = (type *)malloc(sizeof(type)*newSize);
	if (!newData){
		return 0;
	}
	int length = list->length;
	for(int i = 0; i < length; i++){
		newData[i] = get(*list,i);
	}
	free(list->data);
	list->data = newData;
	return 1;
}

/*
	功能：初始化List
*/
int initList(List *list){
	return initList_size(list,DEFAULT_lENGTH);
}

int initList_size(List *list, int size){
	list->data = (type *) malloc(sizeof(type)*size);
	if(!list->data){
		return 0;
	}
	list->listSize = size;
	list->length = 0;
	return 1;
}

/*
	功能：销毁List
*/
int destoryList(List *list){
	if(list->listSize == 0 || list->data == NULL){
		return 0;
	}
	list->length = 0;
	list->listSize = 0;
	free(list->data);
	list->data = NULL;
	return 1;
}

/*
	功能：判断List是否为空
*/
int isEmpty(List list){
	if(list.length != 0){
		return 0;
	}
	return 1;
}

/*
	功能：向List中添加一个值
*/
int insert(List *list,int i, type value){
	if(list->length >= list->listSize){
		if(!boostLenth(list,list->length + INCREASE_VALUE)){
			return 0;
		}
	}
	else if(i > list->length || i < 0){
		return 0;
	}
	for(int j = list->length++; j >= i; j--){
		list->data[j] = list->data[j-1];
	}
	list->data[i] = value;
	
	return 1;
}

/*
	功能：根据下标获取一个值
*/
type get(List list,int i){
	if(i >= list.length){
		printf("index out of range");
		exit(0);
	}
	else if(i < 0){
		printf("index is minus");
		exit(0);
	}
	return list.data[i];
}

/*
	功能：根据下标删除指定的数组值
*/
int deleteByIndex(List *list, int index){
	if(isEmpty(*list) || index >= list->length || index < 0){
		return 0;
	}
	for(int i = index; i < list->length-1; i++){
		list->data[i] = list->data[i+1];
	}
	list->length--;
	return 1;
}

/*
	功能：查找一个值，返回下标
*/
int search(List list, type value){
	for(int i = 0; i < list.length; i++){
		if(list.data[i] == value){
			return i;
		}
	}
	return -1;
}

