#include "LinkedList.h"
#include <stdio.h>
#include <stdlib.h>

//int initLink(LinkList *head);
//void destoryLink(LinkList *head);
//int headInsert(LinkList *head, type value);
//int tailInsert(LinkList *head, type value);
//int insert(LinkList *head, int index, type value);
//int length(const LinkList *head);
//int search(LinkList *head, type targetValue);
//int removeNode(LinkList *head, type targetValue);
//int isEmpty(LinkList *head);
Node* getANode(type value);

// ��������ָ����ֵ�Ľ�㣬������㷵��
Node* getANode(type value){
	Node *aNode;
	aNode = (Node*)malloc(sizeof(Node));
	if(aNode == NULL){
		return NULL;
	}
	aNode->data = value;
	aNode->next = NULL;
	return aNode;
}

// ��ʼ������
int initLink(LinkList *head){
	head = getANode(0);
	return head == NULL ? 0 : 1;
}

// ��������
void destoryLink(LinkList *head){
	if(head->next == NULL){
		free(head);
		return ;
	}
	else {
		destoryLink(head->next);
		free(head);
	}
}

// ͷ�巨
int headInsert(LinkList *head, type value){
	Node* newNode = getANode(value);
	if(newNode == NULL){
		return 0;
	}
	newNode->next = head->next;
	head->next = newNode;
	head->data += 1;
	return 1;
}

// β�巨
int tailInsert(LinkList *head, type value){
	Node *newNode = getANode(value);
	if(newNode == NULL){
		return 0;
	}
	Node *head_help = head;
	while(head_help->next != NULL){
		head_help = head_help->next;
	}
	head_help->next = newNode;
	head->data += 1;
	return 1;
}

// ����ָ��λ��
int insert(LinkList *head, int index, type value){
	Node *newNode = getANode(value);
	if(newNode == NULL || index < 1 || index > head->data){
		return 0;
	}
	Node *head_help = head;
	for(int i = 0; i < index-1; i++){
		head_help = head_help->next;
	}
	newNode->next = head_help->next;
	head_help->next = newNode;
	head->data += 1;
	return 1;
}

// ��ȡ����ĳ���
int length(const LinkList *head){
	return head == NULL ? -1 : head->data;
}

// ����
int search(LinkList *head, type targetValue){
	Node *head_help = head;
	int count = 0;
	while(head_help->next != NULL){
		head_help = head_help->next;
		count++;
		if(head_help->data == targetValue){
			return count;
		}
	}
	return -1;
	
}

// ɾ��ָ��ֵ�Ľ��
int removeNode(LinkList *head, type targetValue){
	Node *head_help = head;
	while(head_help->next != NULL){
		if(head_help->next->data == targetValue){	
			head_help->next = head_help->next->next;
			head->data -= 1;
			return 1;
		}
		head_help = head_help->next;
	}
	return 0;
}

// �ж������Ƿ�Ϊ��
int isEmpty(LinkList *head){
	return head->next == NULL ? 1 : 0;
}
