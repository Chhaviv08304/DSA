class Solution {
public:
    ListNode* sortList(ListNode* head) {
        // Step 1: Base case
        if (head == NULL || head->next == NULL)
            return head;

        // Step 2: Find the middle
        ListNode* slow = head;
        ListNode* fast = head;
        ListNode* prev = NULL;

        while (fast != NULL && fast->next != NULL) {
            prev = slow;
            slow = slow->next;
            fast = fast->next->next;
        }

        // Step 3: Split into two lists
        prev->next = NULL;

        // Step 4: Sort both halves
        ListNode* left = sortList(head);
        ListNode* right = sortList(slow);

        // Step 5: Merge sorted halves
        return merge(left, right);
    }

    ListNode* merge(ListNode* left, ListNode* right) {
        ListNode dummy(0);
        ListNode* tail = &dummy;

        while (left != NULL && right != NULL) {
            if (left->val <= right->val) {
                tail->next = left;
                left = left->next;
            } else {
                tail->next = right;
                right = right->next;
            }

            tail = tail->next;
        }

        if (left != NULL)
            tail->next = left;

        if (right != NULL)
            tail->next = right;

        return dummy.next;
    }
};