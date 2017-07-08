#include <vector>

class NFA_State
{
public:
    NFA_State();
    ~NFA_State();
    add_transition(char requires, NFA_State &destination){
        
    };
    
private:
    std::vector<NFA_State *> char_transition[255];
    std::vector<NFA_State *> epsilon_transition;
};