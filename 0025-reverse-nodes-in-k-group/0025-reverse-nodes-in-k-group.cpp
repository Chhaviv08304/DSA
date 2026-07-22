class Solution {
public:
    ListNode* reverseKGroup(ListNode* head, int k) {

        ListNode* temp = head;
        int cnt = 0;

        // check if k nodes exist
        while(cnt < k){
            if(temp == NULL){
                return head;
            }
            temp = temp->next;
            cnt++;
        }

        // reverse remaining list
        ListNode* prevNode = reverseKGroup(temp, k);

        temp = head;
        cnt = 0;

        // reverse current k nodes
        while(cnt < k){
            ListNode* next = temp->next;
            temp->next = prevNode;
            prevNode = temp;
            temp = next;
            cnt++;
        }

        return prevNode;
    }
};