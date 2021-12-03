#ifndef _LIST_H_
#define _LIST_H_

#define DEFAULT_lENGTH 32
#define INCREASE_VALUE 16

typedef int type;

typedef struct{
	int listSize;
	int length;
	type *data;
}List;


int initList(List *list);
int destroyList(List *list);
int isEmpty(List list);
int add(List *list,type value);
int deleteByIndex(List *list, int index);
int replace(List *list, int index, type newValue);
int search(List list, type value);

#endif
