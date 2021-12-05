#include "List.h"
#include <stdio.h>
#include <stdlib.h>

//int initList(List *list);
//int destroyList(List *list);
//int isEmpty(List list);
//int add(List *list, type value);
//type get(List list);
//int deleteByIndex(List *list, int index);
//int replace(List *list, int index, type newValue);
//int search(List list, type value);
int initList_size(List *list,int size);
int boostLenth(List *list);

/*
	���ܣ��������鳤��
*/
int boostLenth(List *list){
	List *aList;
	int length = list->length;
	//aList��ʼ��
	initList_size(aList,length+INCREASE_VALUE);
	for(int i = 0; i < length; i++){
		add(aList,list->data[i]);
	}
	if(!destoryList(list)){
		return 0;
	}
	list = aList;
	return 1;
}

/*
	���ܣ���ʼ��List
*/
int initList(List *list){
	return initList_size(list,DEFAULT_lENGTH);
}

int initList_size(List *list,int size){
	if(list->listSize != 0 || list->data != NULL){
		return 0;
	}
	list->listSize = size;
	list->length = 0;
	list->data = (type *) malloc(sizeof(type)*size);
	return 1;
}

/*
	���ܣ�����List
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
	���ܣ��ж�List�Ƿ�Ϊ��
*/
int isEmpty(List list){
	if(list.length != 0){
		return 0;
	}
	return 1;
}

/*
	���ܣ���List�����һ��ֵ
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
	���ܣ������±��ȡһ��ֵ
*/
type get(List list,int i){
	if(i > list.length){

	}
}

/*
	���ܣ������±�ɾ��ָ��������ֵ
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
	���ܣ������±��滻ֵ
*/
int replace(List *list, int index, type newValue){
	if(isEmpty(*list)|| index >= list->length){
		return 0;
	}
	list->data[index] = newValue;
	return 1;
}

/*
	���ܣ�����һ��ֵ�������±�
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
	List list;
	List *list_p = &list;
	initList(list_p);
	for(int i = 0; i < 10; i++){
		add(list_p,i);
	}

	return 0;
}
