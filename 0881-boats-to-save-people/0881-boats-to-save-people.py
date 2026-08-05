class Solution:
    def numRescueBoats(self, people: List[int], limit: int) -> int:
        # Sort people from lightest to heaviest
        people.sort()
        
        left = 0
        right = len(people) - 1
        boats = 0
        
        while left <= right:
            # If the lightest and heaviest can share a boat
            if people[left] + people[right] <= limit:
                left += 1  # Lightest person gets on
                
            # Heaviest person always gets on the boat
            right -= 1
            boats += 1  # Increment boat count
            
        return boats
