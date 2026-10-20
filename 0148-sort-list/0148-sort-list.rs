// Definition for singly-linked list.
// #[derive(PartialEq, Eq, Clone, Debug)]
// pub struct ListNode {
//   pub val: i32,
//   pub next: Option<Box<ListNode>>
// }
// 
// impl ListNode {
//   #[inline]
//   fn new(val: i32) -> Self {
//     ListNode {
//       next: None,
//       val
//     }
//   }
// }
impl Solution {
    pub fn sort_list(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        if head.as_ref().map_or(true, |node| node.next.is_none()) {
            return head;
        }

        let mut len = 0;
        let mut current = head.as_ref();

        while let Some(node) = current {
            len += 1;
            current = node.next.as_ref();
        }

        let mut left = head;
        let mut prev = &mut left;

        for _ in 0..len / 2 {
            prev = &mut prev.as_mut().unwrap().next;
        }

        let right = prev.take();

        let sorted_left = Self::sort_list(left);
        let sorted_right = Self::sort_list(right);

        Self::merge(sorted_left, sorted_right)
    }

    fn merge(
        mut left: Option<Box<ListNode>>,
        mut right: Option<Box<ListNode>>,
    ) -> Option<Box<ListNode>> {
        let mut dummy = Box::new(ListNode::new(0));
        let mut tail = &mut dummy;

        while left.is_some() && right.is_some() {
            let take_left =
                left.as_ref().unwrap().val <= right.as_ref().unwrap().val;

            let node = if take_left {
                let mut node = left.take().unwrap();
                left = node.next.take();
                node
            } else {
                let mut node = right.take().unwrap();
                right = node.next.take();
                node
            };

            tail.next = Some(node);
            tail = tail.next.as_mut().unwrap();
        }

        tail.next = if left.is_some() {
            left
        } else {
            right
        };

        dummy.next
    }
}