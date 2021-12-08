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
int initList_size(List *list, int size);
int destoryList(List *list);
int isEmpty(List list);
int insert(List *list,int i, type value);
type get(List list,int i);
int deleteByIndex(List *list, int index);
int search(List list, type value);

#endif
