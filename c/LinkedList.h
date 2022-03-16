#ifndef _LINKED_LIST_H_
#define _LINKED_LIST_H_

typedef int type;

typedef struct Node{
	type data;
	struct Node *next;
}Node,LinkList;

int initLink(LinkList *head);
void destoryLink(LinkList *head);
int headInsert(LinkList *head, type value);
int tailInsert(LinkList *head, type value);
int insert(LinkList *head, int index, type value);
int length(const LinkList *head);
int search(LinkList *head, type targetValue);
int removeNode(LinkList *head, type targetValue);
int isEmpty(LinkList *head);


#endif
