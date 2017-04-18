class ParseTreeNode
{
public:
    ParseTreeNode();
    ~ParseTreeNode();
    
    bool is_operator(){
        if(this->value_ == "+" || this->value_ == "*" ||this->value_ == "(" ||this->value_ == ")" ||this->value_ == "." ){
            return true;
        }
        return false;
    }

private:
    char value_;
    ParseTreeNode *left_child_
    ParseTreeNode *right_child_
};