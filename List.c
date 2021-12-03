#include "List.h"
#include <stdio.h>
#include <stdlib.h>

//int initList(List *list);
//int destroyList(List *list);
//int isEmpty(List list);
//int add(List *list, type value);
//int deleteByIndex(List *list, int index);
//int replace(List *list, int index, type newValue);
//int search(List list, type value);

/*
	功能：增加数组长度
*/
int boostLenth(List *list){
	List *aList;
	aList->listSize = list->listSize + INCREASE_VALUE;
	aList->length = list->length;
	aList = (List*)malloc(sizeof(type)*aList->listSize);
	int length = list->length;
	for(int i = 0; i < length; i++){
		aList->data[i] = list->data[i];
	}
	if(!destroyList(list)){
		return 0;
	}
	list = aList;
	return 1;
}

/*
	功能：初始化List
*/
int initList(List *list){
	if(list->listSize != 0 || list->data != NULL){
		return 0;
	}
	list->listSize = DEFAULT_lENGTH;
	list->length = 0;
	list->data = (type *) malloc(sizeof(type)*DEFAULT_lENGTH);
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
int add(List *list, type value){
	if(list->length >= list->listSize){
		if(!boostLenth(list)){
			return 0;
		}
	}
	list->data[list->listSize++] = value;
	return 1;
}

/*
	功能：根据下标删除指定的数组值
*/
int deleteByIndex(List *list, int index){
	if(isEmpty(*list) || index >= list->length){
		return 0;
	}
	for(int i = index; i < list->length-1; i++){
		list->data[i] = list->data[i+1];
	}
	list->length--;
	return 1;
}

/*
	功能：根据下标替换值
*/
int replace(List *list, int index, type newValue){
	if(isEmpty(*list)|| index >= list->length){
		return 0;
	}
	list->data[index] = newValue;
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

int main(void){
	
}
