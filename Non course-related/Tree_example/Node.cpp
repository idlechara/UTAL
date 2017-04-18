#include <iostream>
#include <stack>

class Node
{
private:
    Node *left;
    Node *right;
    
public:
    int val;
    Node(int value = 0){
        this->val = value;
        this->left = NULL;
        this->right = NULL;
    };
    ~Node(){};

    void print(){
        if(this->left != NULL){
            this->left->print();
        }
        std::cout << this->val << std::endl;
        if(this->right != NULL){
            this->right->print();
        }
    }

    void add(int target){
        if(target <= val){
            if(this->left == NULL)
                this->left = new Node(target);
            else
                this->left->add(target);
        }
        else{
            if(this->right == NULL)
                this->right = new Node(target);
            else
                this->right->add(target);
        }
    }

    void add_2(int target){
        std::stack<Node *> stack;
        stack.push(this);

        do{
            Node *a = stack.top();
            stack.push(a->right);
            stack.push(a->left);
        }while(a->left == NULL);
        
        std::cout << "valor" << a->val << std::endl;

        stack.pop();
        Node *a = stack.top();
        std::cout << "valor" << a->val << std::endl;

        // if(target <= val){
        //     if(this->left == NULL)
        //         this->left = new Node(target);
        //     else
        //         this->left->add(target);
        // }
        // else{
        //     if(this->right == NULL)
        //         this->right = new Node(target);
        //     else
        //         this->right->add(target);
        // }
    }
};